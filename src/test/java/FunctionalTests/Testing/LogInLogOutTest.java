package FunctionalTests.Testing;

import FunctionalTests.Testing.SingleTest.LoginLogoutSingleTest;
import UsedByAll.CsvUsersReader;
import UsedByAll.RegionMatch;
import UsedByAll.TestUser;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertNotEquals;

/**
 * Created by User on 12/2/2014. Login/Logout Test
 */
public class LogInLogOutTest{
    public static WebDriver driver;
    @Test
    public void logInLogOutTest(){
        TestUser[] testUser = new CsvUsersReader().getUsersFromFile("src/Users.csv");
        LoginLogoutSingleTest loginLogoutSingleTest = new LoginLogoutSingleTest();

        assertNotEquals("Нет пользователей для тестирования", testUser.length, 0);
        //System.out.println("Количество пользователей для тестирования: "+ testUser.length);
        for (TestUser aTestUser : testUser) {
            if (RegionMatch.IsStringRegionMatch(aTestUser.getUseInTest(), "_LogInTest(")) {
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                loginLogoutSingleTest.loginLogoutSingleTest(aTestUser);
                if(driver!=null)
                    driver.quit();

            }
        }
        System.out.println("Login/Logout test успешно пройден");
    }
}
