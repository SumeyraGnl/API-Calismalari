package testData;

import java.util.HashMap;

public class TestDataDummy {

    public int basariliStatusCode = 200;
    public String contentType = "application/json";

    /*
    {
    "status":"success",
    "data":{
            "id":3,
            "employee_name":"Ashton Cox",
            "employee_salary":86000,
            "employee_age":66,
            "profile_image":""
            },
    "message":"Successfully! Record has been fetched."
    }
     */

    public HashMap dataBodyOlusturMAP(){

        HashMap<String ,Object> data = new HashMap<>();

        data.put("id",3.0);
        data.put("employee_name","Ashton Cox");
        data.put("employee_salary",86000.0);
        data.put("employee_age",66.0);
        data.put("profile_image","");


        return data ;
    }

    public HashMap expectedBodyOlusturMAP(){

        HashMap<String ,Object> expBody = new HashMap<>();

        expBody.put("status","success");
        expBody.put("data",dataBodyOlusturMAP());
        expBody.put("message","Successfully! Record has been fetched.");

        return expBody ;
    }


}
