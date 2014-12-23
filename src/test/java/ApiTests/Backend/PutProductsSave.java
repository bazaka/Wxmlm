package ApiTests.Backend;

import ApiTests.ObjectClasses.Product;
import ApiTests.UsedByAll.MakeRequest;
import UsedByAll.TestUser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;

import static org.junit.Assert.assertTrue;

// * Created for W-xmlm by Fill on 11.12.2014.
public class PutProductsSave {
    @Test
    public boolean testPutProductsUpdate(String scheme, TestUser user) throws IOException {
        Product originalOne = new GetProducts().getAnyProduct(user, scheme);
        if (originalOne == null) {
            return false;
        }
        String imageUrlOrigString;
        String imageUrlModString;
        if (originalOne.getImageUrl().equals(null)){
            imageUrlOrigString = "null";
        }
        else {
            imageUrlOrigString = "\"" + originalOne.getImageUrl() + "\"";
        }
        String originalJson;
        String modifiedJson;
        Product modifiedOne;
        if (originalOne.getImageUrl().equals(null)){
            imageUrlModString = "null";
        }
        else {
            imageUrlModString = "\"" + originalOne.getImageUrl() + "\"";
        }
        if (originalOne.getCategoryId() == 1) {
            modifiedOne = new Product(originalOne.getId(), originalOne.getCategoryId(), originalOne.getOwnerId(), originalOne.getCreatorId(), originalOne.getTitle() + " title", originalOne.getDescription() + " description", originalOne.getPrice() + 100, originalOne.getStatus(), originalOne.getType(), originalOne.getCreatedDate(), originalOne.getImageUrl(), originalOne.getAvailable(), originalOne.getDiscSpace(), originalOne.getTimeOnline(), originalOne.getBasicIncome(), originalOne.getBasicIncomePeriod(), originalOne.getProfit(), originalOne.getInvestmentPeriod(), originalOne.getStart());
            originalJson = "[{\"id\":" + originalOne.getId() + ", \"category_id\": " + originalOne.getCategoryId() + ", \"owner_id\": " + originalOne.getOwnerId() + ", \"creator_id\":" + originalOne.getCreatorId() + ", \"title\": \"" + originalOne.getTitle() + "\", \"description\": \"" + originalOne.getDescription() + "\", \"price\": " + originalOne.getPrice() + ", \"status\": " + originalOne.getStatus() + ", \"type\": " + originalOne.getType() + ", \"image_url\": " + imageUrlOrigString + ", \"attributes\": { \"available\": " + originalOne.getAvailable() + ", \"discSpace\": " + originalOne.getDiscSpace() + ", \"timeOnline\": " + originalOne.getTimeOnline() + ", \"basicIncome\": " + originalOne.getBasicIncome() + ", \"basicIncomePeriod\": " + originalOne.getBasicIncomePeriod() + ", \"profit\": " + originalOne.getProfit() + ", \"investmentPeriod\": " + originalOne.getInvestmentPeriod() + ", \"start\": \"" + originalOne.getStart() + "\"}}]";
            modifiedJson = "[{\"id\":" + modifiedOne.getId() + ", \"category_id\": " + modifiedOne.getCategoryId() + ", \"owner_id\": " + modifiedOne.getOwnerId() + ", \"creator_id\":" + modifiedOne.getCreatorId() + ", \"title\": \"" + modifiedOne.getTitle() + "\", \"description\": \"" + modifiedOne.getDescription() + "\", \"price\": " + modifiedOne.getPrice() + ", \"status\": " + modifiedOne.getStatus() + ", \"type\": " + modifiedOne.getType() + ", \"image_url\": " + imageUrlModString + ", \"attributes\": { \"available\": " + modifiedOne.getAvailable() + ", \"discSpace\": " + modifiedOne.getDiscSpace() + ", \"timeOnline\": " + modifiedOne.getTimeOnline() + ", \"basicIncome\": " + modifiedOne.getBasicIncome() + ", \"basicIncomePeriod\": " + modifiedOne.getBasicIncomePeriod() + ", \"profit\": " + modifiedOne.getProfit() + ", \"investmentPeriod\": " + modifiedOne.getInvestmentPeriod() + ", \"start\": \"" + modifiedOne.getStart() + "\"}}]";
        }
        else if (originalOne.getCategoryId() == 2) {
            modifiedOne = new Product(originalOne.getId(), originalOne.getCategoryId(), originalOne.getOwnerId(), originalOne.getCreatorId(), originalOne.getTitle() + " title", originalOne.getDescription() + " description", originalOne.getPrice() + 100, originalOne.getStatus(), originalOne.getType(), originalOne.getCreatedDate(), originalOne.getImageUrl(), originalOne.getRequiredForTrial(), originalOne.getTrialPeriod(), originalOne.getQuotaPrefix() + " quotaPrefix", originalOne.getQuota() + 1, originalOne.getQuotaMeasurement() + "QuotaMeasurement", originalOne.getServiceId());
            String requiredForTrialStringMod = "[";
            for (int i = 0; i < modifiedOne.getRequiredForTrial().length - 1; i++){
                requiredForTrialStringMod = requiredForTrialStringMod + modifiedOne.getRequiredForTrial()[i] + ",";
            }
            requiredForTrialStringMod = requiredForTrialStringMod + modifiedOne.getRequiredForTrial()[modifiedOne.getRequiredForTrial().length - 1];
            requiredForTrialStringMod = requiredForTrialStringMod + "]";
            System.out.println("requiredForTrialStringMod: " + requiredForTrialStringMod);
            String requiredForTrialStringOri = "[";
            for (int i = 0; i < modifiedOne.getRequiredForTrial().length - 1; i++){
                requiredForTrialStringOri = requiredForTrialStringOri + originalOne.getRequiredForTrial()[i] + ",";
            }
            requiredForTrialStringOri = requiredForTrialStringOri + originalOne.getRequiredForTrial()[modifiedOne.getRequiredForTrial().length - 1];
            requiredForTrialStringOri = requiredForTrialStringOri + "]";
            System.out.println("requiredForTrialStringOri: " + requiredForTrialStringOri);
            originalJson = "[{\"id\":" + originalOne.getId() + ", \"category_id\": " + originalOne.getCategoryId() + ", \"owner_id\": " + originalOne.getOwnerId() + ", \"creator_id\":" + originalOne.getCreatorId() + ", \"title\": \"" + originalOne.getTitle() + "\", \"description\": \"" + originalOne.getDescription() + "\", \"price\": " + originalOne.getPrice() + ", \"status\": " + originalOne.getStatus() + ", \"type\": " + originalOne.getType() + ", \"image_url\": " + originalOne.getImageUrl() + ", \"attributes\": { \"requiredForTrial\": " + requiredForTrialStringOri + ", \"trialPeriod\": \"" + originalOne.getTrialPeriod() + "\", \"quotaPrefix\": \"" + originalOne.getQuotaPrefix() + "\", \"quota\": " + originalOne.getQuota() + ", \"quotaMeasurement\": \"" + originalOne.getQuotaMeasurement() + "\", \"serviceId\": " + originalOne.getServiceId() + "}}]";
            modifiedJson = "[{\"id\":" + modifiedOne.getId() + ", \"category_id\": " + modifiedOne.getCategoryId() + ", \"owner_id\": " + modifiedOne.getOwnerId() + ", \"creator_id\":" + modifiedOne.getCreatorId() + ", \"title\": \"" + modifiedOne.getTitle() + "\", \"description\": \"" + modifiedOne.getDescription() + "\", \"price\": " + modifiedOne.getPrice() + ", \"status\": " + modifiedOne.getStatus() + ", \"type\": " + modifiedOne.getType() + ", \"image_url\": " + modifiedOne.getImageUrl() + ", \"attributes\": { \"requiredForTrial\": " + requiredForTrialStringMod + ", \"trialPeriod\": \"" + modifiedOne.getTrialPeriod() + "\", \"quotaPrefix\": \"" + modifiedOne.getQuotaPrefix() + "\", \"quota\": " + modifiedOne.getQuota() + ", \"quotaMeasurement\": \"" + modifiedOne.getQuotaMeasurement() + "\", \"serviceId\": " + modifiedOne.getServiceId() + "}}]";
            System.out.println(originalJson);
            System.out.println(modifiedJson);
        }
        else {
            System.out.println("Unrecognised category_id");
            return false;
        }

        // Содзаем URL
        HttpURLConnection httpCon = MakeRequest.getConnection(scheme, user, "products/api/product/save/", "PUT", "application/json", "application/json", true);
        OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(modifiedJson);
        out.close();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);
        // Проверяем GET-запросом, что данные обновились
        Product changedOne = new GetProducts().getProductByParameter("id", originalOne.getId(), user, scheme);
        assertTrue("Check modified data saved correctly", modifiedOne.equalsExceptUpdatedDate(changedOne));

