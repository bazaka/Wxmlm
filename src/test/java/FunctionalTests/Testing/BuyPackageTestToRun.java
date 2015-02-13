package FunctionalTests.Testing;

import FunctionalTests.Pages.*;
import UsedByAll.CsvUsersReader;
import UsedByAll.TestUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.io.IOException;
import java.util.Collection;
import static org.junit.Assert.assertTrue;

// * Created for W-xmlm by Fill on 05.01.2015.

@RunWith(value = Parameterized.class)
public class BuyPackageTestToRun extends BaseNewTest {
    private TestUser testUser;

    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_BuyPackageTest(");
    }

    public BuyPackageTestToRun(TestUser user){
        this.testUser = user;
    }

    @Test
    public void buyPackageTest() throws IOException {

        //Объявляем страницы, которые будут использоваться в тесте
        AuthorizedUserPage authorizedUserPage = new AuthorizedUserPage(driver, wait);
        InvestmentPackagesPage investmentPackagesPage = new InvestmentPackagesPage(driver, wait);
        PackageCartPage packageCartPage = new PackageCartPage(driver, wait);
        PurchasesPage purchasesPage = new PurchasesPage(driver, wait);
        AccountsPage accountsPage = new AccountsPage(driver,wait);
        OperationHistoryPage operationHistoryPage = new OperationHistoryPage(driver, wait);
        driver.manage().window().maximize();

        //Авторизируемся
        LogInPage loginPage = new LogInPage(driver);
        loginPage.open();
        assertTrue("Page not opened", loginPage.isOpened());
        loginPage.goLogin(testUser);
        Assert.assertEquals(loginPage.getTitle(), "KairosNet");

        //Переходим на страницу Инвест-пакетов
        authorizedUserPage.goProducts();

        //Переходим в корзину
        investmentPackagesPage.clickFirstActiveBuyButton();

        //В корзине запоминаем цену, сумму к оплате и кликаем купить, после чего ждем сообщения о успешной покупке
        String price = packageCartPage.getPrice();
        String paymentAmount = packageCartPage.getPaymentAmount();
        packageCartPage.clickBuyButton();
        packageCartPage.waitForSuccessMessage();

        //Переходим в "Мои покупки", сравниваем цены
        packageCartPage.goToPurchases();
        assertTrue("Price of last purchase is different from my purchase. Maybe my purchase item's not shown in purchases table", price.equals(purchasesPage.getLastPurchasePrice()));

        //Переходим в "Мои операции", проверяем данные последней операции
        packageCartPage.goMoney();
        accountsPage.goToOperationHistory();
        assertTrue("Type of last operation is different from \"Buy product\". Maybe my operation item's not shown in purchases table", "Buy product".equals(operationHistoryPage.getLastOperationType()));
        assertTrue("Sender of last operation is different from \"Me, Current\". Maybe my operation item's not shown in purchases table", "Me, Current".equals(operationHistoryPage.getLastOperationSender()));
        assertTrue("Amount of last operation is different from required fee for my purchase. Maybe my operation item's not shown in purchases table", paymentAmount.equals(operationHistoryPage.getLastOperationAmount()));
        assertTrue("Status of last operation is different from \"Sent\". Maybe my operation item's not shown in purchases table", "Sent".equals(operationHistoryPage.getLastOperationStatus()));
    }
}