package Request;

import base_urls.JsonPlaceHolderBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import groovy.util.ObservableMap;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C23_ObjectMapperPostMap extends JsonPlaceHolderBaseUrl {
/*
         Given
           1) https://jsonplaceholder.typicode.com/todos
           2) {
               "userId": 55,
               "title": "Tidy your room",
               "completed": false
               }


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
    public void objectMapperMap() throws JsonProcessingException {
        //Set the url
        spec.pathParams("first", "todos");

        //Set the expected data
        String strJson = """
                {
                "userId": 55,
                "title": "Tidy your room",
                "completed": false
                }
                """; //We wil use this String Json to convert it to Pojo object

        Map expectedData = new ObjectMapper().readValue(strJson, Map.class);//This readValue method works with two parameters. First one is String formatted Json, second one is the data type you want to convert the json to.
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();

        //Do assertion
        Map actualData = new ObjectMapper().readValue(response.asString(), Map.class);
        System.out.println("actualData = " + actualData);

        assertEquals(response.statusCode(), 201);
        assertEquals(actualData.get("userId"), expectedData.get("userId"));
        assertEquals(actualData.get("title"), expectedData.get("title"));
        assertEquals(actualData.get("completed"), expectedData.get("completed"));
    }

//    @Test
//      public void test() throws JsonProcessingException {
//        spec.pathParam("1","todos");
//        String jsonString = """
//                   {
//                                "userId": 55,
//                                "title": "Tidy your room",
//                                "completed": false
//                                }
//
//                """;
//        ObjectMapper objectMapper = new ObjectMapper();
//        Map<String,Object> payLode = objectMapper.readValue(jsonString, Map.class);
//
//        Response response = given(spec).body(payLode).post("{1}");
//        response.prettyPrint();
//  // do assersion
//        Map<String,Object> actualData = new ObjectMapper().readValue(response.asString(), Map.class);
//      //  System.out.println(actualData);
//        assertEquals(201,response.statusCode());
//        assertEquals(payLode.get("userId"),actualData.get("userId"));
//        assertEquals(payLode.get("title"),actualData.get("title"));
//        assertEquals(payLode.get("completed"),actualData.get("completed"));
//
//
//    }
}