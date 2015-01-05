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
        WebDriverWait wait = new WebDriverWait(driver,5);
        LogInPage loginPage = new LogInPage(driver);
        InvestmentPackagesPage investmentPackagesPage = new InvestmentPackagesPage();
        PackageCartPage packageCartPage = new PackageCartPage();
        AuthorizedUserPage authorizedUserPage = new AuthorizedUserPage();

        loginPage.open();
        assertTrue("Page not opened", loginPage.isOpened());

        loginPage.goLogin(testUser);
        Assert.assertEquals(loginPage.getTitle(), "KairosNet"); // логінимось
/*
        wait.until(ExpectedConditions.visibilityOfElementLocated(authorizedUserPage.headNav));
        System.out.println("1");
*/
        wait.until(ExpectedConditions.visibilityOfElementLocated(authorizedUserPage.products));
        System.out.println("2");

        driver.findElement(authorizedUserPage.products).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(InvestmentPackagesPage.firstActiveBuyButton));
        System.out.println("Вижу активную кнопку \"Buy\"");

    }
    @After
    public void tearDown(){
        if(driver!=null)
            driver.quit();
    }

}
