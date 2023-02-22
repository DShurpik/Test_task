package BasePages;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static Utils.Config.BASE_URI;
import static io.restassured.RestAssured.baseURI;

public abstract class BaseTest {

    private static final String EDITOR = "supervisor";

    public static void disableWarning() {
        System.err.close();
        System.setErr(System.out);
    }

    @BeforeTest
    public void precondition() {
        baseURI = BASE_URI;
        RestAssured.defaultParser = Parser.JSON;
        disableWarning();
    }

    public String getJson(String fileName){
        try {
            return new String(Files.readAllBytes(Paths.get("src/test/resources/" + fileName + ".json")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getEDITOR() {
        return EDITOR;
    }

    private static Integer id;

    public static Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
