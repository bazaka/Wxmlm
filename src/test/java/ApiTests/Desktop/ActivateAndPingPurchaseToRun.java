package ApiTests.Desktop;

import ApiTests.ObjectClasses.Purchase;
import ApiTests.UsedByAll.MakeRequest;
import ApiTests.UsedByAll.ValidationChecker;
import UsedByAll.CsvUsersReader;
import UsedByAll.TestUser;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.Collection;

import static org.junit.Assert.*;

// * Created for W-xmlm by Fill on 17.03.2015.
@RunWith(value = Parameterized.class)
public class ActivateAndPingPurchaseToRun {
    private TestUser testUser;

    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_DesktopAPITest(");
    }

    public ActivateAndPingPurchaseToRun(TestUser user){
        this.testUser = user;
    }

    @Test
    public void testPutPurchaseActivationAndGetPing() throws Exception {
        String siteUrl = UsedByAll.Config.getConfig().getProtocol() + UsedByAll.Config.getConfig().getScheme(); // Урл проверяемого сайта
        Purchase purchaseToActivate = new GetMyPurchasesToRun(testUser).getMyNotActivePackage();
        assertNotNull("There is no Not Active Purchase for this user", purchaseToActivate);
        String token = new GetTokenToRun(testUser).getToken();
        String modifiedJson = "{\"token\": \"" + token + "\", \"purchase_id\":" + purchaseToActivate.getId() + "}";

        long startTime;
        long elapsedTime;
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, "users/api/desktop/activation/", "PUT", "application/json", "application/json", true);
        OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(modifiedJson);
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
        System.out.println("Activation total elapsed http request/response time in milliseconds: " + elapsedTime);

        //Парсим JSON
        JSONObject object = new JSONObject(result);

        assertFalse("Sory, something went wrong: " + result, object.has("errors"));
        String packageSecureKey = object.getString("packagesecurekey");
        assertTrue("Incorrect packagesecurekey", ValidationChecker.checkPackageSecureKey(packageSecureKey));
        assertEquals("Incorrect count of JSON Objects", object.length(),1);

        //Ping
        for (int i = 0; i < 5; i++) {
            startTime = System.currentTimeMillis();
            httpCon = MakeRequest.getConnection(siteUrl, "users/api/desktop/ping/?_format=json&token=" + token + "&packagesecurekey=" + packageSecureKey, "GET");
            inStrm = httpCon.getInputStream();
            assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);
            elapsedTime = System.currentTimeMillis() - startTime;
            isReader = new InputStreamReader(inStrm);
            br = new BufferedReader(isReader);
            result = "";
            while ((line = br.readLine()) != null) {
                result += line;
            }
            br.close();
            System.out.println("Ping total elapsed http request/response time in milliseconds: " + elapsedTime);
            object = new JSONObject(result);
            System.out.println(result);
            assertTrue("Incorrect lifetime", ValidationChecker.checkPositiveInt(object.getInt("lifetime")));
            assertEquals("Incorrect count of JSON Objects", object.length(),1);
            Thread.sleep(60000);
        }
    }
}
