package ApiTests.Backend;

import ApiTests.ObjectClasses.Card;
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

/**
 * Created by User on 2/26/2015.
 */
@RunWith(value = Parameterized.class)
public class PutCardsUpdateToRun {
    private TestUser testUser;
    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_BackendAPITest(");
    }

    public PutCardsUpdateToRun(TestUser user){this.testUser = user;}

    @Test
    public void testPutCardsUpdate() throws IOException {
        String siteUrl = Config.getConfig().getProtocol() + Config.getConfig().getScheme(); // Урл проверяемого сайта
        long startTime;
        long elapsedTime;
        Card originalCard = new GetCardsToRun(testUser).getAnyCard(testUser, siteUrl);
        Card modifiedCard = new Card(originalCard.getCardId(), originalCard.getUserId(), originalCard.getCardNumber(), originalCard.getExpDate(), "test_Card_holder_autotest", originalCard.getEnabled());
        String originalJson = "[{\"card_id\":" + originalCard.getCardId() + ", \"user_id\":" + originalCard.getUserId() + ", \"number\":\"" + originalCard.getCardNumber() + "\", \"cardholder\":\"" + originalCard.getCardHolder() + "\", \"expiration\": \"" + originalCard.getExpDate() + "\", \"enabled\": " + originalCard.getEnabled() + "}]";
        String modifiedJson = "[{\"card_id\":" + modifiedCard.getCardId() + ", \"user_id\":" + modifiedCard.getUserId() + ", \"number\":\"" + modifiedCard.getCardNumber() + "\", \"cardholder\":\"" + modifiedCard.getCardHolder() + "\", \"expiration\": \"" + modifiedCard.getExpDate() + "\", \"enabled\": " + modifiedCard.getEnabled() + "}]";
        /*System.out.println("original:" +originalJson);
        System.out.println("modified:" +modifiedJson);*/



        // Содзаем URL
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, testUser, "users/api/cards/update/", "PUT", "application/json", "application/json", true );
        OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(modifiedJson);
        out.close();
    /* System.out.println("code: " + httpCon.getResponseCode());
        System.out.println("message: "+httpCon.getResponseMessage());*/


        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);
        elapsedTime = System.currentTimeMillis() - startTime;
        // Проверяем GET-запросом, что данные обновились
        Card changedCard = new GetCardsToRun(testUser).getCardByParameter("card_id", originalCard.getCardId(), testUser,siteUrl);
        assertTrue("Check modified data saved correctly", modifiedCard.equalsExceptDate(changedCard));

        // Содзаем URL
     //  modifiedJson = originalJson;
        httpCon = MakeRequest.getConnection(siteUrl, testUser, "users/api/cards/update/", "PUT", "application/json", "application/json", true  );
        out = new OutputStreamWriter(httpCon.getOutputStream());

        out.write(originalJson);
        out.close();
        httpCon.getInputStream();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);
        // Проверяем GET-запросом, что данные восстановились
        changedCard = new GetCardsToRun(testUser).getCardByParameter("card_id", originalCard.getCardId(), testUser, siteUrl);
        assertTrue("Check modified data returned correctly", originalCard.equalsExceptDate(changedCard));
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);




    }
}
