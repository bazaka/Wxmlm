package FunctionalTests.Testing;

import FunctionalTests.Pages.LogOutPage;
import FunctionalTests.Pages.LogInPage;
import UsedByAll.CsvUsersReader;
import UsedByAll.RegionMatch;
import UsedByAll.TestUser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by User on 12/2/2014.
 */
public class LogInLogOutTest extends BaseTest{
    @Test
    public void logInLogOutTest(){
        LogInPage logInPage = new LogInPage(driver);
        LogOutPage logOutPage = new LogOutPage(driver);
        TestUser[] testUser = new CsvUsersReader().getUsersFromFile("src/Users.csv");

        assertNotEquals("Нет пользователей для тестирования", testUser.length, 0);
        System.out.println("Количество пользователей для тестирования: "+ testUser.length);
        for(int i=0; i<testUser.length; i++) {
            if (RegionMatch.IsStringRegionMatch(testUser[i].getUseInTest(), "_LogInTest(")) {
                logInPage.open();
                assertTrue("Page not opened", logInPage.isOpened());

                logInPage.goLogin(testUser[i]);
                assertEquals(logInPage.getTitle(), "KairosNet");

                logOutPage.logOut();
                assertEquals(logInPage.getTitle(), "Login");
                System.out.println("Тест для "+ testUser[i].getEmail() +"успешно пройден");

            }
        }
        System.out.println("Login/Logout test успешно пройден");
    }
}
