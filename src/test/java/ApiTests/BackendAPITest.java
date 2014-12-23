package ApiTests;

import ApiTests.Backend.*;
import UsedByAll.TestUser;

public class BackendAPITest {
    public boolean runBackendAPITests(String scheme, TestUser user) throws Exception {
        boolean isComplete = true;
        // GET API users test run
        if (new GetUsers().testGetUsers(scheme, user)) {
            System.out.println("Проверка API GET users пройдена");
        }
        else {
            System.out.println("Проверка API GET users НЕ пройдена");
            isComplete = false;
        }

        // GET API accounts test run
        if (new GetAccounts().testGetAccounts(scheme, user)) {
            System.out.println("Проверка API GET accounts пройдена");
        }
        else {
            System.out.println("Проверка API GET accounts НЕ пройдена");
            isComplete = false;
        }

        // PUT API accounts update test run
        if (new PutAccountsUpdate().testPutAccountsUpdate(scheme, user)) {
            System.out.println("Проверка API PUT accounts update пройдена");
        }
        else {
            System.out.println("Проверка API PUT accounts update НЕ пройдена");
            isComplete = false;
        }

        //GET API operations test run
        if (new GetOperations().testGetOperations(scheme, user)) {
            System.out.println("Проверка API GET operations пройдена");
        }
        else {
            System.out.println("Проверка API GET operations НЕ пройдена");
            isComplete = false;
        }

        // PUT API operations update test run
        if (new PutOperationsUpdate().testPutOperationsUpdate(scheme, user)) {
            System.out.println("Проверка API PUT operations update пройдена");
        }
        else {
            System.out.println("Проверка API PUT operations update НЕ пройдена");
            isComplete = false;
        }

        // POST API operation insert test run
        if (new PostOperationInsert().testPostOperationInsert(scheme, user)) {
            System.out.println("Проверка API POST operation insert пройдена");
        }
        else {
            System.out.println("Проверка API POST operation insert НЕ пройдена");
            isComplete = false;
        }

        // GET API purchases test run
        if (new GetPurchases().testGetPurchases(scheme, user)) {
            System.out.println("Проверка API GET Purchases пройдена");
        }
        else {
            System.out.println("Проверка API GET Purchases НЕ пройдена");
            isComplete = false;
        }
        //PUT API Purchases test run
        if (new PutPurchasesUpdate().testPutPurchasesUpdate(scheme, user)) {
            System.out.println("Проверка API PUT purchases update пройдена");
        }
        else {
            System.out.println("Проверка API PUT purchases update НЕ пройдена");
            isComplete = false;
        }

        //POST API Purchases test run
        if (new PostPurchasesInsert().testPostPurchasesInsert(scheme, user)) {
            System.out.println("Проверка API POST purchases insert пройдена");
        }
        else {
            System.out.println("Проверка API POST purchases insert НЕ пройдена");
            isComplete = false;
        }

        // GET API withdraws test run
        if (new GetWithdraws().testGetWithdraws(scheme, user)) {
            System.out.println("Проверка API GET withdraws пройдена");
        }
        else {
            System.out.println("Проверка API GET withdraws НЕ пройдена");
            isComplete = false;
        }

        // PUT API withdraws update test run
        if (new PutWithdrawsUpdate().testPutWithdrawsUpdate(scheme, user)) {
            System.out.println("Проверка API PUT withdraws update пройдена");
        }
        else {
            System.out.println("Проверка API PUT withdraws update НЕ пройдена");
            isComplete = false;
        }

        // POST API withdraw insert test run
        if (new PostWithdrawInsert().testPostWithdrawInsert(scheme, user)) {
            System.out.println("Проверка API POST withdraw insert пройдена");
        }
        else {
            System.out.println("Проверка API POST withdraw insert НЕ пройдена");
            isComplete = false;
        }

        // GET API products test run
        if (new GetProducts().testGetProducts(scheme, user)) {
            System.out.println("Проверка API GET products пройдена");
        }
        else {
            System.out.println("Проверка API GET products НЕ пройдена");
            isComplete = false;
        }

        // PUT API products update test run
        if (new PutProductsSave().testPutProductsUpdate(scheme, user)) {
            System.out.println("Проверка API PUT products update пройдена");
        }
        else {
            System.out.println("Проверка API PUT products update НЕ пройдена");
            isComplete = false;
        }

// Раскомментировать, когда АПИ продуктов будет возвражать все продукты
/*
        // PUT API products insert test run
        if (new PutProductsSave().testPutProductsInsert(scheme, user)) {
            System.out.println("Проверка API PUT products insert пройдена");
        }
        else {
            System.out.println("Проверка API PUT products insert НЕ пройдена");
            isComplete = false;
        }
*/

        //GET API documents test run
        if (new GetDocuments().testGetDocuments(scheme, user)) {
            System.out.println("Проверка API GET documents пройдена");
        }
        else {
            System.out.println("Проверка API GET documents НЕ пройдена");
            isComplete = false;
        }

        //GET API documents by id test run
        if (new GetDocumentsById().testGetDocumentsById(scheme, user)) {
            System.out.println("Проверка API GET documents GET by ID пройдена");
        }
        else {
            System.out.println("Проверка API GET documents GET by ID НЕ пройдена");
            isComplete = false;
        }

        // GET API config test run
        if (new GetConfig().testGetConfig(scheme, user)) {
            System.out.println("Проверка API GET config пройдена");
        }
        else {
            System.out.println("Проверка API GET config НЕ пройдена");
            isComplete = false;
        }

        // PUT API config update test run
        if (new PutConfigUpdate().testPutConfigUpdate(scheme, user)) {
            System.out.println("Проверка API PUT config update пройдена");
        }
        else {
            System.out.println("Проверка API PUT config update НЕ пройдена");
            isComplete = false;
        }
        return isComplete;
    }
}
