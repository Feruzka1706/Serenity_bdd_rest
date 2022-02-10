package com.cydeo.rest;


import io.cucumber.java.af.En;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityJUnit5Extension;

import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;
import org.junit.jupiter.api.*;

import static org.hamcrest.Matchers.*;

import static org.junit.jupiter.api.Assertions.*;

import static net.serenitybdd.rest.SerenityRest.*;

import org.junit.jupiter.api.extension.ExtendWith;


@ExtendWith(SerenityJUnit5Extension.class)
public class SpartanHelloTest {

    // Send request to GET http://54.236.150.168:8000/api/hello
    @BeforeAll
    public static void setUp(){

       RestAssured.baseURI = "http://100.26.44.167:8000";
        RestAssured.basePath = "/api";

    }
    @AfterAll
    public static void teardown() {
        RestAssured.reset();
    }


    @Tag("smoke")
    @Test
    public void testHello(){

       SerenityRest. given()
                .log().all().
                when()
                .get("/hello").
                then()
                .log().all()
                .statusCode(200);

       //.lastResponse() method from SerenityRest will return
        //get response object from above request
       lastResponse().prettyPeek();

       //can you print out the Date header using the response object
        System.out.println("lastResponse().header(\"Date\") = " + lastResponse().header("Date"));
        System.out.println("lastResponse().contentType() = " + lastResponse().contentType());


    }


    @DisplayName("Test Hello with detailed steps in report")
    @Test
    public void testHelloResponseStepByStep(){

        SerenityRest.given()
                .log().uri()
                .when()
                .get("/hello");
//                .then()
//                .log().body()

        assertEquals(200,lastResponse().statusCode());

        //If you want to add the assertion of the response
        //in the report as a separate step,
        //Ensure class from Serenity to provide the way
        //to let our response assertion show up as a srtep
        //here is how

        /**
         * Ensure.that("Status Code is 200",validatableResponse ->
         * validatableResponse.statusCode(200));
         * intellij helps us if we hit ctlr space for the second parameter
         */
        Ensure.that("Verify  Status code is 200",
                thenPart -> thenPart.statusCode(200)
                );

        Ensure.that("Content Type is Text Plain",
                contentType-> contentType.contentType(ContentType.TEXT)
                );

        Ensure.that("Body is Hello From Sparta",
                thenPart -> thenPart.body(is("Hello from Sparta"))
        );


    }



}
