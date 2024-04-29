package HW.Day05;

import base_urls.SwaggerBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ObjecyMapperUtilites;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class CreatUser extends SwaggerBaseUrl {
  //Write an automation test that will create a 'user' then read, update and delete
  // the created user using the "https://petstore.swagger.io/" document.
  // (Create a classes for each request.)


  @Test
  public void postRequest(){

    //SetUrl
    spec.pathParam("1","user");
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

    UserObject expectedData=  ObjecyMapperUtilites.conversJsonToJava(expectedStr,UserObject.class);


    Response response = given(spec).body(expectedData).when().post("{1}");
    response.prettyPrint();


    assertEquals(200,response.statusCode());

  }
}
