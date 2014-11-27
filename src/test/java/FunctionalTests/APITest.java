package FunctionalTests;

import ApiTests.*;
import UsedByAll.TestUser;

public class APITest {
    public boolean runAPITests(String scheme, TestUser user){
        boolean isComplete = true;
        /*BackendGetUsers newBackendGetUsers = new BackendGetUsers(); // Создаём объект теста
        // Вызов метода, переходящего на главную страницу проекта
        try { newBackendGetUsers.setUp(scheme); }
        catch (Exception e) { e.printStackTrace(); }
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
        try { newBackendGetUsers.tearDown(); }
        catch (Exception e) { e.printStackTrace(); }*/

        BackendGetAccounts newBackendGetAccounts = new BackendGetAccounts(); // Создаём объект теста
        // Вызов метода, переходящего на главную страницу проекта
        try { newBackendGetAccounts.setUp(scheme); }
        catch (Exception e) { e.printStackTrace(); }
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
        //Вызов метода окончания теста
        try { newBackendGetAccounts.tearDown(); }
        catch (Exception e) { e.printStackTrace(); }

        BackendPutAccountsUpdate newBackendPutAccountsUpdate = new BackendPutAccountsUpdate(); // Создаём объект теста
        // Вызов метода, переходящего на главную страницу проекта
        try { newBackendPutAccountsUpdate.setUp(scheme); }
        catch (Exception e) { e.printStackTrace(); }
        //Вызов метода, запускающего Put API Accounts Update
        try
        {
            if (newBackendPutAccountsUpdate.testBackendPutAccountsUpdate(scheme, user))
            {
                System.out.println("Проверка API PUT Accounts Update пройдена");
            }
            else
            {
                System.out.println("Проверка API PUT Accounts Update НЕ пройдена");
                isComplete = false;
            }
        }
        catch (Exception e) { e.printStackTrace();
            System.out.println("Проверка API PUT Accounts Update НЕ пройдена:" + e);  isComplete = false;}
        //Вызов метода окончания теста
        try { newBackendPutAccountsUpdate.tearDown(); }
        catch (Exception e) { e.printStackTrace(); }

        BackendGetOperations newBackendGetOperations = new BackendGetOperations(); // Создаём объект теста
        // Вызов метода, переходящего на главную страницу проекта
        try { newBackendGetOperations.setUp(scheme); }
        catch (Exception e) { e.printStackTrace(); }
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
        //Вызов метода окончания теста
        try { newBackendGetOperations.tearDown(); }
        catch (Exception e) { e.printStackTrace(); }

        BackendPutOperationsUpdate newBackendPutOperationsUpdate = new BackendPutOperationsUpdate(); // Создаём объект теста
        // Вызов метода, переходящего на главную страницу проекта
        try { newBackendPutAccountsUpdate.setUp(scheme); }
        catch (Exception e) { e.printStackTrace(); }
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
        //Вызов метода окончания теста
        try { newBackendPutOperationsUpdate.tearDown(); }
        catch (Exception e) { e.printStackTrace(); }

        return isComplete;
    }
}
