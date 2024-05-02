package Request;

import base_urls.BookerBaseUrl;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.BookingPojo;
import pojo.BookingResponsePojo;
import utilities.ObjecyMapperUtilites;


import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class C31_CreateBooking extends BookerBaseUrl {
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
    static int bookingid;
    static  BookingPojo   payload;
    @Test
    public void createBookingTest(){
       // Set Url
        spec.pathParam("first","booking");

        // Set Expected Data
        String payloadStr = """
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

         payload = ObjecyMapperUtilites.conversJsonToJava(payloadStr, BookingPojo.class);

        // Send Request and Get Response
        Response response = given(spec).body(payload).when().post("{first}");
        response.prettyPrint();

        // Do Assertions
        BookingResponsePojo actualData = response.as(BookingResponsePojo.class);

        assertEquals(200,response.statusCode());
        assertEquals(payload.getFirstname(),actualData.getBooking().getFirstname());
        assertEquals(payload.getLastname(),actualData.getBooking().getLastname());
        assertEquals(payload.getTotalprice(),actualData.getBooking().getTotalprice());
        assertEquals(payload.getDepositpaid(),actualData.getBooking().getDepositpaid());
        assertEquals(payload.getBookingdates().getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(payload.getBookingdates().getCheckout(),actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(payload.getAdditionalneeds(),actualData.getBooking().getAdditionalneeds());

        // Since I will use the bookingid returned in other method I will use Jsonpath() to store bookingid
        bookingid =response.jsonPath().getInt("bookingid");
        // Muhammed's way to get id
        int id = actualData.getBookingid();
        System.out.println("bookingid = " + bookingid);
        System.out.println("id = " + id);
    }
}