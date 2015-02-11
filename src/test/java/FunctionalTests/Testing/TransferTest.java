/*
package FunctionalTests.Testing;

import FunctionalTests.Testing.SingleTest.TransferSingleTest;
import UsedByAll.CsvUsersReader;
import UsedByAll.RegionMatch;
import UsedByAll.TestUser;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Random;

import static org.junit.Assert.assertNotEquals;

*/
/**
 * Created by User on 1/5/2015.
 *//*

public class TransferTest {
    public static WebDriver driver;
    @Test
    public void transferTest(){


        TransferSingleTest transferSingleTest = new TransferSingleTest();
        TestUser[] testUser = new CsvUsersReader().getUsersFromFile("src/Users.csv");
        assertNotEquals("Нет пользователей для нестирования", testUser.length, 0);
        for(int i=0; i<testUser.length; i++){
            if(RegionMatch.IsStringRegionMatch(testUser[i].getUseInTest(), "_TransferTest(")){
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                transferSingleTest.transferSingleTest(testUser[i]);
                if(driver!=null)
                    driver.quit();
            }
        }
        System.out.println("Change Transfer Test успешно пройден");
    }
}
*/
