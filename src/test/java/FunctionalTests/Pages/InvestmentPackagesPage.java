package FunctionalTests.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// * Created for W-xmlm by Fill on 05.01.2015.
public class InvestmentPackagesPage extends AuthorizedUserPage {
    public static final By firstActiveBuyButton = By.linkText("/products/invest/buy/");

    public InvestmentPackagesPage(WebDriver driver) {
        super(driver);
        url = url + "products/invest/";
    }
}
