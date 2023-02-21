package requres.test;

import BasePages.BaseTest;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import requres.api.getCreateUser.UserData;

import java.util.List;

import static io.restassured.RestAssured.given;

public class test_create_user extends BaseTest {


    @Test(description = "create user valid data") //DONE
    public void test1() {

        UserData userData = new UserData(){{
            setAge("25");
            setGender("male");
            setLogin("damavik1");
            setPassword("d1234567");
            setRole("admin");
            setScreenName("damavik1");
        }};

        Response response = given().get("/create/" + getEDITOR() +
                "?age=" + userData.getAge() +
                "&gender=" + userData.getGender() +
                "&login=" + userData.getLogin() +
                "&password=" + userData.getPassword() +
                "&role=" + userData.getRole() +
                "&screenName=" + userData.getScreenName());

        response.then()
                .assertThat().statusCode(200);

        System.out.println(response.then().extract().jsonPath().getInt("id"));
    }

    @Test(description = "create user with invalid data age < then 16") // WORKING
    public void test2() {

        UserData userData = new UserData(){{
            setAge("16"); // BUG, age = 16 registration will be success
            setGender("male");
            setLogin("damavik12");
            setPassword("d1234567");
            setRole("admin");
            setScreenName("damavik1");
        }};

        Response response = given().get("/create/" + getEDITOR() +
                "?age=" + userData.getAge() +
                "&gender=" + userData.getGender() +
                "&login=" + userData.getLogin() +
                "&password=" + userData.getPassword() +
                "&role=" + userData.getRole() +
                "&screenName=" + userData.getScreenName());

        response.then()
                .assertThat().statusCode(400);
    }


    @Test(description = "create user valid data")
    public void test3() {
        JsonObject jsonObject = new JsonParser().parse(getJson("user")).getAsJsonObject();

        UserData userData = new UserData(){{
            setAge(jsonObject.get("age").getAsString());
            setGender(jsonObject.get("gender").getAsString());
            setLogin(jsonObject.get("login").getAsString());
            setPassword(jsonObject.get("password").getAsString());
            setRole(jsonObject.get("role").getAsString());
            setScreenName(jsonObject.get("screenName").getAsString());
        }};

        Response response = given().get("/create/" + getEDITOR() +
                "?age=" + userData.getAge() +
                "&gender=" + userData.getGender() +
                "&login=" + userData.getLogin() +
                "&password=" + userData.getPassword() +
                "&role=" + userData.getRole() +
                "&screenName=" + userData.getScreenName());

        response.then()
                .assertThat().statusCode(200);
    }

    @Test(description = "create user valid data")
    public void test5() {
        JsonObject jsonObject = new JsonParser().parse(getJson("user")).getAsJsonObject();

        Response response = given().get("/create/" + getEDITOR() +
                "?age=" + jsonObject.get("age").getAsString() +
                "&gender=" + jsonObject.get("gender").getAsString() +
                "&login=" + jsonObject.get("login").getAsString() +
                "&password=" + jsonObject.get("password").getAsString() +
                "&role=" + jsonObject.get("role").getAsString() +
                "&screenName=" + jsonObject.get("screenName").getAsString());

        response.then().log().all()
                .assertThat().statusCode(200);
    }

























    @Test
    public void test() {
        List<UserData> users = given()
                .when()
                .contentType(ContentType.JSON)
                .get("https://reqres.in/api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);
        System.out.println(users.size());

    }

}
