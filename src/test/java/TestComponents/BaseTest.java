package TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Framework.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    public WebDriver driver;
    public LandingPage landingPage;

    /**
     * Initialize WebDriver based on browser type from GlobalData.properties
     */
    public WebDriver initialiazeDriver() throws IOException {
    	  System.out.println("🚀 initializeDriver() CALLED");
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//Framework//resources//GlobalData.Properties");
        prop.load(fis);

        String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");
        System.out.println("🖥 Browser selected: " + browserName);

        if (browserName.contains("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            if (browserName.contains("headless")) {
                options.addArguments("headless");
            }
            driver = new ChromeDriver(options);

        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();

        } else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    /**
     * Launch the application and return LandingPage instance
     */
   // @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
    	  System.out.println("🌐 launchApplication() CALLED");
      //  driver = initialiazeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }
    
    @BeforeMethod(alwaysRun = true)
    public void setUp() throws IOException {
    	System.out.println("🔧 @BeforeMethod: setUp() running");
        driver = initialiazeDriver();  // ONLY initialize driver
    }

    /**
     * Close browser after every test method
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            System.out.println("🛑 Closing browser after test...");
            driver.quit();
        }
    }

    /**
     * Convert JSON data to HashMap for DataProvider
     */
    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
    }

    /**
     * Capture screenshot and return file path
     */
    public String getScreenshot(String testCaseName) throws IOException {
        if (driver == null) {
            System.out.println("⚠️ Cannot take screenshot: Driver is null.");
            return null;
        }
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String filePath = System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
        FileUtils.copyFile(source, new File(filePath));
        return filePath;
    }

    /**
     * Handle unexpected alerts gracefully
     */
    public void handleUnexpectedAlerts(WebDriver driver) {
        try {
            Alert alert = driver.switchTo().alert();
            System.out.println("⚠️ Alert detected: " + alert.getText());
            alert.accept();
        } catch (NoAlertPresentException e) {
            // No alert present
        }
    }
}

//package TestComponents;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.time.Duration;
//import java.util.HashMap;
//import java.util.Properties;
//import java.util.List;
//import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.Alert;
//import org.openqa.selenium.NoAlertPresentException;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.chromium.ChromiumOptions;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import Framework.pageobjects.LandingPage;
//import io.github.bonigarcia.wdm.WebDriverManager;
//
//import org.testng.annotations.BeforeSuite;
//import org.testng.annotations.AfterSuite;
//
//public class BaseTest {
////	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
////
////	public WebDriver getDriver() {
////	    return driver.get();
////	}
//	
////	private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
////	public WebDriver getDriver() {
////		// TODO Auto-generated method stub
////		return  driver.get();
////	 }
//public static  WebDriver driver;
//public LandingPage landingPage;
// public WebDriver initialiazeDriver() throws IOException {
//	 
//	 //properties class
//	
//	 Properties prop=new Properties();
//	 
//	 FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Framework//resources//GlobalData.Properties");
//	 prop.load(fis);
//	String BrowserName= System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");
//	 
//	// String BrowserName=prop.getProperty("browser");
//	 
//	 if(BrowserName.contains("Chrome")) {
//		 
//	 
//	 WebDriverManager.chromedriver().setup();
//	 ChromeOptions options =new ChromeOptions();
//	 if(BrowserName.contains("headless")) {
//	 options.addArguments("headless");
//	 }
//	 
//		 driver=new ChromeDriver(options);
//	 }
//	 else if(BrowserName.equalsIgnoreCase("Firefox")){
//		 //invoke Firefox driver
//		 WebDriverManager.firefoxdriver().setup();
//
//		 driver=new FirefoxDriver();
//		 //driver = new FirefoxDriver();
//	 }
//		 else if(BrowserName.equalsIgnoreCase("Edge")){
//			    WebDriverManager.edgedriver().setup();
//
//				 driver=new EdgeDriver();
//			   // driver = new EdgeDriver();
//			}
//	 
//	 
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
//		driver.manage().window().maximize();
//		System.out.println("Driver is initializes():"+driver);
//		return driver;
// }
// 
//	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
//		//read json to string
//		String jsonContent=	FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
//		
//		//string to hashmapjackson databind
//		ObjectMapper mapper=new ObjectMapper();
//				List<HashMap<String,String>>data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){
//					
//				});
//			return data; 
//	}
//	
//	public String getScreenshot(String testCaseName) throws IOException{
//		TakesScreenshot ts= (TakesScreenshot)driver;
//		File source =ts.getScreenshotAs(OutputType.FILE);
//		File file =new File(System.getProperty("user.dir")+"//reports//"+ testCaseName +".png");
//		FileUtils.copyFile(source, file);
//		return System.getProperty(("user.dir")+"//reports//"+ testCaseName +".png");
//	}
//	@BeforeSuite(alwaysRun = true)
//	public void setupSuite() throws IOException {
//	    driver = initialiazeDriver();
//	}
// @BeforeMethod(alwaysRun=true)
// public LandingPage launchApplication() throws IOException {
// if (driver == null) { // <-- Add this check
//	 //initialiazeDriver();
//		   driver = initialiazeDriver();
//    System.out.println("Driver initialized in launchApplication: " + driver);
//		} else {
//		    System.out.println("Reusing existing driver: " + driver);
//		}
//		landingPage = new LandingPage(driver);
//		landingPage.goTo();
//		return landingPage;
////	 driver= initialiazeDriver();
////	    System.out.println("Driver initialized in launchApplication: " + driver);
////	 landingPage=new LandingPage(driver);
////	 landingPage.goTo();
////	 return  landingPage;
//	 
// }
// 
// @AfterMethod(alwaysRun=true)
// public void TearDown() {
//	 
//	 if (driver != null) {
//		  //  driver.quit();
//		//} else {
//		    System.out.println(" skipping driver.quit in  TearDown.");
//		}else {
//			System.out.println("Driver was null");
//		}
////	 if (driver != null) {
////	        driver.quit();  // completely closes the browser and ends WebDriver session
////	        driver = null;
//	 //driver.close();
// }
// 
// 
// public void handleUnexpectedAlerts(WebDriver driver) {
//	    try {
//	        Alert alert = driver.switchTo().alert();
//	        // Optional: log alert message
//	        System.out.println("Alert detected: " + alert.getText());
//	        alert.accept(); // or alert.dismiss();
//	        System.out.println("Alert accepted/dismissed");
//	    } catch (NoAlertPresentException e) {
//	        // No alert present, do nothing
//	    }
//	    
//	    
//	}
//
// 
//
////public void handleUnexpectedAlerts(WebDriver driver2) {
//	// TODO Auto-generated method stub
// @AfterSuite(alwaysRun = true)
// public void cleanUpSuite() {
//     if (driver != null) {
//         System.out.println("Closing driver in @AfterSuite...");
//         driver.quit();
//         driver = null;
//     }
// }
//}
//
