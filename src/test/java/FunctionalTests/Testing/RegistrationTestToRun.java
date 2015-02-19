package FunctionalTests.Testing;

import FunctionalTests.Pages.ProfilePage;
import FunctionalTests.Pages.RegistrationPage;
import UsedByAll.Config;
import UsedByAll.CsvUsersReader;
import UsedByAll.GmailMessager;
import UsedByAll.TestUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by User on 12/1/2014.
 */
@RunWith(value=Parameterized.class )
public class RegistrationTestToRun extends BaseTest {

    private String emailPassword;

    private String emailAddress;
    private String password1;
    private String password2;
    private String inviteCode;
    private String userFullName;
    private String userPhone;

    @Parameters
    public static Collection testData(){return CsvUsersReader.getDataForTest("_RegistrationTest(");}

    public RegistrationTestToRun(TestUser testUser) {

        this.emailPassword=testUser.getEPassword();
        this.emailAddress=testUser.getEmail();
        this.password1=testUser.getPassword1();
        this.password2=testUser.getPassword2();
        this.inviteCode=testUser.getInviteCode();
        this.userFullName=testUser.getFullName();
        this.userPhone=testUser.getPhone();
    }

    @Test
    public void registrationTest() throws IOException, MessagingException {




        RegistrationPage registrationPage = new RegistrationPage(driver,wait);
        GmailMessager gmailMessager = new GmailMessager();


        String confirmLink = Config.getConfig().getScheme() + "register/confirm/";

        String currentMessageTime="";
        String newMessageTime="";
        try {
            //gmailMessager.initializePOP3(testUser);
            currentMessageTime = gmailMessager.getLastMessageTime(emailPassword, emailAddress);
            // System.out.println("Current last message time: " + currentMessageTime);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        registrationPage.open();
        assertTrue("Page not opened", registrationPage.isOpened());
        registrationPage.openRegistration();
        registrationPage.firstStep(emailAddress, password1, password2, inviteCode);
        registrationPage.secondStep(userFullName, userPhone);
        assertTrue("Confirmation message not displayed", registrationPage.isError());
        int count = 1; // лічильник, якщо дорівнює 100, виходимо з циклу
        do {
            try {
                //gmailMessager.initializePOP3(testUser);
                newMessageTime = gmailMessager.getLastMessageTime(emailPassword, emailAddress);
                //System.out.println("New last message time: " + newMessageTime);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            count++;
            if (count == 5) break;
        }while(currentMessageTime.equals(newMessageTime));  // обновляємо до моменту, коли прийде лист, або до оверфлова лічильника
        String activationLink = gmailMessager.openAndReturnLink(emailPassword, emailAddress, "Welcome", confirmLink);




        assertEquals(registrationPage.confirmActivation(activationLink), "Congrats, " + emailAddress + ", your account is now activated.");

        //checkCorrectData(testUser);


        System.out.println("Тест для "+emailAddress+ " успешно пройден");



    }
    public void checkCorrectData(TestUser testUser){

        ProfilePage profilePage = new ProfilePage(driver, wait);
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

        assertEquals("Not same country", profilePage.getCountry(), "Afghanistan");

        System.out.println("RegistrationTest успешно пройден");

    }





}
