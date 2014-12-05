package FunctionalTests.Pages;

import UsedByAll.TestUser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by User on 12/1/2014.
 */
public class RegistrationPage extends BasePage {

    private static final By registration = By.linkText("Registration");
    private static final By email = By.id("fos_user_registration_form_email");
    private static final By password1 = By.id("fos_user_registration_form_plainPassword_first");
    private static final By password2 = By.id("fos_user_registration_form_plainPassword_second");
    private static final By inviteCode = By.id("fos_user_registration_form_code");
    private static final By nextStep = By.xpath(".//*[@id='step1']/div[5]/div/button/i");
    private static final By fullName = By.xpath("//*[@id='fos_user_registration_form_fullName']");
    private static final By phone = By.id("fos_user_registration_form_phone");
    private static final By birth = By.id("fos_user_registration_form_birthday");
    private static final By agreement = By.id("fos_user_registration_form_agreement");
    private static final By register = By.xpath("//div[@id='step2']/div[8]/div/button[2]");
   // private static final By emailSent = By.xpath("//div[contains(., 'An email has been sent to')]\\");
    private static final By emailSent = By.className("step-content");
    private static final By successConfirm = By.xpath("//div[@id='main-modal-window-confirmed-email']//p[contains(text(), 'Congrats')]");



    public RegistrationPage(WebDriver driver){
        super(driver);
        url = "http://xm-654.xmlm.t4web.com.ua/login/";
    }

    public void openRegistration(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        driver.findElement(registration).click();
        wait.until(ExpectedConditions.elementToBeClickable(email));
    }

    public void firstStep(TestUser user){

        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.findElement(email).sendKeys(user.getEmail());
        driver.findElement(password1).sendKeys(user.getPassword1());
        driver.findElement(password2).sendKeys(user.getPassword2());
        driver.findElement(inviteCode).sendKeys(user.getInviteCode());

        driver.findElement(nextStep).click();
        try {
            Thread.sleep(2000);}
        catch (InterruptedException e){System.out.println("Thread sleep exception");}
        driver.findElement(nextStep).click();

        wait.until(ExpectedConditions.elementToBeClickable(fullName));
        System.out.println("Переход на следующий шаг");
    }
    public void secondStep(TestUser user){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.findElement(fullName).sendKeys(user.getFullName());
        driver.findElement(phone).sendKeys(user.getPhone());
        driver.findElement(birth).sendKeys("1970-01-27");
        driver.findElement(agreement).click();

        //driver.findElement(register).click();
        //try {
           // Thread.sleep(2000);}
       // catch (InterruptedException e){System.out.println("Thread sleep exception");}
        driver.findElement(register).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(emailSent));
    }

    public String confirmActivation(){
        return (driver.findElement(successConfirm).getText());
    }
    public boolean isError(){
        return driver.findElement(emailSent).isDisplayed();
    }

}
