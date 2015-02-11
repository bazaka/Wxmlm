package ApiTests.Backend;

import ApiTests.UsedByAll.MakeRequest;
import ApiTests.ObjectClasses.Withdraw;
import UsedByAll.TestUser;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import static org.junit.Assert.assertTrue;

// * Created for W-xmlm by Fill on 02.12.2014. Update withdraws
public class PutWithdrawsUpdate {
    public boolean testPutWithdrawsUpdate(String siteUrl, TestUser user) throws IOException {
        Withdraw originalOne = new GetWithdraws().getAnyWithdraw(user, siteUrl);
        if (originalOne == null) {
            return false;
        }
        String originalJson;
        String modifiedJson;
        Withdraw modifiedOne;
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
        else {
            System.out.println("Bank details is missing in object");
            return false;
        }
        long startTime;
        long elapsedTime;
        // Содзаем URL
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, user, "money/api/withdraws/update/", "PUT", "application/json", "application/json", true);

        OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(modifiedJson);
        out.close();

        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);
        elapsedTime = System.currentTimeMillis() - startTime;
        // Проверяем GET-запросом, что данные обновились
        Withdraw changedOne = new GetWithdraws().getWithdrawByParameter("id", originalOne.getId(), user, siteUrl);
        assertTrue("Check modified data saved correctly", modifiedOne.equalsExceptUpdatedDate(changedOne));

        // Содзаем URL
        httpCon = MakeRequest.getConnection(siteUrl, user, "money/api/withdraws/update/", "PUT", "application/json", "application/json", true);
        out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(originalJson);
        out.close();
        httpCon.getInputStream();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);
        // Проверяем GET-запросом, что данные восстановились
        changedOne = new GetWithdraws().getWithdrawByParameter("id", originalOne.getId(), user, siteUrl);
        assertTrue("Check modified data returned correctly", originalOne.equalsExceptUpdatedDate(changedOne));
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
        return true;
    }
}
