package FunctionalTests.Pages;

import UsedByAll.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// * Created for W-xmlm by Fill on 05.01.2015.
public class PackageCartPage extends ProductsFamilyPage {
    public PackageCartPage(WebDriver driver, WebDriverWait wait) {super(driver, wait);}
    public static final By buyButtonElement = By.xpath("//a[contains(text(), ' Buy')]");
    public static final By requiredFeeToIncreaseElement = By.xpath("//td[text()=' Required fee to increase: ']");
    public static final By packageNameElement = By.xpath("//div[@class='content']//div[@class='header']/h3/strong");

    public String getRequiredFeeToIncrease() {
        return driver.findElement(By.xpath("//td[text()=' Required fee to increase: ']/../td[@class='text-right']")).getText();
    }
    public String getPrice() {
        return driver.findElement(By.xpath("//td[text()=' Price:']/../td[@class='text-right']")).getText();
    }
    public String getPaymentAmount() {
        PackageCartPage packageCartPage = new PackageCartPage(driver, wait);
        wait.until(ExpectedConditions.visibilityOfElementLocated(PackageCartPage.buyButtonElement));
        String paymentAmount = packageCartPage.getPrice();
        if (Element.isElementExists(driver, PackageCartPage.requiredFeeToIncreaseElement))
        {
            paymentAmount = packageCartPage.getRequiredFeeToIncrease();
        }
        return paymentAmount;
    }
    public void clickBuyButton() {
        driver.findElement(buyButtonElement).click();
    }
}
