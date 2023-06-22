package baseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class HerokuAppBaseURL {

    // https://restful-booker.herokuapp.com

    protected RequestSpecification specHerokuApp;

    @Before
    public void setup(){

        specHerokuApp = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();



    }
}