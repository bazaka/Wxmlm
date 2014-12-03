package FunctionalTests.Testing;

import FunctionalTests.Pages.GmailPage;
import FunctionalTests.Pages.RegistrationPage;
import UsedByAll.CsvUsersReader;
import UsedByAll.RegionMatch;
import UsedByAll.TestUser;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by User on 12/1/2014.
 */
public class RegistrationTest extends BaseTest {
    @Test
    public void registrationTest(){

        RegistrationPage registrationPage = new RegistrationPage(driver);
        GmailPage gmailPage = new GmailPage(driver);
        TestUser[] testUser = new CsvUsersReader().getUsersFromFile("src/Users.csv");

        // Проверяем, что массив пользователей не пуст
        assertNotEquals("Нет пользователей для тестирования. Закрываюсь", testUser.length, 0);
        System.out.println("Количество пользователей для тестирования: " + testUser.length);

        // Цикл, выполняющий тесты для пользователей, указанных в конфиге
        for (int i = 0; i < testUser.length; i++) {
            if(RegionMatch.IsStringRegionMatch(testUser[i].getUseInTest(), "_RegistrationTest("))
            {

                /*registrationPage.open();
                assertTrue("Page not opened", registrationPage.isOpened());

                */
                gmailPage.open();
                gmailPage.checkGmail(testUser[i]);

                String gmailTab = driver.getWindowHandle();  // save gmail tab
                driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t"); // open new tab
                ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
                driver.switchTo().window(tabs.get(0));

                registrationPage.open();
                assertTrue("Page not opened", registrationPage.isOpened());
                registrationPage.openRegistration();
                registrationPage.firstStep(testUser[i]);
                registrationPage.secondStep(testUser[i]);
                assertTrue("Confirmation message not displayed", registrationPage.isError());

                driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.TAB);
                driver.switchTo().window(gmailTab);



                gmailPage.checkConfirmLetter(testUser[i], "To finish activating your account");


                assertEquals(gmailPage.confirmActivation(), "Congrats "+testUser[i].getEmail()+ ", your account is now activated.");
                System.out.println("Тест для "+testUser[i].getEmail()+ " успешно пройден");



            }
        }
        System.out.println("RegistraionTest успешно пройден");



    }
}
