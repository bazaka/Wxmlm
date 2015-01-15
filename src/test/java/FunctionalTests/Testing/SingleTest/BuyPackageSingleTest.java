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
    @Test
    public void buyPackageSingleTest(TestUser testUser) throws IOException {
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver,5);
        LogInPage loginPage = new LogInPage(driver);
        Element element = new Element();

        loginPage.open();
        assertTrue("Page not opened", loginPage.isOpened());

        loginPage.goLogin(testUser);
        Assert.assertEquals(loginPage.getTitle(), "KairosNet"); // логінимось
        wait.until(ExpectedConditions.visibilityOfElementLocated(InvestmentPackagesPage.products));
        driver.findElement(InvestmentPackagesPage.products).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(InvestmentPackagesPage.firstActiveBuyButton));
        driver.findElement(InvestmentPackagesPage.firstActiveBuyButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(PackageCartPage.buyButtonElement));
        String packageName = PackageCartPage.packageNameElement.toString();
        System.out.println(packageName);
        String paymentAmount = PackageCartPage.getPrice(driver);
        if (Element.isElementExists(driver, PackageCartPage.requiredFeeToIncreaseElement))
        {
            paymentAmount = PackageCartPage.getRequiredFeeToIncrease(driver);
        }
        System.out.println("Payment amount: " + paymentAmount);

/*
        driver.findElement(PackageCartPage.buyButtonElement).click();
        System.out.println("Инициировал покупку пакета следующего уровня");
        wait.until(ExpectedConditions.visibilityOfElementLocated(PackageCartPage.successMessage));
*/
        driver.findElement(PackageCartPage.purchasesItem).click();
        System.out.println("Перешел в мои покупки");
        wait.until(ExpectedConditions.visibilityOfElementLocated(PackageCartPage.buyButtonElement));


    }
    @After
    public void tearDown(){
        if(driver!=null)
            driver.quit();
    }

}
