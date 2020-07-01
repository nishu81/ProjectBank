package Page;

import Utils.Waits;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class HomePage {

    private WebDriver driver;
    Waits waits;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        waits = new Waits(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//td[text()='Manger Id : mngr265711']")
    private WebElement managerId;

    @FindBy(xpath="//a[text()='New Customer']")
    private WebElement newCustLink;

    public String navigateToHomePage() {
        String homepageURL = "http://demo.guru99.com/V4/manager/Managerhomepage.php";

        return homepageURL;
    }

    public void getNavigateToCreateAccountPage(){
        driver.findElement(By.xpath("//a[text()='New Account']")).click();
    }

    public void getNavigateToDeleteAccountPage(){
        driver.findElement(By.xpath("//a[text()='Delete Account']")).click();
    }

    public ArrayList<String> getHomePageSideMenuOptions() {
        ArrayList<String> menus = new ArrayList<>();
        List<WebElement> sideMenuOptions = driver.findElements(By.xpath("//ul[@class='menusubnav']/li/a"));
        for (WebElement ele : sideMenuOptions) {
            menus.add(ele.getText());
        }
        Assert.assertTrue(menus.size() == 15);
        return menus;
    }

    public Boolean isManagerLoggedIn(String managerLogged) {
        String manager = managerId.getText();
        String[] managers = manager.split(": ");
        if ((managers[1].equals(managerLogged))) {
            return true;
        } else {
            return false;
        }
    }

    public void clickToGetNewCustomerOption(){

        newCustLink.click();
    }
}
