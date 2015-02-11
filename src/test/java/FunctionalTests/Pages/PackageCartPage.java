package FunctionalTests.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// * Created for W-xmlm by Fill on 05.01.2015.
public class PackageCartPage extends ProductsFamilyPage {
    public static final By buyButtonElement = By.xpath("//a[contains(text(), ' Buy')]");
    public static final By requiredFeeToIncreaseElement = By.xpath("//td[text()=' Required fee to increase: ']");
    public static final By packageNameElement = By.xpath("//div[@class='content']//div[@class='header']/h3/strong");

    public static String getRequiredFeeToIncrease(WebDriver driver) {
        return driver.findElement(By.xpath("//td[text()=' Required fee to increase: ']/../td[@class='text-right']")).getText();
    }
    public static String getPrice(WebDriver driver) {
        return driver.findElement(By.xpath("//td[text()=' Price:']/../td[@class='text-right']")).getText();
    }

    public PackageCartPage(WebDriver driver){super(driver);}
}
