package ApiTests.Backend;

import ApiTests.ObjectClasses.Config;
import ApiTests.UsedByAll.MakeRequest;
import ApiTests.UsedByAll.ValidationChecker;
import UsedByAll.TestUser;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GetConfig {
    public boolean testGetConfig(String siteUrl, TestUser user) throws Exception {
        long startTime;
        long elapsedTime;
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, user, "config/api/values/", 500, "GET");
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
        if (jsonArr.length() == 0) {
            System.out.print("Получен пустой массив. Рекомендуется проверить метод с наличием объектов. ");
            return false;
        }
        for (int i = 0; i < jsonArr.length(); i++) {
            JSONObject object = jsonArr.getJSONObject(i);

            assertTrue("Incorrect account_id", ValidationChecker.checkIdValue(object.getInt("id")));
            assertTrue("Incorrect user_id", ValidationChecker.checkStringNotNull(object.getString("name")));
            assertTrue("Incorrect account_number", ValidationChecker.checkStringNotNull(object.getString("value")));
            assertEquals("Incorrect count of Json parameters", object.length(), 3);
        }
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
        return true;
    }

    public Config getAnyConfig(TestUser user, String siteUrl) throws IOException {
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, user, "config/api/values/", 50, "GET");
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
            JSONObject object = jsonArr.getJSONObject(0);
            return new Config(object.getInt("id"), object.getString("name"), object.getString("value"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Config getConfigByParameter(String parameterName, int parameterValue, TestUser user, String siteUrl) throws IOException {
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, user, "config/api/values/", 1, "GET");
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
                    return new Config(object.getInt("id"), object.getString("name"), object.getString("value"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}