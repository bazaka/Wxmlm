package FunctionalTests.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by User on 2/6/2015.
 */
public class MoneyFamilyPage extends AuthorizedUserPage{

    public static final By bonuses = By.xpath("//div[@class='col-md-12']//a[@href='/money/bonuses/']");
    public static final By recharge = By.xpath("//div[@class='col-md-12']//a[@href='/money/buy/']");
    public static final By transfer = By.xpath("//div[@class='col-md-12']//a[@href='/money/transfer/']");
    public static final By withdraw = By.xpath("//div[@class='col-md-12']//a[@href='/money/withdraw/']");
    public static final By operationHistory = By.xpath("//div[@class='col-md-12']//a[@href='/money/operation/']");

    public MoneyFamilyPage(WebDriver driver){super(driver);}
    public void goTransfer(){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.findElement(transfer).click();
        wait.until(ExpectedConditions.titleIs("Transfer"));
    }
}
