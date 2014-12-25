package FunctionalTests.Testing.SingleTest;

import UsedByAll.Config;
import UsedByAll.GmailMessager;
import FunctionalTests.Pages.RecoveryPage;
import FunctionalTests.Testing.RecoveryTest;
import UsedByAll.TestUser;
import org.junit.Test;

import javax.mail.MessagingException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by User on 12/8/2014.
 */
public class RecoverySingleTest extends RecoveryTest {
    /*@Before
    public void setUp(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

    }*/
    //private static WebDriver driver;
    @Test
    public void recoverySingleTest(TestUser testUser) throws IOException, MessagingException {


        RecoveryPage recoveryPage = new RecoveryPage(driver);
        GmailMessager gmailMessager = new GmailMessager();
        String confirmLink = Config.getConfig().getScheme() + "resetting/reset/";



        String currentMessageTime="";
        String newMessageTime="";
        try {
           // gmailMessager.initializePOP3(testUser, testUser.getEmail());
            currentMessageTime = gmailMessager.getLastMessageTime(testUser, testUser.getEmail());
            System.out.println("Current last message time: " + currentMessageTime);
        } catch (MessagingException e) {
            e.printStackTrace();
        }



        recoveryPage.open();
        assertTrue("Page not opened", recoveryPage.isOpened());

        recoveryPage.sendRecoveryLink(testUser);
        assertEquals(recoveryPage.passwordSendMessage(), "Check your e-mail!");
        //gmailMessager.getMessages();


        int count = 1; // лічильник, якщо дорівнює 100, виходимо з циклу
        do {
            try {
                //gmailMessager.initializePOP3(testUser);
                newMessageTime = gmailMessager.getLastMessageTime(testUser, testUser.getEmail());
                System.out.println("New last message time: " +newMessageTime);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            count++;
            if (count == 100) break;
        }while(currentMessageTime.equals(newMessageTime));  // обновляємо до моменту, коли прийде лист, або до оверфлова лічильника
        String activationLink = gmailMessager.openAndReturnLink(testUser, testUser.getEmail(), "Reset Password", confirmLink, "Regards");
        assertEquals("Not same titles", recoveryPage.enterNewPassword(testUser, activationLink), "KairosNet");

    }
    /*@After
    public void tearDown(){
        if(driver!=null)
            driver.quit();
    }*/
}
