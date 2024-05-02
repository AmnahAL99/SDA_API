package utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthenticatuonRestful {
    public static String getToken(){
        String url ="https://restful-booker.herokuapp.com/auth";
        String payload = """
                {
                    "username" : "admin",
                    "password" : "password123"
                }""";
        Response response = given()
                .body(payload)
                .contentType(ContentType.JSON).post(url);
        return response.jsonPath().getString("token");

    }
}
