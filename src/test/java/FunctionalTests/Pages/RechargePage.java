package FunctionalTests.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by User on 3/24/2015.
 */
public class RechargePage extends MoneyFamilyPage{
    public RechargePage(WebDriver driver, WebDriverWait wait){super(driver, wait);}
    public static final By swift = By.xpath("//a[contains(text(), 'Swift')]");

    public void goSwift(){
        driver.findElement(swift).click();
    }
}
