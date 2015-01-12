package FunctionalTests.Testing.SingleTest;

import FunctionalTests.Pages.AuthorizedUserPage;
import FunctionalTests.Pages.InvestmentPackagesPage;
import FunctionalTests.Pages.LogInPage;
import FunctionalTests.Pages.PackageCartPage;
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

        loginPage.open();
        assertTrue("Page not opened", loginPage.isOpened());

        loginPage.goLogin(testUser);
        Assert.assertEquals(loginPage.getTitle(), "KairosNet"); // логінимось
        wait.until(ExpectedConditions.visibilityOfElementLocated(AuthorizedUserPage.headNav));
        wait.until(ExpectedConditions.visibilityOfElementLocated(AuthorizedUserPage.products));

        driver.findElement(AuthorizedUserPage.products).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(InvestmentPackagesPage.firstActiveBuyButton));
        System.out.println("Вижу активную кнопку \"Buy\"");
        driver.findElement(InvestmentPackagesPage.firstActiveBuyButton).click();
        System.out.println("Кликнул активную кнопку \"Buy\"");
        System.out.println("Price: " + PackageCartPage.getPrice(driver));
        System.out.println("required fee to increase: " + PackageCartPage.getRequiredFeeToIncrease(driver));

    }
    @After
    public void tearDown(){
        if(driver!=null)
            driver.quit();
    }

}
