package FunctionalTests.Testing;

import FunctionalTests.Testing.SingleTest.LoginSingleTest;
import UsedByAll.CsvUsersReader;
import UsedByAll.RegionMatch;
import UsedByAll.TestUser;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertNotEquals;
/**
 * Created by User on 12/1/2014.
 */
public class LogInTest{
    public static WebDriver driver;
    @Test
    public void userLoginTest(){

        TestUser[] testUser = new CsvUsersReader().getUsersFromFile("src/Users.csv");
        LoginSingleTest loginSingleTest = new LoginSingleTest();

        assertNotEquals("Нет пользователей для тестирования", testUser.length, 0);
        //System.out.println("Количество пользователей для тестирования: "+ testUser.length);

        for(int i=0; i<testUser.length; i++) {
            if (RegionMatch.IsStringRegionMatch(testUser[i].getUseInTest(), "_LogInTest(")) {
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                loginSingleTest.loginSingleTest(testUser[i]);
                if(driver!=null)
                    driver.quit();

            }
        }
        System.out.println("LoginTest успешно пройден");
    }


}
