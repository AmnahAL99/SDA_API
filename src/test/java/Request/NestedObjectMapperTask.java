package Request;

import base_urls.BookerBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.BookingPojo;
import pojo.BookingResponsePojo;
import utilities.ObjecyMapperUtilites;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class NestedObjectMapperTask extends BookerBaseUrl {
       /*
   Given
       1) https://restful-booker.herokuapp.com/booking
       2) {
               "firstname": "Jane",
               "lastname": "Doe",
               "totalprice": 111,
               "depositpaid": true,
               "bookingdates": {
                   "checkin": "2018-01-01",
                   "checkout": "2019-01-01"
               },
               "additionalneeds": "Extra pillows please"
           }
   When
       I send POST Request to the Url
   Then
       Status code is 200
       And response body should be like
       {
           "bookingid": 2243,
           "booking":{
                       "firstname": "Jane",
                       "lastname": "Doe",
                       "totalprice": 111,
                       "depositpaid": true,
                       "bookingdates": {
                           "checkin": "2018-01-01",
                           "checkout": "2019-01-01"
                       },
                       "additionalneeds": "Extra pillows please"
                   }
            }
*/
    @Test
    public void test() throws JsonProcessingException {
        spec.pathParam("1","booking");
              // Set Expected Data.
        String expectedStr = """
                {
                    "firstname": "Jane",
                    "lastname": "Doe",
                    "totalprice": 111,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2018-01-01",
                        "checkout": "2019-01-01"
                    },
                    "additionalneeds": "Extra pillows please"
                }""";
        ObjectMapper objectMapper = new ObjectMapper();
        BookingPojo payLoad = ObjecyMapperUtilites.conversJsonToJava(expectedStr, BookingPojo.class);

        Response response = given(spec).when().post("{1}");
        response.prettyPrint();

        BookingResponsePojo actualData = objectMapper.readValue(response.asString(), BookingResponsePojo.class);


        assertEquals(payLoad.getFirstname(),actualData.getBooking().getFirstname());
        assertEquals(payLoad.getLastname(),actualData.getBooking().getLastname());
        assertEquals(payLoad.getTotalprice(),actualData.getBooking().getTotalprice());
        assertEquals(payLoad.getDepositpaid(),actualData.getBooking().getDepositpaid());
        assertEquals(payLoad.getBookingdates().getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(payLoad.getBookingdates().getCheckout(),actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(payLoad.getAdditionalneeds(),actualData.getBooking().getAdditionalneeds());
    }


    @Test
    public void test2() throws JsonProcessingException {
        // Set Url
        spec.pathParam("first","booking");

        //Set Expected Data

        String expectedStr = """
                {
                     "firstname": "Jane",
                     "lastname": "Doe",
                     "totalprice": 111,
                     "depositpaid": true,
                     "bookingdates": {
                         "checkin": "2018-01-01",
                         "checkout": "2019-01-01"
                     },
                     "additionalneeds": "Extra pillows please"
                 }""";

        ObjectMapper objectMapper = new ObjectMapper();
     //   BookingPojo payLoad = objectMapper.readValue(expectedStr, BookingPojo.class);
        BookingPojo payLoad = ObjecyMapperUtilites.conversJsonToJava(expectedStr, BookingPojo.class);

        // Send request and Get Response
        Response response = given(spec).body(payLoad).when().post("{first}");
        response.prettyPrint();

        // Do assertions
        BookingResponsePojo actualData = ObjecyMapperUtilites.conversJsonToJava(response.asString(), BookingResponsePojo.class);
        // BookingResponsePojo actualData = objectMapper.readValue(response.asString(), BookingResponsePojo.class);
        assertEquals(200,response.statusCode());
        assertEquals(payLoad.getFirstname(),actualData.getBooking().getFirstname());
        assertEquals(payLoad.getLastname(),actualData.getBooking().getLastname());
        assertEquals(payLoad.getDepositpaid(),actualData.getBooking().getDepositpaid());
        assertEquals(payLoad.getTotalprice(),actualData.getBooking().getTotalprice());
        assertEquals(payLoad.getBookingdates().getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(payLoad.getBookingdates().getCheckout(),actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(payLoad.getAdditionalneeds(),actualData.getBooking().getAdditionalneeds());

    }

}




