package Day01;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class C11_PostRequestMap extends JsonPlaceHolderBaseUrl {
/*
     Given
       1) https://jsonplaceholder.typicode.com/todos
       2)  {
             "userId": 55,
             "title": "Tidy your room",
             "completed": false
          }
    When
        I send POST Request to the Url

    Then
        Status code is 201
    And
        response body is like {
                                "userId": 55,
                                "title": "Tidy your room",
                                "completed": false,
                                "id": 201
                                }
*/

    @Test
    public void postRequestTest() {

        //Set the Url
        spec.pathParams("first", "todos");

        //Set the expected data(Payload) --> Prepare it as Map
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId",55);
        expectedData.put("title","Tidy your room");
        expectedData.put("completed",false);

        System.out.println("expectedData = " + expectedData);


        //Send the request and get the response
        Response response = given(spec).body(expectedData).post("{first}");

        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(201)
                .body("userId", equalTo(expectedData.get("userId")), //By map payload, we can get specific data from body. This is more dynamic.
                        "title", equalTo(expectedData.get("title")),
                        "completed", equalTo(expectedData.get("completed")));

    }
    @Test
    public void test(){
        spec.pathParam("first","todos");
        Map<String,Object> expectesData = new HashMap<>();
        expectesData.put("userId",55);
        expectesData.put("title","Tidy your room");
        expectesData.put("completed",false);
        System.out.println("expectesData is .."+expectesData);

        Response response = given(spec).body(expectesData).post("{first}");
        response.prettyPrint();
        //Do assertion
        JsonPath json = response.jsonPath();
        assertEquals(201,response.statusCode());
        assertEquals(expectesData.get("userId"),json.getInt("userId"));

        Map<String,Object> actualData = response.as(Map.class);
        assertEquals(201,response.statusCode());
        assertEquals(expectesData.get("userId"),actualData.get("userId"));
        assertEquals(expectesData.get("title"),actualData.get("title"));
        assertEquals(expectesData.get("completed"),actualData.get("completed"));




    }

}