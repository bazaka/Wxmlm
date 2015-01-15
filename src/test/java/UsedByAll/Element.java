package UsedByAll;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.NoSuchElementException;

// * Created for W-xmlm by Fill on 12.01.2015.
public class Element {
    public static boolean isElementExists(WebDriver driver, By element)
    {
        try {
            driver.findElement(element);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }
}
