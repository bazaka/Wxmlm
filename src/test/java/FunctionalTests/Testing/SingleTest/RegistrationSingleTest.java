package FunctionalTests.Testing.SingleTest;

import FunctionalTests.Pages.GmailPage;
import FunctionalTests.Pages.RegistrationPage;
import UsedByAll.TestUser;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by User on 12/5/2014.
 */
public class RegistrationSingleTest{
    private static WebDriver driver;
    @Test
    public void registrationSingleTest(TestUser testUser){

        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        GmailPage gmailPage = new GmailPage(driver);

        gmailPage.open();
        //gmailPage.checkAuthorization();
        gmailPage.checkGmail(testUser);

        String gmailTab = driver.getWindowHandle();  // save gmail tab
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t"); // open new tab
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));

        registrationPage.open();
        assertTrue("Page not opened", registrationPage.isOpened());
        registrationPage.openRegistration();
        registrationPage.firstStep(testUser);
        registrationPage.secondStep(testUser);
        assertTrue("Confirmation message not displayed", registrationPage.isError());

        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.TAB);
        driver.switchTo().window(gmailTab);



        gmailPage.checkConfirmLetter(testUser, "To finish activating your account");

        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.TAB);//change tab
        ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(newtabs.get(0)); //switch tab


        assertEquals(registrationPage.confirmActivation(), "Congrats " + testUser.getEmail() + ", your account is now activated.");
        System.out.println("Тест для "+testUser.getEmail()+ " успешно пройден");

            if(driver!=null)
                driver.quit();

    }
}
