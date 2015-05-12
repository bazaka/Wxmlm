package ApiTests.Backend;

import ApiTests.ObjectClasses.User;
import ApiTests.UsedByAll.MakeRequest;
import UsedByAll.Config;
import UsedByAll.CsvUsersReader;
import UsedByAll.TestUser;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

/**
 * Created by User on 3/5/2015.
 */
@RunWith(value = Parameterized.class)
public class PutUserUpdateToRun {
    private TestUser testUser;
    static final String url = "users/api/user/update/";

    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_BackendAPITest(");
    }

    public PutUserUpdateToRun(TestUser user){
        this.testUser = user;
    }

    @Test
    public void putUserUpdate() throws IOException, JSONException {
        String siteUrl = Config.getConfig().getProtocol() + Config.getConfig().getScheme(); // Урл проверяемого сайта
        long startTime;
        long elapsedTime;
        User originalUser = new GetUsersToRun(testUser).getAnyUser(testUser,siteUrl);
        int careerValue = originalUser.getCareer() + 1;
        if (careerValue == 13){
            careerValue=1;
        }
        int status = originalUser.getUserStatusId()+1;
        if(status == 5){
            status = 1;
        }
        boolean debtor = originalUser.getDebtor();
        debtor = !debtor;

        User modifiedUser = new User(originalUser.getUserId(),originalUser.getSurname(), originalUser.getName(), originalUser.getPatronymic(), originalUser.getUsername(), originalUser.getPassword(), originalUser.getSalt(), originalUser.getCountryId(), originalUser.getLanguageId(), originalUser.getBirthDate(), originalUser.getEmailMain(), originalUser.getEmail2(), originalUser.getEmail3(), originalUser.getPhoneNumberMain(), originalUser.getPhoneNumber2(), originalUser.getPhoneNumber3(), originalUser.getPassportNumber(), originalUser.getPassportSeries(), originalUser.getPassportIssuedBy(), originalUser.getPassportIssueDate(), originalUser.getAdressMain(), originalUser.getAdress2(), originalUser.getAdress3(), originalUser.getGenderId(), status, originalUser.getCreatedDate(), originalUser.getCreatedBy(), originalUser.getChangedBy(), originalUser.getChangedDate(), originalUser.getParentId(), originalUser.getLeaderId(), originalUser.getNetwork(), careerValue, originalUser.getIsApproved(), originalUser.getInviteCode(), debtor);
        String originalJson = "[{\"id\": " + originalUser.getUserId() + ", \"career\": " + originalUser.getCareer() + ", \"status\": " + originalUser.getUserStatusId() +", \"debtor\": "+originalUser.getDebtor()+"}]";
        String modifiedJson = "[{\"id\": " + modifiedUser.getUserId() + ", \"career\": " + careerValue + ", \"status\": " + status +", \"debtor\": "+debtor+"}]";
        System.out.println(modifiedJson);



        // Содзаем URL
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, testUser, url, "PUT", "application/json", "application/json", true);
        OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(modifiedJson);
        out.close();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);
        elapsedTime = System.currentTimeMillis() - startTime;
        // Проверяем GET-запросом, что данные обновились
        User changedUser = new GetUsersToRun(testUser).getUserByParameter("user_id", originalUser.getUserId(), testUser, siteUrl);

        assertTrue("Check modified data saved correctly", modifiedUser.exceptedChangedDate(changedUser));

        httpCon = MakeRequest.getConnection(siteUrl, testUser, url, "PUT", "application/json", "application/json", true);
        out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(originalJson);
        out.close();
        httpCon.getInputStream();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);
        System.out.println();
        // Проверяем GET-запросом, что данные восстановились
        changedUser = new GetUsersToRun(testUser).getUserByParameter("user_id", originalUser.getUserId(), testUser, siteUrl );
        assertTrue("Check modified data returned correctly", originalUser.exceptedChangedDate(changedUser));
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
    }
}
