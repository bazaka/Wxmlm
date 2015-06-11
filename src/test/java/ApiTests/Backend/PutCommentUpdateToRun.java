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
        Comment modifiedOne = new Comment(originalOne.getId(), originalOne.getTypeId(), RandomString.generateString(6), originalOne.getUserId(), originalOne.getPurchaseId(), originalOne.getOperationId(), originalOne.getAccountId(), originalOne.getRechargeRequestId(), originalOne.getWithdrawRequestId(), originalOne.getVerificationRequestId(), originalOne.getFrontEventTypeId(), originalOne.getBackEventTypeId(), originalOne.getCreatedDate(), originalOne.getUpdatedDate());

        String originalJson = "[{\"id\":"+originalOne.getId() + ", \"type_id\": " + originalOne.getTypeId() + ", \"text\": \"" + originalOne.getText() + "\", \"user_id\": " + originalOne.getUserId() + ", \"purchase_id\": " + originalOne.getPurchaseId() + ", \"operation_id\": " + originalOne.getOperationId() + ", \"account_id\": " + originalOne.getAccountId() + ", \"recharge_request_id\": " + originalOne.getRechargeRequestId() + ", \"withdraw_request_id\": " + originalOne.getWithdrawRequestId() + ", \"verification_request_id\": " + originalOne.getVerificationRequestId() + ", \"back_event_type_id\": " + originalOne.getBackEventTypeId() + "}]";
        String modifiedJson = "[{\"id\":"+modifiedOne.getId() + ", \"type_id\": " + modifiedOne.getTypeId() + ", \"text\": \"" + modifiedOne.getText() + "\", \"user_id\": " + modifiedOne.getUserId() + ", \"purchase_id\": " + modifiedOne.getPurchaseId() + ", \"operation_id\": " + modifiedOne.getOperationId() + ", \"account_id\": " + modifiedOne.getAccountId() + ", \"recharge_request_id\": " + modifiedOne.getRechargeRequestId() + ", \"withdraw_request_id\": " + modifiedOne.getWithdrawRequestId() + ", \"verification_request_id\": " + modifiedOne.getVerificationRequestId() + ", \"back_event_type_id\": " + modifiedOne.getBackEventTypeId() + "}]";

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
        assertTrue("Check modified data saved correctly", modifiedOne.equalsExceptDates(changedOne, true));

        // Содзаем URL
        httpCon = MakeRequest.getConnection(siteUrl, testUser, "users/api/comment/update/", "PUT", "application/json", "application/json", true);
        out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(originalJson);
        out.close();
        httpCon.getInputStream();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);

        // Проверяем GET-запросом, что данные восстановились
        changedOne = new GetCommentToRun(testUser).getCommentByParameter("id", originalOne.getId(), siteUrl);
        assertTrue("Check modified data returned correctly", originalOne.equalsExceptDates(changedOne, true));
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
    }

}
