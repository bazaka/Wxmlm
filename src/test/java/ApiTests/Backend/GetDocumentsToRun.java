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

import static org.junit.Assert.*;

/**
 * Created by User on 12/11/2014. Проверяет метод АПИ GET Documents
 */
@RunWith(value = Parameterized.class)
public class GetDocumentsToRun {
    private TestUser testUser;
    static final String url = "users/api/documents/";

    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_BackendAPITest(");
    }

    public GetDocumentsToRun(TestUser user){
        this.testUser = user;
    }

    @Test
    public void testGetDocuments() throws IOException, JSONException {
        String siteUrl = Config.getConfig().getProtocol() + Config.getConfig().getScheme();
        long startTime;
        long elapsedTime;
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, testUser, url, 150, "GET");
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
        assertNotEquals("Получен пустой массив. Проверить метод с наличием объектов.", jsonArr.length(), 0);
        for(int i = 0; i<jsonArr.length(); i++)
        {
            JSONObject object = jsonArr.getJSONObject(i);
            assertTrue("Incorrect id", ValidationChecker.checkIdValue(object.getInt("id")));
            assertTrue("Incorrect user_id", ValidationChecker.checkIdValue(object.getInt("user_id")));
            assertTrue("Incorrect file_name", ValidationChecker.checkFileName(object.get("file_name").toString()));
            assertTrue("Incorrect path", ValidationChecker.checkStringNotNull(object.getString("path")));
            assertTrue("Incorrect created_date", ValidationChecker.checkDateTimeString(object.getString("created_date")));
            assertTrue("Incorrect updated_date", ValidationChecker.checkDateTimeString(object.getString("updated_date")));
            assertTrue("Incorrect status", ValidationChecker.checkBooleanValue(object.getBoolean("status")));
            assertTrue("Incorrect approve_id", ValidationChecker.checkIdOrNull(object.get("approve_id")));
            assertTrue("Incorrect md5", ValidationChecker.checkStringNotNull("md5"));

            assertEquals("Incorrect count of JSON objects", object.length(), 9);
        }
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
    }
    public static int[] getDocumentsId(String siteUrl, TestUser testUser) throws IOException, JSONException {
        long startTime;
        long elapsedTime;
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, testUser, url, 150, "GET");
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

        JSONArray jsonArr = new JSONArray(result);
        int ids[] = new int[jsonArr.length()];
        assertNotEquals("Получен пустой массив. Проверить метод с наличием объектов.", jsonArr.length(), 0);
        for(int i=0; i<jsonArr.length(); i++){
            JSONObject object = jsonArr.getJSONObject(i);
            ids[i] = object.getInt("id");
        }
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
        return ids;
    }
}
