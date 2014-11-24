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
        calBefore.add(Calendar.MONTH, -1);
        calAfter.add(Calendar.MONTH, 1);
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
        for (int i = 0; i < jsonArr.length(); i++)
        {
            JSONObject object = jsonArr.getJSONObject(i);
            int accountId = object.getInt("account_id");
            boolean isAccountIdValid = checker.checkIdValue(accountId);
            int userId = object.getInt("user_id");
            boolean isUserIdValid = checker.checkIdValue(userId);
            String accountNumber = object.getString("account_number");
            boolean isAccountNumberValid = checker.checkAccountNumberValue(accountNumber);
            int accountType = object.getInt("account_type");
            boolean isAccountTypeValid = checker.checkAccountTypeValue(accountType);
            boolean status = object.getBoolean("status");
            boolean isStatusValid = checker.checkBooleanValue(status);
            String accountInfo = object.getString("account_info");
            boolean isAccountInfoValid = checker.checkNotNull(accountInfo);
            double amount = object.getDouble("amount");
            boolean isAmountValid = checker.checkDoubleValue(amount);
            String updatedDate = object.getString("updated_date");
            boolean isDateTimeValid = checker.checkDateTimeString(updatedDate);
            if (!isAccountIdValid || !isUserIdValid || !isAccountNumberValid || !isAccountTypeValid || !isStatusValid || !isAccountInfoValid || !isAmountValid || !isDateTimeValid || object.length() != 8)
            {
                System.out.print("Проверка API GET accounts НЕ пройдена на объекте с ID " + accountId + ". ");  // тут єбануть асерт
                return false;
            }
        }
        return true;
    }

    public Account getAccount(TestUser user, String scheme, DefaultSelenium selenium)
    {
        //Создаем и отсылаем запрос
        Calendar calBefore = Calendar.getInstance();
        Calendar calAfter = Calendar.getInstance();
        calBefore.add(Calendar.MONTH, -1);
        calAfter.add(Calendar.MONTH, 1);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String request = "http://" + user.getEmail() + ":" + user.getPassword1() + "@" + scheme + "money/api/accounts/?limit=10&offset=0&dt_from=" + dateFormat.format(calBefore.getTime()) + "T" + timeFormat.format(calBefore.getTime()) + "&dt_to=" + dateFormat.format(calAfter.getTime()) + "T" + timeFormat.format(calAfter.getTime());
        selenium.open(request);
        selenium.waitForPageToLoad("5000");
        //Парсим JSON, делаем и возвращаем обьект Account
        try {JSONArray jsonArr = new JSONArray(selenium.getBodyText());
            JSONObject object = jsonArr.getJSONObject(0);
            return new Account(object.getInt("account_id"), object.getString("account_number"), object.getInt("account_type"), object.getBoolean("status"), object.getString("account_info"), object.getDouble("amount"));}
        catch (Exception e) { e.printStackTrace(); return null;}
    }

	@After
	public void tearDown() throws Exception { selenium.stop(); }
}