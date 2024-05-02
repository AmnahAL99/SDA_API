package Request;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.BookingPojo;
import utilities.ObjecyMapperUtilites;

import static Request.C31_CreateBooking.bookingid;
import static Request.C31_CreateBooking.payload;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C33UpdateBooking extends BookerBaseUrl {
    /*
  Given
      url: "https://restful-booker.herokuapp.com/booking
  And
      body:     {
                  "firstname" : "James",
                  "lastname" : "Brown",
                  "totalprice" : 111,
                  "depositpaid" : true,
                  "bookingdates" : {
                      "checkin" : "2018-01-01",
                      "checkout" : "2019-01-01"
                  },
                  "additionalneeds" : "Lunch"
              }

  When
      user send put request
  Then
      verify status code is 200
  And
      response is like :
                          {
                      "firstname" : "James",
                      "lastname" : "Brown",
                      "totalprice" : 111,
                      "depositpaid" : true,
                      "bookingdates" : {
                          "checkin" : "2018-01-01",
                          "checkout" : "2019-01-01"
                      },
                      "additionalneeds" : "Lunch"
                  }

   */
    @Test(dependsOnMethods = {"Request.C31_CreateBooking.createBookingTest"})
    public void updateBookingTest(){
        // Set Expected Data
        spec.pathParams("first","booking"
                ,"second",bookingid);

        // Set Expected Data
        payload.setFirstname("James");
        payload.setAdditionalneeds("Lunch");

        // Send Request and Response
        Response response = given(spec).body(payload).when().put("{first}/{second}");
        response.prettyPrint();

        response.then().statusCode(200);

        // Do Assertions
        BookingPojo actualData = response.as(BookingPojo.class);

        assertEquals(200,response.statusCode());
        assertEquals(payload.getFirstname(),actualData.getFirstname());
        assertEquals(payload.getLastname(),actualData.getLastname());
        assertEquals(payload.getTotalprice(),actualData.getTotalprice());
        assertEquals(payload.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(payload.getBookingdates().getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(payload.getBookingdates().getCheckout(),actualData.getBookingdates().getCheckout());
        assertEquals(payload.getAdditionalneeds(),actualData.getAdditionalneeds());
    }


    @Test
    public void delete(){
        spec.pathParams("1","Booking","2",bookingid);
        Response response = given(spec).delete("{1}/{2}");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    }


