package HW.Day03;

import base_urls.SwaggerBaseUrl;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.PetPlaceHolderPojo;


import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class Assignment10 extends SwaggerBaseUrl {
    /*
Write an automation test that will create, read, update, delete a 'pet' using the
 "https://petstore.swagger.io/" document
(All actions must be done on same pet)
(Use Pojo)
     */
    private static final String url = "https://petstore.swagger.io/v2/pet";

    @Test
    public void requestTest() {
        PetPlaceHolderPojo pet = new PetPlaceHolderPojo(5, "Doggie", "available");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(pet)
                .when()
                .post(url);

        assertEquals(response.getStatusCode(), 200);
        response.prettyPrint();
    }

    @Test(priority = 1)
    public void GetPet() {
        // Send a GET request to retrieve pet information from the server
        Response response = RestAssured.get(url + "/5");
        // Assert that the response status code is 200 (OK)
        assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 2)
    public void UpdatePet() {
        PetPlaceHolderPojo pet = new PetPlaceHolderPojo(5, "Doggie", "sold");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(pet)
                .when()
                .put(url);
       // response.prettyPrint();
        assertEquals(response.getStatusCode(), 200);

    }

// Directly using RestAssured.delete():
    @Test(priority = 3)
    public void DeletePet() {
        Response response = RestAssured.delete(url + "/5");

        assertEquals(response.getStatusCode(), 200);
    }

}