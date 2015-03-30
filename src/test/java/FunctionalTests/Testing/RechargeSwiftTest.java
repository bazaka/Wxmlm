package FunctionalTests.Testing;

import ApiTests.Backend.GetBankDetailsToRun;
import ApiTests.ObjectClasses.BankDetails;
import ApiTests.UsedByAll.MakeRequest;
import FunctionalTests.Pages.*;
import UsedByAll.Config;
import UsedByAll.CsvUsersReader;
import UsedByAll.TestUser;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by User on 3/24/2015.
 */
@RunWith(value = Parameterized.class)
public class RechargeSwiftTest extends BaseTest {
    String email;
    String password;
    TestUser testUser;

    @Parameterized.Parameters
    public static Collection testDFata(){return CsvUsersReader.getDataForTest("_RechargeSwiftTest(");}

    public RechargeSwiftTest(TestUser testUser){
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
        SwiftPage swiftPage = new SwiftPage(driver, wait);
        ProfilePage profilePage = new ProfilePage(driver, wait);

        logInPage.open();
        logInPage.goLogin(email, password);
        assertEquals(logInPage.getTitle(), "KairosNet");
        userPage.goMoney();
        moneyPage.goToRecharge();
        rechargePage.goSwift();
        profilePage.goProfilePage(); // открыть профиль

        String inviteCode = profilePage.getInviteCode(); // достать инвайт код
        String fullName = profilePage.getFullName();


        profilePage.closeProfilePage(); // закрыть профиль

        String sum = makeRandomValue(); // сгенерировать сумму
        swiftPage.enterAmount(sum);
        swiftPage.createInvoice(); // создать инвойс

        BankDetails bankDetails = new GetBankDetailsToRun(testUser).getBankDetailsById("user_id", 1, Config.getConfig().getAdmin(), siteUrl); // взять данные админа
        assertEquals("Incorrect full name", fullName, swiftPage.getInvoiceFullName());
        assertEquals("Incorrect email", email, swiftPage.getInvoiceEmail());
        assertEquals("Incorrect invite code", inviteCode, swiftPage.getInvoiceInviteCode());
        assertTrue("Payment purpose contains incorrect invite code", swiftPage.getPaymentPurpose().contains(inviteCode));
        assertEquals("Incorrect admin name", bankDetails.getName(), swiftPage.getAdminName());
        assertEquals("Incorrect admin address", bankDetails.getAddress(), swiftPage.getAdminAddress());
        assertEquals("Incorrect bank name", bankDetails.getBankName(), swiftPage.getAdminBank());
        assertEquals("Incorrect bank address", bankDetails.getBankAddress(), swiftPage.getAdminBankAddress());
        assertEquals("Incorrect iban", bankDetails.getIban(), swiftPage.getAdminIban());
        assertEquals("Incorrect swift code", bankDetails.getSwiftCode(), swiftPage.getAdminSwift());
        assertTrue("Incorrect recharge amount", swiftPage.getAmountInvoice().contains(sum));

    }
}
