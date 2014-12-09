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
    @Test
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
        assertNotNull("Получен пустой массив. Проверить метод с наличием объектов.", jsonArr.length());

        for (int i = 0; i < jsonArr.length(); i++) {

            JSONObject object = jsonArr.getJSONObject(i);
            JSONObject attributes = object.getJSONObject("attributes");
            assertTrue("Incorrect id", ValidationChecker.checkIdValue(object.getInt("id")));
            assertTrue("Incorrect category_id", ValidationChecker.checkIdValue(object.getInt("category_id")));
            assertTrue("Incorrect owner_id", ValidationChecker.checkIdValue(object.getInt("owner_id")));
            assertTrue("Incorrect creator_id", ValidationChecker.checkIdValue(object.getInt("creator_id")));
            assertTrue("Incorrect title", ValidationChecker.checkNotNull(object.getString("title")));
            assertTrue("Incorrect description", ValidationChecker.checkNotNull(object.getString("description")));
            assertTrue("Incorrect price", ValidationChecker.checkDoubleValue(object.getDouble("price")));
            assertTrue("Incorrect status", ValidationChecker.checkProductStatusId(object.getInt("status")));
            assertTrue("Incorrect type", ValidationChecker.checkProductTypeId(object.getInt("type")));
            assertTrue("Incorrect created_date", ValidationChecker.checkDateTimeString(object.getString("created_date")));
            assertTrue("Incorrect image_url", ValidationChecker.checkStringOrNull(object.get("image_url")));
            assertTrue("Incorrect product_url", ValidationChecker.checkURLOnDomain(object.getString("product_url"), scheme));
            if (object.getInt("category_id") == 1) {
                assertTrue("Incorrect available", ValidationChecker.checkStringOrNull(attributes.get("available")) || ValidationChecker.checkPositiveInt(attributes.getInt("available")));
                assertTrue("Incorrect discSpace", ValidationChecker.checkStringOrNull(attributes.getInt("discSpace")));
                assertTrue("Incorrect timeOnline", ValidationChecker.checkPositiveInt(attributes.getInt("timeOnline")));
                assertTrue("Incorrect basicIncome", ValidationChecker.checkPositiveInt(attributes.getInt("basicIncome")));
                assertTrue("Incorrect basicIncomePeriod", ValidationChecker.checkPositiveInt(attributes.getInt("basicIncomePeriod")));
                assertTrue("Incorrect profit", ValidationChecker.checkDoubleValue(attributes.getDouble("profit")));
                assertTrue("Incorrect investmentPeriod", ValidationChecker.checkPositiveInt(attributes.getInt("investmentPeriod")));
                assertTrue("Incorrect start", ValidationChecker.checkDateString(attributes.getString("start")));
                assertEquals("Incorrect count of object additional attributes", attributes.length(), 8);
            }
            assertEquals("Incorrect count of object attributes", object.length(), 13);
        }
        return true;
    }
}