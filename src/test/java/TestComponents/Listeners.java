package TestComponents;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Framework.resources.ExtendReporterNG;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class Listeners extends BaseTest implements ITestListener {

    ExtentReports extent = ExtendReporterNG.getReportObject();
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    ThreadLocal<WebDriver> driverRef = new ThreadLocal<>(); // 👈 To hold active driver

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getTestClass().getName() + "." + result.getMethod().getMethodName();
        ExtentTest test = extent.createTest(testName);
        extentTest.set(test);

        // Save driver reference at test start
        Object testInstance = result.getInstance();
        if (testInstance instanceof BaseTest) {
            driverRef.set(((BaseTest) testInstance).driver);
        }

        System.out.println("🚀 Test started: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "✅ Test Passed Successfully");
        System.out.println("✅ Test passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().fail(result.getThrowable());

        try {
            WebDriver activeDriver = driverRef.get();
            if (activeDriver != null) {
                String screenshotPath = takeScreenshot(result.getMethod().getMethodName(), activeDriver);
                extentTest.get().addScreenCaptureFromPath(screenshotPath, result.getMethod().getMethodName());
                System.out.println("📸 Screenshot captured for failed test: " + screenshotPath);
            } else {
                System.out.println("⚠️ No active driver found to take screenshot for: " + result.getName());
            }
        } catch (IOException e) {
            System.out.println("⚠️ Failed to capture screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        System.out.println("🏁 Test Suite finished: " + context.getName());
    }

    private String takeScreenshot(String testName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/reports/" + testName + ".png";
        FileUtils.copyFile(source, new File(path));
        return path;
    }
}


