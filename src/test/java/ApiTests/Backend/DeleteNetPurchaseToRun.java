package ApiTests.Backend;

import ApiTests.ObjectClasses.NetPurchase;
import ApiTests.UsedByAll.MakeRequest;
import UsedByAll.Config;
import UsedByAll.CsvUsersReader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import UsedByAll.TestUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.io.*;
import java.net.HttpURLConnection;
import java.util.Collection;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

// * Created for W-xmlm by Fill on 26.05.2015.
@RunWith(value = Parameterized.class)
public class DeleteNetPurchaseToRun {
    private TestUser testUser;

    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_BackendAPITest(");
    }

    public DeleteNetPurchaseToRun(TestUser user) {
        this.testUser = user;
    }

    @Test
    public void testDeleteNetPurchase() throws IOException, JSONException {
        String siteUrl = Config.getConfig().getProtocol() + Config.getConfig().getScheme(); // Урл проверяемого сайта
        long startTime;
        long elapsedTime;
        NetPurchase originalOne = new GetNetPurchasesToRun(testUser).getAnyNetPurchase(testUser, siteUrl);
        System.out.println(originalOne.getId());

        // Содзаем URL
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, testUser, "users/api/network/purchase/delete/" + originalOne.getId(), "DELETE", "application/json", "application/json", true);
        OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write("");
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
        System.out.println(result);
        // Берем из респонса id новой записи

        JSONObject response = new JSONObject(result);
        JSONArray reports = response.getJSONArray("reports");
        JSONObject report = reports.getJSONObject(0);

        // Проверяем GET-запросом, что данные обновились
        NetPurchase changedOne = new GetNetPurchasesToRun(testUser).getNetPurchaseByParameter("id", originalOne.getId(), testUser, siteUrl);
        assertNull("Check data deleted correctly", changedOne);
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
    }
}
