package FunctionalTests.Pages;

import UsedByAll.TestUser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by User on 12/5/2014.
 */
public class RecoveryPage extends BasePage {

    public static final By forgotPassword = By.xpath("//body/div/div/div[2]/a[1]");
    public static final By email = By.name("username");
    public static final By resetPassword = By.xpath("//button[text()= 'Reset password']");
 //  public static final By resetPassword = By.linkText("Reset password");
    public static final By emailSent = By.xpath("//body//div/h3[contains(text(), 'Check your e-mail!')]");
    public static final By newPassword1 = By.xpath("//div[@class='content']/div[1]//input[@id='password']");
    public static final By newPassword2 = By.xpath("//div[@class='content']/div[2]//input[@id='password']");
    public static final By changePassword = By.xpath("//div[@class='content']/input[@value='Change password']");

    public RecoveryPage(WebDriver driver){
        super(driver);
        url = "http://staging.xmlm.t4web.com.ua/login/";
    }
    public void sendRecoveryLink(TestUser testUser){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.findElement(forgotPassword).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(email));

        driver.findElement(email).sendKeys(testUser.getEmail());
        driver.findElement(resetPassword).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(emailSent));

    }
    public String passwordSendMessage(){

        return (driver.findElement(emailSent).getText());
    }
    public String enterNewPassword(TestUser testUser, String activationLink){
        WebDriverWait wait = new WebDriverWait(driver, 25);
        driver.get(activationLink);
        wait.until(ExpectedConditions.presenceOfElementLocated(newPassword1));
        driver.findElement(newPassword1).sendKeys(testUser.getNewPassword1());
        driver.findElement(newPassword2).sendKeys(testUser.getNewPassword2());
        driver.findElement(changePassword).click();
        wait.until(ExpectedConditions.titleIs("KairosNet"));
        return(driver.getTitle());


    }

}
