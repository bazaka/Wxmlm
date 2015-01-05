package FunctionalTests.Pages;

import UsedByAll.ProfileData;
import UsedByAll.TestUser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by User on 12/22/2014.
 */
public class ProfilePage extends BasePage {
    private static final By profilePage = By.cssSelector("img[alt=\"Avatar\"]");
    private static final By fullName = By.id("fos_user_profile_form_fullName"); // вкладка Information
    private static final By gender = By.id("fos_user_profile_form_gender");
    private static final By genderCurrent = By.xpath("//select[@id='fos_user_profile_form_gender']/option[@selected='selected']");

    private static final By birthDate = By.id("fos_user_profile_form_birthday");
    private static final By address = By.id("fos_user_profile_form_userContact_mailingAddress");
    private static final By email = By.xpath("//div[@id='main-email']/div/div/input");
    private static final By phone = By.id("fos_user_profile_form_phone");
    private static final By updateButton = By.xpath("//div[@id='tab1']//a[text()='Update']");

    private static final By documentsTab = By.xpath("//div[@class='tabbable']//a[text()='Documents']"); //вкладка Documents
    private static final By citizen = By.id("xmlm_bundle_userbundle_document_citizen");
    private static final By citizenCurrent = By.xpath("//select[@id='xmlm_bundle_userbundle_document_citizen']/option[@selected='selected']");
    private static final By passportSeries = By.id("xmlm_bundle_userbundle_document_series");
    private static final By passportNumber = By.id("xmlm_bundle_userbundle_document_number");
    private static final By issued = By.id("xmlm_bundle_userbundle_document_issued");
    private static final By issuedDate = By.id("xmlm_bundle_userbundle_document_issuedDate");
    private static final By saveDocumentsButton = By.id("saveProfileDocument");

    private static final By bankDetailsTab = By.xpath("//div[@class='tabbable']//a[text()='Bank details']"); // вкладка Bank Details
    private static final By bankUserName = By.id("xmlm_bundle_moneybundle_bankdetails_swift_name");
    private static final By bankUserAddress = By.id("xmlm_bundle_moneybundle_bankdetails_swift_address");
    private static final By bankName = By.id("xmlm_bundle_moneybundle_bankdetails_swift_bankName");
    private static final By bankAddress = By.id("xmlm_bundle_moneybundle_bankdetails_swift_bankAddress");
    private static final By iban = By.id("xmlm_bundle_moneybundle_bankdetails_swift_accountIban");
    private static final By swift = By.id("xmlm_bundle_moneybundle_bankdetails_swift_swiftCode");
    private static final By epid = By.id("xmlm_bundle_moneybundle_bankdetails_epayments_epid");
    private static final By saveBankDetailsButton = By.id("saveBankDetails");

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

