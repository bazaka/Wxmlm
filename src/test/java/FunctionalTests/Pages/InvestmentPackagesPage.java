package FunctionalTests.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// * Created for W-xmlm by Fill on 05.01.2015.
public class InvestmentPackagesPage extends ProductsFamilyPage {
    public InvestmentPackagesPage(WebDriver driver) {super(driver);}
    public static final By firstActiveBuyButton = By.xpath("//a[contains(@href, '/products/invest/buy/')]");

    public void clickFirstActiveBuyButton() {
        driver.findElement(firstActiveBuyButton).click();
    }
}
