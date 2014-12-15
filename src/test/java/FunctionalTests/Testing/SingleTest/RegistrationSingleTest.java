package FunctionalTests.Testing.SingleTest;

import UsedByAll.GmailMessager;
import FunctionalTests.Pages.RegistrationPage;
import FunctionalTests.Testing.RegistrationTest;
import UsedByAll.Config;
import UsedByAll.TestUser;
import org.junit.Test;

import javax.mail.MessagingException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by User on 12/5/2014.
 */
public class RegistrationSingleTest extends RegistrationTest{

    @Test
    public void registrationSingleTest(TestUser testUser) throws IOException, MessagingException {


        RegistrationPage registrationPage = new RegistrationPage(driver);
        GmailMessager gmailMessager = new GmailMessager();
        String confirmLink = Config.getConfig().getScheme() + "register/confirm/";

        String currentMessageTime="";
        String newMessageTime="";
        try {
            gmailMessager.initializePOP3(testUser);
            currentMessageTime = gmailMessager.getLastMessageTime(testUser);
            System.out.println("Current last message time: " + currentMessageTime);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        registrationPage.open();
        assertTrue("Page not opened", registrationPage.isOpened());
        registrationPage.openRegistration();
        registrationPage.firstStep(testUser);
        registrationPage.secondStep(testUser);
        assertTrue("Confirmation message not displayed", registrationPage.isError());

        int count = 1; // лічильник, якщо дорівнює 100, виходимо з циклу
        do {
            try {
                gmailMessager.initializePOP3(testUser);
                newMessageTime = gmailMessager.getLastMessageTime(testUser);
                System.out.println("New last message time: " + newMessageTime);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            count++;
            if (count == 100) break;
        }while(currentMessageTime.equals(newMessageTime));  // обновляємо до моменту, коли прийде лист, або до оверфлова лічильника
        String activationLink = gmailMessager.openAndReturnLink(testUser, "Welcome", confirmLink, " </p>");

        //registrationPage.confirmActivation(activationLink);


      /*  gmailPage.checkConfirmLetter(testUser, "To finish activating your account");

        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.TAB);//change tab
        ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(newtabs.get(0)); //switch tab*/


        assertEquals(registrationPage.confirmActivation(activationLink), "Congrats " + testUser.getEmail() + ", your account is now activated.");
        System.out.println("Тест для "+testUser.getEmail()+ " успешно пройден");


    }
}
