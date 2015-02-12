package FunctionalTests.Testing;

import FunctionalTests.Pages.LoginNewPage;
import UsedByAll.CsvUsersReader;
import UsedByAll.RegionMatch;
import UsedByAll.TestUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by User on 2/11/2015.
 */
@RunWith(value = Parameterized.class)
public class LoginNewTest extends BaseNewTest {
    //private TestUser testUser;
    private String email;
    private String password;

    @Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_LogInTest(");
    }

    public LoginNewTest(TestUser testUser /*String email, String password*/){
        this.email=testUser.getEmail();
        this.password=testUser.getPassword1();
    }

    @Test
    public void userLoginNewTest(){
        LoginNewPage loginNewPage = new LoginNewPage(driver);
        //TestUser[] testUser = new CsvUsersReader().getUsersFromFile("src/Users.csv");
        loginNewPage.open();
        assertTrue("Page not opened", loginNewPage.isOpened());
        loginNewPage.goLogin(email, password);
        assertEquals(loginNewPage.getTitle(), "KairosNet");

        System.out.println("LoginTest passed successfully");
    }


}
