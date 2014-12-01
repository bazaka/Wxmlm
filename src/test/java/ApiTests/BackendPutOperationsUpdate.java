package ApiTests;

import ApiTests.ObjectClasses.MakeRequest;
import ApiTests.ObjectClasses.Operation;
import UsedByAll.TestUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import static org.junit.Assert.assertTrue;

public class BackendPutOperationsUpdate {
    @Before
    public void setUp(String scheme) throws Exception {
        System.out.println("Запускаю селениум для проверки API-метода PUT Operations update на " + scheme);
    }

    @Test
    public boolean testBackendPutOperationsUpdate(String scheme, TestUser user) throws IOException {
        Operation originalOperation = new BackendGetOperations().getAnyOperation(user, scheme);
        Operation modifiedOperation = new Operation(originalOperation.getId(), originalOperation.getTargetAccountId(), originalOperation.getSourceAccountId(), originalOperation.getPurchaseId(), originalOperation.getInitiatorUserId(), originalOperation.getCreatedDate(), originalOperation.getAmount() + 50, 1, 9, !originalOperation.getQuarantine());
        String originalJson = "[{\"id\":" + originalOperation.getId() + ", \"target_account_id\":\"" + originalOperation.getTargetAccountId() + "\", \"source_account_id\":" + originalOperation.getSourceAccountId() + ", \"purchase_id\":" + originalOperation.getPurchaseId() + ", \"initiator_user_id\": " + originalOperation.getInitiatorUserId() + ", \"created_date\": \"" + originalOperation.getCreatedDate() + "\", \"amount\": \"" + originalOperation.getAmount() + "\", \"status\": \"" + originalOperation.getStatus() + "\", \"type\": " + originalOperation.getType() + ", \"quarantine\": " + originalOperation.getQuarantine() + "}]";
        String modifiedJson = "[{\"id\":" + modifiedOperation.getId() + ", \"target_account_id\":\"" + modifiedOperation.getTargetAccountId() + "\", \"source_account_id\":" + modifiedOperation.getSourceAccountId() + ", \"purchase_id\":" + modifiedOperation.getPurchaseId() + ", \"initiator_user_id\": " + modifiedOperation.getInitiatorUserId() + ", \"created_date\": \"" + modifiedOperation.getCreatedDate() + "\", \"amount\": \"" + modifiedOperation.getAmount() + "\", \"status\": \"" + modifiedOperation.getStatus() + "\", \"type\": " + modifiedOperation.getType() + ", \"quarantine\": " + modifiedOperation.getQuarantine() + "}]";

        // Содзаем URL
        HttpURLConnection httpCon = MakeRequest.getConnection(scheme, user, "money/api/operations/update/", "PUT", "application/json", "application/json", true);
        OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(modifiedJson);
        out.close();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);
        // Проверяем GET-запросом, что данные обновились
        Operation changedOperation = new BackendGetOperations().getOperationByParameter("id", originalOperation.getId(), user, scheme);
        assertTrue("Check modified data saved correctly", modifiedOperation.equalsExceptUpdatedDate(changedOperation));

        // Содзаем URL
        httpCon = MakeRequest.getConnection(scheme, user, "money/api/operations/update/", "PUT", "application/json", "application/json", true);
        out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(originalJson);
        out.close();
        httpCon.getInputStream();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);

        // Проверяем GET-запросом, что данные восстановились
        changedOperation = new BackendGetOperations().getOperationByParameter("id", originalOperation.getId(), user, scheme);
        assertTrue("Check modified data returned correctly", originalOperation.equalsExceptUpdatedDate(changedOperation));
        return true;
    }
    @After
    public void tearDown() throws Exception {}
}