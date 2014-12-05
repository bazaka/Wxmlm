package FunctionalTests.Testing.SingleTest;

import FunctionalTests.Pages.LogOutPage;
import FunctionalTests.Pages.LogInPage;
import FunctionalTests.Testing.LogInLogOutTest;
import UsedByAll.TestUser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by User on 12/5/2014.
 */
public class LoginLogoutSingleTest extends LogInLogOutTest {
    @Test
    public void loginLogoutSingleTest(TestUser testUser){
        LogInPage loginPage = new LogInPage(driver);
        LogOutPage logoutPage = new LogOutPage(driver);
        loginPage.open();
        assertTrue("Page not opened", loginPage.isOpened());

        loginPage.goLogin(testUser);
        assertEquals(loginPage.getTitle(), "KairosNet");

        logoutPage.logOut();
        assertEquals(loginPage.getTitle(), "Login");
        System.out.println("Тест для "+ testUser.getEmail() +" успешно пройден");

    }
}

