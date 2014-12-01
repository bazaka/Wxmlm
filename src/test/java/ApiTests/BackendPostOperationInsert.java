package ApiTests;

import ApiTests.ObjectClasses.DateForAPI;
import ApiTests.ObjectClasses.MakeRequest;
import ApiTests.ObjectClasses.Operation;
import UsedByAll.TestUser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.*;
import java.net.HttpURLConnection;
import java.util.Calendar;
import static org.junit.Assert.assertTrue;

// * Created for W-xmlm by Fill on 01.12.2014. Insert single operation
public class BackendPostOperationInsert {

    @Before
    public void setUp(String scheme) throws Exception {
        System.out.println("Запускаю селениум для проверки API-метода PUT Operations update на " + scheme);
    }

    @Test
    public boolean testBackendPostOperationInsert(String scheme, TestUser user) throws IOException, JSONException {
        Operation originalOperation = new BackendGetOperations().getAnyOperation(user, scheme);
        Operation newOperation = new Operation(originalOperation.getId(), originalOperation.getTargetAccountId(), originalOperation.getSourceAccountId(), originalOperation.getPurchaseId(), originalOperation.getInitiatorUserId(), DateForAPI.makeDateTimeString(Calendar.getInstance(), 0), originalOperation.getAmount() + 50, originalOperation.status, originalOperation.getType(), !originalOperation.getQuarantine());
        String newJson = "{\"target_account_id\":\"" + newOperation.getTargetAccountId() + "\", \"source_account_id\":" + newOperation.getSourceAccountId() + ", \"purchase_id\":" + newOperation.getPurchaseId() + ", \"initiator_user_id\": " + newOperation.getInitiatorUserId() + ", \"created_date\": \"" + newOperation.getCreatedDate() + "\", \"amount\": \"" + newOperation.getAmount() + "\", \"status\": \"" + newOperation.getStatus() + "\", \"type\": " + newOperation.getType() + ", \"quarantine\": " + newOperation.getQuarantine() + "}";

        // Содзаем URL
        HttpURLConnection httpCon = MakeRequest.getConnection(scheme, user, "money/api/operations/insert/", "POST", "application/json", "application/json", true);
        OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(newJson);
        out.close();
        InputStream inStrm = httpCon.getInputStream();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);
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
        JSONArray reports = response.getJSONArray("reports");
        JSONObject report = reports.getJSONObject(0);
        int newOperationId = report.getInt("id");

        // Проверяем GET-запросом, что данные обновились
        Operation changedOperation = new BackendGetOperations().getOperationByParameter("id", newOperationId, user, scheme);
        assertTrue("Check modified data saved correctly", newOperation.equalsExceptUpdatedDate(changedOperation));
        return true;
    }
    @After
    public void tearDown() throws Exception {}
}
