package FunctionalTests.Pages;

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
    private static final By closeActivationAlert = By.xpath("//div[@id='main-modal-window-confirmed-email']//button[text() = 'Close']");


    public RegistrationPage(WebDriver driver, WebDriverWait wait){
        super(driver, wait);
        url = url + "login/";
    }

    public void openRegistration(){
        //WebDriverWait wait = new WebDriverWait(driver,10);
        driver.findElement(registration).click();
        wait.until(ExpectedConditions.elementToBeClickable(email));
    }

    public void firstStep(String userEmail, String pass1, String pass2, String userInviteCode ){

        //WebDriverWait wait = new WebDriverWait(driver, 20);
        checkEmptyField(email);
        checkEmptyField(password1);
        checkEmptyField(password2);
        checkEmptyField(inviteCode);
        driver.findElement(email).sendKeys(userEmail);
        driver.findElement(password1).sendKeys(pass1);
        driver.findElement(password2).sendKeys(pass2);
        driver.findElement(inviteCode).sendKeys(userInviteCode);

        driver.findElement(nextStep).click();
        try {
            Thread.sleep(2000);}
        catch (InterruptedException e){System.out.println("Thread sleep exception");}
        driver.findElement(nextStep).click();

        wait.until(ExpectedConditions.elementToBeClickable(fullName));
        System.out.println("Переход на следующий шаг");
    }
    public void secondStep(String userFullName, String userPhone){
        //WebDriverWait wait = new WebDriverWait(driver, 10);
        checkEmptyField(fullName);
        checkEmptyField(phone);
        driver.findElement(fullName).sendKeys(userFullName);
        driver.findElement(phone).sendKeys(userPhone);
        driver.findElement(agreement).click();
        driver.findElement(birth).sendKeys("1970-01-27");

        //driver.findElement(register).click();
        //try {
           // Thread.sleep(2000);}
       // catch (InterruptedException e){System.out.println("Thread sleep exception");}
        driver.findElement(register).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(emailSent));
    }


    public String confirmActivation(String activationLink){
        //WebDriverWait wait = new WebDriverWait(driver, 25);
        driver.get(activationLink);
        wait.until(ExpectedConditions.presenceOfElementLocated(successConfirm));
        String successText = driver.findElement(successConfirm).getText();
        driver.findElement(closeActivationAlert).click();


        return successText;
    }
    public boolean isError(){
        return driver.findElement(emailSent).isDisplayed();
    }
    public boolean checkEmptyField(By locator){
        return(driver.findElement(locator).getText().isEmpty());

    }

}
