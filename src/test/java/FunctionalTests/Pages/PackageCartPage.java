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
    public static final By requiredFeeToIncreaseElement = By.xpath("//td[text()=' Required fee to increase: ']/../td[@class='text-right']");
    public static final By price = By.xpath("//td[text()=' Price:']/../td[@class='text-right']");
    public static final By packageNameElement = By.xpath("//div[@class='content']//div[@class='header']/h3/strong");

    public String getRequiredFeeToIncrease() {
        return driver.findElement(requiredFeeToIncreaseElement).getText();
    }
    public String getPrice() {
        return driver.findElement(price).getText();
    }
    public String getPaymentAmount() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(buyButtonElement));
        String paymentAmount = getPrice();
        if (Element.isElementExists(driver, requiredFeeToIncreaseElement))
        {
            paymentAmount = getRequiredFeeToIncrease();
        }
        return paymentAmount;
    }
    public void clickBuyButton() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(buyButtonElement));
        }  catch (Exception e) {
            System.out.println("There is no buy button on cart");
            e.printStackTrace();
        }
        driver.findElement(buyButtonElement).click();
    }
}
