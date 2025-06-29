package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/resources/features", 
		glue= {"stepDefinitions", "hooks"},
		plugin={"pretty", "html:target/CucumberReports/cucumberReport.html",
				"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
		publish=true,
		dryRun=false,
		monochrome=true,
		tags=""
)

public class TestRunner extends AbstractTestNGCucumberTests {
	
	

}
