package requres.test;

import BasePages.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.testng.annotations.Test;
import lombok.extern.log4j.Log4j;

import static io.restassured.RestAssured.given;
@Log4j
public class delete_user extends BaseTest {

    @Test
    public void test1() {

        String str = "{\n" +
                "  \"playerId\": 2146762637\n" +
                "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(str)
                .delete("/delete/" + getEDITOR());

        response.then().assertThat().statusCode(204);
    }

}
