package ApiTests.Backend;

import ApiTests.UsedByAll.MakeRequest;
import ApiTests.ObjectClasses.Operation;
import UsedByAll.TestUser;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import static org.junit.Assert.assertTrue;
public class PutOperationsUpdate {
    public boolean testPutOperationsUpdate(String scheme, TestUser user) throws IOException {
        long startTime;
        long elapsedTime;
        Operation originalOperation = new GetOperations().getAnyOperation(user, scheme);
        Operation modifiedOperation = new Operation(originalOperation.getId(), originalOperation.getTargetAccountId(), originalOperation.getSourceAccountId(), originalOperation.getPurchaseId(), originalOperation.getInitiatorUserId(), originalOperation.getCreatedDate(), originalOperation.getAmount() + 50, 1, 9, !originalOperation.getQuarantine(), originalOperation.getParentOperationId());
        String originalJson = "[{\"id\":" + originalOperation.getId() + ", \"target_account_id\":\"" + originalOperation.getTargetAccountId() + "\", \"source_account_id\":" + originalOperation.getSourceAccountId() + ", \"purchase_id\":" + originalOperation.getPurchaseId() + ", \"initiator_user_id\": " + originalOperation.getInitiatorUserId() + ", \"created_date\": \"" + originalOperation.getCreatedDate() + "\", \"amount\": \"" + originalOperation.getAmount() + "\", \"status\": \"" + originalOperation.getStatus() + "\", \"type\": " + originalOperation.getType() + ", \"quarantine\": " + originalOperation.getQuarantine() + ", \"parent_operation_id\": " + originalOperation.getParentOperationId() + "}]";
        String modifiedJson = "[{\"id\":" + modifiedOperation.getId() + ", \"target_account_id\":\"" + modifiedOperation.getTargetAccountId() + "\", \"source_account_id\":" + modifiedOperation.getSourceAccountId() + ", \"purchase_id\":" + modifiedOperation.getPurchaseId() + ", \"initiator_user_id\": " + modifiedOperation.getInitiatorUserId() + ", \"created_date\": \"" + modifiedOperation.getCreatedDate() + "\", \"amount\": \"" + modifiedOperation.getAmount() + "\", \"status\": \"" + modifiedOperation.getStatus() + "\", \"type\": " + modifiedOperation.getType() + ", \"quarantine\": " + modifiedOperation.getQuarantine() + ", \"parent_operation_id\": " + modifiedOperation.getParentOperationId() + "}]";

        // Содзаем URL
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(scheme, user, "money/api/operations/update/", "PUT", "application/json", "application/json", true);
        OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(modifiedJson);
        out.close();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);
        elapsedTime = System.currentTimeMillis() - startTime;
        // Проверяем GET-запросом, что данные обновились
        Operation changedOperation = new GetOperations().getOperationByParameter("id", originalOperation.getId(), user, scheme);
        assertTrue("Check modified data saved correctly", modifiedOperation.equalsExceptUpdatedDate(changedOperation));

        // Содзаем URL
        httpCon = MakeRequest.getConnection(scheme, user, "money/api/operations/update/", "PUT", "application/json", "application/json", true);
        out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(originalJson);
        out.close();
        httpCon.getInputStream();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);

        // Проверяем GET-запросом, что данные восстановились
        changedOperation = new GetOperations().getOperationByParameter("id", originalOperation.getId(), user, scheme);
        assertTrue("Check modified data returned correctly", originalOperation.equalsExceptUpdatedDate(changedOperation));
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
        return true;
    }
}