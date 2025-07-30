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



public class BaseTest_new {



    public static WebDriver driver;

    public LandingPage landingPage;



    public WebDriver initialiazeDriver() throws IOException {

        if (driver == null) {

            System.out.println("🚀 Starting WebDriver initialization...");

            Properties prop = new Properties();

            String propPath = (System.getProperty("user.dir") + "//src//main//java//Framework//resources//GlobalData.Properties");

            System.out.println("📂 Loading properties from: " + propPath);



            try (FileInputStream fis = new FileInputStream(propPath)) {

                prop.load(fis);

            } catch (IOException e) {

                System.out.println("❌ Failed to load GlobalData.Properties: " + e.getMessage());

                throw e;

            }



            String BrowserName = System.getProperty("browser") != null 

                    ? System.getProperty("browser") 

                    : prop.getProperty("browser");

            System.out.println("🌐 Browser selected: " + BrowserName);



            try {

                if (BrowserName.toLowerCase().contains("chrome")) {

                    System.out.println("⚡ Setting up ChromeDriver...");

                    WebDriverManager.chromedriver().setup();

                    ChromeOptions options = new ChromeOptions();

                    if (BrowserName.toLowerCase().contains("headless")) {

                        options.addArguments("headless");

                        System.out.println("🕶 Running in headless mode.");

                    }

                    driver = new ChromeDriver(options);



                } else if (BrowserName.equalsIgnoreCase("firefox")) {

                    System.out.println("⚡ Setting up FirefoxDriver...");

                    WebDriverManager.firefoxdriver().setup();

                    driver = new FirefoxDriver();



                } else if (BrowserName.equalsIgnoreCase("edge")) {

                    System.out.println("⚡ Setting up EdgeDriver...");

                    WebDriverManager.edgedriver().setup();

                    driver = new EdgeDriver();



                } else {

                    throw new RuntimeException("❌ Browser not supported: " + BrowserName);

                }



                System.out.println("✅ WebDriver instance created: " + driver);

                driver.manage().window().maximize();

                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));



            } catch (Exception e) {

                System.out.println("🔥 WebDriver initialization FAILED: " + e.getMessage());

                e.printStackTrace();

                throw e;

            }

        } else {

            System.out.println("♻️ Reusing existing WebDriver instance: " + driver);

        }

        return driver;

    }



    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

        System.out.println("📖 Reading JSON data from: " + filePath);

        String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);



        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});

    }



    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {

        TakesScreenshot ts = (TakesScreenshot) driver;

        File source = ts.getScreenshotAs(OutputType.FILE);

        String destination = System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";

        FileUtils.copyFile(source, new File(destination));

        System.out.println("📸 Screenshot saved at: " + destination);

        return destination;

    }



    @BeforeMethod(alwaysRun = true)

    public LandingPage launchApplication() throws IOException {
        driver = initialiazeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goTo(); // ✅ ALWAYS call goTo()
        return landingPage;
    }

   // @AfterMethod(alwaysRun = true)

    @AfterMethod(alwaysRun=true)
    public void TearDown() {
        if (driver != null) {
            driver.quit(); // Kill the browser after each test
            driver = null; // Force new driver for next test
        }
    }



    public void handleUnexpectedAlerts(WebDriver driver) {

        try {

            Alert alert = driver.switchTo().alert();

            System.out.println("⚠️ Alert detected: " + alert.getText());

            alert.accept();

            System.out.println("✅ Alert accepted.");

        } catch (NoAlertPresentException e) {

            System.out.println("✅ No unexpected alerts.");

        }

    }

}