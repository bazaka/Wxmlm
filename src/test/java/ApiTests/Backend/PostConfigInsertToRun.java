package ApiTests.Backend;

// * Created for W-xmlm by Fill on 20.02.2015.

import ApiTests.ObjectClasses.AConfig;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(value = Parameterized.class)
public class PostConfigInsertToRun {
    private TestUser testUser;

    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_BackendAPITest(");
    }

    public PostConfigInsertToRun(TestUser user){
        this.testUser = user;
    }

    @Test
    public void testPostConfigInsert() throws IOException, JSONException {
        String siteUrl = Config.getConfig().getProtocol() + Config.getConfig().getScheme(); // Урл проверяемого сайта
        long startTime;
        long elapsedTime;
        AConfig originalOne = new GetConfigToRun(testUser).getAnyConfig(testUser, siteUrl);
        AConfig newOne = new AConfig(originalOne.getName() + "New", originalOne.getValue() + "New");
        String newJson = "{\"name\":\"" + newOne.getName() + "\", \"value\": \"" + newOne.getValue() + "\"}";
        System.out.println(newJson);

        // Содзаем URL
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, testUser, "config/api/values/insert/", "POST", "application/json", "application/json", true);
        OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(newJson);
        out.close();
        assertEquals("Check response code is 200", 200, httpCon.getResponseCode());
        InputStream inStrm = httpCon.getInputStream();
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
        AConfig changedOne = new GetConfigToRun(testUser).getConfigByParameter("id", newOne.getId(), testUser, siteUrl);
        assertTrue("Check modified data saved correctly", newOne.equals(changedOne));
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
    }
}
