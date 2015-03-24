package FunctionalTests.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// * Created for W-xmlm by Fill on 11.02.2015.
public class MoneyFamilyPage extends AuthorizedUserPage {
    public MoneyFamilyPage(WebDriver driver, WebDriverWait wait) {super(driver, wait);}
    public static final By accountsItem = By.xpath("//a[contains(text(), '  Accounts')]");
    public static final By bonuses = By.xpath("//a[contains(text(), '  Bonuses')]");
    public static final By recharge = By.xpath("//a[contains(text(), '  Recharge')]");
    //  public static final By transfer = By.xpath("//a[contains(text(), '  Transfer')]");
    public static final By transfer = By.xpath("//a[@href='/money/transfer/']");
    public static final By withdraw = By.xpath("//a[contains(text(), ' Withdraw')]");
    public static final By operationHistory = By.xpath("//a[@href='/money/operation/']");

    public void goToOperationHistory() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(operationHistory));
        driver.findElement(operationHistory).click();
        wait.until(ExpectedConditions.titleIs("Operations"));
    }
    public void goToTransfer(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(transfer));
        driver.findElement(transfer).click();
        wait.until(ExpectedConditions.titleIs("Transfer"));
    }
    public void goToRecharge(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(recharge));
        driver.findElement(recharge).click();
        wait.until(ExpectedConditions.titleIs("Recharge"));
    }

}