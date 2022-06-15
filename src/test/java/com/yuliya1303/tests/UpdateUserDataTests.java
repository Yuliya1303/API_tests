package com.yuliya1303.tests;

import com.yuliya1303.lombok.User;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static com.yuliya1303.tests.Specs.responseSpec;
import static com.yuliya1303.tests.Specs.requestSpec;
import static org.junit.jupiter.api.Assertions.*;

public class UpdateUserDataTests {
    @Test
    void testUserDataIsUpdatedWithValidData() {

        String bodyCreateUser = "{ \"name\": \"Mariya\", " +
                "\"job\": \"QA Engineer\" }";

        User data = given()
                .spec(requestSpec)
                .body(bodyCreateUser)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .spec(responseSpec)
                .log().body()
                .extract().as(User.class);

        String userId = data.getId();

        String bodyNewJobValue = "{ \"name\": \"Yuliya\", " +
                "\"job\": \"Automation QA\" }";

        User updatedData = given()
                .spec(requestSpec)
                .body(bodyNewJobValue)
                .put("https://reqres.in/api/users/"+ userId)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(User.class);

        assertEquals("Yuliya", updatedData.getName());
        assertEquals("Automation QA", updatedData.getJob());
        assertNotEquals(null, updatedData.getUpdatedAt());
    }
}
