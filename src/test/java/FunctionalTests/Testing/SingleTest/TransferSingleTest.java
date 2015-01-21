package FunctionalTests.Testing.SingleTest;

import FunctionalTests.Testing.TransferTest;
import UsedByAll.TestUser;
import org.junit.Test;

/**
 * Created by User on 1/6/2015.
 */
public class TransferSingleTest extends TransferTest {
    @Test
    public void transferSingleTest(TestUser testUser){
        /*LogInPage loginPage = new LogInPage(driver);
        TransferPage transferPage = new TransferPage(driver);
        AuthorizedUserPage userPage = new AuthorizedUserPage(driver);

        loginPage.open();
        loginPage.goLogin(testUser);
        assertEquals(loginPage.getTitle(), "KairosNet");
        userPage.goMoney();
        userPage.goTransfer();

        Random random = new Random();
        float f = random.nextFloat();
        f = f * 1000;
        int a = (int)Math.round(f);
        f = (float)a/100;
        String value = Float.toString(f);

        transferPage.positiveTransferFromBonusesToCurrent(value);
        transferPage.checkOperationHistory();
        assertEquals(transferPage.getTitle(), "Operations");
        assertEquals(transferPage.getOperationType(), "Transfer to Current");
        assertEquals(transferPage.getOperationSender(), "Me, Bonuses");
        assertEquals(transferPage.getOperationAmount(), value);
        assertEquals(transferPage.getOperationStatus(), "Sent");*/

    }
}
