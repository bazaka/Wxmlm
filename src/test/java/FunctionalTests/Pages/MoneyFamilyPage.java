package FunctionalTests.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// * Created for W-xmlm by Fill on 11.02.2015.
public class MoneyFamilyPage extends AuthorizedUserPage {
    public MoneyFamilyPage(WebDriver driver, WebDriverWait wait) {super(driver, wait);}
    public static final By accountsItem = By.xpath("//a[@href='/money/accounts/']");
    public static final By bonusesItem = By.xpath("//a[@href='/money/bonuses/']");
    public static final By rechargeItem = By.xpath("//a[@href='/money/buy/']");
    public static final By transferItem = By.xpath("//a[@href='/money/transfer/']");
    public static final By withdrawItem = By.xpath("//a[@href='/money/withdraw/']");
    public static final By operationHistoryItem = By.xpath("//a[@href='/money/operation/']");
    public static final By myRequestsItem = By.xpath("//a[@href='/money/merchant-request/']");
    public static final By toggleItem = By.xpath("//a[@id='change-grid']");
    public static final By operationsWidget = By.xpath("//div[@class='global__operation-widget']/div");
    public static final By newPartnersItem = By.xpath("//div[@class='fd-tile detail tile-green no-margin tile-new-partners']");
    public static final By newPurchasesItem = By.xpath("//div[@class='fd-tile detail tile-red no-margin tile-new-purchases']");
    public static final By myNewBonusesItem = By.xpath("//div[@class='fd-tile detail tile-blue no-margin tile-new-bonuses']");
    public static final By myInvestmentIncomeItem = By.xpath("//div[@class='fd-tile detail tile-lemon tile-investment-income']");

    public void goToOperationHistory() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(operationHistoryItem));
        driver.findElement(operationHistoryItem).click();
        wait.until(ExpectedConditions.titleIs("Operations"));
    }
    public void goToTransfer(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(transferItem));
        driver.findElement(transferItem).click();
        wait.until(ExpectedConditions.titleIs("Transfer"));
    }
    public void goToRecharge(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(rechargeItem));
        driver.findElement(rechargeItem).click();
        wait.until(ExpectedConditions.titleIs("Recharge"));
    }
}