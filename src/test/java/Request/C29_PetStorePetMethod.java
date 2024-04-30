package Request;

import HW.Day05.UserObject;
import base_urls.SwaggerBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.pojpPetStore.Category;
import pojo.pojpPetStore.PetStoreResponse;
import pojo.pojpPetStore.TagsItem;
import utilities.ObjecyMapperUtilites;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class C29_PetStorePetMethod extends SwaggerBaseUrl {
    //Write an automation test that will create a 'user' then read, update and delete
    // the created user using the "https://petstore.swagger.io/" document.
    // (Create a classes for each request.)
  int id = 2030;
    @Test
    public void createPetTest() {
        //set url
        spec.pathParam("1", "pet");
        // set expected data

        String payLode = """
                {
                  "id": 2030,
                  "category": {
                    "id": 3,
                    "name": "Doggie"
                  },
                  "name": "Aldo",
                  "photoUrls": [
                    "string","another"
                  ],
                  "tags": [
                    {
                      "id": 1,
                      "name": "Cute"
                    },
                    {
                      "id": 2,
                      "name": "Cheap"
                    }
                  ],
                  "status": "available"
                }""";


        PetStoreResponse expectedData = ObjecyMapperUtilites.conversJsonToJava(payLode, PetStoreResponse.class);
       // expectedData.getTags().get(1).getName();


        /*
         """
                {
                  "id": 2030,
                  "category": {
                    "id": 3,
                    "name": "Doggie"
                  },
                  "name": "Aldo",
                  "photoUrls": [
                    "string","another"
                  ],
                  "tags": [
                    {
                     tag1
                    },
                    {
                   tag2
                    }
                  ],
                  "status": "available"
                }"""
         */

        TagsItem tag1 = new TagsItem("cute",1);
        TagsItem tag2 = new TagsItem("Cheap",2);
        List<TagsItem> tag = new ArrayList<>();
        tag.add(tag1);
        tag.add(tag2);
        List<String> url = new ArrayList<>();
        url.add("string");
        url.add("another");

        Category category = new Category("Doggie",3);
        PetStoreResponse paylode2 = new PetStoreResponse(url,"Aldo",id,category,tag,"available");



        Response response = given(spec).body(expectedData).post("{1}");
        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),200);


    }

    @Test
    public void getPetTest(){
        spec.pathParams("1","pet","2",id);
        // get response
       Response response = given(spec).when().get("{1}/{2}");
        response.prettyPrint();
    }
}