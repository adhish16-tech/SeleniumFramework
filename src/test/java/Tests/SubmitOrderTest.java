package Tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import Framework.pageobjects.CartPage;
import Framework.pageobjects.CheckoutPage;
import Framework.pageobjects.ConfirmationPage;
import Framework.pageobjects.OrderPage;
import Framework.pageobjects.ProductCatalogue;
import TestComponents.BaseTest;

import org.testng.Assert;

public class SubmitOrderTest extends BaseTest {
    String submittedProductName;

    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String, String> input) throws IOException {
    	 System.out.println("✅ Test STARTED with data: " + input);
        // Ensure driver is initialized for this test
        landingPage = launchApplication();

        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
        List<WebElement> products = productCatalogue.getProductList();

        productCatalogue.addProductTocart(input.get("Product"));

        CartPage cartPage = productCatalogue.goToCartPage();
        Boolean match = cartPage.VerifyProductDisplay(input.get("Product"));
        Assert.assertTrue(match);

        submittedProductName = input.get("Product");

        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("india");

        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        String confirmMessage = confirmationPage.getConfirmationMessage();

        System.out.println("Confirmation message from page: '" + confirmMessage + "'");
        AssertJUnit.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void OrderHistoryTest() throws IOException {
        // Ensure driver is initialized for this test
        landingPage = launchApplication();

        ProductCatalogue productCatalogue = landingPage.loginApplication("adhish16singh@gmail.com", "Adhish@123");
        OrderPage ordersPage = productCatalogue.goToOrdersPage();
        Assert.assertTrue(ordersPage.VerifyOrdertDisplay(submittedProductName));
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = getJsonDataToMap(
                System.getProperty("user.dir") + "//src//test//java//Data//PurchaseOrder.json");
        return new Object[][] { { data.get(0) }, { data.get(1) } };
    }
}

//package Tests;
//
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//import org.testng.AssertJUnit;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//
//import org.openqa.selenium.WebElement;
//import Framework.pageobjects.CartPage;
//import Framework.pageobjects.CheckoutPage;
//import Framework.pageobjects.ConfirmationPage;
//import Framework.pageobjects.OrderPage;
//import Framework.pageobjects.ProductCatalogue;
//import TestComponents.BaseTest;
//
//import org.testng.Assert;
//
////import junit.framework.Assert;
//
//
//public class SubmitOrderTest extends BaseTest{
//	String submittedProductName;
//	
//	//public static void main(String[] args) throws InterruptedException {
//		// TODO Auto-generated method stub
//	@Test(dataProvider="getData",groups={"Purchase"})
//	public void submitOrder(HashMap<String,String>input) throws IOException {
//
//		
//	
//		ProductCatalogue productCatalogue=landingPage.loginApplication(input.get("email"),input.get("password"));	
//		List<WebElement> products=productCatalogue.getProductList();
//		
//		
//		//productCatalogue.addProductTocart(ProductName);
//		productCatalogue.addProductTocart(input.get("Product"));
//
//		CartPage cartPage=productCatalogue.goToCartPage();
//     Boolean match=cartPage.VerifyProductDisplay(input.get("Product"));
//
//     Assert.assertTrue(match);	
//     submittedProductName = input.get("Product");
//     CheckoutPage checkoutPage = cartPage.goToCheckout();
//     checkoutPage. selectCountry("india");
//     ConfirmationPage confirmationPage = checkoutPage. submitOrder ();
//     String confirmMessage = confirmationPage.getConfirmationMessage ();
//     
//     System.out.println("Confirmation message from page: '" + confirmMessage + "'");
//      AssertJUnit.assertTrue (confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
////	
//	
//	}
//	//@Test(dependsOnMethods={"submitOrder"})
//	@Test
//	public void OrderHistoryTest() {
//		
//		//Zara coat 3
//		ProductCatalogue productCatalogue=landingPage.loginApplication("adhish16singh@gmail.com", "Adhish@123");
//		OrderPage ordersPage= productCatalogue.goToOrdersPage();
//		Assert.assertTrue(ordersPage.VerifyOrdertDisplay(submittedProductName));
//	}
//	
//	
//	
//	@DataProvider
//	public Object[][] getData() throws IOException {
//		
////		HashMap<String,String> map=new HashMap<String,String>();
////		map.put("email", "adhish16singh@gmail.com");
//
//		//		map.put("password", "Adhish@123");
////		map.put("Product", "ZARA COAT 3");
////		
////		HashMap<String,String> map1=new HashMap<String,String>();
////		map1.put("email", "shetty@gmail.com");
////		map1.put("password", "Iamking@000");
////		map1.put("Product", "ADDIDAS ORIGINAL");
////		
//		List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//Data//PurchaseOrder.json");
//		return new Object [][] {{data.get(0)},{data.get(1)}};
//	}
//}
