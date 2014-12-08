package ApiTests.ApiTests;

import ApiTests.ObjectClasses.MakeRequest;
import ApiTests.ObjectClasses.Withdraw;
import UsedByAll.TestUser;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import static org.junit.Assert.assertTrue;

// * Created for W-xmlm by Fill on 02.12.2014. Update withdraws
public class PutWithdrawsUpdate {
    @Test
    public boolean testPutWithdrawsUpdate(String scheme, TestUser user) throws IOException {
        Withdraw originalWithdraw = new GetWithdraws().getAnyWithdraw(user, scheme);
        String originalJson;
        String modifiedJson;
        Withdraw modifiedWithdraw;
        if (originalWithdraw.getMerchant().equals("ePayment")) {
            modifiedWithdraw = new Withdraw(originalWithdraw.getId(), originalWithdraw.getUserId(), originalWithdraw.getMerchantId(), originalWithdraw.getOperationId(), originalWithdraw.getAmount() + 10, originalWithdraw.getCreatedDate(), 3, originalWithdraw.getEpid() + "123", originalWithdraw.getMerchant());
            originalJson = "[{\"id\":" + originalWithdraw.getId() + ", \"user_id\":" + originalWithdraw.getUserId() + ", \"merchant_id\":" + originalWithdraw.getMerchantId() + ", \"amount\":" + originalWithdraw.getAmount() + ", \"details\": { \"epid\": \"" + originalWithdraw.getEpid() + "\" }, \"created_date\": \"" + originalWithdraw.getCreatedDate() + "\", \"status\": " + originalWithdraw.getStatus() + " }]";
            modifiedJson = "[{\"id\":" + modifiedWithdraw.getId() + ", \"user_id\":" + modifiedWithdraw.getUserId() + ", \"merchant_id\":" + modifiedWithdraw.getMerchantId() + ", \"amount\":" + modifiedWithdraw.getAmount() + ", \"details\": { \"epid\": \"" + modifiedWithdraw.getEpid() + "\" }, \"created_date\": \"" + modifiedWithdraw.getCreatedDate() + "\", \"status\": " + modifiedWithdraw.getStatus() + " }]";
        }
        else if (originalWithdraw.getMerchant().equals("SWIFT")) {
            modifiedWithdraw = new Withdraw(originalWithdraw.getId(), originalWithdraw.getUserId(), originalWithdraw.getMerchantId(), originalWithdraw.getOperationId(), originalWithdraw.getAmount() + 12, originalWithdraw.getCreatedDate(), 2, originalWithdraw.getSwiftName() + "123", originalWithdraw.getSwiftAddress() + "234", originalWithdraw.getBankName() + "345", originalWithdraw.getBankAddress() + "456", originalWithdraw.getAccountIban(), originalWithdraw.getSwiftCode() + "567", originalWithdraw.getMerchant());
            originalJson = "[{\"id\":" + originalWithdraw.getId() + ", \"user_id\":" + originalWithdraw.getUserId() + ", \"merchant_id\":" + originalWithdraw.getMerchantId() + ", \"amount\":" + originalWithdraw.getAmount() + ", \"details\": { \"name\": \"" + originalWithdraw.getSwiftName() + "\", \"address\": \"" + originalWithdraw.getSwiftAddress() + "\", \"bankName\": \"" + originalWithdraw.getBankName() + "\", \"bankAddress\": \"" + originalWithdraw.getBankAddress() + "\", \"accountIban\": \"" + originalWithdraw.getAccountIban() + "\", \"swiftCode\": \"" + originalWithdraw.getSwiftCode() + "\" }, \"created_date\": \"" + originalWithdraw.getCreatedDate() + "\", \"status\": " + originalWithdraw.getStatus() + " }]";
            modifiedJson = "[{\"id\":" + modifiedWithdraw.getId() + ", \"user_id\":" + modifiedWithdraw.getUserId() + ", \"merchant_id\":" + modifiedWithdraw.getMerchantId() + ", \"amount\":" + modifiedWithdraw.getAmount() + ", \"details\": { \"name\": \"" + modifiedWithdraw.getSwiftName() + "\", \"address\": \"" + modifiedWithdraw.getSwiftAddress() + "\", \"bankName\": \"" + modifiedWithdraw.getBankName() + "\", \"bankAddress\": \"" + modifiedWithdraw.getBankAddress() + "\", \"accountIban\": \"" + modifiedWithdraw.getAccountIban() + "\", \"swiftCode\": \"" + modifiedWithdraw.getSwiftCode() + "\" }, \"created_date\": \"" + modifiedWithdraw.getCreatedDate() + "\", \"status\": " + modifiedWithdraw.getStatus() + " }]";
        }
        else {
            System.out.println("Bank details is missing in object");
            return false;
        }

        // Содзаем URL
        HttpURLConnection httpCon = MakeRequest.getConnection(scheme, user, "money/api/withdraws/update/", "PUT", "application/json", "application/json", true);
        OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(modifiedJson);
        out.close();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);
        // Проверяем GET-запросом, что данные обновились
        Withdraw changedWithdraw = new GetWithdraws().getWithdrawByParameter("id", originalWithdraw.getId(), user, scheme);
        assertTrue("Check modified data saved correctly", modifiedWithdraw.equalsExceptUpdatedDate(changedWithdraw));

        // Содзаем URL
        httpCon = MakeRequest.getConnection(scheme, user, "money/api/withdraws/update/", "PUT", "application/json", "application/json", true);
        out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(originalJson);
        out.close();
        httpCon.getInputStream();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);
        // Проверяем GET-запросом, что данные восстановились
        changedWithdraw = new GetWithdraws().getWithdrawByParameter("id", originalWithdraw.getId(), user, scheme);
        assertTrue("Check modified data returned correctly", originalWithdraw.equalsExceptUpdatedDate(changedWithdraw));
        return true;
    }
}
