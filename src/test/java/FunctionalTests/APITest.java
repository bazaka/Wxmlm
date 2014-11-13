package FunctionalTests;

import ApiTests.BackendGetAccounts;
import ApiTests.BackendPutAccountsUpdate;
import UsedByAll.TestUser;
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Mykhail Filonchenko on 10.11.2014.
 */
public class APITest {
    private DefaultSelenium selenium;
    private WebDriver driver;

    @Before
    public void setUp(String scheme) throws Exception {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        selenium = new WebDriverBackedSelenium(driver, "http://" + scheme);
        System.out.println("Запускаю селениум для проверки API-метода PUT Accounts на " + scheme);
    }

    @Test
    public boolean runAPITests(String scheme, TestUser user){
        boolean isComplete = false;
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
            System.out.println("Проверка API GET accounts НЕ пройдена:" + e); }
        //Вызов метода окончания теста
        try { newBackendGetAccounts.tearDown(); }
        catch (Exception e) { e.printStackTrace(); }

        BackendPutAccountsUpdate newBackendPutAccountsUpdate = new BackendPutAccountsUpdate(); // Создаём объект теста
        // Вызов метода, переходящего на главную страницу проекта
        try { newBackendPutAccountsUpdate.setUp(scheme); }
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
            System.out.println("Проверка API GET accounts НЕ пройдена:" + e); }
        //Вызов метода окончания теста
        try { newBackendGetAccounts.tearDown(); }
        catch (Exception e) { e.printStackTrace(); }
        return isComplete;

    }


    @After
    public void tearDown() throws Exception {
        selenium.stop();
    }

}
