package FunctionalTests.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by User on 1/5/2015.
 */
public class TransferPage extends BasePage{

    public static final By moneyPage  = By.xpath("//div[@id='head-nav']//a[@href='/money/']");
    public static final By transferPage = By.xpath("//div[@class='col-md-12']//a[@href='/money/buy/']");
    public static final By currentAccordion = By.xpath("//div[@id='accordion4']/div[1]");
    public static final By bonusesAccordion = By.xpath("//div[@id='accordion4']/div[2]");
    public static final By salaryAccordion = By.xpath("//div[@id='accordion4']/div[3]");
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

    public static final By operationHistoryPage


    public TransferPage(WebDriver driver) {
        super(driver);
    }
}
