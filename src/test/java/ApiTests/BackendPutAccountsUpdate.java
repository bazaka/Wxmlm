package ApiTests;

import ApiTests.ObjectClasses.Account;
import ApiTests.ObjectClasses.MakeRequest;
import UsedByAll.TestUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
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
        HttpURLConnection httpCon = MakeRequest.getConnection(scheme, user, "money/api/accounts/update/", "PUT", "application/json", "application/json", true);
        OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(modifiedJson);
        out.close();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);
        // Проверяем GET-запросом, что данные обновились
        Account changedAccount = new BackendGetAccounts().getAccountByParameter("account_id", originalAccount.getAccountId(), user, scheme);
        assertTrue("Check modified data saved correctly", modifiedAccount.equalsExceptUpdatedDate(changedAccount));

        // Содзаем URL
        httpCon = MakeRequest.getConnection(scheme, user, "money/api/accounts/update/", "PUT", "application/json", "application/json", true);
        out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(originalJson);
        out.close();
        httpCon.getInputStream();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);
        // Проверяем GET-запросом, что данные восстановились
        changedAccount = new BackendGetAccounts().getAccountByParameter("account_id", originalAccount.getAccountId(), user, scheme);
        assertTrue("Check modified data returned correctly", originalAccount.equalsExceptUpdatedDate(changedAccount));
        return true;
    }
    @After
    public void tearDown() throws Exception {}
}