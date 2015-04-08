package FunctionalTests.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by User on 1/5/2015.
 */
public class TransferPage extends MoneyFamilyPage{

    public static final By currentAccordion = By.xpath("//div[@id='accordion4']//strong[contains(text(), 'Current')]");
    public static final By bonusesAccordion = By.xpath("//div[@id='accordion4']//div[contains(@class,'panel-bonuses')]//a");
    public static final By salaryAccordion = By.xpath("//div[@id='accordion4']//div[contains(@class,'panel-salary')]//a");
    public static final By fromBonusToCurrent = By.xpath("//div[@id='tab3-2']//div[@class='radio']/label[@data-type='current']/div");
    public static final By fromBonusToSalary = By.xpath("//div[@id='tab3-2']//div[@class='radio']/label[@data-type='salary']/div");
    //обов"язково перевірку ісСелект
    public static final By fromSalaryToCurrent = By.xpath("//div[@id='tab3-3']//div[@class='radio']/label/div");
    public static final By amountBonuses = By.id("transfer_amount_bonuses");
    public static final By transferButtonBonuses = By.id("transfer_bonuses");
    public static final By amountSalary = By.id("transfer_amount_salary");
    public static final By transferButtonSalary = By.id("transfer_salary");


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
        wait.until(ExpectedConditions.visibilityOfElementLocated(fromBonusToCurrent));
    }
    public void clickSalaryAccordion(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(salaryAccordion));
        driver.findElement(salaryAccordion).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(fromSalaryToCurrent));
    }
    public void clickOnFromBonusesToCurrentRadioButton(){
        //List<WebElement> radioButton = driver.findElements(fromBonusToCurrent);
        driver.findElement(fromBonusToCurrent).click();
        //wait.until(ExpectedConditions.elementSelectionStateToBe(fromBonusToCurrent, true));
    }


    public void clickOnFromBonusesToSalaryRadioButton(){
        driver.findElement(fromBonusToSalary).click();
    }
    public void clickOnFromSalaryToCurrantRadioButton(){driver.findElement(fromSalaryToCurrent).click();}
    public void enterAmountFromBonuses(String amount){
        driver.findElement(amountBonuses).clear();
        driver.findElement(amountBonuses).click();
        driver.findElement(amountBonuses).sendKeys(amount);

    }
    public void enterAmountFromSalary(String amount){
        driver.findElement(amountSalary).clear();
        driver.findElement(amountSalary).click();
        driver.findElement(amountSalary).sendKeys(amount);
    }
    public void clickBonusTransfer(){
        AuthorizedUserPage userPage = new AuthorizedUserPage(driver, wait);
        driver.findElement(transferButtonBonuses).click();
        userPage.waitForSuccessMessage();
    }
    public void clickSalaryTransfer(){
        AuthorizedUserPage userPage = new AuthorizedUserPage(driver, wait);
        driver.findElement(transferButtonSalary).click();
        userPage.waitForSuccessMessage();

    }

}