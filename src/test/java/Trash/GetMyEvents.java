package Trash;

import ApiTests.Desktop.GetTokenToRun;
import ApiTests.UsedByAll.MakeRequest;
import ApiTests.UsedByAll.ValidationChecker;
import UsedByAll.CsvUsersReader;
import UsedByAll.TestUser;


import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by User on 3/17/2015.
 */
@RunWith(value = Parameterized.class)
public class GetMyEvents {
    private TestUser testUser;
    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_DesktopAPITest(");
    }

    public GetMyEvents(TestUser user){this.testUser = user;}

    @Test
    public void getEvents() throws Exception {
        String siteUrl = UsedByAll.Config.getConfig().getProtocol() + UsedByAll.Config.getConfig().getScheme(); // Урл проверяемого сайта
        long startTime;
        long elapsedTime;

        String token = new GetTokenToRun(testUser).getToken();
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, "users/api/desktop/get-events/?_format=json&token=" + token, "GET");
        InputStream inStrm = httpCon.getInputStream();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);
        elapsedTime = System.currentTimeMillis() - startTime;
        InputStreamReader isReader = new InputStreamReader(inStrm);
        BufferedReader br = new BufferedReader(isReader);
        String result = "";
        String line;
        while ((line = br.readLine()) != null) {
            result += line;
        }
        br.close();



       JSONArray jsonArr = new JSONArray(result);
        if(jsonArr.length()==0) {
            System.out.println("Object has empty array");
            System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
        }else{
            for(int j=0; j<jsonArr.length(); j++){
                JSONObject object = jsonArr.getJSONObject(j);
                JSONArray events = object.getJSONArray("object");
                for(int i=0; i<events.length(); i++) {
                    assertTrue("Incorrect event name", ValidationChecker.checkEventName(events.get(i)));
                    System.out.println(events.get(i).toString());}
                assertEquals("Incorrect count of JSON object", object.length(), 1);
                System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);


           }
       }


    }
}
