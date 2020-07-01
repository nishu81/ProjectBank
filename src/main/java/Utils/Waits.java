package Utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waits {

    private WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    public Waits(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
        js = (JavascriptExecutor) driver;
    }

    public Boolean waitIsPresent(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public Boolean waitIsClickable(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public Boolean waitIsInvisible(WebElement element) {
        try {
            wait.until(ExpectedConditions.invisibilityOf(element));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public Boolean waitIsDisplayed(WebElement element) {
        try {
            element.isDisplayed();
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void waitForPageToLoad() {
        for (int i = 0; i < 11; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            //To check page ready state.
            if (js.executeScript("return document.readyState").toString().equals("complete")) {
                break;
            }
        }
    }
}
