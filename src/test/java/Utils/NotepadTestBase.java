package Utils;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public abstract class NotepadTestBase {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://localhost:3000";




    }
    @AfterAll
    public static void tearDown(){

    }
}
