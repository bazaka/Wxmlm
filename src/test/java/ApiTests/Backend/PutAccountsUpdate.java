package ApiTests.Backend;

import ApiTests.ObjectClasses.Account;
import ApiTests.UsedByAll.MakeRequest;
import UsedByAll.TestUser;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import static org.junit.Assert.assertTrue;

public class PutAccountsUpdate {
    public boolean testPutAccountsUpdate(String siteUrl, TestUser user) throws IOException {
        long startTime;
        long elapsedTime;
        Account originalAccount = new GetAccounts().getAnyAccount(user, siteUrl);
        Account modifiedAccount = new Account(originalAccount.getAccountId(), originalAccount.getAccountNumber(), originalAccount.getAccountType(), originalAccount.getStatus(), originalAccount.getAccountInfo() + "1", originalAccount.getAmount() + 50);
        String originalJson = "[{\"account_id\":" + originalAccount.getAccountId() + ", \"account_number\":\"" + originalAccount.getAccountNumber() + "\", \"account_type\":" + originalAccount.getAccountType() + ", \"status\":" + originalAccount.getStatus() + ", \"account_info\": \"" + originalAccount.getAccountInfo() + "\", \"amount\": \"" + originalAccount.getAmount() + "\"}]";
        String modifiedJson = "[{\"account_id\":" + modifiedAccount.getAccountId() + ", \"account_number\":\"" + modifiedAccount.getAccountNumber() + "\", \"account_type\":" + modifiedAccount.getAccountType() + ", \"status\":" + modifiedAccount.getStatus() + ", \"account_info\": \"" + modifiedAccount.getAccountInfo() + "\", \"amount\": \"" + modifiedAccount.getAmount() + "\"}]";

        // Содзаем URL
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, user, "money/api/accounts/update/", "PUT", "application/json", "application/json", true);
        OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(modifiedJson);
        out.close();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);
        elapsedTime = System.currentTimeMillis() - startTime;
        // Проверяем GET-запросом, что данные обновились
        Account changedAccount = new GetAccounts().getAccountByParameter("account_id", originalAccount.getAccountId(), user, siteUrl);
        assertTrue("Check modified data saved correctly", modifiedAccount.equalsExceptUpdatedDate(changedAccount));

        // Содзаем URL
        httpCon = MakeRequest.getConnection(siteUrl, user, "money/api/accounts/update/", "PUT", "application/json", "application/json", true);
        out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(originalJson);
        out.close();
        httpCon.getInputStream();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);
        // Проверяем GET-запросом, что данные восстановились
        changedAccount = new GetAccounts().getAccountByParameter("account_id", originalAccount.getAccountId(), user, siteUrl);
        assertTrue("Check modified data returned correctly", originalAccount.equalsExceptUpdatedDate(changedAccount));
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
        return true;
    }
}