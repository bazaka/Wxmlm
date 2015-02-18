package FunctionalTests.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by User on 12/2/2014.
 */
public class LogOutPage extends BasePage {

    private static final By dropdownToogle = By.xpath("//div[@class='container-fluid']//li[@class='dropdown']/a");
    private static final By dropdownMenu = By.className("dropdown-menu");
    private static final By singOut = By.xpath("//a[contains(@href, '/logout')]");

    public LogOutPage(WebDriver driver, WebDriverWait wait){
        super(driver, wait);
    }
    public void logOut(){

        wait.until(ExpectedConditions.presenceOfElementLocated(dropdownToogle));
        driver.findElement(dropdownToogle).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownMenu));
        //driver.findElement(dropdownMenu).click();

        //wait.until(ExpectedConditions.visibilityOfElementLocated(singOut));
        driver.findElement(singOut).click();
        wait.until(ExpectedConditions.titleIs("Login"));


    }

}
