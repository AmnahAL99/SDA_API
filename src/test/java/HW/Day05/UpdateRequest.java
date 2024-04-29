package HW.Day05;

import base_urls.SwaggerBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ObjecyMapperUtilites;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class UpdateRequest extends SwaggerBaseUrl {
    @Test
    public void UpdateRequest(){
        spec.pathParams("1","user","2","Sam2030");
        String expectedStr = """
                {
                  "id": 0,
                  "username": "Sam2030",
                  "firstName": "Sam",
                  "lastName": "Alona",
                  "email": "sda@com",
                  "password": "Pass4321",
                  "phone": "12456987",
                  "userStatus": 0
                }""";
        UserObject expectedData = ObjecyMapperUtilites.conversJsonToJava(expectedStr, UserObject.class);
        Response response = given(spec).when().body(expectedData).get("{1}/{2}");
        response.prettyPrint();
        assertEquals(200,response.statusCode());
    }
}
