package ApiTests.Backend;

import ApiTests.UsedByAll.MakeRequest;
import ApiTests.UsedByAll.ValidationChecker;
import UsedByAll.Config;
import UsedByAll.CsvUsersReader;
import UsedByAll.RegionMatch;
import UsedByAll.TestUser;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Collection;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by User on 12/12/2014. Проверяет метод АПИ GET Documents by id
 */
@RunWith(value = Parameterized.class)
public class GetDocumentsByIdToRun {
    private TestUser testUser;
    static final String url = "users/api/documents/get/?id=";

    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_BackendAPITest(");
    }

    public GetDocumentsByIdToRun(TestUser user){
        this.testUser = user;
    }

    @Test
    public void testGetDocumentsById() throws IOException, JSONException {
        String siteUrl = Config.getConfig().getProtocol() + Config.getConfig().getScheme();
        int[] ids = GetDocumentsToRun.getDocumentsId(siteUrl, testUser);
        for(int i=0; i<(ids.length -1); i++){
            System.out.println("id: "+ids[i]);
            //создаем соединение

            HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, testUser, url+ids[i], "GET");
            assertTrue("Check response code is 200", (httpCon.getResponseCode() == 200) || (httpCon.getResponseCode() == 404));
            InputStream inStrm = httpCon.getInputStream();
            InputStreamReader isReader = new InputStreamReader(inStrm);
            BufferedReader br = new BufferedReader(isReader);
            String result = "";
            String line;
            while((line=br.readLine()) !=null){
                result+=line;
            }
            br.close();
            assertFalse("Response contains html in its body", RegionMatch.IsStringRegionMatch(result, "<br />"));
            JSONObject object = new JSONObject(result);
            if(object.has("[]")) {
                assertTrue("Object have empty array", object.has("[]"));
                System.out.println("Product with ID" + ids[i] + " is not found");
            }else
            {
                assertTrue("Incorrect id", ValidationChecker.checkIdValue(object.getInt("id")));
                assertTrue("Incorrect user_id", ValidationChecker.checkIdValue(object.getInt("user_id")));
                assertTrue("Incorrect file_name", ValidationChecker.checkFileName(object.getString("file_name")));
                assertTrue("Incorrect path", ValidationChecker.checkStringNotNull(object.getString("path")));
                assertTrue("Incorrect created_date", ValidationChecker.checkDateTimeString(object.getString("created_date")));
                assertTrue("Incorrect status", ValidationChecker.checkBooleanValue(object.getBoolean("status")));
                assertTrue("Incorrect source", ValidationChecker.checkStringNotNull(object.get("source").toString()));
                assertTrue("Incorrect md5", ValidationChecker.checkStringNotNull(object.getString("md5")));
                assertEquals("Incorrect count of object attributes", object.length(), 8);
            }
        }
    }
}
