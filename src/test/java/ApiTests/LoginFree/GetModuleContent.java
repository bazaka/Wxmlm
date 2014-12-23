package ApiTests.LoginFree;

import ApiTests.Backend.GetProducts;
import ApiTests.UsedByAll.MakeRequest;
import ApiTests.UsedByAll.ValidationChecker;
import UsedByAll.RegionMatch;
import UsedByAll.TestUser;
import org.json.JSONObject;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

//* Created for W-xmlm by Fill on 05.12.2014. Gets module's current version by product_id
public class GetModuleContent {
    @Test
    public boolean testGetModuleContent(String scheme, TestUser user) throws Exception {
        int[] ids = GetProducts.getProductsIDs(scheme, user);
        for (int i = 0; i < (ids.length - 1); i++) {
            // Создаем соединение
            HttpURLConnection httpCon = MakeRequest.getConnection(scheme, "application/api/desktop/get-module-content/?product_id=" + ids[i], "GET");

            // Отправляем запрос
            InputStream inStrm = httpCon.getInputStream();

            // Читаем ответ
            assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);
            InputStreamReader isReader = new InputStreamReader(inStrm);
            BufferedReader br = new BufferedReader(isReader);
            String result = "";
            String line;
            while ((line = br.readLine()) != null) {
                result += line;
            }
            br.close();
            if (RegionMatch.IsStringRegionMatch(result, "<br />")) {
                System.out.println("Response contains html in its body. Look: " + result);
                return false;
            }
            //Парсим JSON
            JSONObject object = new JSONObject(result);
            if (object.has("errors")) {
                assertTrue("Error is different from \"Application not found\"", object.getString("errors").equals("Application not found"));
                System.out.println("Product with ID " + ids[i] + ": application not found");
            }
            else {
                //Проверяем структуру
                assertTrue("Incorrect content", ValidationChecker.checkStringNotNull(object.getString("source")));
            }
        }
        return true;
    }
}