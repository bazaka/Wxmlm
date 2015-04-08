package ApiTests.Backend;

import ApiTests.ObjectClasses.FinParameter;
import ApiTests.UsedByAll.MakeRequest;
import UsedByAll.CsvUsersReader;
import UsedByAll.TestUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

/**
 * Created by User on 4/8/2015.
 */
@RunWith(value = Parameterized.class)
public class PutFinParametersUpdateToRun {
    private TestUser testUser;

    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_BackendAPITest(");
    }

    public PutFinParametersUpdateToRun(TestUser user) {
        this.testUser = user;
    }
    @Test
    public void testPutFInParametersUpdate() throws IOException {
        String siteUrl = UsedByAll.Config.getConfig().getProtocol() + UsedByAll.Config.getConfig().getScheme(); // Урл проверяемого сайта
        long startTime;
        long elapsedTime;
        FinParameter originalOne = new GetFinParameterToRun(testUser).getAnyFinParameter(testUser, siteUrl);
        FinParameter modifiedOne = new FinParameter(originalOne.getId(), originalOne.getOperationTypeId(), originalOne.getMerchantId(), originalOne.getPaymentMerchantId(), originalOne.getParameterId(), originalOne.getValue()+"1", originalOne.getDateStart(), originalOne.getDateEnd(), originalOne.getEnabled());

        String originalJson = "[{\"id\":"+originalOne.getId()+", \"operation_type_id\":"+originalOne.getOperationTypeId() +", \"merchant_id\":" +originalOne.getMerchantId()+", \"payment_merchant_id\":"+originalOne.getPaymentMerchantId()+", \"parameter_id\":"+originalOne.getParameterId()+", \"value\":\"" + originalOne.getValue()+"\", \"date_start\":\""+originalOne.getDateStart()+"\", \"date_end\":\""+originalOne.getDateEnd()+"\", \"enabled\":"+originalOne.getEnabled()+"}]";
        String modifiedJson = "[{\"id\":"+modifiedOne.getId()+", \"operation_type_id\":"+modifiedOne.getOperationTypeId() +", \"merchant_id\":" +modifiedOne.getMerchantId()+", \"payment_merchant_id\":"+modifiedOne.getPaymentMerchantId()+", \"parameter_id\":"+modifiedOne.getParameterId()+", \"value\":\"" + modifiedOne.getValue()+"\", \"date_start\":\""+modifiedOne.getDateStart()+"\", \"date_end\":\""+modifiedOne.getDateEnd()+"\", \"enabled\":"+modifiedOne.getEnabled()+"}]";
        startTime = System.currentTimeMillis();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, testUser, "money/api/parameter/update/", "PUT", "application/json", "application/json", true);
        OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(modifiedJson);
        out.close();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);
        elapsedTime = System.currentTimeMillis() - startTime;
        // Проверяем GET-запросом, что данные обновились
        FinParameter changedOne = new GetFinParameterToRun(testUser).getFinParameterByParameter("id", originalOne.getId(), testUser, siteUrl);
        assertTrue("Check modified data saved correctly", modifiedOne.equalsFin(changedOne));

        // Содзаем URL
        httpCon = MakeRequest.getConnection(siteUrl, testUser, "money/api/parameter/update/", "PUT", "application/json", "application/json", true);
        out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(originalJson);
        out.close();
        httpCon.getInputStream();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);

        // Проверяем GET-запросом, что данные восстановились
        changedOne = new GetFinParameterToRun(testUser).getFinParameterByParameter("id", originalOne.getId(), testUser, siteUrl);
        assertTrue("Check modified data returned correctly", originalOne.equalsFin(changedOne));
        System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
    }


}
