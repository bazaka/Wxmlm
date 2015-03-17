package ApiTests.Backend;

// * Created for W-xmlm by Fill on 24.02.2015.

import ApiTests.ObjectClasses.IncomeDetails;
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

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.*;

@RunWith(value = Parameterized.class)
public class GetIncomeDetailsToRun {
    private TestUser testUser;

    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_BackendAPITest(");
    }

    public GetIncomeDetailsToRun(TestUser user){
        this.testUser = user;
    }

    @Test
    public void testGetIncomeDetails() throws Exception{
        String siteUrl = Config.getConfig().getProtocol() + Config.getConfig().getScheme(); // Урл проверяемого сайта
        long startTime;
        long elapsedTime;
        String url = "money/api/income-details/";
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
        assertNotEquals("Получен пустой массив. Проверить метод с наличием объектов.", jsonArr.length(), 0);

        for(int i = 0; i<jsonArr.length(); i++)
        {
            JSONObject object = jsonArr.getJSONObject(i);
            JSONArray purchaseIDs = object.getJSONArray("purchase_ids");

            assertTrue("Incorrect id", ValidationChecker.checkIdValue(object.getInt("id")));
            assertTrue("Incorrect operation_id", ValidationChecker.checkIdValue(object.getInt("operation_id")));
            assertTrue("Incorrect user_id", ValidationChecker.checkIdValue(object.getInt("user_id")));
            assertTrue("Incorrect time_online", ValidationChecker.checkPositiveInt(object.getInt("time_online")));
            assertTrue("Incorrect time_online_paid", ValidationChecker.checkPositiveInt(object.getInt("time_online_paid")));
            assertTrue("Incorrect created_date", ValidationChecker.checkDateTimeString(object.getString("created_date")));
            assertTrue("Incorrect updated_date", ValidationChecker.checkDateTimeString(object.getString("updated_date")));
            for (int j = 0; j < purchaseIDs.length(); j++) {
                assertTrue("Incorrect purchaseIDs",ValidationChecker.checkIdValue(purchaseIDs.getInt(j)));
            }
            assertEquals("Incorrect count of Json Objects", object.length(), 8);
        }
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
    }

    public IncomeDetails getAnyIncomeDetails(TestUser user, String siteUrl) throws IOException {
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, user, "money/api/income-details/", 500, "GET");
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
            JSONArray purchaseIdsArray = object.getJSONArray("purchase_ids");
            int[] purchaseIds = new int[purchaseIdsArray.length()];
            for (int i = 0; i < purchaseIdsArray.length(); i++) {
                purchaseIds[i] = purchaseIdsArray.getInt(i);
            }
            return new IncomeDetails(object.getInt("id"), object.getInt("operation_id"), object.getInt("user_id"), purchaseIds, object.getInt("time_online"), object.getInt("time_online_paid"), object.getString("created_date"), object.getString("updated_date"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public IncomeDetails getIncomeDetailsByParameter(String parameterName, int parameterValue, TestUser user, String siteUrl) throws IOException {
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, user, "money/api/income-details/", 1, "GET");
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
                    JSONArray purchaseIdsArray = object.getJSONArray("purchase_ids");
                    int[] purchaseIds = new int[purchaseIdsArray.length()];
                    for (int j = 0; j < purchaseIdsArray.length(); j++) {
                        purchaseIds[j] = purchaseIdsArray.getInt(j);
                    }
                    return new IncomeDetails(object.getInt("id"), object.getInt("operation_id"), object.getInt("user_id"), purchaseIds, object.getInt("time_online"), object.getInt("time_online_paid"), object.getString("created_date"), object.getString("updated_date"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
