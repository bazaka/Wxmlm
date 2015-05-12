package ApiTests.Backend;

import ApiTests.ObjectClasses.BankDetails;
import ApiTests.UsedByAll.MakeRequest;
import ApiTests.UsedByAll.ValidationChecker;
import UsedByAll.Config;
import UsedByAll.CsvUsersReader;
import UsedByAll.TestUser;
import org.json.JSONArray;
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

import static org.junit.Assert.*;

/**
 * Created by User on 3/16/2015.
 */
@RunWith(value = Parameterized.class)
public class GetBankDetailsToRun {
    private TestUser testUser;
    static final String url = "users/api/user/get-bankdetails?_format=json&id=";


    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_BackendAPITest(");
    }

    public GetBankDetailsToRun(TestUser user){this.testUser = user;}

    @Test
    public void testGetBankdetails() throws IOException, JSONException {
        String siteUrl = Config.getConfig().getProtocol() + Config.getConfig().getScheme();
        long startTime;
        long elapsedTime;
        int[] ids = GetUsersToRun.getUserById(siteUrl, testUser);
        for(int i=0; i<3; i++) {
            //создаем соединение
            startTime = System.currentTimeMillis();
            HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, testUser, url + ids[i], "GET");
            InputStream inStrm = httpCon.getInputStream();
            assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);
            System.out.println(ids[i]);
            elapsedTime = System.currentTimeMillis() - startTime;
            InputStreamReader isReader = new InputStreamReader(inStrm);
            BufferedReader br = new BufferedReader(isReader);
            String result = "";
            String line;
            while ((line = br.readLine()) != null) {
                result += line;
            }
            br.close();
            System.out.println(result);
            JSONObject object = new JSONObject(result);

            assertTrue("Incorrect user_id", ValidationChecker.checkIdValue(object.getInt("user_id")));
            assertEquals("Incorrect count of JSON parameters", object.length(), 3);

           // if(!object.get("epayments").toString().equals("null")){
            if(!object.get("epayments").equals(null)){
                JSONObject epayments = object.getJSONObject("epayments");
                assertTrue("Incorrect epid", ValidationChecker.checkStringOrNull(epayments.get("epid")));
                assertTrue("Incorrect created date", ValidationChecker.checkDateTimeString(epayments.getString("created")));
                assertTrue("Incorrect updated date", ValidationChecker.checkDateTimeString(epayments.getString("updated")));
                assertEquals("Incorrect count of JSON parameters", epayments.length(), 3);
            }
            else
                assertTrue("Epayments not null, but hasn't any elements", ValidationChecker.checkNull(object.get("epayments")));
            if(!object.get("swift").equals(null)){
                JSONObject swift = object.getJSONObject("swift");
                assertTrue("Incorrect name", ValidationChecker.checkStringOrNull(swift.get("name")));
                assertTrue("Incorrect address", ValidationChecker.checkStringOrNull(swift.get("address")));
                assertTrue("Incorrect bank_name", ValidationChecker.checkStringOrNull(swift.get("bank_name")));
                assertTrue("Incorrect bank_address", ValidationChecker.checkStringOrNull(swift.get("bank_address")));
                assertTrue("Incorrect account_iban", ValidationChecker.checkStringOrNull(swift.get("account_iban")));
                //System.out.println(swift.get("swift_code"));
                assertTrue("Incorrect swift code", ValidationChecker.checkSwift(swift.get("swift_code")));
                assertTrue("Incorrect created date", ValidationChecker.checkDateTimeString(swift.getString("created")));
                assertTrue("Incorrect updated date", ValidationChecker.checkDateTimeString(swift.getString("updated")));
                assertEquals("Incorrect count of JSON parameters", swift.length(), 8);
            }
            else
                assertTrue("Swift not null, but hasn't any elements", ValidationChecker.checkNull(object.get("swift")));
            System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
        }

    }
    public BankDetails getBankDetailsById(String parameterName, int parameterValue, TestUser testUser, String siteUrl) throws IOException, JSONException {
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, testUser, url+parameterValue, "GET" );
        InputStream inStrm = httpCon.getInputStream();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);
        InputStreamReader isReader = new InputStreamReader(inStrm);
        BufferedReader br = new BufferedReader(isReader);
        String result = "";
        String line;
        while((line=br.readLine()) !=null) {
            result +=line;

        }
        br.close();

        JSONObject object = new JSONObject(result);


            if (object.getInt(parameterName) == parameterValue) {
                if(object.get("epayments")==null && object.get("swift")==null)
                    return new BankDetails(object.getInt("user_id"), object.get("epayments"), object.get("swift"));
                if(object.get("epayments")!=null && object.get("swift")==null){
                    JSONObject epayments = object.getJSONObject("epayments");
                    return new BankDetails(object.getInt("user_id"), epayments.getString("epid"), epayments.getString("created"), epayments.getString("updated"), object.get("swift"));
                }
                if(object.get("epayments")==null && object.get("swift")!=null){
                    JSONObject swift = object.getJSONObject("swift");
                    return new BankDetails(object.getInt("user_id"), object.get("epayments"), swift.getString("name"), swift.getString("address"), swift.getString("bank_name"), swift.getString("bank_address"), swift.getString("account_iban"), swift.getString("swift_code"), swift.getString("created"), swift.getString("updated"));

                }
                else{
                    JSONObject epayments = object.getJSONObject("epayments");
                    JSONObject swift = object.getJSONObject("swift");
                    return new BankDetails(object.getInt("user_id"), epayments.getString("epid"), epayments.getString("created"), epayments.getString("updated"),swift.getString("name"), swift.getString("address"), swift.getString("bank_name"), swift.getString("bank_address"), swift.getString("account_iban"), swift.getString("swift_code"), swift.getString("created"), swift.getString("updated"));
                }

            }

        return null;
    }
}
