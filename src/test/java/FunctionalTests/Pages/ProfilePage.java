package FunctionalTests.Pages;

import UsedByAll.TestUser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by User on 12/22/2014.
 */
public class ProfilePage extends BasePage {
    private static final By profilePage = By.cssSelector("img[alt=\"Avatar\"]");
    private static final By fullName = By.id("fos_user_profile_form_fullName"); // вкладка Information
    private static final By closeProfilePage = By.xpath("//div[@class='modal-header']/button");
    private static final By gender = By.id("fos_user_profile_form_gender");
    private static final By genderCurrent = By.xpath("//select[@id='fos_user_profile_form_gender']/option[@selected='selected']");

    private static final By birthDate = By.id("fos_user_profile_form_birthday");
    private static final By address = By.id("fos_user_profile_form_userContact_mailingAddress");
    private static final By email = By.xpath("//div[@id='main-email']/div/div/input");
    private static final By phone = By.id("fos_user_profile_form_phone");
    private static final By updateButton = By.xpath("//div[@id='tab1']//a[text()='Save changes']");

    private static final By documentsTab = By.xpath("//div[@class='tabbable']//a[text()='Documents']"); //вкладка Documents
    private static final By citizen = By.id("xmlm_bundle_userbundle_document_user_citizen");
    private static final By citizenCurrent = By.xpath("//select[@id='xmlm_bundle_userbundle_document_user_citizen']/option[@selected='selected']");
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

  /*  private static final By popup = By.xpath("//div[@id='gritter-notice-wrapper']/div/div[@class='gritter-item']/div/p");
    private static final By popup1 = By.xpath("//div[@id='gritter-notice-wrapper']/div/div[@class='gritter-item']/div/p");*/
    public static final By bankCardsTab = By.xpath("//div[@class='tabbable']//a[text()='Bank Cards']");
    public static final By addCardButton = By.id("add-new-card");
    public static final By cardTitle = By.id("xmlm_bundle_userbundle_card_title");
    public static final By cardNumber = By.id("xmlm_bundle_userbundle_card_number");
    public static final By cardHolder = By.id("xmlm_bundle_userbundle_card_cardholder");
    public static final By expDate = By.id("xmlm_bundle_userbundle_card_expiration");
   // public static final By mainCard = By.id("xmlm_bundle_userbundle_card_main");
    public static final By mainCard = By.xpath("//div[@class='form-outer']/select");
    public static final By saveCardButton = By.xpath("//form[@id='addNewCardForm']//button[text()='Save card']");

    public static final By tableCardTitle = By.xpath("//table[@id='my-credit-cards']/tbody/tr[1]/td[1]/a");
    public static final By tableCardNumber = By.xpath("//table[@id='my-credit-cards']/tbody/tr[1]/td[2]");
    public static final By tableCardHolder = By.xpath("//table[@id='my-credit-cards']/tbody/tr[1]/td[3]");
    public static final By tableExpires = By.xpath("//table[@id='my-credit-cards']/tbody/tr[1]/td[4]");
    public static final By tableDeleteCard = By.xpath("//table[@id='my-credit-cards']/tbody/tr[1]/td[5]/a");
    public static final By tableRows = By.xpath("//table[@id='my-credit-cards']/tbody/tr");

    public static final By saveCardInEditor = By.xpath("//form[@id='editCardForm']//button[text()='Save card']");
    public static final By mainCardStatus = By.xpath("//a[@class='card-edit']/i");






