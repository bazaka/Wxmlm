package ApiTests.Backend;

import ApiTests.ObjectClasses.MerchantRequest;
import ApiTests.UsedByAll.MakeRequest;
import UsedByAll.CsvUsersReader;
import UsedByAll.TestUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.Collection;

import static org.junit.Assert.*;

// * Created for W-xmlm by Fill on 16.03.2015.
@RunWith(value = Parameterized.class)
public class PutMerchantRequestsUpdateToRun {
    private TestUser testUser;

    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_BackendAPITest(");
    }

    public PutMerchantRequestsUpdateToRun(TestUser user){
        this.testUser = user;
    }

    @Test
    public void testPutMerchantRequestsUpdate() throws Exception {
        String siteUrl = UsedByAll.Config.getConfig().getProtocol() + UsedByAll.Config.getConfig().getScheme(); // Урл проверяемого сайта
        long startTime;
        long elapsedTime;
        MerchantRequest originalOne = new GetMerchantRequestsToRun(testUser).getAnyMerchantRequest(testUser, siteUrl);
        int statusValue = originalOne.getStatus() - 1;
        String userCommentValue = "\"" + originalOne.getUserComment().toString() + "\"";
        if(statusValue == 0){
            statusValue = 3;
        }
        if(userCommentValue.equals("\"null\"")){
            userCommentValue = "\"\"";
        }
        String adminCommentValue = userCommentValue;
        MerchantRequest modifiedOne = new MerchantRequest(originalOne.getRequestId(), originalOne.getUserId(), originalOne.getFiles(), originalOne.getUserComment(), statusValue, originalOne.getCreatedDate(), originalOne.getUpdatedDate());
        String originalJson = "[{\"request_id\": " + originalOne.getRequestId() + ", \"user_id\": " + originalOne.getUserId() + ", \"admin_comment\": " + adminCommentValue + ", \"status\": " + originalOne.getStatus() + "}]";
        String modifiedJson = "[{\"request_id\": " + originalOne.getRequestId() + ", \"user_id\": " + originalOne.getUserId() + ", \"admin_comment\": " + adminCommentValue + ", \"status\": " + statusValue + "}]";
        // Содзаем URL
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, testUser, "money/api/merchant-requests/update/", "PUT", "application/json", "application/json", true);
        OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(modifiedJson);
        out.close();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);
        elapsedTime = System.currentTimeMillis() - startTime;
        // Проверяем GET-запросом, что данные обновились
        MerchantRequest changedOne = new GetMerchantRequestsToRun(testUser).getMerchantRequestByParameter("request_id", originalOne.getRequestId(), testUser, siteUrl);
        assertTrue("Check modified data saved correctly", modifiedOne.equalsExceptUpdatedDate(changedOne, true));

        // Содзаем URL
        httpCon = MakeRequest.getConnection(siteUrl, testUser, "money/api/merchant-requests/update/", "PUT", "application/json", "application/json", true);
        out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(originalJson);
        out.close();
        httpCon.getInputStream();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);

        // Проверяем GET-запросом, что данные восстановились
        changedOne = new GetMerchantRequestsToRun(testUser).getMerchantRequestByParameter("request_id", originalOne.getRequestId(), testUser, siteUrl);
        assertTrue("Check modified data returned correctly", originalOne.equalsExceptUpdatedDate(changedOne, true));
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
    }

}
