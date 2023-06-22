package test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class C13_Get_SoftAssertIleExpectedDataTesti {

     /*

      http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request
      gonderdigimizde donen response’un asagidaki gibi oldugunu test edin.

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

        // url hazırla

        String url = "http://dummy.restapiexample.com/api/v1/employee/3";

        // Expected data hazırla

        JSONObject data = new JSONObject();

        data.put("id", 3);
        data.put("employee_name", "Ashton Cox");
        data.put("employee_salary", 86000);
        data.put("employee_age", 66);
        data.put("profile_image", "");

        JSONObject expData = new JSONObject();

        expData.put("status", "success");
        expData.put("data", data);
        expData.put("message", "Successfully! Record has been fetched.");

        // response 'i kaydet

        Response response = given().when().get(url);

        response.prettyPrint();


        // Assertion

        SoftAssert softAssert = new SoftAssert();

        JsonPath resJP = response.jsonPath();

        softAssert.assertEquals(resJP.get("status"),expData.get("status"));
        softAssert.assertEquals(resJP.get("data.id"),expData.getJSONObject("data").get("id"));
        softAssert.assertEquals(resJP.get("data.employee_name"),expData.getJSONObject("data").get("employee_name"));
        softAssert.assertEquals(resJP.get("data.employee_salary"),expData.getJSONObject("data").get("employee_salary"));
        softAssert.assertEquals(resJP.get("data.employee_age"),expData.getJSONObject("data").get("employee_age"));
        softAssert.assertEquals(resJP.get("data.profile_image"),expData.getJSONObject("data").get("profile_image"));
        softAssert.assertEquals(resJP.get("message"),expData.get("message"));

        softAssert.assertAll();
    }
}
