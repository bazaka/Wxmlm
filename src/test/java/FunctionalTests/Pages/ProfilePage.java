package FunctionalTests.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by User on 12/22/2014.
 */
public class ProfilePage extends BasePage {
    private static final By profilePage = By.cssSelector("img[alt=\"Avatar\"]");
    private static final By fullName = By.id("fos_user_profile_form_fullName");
    private static final By gender = By.id("fos_user_profile_form_gender");

    private static final By email = By.xpath("//div[@id='main-email']/div/div/input");
    private static final By phone = By.id("fos_user_profile_form_phone");
    private static final By current = By.xpath("//div[@class='user-wallets']/div[1]/div/div/h1");
    private static final By bonuses = By.xpath("//div[@class='user-wallets']/div[2]/div/div/h1");
    private static final By salary = By.xpath("//div[@class='user-wallets']/div[3]/div/div/h1");
    private static final By career = By.xpath("//body//div[@class='row']/div[3]/table/tbody/tr[3]/td[2]/abbr");
    private static final By status = By.xpath("//body//div[@class='row']/div[4]/table/tbody/tr[1]/td[2]/abbr");
    private static final By identification = By.xpath("//body//div[@class='row']/div[4]/table/tbody/tr[3]/td[2]/abbr");

    public ProfilePage(WebDriver driver){
        super(driver);
    }
    public int getCurrentValue(){
        return Integer.parseInt(driver.findElement(current).getText());
    }
    public int getBonusesValue(){
        return Integer.parseInt(driver.findElement(bonuses).getText());
    }
    public int getSalaryValue(){
        return Integer.parseInt(driver.findElement(salary).getText());
    }

    public void goProfilePage(){
        WebDriverWait wait = new WebDriverWait(driver,20);
        driver.findElement(profilePage).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(fullName));
    }
    public String getFullName(){
        return driver.findElement(fullName).getText();
    }
    public String getGender(){
        return driver.findElement(gender).getText();
    }
    public String getEmail(){
        return driver.findElement(email).getText();
    }
    public String getPhone(){
        return driver.findElement(phone).getText();
    }
    public String getCareer() { return driver.findElement(career).getText(); }
    public String getStatus() { return driver.findElement(status).getText(); }
    public String getIdentification() {return driver.findElement(identification).getText(); }
}
