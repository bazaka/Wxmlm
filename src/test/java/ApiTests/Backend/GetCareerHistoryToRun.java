package ApiTests.Backend;

import ApiTests.ObjectClasses.CareerHistory;
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
import java.util.Calendar;
import java.util.Collection;

import static org.junit.Assert.*;

// * Created for W-xmlm by Fill on 03.03.2015.
@RunWith(value = Parameterized.class)
public class GetCareerHistoryToRun {
    private TestUser testUser;

    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_BackendAPITest(");
    }

    public GetCareerHistoryToRun(TestUser user){
        this.testUser = user;
    }

    @Test
    public void testGetCards() throws Exception {
        String siteUrl = Config.getConfig().getProtocol() + Config.getConfig().getScheme(); // Урл проверяемого сайта
        long startTime;
        long elapsedTime;
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, testUser, "career/api/history/", 500, "GET");
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
            JSONArray members = object.getJSONArray("members");

            assertTrue("Incorrect history_id", ValidationChecker.checkIdValue(object.getInt("history_id")));
            assertTrue("Incorrect user_id", ValidationChecker.checkIdValue(object.getInt("user_id")));
            assertTrue("Incorrect career", ValidationChecker.checkIdValue(object.getInt("career")));
            assertTrue("Incorrect date", ValidationChecker.checkDateTimeString(object.getString("date")));
            for (int j = 0; j < members.length(); j++) {
                assertTrue("Incorrect members",ValidationChecker.checkIdValue(members.getInt(j)));
            }
            assertTrue("Incorrect created_date", ValidationChecker.checkDateTimeString(object.getString("created_date")));
            assertTrue("Incorrect updated_date", ValidationChecker.checkDateTimeString(object.getString("updated_date")));
            assertEquals("Incorrect count of Json parameters", object.length(), 7);
        }
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
    }

    public CareerHistory getAnyCareerHistory(TestUser user, String siteUrl) throws IOException {
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, user, "career/api/history/", 500, "GET");
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
            JSONArray membersArray = object.getJSONArray("members");
            int[] members = new int[membersArray.length()];
            for (int i = 0; i < membersArray.length(); i++) {
                members[i] = membersArray.getInt(i);
            }
            return new CareerHistory(object.getInt("history_id"), object.getInt("user_id"), object.getInt("career"), object.getString("date"), members, object.getString("created_date"), object.getString("updated_date"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public CareerHistory getCareerHistoryByParameter(String parameterName, int parameterValue, TestUser user, String siteUrl) throws IOException {
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, user, "career/api/history/", 1, "GET");
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
                JSONObject object = jsonArr.getJSONObject(0);
                JSONArray membersArray = object.getJSONArray("members");
                int[] members = new int[membersArray.length()];
                for (int j = 0; j < membersArray.length(); j++) {
                    members[j] = membersArray.getInt(j);
                }
                if (object.getInt(parameterName) == parameterValue) {
                    return new CareerHistory(object.getInt("history_id"), object.getInt("user_id"), object.getInt("career"), object.getString("date"), members, object.getString("created_date"), object.getString("updated_date"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
