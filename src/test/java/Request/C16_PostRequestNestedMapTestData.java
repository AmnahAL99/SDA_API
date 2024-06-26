package Request;

import TestData.RestulTestData;
import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;


import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C16_PostRequestNestedMapTestData extends BookerBaseUrl {

    /*
    Given
        1) https://restful-booker.herokuapp.com/booking
        2) {
            "firstname": "John",
            "lastname": "Doe",
            "totalprice": 15,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2023-03-07",
                "checkout": "2024-09-25"
            },
            "additionalneeds": "Lunch"
           }
    When
        I send POST Request to the Url
    Then
        Status code is 200
        And response body should be like {
                                            "bookingid": 2243,
                                            "booking": {
                                                "firstname": "John",
                                                "lastname": "Doe",
                                                "totalprice": 471,
                                                "depositpaid": true,
                                                "bookingdates": {
                                                    "checkin": "2023-03-07",
                                                    "checkout": "2024-09-25"
                                                },
                                                "additionalneeds": "Lunch"
                                            }
                                        }
 */
    @Test
    public void nestedMapTest(){ // nested map - data inside data -

        //set the url
        spec.pathParams("1","booking");

        //set the expected data
        // first we need to prepare inner json as map
        Map<String ,Object> bookingDatesMap = RestulTestData.bookingDatesMapper("2023-03-07", "2024-09-25");
        // then outer
        Map<String,Object> expectedData = RestulTestData.bookingMapper("John","Doe",471,true,bookingDatesMap,"Lunch");

        //send the request and send the response
        Response response = given(spec).body(expectedData).post("{1}");
        response.prettyPrint();

        //Do assertion
        Map<String,Object> actualData = response.as(Map.class);
        assertEquals(response.statusCode(),200);
        assertEquals(((Map)actualData.get("booking")).get("firstname"),expectedData.get("firstname"));
        assertEquals(((Map)actualData.get("booking")).get("lastname"),expectedData.get("lastname"));
        assertEquals(((Map)actualData.get("booking")).get("totalprice"),expectedData.get("totalprice"));
        assertEquals(((Map)actualData.get("booking")).get("depositpaid"),expectedData.get("depositpaid"));
        assertEquals(((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkin"),bookingDatesMap.get("checkin"));
        assertEquals(((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkout"),bookingDatesMap.get("checkout"));
        assertEquals(((Map)actualData.get("booking")).get("additionalneeds"),expectedData.get("additionalneeds"));

    }

    }