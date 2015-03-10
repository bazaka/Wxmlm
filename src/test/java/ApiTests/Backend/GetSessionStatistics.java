package ApiTests.Backend;

import ApiTests.UsedByAll.MakeRequest;
import ApiTests.UsedByAll.ValidationChecker;
import UsedByAll.Config;
import UsedByAll.CsvUsersReader;
import UsedByAll.TestUser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by User on 3/10/2015.
 */
@RunWith(value = Parameterized.class)
public class GetSessionStatistics {
    private TestUser testUser;

    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_BackendAPITest(");
    }

    public GetSessionStatistics(TestUser user){
        this.testUser = user;
    }
    @Test
    public void testGetSessionStatistics() throws JSONException, IOException {
        String siteUrl = Config.getConfig().getProtocol() + Config.getConfig().getScheme(); // Урл проверяемого сайта
        long startTime;
        long elapsedTime;
        String url = "users/api/session/statistics";
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, testUser, url, 500, "GET");
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

        //JSON
        JSONArray jsonArr = new JSONArray(result);
        //Structure
        assertNotNull("Получен пустой массив. Проверить метод с наличием объектов.", jsonArr.length());

        for(int i = 0; i<jsonArr.length(); i++) {
            JSONObject object = jsonArr.getJSONObject(i);
            assertTrue("Incorrect id", ValidationChecker.checkIdValue(object.getInt("id")));
            assertTrue("Incorrect user_id", ValidationChecker.checkIdValue(object.getInt("user_id")));
            assertTrue("Incorrect purchase_id", ValidationChecker.checkIdValue(object.getInt("purchase_id")));
            assertTrue("Incorrect actual", ValidationChecker.checkBooleanValue(object.getBoolean("actual")));
            assertTrue("Incorrect created", ValidationChecker.checkDateTimeString(object.getString("created")));
            assertTrue("Incorrect lifetime", ValidationChecker.checkPositiveInt(object.getInt("lifetime")));

            assertEquals("Incorrect count of Json Objects", object.length(), 6);

        }
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);


    }

}
