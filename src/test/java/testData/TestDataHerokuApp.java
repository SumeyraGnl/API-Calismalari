package testData;

import org.json.JSONObject;

import java.util.HashMap;

public class TestDataHerokuApp {

    /*
     Request body
          {
          "firstname" : "Ali",
          "lastname" : “Bak",
          "totalprice" : 500,
          "depositpaid" : false,
          "bookingdates" : {
                      "checkin" : "2021-06-01",
                      "checkout" : "2021-06-10"
                        },
          "additionalneeds" : "wi-fi"
           }
     */

    public int basariliStatusCode = 200;

    public JSONObject bookingdatesOlusturJSON(){

        JSONObject bookingdates = new JSONObject();

        bookingdates.put("checkin" , "2021-06-01");
        bookingdates.put("checkout" , "2021-06-10");

        return bookingdates;
    }

    public JSONObject bookingOlusturJSON(){

        JSONObject booking = new JSONObject();

        booking.put("firstname" , "Ali");
        booking.put("lastname" , "Bak");
        booking.put("totalprice" , 500);
        booking.put("depositpaid" , false);
        booking.put("additionalneeds" , "wi-fi");
        booking.put("bookingdates" , bookingdatesOlusturJSON());

        return booking;
    }

    /*
     Expected Body
    {
    "bookingid":24,
    "booking":{
            "firstname":"Ali",
            "lastname":"Bak",
            "totalprice":500,
            "depositpaid":false,
            "bookingdates":{
                            "checkin":"2021-06-01",
                            "checkout":"2021-06-10"
                            },
            "additionalneeds":"wi-fi"
               }
    }
     */

    public JSONObject expectedBodyOlusturJSON(){

        JSONObject expData = new JSONObject();

        expData.put("bookingid" , 24);
        expData.put("booking" , bookingOlusturJSON());

        return expData;
    }

    public HashMap bookingdatesOlusturMAP(){

        /*
        "bookingdates" : {
                  "checkin" : "2021-06-01",
                  "checkout" : "2021-06-10"
                            },
         */

        HashMap<String ,Object> bookingdates = new HashMap<>();

        bookingdates.put("checkin" , "2021-06-01");
        bookingdates.put("checkout" , "2021-06-10");

        return bookingdates;
    }

    public HashMap requestBodyOlusturMAP(){

        /*
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




        HashMap<String ,Object> booking = new HashMap<>();

        booking.put("firstname","Ahmet");
        booking.put("lastname","Bulut");
        booking.put("totalprice" , 500.0);
        booking.put("depositpaid" , false);
        booking.put("bookingdates" , bookingdatesOlusturMAP());
        booking.put("additionalneeds" , "wi-fi");


        return booking;
    }


    public HashMap expectedBodyOlusturMAP(){

        /*
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
         */

        HashMap<String ,Object> expData = new HashMap<>();

        expData.put("bookingid" , 24);
        expData.put("booking" , requestBodyOlusturMAP());


        return expData;
    }


}
