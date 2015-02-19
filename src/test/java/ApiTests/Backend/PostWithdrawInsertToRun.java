package ApiTests.Backend;

import ApiTests.UsedByAll.MakeRequest;
import ApiTests.ObjectClasses.Withdraw;
import UsedByAll.Config;
import UsedByAll.CsvUsersReader;
import UsedByAll.TestUser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

// * Created for W-xmlm by Fill on 03.12.2014. Insert single withdraw by API
@RunWith(value = Parameterized.class)
public class PostWithdrawInsertToRun {
    private TestUser testUser;

    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_PostWithdrawInsertToRun(");
    }

    public PostWithdrawInsertToRun(TestUser user){
        this.testUser = user;
    }

    @Test
    public void testPostWithdrawInsert() throws IOException, JSONException {
        String siteUrl = Config.getConfig().getProtocol() + Config.getConfig().getScheme(); // Урл проверяемого сайта
        Withdraw originalOne = new GetWithdrawsToRun(testUser).getAnyWithdraw(testUser, siteUrl);
        assertFalse("Withdraw not found", originalOne == null);
        Withdraw newOne = null;
        String newJson = null;
        assertTrue("Bank details is neither \"ePayment\" or \"SWIFT\" ", originalOne.getMerchant().equals("ePayment") || originalOne.getMerchant().equals("SWIFT"));
        if (originalOne.getMerchant().equals("ePayment")) {
            newOne = new Withdraw(originalOne.getId(), originalOne.getUserId(), originalOne.getMerchantId(), originalOne.getOperationId(), originalOne.getAmount() + 50, originalOne.getCreatedDate(), originalOne.getStatus(), originalOne.getEpid() + "0", originalOne.getMerchant());
            newJson = "{\"user_id\": " + newOne.getUserId() + ", \"merchant_id\": " + newOne.getMerchantId() + ", \"amount\": " + newOne.getAmount() + ", \"details\": { \"epid\": \"" + newOne.getEpid() + "\" }, \"created_date\": \"" + newOne.getCreatedDate() + "\", \"status\": " + newOne.getStatus() + "}";
        }
        else if (originalOne.getMerchant().equals("SWIFT")) {
            newOne = new Withdraw(originalOne.getId(), originalOne.getUserId(), originalOne.getMerchantId(), originalOne.getOperationId(), originalOne.getAmount() + 50, originalOne.getCreatedDate(), originalOne.getStatus(), originalOne.getSwiftName() + "1", originalOne.getSwiftAddress() + "2", originalOne.getBankName() + "3", originalOne.getBankAddress() + "4", originalOne.getAccountIban(), originalOne.getSwiftCode() + "6", originalOne.getMerchant());
            newJson = "{\"user_id\": " + newOne.getUserId() + ", \"merchant_id\": " + newOne.getMerchantId() + ", \"amount\": " + newOne.getAmount() + ", \"details\": { \"name\": \"" + newOne.getSwiftName() + "\", \"address\": \"" + newOne.getSwiftAddress() + "\", \"bankName\": \"" + newOne.getBankName() + "\", \"bankAddress\": \"" + newOne.getBankAddress() + "\", \"accountIban\": \"" + newOne.getAccountIban() +"\", \"swiftCode\": \"" + newOne.getSwiftCode() + "\" }, \"created_date\": \"" + newOne.getCreatedDate() + "\", \"status\": " + newOne.getStatus() + "}";
        }
        long startTime;
        long elapsedTime;

        // Содзаем URL
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, testUser, "money/api/withdraws/insert/", "POST", "application/json", "application/json", true);
        OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
        assertFalse("NewJson is null", newJson == null);
        out.write(newJson);
        out.close();
        InputStream inStrm = httpCon.getInputStream();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);
        elapsedTime = System.currentTimeMillis() - startTime;
        InputStreamReader isReader = new InputStreamReader(inStrm);
        BufferedReader br = new BufferedReader(isReader);
        String result = "";
        String line;
        while ((line = br.readLine()) != null) {
            result += line;
        }
        br.close();

        // Берем из респонса id новой операции
        JSONObject response = new JSONObject(result);
        JSONArray reports = response.getJSONArray("reports");
        JSONObject report = reports.getJSONObject(0);
        int newOneId = report.getInt("id");

        // Проверяем GET-запросом, что данные обновились
        Withdraw changedOne = new GetWithdrawsToRun(testUser).getWithdrawByParameter("id", newOneId, testUser, siteUrl);
        assertTrue("Check modified data saved correctly", newOne.equalsWithNew(changedOne));
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
    }
}
