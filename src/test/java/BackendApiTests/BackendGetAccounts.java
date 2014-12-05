package BackendApiTests;

import BackendApiTests.ObjectClasses.Account;
import BackendApiTests.ObjectClasses.MakeRequest;
import UsedByAll.TestUser;
import org.junit.Test;
import org.json.*;
import BackendApiTests.ApiValueCheckers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class BackendGetAccounts {

    @Test
    public boolean testBackendGetAccounts(String scheme, TestUser user) throws Exception {
        HttpURLConnection httpCon = MakeRequest.getConnection(scheme, user, "money/api/accounts/", 5, "GET");
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

        //Парсим JSON
        JSONArray jsonArr = new JSONArray(result);
        //Проверяем структуру
        ValidationChecker checker = new ValidationChecker();
        if (jsonArr.length() == 0) {
            System.out.print("Получен пустой массив. Рекомендуется проверить метод с наличием объектов. ");
            return false;
        }
        for (int i = 0; i < jsonArr.length(); i++) {
            JSONObject object = jsonArr.getJSONObject(i);

            assertTrue("Incorrect account_id", checker.checkIdValue(object.getInt("account_id")));
            assertTrue("Incorrect user_id", checker.checkIdValue(object.getInt("user_id")));
            assertTrue("Incorrect account_number", checker.checkAccountNumberValue(object.getString("account_number")));
            assertTrue("Incorrect account_type", checker.checkAccountTypeValue(object.getInt("account_type")));
            assertTrue("Incorrect status", checker.checkBooleanValue(object.getBoolean("status")));
            assertTrue("Incorrect account_info", checker.checkNotNull(object.getString("account_info")));
            assertTrue("Incorrect amount", checker.checkDoubleValue(object.getDouble("amount")));
            assertTrue("Incorrect updated_date", checker.checkDateTimeString(object.getString("updated_date")));
            assertEquals("Incorrect count of Json parameters", object.length(), 8);
        }
        return true;
    }

    public Account getAnyAccount(TestUser user, String scheme) throws IOException {
        HttpURLConnection httpCon = MakeRequest.getConnection(scheme, user, "money/api/accounts/", 5, "GET");
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
            return new Account(object.getInt("account_id"), object.getString("account_number"), object.getInt("account_type"), object.getBoolean("status"), object.getString("account_info"), object.getDouble("amount"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Account getAccountByParameter(String parameterName, int parameterValue, TestUser user, String scheme) throws IOException {
        HttpURLConnection httpCon = MakeRequest.getConnection(scheme, user, "money/api/accounts/", 1, "GET");
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
                    return new Account(object.getInt("account_id"), object.getString("account_number"), object.getInt("account_type"), object.getBoolean("status"), object.getString("account_info"), object.getDouble("amount"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}