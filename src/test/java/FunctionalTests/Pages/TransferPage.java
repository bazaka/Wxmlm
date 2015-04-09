package FunctionalTests.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by User on 1/5/2015.
 */
public class TransferPage extends MoneyFamilyPage{
    //Локаторы
    //Таб Current
    public static final By currentAccordion = By.xpath("//div[@id='accordion4']//strong[contains(text(), 'Current')]");
    public static final By partnersMainEmailInput = By.xpath("//div[@id='tab3-1']//input[@id='money_account_ajax_check_email']");
    public static final By partnersInviteCode = By.xpath("//div[@id='tab3-1']//input[@id='money_account_ajax_check_code']");
    public static final By currentAmountInput = By.xpath("//div[@id='tab3-1']//input[@id='transfer_amount']");
    public static final By currentTransferButton = By.xpath("//button[@id='transfer_to_user']");
    //Таб Bonuses
    public static final By bonusesAccordion = By.xpath("//div[@id='accordion4']//div[contains(@class,'panel-bonuses')]//a");
    public static final By fromBonusToCurrentRadiobutton = By.xpath("//div[@id='tab3-2']//div[@class='radio']/label[@data-type='current']");
    public static final By fromBonusToSalaryRadiobutton = By.xpath("//div[@id='tab3-2']//div[@class='radio']/label[@data-type='salary']");
    public static final By fromBonusToCurrentRadiobuttonActiveDiv = By.xpath("//div[@id='tab3-2']//div[@class='radio']/label[@data-type='current']/div");
    public static final By fromBonusToSalaryRadiobuttonActiveDiv = By.xpath("//div[@id='tab3-2']//div[@class='radio']/label[@data-type='salary']/div");
    public static final By bonusesAmountInput = By.xpath("//input[@id='transfer_amount_bonuses']");
    public static final By bonusesTransferButton = By.xpath("//button[@id='transfer_bonuses']");
    //Таб Salary
    public static final By salaryAccordion = By.xpath("//div[@id='accordion4']//div[contains(@class,'panel-salary')]//a");
    public static final By fromSalaryToCurrentRadiobutton = By.xpath("//div[@id='tab3-3']//div[@class='radio']/label");
    public static final By fromSalaryToCurrentRadiobuttonActiveDiv = By.xpath("//div[@id='tab3-3']//div[@class='radio']/label/div");
    public static final By salaryAmountInput = By.xpath("//input[@id='transfer_amount_salary']");
    public static final By salaryTransferButton = By.xpath("//button[@id='transfer_salary']");


    public TransferPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public String getTitle(){
        return driver.getTitle();
    }
/*
    public void positiveTransferFromBonusesToCurrent(String value){

        driver.findElement(bonusesAccordion).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(fromBonusToCurrent));
        driver.findElement(fromBonusToCurrent).click();
        wait.until(ExpectedConditions.elementToBeSelected(fromBonusToCurrent));
        driver.findElement(amountBonuses).clear();
        driver.findElement(amountBonuses).click();
        driver.findElement(amountBonuses).sendKeys(value);
        driver.findElement(transferButtonBonuses).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(popup));
    }*/
    public void clickBonusesAccordion(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(bonusesAccordion));
        driver.findElement(bonusesAccordion).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(fromBonusToCurrentRadiobuttonActiveDiv));
    }
    public void clickSalaryAccordion(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(salaryAccordion));
        driver.findElement(salaryAccordion).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(fromSalaryToCurrentRadiobuttonActiveDiv));
    }
    public void clickOnFromBonusesToCurrentRadioButton(){
        //List<WebElement> radioButton = driver.findElements(fromBonusToCurrent);
        driver.findElement(fromBonusToCurrentRadiobuttonActiveDiv).click();
        //wait.until(ExpectedConditions.elementSelectionStateToBe(fromBonusToCurrent, true));
    }


    public void clickOnFromBonusesToSalaryRadioButton(){
        driver.findElement(fromBonusToSalaryRadiobuttonActiveDiv).click();
    }
    public void clickOnFromSalaryToCurrantRadioButton(){driver.findElement(fromSalaryToCurrentRadiobuttonActiveDiv).click();}
    public void enterAmountFromBonuses(String amount){
        driver.findElement(bonusesAmountInput).clear();
        driver.findElement(bonusesAmountInput).click();
        driver.findElement(bonusesAmountInput).sendKeys(amount);

    }
    public void enterAmountFromSalary(String amount){
        driver.findElement(salaryAmountInput).clear();
        driver.findElement(salaryAmountInput).click();
        driver.findElement(salaryAmountInput).sendKeys(amount);
    }
    public void clickBonusTransfer(){
        AuthorizedUserPage userPage = new AuthorizedUserPage(driver, wait);
        driver.findElement(bonusesTransferButton).click();
        userPage.waitForSuccessMessage();
    }
    public void clickSalaryTransfer(){
        AuthorizedUserPage userPage = new AuthorizedUserPage(driver, wait);
        driver.findElement(salaryTransferButton).click();
        userPage.waitForSuccessMessage();
    }
}