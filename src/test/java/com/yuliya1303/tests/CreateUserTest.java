package com.yuliya1303.tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class CreateUserTest {
    @Test
    void testUserIsCreatedWithValidData() {
        String body = "{ \"name\": \"Yuliya\", " +
                "\"job\": \"QA Engineer\" }";

        given()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("Yuliya"))
                .body("job", is("QA Engineer"))
                .body("id", not(nullValue()))
                .body("createdAt", not(nullValue()));
    }
}
