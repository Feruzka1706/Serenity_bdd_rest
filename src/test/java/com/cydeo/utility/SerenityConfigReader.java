package com.cydeo.utility;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;

public class SerenityConfigReader {

    private static EnvironmentVariables environmentVariables ;
    static {
        environmentVariables =
                SystemEnvironmentVariables.createEnvironmentVariables();
    }


    public static String read(String propertyName){

        return EnvironmentSpecificConfiguration
                .from(environmentVariables)
                .getProperty(propertyName);

    }


}
