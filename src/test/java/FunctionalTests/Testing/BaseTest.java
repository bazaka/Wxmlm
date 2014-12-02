package FunctionalTests.Testing;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by User on 12/1/2014.
 */
public class BaseTest {
    public static WebDriver driver;


    @Before
    public void setUp(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

    }
    @After
    public void tearDown(){
        if(driver!=null)
            driver.quit();
    }
}
