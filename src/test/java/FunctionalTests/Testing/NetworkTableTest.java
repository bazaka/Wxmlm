package FunctionalTests.Testing;

import FunctionalTests.Pages.AuthorizedUserPage;
import FunctionalTests.Pages.LogInPage;
import FunctionalTests.Pages.NetworkPage;
import UsedByAll.CsvUsersReader;
import UsedByAll.TestUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by User on 6/23/2015.
 */
@RunWith(value = Parameterized.class)
public class NetworkTableTest extends BaseTest {
    String email;
    String password;

    @Parameterized.Parameters
    public static Collection testData(){return CsvUsersReader.getDataForTest("_NetworkTableTest(");}

    public NetworkTableTest(TestUser testUser){
        this.email=testUser.getEmail();
        this.password=testUser.getPassword1();
    }
    @Test
    public void networkTable(){
        LogInPage logInPage = new LogInPage(driver, wait);
        logInPage.open();
        logInPage.goLogin(email, password);
        assertEquals(logInPage.getTitle(), "KairosNet");
        AuthorizedUserPage userPage = new AuthorizedUserPage(driver, wait);
        userPage.goNetwork();
        NetworkPage networkPage = new NetworkPage(driver, wait);
        networkPage.waitForPageLoad();
        networkPage.goTableView();

       /* assertTrue("Некорректный поиск по email", networkPage.searchByEmail());
        networkPage.resetSearchResult();*/
       /* assertTrue("Некорректный поиск по name", networkPage.searchByName());
        networkPage.resetSearchResult();*/
        assertTrue("Некорректный поиск по career", networkPage.searchByCareer());
    }
}
