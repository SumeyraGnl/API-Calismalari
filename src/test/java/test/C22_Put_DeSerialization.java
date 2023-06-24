package test;

import baseUrl.JsonPlaceHolderBaseURL;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testData.TestDataJsonPlace;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C22_Put_DeSerialization extends JsonPlaceHolderBaseURL {

    /*
    https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki
    body’e sahip bir PUT request yolladigimizda donen response’in
    response body’sinin asagida verilen ile ayni oldugunu test ediniz
    Request Body
        {
        "title":"Ahmet",
        "body":"Merhaba",
        "userId":10,
        "id":70
        }
    Expected Data :
        {
        "title":"Ahmet",
        "body":"Merhaba",
        "userId":10,
        "id":70
        }
     */

    @Test
    public void put01(){

        // 1 - URL ve Request Body hazirla

        specJsonPlace.pathParams("pp1","posts","pp2",70);

        TestDataJsonPlace testDataJsonPlace = new TestDataJsonPlace();

        HashMap<String , Object> reqBody = testDataJsonPlace.requestBodyOlusturMAP();


        // 2 - Expected data hazirla

        HashMap<String , Object> expData = testDataJsonPlace.requestBodyOlusturMAP();

        // 3 - Response 'i kaydet

        Response response = given()
                              .spec(specJsonPlace)
                              .contentType(ContentType.JSON)
                           .when()
                              .body(reqBody)
                           .put("/{pp1}/{pp2}");

        response.prettyPrint();

        // 4 - Assertion

        HashMap <String , Object> resMap =  response.as(HashMap.class);

        assertEquals(expData.get("title"),resMap.get("title"));
        assertEquals(expData.get("body"),resMap.get("body"));
        assertEquals(expData.get("userId"),resMap.get("userId"));
        assertEquals(expData.get("id"),resMap.get("id"));




    }
}
