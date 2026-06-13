package com.omrbranch.runner;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.omrbranch.report.Reporting;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.omrbranch.stepdefinition",
        dryRun = false,
        monochrome = true,
        publish = true,
        stepNotifications = true,
        snippets = SnippetType.CAMELCASE,
        plugin = {
                "pretty",
                "json:target/output.json"
        })
public class TestRunnerClass {

    @AfterClass
    public static void afterClass() {
        Reporting.generateJvmReport("target/output.json");
    }
}  
