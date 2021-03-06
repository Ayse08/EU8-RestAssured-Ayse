package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



public class SpartanGetRequests {

    String baseUrl = "http://35.174.107.83:8000";

    // Given Accept type application/json
    // When user send GET request to api/spartans end point
    // Then status code must 200
    // Then response Content Type must be application/json
    // And response body should include spartan result

    @Test
    public void test1(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(baseUrl + "/api/spartans");

        // print status code from repsonse object
        System.out.println("response.statusCode() = " + response.statusCode());

        //printing response content type from response object
        System.out.println("response.contentType() = " + response.contentType());

        //print whole result body
        response.prettyPrint();

        // how to do API testing then?
        Assertions.assertEquals(response.statusCode(), 200);

        //verify content type is application/json
        Assertions.assertEquals(response.contentType(), "application/json");


    }

    /*
    Given accept is application/json
    When user sends a get request to api/spartans/3
    Then status code should be 200
    And content type should be application/json
    and json body should contain Fidole
     */

    @Test
    public void test2(){

        Response response = RestAssured.given().accept(ContentType.JSON).
                when().get(baseUrl + "api/spartans/3");

        //verify status code 200
        Assertions.assertEquals(200, response.statusCode());

        //verify content type
        Assertions.assertEquals("application/json", response.contentType());

        //verify json body contains Fidole
        Assertions.assertTrue(response.body().asString().contains("Fidole"));

    }

}
