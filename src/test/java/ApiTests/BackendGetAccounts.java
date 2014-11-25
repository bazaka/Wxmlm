package ApiTests;

import ApiTests.ObjectClasses.Account;
import UsedByAll.TestUser;
import com.thoughtworks.selenium.*;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.json.*;
import ApiTests.ApiValueCheckers.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.Assert.assertTrue;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BackendGetAccounts {
    private DefaultSelenium selenium;

    @Before
    public void setUp(String scheme) throws Exception {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        selenium = new WebDriverBackedSelenium(driver, "http://" + scheme);
        System.out.println("Запускаю селениум для проверки API-метода GET Accounts на " + scheme);
    }

    @Test
    public boolean testBackendGetAccounts(String scheme, TestUser User) throws Exception {
        //Создаем и отсылаем запрос
        Calendar calBefore = Calendar.getInstance();
        Calendar calAfter = Calendar.getInstance();
        calBefore.add(Calendar.DATE, -5);
        calAfter.add(Calendar.DATE, 5);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String request = "http://" + User.getEmail() + ":" + User.getPassword1() + "@" + scheme + "money/api/accounts/?limit=10&offset=0&dt_from=" + dateFormat.format(calBefore.getTime()) + "T" + timeFormat.format(calBefore.getTime()) + "&dt_to=" + dateFormat.format(calAfter.getTime()) + "T" + timeFormat.format(calAfter.getTime());
        System.out.println("Открываю запрос: " + request);
        selenium.open(request);
        selenium.waitForPageToLoad("5000");

        //Парсим JSON
        JSONArray jsonArr = new JSONArray(selenium.getBodyText());
        //Проверяем структуру
        ValidationChecker checker = new ValidationChecker();
        if (jsonArr.length() == 0) {
            System.out.print("Получен пустой массив. Рекомендуется проверить метод с наличием объектов. ");
            return false;
        }
        for (int i = 0; i < jsonArr.length(); i++) {
            JSONObject object = jsonArr.getJSONObject(i);

            assertTrue("Incorrect account_id", checker.checkIdValue(object.getInt("account_id")));
            assertTrue("Incorrect user_id", checker.checkIdValue(object.getInt("user_id")));
            assertTrue("Incorrect account_number", checker.checkAccountNumberValue(object.getString("account_number")));
            assertTrue("Incorrect account_type", checker.checkAccountTypeValue(object.getInt("account_type")));
            assertTrue("Incorrect status", checker.checkBooleanValue(object.getBoolean("status")));
            assertTrue("Incorrect account_info", checker.checkNotNull(object.getString("account_info")));
            assertTrue("Incorrect amount", checker.checkDoubleValue(object.getDouble("amount")));
            assertTrue("Incorrect updated_date", checker.checkDateTimeString(object.getString("updated_date")));
            assertTrue("Incorrect parameters number", object.length() != 8);
        }

        return true;
    }

    public Account getAccount(TestUser user, String scheme, DefaultSelenium selenium) {
        //Создаем и отсылаем запрос
        Calendar calBefore = Calendar.getInstance();
        Calendar calAfter = Calendar.getInstance();
        calBefore.add(Calendar.DATE, -5);
        calAfter.add(Calendar.DATE, 5);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String request = "http://" + user.getEmail() + ":" + user.getPassword1() + "@" + scheme + "money/api/accounts/?limit=1000&offset=0&dt_from=" + dateFormat.format(calBefore.getTime()) + "T" + timeFormat.format(calBefore.getTime()) + "&dt_to=" + dateFormat.format(calAfter.getTime()) + "T" + timeFormat.format(calAfter.getTime());
        selenium.open(request);
        selenium.waitForPageToLoad("5000");
        //Парсим JSON, делаем и возвращаем обьект Account
        try {
            JSONArray jsonArr = new JSONArray(selenium.getBodyText());
            JSONObject object = jsonArr.getJSONObject(0);
            return new Account(object.getInt("account_id"), object.getString("account_number"), object.getInt("account_type"), object.getBoolean("status"), object.getString("account_info"), object.getDouble("amount"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Account getAccount(String parameterName, String parameterValue, TestUser user, String scheme, DefaultSelenium selenium) {
        //Создаем и отсылаем запрос
        Calendar calBefore = Calendar.getInstance();
        Calendar calAfter = Calendar.getInstance();
        calBefore.add(Calendar.DATE, -1);
        calAfter.add(Calendar.DATE, 1);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String request = "http://" + user.getEmail() + ":" + user.getPassword1() + "@" + scheme + "money/api/accounts/?limit=1000&offset=0&dt_from=" + dateFormat.format(calBefore.getTime()) + "T" + timeFormat.format(calBefore.getTime()) + "&dt_to=" + dateFormat.format(calAfter.getTime()) + "T" + timeFormat.format(calAfter.getTime());
        selenium.open(request);
        selenium.waitForPageToLoad("5000");
        //Парсим JSON, делаем и возвращаем обьект Account
        try {
            JSONArray jsonArr = new JSONArray(selenium.getBodyText());
            for (int i = 0; i < jsonArr.length(); i++) {
                JSONObject object = jsonArr.getJSONObject(i);
                if (object.getString(parameterName).equals(parameterValue)) {
                    return new Account(object.getInt("account_id"), object.getString("account_number"), object.getInt("account_type"), object.getBoolean("status"), object.getString("account_info"), object.getDouble("amount"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }


    @After
    public void tearDown() throws Exception {
        selenium.stop();
    }
}