package ApiTests.Backend;

import ApiTests.ObjectClasses.Config;
import ApiTests.UsedByAll.MakeRequest;
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
public class PutConfigUpdateToRun {
    private TestUser testUser;

    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_PutConfigUpdateToRun(");
    }

    public PutConfigUpdateToRun(TestUser user){
        this.testUser = user;
    }

    @Test
    public void testPutConfigUpdate() throws IOException {
        String siteUrl = UsedByAll.Config.getConfig().getProtocol() + UsedByAll.Config.getConfig().getScheme(); // Урл проверяемого сайта
        long startTime;
        long elapsedTime;
        Config originalOne = new GetConfigToRun(testUser).getAnyConfig(testUser, siteUrl);
        Config modifiedOne = new Config(originalOne.getId(), originalOne.getName() + "name", originalOne.getValue() + " value");
        String originalJson = "[{\"id\":" + originalOne.getId() + ", \"name\":\"" + originalOne.getName() + "\", \"value\": \"" + originalOne.getValue() + "\"}]";
        String modifiedJson = "[{\"id\":" + modifiedOne.getId() + ", \"name\":\"" + modifiedOne.getName() + "\", \"value\": \"" + modifiedOne.getValue() + "\"}]";

        // Содзаем URL
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, testUser, "config/api/values/update/", "PUT", "application/json", "application/json", true);
        OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(modifiedJson);
        out.close();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);
        elapsedTime = System.currentTimeMillis() - startTime;
        // Проверяем GET-запросом, что данные обновились
        Config changedConfig = new GetConfigToRun(testUser).getConfigByParameter("id", originalOne.getId(), testUser, siteUrl);
        assertTrue("Check modified data saved correctly", modifiedOne.equals(changedConfig));

        // Содзаем URL
        httpCon = MakeRequest.getConnection(siteUrl, testUser, "config/api/values/update/", "PUT", "application/json", "application/json", true);
        out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(originalJson);
        out.close();
        httpCon.getInputStream();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);

        // Проверяем GET-запросом, что данные восстановились
        changedConfig = new GetConfigToRun(testUser).getConfigByParameter("id", originalOne.getId(), testUser, siteUrl);
        assertTrue("Check modified data returned correctly", originalOne.equals(changedConfig));
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
    }
}