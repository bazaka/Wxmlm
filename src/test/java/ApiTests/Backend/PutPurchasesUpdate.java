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
        Purchases originalPurchase = new GetPurchases().getAnyPurchase(testUser, scheme);
        Purchases modifiedPurchase = new Purchases(originalPurchase.getId(), originalPurchase.getBuyerUserId(), originalPurchase.getProductId(), originalPurchase.getDate(), originalPurchase.getPrice(), originalPurchase.getPaymentAmount()+777, 2, originalPurchase.getTerms() );
        String originalJson = "[{\"id\":" + originalPurchase.getId() + ", \"buyer_user_id\":" + originalPurchase.getBuyerUserId() + ", \"product_id\":" + originalPurchase.getProductId() + ", \"date\":" + "\"" + originalPurchase.getDate() + "\"" + ", \"price\":" + originalPurchase.getPrice() + ", \"payment_amount\":" + originalPurchase.getPaymentAmount() + ", \"status\":" + originalPurchase.getStatus() + ", \"terms\":" +originalPurchase.getTerms()+"}]";
        String modifiedJson = "[{\"id\":" + modifiedPurchase.getId() + ", \"buyer_user_id\":" + modifiedPurchase.getBuyerUserId() + ", \"product_id\":" + modifiedPurchase.getProductId() + ", \"date\":" + "\"" + modifiedPurchase.getDate() + "\"" + ", \"price\":" + modifiedPurchase.getPrice() + ", \"payment_amount\":" + modifiedPurchase.getPaymentAmount() + ", \"status\":" + modifiedPurchase.getStatus() + ", \"terms\":" +modifiedPurchase.getTerms()+"}]";

        System.out.println("modif"+modifiedJson);
        //Создаем url
        HttpURLConnection httpCon = MakeRequest.getConnection(scheme, testUser, "products/api/purchase/update/", "PUT", "application/json", "application/json", true);
        OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(modifiedJson);
        out.close();
        System.out.println("resp: " +httpCon.getResponseCode());

        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);
        //Проверяем Get-запросом, что данные обновились
        Purchases changedPurchase = new GetPurchases().getPurchaseByParameter("id", originalPurchase.getId(), testUser, scheme);
        assertTrue("Check modified data saved correctly", modifiedPurchase.equalsExceptUpdatedDate(changedPurchase));

        //Создаем url
        httpCon = MakeRequest.getConnection(scheme, testUser, "products/api/purchase/update/", "PUT", "application/json", "application/json", true);
        out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(originalJson);
        out.close();
        httpCon.getInputStream();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);

        //Проверяем Get запросом, что данные восстановились
        changedPurchase = new GetPurchases().getPurchaseByParameter("id", originalPurchase.getId(), testUser, scheme);
        assertTrue("Check modified data returned correctly", originalPurchase.equalsExceptUpdatedDate(changedPurchase));
        return true;

    }
}
