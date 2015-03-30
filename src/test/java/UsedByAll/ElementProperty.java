package UsedByAll;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

// * Created for W-xmlm by Fill on 30.03.2015.
public class ElementProperty {

    // Метод, проверяющий видимость элемента на странице
    public boolean isElementVisible(WebDriver driver, By element){
        return driver.findElement(element).isDisplayed();
    }
}
