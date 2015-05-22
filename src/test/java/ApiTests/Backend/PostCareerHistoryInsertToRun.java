package ApiTests.Backend;

import ApiTests.ObjectClasses.CareerHistory;
import ApiTests.UsedByAll.MakeRequest;
import UsedByAll.Config;
import UsedByAll.CsvUsersReader;
import UsedByAll.TestUser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

// * Created for W-xmlm by Fill on 04.03.2015.
@RunWith(value = Parameterized.class)
public class PostCareerHistoryInsertToRun {
    private TestUser testUser;

    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_BackendAPITest(");
    }

    public PostCareerHistoryInsertToRun(TestUser user) {
        this.testUser = user;
    }

    @Test
    public void testPostCareerHistoryInsert() throws IOException, JSONException {
        String siteUrl = Config.getConfig().getProtocol() + Config.getConfig().getScheme(); // Урл проверяемого сайта
        long startTime;
        long elapsedTime;
        CareerHistory originalOne = new GetCareerHistoryToRun(testUser).getAnyCareerHistory(testUser, siteUrl);
        int careerValue = originalOne.getCareer() - 1;
        if(careerValue == 0){
            careerValue = 8;
        }
        CareerHistory newOne = new CareerHistory(originalOne.getHistoryId(), originalOne.getUserId(), careerValue, originalOne.getDate(), originalOne.getMembers(), originalOne.getCreatedDate(), originalOne.getUpdatedDate());
        String membersString = "[";
        for (int i = 0; i < originalOne.getMembers().length; i++) {
            if (i!=0){
                membersString = membersString + ", ";
            }
            membersString = membersString + originalOne.getMembers(i);
        }
        membersString = membersString + "]";
        String newJson = "{\"user_id\": " + newOne.getUserId() + ", \"career\": " + newOne.getCareer() + ", \"date\": \"" + newOne.getDate() + "\", \"members\": " + membersString + "}";
        System.out.println(newJson);

        // Содзаем URL
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, testUser, "career/api/history/insert/", "POST", "application/json", "application/json", true);
        OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(newJson);
        out.close();
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

        // Берем из респонса id новой операции
        JSONObject response = new JSONObject(result);
        JSONArray reports = response.getJSONArray("reports");
        JSONObject report = reports.getJSONObject(0);
        newOne.setHistoryId(report.getInt("id"));

        // Проверяем GET-запросом, что данные обновились
        CareerHistory changedOne = new GetCareerHistoryToRun(testUser).getCareerHistoryByParameter("history_id", newOne.getHistoryId(), testUser, siteUrl);
        assertTrue("Check modified data saved correctly", newOne.equalsExceptUpdatedDate(changedOne, false));
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
    }
}
