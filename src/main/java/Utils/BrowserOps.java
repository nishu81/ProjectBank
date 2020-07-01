package Utils;

import org.openqa.selenium.WebDriver;

public class BrowserOps {

    private WebDriver driver;

    public BrowserOps(WebDriver driver) {
        this.driver = driver;
    }

    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    public void clearCookies() {
        driver.manage().deleteAllCookies();
    }

    public void closeBrowser() {
        driver.quit();
    }
}
