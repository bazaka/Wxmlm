package ApiTests.Backend;

import ApiTests.ObjectClasses.Product;
import ApiTests.UsedByAll.MakeRequest;
import UsedByAll.TestUser;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStreamWriter;
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
}