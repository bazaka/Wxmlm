package ApiTests.Backend;

import ApiTests.ObjectClasses.Card;
import ApiTests.ObjectClasses.FinParameter;
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
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

/**
 * Created by User on 4/8/2015.
 */
@RunWith(value= Parameterized.class)
public class GetFinParameterToRun {
    private TestUser testUser;

    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_BackendAPITest(");
    }

    public GetFinParameterToRun(TestUser user) {
        this.testUser = user;
    }
    @Test
    public void testGetFinParameters() throws  Exception{
        String siteUrl = Config.getConfig().getProtocol() + Config.getConfig().getScheme(); // Урл проверяемого сайта
        long startTime;
        long elapsedTime;
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, testUser, "money/api/parameter/", 500, "GET");
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
            assertTrue("Incorrect operation_type_id", ValidationChecker.checkOperationTypeId(object.getInt("operation_type_id")));
            assertTrue("Incorrect merchant_id", ValidationChecker.checkIdOrNull(object.get("merchant_id")));
            assertTrue("Incorrect payment_merchant_id", ValidationChecker.checkIdOrNull(object.get("payment_merchant_id")));
            assertTrue("Incorrect parameter_id", ValidationChecker.checkIdValue(object.getInt("parameter_id")));
            assertTrue("Incorrect value", ValidationChecker.checkStringNotNull(object.getString("value")));
            assertTrue("Incorrect date_start", ValidationChecker.checkDateTimeString(object.get("date_start").toString()));
            assertTrue("Incorrect date_end", ValidationChecker.checkDateTimeOrNull(object.get("date_end")));
            assertTrue("Incorrect enabled", ValidationChecker.checkBooleanValue(object.getBoolean("enabled")));

            assertEquals("Incorrect count of JSON parameters", object.length(), 9);
        }
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
    }
    public FinParameter getAnyFinParameter(TestUser user, String siteUrl) throws IOException{
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, user, "money/api/parameter/", 500, "GET");
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
            // System.out.println(object);
            return new FinParameter(object.getInt("id"),object.getInt("operation_type_id"), object.get("merchant_id"), object.get("payment_merchant_id"), object.getInt("parameter_id"),object.getString("value"),object.getString("date_start"),object.get("date_end"),object.getBoolean("enabled")  );
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
    public FinParameter getFinParameterByParameter(String parameterName, int parameterValue, TestUser user, String siteUrl) throws IOException {
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, user, "money/api/parameter/", 1, "GET");
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
                    return new FinParameter(object.getInt("id"),object.getInt("operation_type_id"), object.get("merchant_id"), object.get("payment_merchant_id"), object.getInt("parameter_id"),object.getString("value"),object.getString("date_start"),object.get("date_end"),object.getBoolean("enabled")  );
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
