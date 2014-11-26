package FunctionalTests;

import ApiTests.BackendGetAccounts;
import ApiTests.BackendGetUsers;
import ApiTests.BackendPutAccountsUpdate;
import UsedByAll.TestUser;

public class APITest {
    public boolean runAPITests(String scheme, TestUser user){
        boolean isComplete = false;
        BackendGetUsers newBackendGetUsers = new BackendGetUsers(); // Создаём объект теста
        // Вызов метода, переходящего на главную страницу проекта
        try { newBackendGetUsers.setUp(scheme); }
        catch (Exception e) { e.printStackTrace(); }
        //Вызов метода, запускающего GET API Accounts
        try
        {
            if (newBackendGetUsers.testBackendGetUsers(scheme, user))
            {
                System.out.println("Проверка API GET Users пройдена");

                isComplete = true;
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
        catch (Exception e) { e.printStackTrace(); }

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
                isComplete = true;
            }
            else
            {
                System.out.println("Проверка API GET accounts НЕ пройдена");
                isComplete = false;
            }
        }
        catch (Exception e) { e.printStackTrace();
            System.out.println("Проверка API GET accounts НЕ пройдена: " + e); }
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
                isComplete = true;
            }
            else
            {
                System.out.println("Проверка API PUT Accounts Update НЕ пройдена");
                isComplete = false;
            }
        }
        catch (Exception e) { e.printStackTrace();
            System.out.println("Проверка API PUT Accounts Update НЕ пройдена:" + e); }
        //Вызов метода окончания теста
        try { newBackendPutAccountsUpdate.tearDown(); }
        catch (Exception e) { e.printStackTrace(); }
        return isComplete;
    }
}
