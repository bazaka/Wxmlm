package FunctionalTests.Testing;

import FunctionalTests.Pages.LogOutPage;
import FunctionalTests.Pages.LogInPage;
import UsedByAll.CsvUsersReader;
import UsedByAll.TestUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by User on 12/2/2014. Login/Logout Test
 */
@RunWith(value = Parameterized.class)
public class LoginLogoutTestToRun extends BaseTest {
    private String email;
    private String password;


    @Parameters
    public static Collection testData(){return CsvUsersReader.getDataForTest("_LoginTest(");}

    public LoginLogoutTestToRun(TestUser testUser){
        this.email=testUser.getEmail();
        this.password=testUser.getPassword1();
    }


    @Test
    public void loginLogoutTestToRun(){


        LogInPage logInPage = new LogInPage(driver, wait);
        LogOutPage logOutPage = new LogOutPage(driver, wait);
        logInPage.open();
        assertTrue("Page not opened", logInPage.isOpened());

        logInPage.goLogin(email, password);
        assertEquals(logInPage.getTitle(), "KairosNet");

        logOutPage.logOut();
        assertEquals(logInPage.getTitle(), "Login");
        System.out.println("Тест для "+ email +" успешно пройден");
        System.out.println("Login/Logout test успешно пройден");
    }
}
