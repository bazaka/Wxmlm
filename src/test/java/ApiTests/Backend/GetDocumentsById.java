package ApiTests.Backend;

import ApiTests.UsedByAll.MakeRequest;
import ApiTests.UsedByAll.ValidationChecker;
import UsedByAll.RegionMatch;
import UsedByAll.TestUser;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by User on 12/12/2014. Проверяет метод АПИ GET Documents by id
 */
public class GetDocumentsById {
    static final String url = "users/api/documents/get/?id=";
    public boolean testGetDocumentsById(String scheme, TestUser testUser) throws IOException, JSONException {


        int[] ids = GetDocuments.getDocumentsId(scheme, testUser);
        for(int i=0; i<(ids.length -1); i++){
            System.out.println("id: "+ids[i]);
            //создаем соединение

            HttpURLConnection httpCon = MakeRequest.getConnection(scheme, testUser, url+ids[i], "GET");
            InputStream inStrm = httpCon.getInputStream();
            assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);

            InputStreamReader isReader = new InputStreamReader(inStrm);
            BufferedReader br = new BufferedReader(isReader);
            String result = "";
            String line;
            while((line=br.readLine()) !=null){
                result+=line;
            }
            br.close();

            if(RegionMatch.IsStringRegionMatch(result, "<br />")){
                System.out.println("Response contains html in its body. Look: " +result);
                return false;
            }
            JSONObject object = new JSONObject(result);
            if(object.has("[]")) {
                assertTrue("Object have empty array", object.has("[]"));
                System.out.println("Product with ID" + ids[i] + " is not found");
            }else{
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
        return true;
    }
}
