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
//public class Listeners_old extends BaseTest implements ITestListener{
//	ExtentTest test;
//	
//	ExtentReports extent= ExtendReporterNG.getReportObject();
//	ThreadLocal<ExtentTest> extentTest= new ThreadLocal <ExtentTest>();
//
////	@Override
////	public boolean isEnabled() {
////		// TODO Auto-generated method stub
////		return ITestListener.super.isEnabled();
////	
////	}
//
//	@Override
//	public void onTestStart(ITestResult result) {
//		// TODO Auto-generated method stub
//		ITestListener.super.onTestStart(result);
//		String testName = result.getTestClass().getName() + "." + result.getMethod().getMethodName();
//		test = extent.createTest(testName);
//		extentTest.set(test);
//	}
//
//	@Override
//	public void onTestSuccess(ITestResult result) {
//		// TODO Auto-generated method stub
//		ITestListener.super.onTestSuccess(result);
//		test.log(Status.PASS, "Test Pass Successfully");
//	}
//
//
//	
//	@Override
////	public void onTestFailure(ITestResult result) {
////	    ITestListener.super.onTestFailure(result);
////	    extentTest.get().fail(result.getThrowable()); 
////	    if (driver == null) {
////	        System.out.println("Driver is null during failure handling, skipping alert and screenshot.");
////	        return;
////	    }
//
//	    // **Call your alert handler method in BaseTest**:
////	    try {
////	        // Access the current test class instance
////	        Object testInstance = result.getInstance();
////	        if (testInstance instanceof BaseTest) {
////	            ((BaseTest) testInstance).handleUnexpectedAlerts(driver);
////	        }
////	    } catch (Exception e) {
////	        System.out.println("Error handling alert: " + e.getMessage());
//////	    }
////
////	    String filePath = null;
////	    try {
////	        // Take the screenshot AFTER alert handling
////	        filePath = getScreenshot(result.getMethod().getMethodName(), driver);
////	        System.out.println("Screenshot saved at: " + filePath);
////	    } catch (IOException e1) {
////	        e1.printStackTrace();
////	    }
////
////	    // Add screenshot to report if path is valid
////	    if (filePath != null && !filePath.isEmpty() && new File(filePath).exists()) {
////	        extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
////	    } else {
////	        System.out.println("Screenshot path is null or file does not exist: " + filePath);
////	    }
////	}
//	public void onTestFailure(ITestResult result) {
//		// TODO Auto-generated method stub
//		ITestListener.super.onTestFailure(result);
//	   extentTest.get().fail(result.getThrowable()); 
//		try {
//		driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
//		}catch (IllegalArgumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NoSuchFieldException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String filePath = null;
//		try {
//			String screenshotName = result.getTestClass().getName() + "." + result.getMethod().getMethodName();
//			filePath = getScreenshot(screenshotName);
//		//filePath=getScreenshot(result.getMethod().getMethodName(), driver);
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//		extentTest.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
//		//screenshot this
//	}
//	
//	@Override
//	public void onTestSkipped(ITestResult result) {
//	    Object retryAnalyzer = result.getMethod().getRetryAnalyzer(result);
//
//	    if (retryAnalyzer != null) {
//	        System.out.println("⚠️ Retry exhausted. Marking test as FAILED: " + result.getName());
//	        result.setStatus(ITestResult.FAILURE);
//
//	        // Log failure in Extent Report
//	        extentTest.get().fail("❌ Test FAILED after all retry attempts.");
//
//	        try {
//	            // Get driver instance
//	            driver = (WebDriver) result.getTestClass()
//	                .getRealClass()
//	                .getField("driver")
//	                .get(result.getInstance());
//
//	            // Take screenshot
//	            String screenshotName = result.getTestClass().getName() + "." + result.getMethod().getMethodName();
//	            String filePath = getScreenshot(screenshotName);
//
//	            // Attach screenshot to ExtentReport
//	            extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
//	            System.out.println("📸 Screenshot attached for retry-exhausted test: " + filePath);
//
//	        } catch (Exception e) {
//	            System.out.println("⚠️ Could not capture screenshot for skipped test: " + e.getMessage());
//	        }
//	    } else {
//	        // Real skip (like dependsOnMethods failure)
//	        System.out.println("⏭ Test SKIPPED (not Retry): " + result.getName());
//	        extentTest.get().skip(result.getThrowable());
//	    }
//	}
//public String getScreenshot(String screenshotName) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
////	@Override
////	public void onTestSkipped(ITestResult result) {
////	    // Check if this test used RetryAnalyzer
////	    Object retryAnalyzer = result.getMethod().getRetryAnalyzer(result);
////	    if (retryAnalyzer != null) {
////	        System.out.println("⚠️ Retry exhausted. Marking test as FAILED: " + result.getName());
////	        
////	        // Mark status as FAIL
////	        result.setStatus(ITestResult.FAILURE);
////	        
////	        // Log to Extent Report
////	        extentTest.get().fail("Test FAILED after all retry attempts.");
////	    } else {
////	        // It’s a real skip (like dependsOnMethods failure), leave it as SKIPPED
////	        System.out.println("⏭ Test SKIPPED (not Retry): " + result.getName());
////	        extentTest.get().skip(result.getThrowable());
////	    }
////	}
//
////	@Override
////	public void onTestSkipped(ITestResult result) {
////		// TODO Auto-generated method stub
////	//	ITestListener.super.onTestSkipped(result);
////		
////	}
//
//	@Override
//	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
//		// TODO Auto-generated method stub
//		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
//	}
//
//	@Override
//	public void onTestFailedWithTimeout(ITestResult result) {
//		// TODO Auto-generated method stub
//		ITestListener.super.onTestFailedWithTimeout(result);
//	}
//
//	@Override
//	public void onStart(ITestContext context) {
//		// TODO Auto-generated method stub
//		ITestListener.super.onStart(context);
//	}
//
//	@Override
//	public void onFinish(ITestContext context) {
//		// TODO Auto-generated method stub
//		ITestListener.super.onFinish(context);
//		extent.flush();
//	}
//
//	@Override
//	public int hashCode() {
//		// TODO Auto-generated method stub
//		return super.hashCode();
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		// TODO Auto-generated method stub
//		return super.equals(obj);
//	}
//
//	@Override
//	protected Object clone() throws CloneNotSupportedException {
//		// TODO Auto-generated method stub
//		return super.clone();
//	}
//
//	@Override
//	public String toString() {
//		// TODO Auto-generated method stub
//		return super.toString();
//	}
//
//	@Override
//	protected void finalize() throws Throwable {
//		// TODO Auto-generated method stub
//		super.finalize();
//	}
//	
//	
//
//}
