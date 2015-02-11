package FunctionalTests.Testing.SingleTest;

import FunctionalTests.Pages.AuthorizedUserPage;
import FunctionalTests.Pages.InvestmentPackagesPage;
import FunctionalTests.Pages.LogInPage;
import FunctionalTests.Pages.PackageCartPage;
import UsedByAll.Element;
import UsedByAll.TestUser;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.junit.Assert;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;
import java.io.IOException;

// * Created for W-xmlm by Fill on 05.01.2015.
public class BuyPackageSingleTest {
    WebDriver driver = new FirefoxDriver();
    WebDriverWait wait = new WebDriverWait(driver,5);
    @Test
    public void buyPackageSingleTest(TestUser testUser) throws IOException {
        AuthorizedUserPage authorizedUserPage = new AuthorizedUserPage(driver, wait);
        InvestmentPackagesPage investmentPackagesPage = new InvestmentPackagesPage(driver, wait);
        PackageCartPage packageCartPage = new PackageCartPage(driver, wait);
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

        //В корзине запоминаем сумму к оплате и кликаем купить, после чего ждем сообщения о успешной покупке
        String paymentAmount = packageCartPage.getPaymentAmount();
        packageCartPage.clickBuyButton();
        packageCartPage.waitForSuccessMessage();

        //Перехожу в "Мои покупки"
        packageCartPage.goToPurchases();

    }
    @After
    public void tearDown(){
        if(driver!=null)
            driver.quit();
    }

}
