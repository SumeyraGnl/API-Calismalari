package Practice;

import baseUrl.DummyBaseURL;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testData.TestDataDummy;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C20_odev extends DummyBaseURL {

    /*

       http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request
       gonderdigimizde donen response’un status code’unun 200, content Type’inin
       application/json ve body’sinin asagidaki gibi oldugunu test edin.

       Response Body
       {
       "status": "success",
       "data": {
              "id": 3,
              "employee_name": "Ashton Cox",
              "employee_salary": 86000,
              "employee_age": 66,
              "profile_image": ""
                },
       "message": "Successfully! Record has been fetched."
        }
     */

      @Test
    public void get01(){

          // 1 - URL hazirla

          specDummy.pathParams("pp1","api","pp2","v1","pp3","employee","pp4",3);

          // 2 - Expected data hazirla

          TestDataDummy testDataDummy = new TestDataDummy();

          JSONObject expData = testDataDummy.expDataOlusturJSON();

          // 3 - Response 'i kaydet

          Response response  = given().spec(specDummy).when().get("/{pp1}/{pp2}/{pp3}/{pp4}");

          response.prettyPrint();

          // 4 - Assertion

         JsonPath resJP = response.jsonPath();

          assertEquals(testDataDummy.basariliStatusCode,response.getStatusCode());
          assertEquals(testDataDummy.contentType,response.getContentType());

          assertEquals(expData.get("status"),resJP.get("status"));
          assertEquals(expData.getJSONObject("data").get("id"),resJP.get("data.id"));
          assertEquals(expData.getJSONObject("data").get("employee_name"),resJP.get("data.employee_name"));
          assertEquals(expData.getJSONObject("data").get("employee_salary"),resJP.get("data.employee_salary"));
          assertEquals(expData.getJSONObject("data").get("employee_age"),resJP.get("data.employee_age"));
          assertEquals(expData.getJSONObject("data").get("profile_image"),resJP.get("data.profile_image"));
          assertEquals(expData.get("message"),resJP.get("message"));




      }




}
