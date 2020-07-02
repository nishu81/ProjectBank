package Utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BrowserOps {

    private WebDriver driver;
    JavascriptExecutor js;

    public BrowserOps(WebDriver driver) {

        this.driver = driver;
        js = (JavascriptExecutor) driver;
    }

    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    public void clearCookies() {
        driver.manage().deleteAllCookies();
    }

    public void jsClick(WebElement element) {
       js.executeScript("arguments[0].click();", element);
    }

    public void closeBrowser() {
        driver.quit();
    }
}
