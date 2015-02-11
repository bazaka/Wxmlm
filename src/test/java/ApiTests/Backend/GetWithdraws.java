package ApiTests.Backend;

import ApiTests.UsedByAll.ValidationChecker;
import ApiTests.UsedByAll.MakeRequest;
import ApiTests.ObjectClasses.Withdraw;
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

// * Created for W-xmlm by Fill on 01.12.2014. Get Withdraws test
public class GetWithdraws {
    public boolean testGetWithdraws(String scheme, TestUser user) throws Exception {
        long startTime;
        long elapsedTime;
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(scheme, user, "money/api/withdraws/", 500, "GET");
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
            System.out.println("Получен пустой массив. Рекомендуется проверить метод с наличием объектов. ");
            return false;
        }
        for (int i = 0; i < jsonArr.length(); i++) {
            JSONObject object = jsonArr.getJSONObject(i);

            assertTrue("Incorrect id", ValidationChecker.checkIdValue(object.getInt("id")));
            assertTrue("Incorrect user_id", ValidationChecker.checkIdValue(object.getInt("user_id")));
            assertTrue("Incorrect merchant_id", ValidationChecker.checkIdValue(object.getInt("merchant_id")));
            assertTrue("Incorrect operation_id", ValidationChecker.checkIdValue(object.getInt("operation_id")));
            assertTrue("Incorrect amount", ValidationChecker.checkDoubleValue(object.getDouble("amount")));
            assertTrue("Incorrect created_date", ValidationChecker.checkDateTimeString(object.getString("created_date")));
            assertTrue("Incorrect status", ValidationChecker.checkWithdrawStatusId(object.getInt("status")));
            assertEquals("Incorrect count of Json parameters", object.length(), 8);
            JSONObject details = object.getJSONObject("details");
            if (details.has("epid")) {
                assertTrue(ValidationChecker.checkStringNotNull(details.getString("epid")));
                assertEquals("Incorrect count of Bank details parameters", details.length(), 1);
            }
            else if (details.has("swiftCode")) {
                assertTrue(ValidationChecker.checkStringNotNull(details.getString("name")));
                assertTrue(ValidationChecker.checkStringOrNull(details.getString("address")));
                assertTrue(ValidationChecker.checkStringNotNull(details.getString("bankName")));
                assertTrue(ValidationChecker.checkStringOrNull(details.getString("bankAddress")));
                assertTrue(ValidationChecker.checkStringNotNull(details.getString("accountIban")));
                assertTrue(ValidationChecker.checkStringNotNull(details.getString("swiftCode")));
                assertEquals("Incorrect count of Bank details parameters", details.length(), 6);
                }
        }
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
        return true;
    }

    public Withdraw getAnyWithdraw(TestUser user, String scheme) throws IOException {
        HttpURLConnection httpCon = MakeRequest.getConnection(scheme, user, "money/api/withdraws/", 500, "GET");
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
            if (jsonArr.length() == 0) {
                System.out.println("Получен пустой массив. Рекомендуется проверить метод с наличием объектов. ");
                return null;
            }
            JSONObject object = jsonArr.getJSONObject(0);
            JSONObject details = object.getJSONObject("details");
            if (details.has("epid"))
                return new Withdraw(object.getInt("id"), object.getInt("user_id"), object.getInt("merchant_id"), object.getInt("operation_id"), object.getDouble("amount"), object.getString("created_date"), object.getInt("status"), details.getString("epid"), "ePayment");
            else if (details.has("swiftCode"))
                return new Withdraw(object.getInt("id"), object.getInt("user_id"), object.getInt("merchant_id"), object.getInt("operation_id"), object.getDouble("amount"), object.getString("created_date"), object.getInt("status"), details.getString("name"), details.getString("address"), details.getString("bankName"), details.getString("bankAddress"), details.getString("accountIban"), details.getString("swiftCode"), "SWIFT");
            else {
            System.out.println("Bank details is missing in Withdraw object");
            return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Withdraw getWithdrawByParameter(String parameterName, int parameterValue, TestUser user, String scheme) throws IOException {
        HttpURLConnection httpCon = MakeRequest.getConnection(scheme, user, "money/api/withdraws/", 500, "GET");
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
            if (jsonArr.length() == 0) {
                System.out.println("Получен пустой массив. Рекомендуется проверить метод с наличием объектов. ");
                return null;
            }
            for (int i = 0; i < jsonArr.length(); i++) {
                JSONObject object = jsonArr.getJSONObject(i);
                JSONObject details = object.getJSONObject("details");
                if (object.getInt(parameterName) == parameterValue) {
                    if (details.has("epid"))
                        return new Withdraw(object.getInt("id"), object.getInt("user_id"), object.getInt("merchant_id"), object.getInt("operation_id"), object.getDouble("amount"), object.getString("created_date"), object.getInt("status"), details.getString("epid"), "ePayment");
                    else if (details.has("swiftCode"))
                        return new Withdraw(object.getInt("id"), object.getInt("user_id"), object.getInt("merchant_id"), object.getInt("operation_id"), object.getDouble("amount"), object.getString("created_date"), object.getInt("status"), details.getString("name"), details.getString("address"), details.getString("bankName"), details.getString("bankAddress"), details.getString("accountIban"), details.getString("swiftCode"), "SWIFT");
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
}