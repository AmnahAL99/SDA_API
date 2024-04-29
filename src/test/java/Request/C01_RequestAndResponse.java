package Request;

import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

public class C01_RequestAndResponse {
    public static void main(String[] args) {
        /*
    Given
        https://restful-booker.herokuapp.com/booking
    When
        User sends a GET Request to the url
    Then
        Print Status Code (should be 200)
    And
        Print Content Type (should be JSON)
    And
        Print Status Line (should be HTTP/1.1 200 OK)
    And
        Print Connection and Date headers on console
    And
        Print all headers on console
*/
        String url = "https://restful-booker.herokuapp.com/booking";
      Response response = given().get(url);
      //How to print response
        response.prettyPrint();
        //How Print Status Code (should be 200)
      int statusCode =  response.statusCode();
        System.out.println("statusCode is.." + statusCode);
        //How to print Print Status Line (should be HTTP/1.1 200 OK)
       String statusLine = response.statusLine();
        System.out.println("statusLine is.." + statusLine);
        //How to print  Print Content Type (should be JSON)
       String contentType = response.contentType();
        System.out.println("contentType is.." + contentType);
       // How  Print Connection and Date headers on console
       String Connection = response.header("Connection");
        System.out.println("Connection" + Connection);
        String Date = response.header("Date");
        System.out.println("Date" + Date);
      // How  Print all headers on console
      Headers headers = response.headers();
        System.out.println("headers is.." +headers);
        //how to get response time
        System.out.println("time" +response.time());
    }


}


// System.out.printf(re.asString());
//                        .log().all() // it is print response
//             // do  assert after then.
//        //body assert  it is use groovy Jpath by default
//
//                        .assertThat().body("[2].name",is(equalTo("Georgia Murphy")))
//                        .assertThat().body("name",hasItem("Georgia Murphy"))
//                        .assertThat().body("name",contains("Jordan Gutmann","Rolando Schumm","Georgia Murphy"));
//content order important or use contents any order
