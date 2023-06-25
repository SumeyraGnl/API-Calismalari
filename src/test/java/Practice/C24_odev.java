package Practice;

import baseUrl.HerokuAppBaseURL;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import testData.TestDataHerokuApp;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C24_odev extends HerokuAppBaseURL {

    /*
       C24_Post_Deserialization
https://restful-booker.herokuapp.com/booking url’ine asagidaki body'ye sahip bir POST
request gonderdigimizde donen response’un id haric asagidaki gibi oldugunu test edin.

Response Body // expected data
{
"bookingid": 24,
"booking": {
"firstname": "Ahmet",
"lastname": "Bulut",
"totalprice": 500,
"depositpaid": false,
"bookingdates": {
"checkin": "2021-06-01",
"checkout": "2021-06-10"
},
"additionalneeds": "wi-fi"
}
}

Request body
{
"firstname" : "Ahmet",
"lastname" : “Bulut",
"totalprice" : 500,
"depositpaid" : false,
"bookingdates" : {
"checkin" : "2021-06-01",
"checkout" : "2021-06-10"
},
"additionalneeds" : "wi-fi"
}
     */

    @Test
    public void post01(){

        // 1 - URL ve request body hazirla

        specHerokuApp.pathParam("pp1","booking");

        TestDataHerokuApp testDataHerokuApp = new TestDataHerokuApp();

        HashMap<String ,Object> reqBody =  testDataHerokuApp.requestBodyOlusturMAP();


        // 2 - Expected data hazirla

        HashMap<String ,Object> expData =  testDataHerokuApp.expectedBodyOlusturMAP();

        // 3 - Response'i kaydet

        Response response = given().spec(specHerokuApp).contentType(ContentType.JSON).when().body(reqBody).post("/{pp1}");

        response.prettyPrint();

        // 4 - Assertion


        HashMap<String ,Object> resMap =  response.as(HashMap.class);

        assertEquals(  ((Map)(expData.get("booking"))) .get("firstname"), ((Map)(resMap.get("booking"))).get("firstname") );
        assertEquals(  ((Map)(expData.get("booking"))) .get("lastname"), ((Map)(resMap.get("booking"))).get("lastname") );
        assertEquals(  ((Map)(expData.get("booking"))) .get("totalprice"), ((Map)(resMap.get("booking"))).get("totalprice") );
        assertEquals(  ((Map)(expData.get("booking"))) .get("depositpaid"), ((Map)(resMap.get("booking"))).get("depositpaid") );

        assertEquals(  ((Map)(((Map)(expData.get("booking"))) .get("bookingdates"))).get("checkin")
                     , ((Map)(((Map)(resMap.get("booking"))).get("bookingdates"))).get("checkin") );
        assertEquals(  ((Map)(((Map)(expData.get("booking"))) .get("bookingdates"))).get("checkout")
                , ((Map)(((Map)(resMap.get("booking"))).get("bookingdates"))).get("checkout") );

        assertEquals(  ((Map)(expData.get("booking"))) .get("additionalneeds"), ((Map)(resMap.get("booking"))).get("additionalneeds") );







    }
}
