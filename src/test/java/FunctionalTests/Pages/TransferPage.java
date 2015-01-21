package FunctionalTests.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by User on 1/5/2015.
 */
public class TransferPage extends BasePage{

    public static final By moneyPage  = By.xpath("//div[@id='head-nav']//a[@href='/money/']");
    public static final By transferPage = By.xpath("//div[@class='col-md-12']//a[@href='/money/buy/']");
    public static final By currentAccordion = By.xpath("//div[@id='accordion4']//strong[contains(text(), 'Current')]");
    public static final By bonusesAccordion = By.xpath("//div[@id='accordion4']//strong[contains(text(), 'Bonuses')]");
    public static final By salaryAccordion = By.xpath("//div[@id='accordion4']//strong[contains(text(), 'Salary')]");
    public static final By fromBonusToCurrent = By.xpath("//div[@id='tab3-2']//div[@class='radio']/label[@data-type='current']/div");
    public static final By fromBonusToSalary = By.xpath("//div[@id='tab3-2']//div[@class='radio']/label[@data-type='salary']/div");
    //обов"язково перевірку ісСелект
    public static final By fromSalaryToCurrent = By.xpath("//div[@id='tab3-3']//div[@class='radio']/label[@data-type='current']/div");
    public static final By amountBonuses = By.id("transfer_amount_bonuses");
    public static final By transferButtonBonuses = By.id("transfer_bonuses");
    public static final By amountSalary = By.id("transfer_amount_salary");
    public static final By transferButtonSalary = By.id("transfer_salary");
    public static final By incorrectValueBonuses = By.xpath("//div[@id='tab3-2']//li[@class='parsley-pattern']");
    public static final By requiredValueBonuses = By.xpath("//div[@id='tab3-2']//li[@class='parsley-required']");
    public static final By incorrectValueSalary = By.xpath("//div[@id='tab3-2']//li[@class='parsley-pattern']");
    public static final By requiredValueSalary = By.xpath("//div[@id='tab3-2']//li[@class='parsley-required']");

    public static final By operationHistoryPage = By.xpath("//div[@class='col-md-12']//a[@href='/money/operation/']");
    public static final By lastType = By.xpath("//table[@id='datatable2']/tbody/tr[1]/td[3]");
    public static final By lastSender = By.xpath("//table[@id='datatable2']/tbody/tr[1]/td[4]");
    public static final By lastAmount = By.xpath("//table[@id='datatable2']/tbody/tr[1]/td[5]");
    public static final By lastStatus = By.xpath("//table[@id='datatable2']/tbody/tr[1]/td[6]");

    public static final By popup = By.xpath("//div[@id='gritter-notice-wrapper']/div/div[@class='gritter-item']/div/p");


    public TransferPage(WebDriver driver) {
        super(driver);
    }
   /* public void openMoneyPage(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        AuthorizedUserPage authorizedUserPage = new AuthorizedUserPage(driver);
        authorizedUserPage.goMoney();
        authorizedUserPage.goTransfer();
        wait.until(ExpectedConditions.presenceOfElementLocated(transferPage));

    }
    public void openTransferPage(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.findElement(transferPage).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(currentAccordion));
    }*/
    public String getTitle(){
        return driver.getTitle();
    }

    public void positiveTransferFromBonusesToCurrent(String value){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.findElement(bonusesAccordion).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(fromBonusToCurrent));
        driver.findElement(fromBonusToCurrent).click();
        wait.until(ExpectedConditions.elementToBeSelected(fromBonusToCurrent));
        driver.findElement(amountBonuses).clear();
        driver.findElement(amountBonuses).click();
        driver.findElement(amountBonuses).sendKeys(value);
        driver.findElement(transferButtonBonuses).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(popup));
    }
    public void checkOperationHistory(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.findElement(operationHistoryPage);
        wait.until(ExpectedConditions.presenceOfElementLocated(lastType));
    }
    public String getOperationType(){
        return driver.findElement(lastType).getText();
    }
    public String getOperationSender(){
        return driver.findElement(lastSender).getText();
    }
    public String getOperationAmount(){
        return driver.findElement(lastAmount).getText();
    }
    public String getOperationStatus(){
        return driver.findElement(lastStatus).getText();
    }
}
