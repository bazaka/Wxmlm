package FunctionalTests.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by User on 2/11/2015.
 */
public class LoginNewPage extends BasePage{
    private static final By username = By.id("username");
    private static final By password = By.id("password");
    private static final By logIn = By.id("xmlm_login_button_login");


    public LoginNewPage(WebDriver driver){
        super(driver);
        url = url + "login/";
    }

    public void goLogin(String email, String pass){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.titleIs("Login"));
        driver.findElement(username).sendKeys(email);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(logIn).click();
        wait.until(ExpectedConditions.titleIs("KairosNet"));

    }
    public String getTitle(){
        return (driver.getTitle());
    }
}
