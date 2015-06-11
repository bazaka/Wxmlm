package ApiTests.Application;

import ApiTests.UsedByAll.MakeRequest;
import ApiTests.UsedByAll.ValidationChecker;
import UsedByAll.Config;
import UsedByAll.CsvUsersReader;
import UsedByAll.TestUser;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// * Created for W-xmlm by Fill on 04.12.2014. Gets application's current version
@RunWith(value = Parameterized.class)
public class GetApplicationInfoToRun {
    private TestUser testUser;

    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_ApplicationAPITest(");
    }

    public GetApplicationInfoToRun(TestUser user){
        this.testUser = user;
    }

    @Test
    public void testGetApplicationInfo() throws Exception {
        String siteUrl = Config.getConfig().getProtocol() + Config.getConfig().getScheme(); // Урл проверяемого сайта
        InputStream inStrm;
        long startTime;
        long elapsedTime;
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, testUser, "application/api/desktop/get-application-info/", "GET");
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);
        inStrm = httpCon.getInputStream();
        elapsedTime = System.currentTimeMillis() - startTime;
        InputStreamReader isReader = new InputStreamReader(inStrm);
        BufferedReader br = new BufferedReader(isReader);
        String result = "";
        String line;
        while ((line = br.readLine()) != null) {
            result += line;
        }
        br.close();

        //Парсим JSON
        JSONObject object = new JSONObject(result);
        //Проверяем структуру
        assertTrue("Incorrect id", ValidationChecker.checkIdValue(object.getInt("id")));
        assertTrue("Incorrect version", ValidationChecker.checkStringNotNull(object.getString("version")));
        assertTrue("Incorrect filename", ValidationChecker.checkStringNotNull(object.getString("filename")));
        assertTrue("Incorrect path", ValidationChecker.checkStringNotNull(object.getString("path")));
        assertTrue("Incorrect status", object.getBoolean("status"));
        assertEquals("Incorrect count of Json parameters", object.length(), 5);
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
    }
}