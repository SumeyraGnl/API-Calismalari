package test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class C07_Get_BodyTekrarlardanKurtulma {

    /*
                https://restful-booker.herokuapp.com/booking/10 url’ine
                bir GET request gonderdigimizde donen Response’un,
                status code’unun 200,
                ve content type’inin application/json; charset=utf-8,
                ve response body’sindeki
                    "firstname“in,"Susan",
                    ve "lastname“in, "Wilson",
                    ve "totalprice“in, 613,
                    ve "depositpaid“in,false,
                    ve "additionalneeds“in,"Breakfast"
                oldugunu test edin
         */

    @Test
    public void get01(){

        // 1 -  Url hazirla

        String url = "https://restful-booker.herokuapp.com/booking/10";


        // 2 - Expected Data hazirla

        // 3 - Response 'i kaydet

        Response response = given().when().get(url);

        response.prettyPrint();

        // Assertion

        response
                .then()
                     .assertThat()
                     .statusCode(200)
                     .contentType("application/json; charset=utf-8")
                     .body("firstname",equalTo("Susan"),// =====> Matchers classını silip equalTo import ettik.Matcher yazmaya gerek yok artık
                             "lastname",equalTo("Wilson"),
                             "totalprice",equalTo(613),
                             "depositpaid",Matchers.equalTo(false),
                             "additionalneeds",Matchers.equalTo("Breakfast"));

    }
}
