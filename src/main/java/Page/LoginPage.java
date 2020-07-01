package Page;

import Utils.Waits;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver driver;
    Waits waits;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        waits = new Waits(driver);
        PageFactory.initElements(driver, this);
    }

    public String navigateToLoginPage() {
        String loginPageURL = "http://demo.guru99.com/V4/index.php";

        return loginPageURL;
    }

    @FindBy(xpath = "//input[@name='uid']")
    private WebElement userName;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement userPassword;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//input[@type='reset']")
    private WebElement resetButton;

    @FindBy(xpath = "//label[text()='Password must not be blank']")
    private WebElement blankNameWarning;

    @FindBy(xpath = "//label[text()='Password must not be blank']")
    private WebElement blankPasswordWarning;

    public void getValidLogin(String uname, String psd) {
        waits.waitIsPresent(userName);
        userName.sendKeys(uname);
        userPassword.sendKeys(psd);
        submitButton.click();
        waits.waitIsInvisible(submitButton);
    }

    public Boolean isGetWarningBlankLogin() {
        boolean alertShown = true;
        String usernameLen = userName.getAttribute("value");
        String userPsdLen = userPassword.getAttribute("value");

        submitButton.click();
        if (usernameLen.length() == 0 || userPsdLen.length() == 0) {
            driver.switchTo().alert();
            alertShown = true;
        } else {
            alertShown = false;
        }
        return alertShown;
    }

    public Boolean isGetErrorMessageBlankFields() {
        boolean errorShown = true;
        userName.click();
        userName.sendKeys(Keys.TAB);

        String usernameLen = userName.getAttribute("value");
        String userPsdLen = userPassword.getAttribute("value");
        if (usernameLen.length() == 0 || userPsdLen.length() == 0) {
            blankNameWarning.isDisplayed();
            blankPasswordWarning.isDisplayed();
            errorShown = true;
        } else {
            errorShown = false;
        }
        return errorShown;
    }

    public Boolean isResetToRemoveText(String uname,String psd) {
        boolean isReset = true;

        userName.sendKeys(uname);
        userPassword.sendKeys(psd);

        resetButton.click();
        String usernameLen = userName.getAttribute("value");
        String userPsdLen = userPassword.getAttribute("value");
        if (usernameLen.length() == 0 || userPsdLen.length() == 0) {
            isReset = true;
        } else {
            isReset = false;
        }
        return isReset;
    }
}


