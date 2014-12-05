package FunctionalTests.Testing;

import FunctionalTests.Pages.LogoutPage;
import FunctionalTests.Pages.LoginPage;
import FunctionalTests.Testing.SingleTest.LoginLogoutSingleTest;
import UsedByAll.CsvUsersReader;
import UsedByAll.RegionMatch;
import UsedByAll.TestUser;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

/**
 * Created by User on 12/2/2014.
 */
public class LogInLogOutTest extends BaseTest{
    @Test
    public void logInLogOutTest(){
        LoginPage loginPage = new LoginPage(driver);
        LogoutPage logoutPage = new LogoutPage(driver);
        TestUser[] testUser = new CsvUsersReader().getUsersFromFile("src/Users.csv");
        LoginLogoutSingleTest loginLogoutSingleTest = new LoginLogoutSingleTest();

        assertNotEquals("Нет пользователей для тестирования", testUser.length, 0);
        //System.out.println("Количество пользователей для тестирования: "+ testUser.length);
        for(int i=0; i<testUser.length; i++) {
            if (RegionMatch.IsStringRegionMatch(testUser[i].getUseInTest(), "_LoginTest(")) {
                loginLogoutSingleTest.loginLogoutSingleTest(testUser[i]);

            }
        }
        System.out.println("Login/Logout test успешно пройден");
    }
}
