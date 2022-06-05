package com.yuliya1303.tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class UpdateUserDataTests {
    @Test
    void testUserDataIsUpdatedWithValidData() {

        String bodyCreateUser = "{ \"name\": \"Yuliya\", " +
                "\"job\": \"QA Engineer\" }";

        String userId = given()
                .body(bodyCreateUser)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .extract().path("id").toString();

        String bodyNewJobValue = "{ \"name\": \"Yuliya\", " +
                "\"job\": \"Automation QA\" }";

        given()
                .body(bodyNewJobValue)
                .contentType(JSON)
                .when()
                .put("https://reqres.in/api/users/"+ userId)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("Yuliya"))
                .body("job", is("Automation QA"))
                .body("updatedAt", not(nullValue()));
    }
}
