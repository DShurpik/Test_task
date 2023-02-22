package request.test;

import BasePages.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import request.api.getCreateUser.UserData;

import static io.restassured.RestAssured.given;

public class Create_user extends BaseTest {

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

    @Test(description = "create user with invalid data age < then 16")
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

}
