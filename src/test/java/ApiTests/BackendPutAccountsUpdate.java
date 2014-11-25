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

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.codec.binary.Base64;
import static org.junit.Assert.assertTrue;

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
        Account originalAccount = new BackendGetAccounts().getAnyAccount(user, scheme, selenium);
        Account modifiedAccount = new Account(originalAccount.getAccountId(), originalAccount.getAccountNumber(), originalAccount.getAccountType(), originalAccount.getStatus(), originalAccount.getAccountInfo() + "1", originalAccount.getAmount() + 50);
        String originalJson = "[{\"account_id\":" + originalAccount.getAccountId() + ", \"account_number\":\"" + originalAccount.getAccountNumber() + "\", \"account_type\":" + originalAccount.getAccountType() + ", \"status\":" + originalAccount.getStatus() + ", \"account_info\": \"" + originalAccount.getAccountInfo() + "\", \"amount\": \"" + originalAccount.getAmount() + "\"}]";
        String modifiedJson = "[{\"account_id\":" + modifiedAccount.getAccountId() + ", \"account_number\":\"" + modifiedAccount.getAccountNumber() + "\", \"account_type\":" + modifiedAccount.getAccountType() + ", \"status\":" + modifiedAccount.getStatus() + ", \"account_info\": \"" + modifiedAccount.getAccountInfo() + "\", \"amount\": \"" + modifiedAccount.getAmount() + "\"}]";

        // Содзаем URL
        String authString = user.getEmail() + ":" + user.getPassword1();
        byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
        String authStringEnc = new String(authEncBytes);
        String stringUrl = "http://" + scheme + "money/api/accounts/update/";
        try {URL url = new URL(stringUrl);
            try {HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
                httpCon.setRequestProperty("Authorization", "Basic " + authStringEnc);
                httpCon.setRequestMethod("PUT");
                httpCon.setRequestProperty("Content-Type", "application/json");
                httpCon.setRequestProperty("Accept", "application/json");
                httpCon.setDoOutput(true);
                OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
                out.write(modifiedJson);
                out.close();
                System.out.println(httpCon.getInputStream());
            }
            catch (IOException e) {e.printStackTrace();}
        }
        catch (MalformedURLException e) {e.printStackTrace();}
        // Проверяем GET-запросом, что данные обновились
        Account changedAccount = new BackendGetAccounts().getAccountByParameter("account_number", originalAccount.getAccountNumber(), user, scheme, selenium);
        assertTrue("Check modified data saved correctly", modifiedAccount.getAccountNumber().equals(changedAccount.getAccountNumber()) && modifiedAccount.getAccountInfo().equals(changedAccount.getAccountInfo()) && modifiedAccount.getAmount() == changedAccount.getAmount());
        try {URL url = new URL(stringUrl);
            try {HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
                httpCon.setRequestProperty("Authorization", "Basic " + authStringEnc);
                httpCon.setRequestMethod("PUT");
                httpCon.setRequestProperty("Content-Type", "application/json");
                httpCon.setRequestProperty("Accept", "application/json");
                httpCon.setDoOutput(true);
                OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
                out.write(originalJson);
                out.close();
                System.out.println(httpCon.getInputStream());
            }
            catch (IOException e) {e.printStackTrace();}
        }
        catch (MalformedURLException e) {e.printStackTrace();}
        // Проверяем GET-запросом, что данные восстановились
        changedAccount = new BackendGetAccounts().getAccountByParameter("account_number", originalAccount.getAccountNumber(), user, scheme, selenium);
        assertTrue("Check modified data returned correctly", originalAccount.getAccountNumber().equals(changedAccount.getAccountNumber()) && originalAccount.getAccountInfo().equals(changedAccount.getAccountInfo()) && originalAccount.getAmount() == changedAccount.getAmount());
        return true;
    }
    @After
    public void tearDown() throws Exception { selenium.stop(); }
}