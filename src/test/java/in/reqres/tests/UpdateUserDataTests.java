package in.reqres.tests;

import in.reqres.models.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static in.reqres.helpers.AllureRestAssuredFilter.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static in.reqres.helpers.Specs.responseSpec;
import static in.reqres.helpers.Specs.requestSpec;
import static org.junit.jupiter.api.Assertions.*;

public class UpdateUserDataTests {
    @Test
    @DisplayName("Check that user data is updated with valid data")
    void testUserDataIsUpdatedWithValidData() {

        User bodyCreateUser = new User();
        bodyCreateUser.setName("Mariya");
        bodyCreateUser.setJob("QA Engineer");

        String newName = "Yuliya";
        String newJob = "QA Engineer";

        User bodyNewJobValue = new User();
        bodyNewJobValue.setName(newName);
        bodyNewJobValue.setJob(newJob);

        User data = given()
                .filter(withCustomTemplates())
                .spec(requestSpec)
                .body(bodyCreateUser)
                .when()
                .post("/users")
                .then()
                .spec(responseSpec)
                .log().body()
                .extract().as(User.class);

        String userId = data.getId();

        User updatedData = given()
                .filter(withCustomTemplates())
                .spec(requestSpec)
                .body(bodyNewJobValue)
                .put("/users/"+ userId)
                .then()
                .log().body()
                .statusCode(200)
                .extract().as(User.class);

        assertEquals(newName, updatedData.getName());
        assertEquals(newJob, updatedData.getJob());
        assertNotEquals(null, updatedData.getUpdatedAt());
    }
}
