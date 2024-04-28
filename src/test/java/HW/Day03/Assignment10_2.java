package HW.Day03;

import HW.Day03.Pojo.PetCategoryPojo;
import HW.Day03.Pojo.PetStorePetPojo;
import HW.Day03.Pojo.PetTagPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.testng.Assert.assertEquals;

public class Assignment10_2 {
    /**
     * //Write an automation test that will create, read, update, delete a 'pet'
     * using the "https://petstore.swagger.io/" document
     * (All actions must be done on same pet)
     * (Use Pojo) and using ArrayList.
     */
    private final String url = "https://petstore.swagger.io/v2/pet";
    private PetCategoryPojo petCategoryPojo;
    private PetTagPojo petTagPojo;
    private PetStorePetPojo expectedData;

    @BeforeClass
    public void beforeMethod() {
        //set the expected data
        //long id = System.currentTimeMillis();
        Integer id = (int) (System.currentTimeMillis() / 1000);
        petCategoryPojo = new PetCategoryPojo(id, "lion");
        ArrayList<String> photoUrlList = new ArrayList<>();
        photoUrlList.add("https://loremflickr.com/640/480/animals");
        petTagPojo = new PetTagPojo(id, "cute");
        ArrayList<PetTagPojo> tagsList = new ArrayList<>();
        tagsList.add(petTagPojo);
        expectedData = new PetStorePetPojo(id, petCategoryPojo, "sam", photoUrlList, tagsList, "available");


    }

    @Test
    public void createPetTest() {

        //Send the request and get the response
        System.out.println("expectedData = " + expectedData);
        Response response = given()
                .body(expectedData)
                .contentType(ContentType.JSON)
                .post(url);
        response.jsonPath().prettyPrint();

        //do assertion
        assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 1)
    public void GetPetTest() {
        //Send the request and get the response
        Response response = given()
                .contentType(ContentType.JSON)
                .get(url + "/" + expectedData.getId());
        response.jsonPath().prettyPrint();

        //do assertion


        assertEquals(response.getStatusCode(), 200);


    }

    @Test(priority = 2)
    public void updatePetTest() {
        //update the expected data
        expectedData.setName("Ema");
        expectedData.getCategory().setName("big cat");
        expectedData.getPhotoUrls().add("www.ur");
        expectedData.getTags().getFirst().setName("white");


        //Send the request and get the response
        System.out.println("expectedData = " + expectedData);
        Response response = given()
                .body(expectedData)
                .contentType(ContentType.JSON)
                .put(url);
        response.jsonPath().prettyPrint();

        //do assertion
        assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 3)
    public void deletePetTest() {

        //Send the request and get the response
        Response response = given()
                .contentType(ContentType.JSON)
                .delete(url + "/" + expectedData.getId());
        response.jsonPath().prettyPrint();

        //do assertion
        response
                .then()
                .statusCode(200)
                .body("code", equalTo(response.getStatusCode())
                        , "type", equalTo("unknown")
                        , "message", equalTo(expectedData.getId().toString())
                );
    }

    }