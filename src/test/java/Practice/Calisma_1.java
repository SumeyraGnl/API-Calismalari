package Practice;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Calisma_1 {
    /*
        https://restful-booker.herokuapp.com/booking/10 url’ine bir GET request
        gonderdigimizde donen Response’un,

        status code’unun 200,
        ve content type’inin application/json; charset=utf-8,
        ve Server isimli Header’in degerinin Cowboy,
        ve status Line’in HTTP/1.1 200 OK

        oldugunu test edin.
     */

    @Test
    public void test01(){

        String url = " https://restful-booker.herokuapp.com/booking/10";

        Response response=given().when().get(url);

        response.prettyPrint();

        // manuel test
        System.out.println("status code : "+response.statusCode());
        System.out.println("contenttype : "+ response.contentType());
        System.out.println("header : "+ response.header("Server"));
        System.out.println("status line : "+ response.statusLine());


        // automation test
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");
        Assert.assertEquals(response.header("Server"),"Cowboy");
        Assert.assertEquals(response.statusLine(),"HTTP/1.1 200 OK");

        // assertion

        response.then().assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .header("Server","Cowboy")
                .statusLine("HTTP/1.1 200 OK");





    }


}
