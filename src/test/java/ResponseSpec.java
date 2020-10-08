
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

class ResponseSpec {

    private final String ACCESS_KEY = "d135d6039b6d0c687064834122342c03";

    void getResponseSpec(String city, String country, int temperature, String desc) {

        given()
                .queryParam("access_key", ACCESS_KEY)
                .queryParam("query", city)
            .when()
                .get()
            .then()
                .statusCode(200)
                .body("request.language", equalTo("en"))
                .body("location.name", equalTo(city))
                .body("location.country", equalTo(country))
                .body("current.temperature", equalTo(temperature))
                .body("current.weather_descriptions", contains(desc))
                .log().all();
    }


    void getMissingAccessKey() {
        given()
                .get()
                .then()
                .statusCode(200)
                .body("error.code", equalTo(101))
                .body("error.type", equalTo("missing_access_key"))
                .log().all();;
    }

    void getInvalidAccessKey() {
        given()
                .queryParam("access_key", "1234") // invaid access_key
                .get()
                .then()
                .statusCode(200)
                .body("error.code", equalTo(101))
                .body("error.type", equalTo("invalid_access_key"))
                .log().all();;
    }

    void getInvalidApiFunction() {
        given()
                .queryParam("access_key", ACCESS_KEY)
                .get("/abc")// invalid path
                .then()
                .statusCode(200)
                .body("error.code", equalTo(103))
                .body("error.type", equalTo("invalid_api_function"))
                .log().all();;
    }

    void getMissingQuery() {
        given()
                .queryParam("access_key", ACCESS_KEY)
                .get()
                .then()
                .statusCode(200)
                .body("error.code", equalTo(601))
                .body("error.type", equalTo("missing_query"))
                .log().all();
    }
}
