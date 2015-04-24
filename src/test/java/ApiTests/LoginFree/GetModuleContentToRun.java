package ApiTests.LoginFree;

import ApiTests.Backend.GetProductsToRun;
import ApiTests.UsedByAll.MakeRequest;
import ApiTests.UsedByAll.ValidationChecker;
import UsedByAll.Config;
import UsedByAll.CsvUsersReader;
import UsedByAll.RegionMatch;
import UsedByAll.TestUser;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

//* Created for W-xmlm by Fill on 05.12.2014. Gets module's current version by product_id
@RunWith(value = Parameterized.class)
public class GetModuleContentToRun {
    private TestUser testUser;

    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_LoginFreeAPITest(");
    }

    public GetModuleContentToRun(TestUser user){
        this.testUser = user;
    }
    @Test
    public void testGetModuleContent() throws Exception {
        String siteUrl = Config.getConfig().getProtocol() + Config.getConfig().getScheme(); // Урл проверяемого сайта
        long startTime;
        long elapsedTime;
        int[] ids = GetProductsToRun.getProductsIDs(siteUrl, testUser);
        for (int i = 0; i < (ids.length - 1); i++) {
            // Создаем соединение
            startTime = System.currentTimeMillis();
            HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, "application/api/desktop/get-module-content/?product_id=" + ids[i], "GET");

            // Читаем ответ
            int responseCode = httpCon.getResponseCode();
            assertTrue("Check response code is 200", (responseCode == 404 || responseCode == 200));
            InputStream inStrm;
            try {
                inStrm = httpCon.getInputStream();
            }
            catch (FileNotFoundException e){
                inStrm = httpCon.getErrorStream();
            }
            InputStreamReader isReader = new InputStreamReader(inStrm);
            elapsedTime = System.currentTimeMillis() - startTime;
            BufferedReader br = new BufferedReader(isReader);
            String result = "";
            String line;
            while ((line = br.readLine()) != null) {
                result += line;
            }
            br.close();
            assertFalse("Response contains html in its body", RegionMatch.IsStringRegionMatch(result, "<br />"));
            //Парсим JSON
            JSONObject object = new JSONObject(result);
            if (object.has("errors")) {
                System.out.println(object.getString("errors"));
                assertTrue("Error is different from \"Application not found\"1", object.getString("errors").equals("File not found")||(object.getString("error").equals("Application not found")));
                System.out.println("Product with ID " + ids[i] + ": application not found");
            }
            else if (object.has("error")) {
                System.out.println(object.getString("error"));
                assertTrue("Error is different from \"Application not found\"2", (object.getString("error").equals("File not found"))||(object.getString("error").equals("Application not found")));
                System.out.println("Product with ID " + ids[i] + ": Application not found");
            }
            else {
                //Проверяем структуру
                assertTrue("Incorrect content", ValidationChecker.checkStringNotNull(object.getString("source")));
            }
            System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime +" by product_id = " +i);
        }
    }
}