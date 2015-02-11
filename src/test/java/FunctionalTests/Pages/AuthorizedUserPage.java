package FunctionalTests.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// * Created for W-xmlm by Fill on 05.01.2015.
public class AuthorizedUserPage extends BasePage{
    private static final By home = By.xpath("//div[@id='head-nav']//li[4]/a[@href='/']");
    private static final By money =  By.xpath("//div[@id='head-nav']//li[@id='menu-money']/a/div");
    public static final By products = By.xpath("//div[@id='head-nav']//li[12]/a[@href='/products/']");
    private static final By net = By.xpath("//div[@id='head-nav']//li[16]/a[@href='/network/']");
    private static final By career = By.xpath("//div[@id='head-nav']//li[20]/a[@href='/career/']");

/*
    public static final By home = By.xpath("//div[@id='head-nav']//li[@id='menu-home']/a[@href='/']");
    public static final By money = By.xpath("//div[@id='head-nav']//li[@id='menu-money']/a[@href='/money/']");
    public static final By products = By.xpath("//div[@id='head-nav']//li[@id='menu-products']/a[@href='/products/']");
    public static final By net = By.xpath("//div[@id='head-nav']//li[@id='menu-net']/a[@href='/network/']");
    public static final By career = By.xpath("//div[@id='head-nav']//li[@id='menu-career']/a[@href='/career/']");
  */
    public static final By transfer = By.xpath("//div[@class='col-md-12']//a[@href='/money/transfer/']");


    public static final By profilePage = By.xpath("//img[@alt='Avatar']");
    public static final By userMenu = By.xpath("//li[@class='dropdown']/a[@class='dropdown-toggle']");
    public static final By mySettings = By.linkText("//a[text()='My Settings']");
    public static final By profile = By.linkText("//a[text()='Profile']");
    public static final By messages = By.linkText("//a[text()='Sign Out']");
    public static final By successMessage = By.xpath("//div[@class='gritter-item']//span[text()='Success!']");

    public AuthorizedUserPage(WebDriver driver){super(driver);}

    public void goUserMenu(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver,20);
        driver.findElement(userMenu).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Profile']")));
    }
    public void goMoney(){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.findElement(money).click();
        wait.until(ExpectedConditions.titleIs("Accounts"));
    }

}
