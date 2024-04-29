package Request;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class C06_QueryParameters extends BookerBaseUrl {
        /*
    Given
       https://restful-booker.herokuapp.com/booking
    When
       User sends a GET request to the URL
    Then
       Status code is 200
    And
       Among the data, there should be someone whose first name is "Jane" and last name is "Doe"
*/
    @Test
    public void queryTest() {
//        1. Set the URL
        String url = "https://restful-booker.herokuapp.com/booking?firstname=Jane&lastname=Doe";
//        2. Set the expected data
//        3. Send the request and get the response
        Response response = given().when().get(url);
//        response.prettyPrint();
//        4. Do Assertion
        response.then().statusCode(200);
        //1st way:
        Assert.assertTrue(response.asString().contains("bookingid")); // check if the response contain at least one booking ID
        //2nd way:
      //  response.then().body("", hasSize(greaterThan(0)));//This means to check if it has at least one booking id



/*
        Assuming 'response' is your response object
        response.then().body("", hasSize(greaterThan(0)));
 Adding assert statement
        assertThat("Response body should have at least one booking ID", response.then().extract().body().jsonPath().getList("").size(), greaterThan(0));
*/

    }
    @Test
    public void requestSpecificationTest() {

//        1. Set the URL
        spec.pathParams("first", "booking")
                .queryParams("firstname", "John", "lastname", "Doe");
//        2. Set the expected data

//        3. Send the request and get the response
        Response response = given(spec).get("{first}");
        //given(spec).get("{first}//{first}/"); if we have more than one pathParams
        response.prettyPrint();

        response.then().body("", hasSize(greaterThan(0)));//This means to check if it has at least one booking id
    }

}
