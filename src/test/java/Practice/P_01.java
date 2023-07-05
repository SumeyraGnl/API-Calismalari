package Practice;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class P_01 {

    String token;

        //api/opdList endpoint'ine gecerli authorization bilgileri ile bir GET request gönderildiginde
        // dönen status code'un 200 oldugu ve response message bilgisinin "Success" oldugu dogrulanmali


    @Test
    public void test01(){

        // ilk is spec ile base url olusturulur

        RequestSpecification spec = new RequestSpecBuilder().setBaseUri("https://heallifehospital.com/").build();

        spec.pathParams("pp1","api","pp2","opdList");

        String fullPath = "/{pp1}/{pp2}";

        // swagger dokumanini kullanarak token aldık manuel >> sonrasinda methoda bagladik

        String token = tokenCreate();

        Response response = given()
                                .contentType(ContentType.JSON)
                                .spec(spec)
                                .headers("Authorization", "Bearer "+ token,
                                        "Content-Type", ContentType.JSON,
                                        "Accept", ContentType.JSON)
                           .when()
                           .get(fullPath);

        response.prettyPrint();

        // dönen status code'un 200 oldugu ve response message bilgisinin "Success" oldugu dogrulanmali

        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("message", Matchers.equalTo("Success"));

    }

    @Test
    public void test02(){

        //api/opdList endpoint'ine gecersiz authorization bilgileri ile bir GET Request gönderildiginde
        // dönen status code'un 403 oldugu ve response message bilgisinin "failed" oldugu dogrulanmali

        RequestSpecification spec = new RequestSpecBuilder().setBaseUri("https://heallifehospital.com/").build();

        spec.pathParams("pp1","api","pp2","opdList");

        String fullPath = "/{pp1}/{pp2}";

        String token = "abcdefdWVSknvjn34hgmr65jgt7";

        // hata olarak 403 bize döndüğü için system exception fırlatıyor. Exceptionın mesajını kaydedip onun üzerinden
        //test yapacağız


        String exceptionMsj = "";
        try {
            Response response = given()
                                    .spec(spec)
                                    .contentType(ContentType.JSON)
                                    .headers("Authorization", "Bearer "+ token,
                                       "Content-Type", ContentType.JSON,
                                                "Accept", ContentType.JSON)
                                .when()
                                .get(fullPath);
        } catch (Exception e) {
            exceptionMsj = e.getMessage();
        }

        System.out.println(exceptionMsj);

        Assert.assertTrue(exceptionMsj.contains("status code: 403"));

    }


    public String tokenCreate(){

        String url = "https://www.heallifehospital.com/api/getToken/";

        /*
        {
    "email": "hatice.kubra.ceylan.admin@heallifehospital.com",
    "password": "heallife123"
}
         */

        JSONObject reqBody = new JSONObject();

        reqBody.put("email","hatice.kubra.ceylan.admin@heallifehospital.com");
        reqBody.put("password", "heallife123");

        Response response = given()
                               .contentType(ContentType.JSON)
                               .header("accept",ContentType.JSON)
                            .when()
                               .body(reqBody.toString())
                            .post(url);

        response.prettyPrint();
        // response içinden token'ı alabilmemiz için öncelikle responsu JsonPath objesine çevirmeliyiz

        JsonPath jsonResponse = response.jsonPath();

        token = jsonResponse.getString("token");

        //System.out.println(token);

        return token;
    }

}
