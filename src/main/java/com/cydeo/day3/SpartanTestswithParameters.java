package com.cydeo.day3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestswithParameters {

    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we don't need to type each http method.
        RestAssured.baseURI = "http://35.174.107.83:8000";
    }

     /*   Given accept type is Json
          And Id parameter value is 5
          When user sends GET request to /api/spartans/{id}
          Then response status code should be 200
          And response content-type: application/json
          And "Blythe" should be in response payload
       */

    @DisplayName("GET request to /api/spartans/{id} with ID 5")
    @Test
    public void test1(){

        Response response = given().
                accept(ContentType.JSON)
                .and().pathParam("id", 5)
                .when()
                .get("/api/spartans/{id}");

        //verify status code
        assertEquals(200, response.statusCode());

        //verify content type
        assertEquals("application/json", response.contentType());

        //verify Blythe in the json payload/body
        assertTrue(response.body().asString().contains("Blythe"));


    }


     /*
        TASK
        Given accept type is Json
        And Id parameter value is 500
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 404
        And response content-type: application/json
        And "Not Found" message should be in response payload
     */

    @DisplayName("GET request /api/spartans/{id} Negative Test")
    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 500)
                .when()
                .get("/api/spartans/{id}");

        //verify status code 404
        assertEquals(404, response.statusCode());

        //verify content type
        assertEquals("application/json", response.contentType());

        //verify Not Found in the json payload/body
        assertTrue(response.body().asString().contains("Not Found"));

    }

    /*
        Given accept type is Json
        And query parameter values are:
        gender|Female
        nameContains|e
        When user sends GET request to /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json
        And "Female" should be in response payload
        And "Janette" should be in response payload
     */

    @DisplayName("GET resquest to /api/spartans/search With Query Params")
    @Test
    public void test3(){

        //log().all() : give all information about the request
        Response response = given().log().all().
                accept(ContentType.JSON)
                .and().queryParam("nameContains", "e")
                .and().queryParam("gender", "Female")
                .when()
                .get("/api/spartans/search");

        //verify status code 200
        assertEquals(200, response.statusCode());

        //verify content type
        assertEquals("application/json", response.contentType());

        //"Female" should be in response payload
        assertTrue(response.body().asString().contains("Female"));

        //"Janette" should be in response payload
        assertTrue(response.body().asString().contains("Janette"));


    }

    @DisplayName("GET request to /api/spartans/search With Query Params (MAP)")
    @Test
    public void test4() {

        //Create a map and add query parameters
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("nameContains", "e");
        queryMap.put("gender", "Female");

        Response response = given().log().all().
                accept(ContentType.JSON)
                .and().queryParams(queryMap)
                .when()
                .get("/api/spartans/search");

        //verify status code 200
        assertEquals(200, response.statusCode());

        //verify content type
        assertEquals("application/json", response.contentType());

        //"Female" should be in response payload
        assertTrue(response.body().asString().contains("Female"));

        //"Janette" should be in response payload
        assertTrue(response.body().asString().contains("Janette"));



    }


    }






