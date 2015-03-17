package ApiTests.Desktop;

import ApiTests.UsedByAll.MakeRequest;
import ApiTests.UsedByAll.ValidationChecker;
import UsedByAll.CsvUsersReader;
import UsedByAll.TestUser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by User on 3/17/2015.
 */
@RunWith(value = Parameterized.class)
public class GetUserByTokenToRun {
    private TestUser testUser;
    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_DesktopAPITest(");
    }

    public GetUserByTokenToRun(TestUser user){
        this.testUser=user;
    }
    @Test
    public void GetUserByToken() throws Exception {
        String siteUrl = UsedByAll.Config.getConfig().getProtocol() + UsedByAll.Config.getConfig().getScheme(); // Урл проверяемого сайта
        long startTime;
        long elapsedTime;

        String token = new GetTokenToRun(testUser).getToken();
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, "users/api/desktop/get-user/?_format=json&token=" + token, "GET");
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
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);

        //Парсим JSON
        JSONObject object = new JSONObject(result);

        assertNotEquals("Получен пустой массив. Проверить метод с наличием объектов.", object.length(), 0);
        JSONObject user = object.getJSONObject("user");
        assertTrue("Incorrect user_id", ValidationChecker.checkIdValue(user.getInt("user_id")));
        assertTrue("Incorrect user_name", ValidationChecker.checkStringNotNull(user.getString("user_name")));
        assertTrue("Incorrect country", ValidationChecker.checkStringNotNull(user.getString("country")));
        assertTrue("Incorrect birth_date", ValidationChecker.checkDateTimeString(user.getString("birth_date")));
        assertTrue("Incorrect email", ValidationChecker.checkEmail(user.getString("email")));
        assertTrue("Incorrect phone", ValidationChecker.checkStringNotNull(user.getString("phone_number_main")));
        assertTrue("Incorrect gender_id", ValidationChecker.checkGenderId(user.getInt("gender_id")));
        assertTrue("Incorrect user_status_id", ValidationChecker.checkUserStatusId(user.getInt("user_status_id")));
        assertTrue("Incorrect created_date", ValidationChecker.checkDateTimeString(user.getString("created_date")));
        assertEquals("Incorrect count of JSON Object",user.length(), 9 );
    }
}
