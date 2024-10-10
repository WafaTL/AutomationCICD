package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber",glue="formation.stepDefinitions",
                  monochrome=true,tags="@ErrorValidation",plugin= {"html:target/Cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
