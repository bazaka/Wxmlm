package ApiTests;

import ApiTests.ObjectClasses.Account;
import UsedByAll.TestUser;
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BackendPutAccountsUpdate{
    private DefaultSelenium selenium;

    @Before
    public void setUp(String scheme) throws Exception {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        selenium = new WebDriverBackedSelenium(driver, "http://" + scheme);
        System.out.println("Запускаю селениум для проверки API-метода PUT Accounts update на " + scheme);
    }

    // ТЕСТ НЕ ДОДЕЛАН
    @Test
    public boolean testBackendPutAccountsUpdate(String scheme, TestUser user){
        Account account = new BackendGetAccounts().getAccount(user, scheme, selenium);
        double newAmount = account.getAmount() + 50;
        String jsn = "[{\"account_id\":" + account.getAccountId() + ", \"account_number\":\"" + account.getAccountNumber() + "\", \"account_type\":" + account.getAccountType() + ", \"status\":" + account.getStatus() + ", \"account_info\": \"" + account.getAccountInfo() + "\", \"amount\": \"" + newAmount + "\"}]";
        System.out.println(jsn);
        return true;
    }
    @After
    public void tearDown() throws Exception { selenium.stop(); }
}