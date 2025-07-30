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


//package TestComponents;
//
//import java.io.File;
//import java.io.IOException;
//
//import org.openqa.selenium.WebDriver;
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.Status;
//
//import Framework.resources.ExtendReporterNG;
//
//public class Listeners extends BaseTest implements ITestListener {
//
//   // public static Throwable lastThrowable;
//	ExtentReports extent = ExtendReporterNG.getReportObject();
//    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
//
//    @Override
//    public void onTestStart(ITestResult result) {
//        String testName = result.getTestClass().getName() + "." + result.getMethod().getMethodName();
//        ExtentTest test = extent.createTest(testName);
//        extentTest.set(test);
//        System.out.println("🚀 Test started: " + testName);
//    }
//
//    @Override
//    public void onTestSuccess(ITestResult result) {
//        extentTest.get().log(Status.PASS, "✅ Test Passed Successfully");
//        System.out.println("✅ Test passed: " + result.getName());
//    }
//
//    @Override
//    public void onTestFailure(ITestResult result) {
//        extentTest.get().fail(result.getThrowable());
//        captureScreenshot(result, "❌ Screenshot for FAILED test");
//    }
//
//    @Override
//    public void onTestSkipped(ITestResult result) {
//        Object retryAnalyzer = result.getMethod().getRetryAnalyzer(result);
//        String testName = result.getTestClass().getName() + "." + result.getMethod().getMethodName();
//        
//        if (retryAnalyzer != null) {
//            // Retry exhausted
//            System.out.println("⚠️ Retry exhausted. Marking as FAILED: " + testName);
//            result.setStatus(ITestResult.FAILURE);
//
//            Throwable cause = result.getThrowable();
//            if (cause != null) {
//                extentTest.get().fail(cause);
//                System.out.println("🔥 Failure Cause: " + cause.getMessage());
//                cause.printStackTrace();
//            } else {
//                extentTest.get().fail("❌ Test FAILED after all retry attempts. (No exception captured)");
//            }
//
//            captureScreenshot(result, "📸 Screenshot for retry-exhausted test");
//        }
//    }
////
////        if (retryAnalyzer != null) {
////            // Retry exhausted
////            System.out.println("⚠️ Retry exhausted. Marking as FAILED: " + testName);
////            result.setStatus(ITestResult.FAILURE);
////            extentTest.get().fail("❌ Test FAILED after all retry attempts.");
////            captureScreenshot(result, "📸 Screenshot for retry-exhausted test");
////        } else {
////            // Real skip (like dependsOnMethods failure)
////            System.out.println("⏭ Test SKIPPED (not retry): " + testName);
////            extentTest.get().skip("⚠️ Test skipped (possibly due to dependency).");
////            captureScreenshot(result, "📸 Screenshot for skipped test");
////        }
////    }
//
//    @Override
//    public void onFinish(ITestContext context) {
//        extent.flush();
//        System.out.println("🏁 Test Suite finished: " + context.getName());
//    }
//
//    private void captureScreenshot(ITestResult result, String logMessage) {
//        try {
//        	//WebDriver driver = ((BaseTest) result.getInstance()).driver;
//        	//WebDriver driver = ((BaseTest) result.getInstance()).getDriver();
//        	//driver = ((BaseTest) result.getInstance()).getDriver();
//            driver = (WebDriver) result.getTestClass()
//                    .getRealClass()
//                    .getField("driver")
//                    .get(result.getInstance());
//
//            if (driver == null) {
//                System.out.println("⚠️ Driver is null. Skipping screenshot for: " + result.getName());
//                return;
//            }
//
//            String screenshotName = result.getTestClass().getName() + "." + result.getMethod().getMethodName();
//            String filePath = getScreenshot(screenshotName);
//
//            if (filePath != null) {
//                extentTest.get().addScreenCaptureFromPath(filePath, screenshotName);
//                System.out.println(logMessage + " - " + filePath);
//            }
//        } catch (Exception e) {
//            System.out.println("⚠️ Could not capture screenshot: " + e.getMessage());
//        }
//    }
//}