package FunctionalTests.Testing;

import FunctionalTests.Testing.SingleTest.ChangeMailSingleTest;
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
 * Created by User on 12/25/2014.
 */
public class ChangeMailTest {
    public static WebDriver driver;
    @Test
    public void changeMailTest(){
        ChangeMailSingleTest changeMailSingleTest = new ChangeMailSingleTest();
        TestUser[] testUser = new CsvUsersReader().getUsersFromFile("src/Users.csv");

        assertNotEquals("Нет пользователей для тестирования. Закрываюсь", testUser.length, 0);
        System.out.println("Количество пользователей для тестирования: "+ testUser.length);

        for (int i = 0; i < testUser.length; i++) {
            if(RegionMatch.IsStringRegionMatch(testUser[i].getUseInTest(), "_ChangeMailTest("))
            {
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                try {
                    changeMailSingleTest.changeMailSingleTest(testUser[i]);
                } catch (IOException e) {
                    e.printStackTrace();
                    driver.quit();
                } catch (MessagingException e) {
                    e.printStackTrace();
                    driver.quit();
                }
                if(driver!=null)
                    driver.quit();
            }
        }
        System.out.println("ChangeMail Test успешно пройден");
    }
}
