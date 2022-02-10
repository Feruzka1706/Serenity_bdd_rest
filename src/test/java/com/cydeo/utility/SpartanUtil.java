package com.cydeo.utility;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import net.serenitybdd.rest.SerenityRest;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SpartanUtil {


    public static Map<String,Object> getRandomSpartanMapBody(){
        /**
         * Having utility method, so we can just call a method as below
         * 1. create a class under utility package with name SpartanUtil
         * 2. create a public static method with return type of Map<String, Object>
         * 3. add above code you already wrote for method body and return the bodyMap from the method
         * 4. call the method here to get the random body
         */
        Map<String,Object> bodyMap=new LinkedHashMap<>();
        Faker faker =new Faker();

        bodyMap.put("name",faker.name().firstName());
        bodyMap.put("gender",faker.demographic().sex());//Male or Female
        bodyMap.put("phone",faker.number().numberBetween(5000000000L,9999999999L));
        //bodyMap.put("phone", faker.number().numberBetween(5000000000L,9999999999L));
        //numerify("###########")

        return bodyMap;
    }

    public static int getRandomNumber(int num1, int num2){

        Faker faker=new Faker();
        int  result=faker.number().numberBetween(num1,num2);

        return result;
    }


    public static int getRandomID() {

        List<Integer> listOfAllSpartanIds = SerenityRest.get("/spartans").path("id") ;

        Faker faker = new Faker();
        int validIndex = faker.number().numberBetween(0, listOfAllSpartanIds.size());
        Integer validId = listOfAllSpartanIds.get(validIndex);

        return validId;

    }

    /**
     * @return
     * Creating utility method which returns randomly  id Json Spartans response
     * only difference is this one will work with Authorized url address
     */
    public static int getRandomIdAuthorized() {

        int idNum=getRandomNumber(RestAssured.given()
                .auth().basic("admin","admin")
                .get("http://52.87.222.1:7000/api/spartans").path("id[0]"),
                RestAssured.given().auth().basic("admin","admin")
                        .get("http://52.87.222.1:7000/api/spartans").path("id[-1]") );

        return idNum;
    }


    public static int getLastIdAuthorized(){

        int lastId=RestAssured.given()
                .auth().basic("admin","admin")
                .get("http://52.87.222.1:7000/api/spartans").path("id[-1]");

        return lastId;
    }

}
