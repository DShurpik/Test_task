package requres.test;

import BasePages.BaseTest;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;
import requres.api.Players;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class End_to_end_tests extends BaseTest {

    @Test(description = "create user valid data", priority = 1)
    public void test1() {
        JsonObject jsonObject = new JsonParser().parse(getJson("user")).getAsJsonObject();

        Response response = given().get("/create/" + getEDITOR() +
                "?age=" + jsonObject.get("age").getAsString() +
                "&gender=" + jsonObject.get("gender").getAsString() +
                "&login=" + jsonObject.get("login").getAsString() +
                "&password=" + jsonObject.get("password").getAsString() +
                "&role=" + jsonObject.get("role").getAsString() +
                "&screenName=" + jsonObject.get("screenName").getAsString());
        response.then()
                .assertThat().statusCode(200);

        setId(response.then().extract().jsonPath().getInt("id"));
    }

    @Test(description = "check user was created", priority = 2)
    public void test2() {
        String userId = "{\n" +
                "  \"playerId\": " + getId() + "\n" +
                "}";

        Response response = given().contentType(ContentType.JSON)
                .body(userId)
                .post("/get");

        response.then().assertThat().statusCode(200);
    }

    @Test(description = "Check user in list users", priority = 3)
    public void test3() {
        Response response = given()
                .get("/get/all");

        response.then().assertThat().statusCode(200);

        List<Players> users = given()
                .when()
                .contentType(ContentType.JSON)
                .get("/get/all")
                .then()
                .extract().body().jsonPath().getList("players", Players.class);

        List<Integer> id = new ArrayList<>();
        for (Players p : users) {
            id.add(p.getId());
        }
        Assert.assertTrue(id.contains(getId()));
    }

    @Test(description = "delete user", priority = 4, enabled = true)
    public void test4() {
        String str = "{\n" +
                "  \"playerId\": "+ getId() +"\n" +
                "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(str)
                .delete("/delete/" + getEDITOR());

        response.then().assertThat().statusCode(204);
    }
}
