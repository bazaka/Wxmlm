package ApiTests.Backend;

import ApiTests.ApiValueCheckers.ValidationChecker;
import ApiTests.ObjectClasses.MakeRequest;
import UsedByAll.TestUser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import static org.junit.Assert.*;

public class GetUsers {
    @Test
    public boolean testGetUsers(String scheme, TestUser User) throws Exception{
        String url = "users/api/users/";
        HttpURLConnection httpCon = MakeRequest.getConnection(scheme, User, url, 5, "GET");
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

        //JSON
        JSONArray jsonArr = new JSONArray(result);
        //Structure
        assertNotNull("Получен пустой массив. Проверить метод с наличием объектов.", jsonArr.length());

        for(int i = 0; i<jsonArr.length(); i++)
        {
            JSONObject object = jsonArr.getJSONObject(i);

            assertTrue("Incorrect user_id", ValidationChecker.checkIdValue(object.getInt("user_id")));
            assertTrue("Incorrect surname", ValidationChecker.checkStringOrNull(object.get("surname").toString()));
            assertTrue("Incorrect name", ValidationChecker.checkNotNull(object.getString("name")));
            assertTrue("Incorrect patronymic",ValidationChecker.checkStringOrNull(object.get("patronymic").toString()));
            assertTrue("Incorrect username",ValidationChecker.checkNotNull(object.getString("username")));
            assertTrue("Incorrect password",ValidationChecker.checkNotNull(object.getString("password")));
            assertTrue("Incorrect salt",ValidationChecker.checkNotNull(object.getString("salt")));
            assertTrue("Incorrect countryId ",ValidationChecker.checkIdValue(object.getInt("country_id")));
            assertTrue("Incorrect languageId",ValidationChecker.checkIdOrNull(object.get("language_id")));
            assertTrue("Incorrect birthDate",ValidationChecker.checkDateTimeString(object.getString("birth_date")));
            assertTrue("Incorrect emailMain",ValidationChecker.checkEmail(object.getString("email_main")));
            assertTrue("Incorrect email2", ValidationChecker.checkStringOrNull(object.get("email2")) || ValidationChecker.checkEmail(object.getString("email2")));
            assertTrue("Incorrect email3", ValidationChecker.checkStringOrNull(object.get("email3")) || ValidationChecker.checkEmail(object.getString("email3")));
            assertTrue("Incorrect phoneNumberMain ",ValidationChecker.checkNotNull(object.getString("phone_number_main")));
            assertTrue("Incorrect phoneNumber2 ",ValidationChecker.checkStringOrNull(object.get("phone_number2").toString()));
            assertTrue("Incorrect phoneNumber3",ValidationChecker.checkStringOrNull(object.get("phone_number3").toString()));
            assertTrue("Incorrect passportNumber",ValidationChecker.checkStringOrNull(object.get("passport_number").toString())); // зробити норм валідацію
            assertTrue("Incorrect passportSeries",ValidationChecker.checkStringOrNull(object.get("passport_series").toString()));
            assertTrue("Incorrect passportIssuedBy",ValidationChecker.checkStringOrNull(object.get("passport_issued_by").toString()));
            assertTrue("Incorrect passportIssueDate", ValidationChecker.checkStringOrNull(object.get("passport_issue_date")) || ValidationChecker.checkDateTimeString(object.getString("passport_issue_date")));
            assertTrue("Incorrect adressMain ",ValidationChecker.checkStringOrNull(object.get("adress_main").toString()));
            assertTrue("Incorrect adress2",ValidationChecker.checkStringOrNull(object.get("adress2").toString()));
            assertTrue("Incorrect adress3",ValidationChecker.checkStringOrNull(object.get("adress3").toString()));
            assertTrue("Incorrect genderId",ValidationChecker.checkGenderId(object.getInt("gender_id")));
            assertTrue("Incorrect invitationCode",ValidationChecker.checkNotNull(object.get("invitation_code").toString()));
            assertTrue("Incorrect userStatusId",ValidationChecker.checkUserStatusId(object.getInt("user_status_id")));
            assertTrue("Incorrect createdDate", ValidationChecker.checkStringOrNull(object.get("created_date")) || ValidationChecker.checkDateTimeString(object.getString("created_date")));
            assertTrue("Incorrect createdBy",ValidationChecker.checkIdOrNull(object.get("created_by")));
            assertTrue("Incorrect changedBy",ValidationChecker.checkIdOrNull(object.get("changed_by")));
            assertTrue("Incorrect changed_date", ValidationChecker.checkStringOrNull(object.get("changed_date")) || ValidationChecker.checkDateTimeString(object.getString("changed_date")));
            assertTrue("Incorrect parentId",ValidationChecker.checkIdOrNull(object.get("parent_id")));
            assertTrue("Incorrect leaderId",ValidationChecker.checkIdOrNull(object.get("leader_id")));
            assertTrue("Incorrect network",ValidationChecker.checkBooleanValue(object.getBoolean("network")));
            assertTrue("Incorrect career",ValidationChecker.checkCareerId(object.getInt("career")));
            assertTrue("Incorrect isApproved",ValidationChecker.checkBooleanValue(object.getBoolean("is_approved")));
            assertEquals("Incorrect count of Json Objects", object.length(), 35);
        }
        return true;
    }
}
