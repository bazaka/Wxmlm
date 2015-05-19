package ApiTests.Backend;

import ApiTests.ObjectClasses.NetPurchase;
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

import static org.junit.Assert.assertTrue;

// * Created for W-xmlm by Fill on 18.05.2015.
@RunWith(value = Parameterized.class)
public class PostNetPurchaseInsertToRun {
    private TestUser testUser;

    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_BackendAPITest(");
    }

    public PostNetPurchaseInsertToRun(TestUser user) {
        this.testUser = user;
    }

    @Test
    public void testPostNetPurchaseInsert() throws IOException, JSONException {
        String siteUrl = Config.getConfig().getProtocol() + Config.getConfig().getScheme(); // Урл проверяемого сайта
        long startTime;
        long elapsedTime;
        NetPurchase originalOne = new GetNetPurchasesToRun(testUser).getAnyNetPurchase(testUser, siteUrl);
        int newUsersNet;
        if (originalOne.getUsersNet() == 1) {
            newUsersNet = 0;
        }
        else newUsersNet = 1;
        int newInTurnover;
        if (originalOne.getInTurnover() == 1) {
            newInTurnover= 0;
        }
        else newInTurnover = 1;
        NetPurchase newOne = new NetPurchase(originalOne.getId(), originalOne.getUserId(), originalOne.getPurchaseId(), newUsersNet, newInTurnover, originalOne.getCreatedDate());
        String newJson = "{\"user_id\":" + newOne.getUserId() + ", \"purchase_id\":" + newOne.getPurchaseId() + ", \"users_net\":" + newOne.getUsersNet() + ", \"in_turnover\": " + newOne.getInTurnover() + "}";

        // Содзаем URL
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, testUser, "users/api/network/purchase/insert/", "POST", "application/json", "application/json", true);
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

        // Берем из респонса id новой записи
        JSONObject response = new JSONObject(result);
        JSONArray reports = response.getJSONArray("reports");
        JSONObject report = reports.getJSONObject(0);
        newOne.setId(report.getInt("id"));

        // Проверяем GET-запросом, что данные обновились
        NetPurchase changedOne = new GetNetPurchasesToRun(testUser).getNetPurchaseByParameter("id", newOne.getId(), testUser, siteUrl);
        assertTrue("Check modified data saved correctly", newOne.equalsExceptUpdatedDate(changedOne, false));
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
    }

}
