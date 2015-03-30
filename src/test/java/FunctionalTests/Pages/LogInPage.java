package FunctionalTests.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by User on 2/11/2015.
 */
public class LogInPage extends BasePage{
    public static final By username = By.id("username");
    public static final By password = By.id("password");
    public static final By logIn = By.id("xmlm_login_button_login");


    public LogInPage(WebDriver driver, WebDriverWait wait){
        super(driver, wait);
        url = url + "login/";
    }

    public void goLogin(String email, String pass){

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
