package ApiTests.Backend;

import ApiTests.ObjectClasses.Purchases;
import ApiTests.UsedByAll.DateForAPI;
import ApiTests.UsedByAll.MakeRequest;
import UsedByAll.TestUser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.Calendar;

import static org.junit.Assert.assertTrue;

/**
 * Created by User on 12/11/2014.
 */
public class PostPurchasesInsert {
    @Test
    public boolean testPostPurchasesInsert(String scheme, TestUser testUser) throws IOException, JSONException {
        long startTime;
        long elapsedTime;
        Purchases originalOne = new GetPurchases().getAnyPurchase(testUser, scheme);
        Purchases newOne = new Purchases(originalOne.getId(), originalOne.getBuyerUserId(), originalOne.getProductId(), DateForAPI.makeDateTimeString(Calendar.getInstance(), 0), originalOne.getPrice(), originalOne.getPaymentAmount()+777, originalOne.getStatus(), originalOne.getTerms() );
        String newJson = "{\"buyer_user_id\":" + newOne.getBuyerUserId() + ", \"product_id\":" + newOne.getProductId() + ", \"date\":" + "\"" + newOne.getDate() + "\"" + ", \"price\":" + newOne.getPrice() + ", \"payment_amount\":" + newOne.getPaymentAmount() + ", \"status\":" + newOne.getStatus() + ", \"terms\":" +newOne.getTerms()+"}";
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(scheme, testUser, "products/api/purchase/insert/", "POST", "application/json", "application/json", true);
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


        //Проверяем Get-запросом, что данный обновились
        Purchases changedPurchase = new GetPurchases().getPurchaseByParameter("id", newOneId, testUser, scheme);

        assertTrue("Check modified data saved correctly", newOne.equalsExceptUpdatedDate(changedPurchase));
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
        return true;

    }
}
