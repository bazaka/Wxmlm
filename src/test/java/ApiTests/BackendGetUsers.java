package ApiTests;

import ApiTests.ApiValueCheckers.ValidationChecker;
import ApiTests.ObjectClasses.MakeRequest;
import UsedByAll.TestUser;
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.*;

public class BackendGetUsers {
    public static WebDriver driver;
    public static WebElement element;
    private DefaultSelenium selenium;
    //public static WebDriverWait wait;


    @Before
    public void setUp(String scheme) throws Exception{
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        selenium = new WebDriverBackedSelenium(driver, "http://" + scheme);
        //driver.get("http://" +scheme);
        System.out.println("Запуск селениум для проверки API метода GET Users на " + scheme);
    }

    @Test
   /* public boolean doTest(JSONArray jsonArr, String fieldName){

        ValidationChecker checker = new ValidationChecker();
        for (int i=0; i<jsonArr.length(); i++){
            JSONObject object = jsonArr.getJSONObject(i);
            int someField = object.getInt(fieldName);
            return assertTrue("Incorrect "+fieldName, checker.checkIdValue(someField));

        }
    }*/
    public boolean testBackendGetUsers(String scheme, TestUser User) throws Exception{
        String url = "users/api/users/";
        selenium.open(MakeRequest.request(scheme, User, url, 5));
        selenium.waitForPageToLoad("5000");

        //JSON
        JSONArray jsonArr = new JSONArray(selenium.getBodyText());
        //Structure
        ValidationChecker checker = new ValidationChecker();
        assertNotNull("Получен пустой массив. Проверить метод с наличием объектов.", jsonArr.length());

        for(int i = 0; i<jsonArr.length(); i++)
        {
            JSONObject object = jsonArr.getJSONObject(i);

            assertTrue("Incorrect user_id", checker.checkIdValue(object.getInt("user_id")));
            assertTrue("Incorrect surname", checker.checkStringOrNull(object.get("surname").toString()));
            assertTrue("Incorrect name", checker.checkNotNull(object.getString("name")));
            assertTrue("Incorrect patronymic",checker.checkStringOrNull(object.get("patronymic").toString()));
            assertTrue("Incorrect username",checker.checkNotNull(object.getString("username")));
            assertTrue("Incorrect password",checker.checkNotNull(object.getString("password")));
            assertTrue("Incorrect salt",checker.checkNotNull(object.getString("salt")));
            assertTrue("Incorrect countryId ",checker.checkIdValue(object.getInt("country_id")));
            assertTrue("Incorrect languageId",checker.checkMoreOrNullId(object.get("language_id")));
            assertTrue("Incorrect birthDate",checker.checkDateTimeString(object.getString("birth_date")));
            assertTrue("Incorrect emailMain",checker.checkEmail(object.get("email_main").toString()));
            assertTrue("Incorrect email2 ",checker.checkAnotherEmail(object.get("email2")));
            assertTrue("Incorrect email3",checker.checkAnotherEmail(object.get("email3")));
            assertTrue("Incorrect phoneNumberMain ",checker.checkNotNull(object.getString("phone_number_main")));
            assertTrue("Incorrect phoneNumber2 ",checker.checkStringOrNull(object.get("phone_number2").toString()));
            assertTrue("Incorrect phoneNumber3",checker.checkStringOrNull(object.get("phone_number3").toString()));
            assertTrue("Incorrect passportNumber",checker.checkStringOrNull(object.get("passport_number").toString())); // зробити норм валідацію
            assertTrue("Incorrect passportSeries",checker.checkStringOrNull(object.get("passport_series").toString()));
            assertTrue("Incorrect passportIssuedBy",checker.checkStringOrNull(object.get("passport_issued_by").toString()));
            assertTrue("Incorrect passportIssueDate",checker.checkDateOrNull(object.get("passport_issue_date")));
            assertTrue("Incorrect adressMain ",checker.checkStringOrNull(object.get("adress_main").toString()));
            assertTrue("Incorrect adress2",checker.checkStringOrNull(object.get("adress2").toString()));
            assertTrue("Incorrect adress3",checker.checkStringOrNull(object.get("adress3").toString()));
            assertTrue("Incorrect genderId",checker.checkGenderId(object.getInt("gender_id")));
            assertTrue("Incorrect invitationCode",checker.checkNotNull(object.get("invitation_code").toString()));
            assertTrue("Incorrect userStatusId",checker.checkUserStatusId(object.getInt("user_status_id")));
            assertTrue("Incorrect createdDate",checker.checkDateOrNull(object.get("created_date")));
            assertTrue("Incorrect createdBy",checker.checkMoreOrNullId(object.get("created_by")));
            assertTrue("Incorrect changedBy",checker.checkMoreOrNullId(object.get("changed_by")));
            assertTrue("Incorrect changed_date",checker.checkDateOrNull(object.get("changed_date")));
            assertTrue("Incorrect parentId",checker.checkMoreOrNullId(object.get("parent_id")));
            assertTrue("Incorrect leaderId",checker.checkMoreOrNullId(object.get("leader_id")));
            assertTrue("Incorrect network",checker.checkBooleanValue(object.getBoolean("network")));
            assertTrue("Incorrect career",checker.checkCareer(object.getInt("career")));
            assertTrue("Incorrect isApproved",checker.checkBooleanValue(object.getBoolean("is_approved")));
            assertEquals("Incorrect count of Json Objects", object.length(), 35);

        }
        return true;
    }
    @After
    public void tearDown() throws Exception{
        selenium.stop();
    }


}
