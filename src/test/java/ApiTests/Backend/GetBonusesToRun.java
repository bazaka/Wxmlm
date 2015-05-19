package ApiTests.Backend;

import ApiTests.ObjectClasses.Bonus;
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

// * Created for W-xmlm by Fill on 19.03.2015.
@RunWith(value = Parameterized.class)
public class GetBonusesToRun {
    private TestUser testUser;

    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_BackendAPITest(");
    }

    public GetBonusesToRun(TestUser user){
        this.testUser = user;
    }

    @Test
    public void testGetBonuses() throws Exception {
        String siteUrl = Config.getConfig().getProtocol() + Config.getConfig().getScheme(); // Урл проверяемого сайта
        long startTime;
        long elapsedTime;
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, testUser, "money/api/bonuses/", 500, "GET");
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

            assertTrue("Incorrect id", ValidationChecker.checkIdValue(object.getInt("id")));
            assertTrue("Incorrect user_id", ValidationChecker.checkIdValue(object.getInt("user_id")));
            assertTrue("Incorrect partner_id", ValidationChecker.checkIdValue(object.getInt("partner_id")));
            assertTrue("Incorrect operation_id", ValidationChecker.checkIdValue(object.getInt("operation_id")));
            assertTrue("Incorrect purchase_id", ValidationChecker.checkIdValue(object.getInt("purchase_id")));
            assertTrue("Incorrect type", ValidationChecker.checkBonusTypeValue(object.getInt("type")));
            assertTrue("Incorrect percent", ValidationChecker.checkPositiveInt(object.getInt("percent")));
            assertTrue("Incorrect created_date", ValidationChecker.checkDateTimeString(object.getString("created_date")));
            assertTrue("Incorrect updated_date", ValidationChecker.checkDateTimeString(object.getString("updated_date")));
            assertEquals("Incorrect count of Json parameters", object.length(), 9);
        }
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
    }

    public Bonus getAnyBonus(String siteUrl) throws IOException {
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, testUser, "money/api/bonuses/", 500, "GET");
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
            return new Bonus(object.getInt("id"), object.getInt("user_id"), object.getInt("partner_id"), object.getInt("operation_id"), object.getInt("purchase_id"), object.getInt("type"), object.getInt("percent"), object.getString("created_date"), object.getString("updated_date"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Bonus getBonusByParameter(String parameterName, int parameterValue, String siteUrl) throws IOException {
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, testUser, "money/api/bonuses/", 1, "GET");
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
                if (object.getInt(parameterName) == parameterValue) {
                    return new Bonus(object.getInt("id"), object.getInt("user_id"), object.getInt("partner_id"), object.getInt("operation_id"), object.getInt("purchase_id"), object.getInt("type"), object.getInt("percent"), object.getString("created_date"), object.getString("updated_date"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

}