package FunctionalTests.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// * Created for W-xmlm by Fill on 05.01.2015.
public class AuthorizedUserPage {
    WebDriver driver;
    WebDriverWait wait;
    public AuthorizedUserPage(WebDriver driver, WebDriverWait wait) {this.driver = driver; this.wait = wait;}
    public static final By home = By.xpath("//div[@id='head-nav']//li[4]/a[@href='/']");
    public static final By money = By.xpath("//div[@id='head-nav']//li[8]/a[@href='/money/']");
    public static final By products = By.xpath("//div[@id='head-nav']//li[12]/a[@href='/products/']");
    public static final By net = By.xpath("//div[@id='head-nav']//li[@id='menu-net']/a[@href='/network/']");
    public static final By career = By.xpath("//div[@id='head-nav']//li[20]/a[@href='/career/']");
    public static final By profilePage = By.xpath("//img[@alt='Avatar']");
    public static final By userMenu = By.xpath("//li[@class='dropdown']/a[@class='dropdown-toggle']");
    public static final By mySettings = By.linkText("//a[text()='My Settings']");
    public static final By profile = By.linkText("//a[text()='Profile']");
    public static final By messages = By.linkText("//a[text()='Sign Out']");
    public static final By successMessage = By.xpath("//span[@class='gritter-title'][text()='Success!']");
    public static final By successMessage2 = By.xpath("//span[@class='gritter-title'][text()='Success']");

    public void goUserMenu(){
        driver.findElement(userMenu).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Profile']")));
    }
    public void goProducts(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(products));
        driver.findElement(products).click();
    }
    public void goMoney(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(money));
        driver.findElement(money).click();
    }
    public void waitForSuccessMessage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
    }
    public void waitForSuccessMessage2(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage2));
    }
    public void goNetwork(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(net));
        driver.findElement(net).click();
    }
}
