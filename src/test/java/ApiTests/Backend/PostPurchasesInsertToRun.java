package ApiTests.Backend;

import ApiTests.ObjectClasses.Purchase;
import ApiTests.UsedByAll.DateForAPI;
import ApiTests.UsedByAll.MakeRequest;
import UsedByAll.Config;
import UsedByAll.CsvUsersReader;
import UsedByAll.TestUser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.Calendar;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

/**
 * Created by User on 12/11/2014. Проверяет метод АПИ POST Purchases insert
 */
@RunWith(value = Parameterized.class)
public class PostPurchasesInsertToRun {
    private TestUser testUser;

    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_BackendAPITest(");
    }

    public PostPurchasesInsertToRun(TestUser user){
        this.testUser = user;
    }

    @Test
    public void testPostPurchasesInsert() throws IOException, JSONException {
        String siteUrl = Config.getConfig().getProtocol() + Config.getConfig().getScheme(); // Урл проверяемого сайта
        long startTime;
        long elapsedTime;
        Purchase originalOne = new GetPurchasesToRun(testUser).getAnyPurchase(testUser, siteUrl);
        Purchase newOne = new Purchase(originalOne.getId(), originalOne.getBuyerUserId(), originalOne.getProductId(), DateForAPI.makeDateTimeString(Calendar.getInstance(), 0), originalOne.getPrice(), originalOne.getPaymentAmount()+777, 5, originalOne.getTerms() );
        String newJson = "{\"buyer_user_id\":" + newOne.getBuyerUserId() + ", \"product_id\":" + newOne.getProductId() + ", \"date\":" + "\"" + newOne.getDate() + "\"" + ", \"price\":" + newOne.getPrice() + ", \"payment_amount\":" + newOne.getPaymentAmount() + ", \"status\":" + newOne.getStatus() + ", \"terms\":" +newOne.getTerms()+"}";
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, testUser, "products/api/purchase/insert/", "POST", "application/json", "application/json", true);
        OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(newJson);
        out.close();
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

        //берем из респонса id новой операции
        JSONObject response = new JSONObject(result);
        JSONArray reports = response.getJSONArray("reports");
        JSONObject report = reports.getJSONObject(0);
        int newOneId = report.getInt("id");
        System.out.println(newOneId);


        //Проверяем Get-запросом, что данный обновились
        Purchase changedPurchase = new GetPurchasesToRun(testUser).getPurchaseByParameter("id", newOneId, testUser, siteUrl);

        assertTrue("Check modified data saved correctly", newOne.equalsExceptUpdatedDate(changedPurchase));
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
    }
}
