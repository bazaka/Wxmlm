package ApiTests.Backend;

import ApiTests.UsedByAll.ValidationChecker;
import ApiTests.UsedByAll.MakeRequest;
import ApiTests.ObjectClasses.Withdraw;
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

// * Created for W-xmlm by Fill on 01.12.2014. Get Withdraws test
@RunWith(value = Parameterized.class)
public class GetWithdrawsToRun {
    private TestUser testUser;

    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_BackendAPITest(");
    }

    public GetWithdrawsToRun(TestUser user){
        this.testUser = user;
    }

    @Test
    public void testGetWithdraws() throws Exception {
        String siteUrl = Config.getConfig().getProtocol() + Config.getConfig().getScheme(); // Урл проверяемого сайта
        long startTime;
        long elapsedTime;
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, testUser, "money/api/withdraws/", 500, "GET");
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
    }

    public Withdraw getAnyWithdraw(TestUser user, String siteUrl) throws IOException {
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, user, "money/api/withdraws/", 500, "GET");
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
            assertNotNull("Получен пустой массив. Проверить метод с наличием объектов.", jsonArr.length());
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

    public Withdraw getWithdrawByParameter(String parameterName, int parameterValue, TestUser user, String siteUrl) throws IOException {
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, user, "money/api/withdraws/", 500, "GET");
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