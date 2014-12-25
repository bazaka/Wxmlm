package FunctionalTests.Testing.SingleTest;

import FunctionalTests.Pages.LogInPage;
import FunctionalTests.Testing.LogInTest;
import UsedByAll.TestUser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by User on 12/5/2014.
 */
public class LoginSingleTest extends LogInTest {
    @Test
    public void loginSingleTest(TestUser testUser){
        LogInPage loginPage = new LogInPage(driver);
        //TestUser[] testUser = new CsvUsersReader().getUsersFromFile("src/Users.csv");
        loginPage.open();
        assertTrue("Page not opened", loginPage.isOpened());

        loginPage.goLogin(testUser);
        assertEquals(loginPage.getTitle(), "KairosNet");

    }
}
