package FunctionalTests;

import ApiTests.LoginFree.GetApplicationInfo;
import ApiTests.LoginFree.GetModuleInfo;
import UsedByAll.TestUser;

public class LoginFreeAPITest {
    public boolean runLoginFreeAPITests(String scheme, TestUser user) throws Exception {
        boolean isComplete = true;

        // GET app info test run
        if (new GetApplicationInfo().testGetApplicationInfo(scheme)) {
            System.out.println("Проверка API GET app info пройдена");
        }
        else {
            System.out.println("Проверка API GET app info НЕ пройдена");
            isComplete = false;
        }
        // GET module info test run
        if (new GetModuleInfo().testGetModuleInfo(scheme, user)) {
            System.out.println("Проверка API GET app info пройдена");
        }
        else {
            System.out.println("Проверка API GET app info НЕ пройдена");
            isComplete = false;
        }
        return isComplete;
    }
}