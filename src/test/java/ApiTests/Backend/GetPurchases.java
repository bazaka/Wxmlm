package ApiTests.Backend;


import ApiTests.UsedByAll.ValidationChecker;
import ApiTests.UsedByAll.MakeRequest;
import UsedByAll.TestUser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import static org.junit.Assert.*;

public class GetPurchases {

    @Test
    public boolean testGetPurchases(String scheme, TestUser User) throws Exception{
        String url = "products/api/purchase/";
        HttpURLConnection httpCon = MakeRequest.getConnection(scheme, User, url, 5, "GET");
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
        JSONArray jsonArr = new JSONArray(result);
        assertNotNull("Получен пустой массив. Проверить метод с наличием объектов.", jsonArr.length());

        for (int i=0; i<jsonArr.length(); i++){

            JSONObject object = jsonArr.getJSONObject(i);
            assertTrue("Incorrect id", ValidationChecker.checkIdValue(object.getInt("id")));
            assertTrue("Incorrect buyer_user_id", ValidationChecker.checkIdValue(object.getInt("buyer_user_id")));
            assertTrue("Incorrect product_id", ValidationChecker.checkProductId(object.getInt("product_id")));
            assertTrue("Incorrect date", ValidationChecker.checkDateTimeString(object.getString("date")));
            assertTrue("Incorrect updated_date", ValidationChecker.checkDateTimeString(object.getString("updated_date")));
            assertTrue("Incorrect price", ValidationChecker.checkMoneyFormat(object.get("price").toString()));
            assertTrue("Incorrect payment_amount", ValidationChecker.checkMoneyFormat(object.get("payment_amount").toString()));
            assertTrue("Incorrect status", ValidationChecker.checkOperationStatusId(object.getInt("status")));
            assertTrue("Incorrect terms", ValidationChecker.checkStringOrNull(object.get("terms")));
            assertEquals("Incorrect count of JSON Objects", object.length(),9);
        }

        return true;
    }



}
