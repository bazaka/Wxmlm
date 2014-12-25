package FunctionalTests.Pages;

import UsedByAll.TestUser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by User on 12/22/2014.
 */
public class ProfilePage extends BasePage {
    private static final By profilePage = By.cssSelector("img[alt=\"Avatar\"]");
    private static final By fullName = By.id("fos_user_profile_form_fullName");

   // private static final By gender = By.id("fos_user_profile_form_gender");
    private static final By gender = By.xpath("//select[@id='fos_user_profile_form_gender']/option");

    private static final By email = By.xpath("//div[@id='main-email']/div/div/input");
    private static final By phone = By.id("fos_user_profile_form_phone");
    private static final By current = By.xpath("//div[@class='user-wallets']/div[1]/div/div/h1");
    private static final By bonuses = By.xpath("//div[@class='user-wallets']/div[2]/div/div/h1");
    private static final By salary = By.xpath("//div[@class='user-wallets']/div[3]/div/div/h1");
    private static final By inviteCode = By.xpath("//div[@class='row']//div[@class='invite-in-profile']/table/tbody/tr/td[2]");
    private static final By career = By.xpath("//body//div[@class='row']/div[3]/table/tbody/tr[3]/td[2]/abbr");
    private static final By status = By.xpath("//body//div[@class='row']/div[4]/table/tbody/tr[1]/td[2]/abbr");
    private static final By identification = By.xpath("//body//div[@class='row']/div[4]/table/tbody/tr[2]/td[2]/abbr");
    private static final By documents = By.xpath("//div[@class='tabbable']/ul/li/a[text()='Documents']");
    private static final By country = By.xpath("//select[@id='xmlm_bundle_userbundle_document_citizen']/option[@selected='selected']");
    private static final By addEmail = By.id("addEmail");
    private static final By secondMail = By.id("fos_user_profile_form_userContact_email1");
    private static final By setAsMain = By.xpath("//div[@id='additional-email-1']//button[contains(text(),'Set as main')]");
    private static final By emailSavedMessage = By.xpath("//[@id='gritter-notice-wrapper']//div/p[text()='Email saved.']");
    private static final By checkNewEmailMessage = By.xpath("//div[@id='main-modal-window-confirmed-old-email']//h3[text()='Check your new email']");
    private static final By closeNewEmailMessage =  By.xpath("//div[@id='main-modal-window-confirmed-old-email']//button[text()='Close']");
    private static final By changedEmailMessage = By.xpath("//div[@id='main-modal-window-confirmed-new-email']//h3[text()='Main E-mail has been changed']");
    private static final By closeChangedEmailMessage =  By.xpath("//div[@id='main-modal-window-confirmed-new-email']//button[text()='Close']");


    public ProfilePage(WebDriver driver){
        super(driver);
    }
    public int getCurrentValue(){
        return Integer.parseInt(driver.findElement(current).getText());
    }
    public int getBonusesValue(){
        return Integer.parseInt(driver.findElement(bonuses).getText());
    }
    public int getSalaryValue(){
        return Integer.parseInt(driver.findElement(salary).getText());
    }

    public void goProfilePage(){
        WebDriverWait wait = new WebDriverWait(driver,20);
        driver.findElement(profilePage).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(fullName));
    }
    public void addNewEmail(TestUser testUser){ //додати другу пошту
        WebDriverWait wait = new WebDriverWait(driver, 20);
        Actions builder = new Actions(driver);
        driver.findElement(addEmail).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(secondMail));
        driver.findElement(secondMail).clear();
        driver.findElement(secondMail).sendKeys(testUser.getNewEmail());
        driver.findElement(setAsMain).click();

    }
    public boolean addNewEmailError(){

        return driver.findElement(emailSavedMessage).isDisplayed();
    }
    public String newMailConfirmation(String activationLink){ // конфірм першого листа зміни пошти
        WebDriverWait wait = new WebDriverWait(driver, 40);
        driver.get(activationLink);
        wait.until(ExpectedConditions.presenceOfElementLocated(checkNewEmailMessage));
        String successText = driver.findElement(checkNewEmailMessage).getText();
        driver.findElement(closeNewEmailMessage).click();
        return successText;
    }
    public String newChangedEmail(String activationLink){ // конфірм другого листа зміни пошти
        WebDriverWait wait = new WebDriverWait(driver, 40);
        driver.get(activationLink);
        wait.until(ExpectedConditions.presenceOfElementLocated(changedEmailMessage));
        String successText = driver.findElement(changedEmailMessage).getText();
        driver.findElement(closeChangedEmailMessage).click();
        return successText;
    }




    public String getFullName(){

        return driver.findElement(fullName).getAttribute("value");
    }
    public String getGender(){
        return driver.findElement(gender).getText();
    }
    public String getEmail(){
        return driver.findElement(email).getAttribute("value").trim();
    }
    public String getSecondEmail(){return driver.findElement(secondMail).getAttribute("value").trim();}

    public String getPhone(){
        return driver.findElement(phone).getAttribute("value");
    }
    public String getCareer() { return driver.findElement(career).getText(); }
    public String getStatus() { return driver.findElement(status).getText(); }
    public String getIdentification() {return driver.findElement(identification).getText(); }
    public String getInviteCode(){return driver.findElement(inviteCode).getText();}

    public String getCountry(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.findElement(documents).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(country));
        return driver.findElement(country).getText();

    }

}
