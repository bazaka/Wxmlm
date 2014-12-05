package FunctionalTests;

import BackendApiTests.*;
import BackendApiTests.BackendGetPurchases;
import UsedByAll.TestUser;

public class BackendAPITest {
    public boolean runAPITests(String scheme, TestUser user) throws Exception {
        boolean isComplete = true;
        // GET API users test run
        if (new BackendGetUsers().testBackendGetUsers(scheme, user)) {
            System.out.println("Проверка API GET users пройдена");
        }
        else {
            System.out.println("Проверка API GET users НЕ пройдена");
            isComplete = false;
        }

        // GET API accounts test run
        if (new BackendGetAccounts().testBackendGetAccounts(scheme, user)) {
            System.out.println("Проверка API GET accounts пройдена");
        }
        else {
            System.out.println("Проверка API GET accounts НЕ пройдена");
            isComplete = false;
        }

        // PUT API accounts update test run
        if (new BackendPutAccountsUpdate().testBackendPutAccountsUpdate(scheme, user)) {
            System.out.println("Проверка API PUT accounts update пройдена");
        }
        else {
            System.out.println("Проверка API PUT accounts update НЕ пройдена");
            isComplete = false;
        }

        //GET API operations test run
        if (new BackendGetOperations().testBackendGetOperations(scheme, user)) {
            System.out.println("Проверка API GET operations пройдена");
        }
        else {
            System.out.println("Проверка API GET operations НЕ пройдена");
            isComplete = false;
        }

        // PUT API operations update test run
        if (new BackendPutOperationsUpdate().testBackendPutOperationsUpdate(scheme, user)) {
            System.out.println("Проверка API PUT operations update пройдена");
        }
        else {
            System.out.println("Проверка API PUT operations update НЕ пройдена");
            isComplete = false;
        }

        // PUT API operation insert test run
        if (new BackendPostOperationInsert().testBackendPostOperationInsert(scheme, user)) {
            System.out.println("Проверка API POST operation insert пройдена");
        }
        else {
            System.out.println("Проверка API POST operation insert НЕ пройдена");
            isComplete = false;
        }

        // GET API purchases test run
        if (new BackendGetPurchases().testBackendGetPurchases(scheme, user)) {
            System.out.println("Проверка API GET Purchases пройдена");
        }
        else {
            System.out.println("Проверка API GET Purchases НЕ пройдена");
            isComplete = false;
        }

        // GET API withdraws test run
        if (new BackendGetWithdraws().testBackendGetWithdraws(scheme, user)) {
            System.out.println("Проверка API GET withdraws пройдена");
        }
        else {
            System.out.println("Проверка API GET withdraws НЕ пройдена");
            isComplete = false;
        }

        // PUT API withdraws update test run
        if (new BackendPutWithdrawsUpdate().testBackendPutWithdrawsUpdate(scheme, user)) {
            System.out.println("Проверка API PUT withdraws update пройдена");
        }
        else {
            System.out.println("Проверка API PUT withdraws update НЕ пройдена");
            isComplete = false;
        }

        // POST API withdraw insert test run
        if (new BackendPostWithdrawInsert().testBackendPostWithdrawInsert(scheme, user)) {
            System.out.println("Проверка API POST withdraw insert пройдена");
        }
        else {
            System.out.println("Проверка API POST withdraw insert НЕ пройдена");
            isComplete = false;
        }

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
