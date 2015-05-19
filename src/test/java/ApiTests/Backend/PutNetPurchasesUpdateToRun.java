package ApiTests.Backend;

// * Created for W-xmlm by Fill on 18.05.2015.

import ApiTests.ObjectClasses.NetPurchase;
import ApiTests.UsedByAll.MakeRequest;
import UsedByAll.Config;
import UsedByAll.CsvUsersReader;
import UsedByAll.TestUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(value = Parameterized.class)
public class PutNetPurchasesUpdateToRun {
    private TestUser testUser;

    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_BackendAPITest(");
    }

    public PutNetPurchasesUpdateToRun(TestUser user){
        this.testUser = user;
    }

    @Test
    public void testPutNetPurchasesUpdate() throws IOException {
        String siteUrl = Config.getConfig().getProtocol() + Config.getConfig().getScheme(); // Урл проверяемого сайта
        long startTime;
        long elapsedTime;
        NetPurchase originalOne = new GetNetPurchasesToRun(testUser).getAnyNetPurchase(testUser, siteUrl);
        int newUsersNet;
        if (originalOne.getUsersNet() == 1) {
            newUsersNet = 0;
        }
        else newUsersNet = 1;
        int newInTurnover;
        if (originalOne.getInTurnover() == 1) {
            newInTurnover= 0;
        }
        else newInTurnover = 1;
        NetPurchase modifiedOne = new NetPurchase(originalOne.getId(), originalOne.getUserId(), originalOne.getPurchaseId(), newUsersNet, newInTurnover, originalOne.getCreatedDate());
        String originalJson = "[{\"id\":" + originalOne.getId() + ", \"user_id\":" + originalOne.getUserId() + ", \"purchase_id\":" + originalOne.getPurchaseId() + ", \"users_net\":" + originalOne.getUsersNet() + ", \"in_turnover\": " + originalOne.getInTurnover() + "}]";
        String modifiedJson = "[{\"id\":" + modifiedOne.getId() + ", \"user_id\":" + modifiedOne.getUserId() + ", \"purchase_id\":" + modifiedOne.getPurchaseId() + ", \"users_net\":" + modifiedOne.getUsersNet() + ", \"in_turnover\": " + modifiedOne.getInTurnover() + "}]";

        // Содзаем URL
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, testUser, "users/api/network/purchase/update/", "PUT", "application/json", "application/json", true);
        OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(modifiedJson);
        out.close();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);
        elapsedTime = System.currentTimeMillis() - startTime;
        // Проверяем GET-запросом, что данные обновились
        NetPurchase changedOne = new GetNetPurchasesToRun(testUser).getNetPurchaseByParameter("id", originalOne.getId(), testUser, siteUrl);
        assertTrue("Check modified data saved correctly", modifiedOne.equalsExceptUpdatedDate(changedOne, true));

        // Содзаем URL
        httpCon = MakeRequest.getConnection(siteUrl, testUser, "users/api/network/purchase/update/", "PUT", "application/json", "application/json", true);
        out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(originalJson);
        out.close();
        httpCon.getInputStream();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);
        // Проверяем GET-запросом, что данные восстановились
        changedOne = new GetNetPurchasesToRun(testUser).getNetPurchaseByParameter("id", originalOne.getId(), testUser, siteUrl);
        assertTrue("Check modified data returned correctly", originalOne.equalsExceptUpdatedDate(changedOne, true));
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
    }

}
