package FunctionalTests.Testing;

import FunctionalTests.Pages.AuthorizedUserPage;
import FunctionalTests.Pages.LogInPage;
import FunctionalTests.Pages.MoneyFamilyPage;
import FunctionalTests.Pages.TransferPage;
import UsedByAll.CsvUsersReader;
import UsedByAll.TestUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Collection;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;



@RunWith(value = Parameterized.class)
public class TransferTest extends BaseTest {
    String email;
    String password;

    @Parameters
    public static Collection testDFata(){return CsvUsersReader.getDataForTest("_TransferTest(");}

    public TransferTest(TestUser testUser){
        this.email=testUser.getEmail();
        this.password=testUser.getPassword1();
    }

    @Test
    public void transferTestToRun(){


        LogInPage logInPage = new LogInPage(driver, wait);
        TransferPage transferPage = new TransferPage(driver, wait);
        AuthorizedUserPage userPage = new AuthorizedUserPage(driver, wait);
        MoneyFamilyPage moneyPage = new MoneyFamilyPage(driver, wait);

        logInPage.open();
        logInPage.goLogin(email, password);
        assertEquals(logInPage.getTitle(), "KairosNet");
        userPage.goMoney();
        moneyPage.goToTransfer();


        String value = makeRandomValue(); // generate random number
        transferPage.clickBonusesAccordion(); // start sending from bonuses to current
        transferPage.clickOnFromBonusesToCurrentRadioButton();
        transferPage.enterAmountFromBonuses(value);
        transferPage.clickBonusTransfer();
        moneyPage.goToOperationHistory();
        transferPage.waitOperationHistoryLoading();
        checkOperationHistoryData("Current", "Bonuses", value); // check operation history

        moneyPage.goToTransfer();
        value = makeRandomValue(); // generate random number
        transferPage.clickBonusesAccordion(); // start sending from bonuses to salary
        transferPage.clickOnFromBonusesToSalaryRadioButton();
        transferPage.enterAmountFromBonuses(value);
        transferPage.clickBonusTransfer();
        moneyPage.goToOperationHistory();
        transferPage.waitOperationHistoryLoading();
        checkOperationHistoryData("Withdraw", "Bonuses", value); // check operation history

        moneyPage.goToTransfer();
        value = makeRandomValue(); // generate random number
        transferPage.clickSalaryAccordion(); // start sending from salary to current
        transferPage.clickOnFromSalaryToCurrantRadioButton();
        transferPage.enterAmountFromSalary(value);
        transferPage.clickSalaryTransfer();
        moneyPage.goToOperationHistory();
        transferPage.waitOperationHistoryLoading();
        checkOperationHistoryData("Current", "Salary", value);



        System.out.println("Transfer Test успешно пройден");
    }

    public String makeRandomValue(){
        Random random = new Random();
        float f = random.nextFloat();
        f = f * 1000;
        int a = (int)Math.round(f);
        f = (float)a/100;
        return Float.toString(f);
    }
    public void checkOperationHistoryData(String accountType, String sendAccountType, String value){
        TransferPage transferPage = new TransferPage(driver, wait);
        assertEquals(transferPage.getTitle(), "Operations");
        assertEquals(transferPage.getOperationType(), "Transfer to "+accountType);
        assertEquals(transferPage.getOperationSender(), "Me, "+sendAccountType);
        assertEquals(transferPage.getOperationAmount(), value);
        assertEquals(transferPage.getOperationStatus(), "Sent");

    }

}
