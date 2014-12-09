package FunctionalTests.Testing;

import FunctionalTests.Testing.SingleTest.RecoverySingleTest;
import UsedByAll.CsvUsersReader;
import UsedByAll.RegionMatch;
import UsedByAll.TestUser;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import javax.mail.MessagingException;
import java.io.IOException;

import static org.junit.Assert.assertNotEquals;

/**
 * Created by User on 12/8/2014.
 */
public class RecoveryTest /*extends BaseTest */{
    public static WebDriver driver;

    @Test
    public void recoveryTest() throws IOException, MessagingException {
        System.out.println("Начинаю инициализацию RecoveryTest");
        RecoverySingleTest recoverySingleTest = new RecoverySingleTest();
        TestUser[] testUser = new CsvUsersReader().getUsersFromFile("src/Users.csv");
        assertNotEquals("Нет пользователей для тестирования. Закрываюсь", testUser.length, 0);
        System.out.println("Начинаю тестирование");
        for (int i = 0; i < testUser.length; i++) {
            if(RegionMatch.IsStringRegionMatch(testUser[i].getUseInTest(), "_RecoveryTest("))
            {
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                recoverySingleTest.recoverySingleTest(testUser[i]);
                if(driver!=null)
                    driver.quit();

            }
        }



        System.out.println("RecoveryTest успешно пройден");
    }
}
