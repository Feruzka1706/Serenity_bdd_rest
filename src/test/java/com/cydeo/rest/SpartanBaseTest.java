package com.cydeo.rest;

import static com.cydeo.utility.SerenityConfigReader.read;
import io.restassured.RestAssured;
import net.serenitybdd.rest.SerenityRest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class SpartanBaseTest {

    // Send request to GET http://54.236.150.168:8000/api/hello
    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = read("spartan.base.url");
        RestAssured.basePath = "/api";

    }
    @AfterAll
    public static void teardown() {
        RestAssured.reset();

        //there is also a method to ensure to reset all restassured static fields
        //that serenity use Serenity.reset() method
        SerenityRest.rest();
    }


}
