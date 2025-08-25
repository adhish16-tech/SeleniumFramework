package Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Framework.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
String ProductName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		LandingPage landingPage= new LandingPage(driver);
		
		
		driver.findElement(By.id("userEmail")).sendKeys("adhish16singh@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Adhish@123");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		
		
		WebElement prod=products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
	prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	Thread.sleep(5000);
	driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	//driver.close();
	List <WebElement>cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
	Boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(ProductName));
	Assert.assertTrue(match);
	//Thread.sleep(5000);
	
//	JavascriptExecutor js = (JavascriptExecutor) driver;
//	js.executeScript("arguments[0].click();", driver.findElement(By.cssSelector(".totalRow button")));
//
	
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".totalRow button")));
	//driver.findElement(By.cssSelector("li[class='totalRow'] button[type='button']"));
	driver.findElement(By.xpath("//button[text()='Checkout']")).click();

	
	
	Actions a= new Actions(driver);
	a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"india").build().perform();
	
	
	
	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results")));
	
	driver.findElement(By.xpath("(//button [contains (@class, 'ta-item' )]) [2]")).click(); 
	driver.findElement(By.xpath("//a[contains(@class, 'action__submit') and contains(text(),'Place Order')]")).click();

	//driver.findElement(By.cssSelector(".action_submit")).click();
	
	String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
			Assert.assertTrue (confirmMessage.equalsIgnoreCase ("THANKYOU FOR THE ORDER."));
			driver.close();
	
	
	}

}
