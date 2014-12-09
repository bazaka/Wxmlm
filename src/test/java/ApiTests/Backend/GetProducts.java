package ApiTests.Backend;

import ApiTests.ApiValueCheckers.ValidationChecker;
import ApiTests.ObjectClasses.MakeRequest;
import UsedByAll.TestUser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

// * Created for W-xmlm by Fill on 08.12.2014. GET Products
public class GetProducts {
/*    @Test
    public boolean testGetProducts(String scheme, TestUser User) throws Exception {
        String url = "products/api/products/";
        HttpURLConnection httpCon = MakeRequest.getConnection(scheme, User, url, 500, "GET");
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
        ValidationChecker checker = new ValidationChecker();
        assertNotNull("Получен пустой массив. Проверить метод с наличием объектов.", jsonArr.length());

        for (int i = 0; i < jsonArr.length(); i++) {

            JSONObject object = jsonArr.getJSONObject(i);
            assertTrue("Incorrect id", checker.checkIdValue(object.getInt("id")));
            assertTrue("Incorrect category_id", checker.checkIdValue(object.getInt("category_id")));
            assertTrue("Incorrect product_id", checker.checkProductId(object.getInt("product_id")));
            assertTrue("Incorrect date", checker.checkDateTimeString(object.getString("date")));
            assertTrue("Incorrect updated_date", checker.checkDateTimeString(object.getString("updated_date")));
            assertTrue("Incorrect price", checker.checkMoneyFormat(object.get("price").toString()));
            assertTrue("Incorrect payment_amount", checker.checkMoneyFormat(object.get("payment_amount").toString()));
            assertTrue("Incorrect status", checker.checkOperationStatusId(object.getInt("status")));
            assertTrue("Incorrect terms", checker.checkStringOrNull(object.get("terms")));
            assertEquals("Incorrect count of JSON Objects", object.length(), 9);
        }
        return true;
    }*/
}