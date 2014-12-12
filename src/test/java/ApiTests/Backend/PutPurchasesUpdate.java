package ApiTests.Backend;


import ApiTests.ObjectClasses.Purchases;
import ApiTests.UsedByAll.MakeRequest;
import UsedByAll.TestUser;
import org.json.JSONException;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import static org.junit.Assert.assertTrue;

/**
 * Created by User on 12/10/2014.
 */
public class PutPurchasesUpdate {
    @Test
    public boolean testPutPurchasesUpdate(String scheme, TestUser testUser) throws IOException, JSONException {
        Purchases originalOne = new GetPurchases().getAnyPurchase(testUser, scheme);
        Purchases newOne = new Purchases(originalOne.getId(), originalOne.getBuyerUserId(), originalOne.getProductId(), originalOne.getDate(), originalOne.getPrice(), originalOne.getPaymentAmount()+777, 1, originalOne.getTerms() );
        String originalJson = "[{\"id\":" + originalOne.getId() + ", \"buyer_user_id\":" + originalOne.getBuyerUserId() + ", \"product_id\":" + originalOne.getProductId() + ", \"date\":" + "\"" + originalOne.getDate() + "\"" + ", \"price\":" + originalOne.getPrice() + ", \"payment_amount\":" + originalOne.getPaymentAmount() + ", \"status\":" + originalOne.getStatus() + ", \"terms\":" +originalOne.getTerms()+"}]";
        String modifiedJson = "[{\"id\":" + newOne.getId() + ", \"buyer_user_id\":" + newOne.getBuyerUserId() + ", \"product_id\":" + newOne.getProductId() + ", \"date\":" + "\"" + newOne.getDate() + "\"" + ", \"price\":" + newOne.getPrice() + ", \"payment_amount\":" + newOne.getPaymentAmount() + ", \"status\":" + newOne.getStatus() + ", \"terms\":" +newOne.getTerms()+"}]";


        //Создаем url
        HttpURLConnection httpCon = MakeRequest.getConnection(scheme, testUser, "products/api/purchase/update/", "PUT", "application/json", "application/json", true);
        OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(modifiedJson);
        out.close();


        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);
        //Проверяем Get-запросом, что данные обновились
        Purchases changedPurchase = new GetPurchases().getPurchaseByParameter("id", originalOne.getId(), testUser, scheme);
        assertTrue("Check modified data saved correctly", newOne.equalsExceptUpdatedDate(changedPurchase));

        //Создаем url
        httpCon = MakeRequest.getConnection(scheme, testUser, "products/api/purchase/update/", "PUT", "application/json", "application/json", true);
        out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(originalJson);
        out.close();
        httpCon.getInputStream();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);

        //Проверяем Get запросом, что данные восстановились
        changedPurchase = new GetPurchases().getPurchaseByParameter("id", originalOne.getId(), testUser, scheme);
        assertTrue("Check modified data returned correctly", originalOne.equalsExceptUpdatedDate(changedPurchase));
        return true;

    }
}
