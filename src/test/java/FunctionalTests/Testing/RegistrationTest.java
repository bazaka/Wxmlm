package FunctionalTests.Testing;

import FunctionalTests.Testing.SingleTest.RegistrationSingleTest;
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
 * Created by User on 12/1/2014.
 */
public class RegistrationTest{
    public static WebDriver driver;
    @Test
    public void registrationTest() throws IOException, MessagingException {

        RegistrationSingleTest registrationSingleTest = new RegistrationSingleTest();
        TestUser[] testUser = new CsvUsersReader().getUsersFromFile("src/Users.csv");

        // Проверяем, что массив пользователей не пуст
        assertNotEquals("Нет пользователей для тестирования. Закрываюсь", testUser.length, 0);
        System.out.println("Количество пользователей для тестирования: " + testUser.length);

        // Цикл, выполняющий тесты для пользователей, указанных в конфиге
        for (int i = 0; i < testUser.length; i++) {
            if(RegionMatch.IsStringRegionMatch(testUser[i].getUseInTest(), "_RegistrationTest("))
            {
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                registrationSingleTest.registrationSingleTest(testUser[i]);
                if(driver!=null)
                    driver.quit();
            }
        }
        System.out.println("RegistraionTest успешно пройден");



    }
}
