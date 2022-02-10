package com.cydeo.rest;


import com.github.javafaker.Faker;
import io.cucumber.java.sl.In;
import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.rest.SerenityRest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static net.serenitybdd.rest.SerenityRest.*;
import net.serenitybdd.rest.Ensure;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;


@ExtendWith(SerenityJUnit5Extension.class) //to get report otherwise it will run only Java programm
//and will not create report
public class SpartanDataTest extends SpartanBaseTest{



    @DisplayName("Test user Can get single Spartan Data")
    @Test
    public void test1Spartan(){

        //get last valid ID from /spartans
        SerenityRest.given()
                .log().uri()
                .when()
                .get("/spartans");
        int lastId=lastResponse().path("id[-1]");
        System.out.println("lastId = " + lastId);

        SerenityRest.given()
                .pathParam("id",lastId)
                .when()
                .get("/spartans/{id}");

       /** Ensure.that("Verify Status code is 200",thenPart-> thenPart.statusCode(200));
        Ensure.that("Verify Content Type is JSON",contentType-> contentType.contentType(ContentType.JSON));
        */

        Ensure.that("Status code is 200", v -> v.statusCode(200)    )
                .andThat("Content Type is JSON", v-> v.contentType(ContentType.JSON) )
                .andThat("The Id value is "+lastId
                        , v -> v.body("id" , is(lastId)   )      );

    }


    @Tag("ddt")
    @ParameterizedTest(name = "Testing Spartan Data with ID of {0}")
    @ValueSource(ints = {5,65,44,99,115} )//5,65,44,99,115
    //@ValueSource(ints = { 910, 911, 917, 918, 919}   )
    // use method source instead of magic number
    // get 10 data from the system and pass it here
    public void testOneSpartanDDT(int idParam) {

        //System.out.println("dParam = " + dParam);
        SerenityRest.given()
                .pathParam("id", idParam)
                .log().uri()
                .when()
                .get("/spartans/{id}");



        Ensure.that("Status code is 200", v -> v.statusCode(200)    )
                .andThat("Content Type is JSON", v-> v.contentType(ContentType.JSON) )
                .andThat("The Id value is "+idParam
                        , v -> v.body("id" , is(idParam)   )  );

    }




    @Tag("MethodSource")
    @ParameterizedTest(name = "Testing Spartan DDT with Method Source")
    @MethodSource("testMethodSource")
    public void testSpartanDDTWithMethodSource(int eachId){

        SerenityRest.given()
                .pathParam("id", eachId)
                .log().uri()
                .when()
                .get("/spartans/{id}");

        Ensure.that("Status code is 200", v -> v.statusCode(200)    )
                .andThat("Content Type is JSON", v-> v.contentType(ContentType.JSON) )
                .andThat("The Id value is "+eachId
                        , v -> v.body("id" , is(eachId)   )  );

    }




    public static List<Integer>  testMethodSource(){
//        //List<Integer> tenIdNums=new ArrayList<>();
//
//        SerenityRest.get("/spartans");
//        List<Integer> tenIdNums=new ArrayList<>();
//        List<Integer> allIdsFromSpartan= lastResponse().path("id");
//
//        for (int i = 1; i <11; i++) {
//            tenIdNums.add(allIdsFromSpartan.get(i));
//        }
        List<Integer> allSpartanItems=new ArrayList<>();

        allSpartanItems.addAll(SerenityRest.get("/spartans").jsonPath().getList("id"));
        List<Integer> listOfIds=new ArrayList<>();

        Faker faker =new Faker();

        for (int i = 1; i <=10 ; i++) {
            listOfIds.add(allSpartanItems.get(faker.number().numberBetween(0,allSpartanItems.size()-1)));
        }


        return listOfIds;
    }


    @Tag("CSVSource")
    @ParameterizedTest
    @CsvFileSource (resources ="/spartanInfos.csv",numLinesToSkip = 1)
    public void testSpartanDDTWithCSVSource(String eachName){

        SerenityRest.given()
                .log().uri()
                .when()
                .get("/spartans");


        Ensure.that("Status code is 200", v -> v.statusCode(200)    )
                .andThat("Content Type is JSON", v-> v.contentType(ContentType.JSON) )
                .andThat("The body contains "+eachName
                        , v -> v.body("name" , hasItem(eachName)   )  );



    }





}
