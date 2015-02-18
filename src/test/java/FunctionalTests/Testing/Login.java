package FunctionalTests.Testing;

import FunctionalTests.Pages.LogInPage;
import UsedByAll.CsvUsersReader;
import UsedByAll.TestUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Collection;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by User on 2/11/2015.
 */
@RunWith(value = Parameterized.class)
public class Login extends BaseClass {
    //private TestUser testUser;
    private String email;
    private String password;

    @Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_LogInTest(");
    }

    public Login(TestUser testUser /*String email, String password*/){
        this.email=testUser.getEmail();
        this.password=testUser.getPassword1();
    }

    @Test
    public void userLoginNewTest(){
        LogInPage logInPage = new LogInPage(driver, wait);
        //TestUser[] testUser = new CsvUsersReader().getUsersFromFile("src/Users.csv");
        logInPage.open();
        assertTrue("Page not opened", logInPage.isOpened());
        logInPage.goLogin(email, password);
        assertEquals(logInPage.getTitle(), "KairosNet");

        System.out.println("LoginTest passed successfully");
    }


}
