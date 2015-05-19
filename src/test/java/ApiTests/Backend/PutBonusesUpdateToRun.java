package ApiTests.Backend;

import ApiTests.ObjectClasses.Bonus;
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

// * Created for W-xmlm by Fill on 19.03.2015.
@RunWith(value = Parameterized.class)
public class PutBonusesUpdateToRun {
    private TestUser testUser;

    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_BackendAPITest(");
    }

    public PutBonusesUpdateToRun(TestUser user){
        this.testUser = user;
    }

    @Test
    public void testPutBonusesUpdate() throws Exception {
        String siteUrl = UsedByAll.Config.getConfig().getProtocol() + UsedByAll.Config.getConfig().getScheme(); // Урл проверяемого сайта
        long startTime;
        long elapsedTime;
        Bonus originalOne = new GetBonusesToRun(testUser).getAnyBonus(siteUrl);
        int typeValue = originalOne.getType() - 1;
        if(typeValue == -1){
            typeValue = 7;
        }
        Bonus modifiedOne = new Bonus(originalOne.getId(), originalOne.getUserId(), originalOne.getPartnerId(), originalOne.getOperationId(), originalOne.getPurchaseId(), typeValue, originalOne.getPercent() + 1, originalOne.getCreatedDate(), originalOne.getUpdatedDate());
        String originalJson = "[{\"id\": " + originalOne.getId() + ", \"user_id\": " + originalOne.getUserId() + ", \"partner_id\": " + originalOne.getPartnerId() + ", \"operation_id\": " + originalOne.getOperationId() + ", \"purchase_id\": " + originalOne.getPurchaseId() + ", \"type\": " + originalOne.getType() + ", \"percent\": " + originalOne.getPercent() + "}]";
        String modifiedJson = "[{\"id\": " + modifiedOne.getId() + ", \"user_id\": " + modifiedOne.getUserId() + ", \"partner_id\": " + modifiedOne.getPartnerId() + ", \"operation_id\": " + modifiedOne.getOperationId() + ", \"purchase_id\": " + modifiedOne.getPurchaseId() + ", \"type\": " + modifiedOne.getType() + ", \"percent\": " + modifiedOne.getPercent() + "}]";

        System.out.println(originalJson);
        System.out.println(modifiedJson);
        // Содзаем URL
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, testUser, "money/api/bonuses/update/", "PUT", "application/json", "application/json", true);
        OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(modifiedJson);
        out.close();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);
        elapsedTime = System.currentTimeMillis() - startTime;
        // Проверяем GET-запросом, что данные обновились
        Bonus changedOne = new GetBonusesToRun(testUser).getBonusByParameter("id", originalOne.getId(), siteUrl);
        assertTrue("Check modified data saved correctly1", modifiedOne.equalsExceptUpdatedDate(changedOne, true));

        // Содзаем URL
        httpCon = MakeRequest.getConnection(siteUrl, testUser, "money/api/bonuses/update/", "PUT", "application/json", "application/json", true);
        out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(originalJson);
        out.close();
        httpCon.getInputStream();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);

        // Проверяем GET-запросом, что данные восстановились
        changedOne = new GetBonusesToRun(testUser).getBonusByParameter("id", originalOne.getId(), siteUrl);
        assertTrue("Check modified data returned correctly2", originalOne.equalsExceptUpdatedDate(changedOne, true));
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
    }
}
