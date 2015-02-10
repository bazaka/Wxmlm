package FunctionalTests.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// * Created for W-xmlm by Fill on 05.01.2015.
public class InvestmentPackagesPage extends ProductsFamilyPage {
    public InvestmentPackagesPage(WebDriver driver, WebDriverWait wait) {super(driver, wait);}
    public static final By firstActiveBuyButton = By.xpath("//a[contains(@href, '/products/invest/buy/')]");

    public void clickFirstActiveBuyButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstActiveBuyButton));
        driver.findElement(firstActiveBuyButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(PackageCartPage.buyButtonElement));
    }
}
