package com.automationexercise.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * Runs every feature file. Filter with tags, e.g. mvn test -Dcucumber.filter.tags="@smoke".
 */
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.automationexercise.steps", "com.automationexercise.hooks"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json",
                "junit:target/cucumber-reports/cucumber.xml"
        },
        monochrome = true)
public class TestRunner extends AbstractTestNGCucumberTests {
}
