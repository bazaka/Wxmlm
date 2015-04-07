package FunctionalTests.Testing;

import ApiTests.Backend.GetBankDetailsToRun;
import ApiTests.ObjectClasses.BankDetails;
import FunctionalTests.Pages.*;
import UsedByAll.Config;
import UsedByAll.CsvUsersReader;
import UsedByAll.MakeRandomValue;
import UsedByAll.TestUser;

import junit.framework.TestCase;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by User on 3/24/2015.
 */
@RunWith(value = Parameterized.class)
public class RechargeSwiftTestToRun extends BaseTest {
    String email;
    String password;
    TestUser testUser;

    @Parameterized.Parameters
    public static Collection testData(){return CsvUsersReader.getDataForTest("_RechargeSwiftTest(");}

    public RechargeSwiftTestToRun(TestUser testUser){
        this.email=testUser.getEmail();
        this.password=testUser.getPassword1();
        this.testUser=testUser;
    }
    @Test
    public void swiftTest() throws IOException, JSONException {
        String siteUrl = Config.getConfig().getProtocol() + Config.getConfig().getScheme();

        LogInPage logInPage = new LogInPage(driver, wait);
        AuthorizedUserPage userPage = new AuthorizedUserPage(driver, wait);
        MoneyFamilyPage moneyPage = new MoneyFamilyPage(driver, wait);
        RechargePage rechargePage = new RechargePage(driver, wait);
        ProfilePage profilePage = new ProfilePage(driver, wait);

        logInPage.open();
        logInPage.goLogin(email, password);
        assertEquals(logInPage.getTitle(), "KairosNet"); //
        userPage.goMoney();
        moneyPage.goToRecharge();
        rechargePage.goSwift();
        profilePage.goProfilePage(); // открыть профиль

        String inviteCode = profilePage.getInviteCode(); // достать инвайт код
        String fullName = profilePage.getFullName();


        profilePage.closeProfilePage(); // закрыть профиль
        TestCase.assertTrue("Incorrect image", rechargePage.getSwiftImageLink().contains("swift"));

        MakeRandomValue getRandomValue = new MakeRandomValue();
        String sum = getRandomValue.makeRandomValue(); // сгенерировать сумму
        rechargePage.enterSwiftAmount(sum);
        rechargePage.createInvoice(); // создать инвойс

        BankDetails adminBankDetails = new GetBankDetailsToRun(testUser).getBankDetailsById("user_id", 1, Config.getConfig().getAdmin(), siteUrl); // взять данные админа
        assertEquals("Incorrect full name", fullName, rechargePage.getInvoiceFullName());
        assertEquals("Incorrect email", email, rechargePage.getInvoiceEmail());
        assertEquals("Incorrect invite code", inviteCode, rechargePage.getInvoiceInviteCode());
        assertTrue("Payment purpose contains incorrect invite code", rechargePage.getPaymentPurpose().contains(inviteCode));
        assertEquals("Incorrect admin name", adminBankDetails.getName(), rechargePage.getAdminName());
        assertEquals("Incorrect admin address", adminBankDetails.getAddress(), rechargePage.getAdminAddress());
        assertEquals("Incorrect bank name", adminBankDetails.getBankName(), rechargePage.getAdminBank());
        assertEquals("Incorrect bank address", adminBankDetails.getBankAddress(), rechargePage.getAdminBankAddress());
        assertEquals("Incorrect iban", adminBankDetails.getIban(), rechargePage.getAdminIban());
        assertEquals("Incorrect swift code", adminBankDetails.getSwiftCode(), rechargePage.getAdminSwift());
        assertTrue("Incorrect recharge amount", rechargePage.getAmountInvoice().contains(sum));
    }
}
