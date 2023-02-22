package request.test;

import BasePages.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Get_player_info_by_id extends BaseTest {

    @Test(description = "check user info by id")
    public void test1() {
        String userId = "{\n" +
                "  \"playerId\": 1\n" +
                "}";

        Response response = given().contentType(ContentType.JSON)
                .body(userId)
                .post("/get");

        response.then().assertThat().statusCode(200);
    }

    @Test(description = "check user info by invalid id")
    public void test2() {
        String userId = "{\n" +
                "  \"playerId\": -1\n" +
                "}";

        Response response = given().contentType(ContentType.JSON)
                .body(userId)
                .post("/get");

        response.then().assertThat().statusCode(404);
    }
}
