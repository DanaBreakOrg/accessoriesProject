package myPackage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features= "useCases" ,monochrome = true , snippets = SnippetType.CAMELCASE , glue = {"myPackage"})

public class AcceptanceTest {


}