   // private static final By popup = By.className("gritter-without-image");
    private static final By popup = By.xpath("//div[@id='gritter-notice-wrapper']/div/div/div[@class='gritter-without-image']/span[text()='Success!']");
    private static final By popup1 = By.xpath("//div[@id='gritter-notice-wrapper']/div/div/div[@class='gritter-without-image']/span[text()='Success']");


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
        return driver.findElement(genderCurrent).getText();
    }
    public String getBirthDate() { return driver.findElement(birthDate).getAttribute("value");}
    public String getEmail(){
        return driver.findElement(email).getAttribute("value").trim();
    }
    public String getSecondEmail(){return driver.findElement(secondMail).getAttribute("value").trim();}
    public String getPhone(){
        return driver.findElement(phone).getAttribute("value");
    }
    public String getAddress() {return driver.findElement(address).getText();}

    public String getCitizen() {return driver.findElement(citizenCurrent).getText();}
    public String getPassportSeries() {return driver.findElement(passportSeries).getAttribute("value");}
    public String getPassportNumber() {return driver.findElement(passportNumber).getAttribute("value");}
    public String getIssued() {return driver.findElement(issued).getAttribute("value");}
    public String getIssuedDate() {return driver.findElement(issuedDate).getAttribute("value");}

    public String getBankUserName() {return driver.findElement(bankUserName).getAttribute("value");}
    public String getBankUserAddress() {return driver.findElement(bankUserAddress).getAttribute("value");}
    public String getBankName() {return driver.findElement(bankName).getAttribute("value");}
    public String getBankAddress() {return driver.findElement(bankAddress).getText();}
    public String getIban() {return driver.findElement(iban).getAttribute("value");}
    public String getSwift() {return driver.findElement(swift).getAttribute("value");}
    public String getEpid() {return driver.findElement(epid).getAttribute("value");}



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
    public void editInformationTab(ProfileData profileData){  // редагування вкладки Information

        WebDriverWait wait = new WebDriverWait(driver, 15);
        Actions builder = new Actions(driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(fullName));

        driver.findElement(fullName).clear();
        driver.findElement(fullName).click();
        driver.findElement(fullName).sendKeys(profileData.getUserFullName());

        Select dropdownGender = new Select(driver.findElement(gender));
        dropdownGender.selectByVisibleText(profileData.getGender());

        driver.findElement(birthDate).clear();
        driver.findElement(birthDate).click();
        driver.findElement(birthDate).sendKeys(profileData.getBirthDate());

        driver.findElement(phone).clear();
        driver.findElement(phone).click();
        driver.findElement(phone).sendKeys(profileData.getPhoneNumber());

        driver.findElement(address).clear();
        driver.findElement(address).click();
        driver.findElement(address).sendKeys(profileData.getAddress());


        driver.findElement(updateButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(popup));
        driver.navigate().refresh();
        wait.until(ExpectedConditions.presenceOfElementLocated(profilePage));
        goProfilePage();


    }
    public void editDocumentsTab(ProfileData profileData){
        WebDriverWait wait = new WebDriverWait(driver, 15);
        driver.findElement(documentsTab).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(passportSeries));

        Select dropdownCitizen = new Select(driver.findElement(citizen));
        dropdownCitizen.selectByVisibleText(profileData.getCitizen());

        driver.findElement(passportSeries).clear();
        driver.findElement(passportSeries).click();
        driver.findElement(passportSeries).sendKeys(profileData.getPassportSeries());

        driver.findElement(passportNumber).clear();
        driver.findElement(passportNumber).click();
        driver.findElement(passportNumber).sendKeys(profileData.getPassportNum());

        driver.findElement(issuedDate).clear();
        driver.findElement(issuedDate).click();
        driver.findElement(issuedDate).sendKeys(profileData.getIssuedDate());

        driver.findElement(issued).clear();
        driver.findElement(issued).click();
        driver.findElement(issued).sendKeys(profileData.getIssued());



        driver.findElement(saveDocumentsButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(popup1));

        driver.navigate().refresh();
        wait.until(ExpectedConditions.presenceOfElementLocated(profilePage));
        goProfilePage();
        driver.findElement(documentsTab).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(passportSeries));
    }
    public void editBankTab(ProfileData profileData){
        WebDriverWait wait = new WebDriverWait(driver, 120);
        driver.findElement(bankDetailsTab).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(bankUserName));

        driver.findElement(bankUserName).clear();
        driver.findElement(bankUserName).click();
        driver.findElement(bankUserName).sendKeys(profileData.getBName());

        driver.findElement(bankUserAddress).clear();
        driver.findElement(bankUserAddress).click();
        driver.findElement(bankUserAddress).sendKeys(profileData.getBAddress());

        driver.findElement(bankName).clear();
        driver.findElement(bankName).click();
        driver.findElement(bankName).sendKeys(profileData.getBankName());

        driver.findElement(bankAddress).clear();
        driver.findElement(bankAddress).click();
        driver.findElement(bankAddress).sendKeys(profileData.getBankAddress());

        driver.findElement(iban).clear();
        driver.findElement(iban).click();
        driver.findElement(iban).sendKeys(profileData.getIban());

        driver.findElement(swift).clear();
        driver.findElement(swift).click();
        driver.findElement(swift).sendKeys(profileData.getSwift());

        driver.findElement(epid).clear();
        driver.findElement(epid).click();
        driver.findElement(epid).sendKeys(profileData.getEpid());

        driver.findElement(saveBankDetailsButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(popup1));
       /* try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        driver.navigate().refresh();
        wait.until(ExpectedConditions.presenceOfElementLocated(profilePage));
        goProfilePage();
        driver.findElement(bankDetailsTab).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(bankUserName));

    }

}
