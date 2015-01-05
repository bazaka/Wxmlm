package FunctionalTests.Testing;

import FunctionalTests.Testing.SingleTest.ChangeProfileDataSingleTest;
import UsedByAll.*;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertNotEquals;

/**
 * Created by User on 12/26/2014.
 */
public class ChangeProfileDataTest {
    public static WebDriver driver;
    @Test
    public void changeProfileDataTest(){
        ChangeProfileDataSingleTest changeProfileDataSingleTest = new ChangeProfileDataSingleTest();
        TestUser[] testUser = new CsvUsersReader().getUsersFromFile("src/Users.csv");
        ProfileData[] profileDataCsv = new CsvProfileDataReader().getProfileDataFromFile("src/ProfileData.csv");

        assertNotEquals("Нет пользователей для тестирования", testUser.length, 0);
        for(int i=0; i<testUser.length; i++){
            if(RegionMatch.IsStringRegionMatch(testUser[i].getUseInTest(), "_ChangeProfileDataTest(")){
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                changeProfileDataSingleTest.changeProfileDataSingleTest(testUser[i], profileDataCsv[0]);
                if(driver!=null)
                    driver.quit();
            }
        }
        System.out.println("Change Profile Data Test успешно пройден");
    }
}
