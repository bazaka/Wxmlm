package ApiTests.Backend;

import ApiTests.UsedByAll.ValidationChecker;
import ApiTests.UsedByAll.MakeRequest;
import ApiTests.ObjectClasses.Operation;
import UsedByAll.TestUser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import java.io.*;
import java.net.HttpURLConnection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// * Created for W-xmlm by Fill on 25.11.2014. Тест проверяет метод АПИ GET Operations
public class GetOperations {

    @Test
    public boolean testGetOperations(String scheme, TestUser user) throws Exception {
        long startTime;
        long elapsedTime;
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(scheme, user, "money/api/operations/", 500, "GET");
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

            assertTrue("Incorrect id", ValidationChecker.checkIdValue(object.getInt("id")));
            assertTrue("Incorrect target_account_id", ValidationChecker.checkIdValue(object.getInt("target_account_id")));
            assertTrue("Incorrect source_account_id", ValidationChecker.checkIdValue(object.getInt("source_account_id")));
            assertTrue("Incorrect purchase_id", ValidationChecker.checkIdOrNull(object.get("purchase_id")));
            assertTrue("Incorrect initiator_user_id", ValidationChecker.checkIdValue(object.getInt("initiator_user_id")));
            assertTrue("Incorrect created_date", ValidationChecker.checkDateTimeString(object.getString("created_date")));
            assertTrue("Incorrect updated_date", ValidationChecker.checkDateTimeString(object.getString("updated_date")));
            assertTrue("Incorrect amount", ValidationChecker.checkDoubleValue(object.getDouble("amount")));
            assertTrue("Incorrect status", ValidationChecker.checkOperationStatusId(object.getInt("status")));
            assertTrue("Incorrect type", ValidationChecker.checkOperationTypeId(object.getInt("type")));
            assertTrue("Incorrect quarantine", ValidationChecker.checkBooleanValue(object.getBoolean("quarantine")));
            assertTrue("Incorrect parent_operation_id", ValidationChecker.checkIdOrNull(object.get("parent_operation_id")));
            assertEquals("Incorrect count of Json parameters", object.length(), 12);
        }
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
        return true;
    }

    public Operation getAnyOperation(TestUser user, String scheme) throws IOException {
        HttpURLConnection httpCon = MakeRequest.getConnection(scheme, user, "money/api/operations/", 500, "GET");
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
            String purchase_id = null;
            if (object.get("purchase_id") != null)
                purchase_id = object.get("purchase_id").toString();
            return new Operation(object.getInt("id"), object.getInt("target_account_id"), object.getInt("source_account_id"), purchase_id, object.getInt("initiator_user_id"), object.getString("created_date"), object.getDouble("amount"), object.getInt("status"), object.getInt("type"), object.getBoolean("quarantine"), object.get("parent_operation_id"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Operation getOperationByParameter(String parameterName, int parameterValue, TestUser user, String scheme) throws IOException {
        HttpURLConnection httpCon = MakeRequest.getConnection(scheme, user, "money/api/operations/", 1, "GET");
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
                    String purchase_id = null;
                    if (object.get("purchase_id") != null)
                        purchase_id = object.get("purchase_id").toString();
                    return new Operation(object.getInt("id"), object.getInt("target_account_id"), object.getInt("source_account_id"), purchase_id, object.getInt("initiator_user_id"), object.getString("created_date"), object.getDouble("amount"), object.getInt("status"), object.getInt("type"), object.getBoolean("quarantine"), object.get("parent_operation_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
