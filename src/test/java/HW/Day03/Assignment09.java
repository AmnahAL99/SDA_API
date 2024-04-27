package HW.Day03;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Assignment09 {
    /*
    Given
        https://automationexercise.com/api/productsList
    When
        User sends a GET request
    Then
        Assert that the number of "Women" user type is 12
    Note: Print using JsonPath: response.jsonPath().prettyPrint();
*/
    @Test
    public void requestTest(){
        Response response = given().get("https://automationexercise.com/api/productsList");

        //print
        JsonPath jsonPath =  response.jsonPath();
      //  response.jsonPath().prettyPrint();

        //assertion
        // products.findAll{it.category.usertype.usertype=='Women'}
        List<String> actualData = jsonPath.getList("products.findAll{it.category.usertype.usertype=='Women'}");
        Assert.assertEquals(actualData.size(),12);

        System.out.println(actualData);

    }

}
