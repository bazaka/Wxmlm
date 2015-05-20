package ApiTests.Backend;

import ApiTests.UsedByAll.DateForAPI;
import ApiTests.UsedByAll.MakeRequest;
import ApiTests.ObjectClasses.Operation;
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
import java.util.Calendar;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

// * Created for W-xmlm by Fill on 01.12.2014. Insert single operation
@RunWith(value = Parameterized.class)
public class PostOperationInsertToRun {
    private TestUser testUser;

    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_BackendAPITest(");
    }

    public PostOperationInsertToRun(TestUser user){
        this.testUser = user;
    }

    @Test
    public void testPostOperationInsert() throws IOException, JSONException {
        String siteUrl = Config.getConfig().getProtocol() + Config.getConfig().getScheme(); // Урл проверяемого сайта
        long startTime;
        long elapsedTime;
        Operation originalOne = new GetOperationsToRun(testUser).getAnyOperation(testUser, siteUrl);
        Operation newOne = new Operation(originalOne.getId(), originalOne.getTargetAccountId(), originalOne.getSourceAccountId(), originalOne.getPurchaseId(), originalOne.getInitiatorUserId(), DateForAPI.makeDateTimeString(Calendar.getInstance(), 0), originalOne.getAmount() + 50, originalOne.getStatus(), originalOne.getType(), !originalOne.getQuarantine(), originalOne.getParentOperationId(), originalOne.getSourceUserId(), originalOne.getTargetUserId());
        String newJson = "{\"target_account_id\":\"" + newOne.getTargetAccountId() + "\", \"source_account_id\":" + newOne.getSourceAccountId() + ", \"purchase_id\":" + newOne.getPurchaseId() + ", \"initiator_user_id\": " + newOne.getInitiatorUserId() + ", \"created_date\": \"" + newOne.getCreatedDate() + "\", \"amount\": \"" + newOne.getAmount() + "\", \"status\": \"" + newOne.getStatus() + "\", \"type\": " + newOne.getType() + ", \"quarantine\": " + newOne.getQuarantine() + ", \"parent_operation_id\": " + newOne.getParentOperationId() + ", \"source_user_id\": " + newOne.getSourceUserId() + ", \"target_user_id\": " + newOne.getTargetUserId() + "}";

        // Содзаем URL
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, testUser, "money/api/operations/insert/", "POST", "application/json", "application/json", true);
        OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
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
        System.out.println(result);
        JSONObject response = new JSONObject(result);
        JSONArray reports = response.getJSONArray("reports");
        JSONObject report = reports.getJSONObject(0);
        int newOneId = report.getInt("id");

        // Проверяем GET-запросом, что данные обновились
        Operation changedOne = new GetOperationsToRun(testUser).getOperationByParameter("id", newOneId, testUser, siteUrl);
        assertTrue("Check modified data saved correctly", newOne.equalsExceptUpdatedDate(changedOne));
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
    }
}
