package ApiTests;

import ApiTests.LoginFree.GetApplicationInfo;
import ApiTests.LoginFree.GetModuleContent;
import ApiTests.LoginFree.GetModuleInfo;
import UsedByAll.Config;
import UsedByAll.CsvUsersReader;
import UsedByAll.RegionMatch;
import UsedByAll.TestUser;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LoginFreeAPITest {
    @Test
    public void runLoginFreeAPITests() throws Exception {
        Config config = Config.getConfig();
        String siteUrl = config.getProtocol() + config.getScheme(); // Урл проверяемого сайта
        TestUser[] testUser = new CsvUsersReader().getUsersFromFile("src/Users.csv");
        boolean isComplete = true;
        for (TestUser aTestUser : testUser) {
            if (RegionMatch.IsStringRegionMatch(aTestUser.getUseInTest(), "_LoginFreeAPITest(")) {
                // GET app info test run
                if (!(new GetApplicationInfo().testGetApplicationInfo(siteUrl))) {
                    System.out.println("Проверка API GET app info НЕ пройдена");
                    isComplete = false;
                }
                // GET module info test run
                if (new GetModuleInfo().testGetModuleInfo(siteUrl, aTestUser)) {
                    System.out.println("Проверка API GET module info пройдена");
                } else {
                    System.out.println("Проверка API GET module info НЕ пройдена");
                    isComplete = false;
                }
                // GET module content test run
                if (new GetModuleContent().testGetModuleContent(siteUrl, aTestUser)) {
                    System.out.println("Проверка API GET module content пройдена");
                } else {
                    System.out.println("Проверка API GET module content НЕ пройдена");
                    isComplete = false;
                }
                
            }
            assertTrue("BackendAPITests failed", isComplete);
        }
    }
}