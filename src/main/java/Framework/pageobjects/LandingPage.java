package Framework.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Framework.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		
		super(driver);
		//initialisation
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//Page Factory
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordElement;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	
	
	public ProductCatalogue loginApplication(String email,String password) {
		userEmail.clear();
		userEmail.sendKeys(email);
		passwordElement.clear();
		passwordElement.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue= new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}

//package Framework.pageobjects;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//
//import Framework.AbstractComponents.AbstractComponent;
//
//public class LandingPage extends AbstractComponent {
//
//	WebDriver driver;
//	
//	public LandingPage(WebDriver driver) {
//		
//		super(driver);
//		//initialisation
//		this.driver=driver;
//		PageFactory.initElements(driver, this);
//		
//	}
//	
//	//Page Factory
//	@FindBy(id="userEmail")
//	WebElement userEmail;
//	
//	@FindBy(id="userPassword")
//	WebElement passwordElement;
//	
//	@FindBy(id="login")
//	WebElement submit;
//	
//	@FindBy(css="[class*='flyInOut']")
//	WebElement errorMessage;
//	
//	
//	
//	public ProductCatalogue loginApplication(String email,String password) {
//		userEmail.clear();
//		userEmail.sendKeys(email);
//		passwordElement.clear();
//		passwordElement.sendKeys(password);
//		submit.click();
//		ProductCatalogue productCatalogue= new ProductCatalogue(driver);
//		return productCatalogue;
//	}
//	
//	public String getErrorMessage() {
//		waitForWebElementToAppear(errorMessage);
//		return errorMessage.getText();
//	}
//	
//	public void goTo() {
//		driver.get("https://rahulshettyacademy.com/client");
//	}
//}
