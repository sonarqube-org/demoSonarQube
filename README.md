# launch321-link-api-automation
This repo is for API testing and generating Allure report for all the UPSIGHT services.

launch321-link-api-automation

### Allure JUnit5 Integration (using Maven)

sudo apt-get install allure

Then build the project (build requires JDK 1.8 or higher):

bash
$ ./mvnw clean verify


Then, to build Allure report run

bash
$ ./mvnw allure:report


In order to view the report run

bash
$ ./mvnw allure:serve

Dependency need to into POM : 

Rest Assured : 

<dependency>
            <groupId>com.ontestautomation</groupId>
            <artifactId>restassured-graphql</artifactId>
            <version>1.0-SNAPSHOT</version>
            <scope>test</scope>
</dependency>


Alluer : 

<dependency>
            <groupId>io.qameta.allure</groupId>
            <artifactId>allure-junit5</artifactId>
            <version>${allure.version}</version>
</dependency>

