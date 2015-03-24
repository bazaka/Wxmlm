package FunctionalTests.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

// * Created for W-xmlm by Fill on 12.01.2015.
public class ProductsFamilyPage extends AuthorizedUserPage {
    public ProductsFamilyPage(WebDriver driver, WebDriverWait wait) {super(driver, wait);}
    public static final By investmentPackagesItem = By.xpath("//a[contains(text(), '  Investment packages')]/..");
    public static final By itProductsItem = By.xpath("//a[contains(text(), '  IT products')]/..");
    public static final By servicesItem = By.xpath("//a[contains(text(), '  Services')]/..");
    public static final By trainingItem = By.xpath("//a[contains(text(), '  Training')]/..");
    public static final By purchasesItem = By.xpath("//span[contains(text(), 'Purchases')]/..");

    public void goToPurchases() {
        driver.findElement(purchasesItem).click();
    }
}
