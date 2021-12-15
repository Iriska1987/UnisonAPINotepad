package test;

import Utils.NotepadTestBase;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class API extends NotepadTestBase {
    static String id = "";
    static String title = "Prepare for Demo meeting";
    static String schedule = "2022-01-01T09:00:00.000Z";
    static Map<String, Object> body = new HashMap<>();

    @DisplayName("POST request to /notes")
    @Test
    public void test1() {

        //2022-01-01T00:00:00.000-07:00
        //"title": "string",
        //  "schedule": "string"


        body.put("title", title);
        body.put("schedule", schedule);
        id = given().
                accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/notes")
                .then().statusCode(200)
                .extract().jsonPath().getString("id");
    }

    @DisplayName("GET request to /notes/{id} with ID ")
    @Test
    public void test2() {
        given().
                accept(ContentType.JSON)
                .when()
                .get("/notes/" + id)
                .then().statusCode(200)
                .body("title", Matchers.is(title))
                .body("schedule", Matchers.is(schedule))
                .body("id", Matchers.is(id));


    }

    @DisplayName("PUT request to /notes/{id}")
    @Test


    public void test3() {
        body.put("title", "Cancel the title");
        body.put("id", id);

        given().contentType(ContentType.JSON)
                .body(body)

                .when()
                .put("/notes/" + id)
                .then()
                .statusCode(200)
                .body("title", Matchers.is("Cancel the title"))
                .body("id", Matchers.is(id))
                .body("schedule", Matchers.is(schedule));


    }

    @DisplayName("DELETE request to /notes/{id}")
    @Test
    public void test4() {


        given().contentType(ContentType.JSON)
                .when()
                .delete("/notes/" + id)
                .then()
                .statusCode(200)
                .body("title", Matchers.is("Cancel the title"))
                .body("id", Matchers.is(id))
                .body("schedule", Matchers.is(schedule));
        try {
            test2();
        } catch (AssertionError e) {
            System.err.println("DELETE happened successfully");
        }

    }
}

