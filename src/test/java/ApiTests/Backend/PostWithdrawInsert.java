package ApiTests.Backend;

import ApiTests.UsedByAll.MakeRequest;
import ApiTests.ObjectClasses.Withdraw;
import UsedByAll.TestUser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;

import static org.junit.Assert.assertTrue;

// * Created for W-xmlm by Fill on 03.12.2014. Insert single withdraw by API
public class PostWithdrawInsert {
    @Test
    public boolean testPostWithdrawInsert(String scheme, TestUser user) throws IOException, JSONException {
        Withdraw originalOne = new GetWithdraws().getAnyWithdraw(user, scheme);
        if (originalOne == null) {
            return false;
        }
        Withdraw newOne;
        String newJson;
        if (originalOne.getMerchant().equals("ePayment")) {
            newOne = new Withdraw(originalOne.getId(), originalOne.getUserId(), originalOne.getMerchantId(), originalOne.getOperationId(), originalOne.getAmount() + 50, originalOne.getCreatedDate(), originalOne.getStatus(), originalOne.getEpid() + "0", originalOne.getMerchant());
            newJson = "{\"user_id\": " + newOne.getUserId() + ", \"merchant_id\": " + newOne.getMerchantId() + ", \"amount\": " + newOne.getAmount() + ", \"details\": { \"epid\": \"" + newOne.getEpid() + "\" }, \"created_date\": \"" + newOne.getCreatedDate() + "\", \"status\": " + newOne.getStatus() + "}";
        }
        else if (originalOne.getMerchant().equals("SWIFT")) {
            newOne = new Withdraw(originalOne.getId(), originalOne.getUserId(), originalOne.getMerchantId(), originalOne.getOperationId(), originalOne.getAmount() + 50, originalOne.getCreatedDate(), originalOne.getStatus(), originalOne.getSwiftName() + "1", originalOne.getSwiftAddress() + "2", originalOne.getBankName() + "3", originalOne.getBankAddress() + "4", originalOne.getAccountIban(), originalOne.getSwiftCode() + "6", originalOne.getMerchant());
            newJson = "{\"user_id\": " + newOne.getUserId() + ", \"merchant_id\": " + newOne.getMerchantId() + ", \"amount\": " + newOne.getAmount() + ", \"details\": { \"name\": \"" + newOne.getSwiftName() + "\", \"address\": \"" + newOne.getSwiftAddress() + "\", \"bankName\": \"" + newOne.getBankName() + "\", \"bankAddress\": \"" + newOne.getBankAddress() + "\", \"accountIban\": \"" + newOne.getAccountIban() +"\", \"swiftCode\": \"" + newOne.getSwiftCode() + "\" }, \"created_date\": \"" + newOne.getCreatedDate() + "\", \"status\": " + newOne.getStatus() + "}";
        }
        else {
            System.out.println("Bank details is missing in Withdraw object");
            return false;
        }

        // Содзаем URL
        HttpURLConnection httpCon = MakeRequest.getConnection(scheme, user, "money/api/withdraws/insert/", "POST", "application/json", "application/json", true);
        OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(newJson);
        out.close();
        InputStream inStrm = httpCon.getInputStream();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);
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
        Withdraw changedOne = new GetWithdraws().getWithdrawByParameter("id", newOneId, user, scheme);
        assertTrue("Check modified data saved correctly", newOne.equalsWithNew(changedOne));
        return true;
    }
}
