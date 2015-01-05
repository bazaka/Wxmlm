package FunctionalTests.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

// * Created for W-xmlm by Fill on 05.01.2015.
public class PackageCartPage extends AuthorizedUserPage {
    public PackageCartPage(WebDriver driver) {
        super(driver);
    }
    public String getRequiredFeeToIncrease() {
        return driver.findElement(By.xpath("//td[text()=' Required fee to increase: ']/../td[@class='text-right']")).getText();
    }
    public String getPrice() {
        return driver.findElement(By.xpath("//td[text()=' Price:']/../td[@class='text-right']")).getText();
    }
}
