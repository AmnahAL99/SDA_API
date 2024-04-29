package Request;

import base_urls.BookerBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.BookingPojo;
import pojo.JsonPlaceHolderPojo;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class NestedObjectMapper extends BookerBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/466
        When
            I send GET Request to the url
        Then
            Response body should be like that;
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
            }
*/
    @Test
    public void test() throws JsonProcessingException {
        spec.pathParams("1","booking","2","466");
        // Set Expected Data.
        String expDataStr = """
                     {
                                "firstname": "Jane",
                                "lastname": "Doe",
                                "totalprice": 111,
                                "depositpaid": true,
                                "bookingdates": {
                                    "checkin": "2018-01-01",
                                    "checkout": "2019-01-01"
                                },""";
        ObjectMapper objectMapper = new ObjectMapper();
       BookingPojo expectedData = objectMapper.readValue(expDataStr, BookingPojo.class);

       // List<JsonPlaceHolderPojo> actualDataList = response.as(new TypeRef<>() {});//new TypeRef<>() {} --> This takes the data type and assign json to that data type.
        Response response = given(spec).when().get("{1}/{2}");
        response.prettyPrint();

        // do assertion
       BookingPojo actualData = objectMapper.readValue(response.asString(), BookingPojo.class);

        assertEquals(response.statusCode(), 200);
        assertEquals(actualData.getFirstname(), expectedData.getFirstname());
        assertEquals(actualData.getLastname(), expectedData.getLastname());
        assertEquals(actualData.getTotalprice(), expectedData.getTotalprice());
        assertEquals(actualData.getDepositpaid(), expectedData.getBookingdates());
        assertEquals(actualData.getBookingdates().getCheckin(), expectedData.getBookingdates().getCheckin());
        assertEquals(actualData.getBookingdates().getCheckout(), expectedData.getBookingdates().getCheckout());

    }


    @Test
    public void test2() throws JsonProcessingException {
        // Set Url
        spec.pathParams("first","booking","second",15);

        // Set Expected Data
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
        BookingPojo expectedData = objectMapper.readValue(expectedStr, BookingPojo.class);

        // Send Request and Get Response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        // Do assertions
        BookingPojo actualData = objectMapper.readValue(response.asString(),BookingPojo.class);

        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(expectedData.getBookingdates().getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(),actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());
    }

}


