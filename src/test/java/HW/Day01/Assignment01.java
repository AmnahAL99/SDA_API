package HW.Day01;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class Assignment01 {
    /*
   Given
       https://reqres.in/api/users/3
   When
       User sends a GET Request to the url
   Then
       HTTP Status Code should be 200
   And
       Content Type should be JSON
   And
       Status Line should be HTTP/1.1 200 OK
*/
    @Test
    public void Test() {
//        1. Set the URL
        String url = "https://reqres.in/api/users/3";
//        2. Set the expected data
//        3. Send the request and get the response
        Response response = given().get(url);
//        response.prettyPrint();
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .statusLine("HTTP/1.1 200 OK");
        //  4. Do Assertion
        assertEquals(200,response.statusCode());
        assertEquals("HTTP/1.1 200 OK",response.statusLine());
    }
}
