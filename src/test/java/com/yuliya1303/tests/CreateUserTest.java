package com.yuliya1303.tests;

import com.sun.jdi.IntegerValue;
import com.yuliya1303.lombok.LombokUserData;
import com.yuliya1303.lombok.User;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static com.yuliya1303.tests.Specs.responseSpec;
import static com.yuliya1303.tests.Specs.requestSpec;
import static org.junit.jupiter.api.Assertions.*;

public class CreateUserTest {
    @Test
    void testUserIsCreatedWithValidData() {
        String body = "{ \"name\": \"Yuliya\", " +
                "\"job\": \"QA Engineer\" }";

        User data = given()
                .spec(requestSpec)
                .body(body)
                .when()
                .post("/users")
                .then()
                .spec(responseSpec)
                .log().body()
                .extract().as(User.class);

        assertNotEquals(null, data.getId());
        assertEquals("Yuliya", data.getName());
        assertEquals("QA Engineer", data.getJob());
        assertNotEquals(null, data.getCreatedAt());
    }
}
