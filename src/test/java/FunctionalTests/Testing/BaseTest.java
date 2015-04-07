package FunctionalTests.Testing;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

/**
 * Created by User on 2/11/2015.
 */
public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    @Before
    public void preCondition(){
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 15);
        driver.manage().window().maximize();

    }
    @After
    public void postCondition(){
/*
        if(driver!=null)
            driver.quit();
*/
    }

}
