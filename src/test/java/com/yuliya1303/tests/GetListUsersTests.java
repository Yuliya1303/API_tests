package com.yuliya1303.tests;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetListUsersTests {
    @Test
    void testTotalUsersPerPageIsCorrect() {
        get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("per_page", is(6));
    }

    @Test
    void testTotalUsersPerPageEqualsUsersFromData() {
        ArrayList actualUsersPerPageList = get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .extract().path("data.id");

        int actualUsersNumberPerPage = actualUsersPerPageList.size();

        int expectedUsersNumberPerPage = get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .extract().path("per_page");

        assertEquals(expectedUsersNumberPerPage, actualUsersNumberPerPage);
    }

    @Test
    void testUser7HasCorrectEmail() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("data.email", hasItem("michael.lawson@reqres.in"));
    }
}
