package FunctionalTests;

import ApiTests.*;
import ApiTests.BackendGetPurchases;
import UsedByAll.TestUser;

import java.io.IOException;

public class APITest {
    public boolean runAPITests(String scheme, TestUser user) throws Exception {
        boolean isComplete = true;
        BackendGetUsers newBackendGetUsers = new BackendGetUsers(); // Создаём объект теста
        //Вызов метода, запускающего GET API Accounts
        if (newBackendGetUsers.testBackendGetUsers(scheme, user)) {
            System.out.println("Проверка API GET Users пройдена");
        }
        else {
            System.out.println("Проверка API GET Users НЕ пройдена");
            isComplete = false;
        }

        BackendGetAccounts newBackendGetAccounts = new BackendGetAccounts(); // Создаём объект теста
        //Вызов метода, запускающего GET API Accounts
        if (newBackendGetAccounts.testBackendGetAccounts(scheme, user)) {
            System.out.println("Проверка API GET accounts пройдена");
        }
        else {
            System.out.println("Проверка API GET accounts НЕ пройдена");
            isComplete = false;
        }

        BackendPutAccountsUpdate newBackendPutAccountsUpdate = new BackendPutAccountsUpdate(); // Создаём объект теста
        //Вызов метода, запускающего Put API Accounts Update
        if (newBackendPutAccountsUpdate.testBackendPutAccountsUpdate(scheme, user)) {
            System.out.println("Проверка API PUT accounts update пройдена");
        }
        else {
            System.out.println("Проверка API PUT accounts update НЕ пройдена");
            isComplete = false;
        }

        BackendGetOperations newBackendGetOperations = new BackendGetOperations(); // Создаём объект теста
        //Вызов метода, запускающего GET API Operations
        if (newBackendGetOperations.testBackendGetOperations(scheme, user)) {
            System.out.println("Проверка API GET operations пройдена");
        }
        else {
            System.out.println("Проверка API GET operations НЕ пройдена");
            isComplete = false;
        }

        BackendPutOperationsUpdate newBackendPutOperationsUpdate = new BackendPutOperationsUpdate(); // Создаём объект теста
        //Вызов метода, запускающего PUT API Operations Update
        if (newBackendPutOperationsUpdate.testBackendPutOperationsUpdate(scheme, user)) {
            System.out.println("Проверка API PUT operations update пройдена");
        }
        else {
            System.out.println("Проверка API PUT operations update НЕ пройдена");
            isComplete = false;
        }

        BackendPostOperationInsert newBackendPostOperationInsert = new BackendPostOperationInsert(); // Создаём объект теста
        //Вызов метода, запускающего PUT API Operation Insert
        if (newBackendPostOperationInsert.testBackendPostOperationInsert(scheme, user)) {
            System.out.println("Проверка API POST operation insert пройдена");
        }
        else {
            System.out.println("Проверка API POST operation insert НЕ пройдена");
            isComplete = false;
        }

        BackendGetPurchases newBackendGetPurchases = new BackendGetPurchases(); // Создаём объект теста
        //Вызов метода, запускающего GET API Accounts
        if (newBackendGetPurchases.testBackendGetPurchases(scheme, user)) {
            System.out.println("Проверка API GET Purchases пройдена");
        }
        else {
            System.out.println("Проверка API GET Purchases НЕ пройдена");
            isComplete = false;
        }

        BackendGetWithdraws newBackendGetWithdraws = new BackendGetWithdraws(); // Создаём объект теста
        //Вызов метода, запускающего GET API Withdraws
        if (newBackendGetWithdraws.testBackendGetWithdraws(scheme, user)) {
            System.out.println("Проверка API GET withdraws пройдена");
        }
        else {
            System.out.println("Проверка API GET withdraws НЕ пройдена");
            isComplete = false;
        }

        BackendPutWithdrawsUpdate newBackendPutWithdrawsUpdate = new BackendPutWithdrawsUpdate(); // Создаём объект теста
        //Вызов метода, запускающего PUT API Withdraws update
        if (newBackendPutWithdrawsUpdate.testBackendPutWithdrawsUpdate(scheme, user)) {
            System.out.println("Проверка API PUT withdraws update пройдена");
        }
        else {
            System.out.println("Проверка API PUT withdraws update НЕ пройдена");
            isComplete = false;
        }

        return isComplete;
    }
}
