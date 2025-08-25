package Tests;

import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import Framework.pageobjects.CartPage;
import Framework.pageobjects.ProductCatalogue;
import TestComponents.BaseTest;
import org.testng.Assert;
import TestComponents.Retry;
//import junit.framework.Assert;


public class ErrorValidation extends BaseTest{

	//public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
	//@Test(groups= {"Errorhandling"}, retryAnalyzer=Retry.class)
	@Test(groups={"Errorhandling"}, retryAnalyzer =Retry.class)
	public void submitOrder() throws IOException {
//String ProductName = "ZARA COAT 3";
	
		
		landingPage = launchApplication();
		landingPage.loginApplication("adhish16singh@gmail.com", "Asdhish@123");	
		//landingPage.loginApplication(ProductName, ProductName)
		Assert.assertEquals("Incorrect emasil or password.",landingPage.getErrorMessage());
	    
	}
@Test
public void ProductErrorValidation() throws IOException {
	landingPage = launchApplication();
	String productName = "ZARA COAT 3" ;
	ProductCatalogue productCatalogue = landingPage.loginApplication ("rahulshetty@gmail.com","Iamking@000");
	List<WebElement> products = productCatalogue.getProductList();
	productCatalogue.addProductTocart(productName);
	CartPage cartPage = productCatalogue.goToCartPage () ;
	Boolean match = cartPage.VerifyProductDisplay ("ZARA COAT 3");
	Assert.assertFalse(match);

}
}
  	