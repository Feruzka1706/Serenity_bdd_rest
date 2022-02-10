package com.cydeo.rest;

import static com.cydeo.utility.SerenityConfigReader.read;

import com.cydeo.utility.SerenityConfigReader;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SerenityJUnit5Extension.class)
public class SerenityPracticeTest {


    /**
     * Serenity.properties file is used by serenity to configuration
     * and we can also add our own key value pair directly there
     * and access them using the method provided by the library itself
     */

    @Tag("wip2")
    @Test
    public void testOutReader(){

       String url= read("spartan.base.url");
        System.out.println("url = " + url);

        String url2= SerenityConfigReader.read("spartan.base.url");
        System.out.println("url2 = " + url2);


        System.out.println("read(\"cool\") = " + read("cool"));

        /**If you run it like this
         mvn clean test -Denvironment=3
        you will get https://library3.cybertekschool.com

        If you run it like this
        mvn clean test -Denvironment=2
         you will get https://library2.cybertekschool.com

         If you don't specify any environment
         It will go to default section, in this case
         you will get https://library2.cybertekschool.com by default
         */
        System.out.println("read(\"library.base.url\") = " + read("library.base.url"));

    }



}
