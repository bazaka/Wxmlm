package ApiTests.LoginFree;

import ApiTests.ApiValueCheckers.ValidationChecker;
import ApiTests.Backend.GetProducts;
import ApiTests.ObjectClasses.MakeRequest;
import UsedByAll.TestUser;
import org.json.JSONObject;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

//* Created for W-xmlm by Fill on 05.12.2014. Gets module's current version by product_id
public class GetModuleVersion {
    @Test
    public boolean testGetModuleVersion(String scheme, TestUser user) throws Exception {
        int[] ids = GetProducts.getProductsIDs(scheme, user);
        for (int i = 0; i < (ids.length - 1); i++) {
            HttpURLConnection httpCon = MakeRequest.getConnection(scheme, "application/api/desktop/get-module-info/?product_id=" + ids[i], "GET");
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

            //Парсим JSON
            JSONObject object = new JSONObject(result);
            //Проверяем структуру
            /*assertTrue("Incorrect id", ValidationChecker.checkIdValue(object.getInt("id")));
            assertTrue("Incorrect product_id", ValidationChecker.checkIdValue(object.getInt("id")) && object.getInt("id") == ids[i]);
            assertTrue("Incorrect title", ValidationChecker.checkStringOrNull(object.getString("title")));
            assertTrue("Incorrect version", ValidationChecker.checkStringNotNull(object.getString("version")));
            assertTrue("Incorrect filename", checker.checkString(object.get("filename")));
            assertTrue("Incorrect path", checker.checkString(object.get("path")));
            assertTrue("Incorrect status", object.getBoolean("status"));
            assertEquals("Incorrect count of Json parameters", object.length(), 5);*/
        }
        return true;
    }
}
