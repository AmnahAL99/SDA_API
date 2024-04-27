package Day01;


import TestData.JsonPlaceHolderTestData;
import base_urls.BookerBaseUrl;
import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.util.HashMap;
import java.util.Map;

import static TestData.JsonPlaceHolderTestData.expectedDataMap;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C13_PutRequest extends JsonPlaceHolderBaseUrl{

    /*
    Given
        1) https://jsonplaceholder.typicode.com/todos/198 // to updated we must add the id here to select it
        2) {
             "userId": 21,  // updated
             "title": "Read Books", // updated
             "completed": false // updated
           }
    When
        I send a PUT request to the URL
    Then
       the status code should be 200
       And the response body should be like:
       {
          "completed": false,
          "title": "Read Books",
          "userId": 21,
          "id": 198
       }
*/

    @Test
    public void putRequestTest(){

        // set the url
        spec.pathParams("first","todos","second",198);

        // set the expected data
        Map<String, Object> expectedData = expectedDataMap(21,"Read Books",false);

        // send the request and get the response
        Response response = given(spec)
                .body(expectedData)
                .put("{first}/{second}");
        response.prettyPrint();

        //do Assertion
        Map<String,Object> actualData = response.as(Map.class); // De-Serialization --> we convert json response into java object map ( to be able to compare same data type - for thr assertion - )
        System.out.println("actualData = " + actualData);

        assertEquals(response.statusCode(),200);
        assertEquals(actualData.get("completed"),expectedData.get("completed"));
        assertEquals(actualData.get("title"),expectedData.get("title"));
        assertEquals(actualData.get("userId"),expectedData.get("userId"));
    }


    @Test
    public void putRequestTest2(){
        spec.pathParams("first","todos"
                ,"second",198);
        Map<String, Object> payLoad =  expectedDataMap(21,"Read Books",false);

        Response response =given(spec)
                .body(payLoad)
                .when()
                .put("{first}/{second}");
        response.prettyPrint();

        Map<String, Object> actualData = response.as(Map.class);
        assertEquals(200,response.statusCode());
        assertEquals(payLoad.get("userId"),actualData.get("userId"));
        assertEquals(payLoad.get("title"),actualData.get("title"));
        assertEquals(payLoad.get("completed"),actualData.get("completed"));
        assertEquals(payLoad.get("id"),actualData.get("id"));
    }

}

