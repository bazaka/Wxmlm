package FunctionalTests.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// * Created for W-xmlm by Fill on 12.01.2015.
public class PurchasesPage extends ProductsFamilyPage {
    public PurchasesPage(WebDriver driver, WebDriverWait wait) {super(driver, wait);}
    public static final By appDownloadButton = By.xpath("//a[@href='/products/installer/main/downloader/']");
    public static final By lastPurchasePriceCell = By.xpath("//table[@id='datatable-purchases']/tbody/tr[1]/td[3]");

    public String getLastPurchasePrice(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastPurchasePriceCell));
        return driver.findElement(lastPurchasePriceCell).getText();
    }
}
