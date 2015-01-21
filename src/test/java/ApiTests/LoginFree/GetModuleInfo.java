package ApiTests.LoginFree;

import ApiTests.Backend.GetProducts;
import ApiTests.UsedByAll.MakeRequest;
import ApiTests.UsedByAll.ValidationChecker;
import UsedByAll.TestUser;
import org.json.JSONObject;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

//* Created for W-xmlm by Fill on 05.12.2014. Gets module's current version by product_id
public class GetModuleInfo {
    @Test
    public boolean testGetModuleInfo(String scheme, TestUser user) throws Exception {
        int[] ids = GetProducts.getProductsIDs(scheme, user);
        int responseCode;
        for (int i = 0; i < (ids.length - 1); i++) {
            // Создаем соединение
            HttpURLConnection httpCon = MakeRequest.getConnection(scheme, "application/api/desktop/get-module-info/?product_id=" + ids[i], "GET");

            // Отправляем запрос
            assertTrue("Check response code is 200", (httpCon.getResponseCode() == 200));
            InputStream inStrm = httpCon.getInputStream();

            // Читаем ответ
            InputStreamReader isReader = new InputStreamReader(inStrm);
            BufferedReader br = new BufferedReader(isReader);
            String result = "";
            String line;
            while ((line = br.readLine()) != null) {
                result += line;
            }
            br.close();

            //Парсим JSON
            JSONObject object = new JSONObject(result);
            //Проверяем структуру
            assertTrue("Incorrect id", ValidationChecker.checkIdValue(object.getInt("id")));
            assertTrue("Incorrect product_id", (ValidationChecker.checkIdValue(object.getInt("product_id"))) && (ids[i] == object.getInt("product_id")));
            assertTrue("Incorrect title", ValidationChecker.checkStringOrNull(object.getString("title")));
            assertTrue("Incorrect version", ValidationChecker.checkStringNotNull(object.getString("version")));
            assertTrue("Incorrect description", ValidationChecker.checkStringOrNull(object.getString("description")));
            assertTrue("Incorrect filename", ValidationChecker.checkStringNotNull(object.getString("filename")));
            assertTrue("Incorrect url", ValidationChecker.checkURLOnDomain(object.getString("url"), scheme));
            assertEquals("Incorrect count of Json parameters", object.length(), 7);
        }
        return true;
    }
}