package ApiTests;

import ApiTests.ObjectClasses.Account;
import UsedByAll.TestUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.codec.binary.Base64;
import static org.junit.Assert.assertTrue;

public class BackendPutAccountsUpdate{
    @Before
    public void setUp(String scheme) throws Exception {
        System.out.println("Запускаю селениум для проверки API-метода PUT Accounts update на " + scheme);
    }

    @Test
    public boolean testBackendPutAccountsUpdate(String scheme, TestUser user) throws IOException {
        Account originalAccount = new BackendGetAccounts().getAnyAccount(user, scheme);
        Account modifiedAccount = new Account(originalAccount.getAccountId(), originalAccount.getAccountNumber(), originalAccount.getAccountType(), originalAccount.getStatus(), originalAccount.getAccountInfo() + "1", originalAccount.getAmount() + 50);
        String originalJson = "[{\"account_id\":" + originalAccount.getAccountId() + ", \"account_number\":\"" + originalAccount.getAccountNumber() + "\", \"account_type\":" + originalAccount.getAccountType() + ", \"status\":" + originalAccount.getStatus() + ", \"account_info\": \"" + originalAccount.getAccountInfo() + "\", \"amount\": \"" + originalAccount.getAmount() + "\"}]";
        String modifiedJson = "[{\"account_id\":" + modifiedAccount.getAccountId() + ", \"account_number\":\"" + modifiedAccount.getAccountNumber() + "\", \"account_type\":" + modifiedAccount.getAccountType() + ", \"status\":" + modifiedAccount.getStatus() + ", \"account_info\": \"" + modifiedAccount.getAccountInfo() + "\", \"amount\": \"" + modifiedAccount.getAmount() + "\"}]";

        // Содзаем URL
        String authString = user.getEmail() + ":" + user.getPassword1();
        byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
        String authStringEnc = new String(authEncBytes);
        String stringUrl = "http://" + scheme + "money/api/accounts/update/";
        URL url = new URL(stringUrl);
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        httpCon.setRequestProperty("Authorization", "Basic " + authStringEnc);
        httpCon.setRequestMethod("PUT");
        httpCon.setRequestProperty("Content-Type", "application/json");
        httpCon.setRequestProperty("Accept", "application/json");
        httpCon.setDoOutput(true);
        OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(modifiedJson);
        out.close();
        httpCon.getInputStream();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);

        // Проверяем GET-запросом, что данные обновились
        Account changedAccount = new BackendGetAccounts().getAccountByParameter("account_number", originalAccount.getAccountNumber(), user, scheme);
        assertTrue("Check modified data saved correctly", modifiedAccount.equalsExceptUpdatedDate(changedAccount));
        url = new URL(stringUrl);
        httpCon = (HttpURLConnection) url.openConnection();
        httpCon.setRequestProperty("Authorization", "Basic " + authStringEnc);
        httpCon.setRequestMethod("PUT");
        httpCon.setRequestProperty("Content-Type", "application/json");
        httpCon.setRequestProperty("Accept", "application/json");
        httpCon.setDoOutput(true);
        out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(originalJson);
        out.close();
        httpCon.getInputStream();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);

        // Проверяем GET-запросом, что данные восстановились
        changedAccount = new BackendGetAccounts().getAccountByParameter("account_number", originalAccount.getAccountNumber(), user, scheme);
        assertTrue("Check modified data returned correctly", originalAccount.equalsExceptUpdatedDate(changedAccount));
        return true;
    }
    @After
    public void tearDown() throws Exception {}
}