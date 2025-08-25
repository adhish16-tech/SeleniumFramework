package Framework.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import Framework.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Page Factory
    @FindBy(css = ".action__submit")
    private WebElement submit;

    @FindBy(css = "[placeholder='Select Country']")
    private WebElement countryInput;

    @FindBy(css = ".ta-results button:nth-of-type(1)")
    private WebElement selectCountryResult;

    public void selectCountry(String countryName) {
        waitForWebElementToAppear(countryInput);
        countryInput.sendKeys(countryName);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(selectCountryResult)).click();
        System.out.println("🌏 Selecting country: " + countryName);
    }

    public ConfirmationPage submitOrder() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitForOverlayToDisappear(); // ✅ Ensure any spinners are gone

        try {
            wait.until(ExpectedConditions.elementToBeClickable(submit)).click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            System.out.println("⚠️ Element intercepted. Retrying click with JS.");
            scrollIntoViewAndClick(submit);
        }

        System.out.println("✅ Submitting order...");
        return new ConfirmationPage(driver);
    }
}
