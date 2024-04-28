package Day01;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.BookingDatesPojo;
import pojo.BookingPojo;
import pojo.BookingResponsePojo;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class C20_PostRequestNestedPojo extends BookerBaseUrl {
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
        And response body should be like {
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
    public void postRequestNestedPojo() {
        //Set the url
        spec.pathParams("first", "booking");

        //Set the expected data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01", "2019-01-01");
        BookingPojo expectedData = new BookingPojo("Jane", "Doe", 111, true, bookingDatesPojo, "Extra pillows please");
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();

        //Do assertion
        BookingResponsePojo actualData = response.as(BookingResponsePojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(response.statusCode(), 200);
        assertEquals(actualData.getBooking().getFirstname(), expectedData.getFirstname());
        assertEquals(actualData.getBooking().getLastname(), expectedData.getLastname());
        assertEquals(actualData.getBooking().getTotalprice(), expectedData.getTotalprice());
        assertEquals(actualData.getBooking().getDepositpaid(), expectedData.getDepositpaid());
        assertEquals(actualData.getBooking().getBookingdates().getCheckin(), bookingDatesPojo.getCheckin());
        assertEquals(actualData.getBooking().getBookingdates().getCheckout(), bookingDatesPojo.getCheckout());
        assertEquals(actualData.getBooking().getAdditionalneeds(), expectedData.getAdditionalneeds());


    }

    /*
    Given
        https://jsonplaceholder.typicode.com/todos/198
    When
        I send DELETE Request to the Url
    Then
        Status code is 200
        And Response body is { }
*/
//public class C21_DeleteRequest extends JsonPlaceHolderBaseUrl {
    @Test
    public void deleteRequestTest() {

        //Set the url
        spec.pathParams("1", "todos", "2", "198");

        //send the request and get the response
        Response response = given(spec).delete("{1}/{2}");
        response.prettyPrint();

        //Do assertion
        Map<Object, Object> actualData = response.as(Map.class); // we created this just to assert the body
        System.out.println("actualData = " + actualData);
        assertEquals(response.statusCode(), 200);
        assertTrue(actualData.isEmpty());

    }
    }
