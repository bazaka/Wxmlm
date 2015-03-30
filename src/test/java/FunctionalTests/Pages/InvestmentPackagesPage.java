package FunctionalTests.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// * Created for W-xmlm by Fill on 05.01.2015.
public class InvestmentPackagesPage extends ProductsFamilyPage {
    public InvestmentPackagesPage(WebDriver driver, WebDriverWait wait) {super(driver, wait);}
    public static final By firstActiveBuyButton = By.xpath("//a[contains(@href, '/products/invest/buy/')]");
    public static final By pageHeader = By.xpath("//div[@class='header']/h3[contains(text(), 'Investment packages')]");
    public static final By investmentPackagesTable = By.xpath("//table[@class='table no-border products no-v-borders']");

    public void clickFirstActiveBuyButton() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(firstActiveBuyButton));
        } catch (Exception e) {
            System.out.println("There is no active buy button on investment packages page");
            e.printStackTrace();
        }
        driver.findElement(firstActiveBuyButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(PackageCartPage.buyButtonElement));
    }
}
