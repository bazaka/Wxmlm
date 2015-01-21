package FunctionalTests.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// * Created for W-xmlm by Fill on 05.01.2015.
public class AuthorizedUserPage extends BasePage {
    private static final By home = By.xpath("//div[@id='head-nav']//li[4]/a[@href='/']");
    private static final By money = By.xpath("//div[@id='head-nav']//li[8]/a[@href='/money/']");
    private static final By products = By.xpath("//div[@id='head-nav']//li[12]/a[@href='/products/']");
    private static final By net = By.xpath("//div[@id='head-nav']//li[16]/a[@href='/network/']");
    private static final By career = By.xpath("//div[@id='head-nav']//li[20]/a[@href='/career/']");
    private static final By profilePage = By.xpath("//img[@alt='Avatar']");
    private static final By userMenu = By.xpath("//li[@class='dropdown']/a[@class='dropdown-toggle']");
    private static final By mySettings = By.linkText("My Settings");
    private static final By profile = By.linkText("Profile");
    private static final By messages = By.linkText("Sign Out");
    private static final By successMessage = By.xpath("//div[@class='gritter-item']//span[text()='Success!']");

    private static final By accounts = By.linkText("/money/accounts/");
    private static final By bonuses = By.linkText("/money/bonuses/");
    private static final By recharge = By.linkText("/money/buy/");
    private static final By transfer = By.xpath("//div[@class='row']//a[@href='/money/transfer/']");
    private static final By withdraw = By.linkText("/money/withdraw/");
    private static final By oHistory = By.linkText("/money/operation/");

    public void goUserMenu(){
        WebDriverWait wait = new WebDriverWait(driver,20);
        driver.findElement(userMenu).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(profile));
    }
    public void goMoney(){
        WebDriverWait wait = new WebDriverWait(driver,20);
        driver.findElement(money).click();
        wait.until(ExpectedConditions.titleIs("Accounts"));

    }

    public void goTransfer(){
        WebDriverWait wait = new WebDriverWait(driver,20);
        driver.findElement(transfer).click();
        wait.until(ExpectedConditions.titleIs("Transfer"));
    }
    public void goOperationHistory(){
        WebDriverWait wait = new WebDriverWait(driver,20);
        driver.findElement(oHistory).click();
        wait.until(ExpectedConditions.titleIs("Operations"));
    }
    public AuthorizedUserPage(WebDriver driver) {
        super(driver);
    }
}
