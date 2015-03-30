package UsedByAll;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.NoSuchElementException;

// * Created for W-xmlm by Fill on 30.03.2015.
public class ElementProperty {

    // Метод, проверяющий видимость элемента на странице
    public boolean isElementVisible(WebDriver driver, By element){
        if (isElementPresent(driver, element)) {
            return driver.findElement(element).isDisplayed();
        }
        return false;
    }

    // Метод, проверяющий наличие элемента на странице
    public boolean isElementPresent(WebDriver driver, By element) {
        try {
            driver.findElement(element);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
