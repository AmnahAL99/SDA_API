package HW.Day05;

import base_urls.SwaggerBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ObjecyMapperUtilites;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class ReadRequest extends SwaggerBaseUrl {
    //Write an automation test that will create a 'user' then read, update and delete
    // the created user using the "https://petstore.swagger.io/" document.
    // (Create a classes for each request.)

    @Test
    public void getRequest() {

        //SetUrl
        spec.pathParams("1", "user", "2", "Sam2030");
        //Set expected Data
        String expectedStr = """
                {
                  "id": 0,
                  "username": "Sam2030",
                  "firstName": "Sam",
                  "lastName": "Alona",
                  "email": "sda@com",
                  "password": "1234",
                  "phone": "12456987",
                  "userStatus": 0
                }""";

        UserObject expectedData = ObjecyMapperUtilites.conversJsonToJava(expectedStr, UserObject.class);

        //Send Post Request and get Response
        Response response = given(spec).when().get("{1}/{2}");
        response.prettyPrint();
        //Do assertions
        UserObject actualData = ObjecyMapperUtilites.conversJsonToJava(response.asString(),UserObject.class);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getUsername(),actualData.getUsername());
        assertEquals(expectedData.getFirstName(),actualData.getFirstName());
        assertEquals(expectedData.getLastName(),actualData.getLastName());
        assertEquals(expectedData.getEmail(),actualData.getEmail());
        assertEquals(expectedData.getPassword(),actualData.getPassword());
        assertEquals(expectedData.getPhone(),actualData.getPhone());
        assertEquals(expectedData.getUserStatus(),actualData.getUserStatus());

    }
}
