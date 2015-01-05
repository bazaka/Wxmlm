package FunctionalTests.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// * Created for W-xmlm by Fill on 05.01.2015.
public class AuthorizedUserPage extends BasePage {
    private static final By home = By.xpath("//div[@id='head-nav']//a[@href='/']");
    private static final By money = By.xpath("//div[@id='head-nav']//a[@href='/money/']");
    private static final By products = By.xpath("//div[@id='head-nav']//a[@href='/products/']");
    private static final By net = By.xpath("//div[@id='head-nav']//a[@href='/network/']");
    private static final By career = By.xpath("//div[@id='head-nav']//a[@href='/career/']");
    private static final By profilePage = By.xpath("//img[@alt='Avatar']");
    private static final By userMenu = By.xpath("//li[@class='dropdown']/a[@class='dropdown-toggle']");
    private static final By mySettings = By.linkText("//a[text()='My Settings']");
    private static final By profile = By.linkText("//a[text()='Profile']");
    private static final By messages = By.linkText("//a[text()='Sign Out']");
    private static final By successMessage = By.xpath("//div[@class='gritter-item']//span[text()='Success!']");

    public void goUserMenu(){
        WebDriverWait wait = new WebDriverWait(driver,20);
        driver.findElement(userMenu).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Profile']")));
    }

    public AuthorizedUserPage(WebDriver driver) {
        super(driver);
    }
}
