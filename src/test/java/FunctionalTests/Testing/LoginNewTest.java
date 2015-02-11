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
import java.util.Collection;
import java.util.List;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by User on 2/11/2015.
 */
@RunWith(value = Parameterized.class)
public class LoginNewTest extends BaseNewTest {

    private String email;
    private String password;

    @Parameters
    public static Collection testData() {
        TestUser[] testUser = new CsvUsersReader().getUsersFromFile("src/Users.csv");
        List<TestUser> testUsers = new ArrayList<TestUser>();
        for (int i = 0; i < testUser.length; i++) {
            if (RegionMatch.IsStringRegionMatch(testUser[i].getUseInTest(), "_LogInTest(")) {
                testUsers.add(i, testUser[i]);
            }
        }
        return testUsers;
    }
    public LoginNewTest(TestUser testUser){
        email = testUser.getEmail();
        password = testUser.getPassword1();
    }

    @Test
    public void userLoginNewTest(){
        LoginNewPage loginNewPage = new LoginNewPage(driver);
        //TestUser[] testUser = new CsvUsersReader().getUsersFromFile("src/Users.csv");
        loginNewPage.open();
        assertTrue("Page not opened", loginNewPage.isOpened());

        loginNewPage.goLogin(email, password);
        assertEquals(loginNewPage.getTitle(), "KairosNet");




        System.out.println("LoginTest успешно пройден");
    }


}
