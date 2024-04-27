package HW.Day03;

import base_urls.SwaggerBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Assignment07 extends SwaggerBaseUrl {
    /*
Write an automation test that will create a 'user'
 using the "https://petstore.swagger.io/" document
     "id": 0,
    "username": "string",
    "firstName": "string",
    "lastName": "string",
    "email": "string",
    "password": "string",
    "phone": "string",
    "userStatus": 0
*/
    @Test
    public void requestTest(){
        // set up the url
        spec.pathParams("1","user");
        //set the expected data
        Map<String,Object> expectedDta = new HashMap<>();
        expectedDta.put("id",2);
        expectedDta.put("username","Amnah88");
        expectedDta.put("firstName","Amnah");
        expectedDta.put("lastName","Almutari");
        expectedDta.put("email","amnah@gmail.com");
        expectedDta.put("password","12345");
        expectedDta.put("phone","0522110987");
        expectedDta.put("userStatus",2);
        System.out.println(expectedDta);
        //send the request and send the response
        Response response = given(spec).body(expectedDta)
                .post("{1}");
        response.prettyPrint();

        //Do Assertion
       // Assert.assertEquals(response.statusCode(),(200));

        response.then()
                .statusCode(200);
        // Verify the response body using JsonPath
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.getString("username"),expectedDta.get("Amnah88"));
        Assert.assertEquals(jsonPath.getString("firstName"),expectedDta.get("Amnah"));
        Assert.assertEquals(jsonPath.getString("lastName"),expectedDta.get("Almutari"));
        Assert.assertEquals(jsonPath.getString("email"),expectedDta.get("amnah@gmail.com"));
        Assert.assertEquals(jsonPath.getString("password"),expectedDta.get("12345"));
        Assert.assertEquals(jsonPath.getString("phone"),expectedDta.get("0522110987"));
        Assert.assertEquals(jsonPath.getString("userStatus"),expectedDta.get("2"));

    }

}
