package FunctionalTests.Testing;

import FunctionalTests.Testing.SingleTest.ProfileSingleTest;
import UsedByAll.CsvUsersReader;
import UsedByAll.RegionMatch;
import UsedByAll.TestUser;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertNotEquals;

/**
 * Created by User on 12/22/2014.
 */
public class ProfileTest {
    public static WebDriver driver;
    @Test
    public void profileTest(){
        TestUser[] testUser = new CsvUsersReader().getUsersFromFile("src/Users.csv");
        ProfileSingleTest profileSingleTest = new ProfileSingleTest();
        assertNotEquals("Нет пользователей для тестирования", testUser.length, 0);
        for(TestUser aTestUser : testUser){
            if(RegionMatch.IsStringRegionMatch(aTestUser.getUseInTest(), "_ProfileTest(")){
                driver = new FirefoxDriver();
                driver.manage().window().maximize();

                if(driver!=null)
                    driver.quit();
            }
        }
        System.out.println("Profile Test успешно пройден");
    }
    @After
    public void driverClose(WebDriver driver){
        if(driver!=null)
            driver.quit();
    }
}
