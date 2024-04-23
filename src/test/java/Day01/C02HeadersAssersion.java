package Day01;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C02HeadersAssersion {
    /*
    Given
        https://restful-booker.herokuapp.com/booking
    When
        User sends a GET Request to the url
    Then
        HTTP Status Code should be 200
    And
        Content Type should be JSON
    And
        Status Line should be HTTP/1.1 200 OK
    And
        Connection should be keep-alive
*/
    @Test
 // While doing api test you can follow the following 4 steps:
// Set Url
// Set expected data (if we expect data in certain format) or payload (if we use put or post)
// Sent request and get response
// Do assertions
    public void test() {
        String url = "https://restful-booker.herokuapp.com/booking";
//    first way.1    given()
//                .when()
//                .get(url)
//                        .then()
//                     .statusCode(200)
//                .contentType("application/json")
//               // .statusLine("HTTP/1.1 200 OK")
//                .header("Connection","keep-alive");
// sound way 1.2
      Response response =  given().when().get(url);
        response.prettyPrint();
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .statusLine("HTTP/1.1 200 OK")
               .header("Connection","keep-alive");
  // sound way 2
    int statusCode =   response.statusCode();
    assertEquals(200,statusCode);
    String statusLine = response.statusLine();
        assertEquals("HTTP/1.1 200 OK",statusLine);



    }
}

