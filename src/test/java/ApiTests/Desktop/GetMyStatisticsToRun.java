package ApiTests.Desktop;

import ApiTests.UsedByAll.MakeRequest;
import ApiTests.UsedByAll.ValidationChecker;
import UsedByAll.CsvUsersReader;
import UsedByAll.TestUser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Collection;

import static org.junit.Assert.*;

// * Created for W-xmlm by Fill on 17.03.2015.
@RunWith(value = Parameterized.class)
public class GetMyStatisticsToRun {
    private TestUser testUser;
    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_DesktopAPITest(");
    }

    public GetMyStatisticsToRun(TestUser user){
        this.testUser=user;
    }
    @Test
    public void testGetMyStatistics() throws Exception {
        String siteUrl = UsedByAll.Config.getConfig().getProtocol() + UsedByAll.Config.getConfig().getScheme(); // Урл проверяемого сайта
        //String token = new GetTokenToRun(testUser).getToken();
        long startTime;
        long elapsedTime;
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl,testUser, "users/api/desktop/get-statistic/?_format=json", 20, "GET");
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
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);

        //Парсим JSON
        JSONObject object = new JSONObject(result);
        System.out.println(result);
        JSONArray sessions = object.getJSONArray("sessions");
        assertNotEquals("Получен пустой массив. Проверить метод с наличием объектов.", sessions.length(), 0);
        for (int i=0; i<sessions.length(); i++){
            JSONObject session = sessions.getJSONObject(i);
            assertTrue("Incorrect id", ValidationChecker.checkIdValue(session.getInt("id")));
            assertTrue("Incorrect actual", ValidationChecker.checkBooleanValue(session.getBoolean("actual")));
            assertTrue("Incorrect created", ValidationChecker.checkDateTimeString(session.getString("created")));
            assertTrue("Incorrect lifetime", ValidationChecker.checkPositiveInt(session.getInt("lifetime")));
            assertTrue("Incorrect last_ping_date", ValidationChecker.checkDateTimeString(session.getString("last_ping_date")));
            assertEquals("Incorrect count of JSON Objects", session.length(),5);
        }
    }

}
