package HW.Day05;

import base_urls.SwaggerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class DeleteRequest extends SwaggerBaseUrl {
    @Test
    public void DeleteRequest(){
        spec.pathParams("1","user","2","Sam2030");
        Response response = given(spec).when().delete("{1}/{2}");
        response.prettyPrint();
        assertEquals(200,response.statusCode());


    }
}
