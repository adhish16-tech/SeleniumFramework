package Framework.AbstractComponents;

import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Framework.pageobjects.CartPage;
import Framework.pageobjects.OrderPage;

public class AbstractComponent {

    protected WebDriver driver;
    private static final Duration TIMEOUT = Duration.ofSeconds(10);

    public AbstractComponent(WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver instance is null in AbstractComponent!");
        }
        this.driver = driver;
    }

    @FindBy(css = "[routerlink*='cart']")
    WebElement cartHeader;

    @FindBy(css = "[routerlink*='myorders']")
    WebElement orderHeader;

    @FindBy(css = ".ng-animating")
    WebElement spinner;

    public void waitForElementToAppear(By findBy) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForWebElementToAppear(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToDisappear(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void waitForElementToDisappear(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitForOverlayToDisappear() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ngx-spinner-overlay")));
        } catch (Exception e) {
            System.out.println("No overlay found or already disappeared.");
        }
    }

    public CartPage goToCartPage() {
        try {
            waitForOverlayToDisappear(); // ✅ Wait for any spinner/overlay
        } catch (Exception e) {
            System.out.println("⚠️ Overlay already gone or not found.");
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(cartHeader)).click();
            System.out.println("🛒 Navigated to Cart Page");
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            System.out.println("⚠️ Element click intercepted. Retrying with JS click.");
            scrollIntoViewAndClick1(cartHeader);
        }

        return new CartPage(driver);
    }

    private void scrollIntoViewAndClick1(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            System.out.println("❌ JS click failed: " + e.getMessage());
        }
    }
    public OrderPage goToOrdersPage() {
        try {
            waitForOverlayToDisappear();
        } catch (Exception e) {
            System.out.println("Overlay already gone or not found.");
        }
        System.out.println("Navigating to Orders Page...");
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(orderHeader));
        orderHeader.click();
        return new OrderPage(driver);
    }
    
    public void scrollIntoViewAndClick(WebElement element) {
        try {
            ((org.openqa.selenium.JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView(true);", element);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();

            System.out.println("✅ Clicked element after scrolling into view.");
        } catch (Exception ex) {
            throw new RuntimeException("🔥 Failed to click element after scroll: " + ex.getMessage());
        }
    }
}


