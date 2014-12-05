package BackendApiTests;

import BackendApiTests.ApiValueCheckers.ValidationChecker;
import BackendApiTests.ObjectClasses.MakeRequest;
import org.json.JSONObject;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// * Created for W-xmlm by Fill on 04.12.2014. Gets application's current version
public class GetApplicationInfo {
    @Test
    public boolean testGetApplicationInfo(String scheme) throws Exception {
        HttpURLConnection httpCon = MakeRequest.getConnection(scheme, "application/api/desktop/get-application-info/", "GET");
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
        ValidationChecker checker = new ValidationChecker();
        assertTrue("Incorrect id", checker.checkIdValue(object.getInt("id")));
        assertTrue("Incorrect version", checker.checkString(object.get("version")));
        assertTrue("Incorrect filename", checker.checkString(object.get("filename")));
        assertTrue("Incorrect path", checker.checkString(object.get("path")));
        assertTrue("Incorrect status", object.getBoolean("status"));
        assertEquals("Incorrect count of Json parameters", object.length(), 5);
    return true;
    }
}