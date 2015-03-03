package ApiTests.Backend;

import ApiTests.ObjectClasses.IncomeDetails;
import ApiTests.UsedByAll.MakeRequest;
import UsedByAll.Config;
import UsedByAll.CsvUsersReader;
import UsedByAll.TestUser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.Collection;
import java.io.*;

import static org.junit.Assert.assertTrue;

// * Created for W-xmlm by Fill on 03.03.2015.
@RunWith(value = Parameterized.class)
public class PostIncomeDetailsInsertToRun {
    private TestUser testUser;

    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_BackendAPITest(");
    }

    public PostIncomeDetailsInsertToRun(TestUser user) {
        this.testUser = user;
    }

    @Test
    public void testPostIncomeDetailsInsert() throws IOException, JSONException {
        String siteUrl = Config.getConfig().getProtocol() + Config.getConfig().getScheme(); // Урл проверяемого сайта
        long startTime;
        long elapsedTime;
        IncomeDetails originalOne = new GetIncomeDetailsToRun(testUser).getAnyIncomeDetails(testUser, siteUrl);
        IncomeDetails newOne = new IncomeDetails(originalOne.getId(), originalOne.getOperationId(), originalOne.getUserId(), originalOne.getPurchaseIds(), originalOne.getTimeOnline() + 500, originalOne.getTimeOnlinePaid() + 200, originalOne.getCreatedDate(), originalOne.getUpdatedDate());
        String purchaseIdsString = "[";
        for (int i = 0; i < originalOne.getPurchaseIds().length; i++) {
            if (i!=0){
                purchaseIdsString = purchaseIdsString + ", ";
            }
            purchaseIdsString = purchaseIdsString + originalOne.getPurchaseIds(i);
        }
        purchaseIdsString = purchaseIdsString + "]";
        String newJson = "{\"time_online\": " + newOne.getTimeOnline() + ", \"time_online_paid\": " + newOne.getTimeOnlinePaid() + ", \"operation_id\": " + newOne.getOperationId() + ", \"purchase_ids\": " + purchaseIdsString + ", \"user_id\": " + newOne.getUserId() + "}";

        // Содзаем URL
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, testUser, "money/api/income-details/insert/", "POST", "application/json", "application/json", true);
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
        JSONObject response = new JSONObject(result);
        JSONArray reports = response.getJSONArray("reports");
        JSONObject report = reports.getJSONObject(0);
        newOne.setId(report.getInt("id"));

        // Проверяем GET-запросом, что данные обновились
        IncomeDetails changedOne = new GetIncomeDetailsToRun(testUser).getIncomeDetailsByParameter("id", newOne.getId(), testUser, siteUrl);
        assertTrue("Check modified data saved correctly", newOne.equalsExceptUpdatedDate(changedOne, false));
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
    }
}