package ApiTests.Backend;

import ApiTests.ObjectClasses.Approve;
import ApiTests.UsedByAll.MakeRequest;
import ApiTests.UsedByAll.ValidationChecker;
import UsedByAll.Config;
import UsedByAll.CsvUsersReader;
import UsedByAll.TestUser;
import org.json.JSONArray;
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

// * Created for W-xmlm by Fill on 05.03.2015.
@RunWith(value = Parameterized.class)
public class GetApprovesToRun {
    private TestUser testUser;

    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_BackendAPITest(");
    }

    public GetApprovesToRun(TestUser user){
        this.testUser = user;
    }

    @Test
    public void testGetApproves() throws Exception {
        String siteUrl = Config.getConfig().getProtocol() + Config.getConfig().getScheme(); // Урл проверяемого сайта
        long startTime;
        long elapsedTime;
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, testUser, "users/api/approves/", 500, "GET");
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

        //Парсим JSON
        JSONArray jsonArr = new JSONArray(result);
        //Проверяем структуру
        assertNotSame("Получен пустой массив. Рекомендуется проверить метод с наличием объектов. ", jsonArr.length(), 0);
        for (int i = 0; i < jsonArr.length(); i++) {
            JSONObject object = jsonArr.getJSONObject(i);
            JSONArray documents = object.getJSONArray("documents");

            assertTrue("Incorrect id", ValidationChecker.checkIdValue(object.getInt("id")));
            assertTrue("Incorrect user_id", ValidationChecker.checkIdValue(object.getInt("user_id")));
            assertTrue("Incorrect approve_user_id", ValidationChecker.checkIdOrNull(object.get("approve_user_id")));
            assertTrue("Incorrect created_date", ValidationChecker.checkDateTimeString(object.getString("create_date")));
            assertTrue("Incorrect updated_date", ValidationChecker.checkDateTimeString(object.getString("update_date")));
            assertTrue("Incorrect approve_date", ValidationChecker.checkStringOrNull(object.getString("approve_date")));
            assertTrue("Incorrect status", ValidationChecker.checkApproveStatus(object.getInt("status")));
            for (int j = 0; j < documents.length(); j++) {
                assertTrue("Incorrect documents",ValidationChecker.checkIdValue(documents.getInt(j)));
            }
            assertEquals("Incorrect count of Json parameters", object.length(), 8);
        }
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
    }

    public Approve getAnyApprove(String siteUrl) throws IOException {
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, testUser, "users/api/approves/", 500, "GET");
        InputStream inStrm = httpCon.getInputStream();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);
        InputStreamReader isReader = new InputStreamReader(inStrm);
        BufferedReader br = new BufferedReader(isReader);
        String result = "";
        String line;
        while ((line = br.readLine()) != null) {
            result += line;
        }
        br.close();
        try {
            JSONArray jsonArr = new JSONArray(result);
            assertFalse("There is an empty Array", jsonArr.length() == 0);
            JSONObject object = jsonArr.getJSONObject(0);
            JSONArray documentsArray = object.getJSONArray("documents");
            int[] documents = new int[documentsArray.length()];
            for (int i = 0; i < documentsArray.length(); i++) {
                documents[i] = documentsArray.getInt(i);
            }
            return new Approve(object.getInt("id"), object.getInt("user_id"), object.get("approve_user_id"), object.getString("create_date"), object.getString("update_date"), object.get("approve_date"), object.getInt("status"), documents);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Approve getApproveByParameter(String parameterName, int parameterValue, String siteUrl) throws IOException {
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, testUser, "users/api/approves/", 1, "GET");
        InputStream inStrm = httpCon.getInputStream();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);
        InputStreamReader isReader = new InputStreamReader(inStrm);
        BufferedReader br = new BufferedReader(isReader);
        String result = "";
        String line;
        while ((line = br.readLine()) != null) {
            result += line;
        }
        br.close();
        try {
            JSONArray jsonArr = new JSONArray(result);
            assertFalse("There is an empty Array", jsonArr.length() == 0);
            for (int i = 0; i < jsonArr.length(); i++) {
                JSONObject object = jsonArr.getJSONObject(i);
                JSONArray documentsArray = object.getJSONArray("documents");
                int[] documents = new int[documentsArray.length()];
                for (int j = 0; j < documentsArray.length(); j++) {
                    documents[j] = documentsArray.getInt(j);
                }
                if (object.getInt(parameterName) == parameterValue) {
                    return new Approve(object.getInt("id"), object.getInt("user_id"), object.get("approve_user_id"), object.getString("create_date"), object.getString("update_date"), object.get("approve_date"), object.getInt("status"), documents);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
