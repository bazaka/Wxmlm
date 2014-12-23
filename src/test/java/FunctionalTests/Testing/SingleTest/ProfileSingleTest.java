package FunctionalTests.Testing.SingleTest;

import FunctionalTests.Pages.LogInPage;
import FunctionalTests.Testing.ProfileTest;
import UsedByAll.TestUser;
import org.junit.Test;

/**
 * Created by User on 12/22/2014.
 */
public class ProfileSingleTest extends ProfileTest {
    @Test
    public void profileSingleTest(TestUser testUser){
        LogInPage loginPage = new LogInPage(driver);
    }
}
