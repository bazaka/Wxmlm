package ApiTests.Desktop;

import ApiTests.UsedByAll.MakeRequest;
import UsedByAll.CsvUsersReader;
import UsedByAll.TestUser;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.net.HttpURLConnection;
import java.util.Collection;

/**
 * Created by User on 3/16/2015.
 */
@RunWith(value = Parameterized.class)
public class GetNewToken {
    private TestUser testUser;

    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_DesktopAPITest(");
    }

    public GetNewToken()throws  Exception{
        String siteUrl = UsedByAll.Config.getConfig().getProtocol() + UsedByAll.Config.getConfig().getScheme(); // Урл проверяемого сайта
        long startTime;
        long elapsedTime;
        startTime = System.currentTimeMillis();
        String token = GetTokenToRun.testData();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, testUser, "users/api/desktop/get-new-token/?_format=json&token="+token, "GET");

    }
}
