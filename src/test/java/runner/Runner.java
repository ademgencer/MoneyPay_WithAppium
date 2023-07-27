package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepdefs",
        tags = "",
        plugin = {"pretty",
                "json:test-output/cucumber-reports/cucumber.json",
                "html:test-output/cucumber-reports/cucumber.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)
public class Runner extends AbstractTestNGCucumberTests {
    // TestNG metotları yazılabilir, Cucumber dan önce çalışırlar.
}
