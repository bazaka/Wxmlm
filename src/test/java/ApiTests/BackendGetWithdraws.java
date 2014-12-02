package ApiTests;

import ApiTests.ApiValueCheckers.ValidationChecker;
import ApiTests.ObjectClasses.Account;
import ApiTests.ObjectClasses.MakeRequest;
import ApiTests.ObjectClasses.Withdraw;
import UsedByAll.TestUser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// * Created for W-xmlm by Fill on 01.12.2014. Get Withdraws test
public class BackendGetWithdraws {

    @Before
    public void setUp(String scheme) throws Exception {
        System.out.println("Запускаю селениум для проверки API-метода GET Withdraws на " + scheme);
    }

    @Test
    public boolean testBackendGetWithdraws(String scheme, TestUser user) throws Exception {
        HttpURLConnection httpCon = MakeRequest.getConnection(scheme, user, "money/api/withdraws/", 5, "GET");
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

            assertTrue("Incorrect id", checker.checkIdValue(object.getInt("id")));
            assertTrue("Incorrect user_id", checker.checkIdValue(object.getInt("user_id")));
            assertTrue("Incorrect merchant_id", checker.checkIdValue(object.getInt("merchant_id")));
            assertTrue("Incorrect operation_id", checker.checkIdValue(object.getInt("operation_id")));
            assertTrue("Incorrect amount", checker.checkDoubleValue(object.getDouble("amount")));
            assertTrue("Incorrect created_date", checker.checkDateTimeString(object.getString("created_date")));
            assertTrue("Incorrect status", checker.checkWithdrawStatusId(object.getInt("status")));
            JSONObject details = object.getJSONObject("details");
            if (details.has("epid"))
                assertTrue(checker.checkNotNull(details.getString("epid")));
            else if (details.has("swiftCode")) {
                assertTrue(checker.checkNotNull(details.getString("name")));
                assertTrue(checker.checkStringOrNull(details.getString("address")));
                assertTrue(checker.checkNotNull(details.getString("bankName")));
                assertTrue(checker.checkStringOrNull(details.getString("bankAddress")));
                assertTrue(checker.checkNotNull(details.getString("accountIban")));
                assertTrue(checker.checkNotNull(details.getString("swiftCode")));
                }
        }
        return true;
    }

    public Withdraw getAnyWithdraw(TestUser user, String scheme) throws IOException {
        HttpURLConnection httpCon = MakeRequest.getConnection(scheme, user, "money/api/withdraws/", 5, "GET");
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
            JSONObject details = object.getJSONObject("details");
            if (details.has("epid"))
                return new Withdraw(object.getInt("id"), object.getInt("user_id"), object.getInt("merchant_id"), object.getInt("operation_id"), object.getDouble("amount"), object.getString("created_date"), object.getInt("status"), details.getString("epid"));
            else if (details.has("swiftCode"))
                return new Withdraw(object.getInt("id"), object.getInt("user_id"), object.getInt("merchant_id"), object.getInt("operation_id"), object.getDouble("amount"), object.getString("created_date"), object.getInt("status"), details.getString("name"), details.getString("address"), details.getString("bankName"), details.getString("bankAddress"), details.getString("accountIban"), details.getString("swiftCode"));
            else {
            System.out.println("Bank details is missing in object");
            return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Withdraw getWithdrawByParameter(String parameterName, String parameterValue, TestUser user, String scheme) throws IOException {
        HttpURLConnection httpCon = MakeRequest.getConnection(scheme, user, "money/api/withdraws/", 5, "GET");
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
                JSONObject details = object.getJSONObject("details");
                if (object.getString(parameterName).equals(parameterValue)) {
                    if (details.has("epid"))
                        return new Withdraw(object.getInt("id"), object.getInt("user_id"), object.getInt("merchant_id"), object.getInt("operation_id"), object.getDouble("amount"), object.getString("created_date"), object.getInt("status"), details.getString("epid"));
                    else if (details.has("swiftCode"))
                        return new Withdraw(object.getInt("id"), object.getInt("user_id"), object.getInt("merchant_id"), object.getInt("operation_id"), object.getDouble("amount"), object.getString("created_date"), object.getInt("status"), details.getString("name"), details.getString("address"), details.getString("bankName"), details.getString("bankAddress"), details.getString("accountIban"), details.getString("swiftCode"));
                    else {
                        System.out.println("Bank details is missing in object");
                        return null;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }


    @After
    public void tearDown() throws Exception {}
}