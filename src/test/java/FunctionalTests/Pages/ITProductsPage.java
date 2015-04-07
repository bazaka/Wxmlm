package FunctionalTests.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// * Created for W-xmlm by Fill on 31.03.2015.
public class ITProductsPage extends ProductsFamilyPage {
    public ITProductsPage(WebDriver driver, WebDriverWait wait) {super(driver, wait);}
    public static final By pageHeader = By.xpath("//div[@class='header']/h3[contains(text(), 'IT Products')]");
    public static final By itProductsTable = By.xpath("//table[@class='table no-v-borders it-products__table']");
}
