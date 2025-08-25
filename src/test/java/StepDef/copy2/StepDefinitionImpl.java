package StepDef.copy2;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Framework.pageobjects.CartPage;
import Framework.pageobjects.CheckoutPage;
import Framework.pageobjects.ConfirmationPage;
import Framework.pageobjects.LandingPage;
import Framework.pageobjects.ProductCatalogue;
import TestComponents.BaseTest;

public class StepDefinitionImpl extends BaseTest {

    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;
    public ConfirmationPage confirmationPage;

    @Given("I landed on Ecommerce Page")
    public void i_landed_on_ecommerce_page() throws IOException {
        // Initialize browser for this thread
        landingPage = launchApplication();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_username_and_password(String username, String password) {
        productCatalogue = landingPage.loginApplication(username, password);
    }

    @When("^I add product (.+) to Cart$")
    public void i_add_product_to_cart(String productName) {
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductTocart(productName);
    }

    @When("^Checkout (.+) and submit the order$")
    public void checkout_submit_order(String productName) {
        CartPage cartPage = productCatalogue.goToCartPage();
        boolean match = cartPage.VerifyProductDisplay(productName);
        Assert.assertTrue(match, "❌ Product not found in cart: " + productName);

        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("India");
        confirmationPage = checkoutPage.submitOrder();
    }

    @Then("^\"([^\"]*)\" message is displayed on ConfirmationPage$")
    public void message_displayed_confirmation_page(String expectedMessage) {
        String actualMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(
            actualMessage.equalsIgnoreCase(expectedMessage),
            "❌ Expected: " + expectedMessage + ", but got: " + actualMessage
        );
    }
}

