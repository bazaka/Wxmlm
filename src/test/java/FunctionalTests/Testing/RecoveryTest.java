package FunctionalTests.Testing;

import FunctionalTests.Pages.RecoveryPage;
import UsedByAll.Config;
import UsedByAll.CsvUsersReader;
import UsedByAll.GmailMessager;
import UsedByAll.TestUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by User on 12/8/2014.
 */
@RunWith(value=Parameterized.class )
public class RecoveryTest extends BaseTest/*extends BaseTest */{

    private String emailPassword;
    private String emailAddress;
    private String newPassword1;
    private String newPassword2;

    @Parameterized.Parameters
    public static Collection testData(){return CsvUsersReader.getDataForTest("_RecoveryTest(");}

    public RecoveryTest(TestUser testUser) {
        this.emailAddress=testUser.getEmail();
        this.emailPassword=testUser.getEPassword();
        this.newPassword1=testUser.getNewPassword1();
        this.newPassword2=testUser.getNewPassword2();


    }

    @Test
    public void recoveryTestToRun() throws IOException, MessagingException {

        RecoveryPage recoveryPage = new RecoveryPage(driver, wait);
        GmailMessager gmailMessager = new GmailMessager();
        String confirmLink = Config.getConfig().getScheme() + "resetting/reset/";



        String currentMessageTime="";
        String newMessageTime="";
        try {
            // gmailMessager.initializePOP3(testUser, testUser.getEmail());
            currentMessageTime = gmailMessager.getLastMessageTime(emailPassword, emailAddress); //emailPass, emailAdd
            System.out.println("Current last message time: " + currentMessageTime);
        } catch (MessagingException e) {
            e.printStackTrace();
        }



        recoveryPage.open();
        assertTrue("Page not opened", recoveryPage.isOpened());

        recoveryPage.sendRecoveryLink(emailAddress); //email
        assertEquals(recoveryPage.passwordSendMessage(), "Check your e-mail!");
        //gmailMessager.getMessages();


        int count = 1; // лічильник, якщо дорівнює 100, виходимо з циклу
        do {
            try {
                //gmailMessager.initializePOP3(testUser);
                newMessageTime = gmailMessager.getLastMessageTime(emailPassword, emailAddress);
                System.out.println("New last message time: " +newMessageTime);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            count++;
            if (count == 5) break;
        }while(currentMessageTime.equals(newMessageTime));  // обновляємо до моменту, коли прийде лист, або до оверфлова лічильника
        String activationLink = gmailMessager.openAndReturnLink(emailPassword, emailAddress, "Reset Password", confirmLink);
        assertEquals("Not same titles", recoveryPage.enterNewPassword(newPassword1, newPassword2, activationLink), "KairosNet");
        System.out.println("RecoveryTest успешно пройден");
    }
}
