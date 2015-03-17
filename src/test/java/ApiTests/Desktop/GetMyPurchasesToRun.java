package ApiTests.Desktop;

import ApiTests.ObjectClasses.Purchase;
import ApiTests.UsedByAll.MakeRequest;
import ApiTests.UsedByAll.ValidationChecker;
import UsedByAll.CsvUsersReader;
import UsedByAll.TestUser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Collection;

import static org.junit.Assert.*;

// * Created for W-xmlm by Fill on 16.03.2015.
@RunWith(value = Parameterized.class)
public class GetMyPurchasesToRun {
    private TestUser testUser;

    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_DesktopAPITest(");
    }

    public GetMyPurchasesToRun(TestUser user){
        this.testUser = user;
    }

    @Test
    public void testGetToken() throws Exception {
        String siteUrl = UsedByAll.Config.getConfig().getProtocol() + UsedByAll.Config.getConfig().getScheme(); // Урл проверяемого сайта
        String token = new GetTokenToRun(testUser).getToken();
        long startTime;
        long elapsedTime;
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, "users/api/desktop/get-purchases/?_format=json&token=" + token, "GET");
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
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);

        //Парсим JSON
        JSONObject object = new JSONObject(result);
        JSONArray purchases = object.getJSONArray("purchases");
        assertNotEquals("Получен пустой массив. Проверить метод с наличием объектов.", purchases.length(), 0);
        for (int i=0; i<purchases.length(); i++){
            JSONObject purchase = purchases.getJSONObject(i);
            assertTrue("Incorrect id", ValidationChecker.checkIdValue(purchase.getInt("id")));
            assertTrue("Incorrect buyer_user_id", ValidationChecker.checkIdValue(purchase.getInt("buyer_user_id")));
            assertTrue("Incorrect product_id", ValidationChecker.checkProductId(purchase.getInt("product_id")));
            assertTrue("Incorrect price", ValidationChecker.checkMoneyFormat(purchase.get("price").toString()));
            assertTrue("Incorrect status", ValidationChecker.checkPurchaseStatusId(purchase.getInt("status")));
            assertTrue("Incorrect terms", ValidationChecker.checkStringOrNull(purchase.get("terms")));
            assertTrue("Incorrect package_status", ValidationChecker.checkPackageStatusId(purchase.getInt("status")));
            assertTrue("Incorrect date", ValidationChecker.checkDateTimeString(purchase.getString("date")));
            assertEquals("Incorrect count of JSON Objects", purchase.length(),8);
        }
    }

    public Purchase getMyNotActivePackage() throws Exception {
        String siteUrl = UsedByAll.Config.getConfig().getProtocol() + UsedByAll.Config.getConfig().getScheme(); // Урл проверяемого сайта
        String token = new GetTokenToRun(testUser).getToken();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, "users/api/desktop/get-purchases/?_format=json&token=" + token, "GET");
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
            JSONObject response = new JSONObject(result);
            JSONArray jsonArr = response.getJSONArray("purchases");
            for (int i = 0; i < jsonArr.length(); i++) {
                JSONObject object = jsonArr.getJSONObject(i);
                if (object.getInt("status") == 1 && object.getInt("package_status") == 1) {
                    String terms = null;
                    if(object.get("terms")!= null)
                        terms = object.get("terms").toString();
                    return new Purchase(object.getInt("id"), object.getInt("buyer_user_id"), object.getInt("product_id"), object.getString("date"), object.get("price").toString(), object.getInt("status"), terms, object.getInt("package_status"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
