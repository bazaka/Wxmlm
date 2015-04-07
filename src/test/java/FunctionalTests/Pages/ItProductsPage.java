package FunctionalTests.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by User on 3/31/2015.
 */
public class ItProductsPage extends ProductsFamilyPage {
    public static final By notActiveTrial = By.xpath("//a[@data-modal='create-account']");
    public static final By trialLoginField = By.id("xmlm_bundle_productBundle_it_product_trial_login");
    public static final By domainName = By.xpath("//div[@class='newaccount-inner']/div/label");
    public static final By createTrial = By.id("xmlm_bundle_productBundle_it_product_trial_create");
    private static final By popup = By.xpath("//div[@id='gritter-notice-wrapper']/div/div/div[@class='gritter-without-image']/span[text()='Success']");
    private static final By closePopup = By.xpath("//div[@id='gritter-notice-wrapper']//a[@class='gritter-close']");


    public ItProductsPage(WebDriver driver, WebDriverWait wait){super(driver, wait);}

    public void waitForPageLoad(){
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(notActiveTrial));
        }catch (Exception e){
            System.out.println("This user haven't non-active trials");
            e.printStackTrace();
        }
    }
    public boolean getCountOfNonActiveTrials(int amount){
        List trialCount = driver.findElements(notActiveTrial);
        if(trialCount.size()!=amount){
            System.out.println("Not all trials are non-active for this user");
            return false;
        }else
            return true;
    }
    public void clickActivateButton(){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(popup));
        driver.findElement(notActiveTrial).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(trialLoginField));
    }
    public void enterTrialLogin(String login){
        driver.findElement(trialLoginField).clear();
        driver.findElement(trialLoginField).click();
        driver.findElement(trialLoginField).sendKeys(login);
    }
    public String getDomainName(){
        return(driver.findElement(domainName).getText());
    }
    public void createTrial(){
        driver.findElement(createTrial).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(popup));
        driver.findElement(closePopup).click();
    }


}
