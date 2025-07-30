package Framework.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Framework.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Page Factory
    @FindBy(css = ".cartSection h3")
    private List<WebElement> cartProducts;

    @FindBy(css = ".totalRow button")
    private WebElement checkoutButton;

    /**
     * Verify if a product is displayed in the cart
     * @param productName - The name of the product to verify
     * @return true if the product is in the cart, false otherwise
     */
    public boolean VerifyProductDisplay(String productName) {
        System.out.println("🔎 Checking if product exists in the cart: " + productName);
        return cartProducts.stream()
                .anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
    }

    /**
     * Navigate to the checkout page
     * @return CheckoutPage object
     */
    public CheckoutPage goToCheckout() {
        try {
            // Wait for any toast/overlay animations to disappear
            waitForOverlayToDisappear();
        } catch (Exception e) {
            System.out.println("⚠️ No overlay or toast found before checkout button.");
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
            System.out.println("✅ Clicked on Checkout button");
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            System.out.println("⚠️ Checkout button intercepted. Retrying with JS click...");
            scrollIntoViewAndClick(checkoutButton);
        }
        return new CheckoutPage(driver);
    }
}

//package Framework.pageobjects;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//import java.util.List; 
//
//import Framework.AbstractComponents.AbstractComponent;
//
//public class CartPage extends AbstractComponent {
//
//	public CartPage(WebDriver driver) {
//		super(driver);
//		//this.driver=driver;
//		PageFactory.initElements(driver, this);
//		// TODO Auto-generated constructor stub
//	}
//
////	WebDriver driver;
//	@FindBy(css=".totalRow button")
//	WebElement checkoutElement;
//	
//	@FindBy(css=".cartSection h3")
//	private List<WebElement> cartProducts;
//	
//	
//	public Boolean VerifyProductDisplay(String productName) {
//		Boolean match=	cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
//		return match;
//		
//	}
//public CheckoutPage  goToCheckout() {
//
//	
//	checkoutElement.click();
//	return new CheckoutPage(driver);
//	
//}
//}
//
//
