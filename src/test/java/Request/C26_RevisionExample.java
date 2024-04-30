package Request;

import base_urls.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.impl.cookie.BasicMaxAgeHandler;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class C26_RevisionExample extends DummyBaseUrl {
    /*
    Given
        https://dummy.restapiexample.com/api/v1/employees
    When
        User sends Get Request to the Url
    Then
        Status code is 200
    And
        There are 24 employees
    And
        "Tiger Nixon" and "Garrett Winters" are among the employees
    And
        The greatest age is 66
    And
        The name of the lowest age is "Tatyana Fitzpatrick"
    And
        Total salary of all employees is 6,644,770
 */

    @Test
    public void test(){
      spec.pathParam("1","employees") ;
        Response response = given(spec).when().get("{1}");
       // response.prettyPrint();
        Assert.assertEquals(response.statusCode(),200);

        // The number of users should  be 24
        JsonPath json = response.jsonPath();
        List<Integer> employees = json.getList("data.id");
        System.out.println("employees = " + employees);
        assertEquals(employees.size(), 24);
      //  "Tiger Nixon" and "Garrett Winters" are among the employees
//     response.then()
//      .body("data.employee_name", hasItems("Tiger Nixon", "Garrett Winters"));

      List <String>  names = json.getList("data.employee_name");
      assertTrue(names.contains("Tiger Nixon"));
      assertTrue(names.contains("Garrett Winters"));

      List <Integer> ageList =  json.getList("data.employee_age");
       int maxAge =0;
       for(Integer age : ageList){
           if(age>maxAge){
               maxAge=age;
           }

       }
       assertEquals(maxAge,66);

     //   The name of the lowest age is "Tatyana Fitzpatrick"
     int minAge = 100;
     for (Integer age : ageList){
         if (age<minAge){
             minAge=age;
         }
       Object youngestEmpl =  json.getList("data.findAll{it.employee_age=="+minAge+"}.employee_name").get(0);
        // System.out.println(youngestEmpl);
         assertEquals(youngestEmpl,"Tatyana Fitzpatrick");

     }

     //   Total salary of all employees is 6,644,770

      List <Integer> salaryList =  json.getList("data.employee_salary");

     int totalSalary =0;
     for(Integer salary :salaryList){
         totalSalary += salary;


     }
     assertEquals(6644770,totalSalary);

    }


    @Test
    public void test2(){
        // Set Url
        spec.pathParams("first","employees");

        //Sent request and Get Response
        Response response= given(spec).when().get("{first}");
        response.prettyPrint();

        // Status code is 200
        response.then().statusCode(200)
                .body("data.id",hasSize(24))
                .body("data.employee_name",hasItems("Tiger Nixon","Garrett Winters"));

        // There are 24 employees
        JsonPath json = response.jsonPath();
        int numberOfEmployees = json.getList("data.id").size();
        assertEquals(24,numberOfEmployees);

        // "Tiger Nixon" and "Garrett Winters" are among the employees
        List<String> names = json.getList("data.employee_name");
        assertTrue(names.contains("Tiger Nixon"));
        assertTrue(names.contains("Garrett Winters"));

        // The greatest age is 66
        List<Integer> ageList = json.getList("data.employee_age");
        int maxAge = 0;
        for (Integer age: ageList){
            if (age>maxAge){
                maxAge=age;
            }
        }
        assertEquals(66,maxAge);

        // The name of the lowest age is "Tatyana Fitzpatrick"
        int minAge =200;
        for (Integer age: ageList){
            if (age<minAge){
                minAge=age;
            }
        }
        Object youngestEmployee = json.getList("data.findAll{it.employee_age=="+minAge+"}.employee_name").get(0);
        assertEquals("Tatyana Fitzpatrick",youngestEmployee);

        // Total salary of all employees is 6,644,770
        List<Integer> salaryList =  json.getList("data.employee_salary");
        int totalSalary = 0;
        for (Integer salary: salaryList){
            totalSalary+=salary;
        }

        assertEquals(6644770,totalSalary);

    }

}

//public class team03_practise extends dummy{
//    /**
//     * // Base URL should be used as Spec
//     * // Given https://gorest.co.in/public/v2/todos/47900
//     * // When user send Request via GET Method
//     * // Then Assert that Status Code is "200"
//     * // And Assert that Content Type is "application/json"
//     * // And Assert that Response Body is as follows:
//     * {
//     *     "id": 47900,
//     *     "user_id": 6861183,
//     *     "title": "Et minus libero aegrotatio teres quia.",
//     *     "due_on": "2024-04-25T00:00:00.000+05:30",
//     *     "status": "pending"
//     * }
//     * */
//
//    @Test
//    public void test(){
//        specification.pathParams("1","todos","2","47900");
//
//        Response response = given(specification).get("{1}/{2}");
//        response.prettyPrint();
//
//        JsonPath jsonPath = response.jsonPath();
//
//        response.then().statusCode(200).contentType("application/json");
//        SoftAssert softAssert = new SoftAssert();
//        softAssert.assertEquals(response.statusCode(),200);
//        softAssert.assertEquals(jsonPath.getInt("id"),47900);
//        softAssert.assertEquals(jsonPath.getInt("user_id"),6861183);
//        softAssert.assertEquals(jsonPath.getString("title"),"Et minus libero aegrotatio teres quia.");
//        softAssert.assertEquals(jsonPath.getString("due_on"),"2024-04-25T00:00:00.000+05:30");
//        softAssert.assertEquals(jsonPath.getString("status"),"pending");
//        softAssert.assertAll();
//
//    }