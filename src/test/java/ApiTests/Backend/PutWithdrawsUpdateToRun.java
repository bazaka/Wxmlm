package ApiTests.Backend;

import ApiTests.UsedByAll.MakeRequest;
import ApiTests.ObjectClasses.Withdraw;
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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

// * Created for W-xmlm by Fill on 02.12.2014. Update withdraws
@RunWith(value = Parameterized.class)
public class PutWithdrawsUpdateToRun {
    private TestUser testUser;

    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_PutWithdrawsUpdateToRun(");
    }

    public PutWithdrawsUpdateToRun(TestUser user){
        this.testUser = user;
    }

    @Test
    public void testPutWithdrawsUpdate() throws IOException {
        String siteUrl = Config.getConfig().getProtocol() + Config.getConfig().getScheme(); // Урл проверяемого сайта
        Withdraw originalOne = new GetWithdrawsToRun(testUser).getAnyWithdraw(testUser, siteUrl);
        assertFalse("Withdraw not found", originalOne == null);
        String originalJson = null;
        String modifiedJson = null;
        Withdraw modifiedOne = null;
        assertTrue("Bank details is neither \"ePayment\" or \"SWIFT\" ", originalOne.getMerchant().equals("ePayment") || originalOne.getMerchant().equals("SWIFT"));
        if (originalOne.getMerchant().equals("ePayment")) {
            modifiedOne = new Withdraw(originalOne.getId(), originalOne.getUserId(), originalOne.getMerchantId(), originalOne.getOperationId(), originalOne.getAmount() + 10, originalOne.getCreatedDate(), 3, originalOne.getEpid() + "123", originalOne.getMerchant());
            originalJson = "[{\"id\":" + originalOne.getId() + ", \"user_id\":" + originalOne.getUserId() + ", \"merchant_id\":" + originalOne.getMerchantId() + ", \"amount\":" + originalOne.getAmount() + ", \"details\": { \"epid\": \"" + originalOne.getEpid() + "\" }, \"created_date\": \"" + originalOne.getCreatedDate() + "\", \"status\": " + originalOne.getStatus() + " }]";
            modifiedJson = "[{\"id\":" + modifiedOne.getId() + ", \"user_id\":" + modifiedOne.getUserId() + ", \"merchant_id\":" + modifiedOne.getMerchantId() + ", \"amount\":" + modifiedOne.getAmount() + ", \"details\": { \"epid\": \"" + modifiedOne.getEpid() + "\" }, \"created_date\": \"" + modifiedOne.getCreatedDate() + "\", \"status\": " + modifiedOne.getStatus() + " }]";
        }
        else if (originalOne.getMerchant().equals("SWIFT")) {
            modifiedOne = new Withdraw(originalOne.getId(), originalOne.getUserId(), originalOne.getMerchantId(), originalOne.getOperationId(), originalOne.getAmount() + 12, originalOne.getCreatedDate(), 2, originalOne.getSwiftName() + "123", originalOne.getSwiftAddress() + "234", originalOne.getBankName() + "345", originalOne.getBankAddress() + "456", originalOne.getAccountIban(), originalOne.getSwiftCode() + "567", originalOne.getMerchant());
            originalJson = "[{\"id\":" + originalOne.getId() + ", \"user_id\":" + originalOne.getUserId() + ", \"merchant_id\":" + originalOne.getMerchantId() + ", \"amount\":" + originalOne.getAmount() + ", \"details\": { \"name\": \"" + originalOne.getSwiftName() + "\", \"address\": \"" + originalOne.getSwiftAddress() + "\", \"bankName\": \"" + originalOne.getBankName() + "\", \"bankAddress\": \"" + originalOne.getBankAddress() + "\", \"accountIban\": \"" + originalOne.getAccountIban() + "\", \"swiftCode\": \"" + originalOne.getSwiftCode() + "\" }, \"created_date\": \"" + originalOne.getCreatedDate() + "\", \"status\": " + originalOne.getStatus() + " }]";
            modifiedJson = "[{\"id\":" + modifiedOne.getId() + ", \"user_id\":" + modifiedOne.getUserId() + ", \"merchant_id\":" + modifiedOne.getMerchantId() + ", \"amount\":" + modifiedOne.getAmount() + ", \"details\": { \"name\": \"" + modifiedOne.getSwiftName() + "\", \"address\": \"" + modifiedOne.getSwiftAddress() + "\", \"bankName\": \"" + modifiedOne.getBankName() + "\", \"bankAddress\": \"" + modifiedOne.getBankAddress() + "\", \"accountIban\": \"" + modifiedOne.getAccountIban() + "\", \"swiftCode\": \"" + modifiedOne.getSwiftCode() + "\" }, \"created_date\": \"" + modifiedOne.getCreatedDate() + "\", \"status\": " + modifiedOne.getStatus() + " }]";
        }
        long startTime;
        long elapsedTime;
        // Содзаем URL
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, testUser, "money/api/withdraws/update/", "PUT", "application/json", "application/json", true);

        OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
        assertFalse("ModifiedJson is null", modifiedJson == null);
        out.write(modifiedJson);
        out.close();

        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);
        elapsedTime = System.currentTimeMillis() - startTime;
        // Проверяем GET-запросом, что данные обновились
        Withdraw changedOne = new GetWithdrawsToRun(testUser).getWithdrawByParameter("id", originalOne.getId(), testUser, siteUrl);
        assertTrue("Check modified data saved correctly", modifiedOne.equalsExceptUpdatedDate(changedOne));

        // Содзаем URL
        httpCon = MakeRequest.getConnection(siteUrl, testUser, "money/api/withdraws/update/", "PUT", "application/json", "application/json", true);
        out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(originalJson);
        out.close();
        httpCon.getInputStream();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);
        // Проверяем GET-запросом, что данные восстановились
        changedOne = new GetWithdrawsToRun(testUser).getWithdrawByParameter("id", originalOne.getId(), testUser, siteUrl);
        assertTrue("Check modified data returned correctly", originalOne.equalsExceptUpdatedDate(changedOne));
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
    }
}
