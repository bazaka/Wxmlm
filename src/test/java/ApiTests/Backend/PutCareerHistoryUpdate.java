package ApiTests.Backend;

import ApiTests.ObjectClasses.CareerHistory;
import ApiTests.UsedByAll.MakeRequest;
import UsedByAll.CsvUsersReader;
import UsedByAll.TestUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

// * Created for W-xmlm by Fill on 03.03.2015.
@RunWith(value = Parameterized.class)
public class PutCareerHistoryUpdate {
    private TestUser testUser;

    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_BackendAPITest(");
    }

    public PutCareerHistoryUpdate(TestUser user) {
        this.testUser = user;
    }

    @Test
    public void testPutIncomeDetailsUpdate() throws Exception {
        String siteUrl = UsedByAll.Config.getConfig().getProtocol() + UsedByAll.Config.getConfig().getScheme(); // Урл проверяемого сайта
        long startTime;
        long elapsedTime;
        CareerHistory originalOne = new GetCareerHistoryToRun(testUser).getAnyCareerHistory(testUser, siteUrl);
        int careerValue = originalOne.getCareer() - 1;
        if(careerValue == 0){
            careerValue = 8;
        }
        CareerHistory modifiedOne = new CareerHistory(originalOne.getHistoryId(), originalOne.getUserId(), careerValue, originalOne.getDate(), originalOne.getMembers(), originalOne.getCreatedDate(), originalOne.getUpdatedDate());
        String membersString = "[";
        for (int i = 0; i < originalOne.getMembers().length; i++) {
            if (i!=0){
                membersString = membersString + ", ";
            }
            membersString = membersString + originalOne.getMembers(i);
        }
        membersString = membersString + "]";
        String originalJson = "[{\"history_id\": " + originalOne.getHistoryId() + ", \"user_id\": " + originalOne.getUserId() + ", \"career\": " + originalOne.getCareer() + ", \"date\": \"" + originalOne.getDate() + "\", \"members\": " + membersString + "}]";
        String modifiedJson = "[{\"history_id\": " + modifiedOne.getHistoryId() + ", \"user_id\": " + modifiedOne.getUserId() + ", \"career\": " + modifiedOne.getCareer() + ", \"date\": \"" + modifiedOne.getDate() + "\", \"members\": " + membersString + "}]";

        // Содзаем URL
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, testUser, "money/api/income-details/update/", "PUT", "application/json", "application/json", true);
        OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(modifiedJson);
        out.close();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);
        elapsedTime = System.currentTimeMillis() - startTime;
        // Проверяем GET-запросом, что данные обновились
        CareerHistory changedOne = new GetCareerHistoryToRun(testUser).getCareerHistoryByParameter("history_id", originalOne.getHistoryId(), testUser, siteUrl);
/*        assertTrue("Check modified data saved correctly", modifiedOne.equalsExceptUpdatedDate(changedOne, true));

        // Содзаем URL
        httpCon = MakeRequest.getConnection(siteUrl, testUser, "money/api/income-details/update/", "PUT", "application/json", "application/json", true);
        out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(originalJson);
        out.close();
        httpCon.getInputStream();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);

        // Проверяем GET-запросом, что данные восстановились
        changedOne = new GetIncomeDetailsToRun(testUser).getIncomeDetailsByParameter("id", originalOne.getId(), testUser, siteUrl);
        assertTrue("Check modified data returned correctly", originalOne.equalsExceptUpdatedDate(changedOne, true));
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);*/
    }
}
