package ApiTests.Backend;

import ApiTests.ObjectClasses.MerchantRequest;
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

// * Created for W-xmlm by Fill on 16.03.2015.
@RunWith(value = Parameterized.class)
public class GetMerchantRequestsToRun {
    private TestUser testUser;

    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_BackendAPITest(");
    }

    public GetMerchantRequestsToRun(TestUser user){
        this.testUser = user;
    }

    @Test
    public void testGetMerchantRequests() throws Exception {
        String siteUrl = Config.getConfig().getProtocol() + Config.getConfig().getScheme(); // Урл проверяемого сайта
        long startTime;
        long elapsedTime;
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, testUser, "money/api/merchant-requests/", 500, "GET");
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
        assertNotEquals("Получен пустой массив. Проверить метод с наличием объектов.", jsonArr.length(), 0);
        for (int i = 0; i < jsonArr.length(); i++) {
            JSONObject object = jsonArr.getJSONObject(i);
            JSONArray files = object.getJSONArray("files");

            assertTrue("Incorrect request_id", ValidationChecker.checkIdValue(object.getInt("request_id")));
            assertTrue("Incorrect user_id", ValidationChecker.checkIdValue(object.getInt("user_id")));
            for (int j = 0; j < files.length(); j++) {
                assertTrue("Incorrect files",ValidationChecker.checkURLOnDomain(files.getString(j), siteUrl));
            }
            assertTrue("Incorrect user_comment", ValidationChecker.checkStringOrNull(object.get("user_comment")));
            assertTrue("Incorrect status", ValidationChecker.checkMerchantRequestStatus(object.getInt("status")));
            assertTrue("Incorrect created_date", ValidationChecker.checkDateTimeString(object.getString("created_date")));
            assertTrue("Incorrect updated_date", ValidationChecker.checkDateTimeString(object.getString("updated_date")));
            assertEquals("Incorrect count of Json parameters", object.length(), 7);
        }
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
    }

    public MerchantRequest getAnyMerchantRequest(TestUser user, String siteUrl) throws IOException {
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, user, "money/api/merchant-requests/", 500, "GET");
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
            JSONArray filesArray = object.getJSONArray("files");
            String[] files = new String[filesArray.length()];
            for (int i = 0; i < filesArray.length(); i++) {
                files[i] = filesArray.getString(i);
            }
            return new MerchantRequest(object.getInt("request_id"), object.getInt("user_id"), files, object.get("user_comment"), object.getInt("status"), object.getString("created_date"), object.getString("updated_date"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public MerchantRequest getMerchantRequestByParameter(String parameterName, int parameterValue, TestUser user, String siteUrl) throws IOException {
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, user, "money/api/merchant-requests/", 500, "GET");
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
            for (int i = 0; i < jsonArr.length(); i++) {
                JSONObject object = jsonArr.getJSONObject(i);
                if (object.getInt(parameterName) == parameterValue) {
                    JSONArray filesArray = object.getJSONArray("files");
                    String[] files = new String[filesArray.length()];
                    for (int j = 0; j < filesArray.length(); j++) {
                        files[j] = filesArray.getString(j);
                    }
                    return new MerchantRequest(object.getInt("request_id"), object.getInt("user_id"), files, object.get("user_comment"), object.getInt("status"), object.getString("created_date"), object.getString("updated_date"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
