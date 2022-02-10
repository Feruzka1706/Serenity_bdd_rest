package com.cydeo.rest;

import com.cydeo.pojo.Spartan;
import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@ExtendWith(SerenityJUnit5Extension.class)
public class SpartanAddDataTest extends SpartanBaseTest{


    @DisplayName("POST /spartans to Add 1 data")
    @Test
    public void testAddOneData(){

        Spartan spartan1=new Spartan("B23 Serenity","Female",123647382392L);
        System.out.println("spartan1 = " + spartan1);

        SerenityRest.given()
                .log().body()
                .contentType(ContentType.JSON)
                .body(spartan1)
                .when()
                .post("/spartans");

        Ensure.that("Status code is 201",v-> v.statusCode(201) )
                .andThat("name is ",v-> v.body("data.name",is(spartan1.getName()) )
                );


    }

}
