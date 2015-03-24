package ApiTests.Backend;

import ApiTests.ObjectClasses.Product;
import ApiTests.UsedByAll.ValidationChecker;
import ApiTests.UsedByAll.MakeRequest;
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

// * Created for W-xmlm by Fill on 08.12.2014. GET Products
@RunWith(value = Parameterized.class)
public class GetProductsToRun {
    private TestUser testUser;

    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_GetProductsTest(");
    }

    public GetProductsToRun(TestUser user){
        this.testUser = user;
    }

    @Test
    public void testGetProducts() throws Exception {
        String siteUrl = Config.getConfig().getProtocol() + Config.getConfig().getScheme(); // Урл проверяемого сайта
        long startTime;
        long elapsedTime;
        String url = "products/api/products/";
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, testUser, url, 500, "GET");
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
        JSONArray jsonArr = new JSONArray(result);
        assertNotEquals("Получен пустой массив. Проверить метод с наличием объектов.", jsonArr.length(), 0);

        for (int i = 0; i < jsonArr.length(); i++) {

            JSONObject object = jsonArr.getJSONObject(i);
            JSONObject attributes = object.getJSONObject("attributes");
            assertTrue("Incorrect id", ValidationChecker.checkIdValue(object.getInt("id")));
            assertTrue("Incorrect category_id", ValidationChecker.checkIdValue(object.getInt("category_id")));
            assertTrue("Incorrect owner_id", ValidationChecker.checkIdValue(object.getInt("owner_id")));
            assertTrue("Incorrect creator_id", ValidationChecker.checkIdValue(object.getInt("creator_id")));
            assertTrue("Incorrect title", ValidationChecker.checkStringNotNull(object.getString("title")));
            assertTrue("Incorrect description", ValidationChecker.checkStringOrNull(object.get("description")));
            assertTrue("Incorrect price", ValidationChecker.checkDoubleValue(object.getDouble("price")));
            assertTrue("Incorrect status", ValidationChecker.checkProductStatusId(object.getInt("status")));
            assertTrue("Incorrect type", ValidationChecker.checkProductTypeId(object.getInt("type")));
            assertTrue("Incorrect created_date", ValidationChecker.checkDateTimeString(object.getString("created_date")));
            assertTrue("Incorrect image_url", ValidationChecker.checkStringOrNull(object.get("image_url")));
            assertTrue("Incorrect product_url", ValidationChecker.checkURLOnDomain(object.getString("product_url"), siteUrl));
            assertTrue("Unrecognised category_id", object.getInt("category_id") == 1 || object.getInt("category_id") == 2);
            if (object.getInt("category_id") == 1) {
                assertTrue("Incorrect available", ValidationChecker.checkStringOrNull(attributes.get("available")) || ValidationChecker.checkPositiveInt(attributes.getInt("available")));
                assertTrue("Incorrect discSpace", ValidationChecker.checkStringOrNull(attributes.get("discSpace")));
                assertTrue("Incorrect timeOnline", ValidationChecker.checkStringOrNull(attributes.get("timeOnline")));
                assertTrue("Incorrect basicIncome", ValidationChecker.checkPositiveInt(attributes.getInt("basicIncome")));
                assertTrue("Incorrect basicIncomePeriod", ValidationChecker.checkPositiveInt(attributes.getInt("basicIncomePeriod")));
                assertTrue("Incorrect profit", ((ValidationChecker.checkStringOrNull(attributes.get("profit"))) || ValidationChecker.checkDoubleValue(attributes.getDouble("profit"))));
                assertTrue("Incorrect investmentPeriod", ValidationChecker.checkPositiveInt(attributes.getInt("investmentPeriod")));
                assertTrue("Incorrect start", ValidationChecker.checkDateString(attributes.getString("start")));
                assertEquals("Incorrect count of object additional attributes", attributes.length(), 8);
            }
            else if (object.getInt("category_id") == 2) {
                JSONArray requiredForTrialArray = attributes.getJSONArray("requiredForTrial");
                for (int a = 0; a < requiredForTrialArray.length(); a++){
                    assertTrue("Incorrect requiredForTrial", ValidationChecker.checkIdValue(requiredForTrialArray.optInt(a)));
                }
                assertTrue("Incorrect trialPeriod", ValidationChecker.checkPositiveInt(Integer.valueOf(attributes.get("trialPeriod").toString())));
                assertTrue("Incorrect quotaPrefix", ValidationChecker.checkStringOrNull(attributes.get("quotaPrefix")));
                assertTrue("Incorrect quota", ValidationChecker.checkPositiveInt(attributes.getInt("quota")));
                assertTrue("Incorrect quotaMeasurement", ValidationChecker.checkStringOrNull(attributes.get("quotaMeasurement")));
                assertTrue("Incorrect serviceId", ValidationChecker.checkIdValue(attributes.getInt("serviceId")));
                assertEquals("Incorrect count of object additional attributes", attributes.length(), 6);
            }
            assertEquals("Incorrect count of object attributes", object.length(), 13);
        }
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
    }
    public static int[] getProductsIDs(String siteUrl, TestUser user) throws Exception {
        String url = "products/api/products/";
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, user, url, 500, "GET");
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
        int[] ids = new int[jsonArr.length()];
        assertNotNull("Получен пустой массив. Проверить метод с наличием объектов.", jsonArr.length());
        for (int i = 0; i < jsonArr.length(); i++) {
            JSONObject object = jsonArr.getJSONObject(i);
            ids[i] = object.getInt("id");
        }
        return ids;
    }
    public Product getFirstProduct(TestUser user, String siteUrl) throws IOException {
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, user, "products/api/products/", 500, "GET");
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
            JSONObject attributes = object.getJSONObject("attributes");
            if (object.getInt("category_id") == 1) {
                return new Product(object.getInt("id"), object.getInt("category_id"), object.getInt("owner_id"), object.getInt("creator_id"), object.getString("title"), object.get("description").toString(), object.getDouble("price"), object.getInt("status"), object.getInt("type"), object.getString("created_date"), object.get("image_url"), attributes.get("available"), attributes.get("discSpace"), attributes.get("timeOnline"), attributes.getInt("basicIncome"), attributes.getInt("basicIncomePeriod"), attributes.getString("profit"), attributes.getInt("investmentPeriod"), attributes.getString("start"));
            }
            else if (object.getInt("category_id") == 2) {
                JSONArray requiredForTrialArray = attributes.getJSONArray("requiredForTrial");
                int[] requiredForTrial = new int[requiredForTrialArray.length()];
                for (int i = 0; i < requiredForTrialArray.length(); i++){
                    requiredForTrial[i] = requiredForTrialArray.optInt(i);
                }
                return new Product(object.getInt("id"), object.getInt("category_id"), object.getInt("owner_id"), object.getInt("creator_id"), object.getString("title"), object.get("description").toString(), object.getDouble("price"), object.getInt("status"), object.getInt("type"), object.getString("created_date"), object.get("image_url"), requiredForTrial, attributes.getString("trialPeriod"), attributes.getString("quotaPrefix"), attributes.getInt("quota"), attributes.getString("quotaMeasurement"), attributes.getInt("serviceId"));
            }
            else {
                System.out.println("Unrecognised category_id");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public Product getLastUpdatedProduct(TestUser user, String siteUrl) throws IOException {
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, user, "products/api/products/", 500, "GET");
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
            JSONObject object = jsonArr.getJSONObject(jsonArr.length() - 1);
            JSONObject attributes = object.getJSONObject("attributes");
            if (object.getInt("category_id") == 1) {
                return new Product(object.getInt("id"), object.getInt("category_id"), object.getInt("owner_id"), object.getInt("creator_id"), object.getString("title"), object.get("description").toString(), object.getDouble("price"), object.getInt("status"), object.getInt("type"), object.getString("created_date"), object.get("image_url"), attributes.get("available"), attributes.get("discSpace"), attributes.get("timeOnline"), attributes.getInt("basicIncome"), attributes.getInt("basicIncomePeriod"), attributes.getString("profit"), attributes.getInt("investmentPeriod"), attributes.getString("start"));
            }
            else if (object.getInt("category_id") == 2) {
                JSONArray requiredForTrialArray = attributes.getJSONArray("requiredForTrial");
                int[] requiredForTrial = new int[requiredForTrialArray.length()];
                for (int i = 0; i < requiredForTrialArray.length(); i++){
                    requiredForTrial[i] = requiredForTrialArray.optInt(i);
                }
                return new Product(object.getInt("id"), object.getInt("category_id"), object.getInt("owner_id"), object.getInt("creator_id"), object.getString("title"), object.get("description").toString(), object.getDouble("price"), object.getInt("status"), object.getInt("type"), object.getString("created_date"), object.get("image_url"), requiredForTrial, attributes.getString("trialPeriod"), attributes.getString("quotaPrefix"), attributes.getInt("quota"), attributes.getString("quotaMeasurement"), attributes.getInt("serviceId"));
            }
            else {
                System.out.println("Unrecognised category_id");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public Product getProductByParameter(String parameterName, int parameterValue, TestUser user, String siteUrl) throws IOException {
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, user, "products/api/products/", 1, "GET");
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
                JSONObject attributes = object.getJSONObject("attributes");
                if (object.getInt(parameterName) == parameterValue) {
                    if (object.getInt("category_id") == 1) {
                        return new Product(object.getInt("id"), object.getInt("category_id"), object.getInt("owner_id"), object.getInt("creator_id"), object.getString("title"), object.getString("description"), object.getDouble("price"), object.getInt("status"), object.getInt("type"), object.getString("created_date"), object.get("image_url"), attributes.get("available"), attributes.get("discSpace"), attributes.get("timeOnline"), attributes.getInt("basicIncome"), attributes.getInt("basicIncomePeriod"), attributes.getString("profit"), attributes.getInt("investmentPeriod"), attributes.getString("start"));
                    }
                    else if (object.getInt("category_id") == 2) {
                        JSONArray requiredForTrialArray = attributes.getJSONArray("requiredForTrial");
                        int[] requiredForTrial = new int[requiredForTrialArray.length()];
                        for (int a = 0; a < requiredForTrialArray.length(); a++){
                            requiredForTrial[a] = requiredForTrialArray.optInt(a);
                        }
                        return new Product(object.getInt("id"), object.getInt("category_id"), object.getInt("owner_id"), object.getInt("creator_id"), object.getString("title"), object.getString("description"), object.getDouble("price"), object.getInt("status"), object.getInt("type"), object.getString("created_date"), object.get("image_url"), requiredForTrial, attributes.getString("trialPeriod"), attributes.getString("quotaPrefix"), attributes.getInt("quota"), attributes.getString("quotaMeasurement"), attributes.getInt("serviceId"));
                    }
                    else {
                        System.out.println("Unrecognised category_id");
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