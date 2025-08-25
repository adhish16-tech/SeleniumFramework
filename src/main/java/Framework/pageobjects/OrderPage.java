package Framework.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List; 

import Framework.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {

	public OrderPage(WebDriver driver) {
		super(driver);
		//this.driver=driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

//	WebDriver driver;
	@FindBy(css=".totalRow button")
	WebElement checkoutElement;
	
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> productNames;
	
	
	public Boolean VerifyOrdertDisplay(String productName) {
		Boolean match=	productNames.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
		
	}

}




