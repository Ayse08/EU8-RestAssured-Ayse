package com.cydeo.day1;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimpleGetRequest {

    String url = "http://35.174.107.83:8000/api/spartans";

@Test
    public void test1(){

    //send a get request and save response inside the Response object
    Response response = RestAssured.get(url);

    //print response status code
    System.out.println(response.statusCode());

    //print response body
    response.prettyPrint();

}

/*
Given no headers provided
When Users sends GET request to api/hello
Then response status code should be 200
And content type header should be "text/plain;charset=UTF-8"
And header should contain date
And content-Length should be 17
And body should be "Hello from Sparta"
 */

    String baseUrl = "http://35.174.107.83:8000";

    @Test
    public void test3(){

        //send request and save response inside the response object
        Response response = RestAssured.when().get(baseUrl + "/api/hello");

        //verify status code 200
        Assertions.assertEquals(200, response.statusCode());

        //verify content type
        Assertions.assertEquals("text/plain;charset=UTF-8", response.contentType());

        //verify we have headers named date
        //we use hasHeaderWithName method to verify header exist or not- it returns boolean
        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));

        //how to get and header from response using header key?
        //we use response.header(String headerName) method to get any header value
        System.out.println("response.header(\"Content-Length\") = " + response.header("Content-Length"));
        System.out.println("response.header(\"Date\") = " + response.header("Date"));

        //verify content length is 17
        Assertions.assertEquals("17", response.header("Content-Length"));

        //verify body is "Hello from Sparta"
        Assertions.assertEquals("Hello from Sparta", response.body().asString());
    }

}
