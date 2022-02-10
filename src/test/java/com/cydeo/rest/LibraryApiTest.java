package com.cydeo.rest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import static net.serenitybdd.rest.SerenityRest.*;
import  net.serenitybdd.rest.SerenityRest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.cydeo.utility.SerenityConfigReader.read;


@ExtendWith(SerenityJUnit5Extension.class)
public class LibraryApiTest {



    /**
     * Inside Serenity.conf file
     *  add library_url, librarian_email, librarian_password
     *  add them into library2 and 2 environment section
     * Create a new class called LibraryAPITest
     *
     * Create a test method testDashboard status
     *  inside tests
     *  Send request to POST /login to get token
     *  Send request to GET /dashboard_stats
     *    print out the numbers
     *    assuming that you already know the expected number
     *    verify all those 3 numbers match
     *
     *    run this test in both library2 and library3 environment
     */

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI=read("library.base.url");
        RestAssured.basePath=read("library.base.path");
    }

    @AfterAll
    public static void tearDown(){
      RestAssured.reset();


    }


    @Tag("library")
    @DisplayName("Testing /dashboard_stats")
    @Test
    public void testDashboardStatus(){

        //get token using POST /login endpoint
        String myToken=SerenityRest.given()
                .log().all()
                .formParam("email",read("librarian.email") )
                .formParam("password",read("librarian.password") )
                .contentType(ContentType.URLENC)
                .when()
                .post("/login").path("token");

        System.out.println("myToken = " + myToken);

        //GET /dashboard_stats and provide x-library-token header with above value
        SerenityRest.given()
                .log().uri()
                .header("x-library-token",myToken)
                .when()
                .get("/dashboard_stats") ;

        /**
         * {
         *     "book_count": "8508",
         *     "borrowed_books": "4499",
         *     "users": "7006"
         * }
         */

        System.out.println("lastResponse().path(\"book_count\") = "
                + lastResponse().path("book_count"));

        System.out.println("lastResponse().path(\"borrowed_books\") = "
                + lastResponse().path("borrowed_books"));

        System.out.println("lastResponse().path(\"users\") = "
                + lastResponse().path("users"));

        //Use API DB Combo to verify above numbers are correct
    }


}
