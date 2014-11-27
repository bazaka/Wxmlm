package ApiTests;

import ApiTests.ObjectClasses.Operation;
import UsedByAll.TestUser;
import org.apache.commons.codec.binary.Base64;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.assertTrue;

public class BackendPutOperationsUpdate {
    @Before
    public void setUp(String scheme) throws Exception {
        System.out.println("Запускаю селениум для проверки API-метода PUT Operations update на " + scheme);
    }

    @Test
    public boolean testBackendPutOperationsUpdate(String scheme, TestUser user) throws IOException {
        Operation originalOperation = new BackendGetOperations().getAnyOperation(user, scheme);
        Operation modifiedOperation = new Operation(originalOperation.getId(), originalOperation.getTargetAccountId(), originalOperation.getSourceAccountId(), originalOperation.getPurchaseId(), originalOperation.getInitiatorUserId(), originalOperation.getCreatedDate(), originalOperation.getAmount() + 50, 1, 9);
        String originalJson = "[{\"id\":" + originalOperation.getId() + ", \"target_account_id\":\"" + originalOperation.getTargetAccountId() + "\", \"source_account_id\":" + originalOperation.getSourceAccountId() + ", \"purchase_id\":" + originalOperation.getPurchaseId() + ", \"initiator_user_id\": \"" + originalOperation.getInitiatorUserId() + "\", \"amount\": \"" + originalOperation.getAmount() + "\", \"status\": \"" + originalOperation.getStatus() + "\", \"type\": \"" + originalOperation.getType() + "\"}]";
        String modifiedJson = "[{\"id\":" + modifiedOperation.getId() + ", \"target_account_id\":\"" + modifiedOperation.getTargetAccountId() + "\", \"source_account_id\":" + modifiedOperation.getSourceAccountId() + ", \"purchase_id\":" + modifiedOperation.getPurchaseId() + ", \"initiator_user_id\": \"" + modifiedOperation.getInitiatorUserId() + "\", \"amount\": \"" + modifiedOperation.getAmount() + "\", \"status\": \"" + modifiedOperation.getStatus() + "\", \"type\": \"" + modifiedOperation.getType() + "\"}]";

        // Содзаем URL
        String authString = user.getEmail() + ":" + user.getPassword1();
        byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
        String authStringEnc = new String(authEncBytes);
        String stringUrl = "http://" + scheme + "money/api/accounts/update/";
        URL url = new URL(stringUrl);
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        httpCon.setRequestProperty("Authorization", "Basic " + authStringEnc);
        httpCon.setRequestMethod("PUT");
        httpCon.setRequestProperty("Content-Type", "application/json");
        httpCon.setRequestProperty("Accept", "application/json");
        httpCon.setDoOutput(true);
        OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(modifiedJson);
        out.close();
        httpCon.getInputStream();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);

        // Проверяем GET-запросом, что данные обновились
        Operation changedOperation = new BackendGetOperations().getOperationByParameter("id", String.valueOf(originalOperation.getId()), user, scheme);
        assertTrue("Check modified data saved correctly", modifiedOperation.equalsExceptUpdatedDate(changedOperation));
        url = new URL(stringUrl);
        httpCon = (HttpURLConnection) url.openConnection();
        httpCon.setRequestProperty("Authorization", "Basic " + authStringEnc);
        httpCon.setRequestMethod("PUT");
        httpCon.setRequestProperty("Content-Type", "application/json");
        httpCon.setRequestProperty("Accept", "application/json");
        httpCon.setDoOutput(true);
        out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(originalJson);
        out.close();
        httpCon.getInputStream();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);

        // Проверяем GET-запросом, что данные восстановились
        changedOperation = new BackendGetOperations().getOperationByParameter("id", String.valueOf(originalOperation.getId()), user, scheme);
        assertTrue("Check modified data returned correctly", originalOperation.equalsExceptUpdatedDate(changedOperation));
        return true;
    }
    @After
    public void tearDown() throws Exception {}
}