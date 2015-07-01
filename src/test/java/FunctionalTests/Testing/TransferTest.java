package FunctionalTests.Testing;

import FunctionalTests.Pages.*;
import UsedByAll.CsvUsersReader;
import UsedByAll.RandomValue;
import UsedByAll.TestUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Collection;

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
        OperationHistoryPage operationHistoryPage = new OperationHistoryPage(driver, wait);

        logInPage.open();
        logInPage.goLogin(email, password);
        assertEquals(logInPage.getTitle(), "KairosNet");
        userPage.goMoney();
        moneyPage.goToTransfer();
        RandomValue randomValue = new RandomValue();

        String value = randomValue.randomValue(); // generate random number
        transferPage.clickBonusesAccordion(); // start sending from bonusesItem to current
        transferPage.clickOnFromBonusesToCurrentRadioButton();
        transferPage.enterAmountFromBonuses(value);
        transferPage.clickBonusTransfer();
        moneyPage.goToOperationHistory();
        operationHistoryPage.waitOperationHistoryLoading();
        checkOperationHistoryData("Current", "Bonuses", value); // check operation history

        moneyPage.goToTransfer();
        value = randomValue.randomValue(); // generate random number
        transferPage.clickBonusesAccordion(); // start sending from bonusesItem to salary
        transferPage.clickOnFromBonusesToSalaryRadioButton();
        transferPage.enterAmountFromBonuses(value);
        transferPage.clickBonusTransfer();
        moneyPage.goToOperationHistory();
        operationHistoryPage.waitOperationHistoryLoading();
        checkOperationHistoryData("Withdraw", "Bonuses", value); // check operation history

        moneyPage.goToTransfer();
        value = randomValue.randomValue(); // generate random number
        transferPage.clickSalaryAccordion(); // start sending from salary to current
        transferPage.clickOnFromSalaryToCurrantRadioButton();
        transferPage.enterAmountFromSalary(value);
        transferPage.clickSalaryTransfer();
        moneyPage.goToOperationHistory();
        operationHistoryPage.waitOperationHistoryLoading();
        checkOperationHistoryData("Current", "Salary", value);



        System.out.println("Transfer Test успешно пройден");
    }


    public void checkOperationHistoryData(String accountType, String sendAccountType, String value){
        OperationHistoryPage operationHistoryPage = new OperationHistoryPage(driver, wait);

        assertEquals(operationHistoryPage.getTitle(), "Operations");
        assertEquals(operationHistoryPage.getOperationType(), "Transfer to "+accountType);
        assertEquals(operationHistoryPage.getOperationSender(), "Me, "+sendAccountType);
        assertEquals(operationHistoryPage.getOperationAmount(), value);
        assertEquals(operationHistoryPage.getOperationStatus(), "Sent");

    }

}
