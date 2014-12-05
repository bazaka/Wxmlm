package FunctionalTests.Testing.SingleTest;

import FunctionalTests.Pages.LogoutPage;
import FunctionalTests.Pages.LoginPage;
import FunctionalTests.Testing.LoginLogoutTest;
import UsedByAll.TestUser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by User on 12/5/2014.
 */
public class LoginLogoutSingleTest extends LoginLogoutTest {
    @Test
    public void loginLogoutSingleTest(TestUser testUser){
        LoginPage loginPage = new LoginPage(driver);
        LogoutPage logoutPage = new LogoutPage(driver);
        loginPage.open();
        assertTrue("Page not opened", loginPage.isOpened());

        loginPage.goLogin(testUser);
        assertEquals(loginPage.getTitle(), "KairosNet");

        logoutPage.logOut();
        assertEquals(loginPage.getTitle(), "Login");
        System.out.println("Тест для "+ testUser.getEmail() +" успешно пройден");

    }
}

