package ApiTests;

import ApiTests.LoginFree.GetApplicationInfo;
import ApiTests.LoginFree.GetModuleContent;
import ApiTests.LoginFree.GetModuleInfo;
import UsedByAll.Config;
import UsedByAll.CsvUsersReader;
import UsedByAll.RegionMatch;
import UsedByAll.TestUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(value = Parameterized.class)
public class LoginFreeAPITestToRun {
    private TestUser testUser;

    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_LoginFreeAPITest(");
    }

    public LoginFreeAPITestToRun(TestUser user){
        this.testUser = user;
    }
    @Test
    public void runLoginFreeAPITests() throws Exception {
        Config config = Config.getConfig();
        String siteUrl = config.getProtocol() + config.getScheme(); // Урл проверяемого сайта
        boolean isComplete = true;
        // GET app info test run
        if (!(new GetApplicationInfo().testGetApplicationInfo(siteUrl))) {
            System.out.println("Проверка API GET app info НЕ пройдена");
            isComplete = false;
        }
        // GET module info test run
        if (new GetModuleInfo().testGetModuleInfo(siteUrl, testUser)) {
            System.out.println("Проверка API GET module info пройдена");
        } else {
            System.out.println("Проверка API GET module info НЕ пройдена");
            isComplete = false;
        }
        // GET module content test run
        if (new GetModuleContent().testGetModuleContent(siteUrl, testUser)) {
            System.out.println("Проверка API GET module content пройдена");
        } else {
            System.out.println("Проверка API GET module content НЕ пройдена");
            isComplete = false;
        }
        assertTrue("BackendAPITests failed", isComplete);
    }
}