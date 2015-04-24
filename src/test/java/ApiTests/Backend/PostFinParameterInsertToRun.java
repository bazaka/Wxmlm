package ApiTests.Backend;

import ApiTests.ObjectClasses.FinParameter;
import ApiTests.UsedByAll.MakeRequest;
import UsedByAll.Config;
import UsedByAll.CsvUsersReader;
import UsedByAll.TestUser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

/**
 * Created by User on 4/8/2015.
 */
@RunWith(value = Parameterized.class)
public class PostFinParameterInsertToRun {
    private TestUser testUser;

    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_BackendAPITest(");
    }

    public PostFinParameterInsertToRun(TestUser user) {
        this.testUser = user;
    }

    @Test
    public void testPostFinParameterInsert() throws IOException, JSONException {
        String siteUrl = Config.getConfig().getProtocol() + Config.getConfig().getScheme(); // Урл проверяемого сайта
        long startTime;
        long elapsedTime;
        FinParameter originalOne = new GetFinParameterToRun(testUser).getAnyFinParameter(testUser, siteUrl);
        FinParameter newOne = new FinParameter(originalOne.getId(), originalOne.getOperationTypeId(), originalOne.getMerchantId(), originalOne.getPaymentMerchantId(), originalOne.getParameterId(), originalOne.getValue(), originalOne.getDateStart(), originalOne.getDateEnd(), false);



        String newJson = "{\"operation_type_id\":"+newOne.getOperationTypeId() +", \"merchant_id\":" +newOne.getMerchantId()+", \"payment_merchant_id\":"+newOne.getPaymentMerchantId()+", \"parameter_id\":"+newOne.getParameterId()+", \"value\":\"" + newOne.getValue()+"\", \"date_start\":\""+newOne.getDateStart()+"\", \"date_end\":\""+newOne.getDateEnd()+"\", \"enabled\":"+newOne.getEnabled()+"}";

        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, testUser, "money/api/parameter/insert/", "POST", "application/json", "application/json", true);
        OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(newJson);
        out.close();
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

        // Берем из респонса id новой операции
        JSONObject response = new JSONObject(result);
        JSONArray report = response.getJSONArray("reports");
        String parseResponse = report.getString(0).split("ID ")[1];
        newOne.setId(Integer.parseInt(parseResponse.split(" was")[0]));

        // Проверяем GET-запросом, что данные обновились
        FinParameter changedOne = new GetFinParameterToRun(testUser).getFinParameterByParameter("id", newOne.getId(), testUser, siteUrl);
        assertTrue("Check modified data saved correctly", newOne.equalsFin(changedOne));
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
    }
}
