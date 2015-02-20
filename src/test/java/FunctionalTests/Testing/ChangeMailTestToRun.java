package FunctionalTests.Testing;

import FunctionalTests.Pages.LogInPage;
import FunctionalTests.Pages.ProfilePage;
import UsedByAll.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

@RunWith(value = Parameterized.class)
public class ChangeMailTestToRun extends BaseTest {
    String email;
    String password;
    String emailPassword;
    String newEmail;

    @Parameters
    public static Collection testData(){return CsvUsersReader.getDataForTest("_ChangeMailTest(");}

    public ChangeMailTestToRun(TestUser testUser){
        this.email=testUser.getEmail();
        this.password=testUser.getPassword1();
        this.emailPassword=testUser.getEPassword();
        this.newEmail=testUser.getNewEmail();
    }

    @Test
    public void changeMailTestToRun() throws MessagingException, IOException {
        GmailMessager gmailMessager = new GmailMessager();
        LogInPage logInPage = new LogInPage(driver, wait);
        ProfilePage profilePage = new ProfilePage(driver, wait);

        String confirmOldMailLink = Config.getConfig().getScheme() + "users/email/checkOldConfirmation/";
        String confirmNewMailLink = Config.getConfig().getScheme() + "users/email/checkNewConfirmation/";

        String currentMessageTime = "";
        String newMessageTime = "";



        logInPage.open();
        assertTrue("Page not opened", logInPage.isOpened());

        logInPage.goLogin(email, password);

        Assert.assertEquals(logInPage.getTitle(), "KairosNet"); // логінимось

        profilePage.goProfilePage(); // заходимо на сторінку профіля
        currentMessageTime = gmailMessager.getLastMessageTime(emailPassword, email); //дата останнього листа
        profilePage.addNewEmail(newEmail);  //додаємо другу пошту
        assertTrue("Second mail is not added", profilePage.isOpened());
        int count = 1;
        do{ //стара пошта
            newMessageTime = gmailMessager.getLastMessageTime(emailPassword, email);
            count++;
            if(count == 5) break;
        }while(currentMessageTime.equals(newMessageTime)); // обновляемо пошту до моменту, коли не прийде лист, або до оверфлова лічильника
        String changeConfirmationLink = gmailMessager.openAndReturnLink(emailPassword, email, "Email change confirmation", confirmOldMailLink); //витягуємо з листа лінк

        currentMessageTime = gmailMessager.getLastMessageTime(emailPassword, newEmail); //заходимо на нову пошту, дата останнього листа;
        assertEquals(profilePage.newMailConfirmation(changeConfirmationLink), "Check your new email"); //перейшли по лінку з листа, повинен відправитись лист на нову пошту
        count = 1;
        do{ //нова пошта
            newMessageTime = gmailMessager.getLastMessageTime(emailPassword, newEmail);
            count++;
            if(count == 5) break;
        }while(currentMessageTime.equals(newMessageTime)); // обновляемо пошту до моменту, коли не прийде лист, або до оверфлова лічильника

        String newMailConfirmLink = gmailMessager.openAndReturnLink(emailPassword, newEmail, "Main Email confirmation", confirmNewMailLink); //витягуємо лінк з листа
        assertEquals(profilePage.newChangedEmail(newMailConfirmLink), "Main E-mail has been changed"); //переход по лінку з листа і перевірка

        //перевіряємо зміну пошт
        profilePage.goProfilePage();
        assertEquals(profilePage.getEmail(), newEmail);
        assertEquals(profilePage.getSecondEmail(), email);
        System.out.println("Почта успешно изменена из " +email +" на "+newEmail);
        System.out.println("ChangeMail Test успешно пройден");
    }
}
