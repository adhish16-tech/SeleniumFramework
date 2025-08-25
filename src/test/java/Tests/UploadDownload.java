package Tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.decorators.WebDriverDecorator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class UploadDownload {

	private static final Duration TIMEOUT = Duration.ofSeconds(10);

	public static void main(String[] args) {
		String fruitName="Apple";	
		// TODO Auto-generated method stubriver
		
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		driver.get("https://rahulshettyacademy.com/upload-download-test/index.html");
		
		//download the file
		driver.findElement(By.id("downloadButton")).click();
		WebElement upload=driver.findElement(By.cssSelector("input[type='file']"));
		upload.sendKeys("/users/adhishgurjar/downloads/download.xlsx");
		
		
		//wait for success message to appear and dissapear
		
		 WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
         wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".Toastify__toast-body div:nth-child(2")));
       String ToastMessage=driver.findElement(By.cssSelector(".Toastify__toast-body div:nth-child(2")).getText();
       
      System.out.println(ToastMessage);
       Assert.assertEquals("Updated Excel Data Successfully.",ToastMessage);
       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".Toastify__toast-body div:nth-child(2")));
       
         //get price
       String priceColumn=driver.findElement(By.xpath("//div[text()='Price']")).getAttribute("data-column-id");
      String actualPrice= driver.findElement(By.xpath("//div[text()='"+fruitName+"']/parent::div/parent::div/div[@id='cell-"+priceColumn+"-undefined']")).getText();
      System.out.println(priceColumn);
      System.out.println(actualPrice);
      Assert.assertEquals("345",actualPrice);

	}

}
