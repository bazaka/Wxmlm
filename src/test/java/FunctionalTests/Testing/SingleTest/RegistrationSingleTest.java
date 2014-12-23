package FunctionalTests.Testing.SingleTest;

import FunctionalTests.Pages.ProfilePage;
import FunctionalTests.Pages.RegistrationPage;
import FunctionalTests.Testing.RegistrationTest;
import UsedByAll.Config;
import UsedByAll.GmailMessager;
import UsedByAll.TestUser;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Test;

import javax.mail.MessagingException;
import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by User on 12/5/2014.
 */
public class RegistrationSingleTest extends RegistrationTest{

    @Test
    public void registrationSingleTest(TestUser testUser) throws IOException, MessagingException {


        ProfilePage profilePage = new ProfilePage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        GmailMessager gmailMessager = new GmailMessager();


        String confirmLink = Config.getConfig().getScheme() + "register/confirm/";

        String currentMessageTime="";
        String newMessageTime="";
        try {
            gmailMessager.initializePOP3(testUser);
            currentMessageTime = gmailMessager.getLastMessageTime(testUser);
           // System.out.println("Current last message time: " + currentMessageTime);
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
                //System.out.println("New last message time: " + newMessageTime);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            count++;
            if (count == 30) break;
        }while(currentMessageTime.equals(newMessageTime));  // обновляємо до моменту, коли прийде лист, або до оверфлова лічильника
        String activationLink = gmailMessager.openAndReturnLink(testUser, "Welcome", confirmLink, " ");




        assertEquals(registrationPage.confirmActivation(activationLink), "Congrats " + testUser.getEmail() + ", your account is now activated.");

        assertEquals("Current value not 0", profilePage.getCurrentValue(), 0);
        assertEquals("Bonus value not 0", profilePage.getBonusesValue(), 0);
        assertEquals("Salary value not 0", profilePage.getSalaryValue(), 0);



        profilePage.goProfilePage();

        assertEquals("Not same Full Name", profilePage.getFullName(), testUser.getFullName());
        assertEquals("Not same Gender", profilePage.getGender(), "Male");
        assertEquals("Not same Email", profilePage.getEmail(), testUser.getEmail());
        assertEquals("Not same Phone", profilePage.getPhone(), testUser.getPhone());

        assertEquals("Not same career", profilePage.getCareer(), "None");
        assertEquals("Not same status", profilePage.getStatus(), "Potential");
        assertEquals("Not same identification", profilePage.getIdentification(), "Not Approved");
        assertEquals("Not same invite code", profilePage.getInviteCode(), "You have not permissions to view the invite code.");

        Assert.assertEquals("Not same country", profilePage.getCountry(), "Afghanistan");


        System.out.println("Тест для "+testUser.getEmail()+ " успешно пройден");


    }
    @After
    public void tearDown(){
        if(driver!=null)
            driver.quit();
    }
}
