import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import org.junit.runner.RunWith;


public class AcceptanceTest {

    @RunWith(Cucumber.class)
    @CucumberOptions(features= "useCases" ,monochrome = true , snippets = SnippetType.CAMELCASE , glue = {"carProj.AcceptanceTest"})

    public class acceptanceTest {

    }

}
