package FunctionalTests.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

/**
 * Created by User on 3/24/2015.
 */
public class SwiftPage extends RechargePage {
    public SwiftPage(WebDriver driver, WebDriverWait wait){super(driver, wait);}
    public static final By summ = By.xpath("//div[@id='Swift']//input[@name='amount']");
    public static final By createInvoice = By.xpath("//div[@id='Swift']//button[text()='Create invoice']");

    public void enterAmount(String amount){
        wait.until(ExpectedConditions.visibilityOfElementLocated(summ));
        driver.findElement(summ).clear();
        driver.findElement(summ).click();
        driver.findElement(summ).sendKeys(amount);
    }
    public void createInvoice(){
        Set<String> oldWindowsSet = driver.getWindowHandles(); //набор текущих открытых окон
        driver.findElement(createInvoice).click();




    }
}
