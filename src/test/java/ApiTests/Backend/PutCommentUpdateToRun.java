package ApiTests.Backend;

import ApiTests.ObjectClasses.Comment;
import ApiTests.UsedByAll.MakeRequest;
import UsedByAll.CsvUsersReader;
import UsedByAll.RandomString;
import UsedByAll.TestUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

/**
 * Created by User on 5/19/2015.
 */
@RunWith(value = Parameterized.class)
public class PutCommentUpdateToRun {
    private TestUser testUser;

    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_BackendAPITest(");
    }

    public PutCommentUpdateToRun(TestUser user) {
        this.testUser = user;
    }

    @Test
    public void testPutCommentUpdate() throws IOException {
        String siteUrl = UsedByAll.Config.getConfig().getProtocol() + UsedByAll.Config.getConfig().getScheme(); // Урл проверяемого сайта
        long startTime;
        long elapsedTime;
        Comment originalOne = new GetCommentToRun(testUser).getAnyComment(siteUrl);
        Comment modifiedOne = new Comment(originalOne.getId(), originalOne.getObjectType(), originalOne.getObjectId(), originalOne.getType(), RandomString.generateString(6), originalOne.getCreatedDate(), originalOne.getUpdatedDate() );

        String originalJson = "[{\"id\":"+originalOne.getId() +", \"object_type\":"+ originalOne.getObjectType() +", \"object_id\":" + originalOne.getObjectId() +", \"type\": "+originalOne.getType()+", \"text\": \""+originalOne.getText()+"\"}]";
        String modifiedJson = "[{\"id\":"+modifiedOne.getId() +", \"object_type\":"+ modifiedOne.getObjectType() +", \"object_id\":" + modifiedOne.getObjectId() +", \"type\": "+modifiedOne.getType()+", \"text\": \""+modifiedOne.getText()+"\"}]";

        // Содзаем URL
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, testUser, "users/api/comment/update/", "PUT", "application/json", "application/json", true);
        OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(modifiedJson);
        out.close();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);
        elapsedTime = System.currentTimeMillis() - startTime;
        // Проверяем GET-запросом, что данные обновились
        Comment changedOne = new GetCommentToRun(testUser).getCommentByParameter("id", originalOne.getId(), siteUrl);
        assertTrue("Check modified data saved correctly", modifiedOne.equalsExceptDates(changedOne));

        // Содзаем URL
        httpCon = MakeRequest.getConnection(siteUrl, testUser, "users/api/comment/update/", "PUT", "application/json", "application/json", true);
        out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(originalJson);
        out.close();
        httpCon.getInputStream();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);

        // Проверяем GET-запросом, что данные восстановились
        changedOne = new GetCommentToRun(testUser).getCommentByParameter("id", originalOne.getId(), siteUrl);
        assertTrue("Check modified data returned correctly", originalOne.equalsExceptDates(changedOne));
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
    }

}
