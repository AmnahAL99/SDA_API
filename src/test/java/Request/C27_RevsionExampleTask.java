package Request;

import base_urls.GorstBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class C27_RevsionExampleTask extends GorstBaseUrl {
    /*
        Given
            https://gorest.co.in/public/v1/users
        When
            User send GET Request
        Then
            The value of "pagination limit" is 10
        And
            The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        And
            The number of users should  be 10
        And
            We have at least one "active" status
        And
            "Abhaidev Kaur", "Fr. Deenabandhu Adiga", "Akshita Singh DC" are among the users -> Data may change
        And
            The female users are less than or equals to male users -> Data may change
*/

    @Test
    public void test() {
        // Set Url
        spec.pathParam("first", "users");

        Response response = given(spec).when().get("{first}");
        //response.prettyPrint();

        JsonPath json = response.jsonPath();

        // The value of "pagination limit" is 10
        Integer limit = json.get("meta.pagination.limit");
        System.out.println("limit = " + limit);
        assertEquals(limit, 10);

        // The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        String link = json.getString("meta.pagination.links.current");
        System.out.println("link = " + link);
        assertEquals(link, "https://gorest.co.in/public/v1/users?page=1");

        // The number of users should  be 10
        List<Integer> users = json.getList("data.id");
        System.out.println("users = " + users);
        assertEquals(users.size(), 10);

        // We have at least one "active" status
        List<String> status = json.getList("data.findAll{it.status=='active'}.status");
        System.out.println("status = " + status);
        assertTrue(status.size() >= 1);

        // "Draupadi Singh", "Msgr. Gati Mahajan", "Radha Mukhopadhyay" are among the users -> Data may change
        Object name1 = json.getList("data.findAll{it.name=='Draupadi Singh'}.name").get(0);
        assertEquals(name1, "Draupadi Singh");

        Object name2 = json.getList("data.findAll{it.name=='Msgr. Gati Mahajan'}.name").get(0);
        assertEquals(name2, "Msgr. Gati Mahajan");

        Object name3 = json.getList("data.findAll{it.name=='Radha Mukhopadhyay'}.name").get(0);
        assertEquals(name3, "Radha Mukhopadhyay");

        // The female users are less than or equals to male users -> Data may change
        List<String> females = json.getList("data.findAll{it.gender=='female'}.gender");
        System.out.println("females = " + females);

        List<String> males = json.getList("data.findAll{it.gender=='male'}.gender");
        System.out.println("males = " + males);

        assertTrue(females.size() <= males.size());

    }

    // diff way
    @Test
    public void test2() {
        // Set Url
        spec.pathParam("1", "users");

        // Send Request and get response
        Response response = given(spec).when().get("{1}");
         response.prettyPrint();
        // Do assertions
        response
                .then()
                .body("meta.pagination.limit", equalTo(10))
                .body("meta.pagination.links.current", equalTo("https://gorest.co.in/public/v1/users?page=1"))
                .body("data.name", hasSize(10))
                .body("data.status", hasItem("active"))
                .body("data.name", hasItems("Mohinder Kaur", "Msgr. Bheeshma Bhattathiri", "Dhanadeepa Verma"))
        ;

        JsonPath json = response.jsonPath();
        int numOfFemale = json.getList("data.findAll{it.gender=='female'}").size();
        int numOfMale = json.getList("data.findAll{it.gender=='male'}").size();

        assertTrue(numOfFemale >= numOfMale);
    }
}