package ApiTests.Backend;

import ApiTests.ObjectClasses.IncomeDetails;
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

// * Created for W-xmlm by Fill on 24.02.2015.
@RunWith(value = Parameterized.class)
public class PutIncomeDetailsUpdateToRun {
    private TestUser testUser;

    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_BackendAPITest(");
    }

    public PutIncomeDetailsUpdateToRun(TestUser user) {
        this.testUser = user;
    }

    @Test
    public void testPutIncomeDetailsUpdate() throws Exception {
        String siteUrl = UsedByAll.Config.getConfig().getProtocol() + UsedByAll.Config.getConfig().getScheme(); // Урл проверяемого сайта
        long startTime;
        long elapsedTime;
        IncomeDetails originalOne = new GetIncomeDetailsToRun(testUser).getAnyIncomeDetails(siteUrl);
        IncomeDetails modifiedOne = new IncomeDetails(originalOne.getId(), originalOne.getOperationId(), originalOne.getUserId(), originalOne.getPurchaseIds(), originalOne.getTimeOnline() + 500, originalOne.getTimeOnlinePaid() + 200, originalOne.getCreatedDate(), originalOne.getUpdatedDate());
        String purchaseIdsString = "[";
        for (int i = 0; i < originalOne.getPurchaseIds().length; i++) {
            if (i!=0){
                purchaseIdsString = purchaseIdsString + ", ";
            }
            purchaseIdsString = purchaseIdsString + originalOne.getPurchaseIds(i);
        }
        purchaseIdsString = purchaseIdsString + "]";
        String originalJson = "[{\"id\": " + originalOne.getId() + ", \"time_online\": " + originalOne.getTimeOnline() + ", \"time_online_paid\": " + originalOne.getTimeOnlinePaid() + ", \"operation_id\": " + originalOne.getOperationId() + ", \"purchase_ids\": " + purchaseIdsString + ", \"user_id\": " + originalOne.getUserId() + "}]";
        String modifiedJson = "[{\"id\": " + modifiedOne.getId() + ", \"time_online\": " + modifiedOne.getTimeOnline() + ", \"time_online_paid\": " + modifiedOne.getTimeOnlinePaid() + ", \"operation_id\": " + modifiedOne.getOperationId() + ", \"purchase_ids\": " + purchaseIdsString + ", \"user_id\": " + modifiedOne.getUserId() + "}]";

        // Содзаем URL
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, testUser, "money/api/income-details/update/", "PUT", "application/json", "application/json", true);
        OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(modifiedJson);
        out.close();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);
        elapsedTime = System.currentTimeMillis() - startTime;
        // Проверяем GET-запросом, что данные обновились
        IncomeDetails changedOne = new GetIncomeDetailsToRun(testUser).getIncomeDetailsByParameter("id", originalOne.getId(), siteUrl);
        assertTrue("Check modified data saved correctly", modifiedOne.equalsExceptUpdatedDate(changedOne, true));

        // Содзаем URL
        httpCon = MakeRequest.getConnection(siteUrl, testUser, "money/api/income-details/update/", "PUT", "application/json", "application/json", true);
        out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(originalJson);
        out.close();
        httpCon.getInputStream();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);

        // Проверяем GET-запросом, что данные восстановились
        changedOne = new GetIncomeDetailsToRun(testUser).getIncomeDetailsByParameter("id", originalOne.getId(), siteUrl);
        assertTrue("Check modified data returned correctly", originalOne.equalsExceptUpdatedDate(changedOne, true));
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
    }
}
