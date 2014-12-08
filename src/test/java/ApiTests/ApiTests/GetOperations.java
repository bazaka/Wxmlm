package ApiTests.ApiTests;

import ApiTests.ApiValueCheckers.ValidationChecker;
import ApiTests.ObjectClasses.MakeRequest;
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
        HttpURLConnection httpCon = MakeRequest.getConnection(scheme, user, "money/api/operations/", 5, "GET");
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
            assertTrue("Incorrect target_account_id", checker.checkIdValue(object.getInt("target_account_id")));
            assertTrue("Incorrect source_account_id", checker.checkIdValue(object.getInt("source_account_id")));
            assertTrue("Incorrect purchase_id", checker.checkIdOrNull(object.get("purchase_id")));
            assertTrue("Incorrect initiator_user_id", checker.checkIdValue(object.getInt("initiator_user_id")));
            assertTrue("Incorrect created_date", checker.checkDateTimeString(object.getString("created_date")));
            assertTrue("Incorrect updated_date", checker.checkDateTimeString(object.getString("updated_date")));
            assertTrue("Incorrect amount", checker.checkDoubleValue(object.getDouble("amount")));
            assertTrue("Incorrect status", checker.checkOperationStatusId(object.getInt("status")));
            assertTrue("Incorrect type", checker.checkOperationTypeId(object.getInt("type")));
            assertTrue("Incorrect quarantine", checker.checkBooleanValue(object.getBoolean("quarantine")));
            assertEquals("Incorrect count of Json parameters", object.length(), 11);
        }
        return true;
    }

    public Operation getAnyOperation(TestUser user, String scheme) throws IOException {
        HttpURLConnection httpCon = MakeRequest.getConnection(scheme, user, "money/api/operations/", 5, "GET");
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
            return new Operation(object.getInt("id"), object.getInt("target_account_id"), object.getInt("source_account_id"), purchase_id, object.getInt("initiator_user_id"), object.getString("created_date"), object.getDouble("amount"), object.getInt("status"), object.getInt("type"), object.getBoolean("quarantine"));
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
                    return new Operation(object.getInt("id"), object.getInt("target_account_id"), object.getInt("source_account_id"), purchase_id, object.getInt("initiator_user_id"), object.getString("created_date"), object.getDouble("amount"), object.getInt("status"), object.getInt("type"), object.getBoolean("quarantine"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
