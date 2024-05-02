package HW.Day06;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.BookingPojo;
import pojo.BookingResponsePojo;
import utilities.ObjecyMapperUtilites;

import java.awt.print.Book;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class CreateBooking extends BookerBaseUrl {
    static int id ;

    /*
   Given
       1) https://restful-booker.herokuapp.com/booking
       2) {
                "firstname": "Amnah",
                "lastname": "Almutari",
                "totalprice": 100,
                "depositpaid": true,
                "bookingdates": {
                "checkin": "2018-01-01",
                "checkout": "2019-01-01"
            },
            "additionalneeds": "Breakfast"
        }
   When
       I send POST Request to the Url
   Then
       Status code is 200
       And response body should be like
       {
                "bookingid": 3774,
        "booking": {
        "firstname": "Amnah",
        "lastname": "Almutari",
        "totalprice": 100,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2018-01-01",
            "checkout": "2019-01-01"
        },
        "additionalneeds": "Breakfast"
    }
}
*/
    @Test
    public void CreateBookingTest() {

        //Set the url
        spec.pathParam("1", "booking");

        //Set Expected Data
        String expectedStr = """
                {
                                "firstname": "Amnah",
                                "lastname": "Almutari",
                                "totalprice": 100,
                                "depositpaid": true,
                                "bookingdates": {
                                "checkin": "2018-01-01",
                                "checkout": "2019-01-01"
                            },
                            "additionalneeds": "Breakfast"
                        }""";

        BookingPojo payLoad = ObjecyMapperUtilites.conversJsonToJava(expectedStr, BookingPojo.class);

        //Send the request and get the response
        Response response = given(spec).body(payLoad).when().post("{1}");
        response.prettyPrint();

        BookingResponsePojo actualData = ObjecyMapperUtilites.conversJsonToJava(response.asString(), BookingResponsePojo.class);


        //Do assertion
        assertEquals(200, response.statusCode());
        assertEquals(payLoad.getFirstname(), actualData.getBooking().getFirstname());
        assertEquals(payLoad.getLastname(), actualData.getBooking().getLastname());
        assertEquals(payLoad.getTotalprice(), actualData.getBooking().getTotalprice());
        assertEquals(payLoad.getDepositpaid(), actualData.getBooking().getDepositpaid());
        assertEquals(payLoad.getBookingdates().getCheckin(), actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(payLoad.getBookingdates().getCheckout(), actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(payLoad.getAdditionalneeds(), actualData.getBooking().getAdditionalneeds());

        id = actualData.getBookingid();
        System.out.println(id);

    }
    @Test
    public void GetBookingTest() {

        //Set the url
        spec.pathParams("1", "booking", "2", 1013);

        //Set Expected Data
        String expectedStr = """
                {
                  "firstname": "Amnah",
                 "lastname": "Almutari",
                "totalprice": 100,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2018-01-01",
                        "checkout": "2019-01-01"
                    },
                    "additionalneeds": "Breakfast"
                }""";

        BookingPojo expectedData = ObjecyMapperUtilites.conversJsonToJava(expectedStr, BookingPojo.class);

        //Send the request and get the response
        Response response = given(spec).when().get("{1}/{2}");
        response.prettyPrint();

        BookingPojo actualData = ObjecyMapperUtilites.conversJsonToJava(response.asString(), BookingPojo.class);


        //Do assertion
        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getFirstname(), actualData.getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());
        assertEquals(expectedData.getBookingdates().getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(), actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());

    }

    /*
    Given
        url: "https://restful-booker.herokuapp.com/booking
    And
        body:     {
                    "firstname" : "Jim",
                    "lastname" : "Brown",
                    "totalprice" : 111,
                    "depositpaid" : true,
                    "bookingdates" : {
                        "checkin" : "2018-01-01",
                        "checkout" : "2019-01-01"
                    },
                    "additionalneeds" : "Breakfast"
                }

    When
        user send post request
    Then
        verify status code is 200
    And
        response is like :
        {
    "bookingid": 1,
    "booking": {
        "firstname": "Jim",
        "lastname": "Brown",
        "totalprice": 111,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2018-01-01",
            "checkout": "2019-01-01"
        },
        "additionalneeds": "Breakfast"
    }
}

     */
    @Test
    public void testCreate(){
        spec.pathParams("1","booking");
        String str = """
                {
                                    "firstname" : "Jim",
                                    "lastname" : "Brown",
                                    "totalprice" : 111,
                                    "depositpaid" : true,
                                    "bookingdates" : {
                                        "checkin" : "2018-01-01",
                                        "checkout" : "2019-01-01"
                                    },
                                    "additionalneeds" : "Breakfast"
                                }""";

        BookingPojo paylode = ObjecyMapperUtilites.conversJsonToJava(str,BookingPojo.class);


        Response response = given(spec).body(paylode).post("{1}");
        response.prettyPrint();

        // since i will use the booking id returned in other method i will use jsonPath method

//      int id =  response.jsonPath().getInt("bookingid");
//        System.out.println(id);

    }

    @Test (dependsOnMethods = "CreateBookingTest")
    public void testGet(){
        spec.pathParams("1","booking","2",id);
        Response response = given(spec).get("{1}/{2}");
        response.prettyPrint();


    }

}
