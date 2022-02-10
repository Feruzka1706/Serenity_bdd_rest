package com.cydeo.rest;


import com.cydeo.utility.SpartanUtil;
import io.cucumber.java.af.En;
import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

//Our maven pom.xml is set to run only what's defined in Testrunner
//using mvn clean verify
// mvn clean verify -Dtest=YourClassNameGoesHere
//mvn clean verify -Dtest=SpartanUpdateDeleteTest

//How to just run certain tag using mmvn command
//mvn clean verify -Dgroups=YourTagGoesHere
//

@ExtendWith(SerenityJUnit5Extension.class)
public class SpartanUpdateDeleteTest extends SpartanBaseTest{


  // @Tag("wip")
  @DisplayName("Test PUT /spartans/{id} endpoint")
 @Test
public void testUpdateOneData(){
        //assuming that we have a valid ID 917

        Map<String,Object> bodyMap= SpartanUtil.getRandomSpartanMapBody();
        int validRandomId=SpartanUtil.getRandomID();
        SerenityRest.given()
                .pathParam("id",validRandomId)
                .contentType(ContentType.JSON)
                .body(bodyMap)
                .log().uri()
            .when()
                .put("/spartans/{id}");

        Ensure.that("Update Successful! ",v->v.statusCode(204) );

        //Verify this worked by sending another get request
        SerenityRest.given()
                .pathParam("id",validRandomId)
                .log().uri()
                .when()
                .get("/spartans/{id}");

        Ensure.that("Name is "+bodyMap.get("name"),v->v.body("name",is(bodyMap.get("name") ) ) );

        Ensure.that("Gender is "+bodyMap.get("gender"),v->v.body("gender",is(bodyMap.get("gender") ) ) );

        Ensure.that("Phone is "+bodyMap.get("phone"),v->v.body("phone",is(bodyMap.get("phone") ) ) );


        //another equivalence of Ensure.that is restAssuredThat() method
            SerenityRest.restAssuredThat(  v-> v.statusCode(200)  );


        //HOMEWORK: verify this worked from DB

}

     @Tag("wip")
    //DELETE /spartans/{id}
    @DisplayName("Test DELETE /spartans/{id} endpoint")
    @Test
    public void testDelete(){
       int validRandomId=SpartanUtil.getRandomID();

       SerenityRest.given()
               .pathParam("id",validRandomId)
               .log().uri()
               .when()
               .delete("/spartans/{id}");

       //SerenityRest.restAssuredThat( v->v.statusCode(204) );
         Ensure.that("Update Successful! ",v->v.statusCode(204) );

         //Verify this worked by sending another get request this time we should get 404 status code
         SerenityRest.given()
                 .pathParam("id",validRandomId)
                 .log().uri()
                 .when()
                 .get("/spartans/{id}");

         Ensure.that("Deleted Data does not exists ",v->v.statusCode(404) );


     }



}
