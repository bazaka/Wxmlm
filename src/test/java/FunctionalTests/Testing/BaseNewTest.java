package FunctionalTests.Testing;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by User on 2/11/2015.
 */
public class BaseNewTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    @Before
    public void preCondition(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver,10);
    }
    @After
    public void postCondition(){
        if(driver!=null)
            driver.quit();
    }
}