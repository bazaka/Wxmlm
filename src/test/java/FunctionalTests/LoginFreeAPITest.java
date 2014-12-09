package FunctionalTests;

import ApiTests.LoginFree.GetApplicationInfo;
import UsedByAll.TestUser;

public class LoginFreeAPITest {
    public boolean runLoginFreeAPITests(String scheme) throws Exception {
        boolean isComplete = true;

        // GET app info test run
        if (new GetApplicationInfo().testGetApplicationInfo(scheme)) {
            System.out.println("Проверка API GET app info пройдена");
        }
        else {
            System.out.println("Проверка API GET app info НЕ пройдена");
            isComplete = false;
        }

        return isComplete;
    }
}