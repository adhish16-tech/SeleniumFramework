	package cucumber;

	import io.cucumber.testng.AbstractTestNGCucumberTests;
	import io.cucumber.testng.CucumberOptions;

	@CucumberOptions(
	    features = "src/test/java/cucumber/SubmitOrder.feature",  // Direct path to your feature file
	    glue = "StepDef.copy2",              // Your step definition package
	    monochrome = true,                                        // Cleaner console output
	    plugin = {
	        "pretty",                                             // Pretty console logs
	        "html:target/cucumber-reports.html",                  // HTML report
	        "json:target/cucumber-reports/Cucumber.json",         // JSON report
	        "junit:target/cucumber-reports/Cucumber.xml"          // JUnit report
	    }
	)
	public class TestngTestRunner extends AbstractTestNGCucumberTests {
	}

