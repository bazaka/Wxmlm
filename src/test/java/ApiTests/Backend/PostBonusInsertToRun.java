package ApiTests.Backend;

import ApiTests.ObjectClasses.Bonus;
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

import java.io.*;
import java.net.HttpURLConnection;
import java.util.Collection;

import static org.junit.Assert.*;

// * Created for W-xmlm by Fill on 19.03.2015.
@RunWith(value = Parameterized.class)
public class PostBonusInsertToRun {
    private TestUser testUser;

    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_BackendAPITest(");
    }

    public PostBonusInsertToRun(TestUser user) {
        this.testUser = user;
    }

    @Test
    public void testPostIncomeDetailsInsert() throws IOException, JSONException {
        String siteUrl = Config.getConfig().getProtocol() + Config.getConfig().getScheme(); // Урл проверяемого сайта
        long startTime;
        long elapsedTime;
        Bonus originalOne = new GetBonusesToRun(testUser).getAnyBonus(siteUrl);
        int typeValue = originalOne.getType() - 1;
        if(typeValue == -1){
            typeValue = 7;
        }
        Bonus newOne = new Bonus(originalOne.getId(), originalOne.getUserId(), originalOne.getPartnerId(), originalOne.getOperationId(), originalOne.getPurchaseId(), typeValue, originalOne.getPercent() + 1, originalOne.getCreatedDate(), originalOne.getUpdatedDate());
        String newJson = "{\"user_id\": " + newOne.getUserId() + ", \"partner_id\": " + newOne.getPartnerId() + ", \"operation_id\": " + newOne.getOperationId() + ", \"purchase_id\": " + newOne.getPurchaseId() + ", \"type\": " + newOne.getType() + ", \"percent\": " + newOne.getPercent() + "}";

        // Содзаем URL
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, testUser, "money/api/bonuses/insert/", "POST", "application/json", "application/json", true);
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
        Bonus changedOne = new GetBonusesToRun(testUser).getBonusByParameter("id", newOne.getId(), siteUrl);
        assertTrue("Check modified data saved correctly", newOne.equalsExceptUpdatedDate(changedOne, false));
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
    }

}
