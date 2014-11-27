package ApiTests;

import ApiTests.ApiValueCheckers.ValidationChecker;
import UsedByAll.TestUser;
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.junit.Assert.assertTrue;

// * Created for W-xmlm by Fill on 25.11.2014.
public class BackendGetOperations {
    private DefaultSelenium selenium;

    @Before
    public void setUp(String scheme) throws Exception {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        selenium = new WebDriverBackedSelenium(driver, "http://" + scheme);
        System.out.println("Запускаю селениум для проверки API-метода GET Accounts на " + scheme);
    }

    @Test
    public boolean testBackendGetOperations(String scheme, TestUser user) throws Exception {
        //Создаем и отсылаем запрос
        Calendar calBefore = Calendar.getInstance();
        Calendar calAfter = Calendar.getInstance();
        calBefore.add(Calendar.DATE, -5);
        calAfter.add(Calendar.DATE, 5);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String request = "http://" + scheme + "money/api/operations/?limit=10&offset=0&dt_from=" + dateFormat.format(calBefore.getTime()) + "T" + timeFormat.format(calBefore.getTime()) + "&dt_to=" + dateFormat.format(calAfter.getTime()) + "T" + timeFormat.format(calAfter.getTime());

        // Содзаем URL
        String authString = user.getEmail() + ":" + user.getPassword1();
        byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
        String authStringEnc = new String(authEncBytes);
        URL url = new URL(request);
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        httpCon.setRequestProperty("Authorization", "Basic " + authStringEnc);
        httpCon.setRequestMethod("GET");
        httpCon.setRequestProperty("Accept", "application/json");
        InputStream inStrm = httpCon.getInputStream();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);

        /*selenium.open(request);
        selenium.waitForPageToLoad("5000");*/

        //Парсим JSON
        JSONArray jsonArr = new JSONArray(httpCon.getResponseMessage());
        //Проверяем структуру
        ValidationChecker checker = new ValidationChecker();
        if (jsonArr.length() == 0) {
            System.out.print("Получен пустой массив. Рекомендуется проверить метод с наличием объектов. ");
            return false;
        }
        for (int i = 0; i < jsonArr.length(); i++) {
            JSONObject object = jsonArr.getJSONObject(i);

            assertTrue("Incorrect id", checker.checkIdValue(object.getInt("id")));
            assertTrue("Incorrect target_account_id", checker.checkIdValue(object.getInt("target_account_id")));
            assertTrue("Incorrect source_account_id", checker.checkIdValue(object.getInt("source_account_id")));
            assertTrue("Incorrect purchase_id", checker.checkIdOrNull(object.getString("purchase_id")));
            assertTrue("Incorrect initiator_user_id", checker.checkIdValue(object.getInt("initiator_user_id")));
            assertTrue("Incorrect created_date", checker.checkDateTimeString(object.getString("created_date")));
            assertTrue("Incorrect updated_date", checker.checkDateTimeString(object.getString("updated_date")));
            assertTrue("Incorrect amount", checker.checkDoubleValue(object.getDouble("amount")));
            assertTrue("Incorrect status", checker.checkOperationStatusId(object.getInt("status")));
            assertTrue("Incorrect type", checker.checkOperationTypeId(object.getInt("type")));
            assertTrue("Incorrect quarantine", checker.checkBooleanValue(object.getBoolean("quarantine")));
            assertTrue("Incorrect parameters number", object.length() == 11); // assertEquals
        }
        return true;
    }

}
