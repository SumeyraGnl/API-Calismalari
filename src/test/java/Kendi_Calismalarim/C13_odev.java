package Kendi_Calismalarim;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C13_odev {

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

        JsonPath resJP = response.jsonPath();

        Assert.assertEquals(expData.get("status"),resJP.get("status"));
        Assert.assertEquals(expData.getJSONObject("data").get("id"),resJP.get("data.id"));
        Assert.assertEquals(expData.getJSONObject("data").get("employee_name"),resJP.get("data.employee_name"));
        Assert.assertEquals(expData.getJSONObject("data").get("employee_salary"),resJP.get("data.employee_salary"));
        Assert.assertEquals(expData.getJSONObject("data").get("employee_age"),resJP.get("data.employee_age"));
        Assert.assertEquals(expData.getJSONObject("data").get("profile_image"),resJP.get("data.profile_image"));
        Assert.assertEquals(expData.get("message"),resJP.get("message"));







    }
}
