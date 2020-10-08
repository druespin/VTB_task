
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;


public class WeatherTest {

    private ResponseSpec spec = new ResponseSpec();

    @Before
    public void setup() {
        RestAssured.baseURI = "http://api.weatherstack.com";
        RestAssured.basePath = "/current";
    }

    @Test
    public void sendRequest_Moscow() {

        spec.getResponseSpec("Moscow", "Russia", 16, "Sunny");
    }

    @Test
    public void sendRequest_London() {

        spec.getResponseSpec("London", "United Kingdom", 15, "Sunny");
    }

    @Test
    public void sendRequest_LasVegas() {

        spec.getResponseSpec("Las Vegas", "United States of America", 27, "Partly cloudy");
    }

    /**
     *   Error tests
     */

    @Test
    public void sendErrorRequest_MissingAccessKey() {

        spec.getMissingAccessKey();
    }

    @Test
    public void sendErrorRequest_InvalidAccessKey() {

        spec.getInvalidAccessKey();
    }

    @Test
    public void sendErrorRequest_InvalidApi() {

        spec.getInvalidApiFunction();
    }

    @Test
    public void sendErrorRequest_MissingQuery() {

        spec.getMissingQuery();
    }
}