    public ProfilePage(WebDriver driver, WebDriverWait wait){
        super(driver, wait);
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
       // WebDriverWait wait = new WebDriverWait(driver,20);
        driver.findElement(profilePage).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(fullName));
    }
    public void closeProfilePage(){
        driver.findElement(closeProfilePage).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(closeProfilePage));
    }
    public String getInviteCode(){
        String inviteCodeLink = driver.findElement(inviteCode).getText();
        return (inviteCodeLink.split("#")[1]);

    }
    public void addNewEmail(String newEmail){ //додати другу пошту
       // WebDriverWait wait = new WebDriverWait(driver, 20);
      //  Actions builder = new Actions(driver);
        driver.findElement(addEmail).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(secondMail));
        driver.findElement(secondMail).clear();
        driver.findElement(secondMail).sendKeys(newEmail);
        driver.findElement(setAsMain).click();

    }
    public boolean addNewEmailError(){

        return driver.findElement(emailSavedMessage).isDisplayed();
    }
    public String newMailConfirmation(String activationLink){ // конфірм першого листа зміни пошти
        //WebDriverWait wait = new WebDriverWait(driver, 40);
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
    //public String getInviteCode(){return driver.findElement(inviteCode).getText();}

    public String getCountry(){
        //WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.findElement(documents).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(country));
        return driver.findElement(country).getText();

    }
    public void editInformationTab(String profileFullName, String profileGender, String profileBirth, String profilePhone, String profileAddress ){  // редагування вкладки Information

       // WebDriverWait wait = new WebDriverWait(driver, 15);
        Actions builder = new Actions(driver);

        wait.until(ExpectedConditions.presenceOfElementLocated(fullName));

        driver.findElement(fullName).clear();
        driver.findElement(fullName).click();
        driver.findElement(fullName).sendKeys(profileFullName);

        Select dropdownGender = new Select(driver.findElement(gender));
        dropdownGender.selectByVisibleText(profileGender);

        driver.findElement(birthDate).clear();
        driver.findElement(birthDate).click();
        driver.findElement(birthDate).sendKeys(profileBirth);

        driver.findElement(phone).clear();
        driver.findElement(phone).click();
        driver.findElement(phone).sendKeys(profilePhone);

        driver.findElement(address).clear();
        driver.findElement(address).click();
        driver.findElement(address).sendKeys(profileAddress);


        driver.findElement(updateButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(popup));
        driver.navigate().refresh();
        wait.until(ExpectedConditions.presenceOfElementLocated(profilePage));
        goProfilePage();


    }
    public void editDocumentsTab(String profileCitizen, String profilePassportSeries, String profilePassportNum, String profileIssuedDate, String profileIssued ){
        //WebDriverWait wait = new WebDriverWait(driver, 15);
        driver.findElement(documentsTab).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(passportSeries));

        Select dropdownCitizen = new Select(driver.findElement(citizen));
        dropdownCitizen.selectByVisibleText(profileCitizen);

        driver.findElement(passportSeries).clear();
        driver.findElement(passportSeries).click();
        driver.findElement(passportSeries).sendKeys(profilePassportSeries);

        driver.findElement(passportNumber).clear();
        driver.findElement(passportNumber).click();
        driver.findElement(passportNumber).sendKeys(profilePassportNum);

        driver.findElement(issuedDate).clear();
        driver.findElement(issuedDate).click();
        driver.findElement(issuedDate).sendKeys(profileIssuedDate);

        driver.findElement(issued).clear();
        driver.findElement(issued).click();
        driver.findElement(issued).sendKeys(profileIssued);



        driver.findElement(saveDocumentsButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(popup1));

        driver.navigate().refresh();
        wait.until(ExpectedConditions.presenceOfElementLocated(profilePage));
        goProfilePage();
        driver.findElement(documentsTab).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(passportSeries));
    }
    public void editBankTab(String profileBName, String profileBAddress, String profileBankName, String profileBankAddress,String profileIban, String profileSwift, String profileEpid  ){
       // WebDriverWait wait = new WebDriverWait(driver, 120);
        driver.findElement(bankDetailsTab).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(bankUserName));

        driver.findElement(bankUserName).clear();
        driver.findElement(bankUserName).click();
        driver.findElement(bankUserName).sendKeys(profileBName);

        driver.findElement(bankUserAddress).clear();
        driver.findElement(bankUserAddress).click();
        driver.findElement(bankUserAddress).sendKeys(profileBAddress);

        driver.findElement(bankName).clear();
        driver.findElement(bankName).click();
        driver.findElement(bankName).sendKeys(profileBankName);

        driver.findElement(bankAddress).clear();
        driver.findElement(bankAddress).click();
        driver.findElement(bankAddress).sendKeys(profileBankAddress);

        driver.findElement(iban).clear();
        driver.findElement(iban).click();
        driver.findElement(iban).sendKeys(profileIban);

        driver.findElement(swift).clear();
        driver.findElement(swift).click();
        driver.findElement(swift).sendKeys(profileSwift);

        driver.findElement(epid).clear();
        driver.findElement(epid).click();
        driver.findElement(epid).sendKeys(profileEpid);

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
    public void editBankCardTab(String cardFileName, String cardFileNumber, String cardFileHolderName, String fileExpirationDate){
        driver.findElement(bankCardsTab).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(addCardButton));
        driver.findElement(addCardButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(cardTitle));

        driver.findElement(cardTitle).clear();
        driver.findElement(cardTitle).click();
        driver.findElement(cardTitle).sendKeys(cardFileName);

       // driver.findElement(cardNumber).clear();
        driver.findElement(cardNumber).click();
        driver.findElement(cardNumber).sendKeys(cardFileNumber);

        driver.findElement(cardHolder).clear();
        driver.findElement(cardHolder).click();
        driver.findElement(cardHolder).sendKeys(cardFileHolderName);

        //driver.findElement(expDate ).clear();
        driver.findElement(expDate ).click();
        driver.findElement(expDate ).sendKeys(fileExpirationDate);

        driver.findElement(saveCardButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(popup));
        driver.navigate().refresh();
        wait.until(ExpectedConditions.presenceOfElementLocated(profilePage));
        goProfilePage();
        driver.findElement(bankCardsTab).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(addCardButton));
    }
    public String getLastCardTitle(){
        List<WebElement> cardTitles = driver.findElements(tableCardTitle);
        int titleCounts = cardTitles.size() - 1;
        return(cardTitles.get(titleCounts).getText());

    }
    public String getLastCardNumber(){
        List<WebElement> cardNum = driver.findElements(tableCardNumber);
        int numCounts = cardNum.size() - 1;
        return(cardNum.get(numCounts).getText().trim());
    }
    public String getLastCardHolder(){
        List<WebElement> cardHolder = driver.findElements(tableCardHolder);
        int holderCounts = cardHolder.size() - 1;
        return(cardHolder.get(holderCounts).getText());
    }
    public String getLastExpDate(){
        List<WebElement> cardDates = driver.findElements(tableExpires);
        int dateCounts = cardDates.size() - 1;
        return(cardDates.get(dateCounts).getText());

    }

    public int getCardsCount(){
        List<WebElement> cards = driver.findElements(tableRows);
        return (cards.size());
    }
    public String getCardStatusInFirstCard(){ //берем значение класса первой карты
        return(driver.findElement(mainCardStatus).getAttribute("class"));

    }
    public void editFirstCardToMainCard(){
        driver.findElement(tableCardTitle).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(mainCard));
        Select dropdownMainCard = new Select(driver.findElement(mainCard));
        dropdownMainCard.selectByVisibleText("Yes");
        //dropdownMainCard.selectByValue("1");
        driver.findElement(saveCardInEditor).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(popup));
    }
    public String getCardStatusInLastCard(){ //заносим все карты в список и берем значение атрибута последнего
        List<WebElement> cards = driver.findElements(mainCardStatus);
        int cardCounts = cards.size() - 1;
        return(cards.get(cardCounts).getAttribute("class"));
    }


    public void deleteFirstCard(){

        driver.findElement(tableDeleteCard).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(popup));

    }

}
