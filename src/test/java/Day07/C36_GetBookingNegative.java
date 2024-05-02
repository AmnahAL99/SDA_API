package Day07;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.BookingDatesPojo;
import pojo.BookingPojo;

import static Day07.C31_CreateBooking.bookingid;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C36_GetBookingNegative extends BookerBaseUrl {
    /*
    Given
        url: "https://restful-booker.herokuapp.com/booking/:id
    When
        user send GET request
    Then
        verify status code is 200
    And
        response is like :

     */

    @Test(dependsOnMethods = {"day07.C31_CreateBooking.createBookingTest","day07.C35_DeleteBooking.deleteBookingTest"})
    public void getBookingTest(){
        // Set Url
        spec.pathParams("first","booking"
                ,"second", bookingid);

        // Set Expected Data
        BookingDatesPojo bookingDates = new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingPojo expectedData = new BookingPojo("Jim","Brown",111,true,bookingDates,"Breakfast");

        // Send request and response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        assertEquals(404,response.statusCode());
        assertEquals("Not Found",response.asString());



    }
}