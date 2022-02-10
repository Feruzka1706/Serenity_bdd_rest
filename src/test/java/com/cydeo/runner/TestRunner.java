package com.cydeo.runner;


import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("API Test Suites")
@SelectPackages("com.cydeo.rest")
@IncludeTags("library")
//@IncludeTags( {"smoke","ddt"} )

public class TestRunner {


}
