
package Framework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;


import Framework.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{

	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		//initialisation
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//Page Factory
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;

	By productsBy=By.cssSelector(".mb-3");
	By addTocart=By.cssSelector(".card-body button:last-of-type");
	By toastMessage=By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
		
	}
		public WebElement getProductByName(String productName)
		{
			WebElement prod=products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
			return prod;
		}
		
		public void addProductTocart(String productName) {
			
			WebElement prod= getProductByName(productName);
			
			prod.findElement(addTocart).click();
			waitForElementToAppear(toastMessage);
		    waitForOverlayToDisappear();  // ✅ safer than waiting for spinner WebElement

			//waitForElementToDissapear(spinner);
			
		}
}
	

//package Framework.pageobjects;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//
//import java.util.List;
//
//
//import Framework.AbstractComponents.AbstractComponent;
//
//public class ProductCatalogue extends AbstractComponent{
//
//	WebDriver driver;
//	
//	public ProductCatalogue(WebDriver driver) {
//		//initialisation
//		super(driver);
//		this.driver=driver;
//		PageFactory.initElements(driver, this);
//		
//	}
//	
//	//Page Factory
//	@FindBy(css=".mb-3")
//	List<WebElement> products;
//	
//	@FindBy(css=".ng-animating")
//	WebElement spinner;
//
//	By productsBy=By.cssSelector(".mb-3");
//	By addTocart=By.cssSelector(".card-body button:last-of-type");
//	By toastMessage=By.cssSelector("#toast-container");
//
//	public List<WebElement> getProductList() {
//		waitForElementToAppear(productsBy);
//		return products;
//		
//	}
//		public WebElement getProductByName(String productName)
//		{
//			WebElement prod=products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
//			return prod;
//		}
//		
//		public void addProductTocart(String productName) {
//			
//			WebElement prod= getProductByName(productName);
//			
//			prod.findElement(addTocart).click();
//			waitForElementToAppear(toastMessage);
//		    waitForOverlayToDisappear();  // ✅ safer than waiting for spinner WebElement
//
//			//waitForElementToDissapear(spinner);
//			
//		}
//}
//	
//
