package HW.Day02;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Assignment05 {

      /*
       Given
              https://reqres.in/api/unknown/
       When
            I send GET Request to the URL
       Then

            1)Status code is 200
            2)Print all pantone_values
            3)Print all ids greater than 3 on the console
              Assert that there are 3 ids greater than 3
            4)Print all names whose ids are less than 3 on the console
              Assert that the number of names whose ids are less than 3 is 2
    */

    @Test
    public void requestTest() {

        String url = "https://reqres.in/api/unknown/";

        Response response = given().get(url);

        //1)Status code is 200
        response.then().statusCode(200);

        //2)Print all pantone_values
        JsonPath jsonPath = response.jsonPath();
        List<String> pantoneValues = jsonPath.getList("data.pantone_value");
        System.out.println("\nPantone Values: " + pantoneValues);

        // 3) Print all ids greater than 3 on the console and assert that there are 3 ids greater than 3
        List<Integer> idsGreaterThan3 = jsonPath.getList("data.findAll { it.id > 3 }.id");
        System.out.println("\nIds greater than 3: " + idsGreaterThan3);
        Assert.assertEquals(idsGreaterThan3.size(), 3);

        // 4) Print all names whose ids are less than 3 on the console and assert that the number of names whose ids are less than 3 is 2
        List<String> namesLessThan3 = jsonPath.getList("data.findAll { it.id < 3 }.name");
        System.out.println("\nNames whose ids are less than 3: " + namesLessThan3);
        Assert.assertEquals(namesLessThan3.size(), 2);

    }

}