package Page;

import Configuration.Customer;
import Utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class NewCustomerCreatePage {

    private static WebDriver driver;
    Waits waits;

    public NewCustomerCreatePage(WebDriver driver) {
        this.driver = driver;
        waits = new Waits(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@name='name']")
    private WebElement customerName;

    @FindBy(xpath = "//input[@name='name']/..//label[text()='Customer name must not be blank']")
    private WebElement getCustomerNameBlankWarning;

    @FindBy(xpath = "//input[@value='m']")
    private WebElement selectCustomerGenderM;

    @FindBy(xpath = " //input[@name='dob']")
    private WebElement selectCustomerDOB;

    @FindBy(xpath = "//textarea[@name='addr']")
    private WebElement customerAddress;

    @FindBy(xpath = "//textarea[@name='addr']/..//label[text()='Address Field must not be blank']")
    private WebElement getCustomerAddressBlankWarning;

    @FindBy(xpath = "//input[@name='city']")
    private WebElement customerCity;

    @FindBy(xpath = "//input[@name='city']/following::label[text()='City Field must not be blank']")
    private WebElement getCustomerCityBlankWarning;

    @FindBy(xpath = "//input[@name='state']")
    private WebElement customerState;

    @FindBy(xpath = "//input[@name='state']/following::label[text()='State must not be blank']")
    private WebElement getCustomerStateBlankWarning;

    @FindBy(xpath = "//input[@name='pinno']")
    private WebElement customerPin;

    @FindBy(xpath = "//input[@name='pinno']/following::label[text()='PIN Code must not be blank']")
    private WebElement getCustomerPinBlankWarning;

    @FindBy(xpath = "//input[@name='telephoneno']")
    private WebElement customerMobile;

    @FindBy(xpath = "//input[@name='telephoneno']/following::label[text()='Mobile no must not be blank']")
    private WebElement getCustomerMobileBlankWarning;

    @FindBy(xpath = "//input[@name='emailid']")
    private WebElement customerEmail;

    @FindBy(xpath = "//input[@name='emailid']/following::label[text()='Email-ID must not be blank']")
    private WebElement getCustomerEmailBlankWarning;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement customerPassword;

    @FindBy(xpath = "//input[@name='password']/following::label[text()='Password must not be blank']")
    private WebElement getCustomerPasswordBlankWarning;

    @FindBy(xpath = "//input[@name='sub']")
    private WebElement submitButton;

    @FindBy(xpath = "//input[@name='res']")
    private WebElement resetButton;

    //Will generate warning for fields left blank
    public Boolean isGetWarningForEmptyFields() {
        boolean getWarning = true;
        HashMap<WebElement, WebElement> fieldWarning = new HashMap<>();

        fieldWarning.put(customerName, getCustomerNameBlankWarning);
        fieldWarning.put(customerAddress, getCustomerAddressBlankWarning);
        fieldWarning.put(customerCity, getCustomerCityBlankWarning);
        fieldWarning.put(customerPin, getCustomerPinBlankWarning);
        fieldWarning.put(customerEmail, getCustomerEmailBlankWarning);
        fieldWarning.put(customerPassword, getCustomerPasswordBlankWarning);

        Set<WebElement> keySets = fieldWarning.keySet();
        for (WebElement ele : keySets) {
            ele.click();
            ele.sendKeys(Keys.TAB);
            if (waits.waitIsPresent(fieldWarning.get(ele))) {
                getWarning = true;
            } else {
                getWarning = false;
            }
        }

        return getWarning;
    }

    //If Customer created then the Submit button will not be visible
    public Boolean isSuccessfulSubmit() {
        return driver.findElements(By.xpath("//input[@name='sub']")).size() == 0;
    }

    public NewCustomerCreatePage createACustomer(Customer configCustomer) {
        customerName.sendKeys(configCustomer.getCustomerName());
        selectCustomerGenderM.click();
        selectCustomerDOB.sendKeys(configCustomer.getDateOfBirth());
        customerAddress.sendKeys(configCustomer.getCustomerAddress());
        customerCity.sendKeys(configCustomer.getCustomerCity());
        customerState.sendKeys(configCustomer.getCustomerState());
        customerPin.sendKeys(configCustomer.getCustomerPin());
        customerMobile.sendKeys(configCustomer.getCustomerMobile());
        customerEmail.sendKeys(configCustomer.getCustomerEmail());
        customerPassword.sendKeys(configCustomer.getCustomerPassword());
        submitButton.click();
        waits.waitIsInvisible(submitButton);
        return this;
    }

    //Will erase all data from fields
    public Boolean isFieldBlankReset(ArrayList<String> value) {
        for(String str: value){
            customerName.sendKeys(str);
            customerPin.sendKeys(str);
            customerEmail.sendKeys(str);
            customerCity.sendKeys(str);
        }
        boolean fieldEmpty = true;
        resetButton.click();
        if (customerName.getAttribute("value").length() == 0 && customerAddress.getAttribute("value").length() == 0
                && customerPin.getAttribute("value").length() == 0 && customerPin.getAttribute("value").length() == 0
                && customerEmail.getAttribute("value").length() == 0)
        {
            fieldEmpty = true;
        }
       else{
           fieldEmpty = false;
        }
       return fieldEmpty;
    }

}

