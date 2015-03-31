package FunctionalTests.Pages;

import UsedByAll.PathFromBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

// * Created for W-xmlm by Fill on 11.02.2015.
public class AccountsPage extends MoneyFamilyPage {
    public AccountsPage(WebDriver driver, WebDriverWait wait) {super(driver, wait);}
    public static final By pageHeader = By.xpath("//div[@class='header']/h3[contains(text(), 'Accounts')]");

    public static final By currentAccountBlock = By.xpath("//h3[contains(text(), 'Current')]/../..");
    public static final By currentAccountBuyButton = By.xpath(PathFromBy.getXpathFromBy(currentAccountBlock) + "//a[@href='/products/']");
    public static final By currentAccountRechargeButton = By.xpath(PathFromBy.getXpathFromBy(currentAccountBlock) + "//a[@href='/money/buy/']");
    public static final By currentAccountTransferToPartnerButton = By.xpath(PathFromBy.getXpathFromBy(currentAccountBlock) + "//a[@href='/money/transfer/#transfer=1']");
    public static final By currentAccountOperationHistoryButton = By.xpath(PathFromBy.getXpathFromBy(currentAccountBlock) + "//a[@href='/money/operation/?filter=Me, Current']");

    public static final By bonusesAccountBlock = By.xpath("//h3[contains(text(), 'Bonuses')]/../..");
    public static final By bonusesAccountTransferToCurrentButton = By.xpath(PathFromBy.getXpathFromBy(bonusesAccountBlock) + "//a[@href='/money/transfer/#transfer=21']");
    public static final By bonusesAccountTransferToSalaryButton = By.xpath(PathFromBy.getXpathFromBy(bonusesAccountBlock) + "//a[@href='/money/transfer/#transfer=22']");
    public static final By bonusesAccountOperationHistoryButton = By.xpath(PathFromBy.getXpathFromBy(bonusesAccountBlock) + "//a[@href='/money/operation/?filter=Me, Bonuses']");

    public static final By salaryAccountBlock = By.xpath("//h3[contains(text(), 'Salary')]/../..");
    public static final By salaryAccountTransferToCurrentButton = By.xpath(PathFromBy.getXpathFromBy(salaryAccountBlock) + "//a[@href='/money/transfer/#transfer=3']");
    public static final By salaryAccountWithdrawButton = By.xpath(PathFromBy.getXpathFromBy(salaryAccountBlock) + "//a[@href='/money/withdraw/']");
    public static final By salaryAccountOperationHistoryButton = By.xpath(PathFromBy.getXpathFromBy(salaryAccountBlock) + "//a[@href='/money/operation/?filter=Me, Salary']");
}
