package HW;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.isEmptyString;
import static org.testng.Assert.assertEquals;

public class Assignment02 {

    /*
         Given
             https://reqres.in/api/users/23
         When
             User send a GET Request to the url
         Then
             HTTP Status code should be 404
         And
             Status Line should be HTTP/1.1 404 Not Found
         And
             Server is "cloudflare"
         And
             Response body should be empty
      */
    @Test
    public void requestTest() {
//        1. Set the URL
        String url = "https://reqres.in/api/users/23";
//        2. Set the expected data
//        3. Send the request and get the response
        Response response = given().get(url);
        response.prettyPrint();
//        4. Do Assertion
        response.then()
                .statusCode(404)
                .statusLine("HTTP/1.1 404 Not Found")
                .header("Server","cloudflare");

        String Body = response.body().asString();
        Assert.assertTrue(Body.contains(""));

    }
    @Test
    public void test(){
        /*
Given Send GET request by adding static import to the class
Then assert that status code is 200
And assert that status line is HTTP/1.1 200 OK
 */

        //        1. Set the URL
        String url = "https://reqres.in/api/users?page=2";
//        2. Set the expected data
//        3. Send the request and get the response
        Response response = given().get(url);
        response.then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK");
        assertEquals(200,response.statusCode());
        assertEquals("HTTP/1.1 200 OK",response.statusLine());


    }
}