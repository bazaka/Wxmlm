package ApiTests.Backend;

import ApiTests.ObjectClasses.Config;
import ApiTests.UsedByAll.MakeRequest;
import UsedByAll.TestUser;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import static org.junit.Assert.assertTrue;

public class PutConfigUpdate {
    public boolean testPutConfigUpdate(String scheme, TestUser user) throws IOException {
        long startTime;
        long elapsedTime;
        Config originalOne = new GetConfig().getAnyConfig(user, scheme);
        Config modifiedOne = new Config(originalOne.getId(), originalOne.getName() + "name", originalOne.getValue() + " value");
        String originalJson = "[{\"id\":" + originalOne.getId() + ", \"name\":\"" + originalOne.getName() + "\", \"value\": \"" + originalOne.getValue() + "\"}]";
        String modifiedJson = "[{\"id\":" + modifiedOne.getId() + ", \"name\":\"" + modifiedOne.getName() + "\", \"value\": \"" + modifiedOne.getValue() + "\"}]";

        // Содзаем URL
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(scheme, user, "config/api/values/update/", "PUT", "application/json", "application/json", true);
        OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(modifiedJson);
        out.close();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);
        elapsedTime = System.currentTimeMillis() - startTime;
        // Проверяем GET-запросом, что данные обновились
        Config changedConfig = new GetConfig().getConfigByParameter("id", originalOne.getId(), user, scheme);
        assertTrue("Check modified data saved correctly", modifiedOne.equals(changedConfig));

        // Содзаем URL
        httpCon = MakeRequest.getConnection(scheme, user, "config/api/values/update/", "PUT", "application/json", "application/json", true);
        out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(originalJson);
        out.close();
        httpCon.getInputStream();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);

        // Проверяем GET-запросом, что данные восстановились
        changedConfig = new GetConfig().getConfigByParameter("id", originalOne.getId(), user, scheme);
        assertTrue("Check modified data returned correctly", originalOne.equals(changedConfig));
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
        return true;
    }
}