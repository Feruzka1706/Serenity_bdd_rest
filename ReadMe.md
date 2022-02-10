#Serenity- BDD- Rest


1.serenity dependency
````
 <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-core</artifactId>
            <version>3.1.1</version>
        </dependency>

````
2. Serenity dependency with Rest Assured
````
<dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-rest-assured</artifactId>
            <version>3.1.1</version>
        </dependency>
````
3. Added Junit dependency
````
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.8.2</version>
        </dependency>
````
4. Added Serenity Junit5 dependency
````
  <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-junit5</artifactId>
            <version>3.1.1</version>
        </dependency>
````
5. Adding Junit Suit dependency
````
 <dependency>
            <groupId>org.junit.platform</groupId>
            <artifactId>junit-platform-suite</artifactId>
            <version>1.8.2</version>
        </dependency>
`````
Added here plugins. Make sure add your  plugin after dependencies
````
<build>
        <plugins>
<!--            sure fire plugin -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.0.0-M5</version>
                <configuration>
                    <testFailureIgnore>true</testFailureIgnore>
                </configuration>
            </plugin>
````
Where the report is being generated after the test run
````
 <plugin>
                <groupId>net.serenity-bdd.maven.plugins</groupId>
                <artifactId>serenity-maven-plugin</artifactId>
                <version>3.1.1</version>
                <executions>
                    <execution>
                        <id>serenity-reports</id>
                        <phase>post-integration-test</phase>
                        <goals>
                            <goal>aggregate</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>

        </plugins>
````

Runing only single class is
``````
mvn clean verify -Dtest=SpartanDataTest 
``````
Running only inside `TestRunner class`
````
mvn clean verify -Drest=TestRunner
````
```//Our maven pom.xml is set to run only what's defined in Testrunner
//using mvn clean verify
// mvn clean verify -Dtest=YourClassNameGoesHere
mvn clean verify -Dtest=SpartanUpdateDeleteTest```

running specific tag from mvn command``` mvn clean verify -Dgroup=MethodSource ```

```
```
mvn clean test -D"bla=b23 is great"
System.out.println("System.getProperties(\"bla\") = "
                    + System.getProperty("env"));

            if( System.getProperty("env").equals("qa1")  ){

                System.out.println("DO QA1 ENVIRONMENT STUFF HERE");

            }else if( System.getProperty("env").equals("qa2") ){

                System.out.println("DO QA3 ENVIRONMENT STUFF HERE");

            }
```
