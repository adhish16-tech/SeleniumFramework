package Framework.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtendReporterNG {

	public static ExtentReports getReportObject() {
		String path = "/Users/adhishgurjar/eclipse-workspace/SeleniumFrameworkDesign/reports/index.html";

    	System.out.println("Report path: " + path);
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");

        ExtentReports   extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Adhish Singh");
        return extent;
	}
}
