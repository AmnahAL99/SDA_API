package Request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.common.mapper.TypeRef;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.BookingPojo;
import pojo.JsonPlaceHolderPojo;
import utilities.ObjecyMapperUtilites;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static utilities.ObjecyMapperUtilites.conversJsonToJava;

public class C09_Groovy extends JsonPlaceHolderBaseUrl {
/*
    Given
        https://jsonplaceholder.typicode.com/todos
    When
         I send GET Request to the URL
    Then
         1)Status code is 200
         2)Print all ids greater than 190 on the console
           Assert that there are 10 ids greater than 190
         3)Print all 'completes' whose ids are less than 5 on the console
           Assert that the number of userIds whose ids are less than 5 is 4
         4)Print all titles whose ids are greater than 195
           Assert that "quis eius est sint explicabo" is one of the titles whose id is less than 5
         5)Print id whose title is "quo adipisci enim quam ut ab"
           Assert that id is 8
*/
    @Test
    public void groovyTest() {

        spec.pathParams("first", "todos");
        Response response = given(spec).get("{first}");
        //response.prettyPrint();
        JsonPath jsonPath = response.jsonPath();
        response.then()
                .statusCode(200);

//        2)Print all ids greater than 190 on the console
//        Assert that there are 10 ids greater than 190
        List<Integer> idlist = jsonPath.getList("id");
        //System.out.println("idlist = " + idlist);
        int idsGreaterThan190 = 0;
        for (int w : idlist) {
            if (w > 190) {
                //System.out.println(w);
                idsGreaterThan190++;
            }
        }
        Assert.assertEquals(idsGreaterThan190, 10);

//        3)Print all 'completes' whose ids are less than 5 on the console
//        Assert that the number of userIds whose ids are less than 5 is 4
        List<Boolean> completedList = jsonPath.getList("findAll{it.id<5}.completed");
        System.out.println("completedList" + completedList);
        //  List<Boolean> completedList2 = jsonPath.getList("findAll{it.completed==false}.id");
        //System.out.println("completedList2"+ completedList2);
        Assert.assertEquals(completedList.size(), 4);

//        4)Print all titles whose ids are greater than 195
//        Assert that "quis eius est sint explicabo" is one of the titles whose id is less than 5
        List<String> titles = jsonPath.getList("findAll{it.id>195}.title");
        Assert.assertTrue(titles.contains("quis eius est sint explicabo"));

//        5)Print id whose title is "quo adipisci enim quam ut ab"
//        Assert that id is 8
        Object idWithTitle = jsonPath.getList("findAll{it.title=='quo adipisci enim quam ut ab'}.id").getFirst();
        Assert.assertEquals(idWithTitle, 8);

    }

    @Test
    public void test(){
        spec.pathParams("first", "todos");
        //Set the expected data
        String st = """
                                    {
                                            "userId": 1,
                                            "id": 4,
                                            "title": "et porro tempora",
                                            "completed": true
                                    }
                """;
        Map<String,Object> payLoad = conversJsonToJava(st, Map.class);


        Response response = given(spec).get("{first}");
        JsonPath jsonPath = response.jsonPath();
        Object userId = jsonPath.getList("findAll{it.id==4}.userId").get(0);
        Object title = jsonPath.getList("findAll{it.id==4}.title").get(0);
        Object completed = jsonPath.getList("findAll{it.id==4}.completed").get(0);
        //  response.prettyPrint();
        assertEquals(payLoad.get("userId"), userId);
        assertEquals(payLoad.get("title"), title);
        assertEquals(payLoad.get("completed"), completed);
        //diff way
        Object actualTodo = jsonPath.getList("findAll{it.id==4}.userId").get(0);
        System.out.println(actualTodo);

        Map<String, Object> actualTodoMap = (Map) actualTodo;
        assertEquals(payLoad.get("userId"), actualTodoMap.get("userId"));

    }


}
