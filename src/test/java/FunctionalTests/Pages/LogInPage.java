package FunctionalTests.Pages;

import UsedByAll.TestUser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by User on 12/1/2014.
 */
public class LogInPage extends BasePage {

    private static final By username = By.id("username");
    private static final By password = By.id("password");
    private static final By logIn = By.id("xmlm_login_button_login");


    public LogInPage(WebDriver driver){
        super(driver);
        url = url + "login/";
    }

    public void goLogin(TestUser user){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.titleIs("Login"));
        driver.findElement(username).sendKeys(user.getEmail());
        driver.findElement(password).sendKeys(user.getPassword1());
        driver.findElement(logIn).click();
        wait.until(ExpectedConditions.titleIs("KairosNet"));

    }
    public String getTitle(){
        return (driver.getTitle());
    }
}