        // Содзаем URL
        httpCon = MakeRequest.getConnection(scheme, user, "products/api/product/save/", "PUT", "application/json", "application/json", true);
        out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(originalJson);
        out.close();
        httpCon.getInputStream();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);
        // Проверяем GET-запросом, что данные восстановились
        changedOne = new GetProducts().getProductByParameter("id", originalOne.getId(), user, scheme);
        assertTrue("Check modified data returned correctly", originalOne.equalsExceptUpdatedDate(changedOne));
        return true;
    }

    public boolean testPutProductsInsert(String scheme, TestUser testUser) throws IOException, JSONException {
        Product originalOne = new GetProducts().getAnyProduct(testUser, scheme);
        if (originalOne == null) {
            return false;
        }
        String imageUrlModString;
        String newJson;
        Product newOne;
        if (originalOne.getImageUrl().equals(null)){
            imageUrlModString = "null";
        }
        else {
            imageUrlModString = "\"" + originalOne.getImageUrl() + "\"";
        }
        if (originalOne.getCategoryId() == 1) {
            newOne = new Product(originalOne.getId() + 30, 4, originalOne.getOwnerId(), originalOne.getCreatorId(), originalOne.getTitle() + " title", originalOne.getDescription() + " description", originalOne.getPrice() + 100, originalOne.getStatus(), 41, originalOne.getCreatedDate(), originalOne.getImageUrl(), originalOne.getAvailable(), originalOne.getDiscSpace(), originalOne.getTimeOnline(), originalOne.getBasicIncome(), originalOne.getBasicIncomePeriod(), originalOne.getProfit(), originalOne.getInvestmentPeriod(), originalOne.getStart());
            newJson = "[{\"id\":" + newOne.getId() + ", \"category_id\": " + newOne.getCategoryId() + ", \"owner_id\": " + newOne.getOwnerId() + ", \"creator_id\":" + newOne.getCreatorId() + ", \"title\": \"" + newOne.getTitle() + "\", \"description\": \"" + newOne.getDescription() + "\", \"price\": " + newOne.getPrice() + ", \"status\": " + newOne.getStatus() + ", \"type\": " + newOne.getType() + ", \"image_url\": " + imageUrlModString + ", \"attributes\": { \"available\": " + newOne.getAvailable() + ", \"discSpace\": " + newOne.getDiscSpace() + ", \"timeOnline\": " + newOne.getTimeOnline() + ", \"basicIncome\": " + newOne.getBasicIncome() + ", \"basicIncomePeriod\": " + newOne.getBasicIncomePeriod() + ", \"profit\": " + newOne.getProfit() + ", \"investmentPeriod\": " + newOne.getInvestmentPeriod() + ", \"start\": \"" + newOne.getStart() + "\"}}]";
        }
        else if (originalOne.getCategoryId() == 2) {
            newOne = new Product(originalOne.getId(), originalOne.getCategoryId(), originalOne.getOwnerId(), originalOne.getCreatorId(), originalOne.getTitle() + " title", originalOne.getDescription() + " description", originalOne.getPrice() + 100, originalOne.getStatus(), originalOne.getType(), originalOne.getCreatedDate(), originalOne.getImageUrl(), originalOne.getRequiredForTrial(), originalOne.getTrialPeriod(), originalOne.getQuotaPrefix() + " quotaPrefix", originalOne.getQuota() + 1, originalOne.getQuotaMeasurement() + "QuotaMeasurement", originalOne.getServiceId());
            String requiredForTrialStringMod = "[";
            for (int i = 0; i < newOne.getRequiredForTrial().length - 1; i++){
                requiredForTrialStringMod = requiredForTrialStringMod + newOne.getRequiredForTrial()[i] + ",";
            }
            requiredForTrialStringMod = requiredForTrialStringMod + newOne.getRequiredForTrial()[newOne.getRequiredForTrial().length - 1];
            requiredForTrialStringMod = requiredForTrialStringMod + "]";
            System.out.println("requiredForTrialStringMod: " + requiredForTrialStringMod);
            String requiredForTrialStringOri = "[";
            for (int i = 0; i < newOne.getRequiredForTrial().length - 1; i++){
                requiredForTrialStringOri = requiredForTrialStringOri + originalOne.getRequiredForTrial()[i] + ",";
            }
            requiredForTrialStringOri = requiredForTrialStringOri + originalOne.getRequiredForTrial()[newOne.getRequiredForTrial().length - 1];
            requiredForTrialStringOri = requiredForTrialStringOri + "]";
            System.out.println("requiredForTrialStringOri: " + requiredForTrialStringOri);
            newJson = "[{\"id\":" + newOne.getId() + ", \"category_id\": " + newOne.getCategoryId() + ", \"owner_id\": " + newOne.getOwnerId() + ", \"creator_id\":" + newOne.getCreatorId() + ", \"title\": \"" + newOne.getTitle() + "\", \"description\": \"" + newOne.getDescription() + "\", \"price\": " + newOne.getPrice() + ", \"status\": " + newOne.getStatus() + ", \"type\": " + newOne.getType() + ", \"image_url\": " + newOne.getImageUrl() + ", \"attributes\": { \"requiredForTrial\": " + requiredForTrialStringMod + ", \"trialPeriod\": \"" + newOne.getTrialPeriod() + "\", \"quotaPrefix\": \"" + newOne.getQuotaPrefix() + "\", \"quota\": " + newOne.getQuota() + ", \"quotaMeasurement\": \"" + newOne.getQuotaMeasurement() + "\", \"serviceId\": " + newOne.getServiceId() + "}}]";
        }
        else {
            System.out.println("Unrecognised category_id");
            return false;
        }
        HttpURLConnection httpCon = MakeRequest.getConnection(scheme, testUser, "products/api/product/save/", "PUT", "application/json", "application/json", true);
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

        //берем из респонса id новой операции
        JSONObject response = new JSONObject(result);
        JSONArray reports = response.getJSONArray("reports");
        String report = reports.getString(0);
        System.out.println(report);
        int newOneId = Integer.parseInt(report.replaceAll("[\\D]", ""));
        System.out.println("newOneId: " + newOneId);

        //Проверяем Get-запросом, что данный обновились
        Product changedOne = new GetProducts().getProductByParameter("id", newOneId, testUser, scheme);
        assertTrue("Check modified data saved correctly", newOne.equalsExceptUpdatedDate(changedOne));
        return true;
    }
}