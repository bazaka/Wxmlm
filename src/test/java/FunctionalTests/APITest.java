package FunctionalTests;

import ApiTests.*;
import ApiTests.BackendGetPurchases;
import UsedByAll.TestUser;

public class APITest {
    public boolean runAPITests(String scheme, TestUser user){
        boolean isComplete = true;
        /*BackendGetUsers newBackendGetUsers = new BackendGetUsers(); // Создаём объект теста
        // Вызов метода, переходящего на главную страницу проекта
        //try { newBackendGetUsers.setUp(scheme); }
        //catch (Exception e) { e.printStackTrace(); }
        //Вызов метода, запускающего GET API Accounts
        try
        {
            if (newBackendGetUsers.testBackendGetUsers(scheme, user))
            {
                System.out.println("Проверка API GET Users пройдена");
            }
            else
            {
                System.out.println("Проверка API GET Users НЕ пройдена");
                isComplete = false;
            }
        }
        catch (Exception e) { e.printStackTrace();
            System.out.println("Проверка API GET Users НЕ пройдена:" + e); }
        //Вызов метода окончания теста
        //try { newBackendGetUsers.tearDown(); }
        //catch (Exception e) { e.printStackTrace(); }*/

        BackendGetAccounts newBackendGetAccounts = new BackendGetAccounts(); // Создаём объект теста
        //Вызов метода, запускающего GET API Accounts
        try
        {
            if (newBackendGetAccounts.testBackendGetAccounts(scheme, user))
            {
                System.out.println("Проверка API GET accounts пройдена");
            }
            else
            {
                System.out.println("Проверка API GET accounts НЕ пройдена");
                isComplete = false;
            }
        }
        catch (Exception e) { e.printStackTrace();
            System.out.println("Проверка API GET accounts НЕ пройдена: " + e); isComplete = false;}

        BackendPutAccountsUpdate newBackendPutAccountsUpdate = new BackendPutAccountsUpdate(); // Создаём объект теста
        //Вызов метода, запускающего Put API Accounts Update
        try
        {
            if (newBackendPutAccountsUpdate.testBackendPutAccountsUpdate(scheme, user))
            {
                System.out.println("Проверка API PUT accounts update пройдена");
            }
            else
            {
                System.out.println("Проверка API PUT accounts update НЕ пройдена");
                isComplete = false;
            }
        }
        catch (Exception e) { e.printStackTrace();
            System.out.println("Проверка API PUT accounts update НЕ пройдена:" + e);  isComplete = false;}

        BackendGetOperations newBackendGetOperations = new BackendGetOperations(); // Создаём объект теста
        //Вызов метода, запускающего GET API Operations
        try
        {
            if (newBackendGetOperations.testBackendGetOperations(scheme, user))
            {
                System.out.println("Проверка API GET operations пройдена");
            }
            else
            {
                System.out.println("Проверка API GET operations НЕ пройдена");
                isComplete = false;
            }
        }
        catch (Exception e) { e.printStackTrace();
            System.out.println("Проверка API GET operations НЕ пройдена: " + e);  isComplete = false;}

        BackendPutOperationsUpdate newBackendPutOperationsUpdate = new BackendPutOperationsUpdate(); // Создаём объект теста
        //Вызов метода, запускающего PUT API Operations Update
        try
        {
            if (newBackendPutOperationsUpdate.testBackendPutOperationsUpdate(scheme, user))
            {
                System.out.println("Проверка API PUT operations update пройдена");
            }
            else
            {
                System.out.println("Проверка API PUT operations update НЕ пройдена");
                isComplete = false;
            }
        }
        catch (Exception e) { e.printStackTrace();
            System.out.println("Проверка API PUT operations update НЕ пройдена: " + e);  isComplete = false;}

        BackendGetPurchases newBackendGetPurchases = new BackendGetPurchases(); // Создаём объект теста
        // Вызов метода, переходящего на главную страницу проекта
        //try { newBackendGetUsers.setUp(scheme); }
        //catch (Exception e) { e.printStackTrace(); }
        //Вызов метода, запускающего GET API Accounts
        try
        {
            if (newBackendGetPurchases.testBackendGetPurchases(scheme, user))
            {
                System.out.println("Проверка API GET Purchases пройдена");
            }
            else
            {
                System.out.println("Проверка API GET Purchases НЕ пройдена");
                isComplete = false;
            }
        }
        catch (Exception e) { e.printStackTrace();
            System.out.println("Проверка API GET Purchases НЕ пройдена:" + e); }

        return isComplete;
    }
}
