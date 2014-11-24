package ApiTests;

import ApiTests.ApiValueCheckers.ValidationChecker;
import UsedByAll.TestUser;
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.junit.Assert.assertTrue;

public class BackendGetUsers {
    private DefaultSelenium selenium;

    @Before
    public void setUp(String scheme) throws Exception{
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        selenium = new WebDriverBackedSelenium(driver, "http://" + scheme);
        System.out.print("Запуск селениум для проверки API метода GET Users на " +scheme);
    }

    @Test
    public boolean testBackendGetUsers(String scheme, TestUser User) throws Exception{
        Calendar calBefore = Calendar.getInstance();            //якщо ця хрень буде для всіх ГЕТ однакова, то потім винести в окремий метод
        Calendar calAfter = Calendar.getInstance();
        calBefore.add(Calendar.MONTH, -1);
        calAfter.add(Calendar.MONTH, 1);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String request = "http://" + User.getEmail() + ":" + User.getPassword1() + "@" + scheme + "users/api/users/?limit=10&offset=0&dt_from=" + dateFormat.format(calBefore.getTime()) + "T" + timeFormat.format(calBefore.getTime()) + "&dt_to=" + dateFormat.format(calAfter.getTime()) + "T" + timeFormat.format(calAfter.getTime());

        //String request = "http://" + User.getEmail() + ":" + User.getPassword1() + "@" + scheme + "users/api/users?limit=10&offset=0&dt_from=" + dateFormat.format(calBefore.getTime()) + "T" + timeFormat.format(calBefore.getTime()) + "&dt_to=" + dateFormat.format(calAfter.getTime()) + "T" + timeFormat.format(calAfter.getTime());
        selenium.open(request); // спробувати змінити на драйвер.гет(юрл)
        selenium.waitForPageToLoad("5000"); // потім змінні повиносити нагору, так зручніше редагувати потім

        //JSON
        JSONArray jsonArr = new JSONArray(selenium.getBodyText());
        //Structure
        ValidationChecker checker = new ValidationChecker();
        if (jsonArr.length() == 0){         // замінити на Ассерт
            System.out.print("Получен пустой массив. Проверить метод с наличием объектов. ");
            return false;
        }
        for(int i = 0; i<jsonArr.length(); i++)
        {
            JSONObject object = jsonArr.getJSONObject(i);

            int userId = object.getInt("user_id");
            assertTrue("Incorrect user_id", checker.checkIdValue(userId));

            String surname = object.get("surname").toString();
            assertTrue("Incorrect surname", checker.checkStringOrNull(surname));

            String name = object.getString("name");
            assertTrue("Incorrect name", checker.checkNotNull(name));

            String patronymic = object.get("patronymic").toString();
            assertTrue("Incorrect patronymic",checker.checkStringOrNull(patronymic));

            String username = object.getString("username");
            assertTrue("Incorrect username",checker.checkNotNull(username));

            String password = object.getString("password");
            assertTrue("Incorrect password",checker.checkNotNull(password));

            String salt = object.getString("salt");
            assertTrue("Incorrect salt",checker.checkNotNull(salt));

            int countryId = object.getInt("country_id");
            assertTrue("Incorrect countryId ",checker.checkIdValue(countryId));

            /*int languageId = object.getInt("language_id");
            assertTrue("Incorrect languageId",checker.checkMoreOrNull(languageId));*/
            Object languageId = object.get("language_id");
            assertTrue("Incorrect languageId",checker.checkMoreOrNullId(languageId));

            String birthDate = object.getString("birth_date");
            assertTrue("Incorrect birthDate",checker.checkDateTimeString(birthDate));

            String emailMain = object.get("email_main").toString();
            assertTrue("Incorrect emailMain",checker.checkEmail(emailMain));

            Object email2 = object.get("email2");
            assertTrue("Incorrect email2 ",checker.checkAnotherEmail(email2));

            Object email3 = object.get("email3");
            assertTrue("Incorrect email3",checker.checkAnotherEmail(email3));

            String phoneNumberMain = object.getString("phone_number_main");
            assertTrue("Incorrect phoneNumberMain ",checker.checkNotNull(phoneNumberMain));

            String phoneNumber2 = object.get("phone_number2").toString();
            assertTrue("Incorrect phoneNumber2 ",checker.checkStringOrNull(phoneNumber2));

            String phoneNumber3 = object.get("phone_number3").toString();
            assertTrue("Incorrect phoneNumber3",checker.checkStringOrNull(phoneNumber3));

            /*int passportNumber = object.getInt("passport_number");
            assertTrue("Incorrect passportNumber",checker.checkMoreOrNull(passportNumber));*/

            String passportSeries = object.get("passport_series").toString();
            assertTrue("Incorrect passportSeries",checker.checkStringOrNull(passportSeries));

            String passportIssuedBy = object.get("passport_issued_by").toString();
            assertTrue("Incorrect passportIssuedBy",checker.checkStringOrNull(passportIssuedBy));

            String passportIssueDate = object.get("passport_issue_date").toString();  // ЗРОБИТИ валідатор DateOrNull
            assertTrue("Incorrect passportIssueDate",checker.checkStringOrNull(passportIssueDate));

            String adressMain = object.get("adress_main").toString();
            assertTrue("Incorrect adressMain ",checker.checkStringOrNull(adressMain));

            String adress2 = object.get("adress2").toString();
            assertTrue("Incorrect adress2",checker.checkStringOrNull(adress2));

            String adress3 = object.get("adress3").toString();
            assertTrue("Incorrect adress3",checker.checkStringOrNull(adress3));

            int genderId = object.getInt("gender_id");
            assertTrue("Incorrect genderId",checker.checkGenderId(genderId));

            String invitationCode = object.get("invitation_code").toString();
            assertTrue("Incorrect invitationCode",checker.checkNotNull(invitationCode));

            int userStatusId = object.getInt("user_status_id");
            assertTrue("Incorrect userStatusId",checker.checkUserStatusId(userStatusId));

            String createdDate = object.get("created_date").toString();//datetime
            assertTrue("Incorrect createdDate",checker.checkDateTimeString(createdDate));

            Object createdBy = object.get("created_by");
            assertTrue("Incorrect createdBy",checker.checkMoreOrNullId(createdBy));

            Object changedBy= object.get("changed_by");
            assertTrue("Incorrect changedBy",checker.checkMoreOrNullId(changedBy));

            String changedDate= object.get("changed_date").toString();       // datetime
            assertTrue("Incorrect changed_date",checker.checkStringOrNull(changedDate));

            Object parentId = object.get("parent_id");
            assertTrue("Incorrect parentId",checker.checkMoreOrNullId(parentId));

            Object leaderId= object.get("leader_id");
            assertTrue("Incorrect leaderId",checker.checkMoreOrNullId(leaderId));

            boolean network = object.getBoolean("network");
            assertTrue("Incorrect network",checker.checkBooleanValue(network));

            int career = object.getInt("career");
            assertTrue("Incorrect career",checker.checkCareer(career));

            boolean isApproved = object.getBoolean("is_approved");
            assertTrue("Incorrect isApproved",checker.checkBooleanValue(isApproved));


        }
        return true;
    }
    @After
    public void tearDown() throws Exception{
        selenium.stop();
    }


}
