package Day07;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static Day07.C31_CreateBooking.bookingid;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C35_DeleteBooking extends BookerBaseUrl {

        /*
    Given
        url: "https://restful-booker.herokuapp.com/booking/:id
    When
        user send DELETe request
    Then
        verify status code is 201
    And
        response is like : Created

     */

    @Test(dependsOnMethods = {"day07.C31_CreateBooking.createBookingTest"})
    public void deleteBookingTest(){
        // Set Url
        spec.pathParams("first","booking"
                ,"second",bookingid);

        // Set Expected Data
        String expectedStr = "Created";

        // Send Request And Get Response
        Response response = given(spec).when().delete("{first}/{second}");
        response.prettyPrint();

        assertEquals(201,response.statusCode());
        assertEquals(expectedStr,response.asString());


    }
}