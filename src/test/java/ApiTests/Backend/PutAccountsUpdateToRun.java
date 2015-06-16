package ApiTests.Backend;

import ApiTests.ObjectClasses.Account;
import ApiTests.UsedByAll.MakeRequest;
import UsedByAll.Config;
import UsedByAll.CsvUsersReader;
import UsedByAll.TestUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(value = Parameterized.class)
public class PutAccountsUpdateToRun {
    private TestUser testUser;

    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_BackendAPITest(");
    }

    public PutAccountsUpdateToRun(TestUser user){
        this.testUser = user;
    }

    @Test
    public void testPutAccountsUpdate() throws IOException {
        String siteUrl = Config.getConfig().getProtocol() + Config.getConfig().getScheme(); // Урл проверяемого сайта
        long startTime;
        long elapsedTime;
        Account originalAccount = new GetAccountsToRun(testUser).getAnyAccount(testUser, siteUrl);
        System.out.println(originalAccount.getAccountId());
        Account modifiedAccount = new Account(originalAccount.getAccountId(), originalAccount.getAccountNumber(), originalAccount.getAccountType(), originalAccount.getStatus(), originalAccount.getAccountInfo(), originalAccount.getAmount() + 50);
        String originalJson = "[{\"account_id\":" + originalAccount.getAccountId() + ", \"account_number\":\"" + originalAccount.getAccountNumber() + "\", \"account_type\":" + originalAccount.getAccountType() + ", \"status\":" + originalAccount.getStatus() + ", \"amount\": \"" + originalAccount.getAmount() + "\"}]";
        String modifiedJson = "[{\"account_id\":" + modifiedAccount.getAccountId() + ", \"account_number\":\"" + modifiedAccount.getAccountNumber() + "\", \"account_type\":" + modifiedAccount.getAccountType() + ", \"status\":" + modifiedAccount.getStatus() + ", \"amount\": \"" + modifiedAccount.getAmount() + "\"}]";
        System.out.println("Original: " + originalJson);
        System.out.println("Modified: " + modifiedJson);

        // Содзаем URL
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, testUser, "money/api/accounts/update/", "PUT", "application/json", "application/json", true);
        OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(modifiedJson);
        out.close();
        assertEquals("Check response code is 200", 200, httpCon.getResponseCode());
        elapsedTime = System.currentTimeMillis() - startTime;
        // Проверяем GET-запросом, что данные обновились
        Account changedAccount = new GetAccountsToRun(testUser).getAccountByParameter("account_id", originalAccount.getAccountId(), testUser, siteUrl);
        assertTrue("Check modified data saved correctly", modifiedAccount.equalsExceptUpdatedDate(changedAccount));

        // Содзаем URL
        httpCon = MakeRequest.getConnection(siteUrl, testUser, "money/api/accounts/update/", "PUT", "application/json", "application/json", true);
        out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(originalJson);
        out.close();
        httpCon.getInputStream();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);
        // Проверяем GET-запросом, что данные восстановились
        changedAccount = new GetAccountsToRun(testUser).getAccountByParameter("account_id", originalAccount.getAccountId(), testUser, siteUrl);
        assertTrue("Check modified data returned correctly", originalAccount.equalsExceptUpdatedDate(changedAccount));
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
    }
}