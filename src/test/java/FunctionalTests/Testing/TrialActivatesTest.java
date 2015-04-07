package FunctionalTests.Testing;

import FunctionalTests.Pages.*;
import UsedByAll.Config;
import UsedByAll.CsvUsersReader;
import UsedByAll.MakeRandomString;
import UsedByAll.TestUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by User on 3/31/2015.
 */
@RunWith(value = Parameterized.class)
public class TrialActivatesTest extends BaseTest {
    String email;
    String password;
    int trialAmount = 4; //количество триалов
    String domain = "@kairosplanet.com"; //домен

    @Parameterized.Parameters
    public static Collection testData(){return CsvUsersReader.getDataForTest("_TrialActivatesTest(");}

    public TrialActivatesTest(TestUser testUser){
        this.email=testUser.getEmail();
        this.password=testUser.getPassword1();
    }
    @Test
    public void TrialActivates(){
        String siteUrl = Config.getConfig().getProtocol() + Config.getConfig().getScheme();
        LogInPage loginPage = new LogInPage(driver, wait);
        AuthorizedUserPage userPage = new AuthorizedUserPage(driver, wait);
        ProductsFamilyPage productPage = new ProductsFamilyPage(driver, wait);
        ItProductsPage itProductsPage = new ItProductsPage(driver, wait);
        MakeRandomString randomString = new MakeRandomString();


        loginPage.open();
        loginPage.goLogin(email, password);
        assertEquals(loginPage.getTitle(), "KairosNet");
        userPage.goProducts();
        productPage.waitFroPageLoading();
        productPage.goItProducts();
        itProductsPage.waitForPageLoad();

        //проверяем, что у юзера есть возможность активировать все 4 триала

        assertTrue("This user must have "+trialAmount+" unactive trials to continue this test", itProductsPage.getCountOfNonActiveTrials(trialAmount));

        //активируем все 4 продукта в цикле
        String[] trialLogins = new String[trialAmount]; //массив случайно сгенерированных имен триалов для подальшей проверки
        for(int i=0; i<trialAmount; i++){
            itProductsPage.clickActivateButton();
            trialLogins[i]=randomString.generateString(6); //длина рандомной строки = 6
            itProductsPage.enterTrialLogin(trialLogins[i]);
            //проверка корректности домена
            assertEquals("Incorrect domain. Domain is: "+itProductsPage.getDomainName(), itProductsPage.getDomainName(), domain);
            itProductsPage.createTrial();
        }
        productPage.goToPurchases();
        PurchasesPage purchasesPage = new PurchasesPage(driver, wait);
        purchasesPage.waitForPageLoading();
        purchasesPage.goItProductsTable();
        for(int i=0; i<trialAmount; i++){
            assertEquals("Not same activated trial names: "+trialLogins[i]+"@kairosplanet.com and "+purchasesPage.getTrialTableLogins()[i], trialLogins[i]+"@kairosplanet.com",purchasesPage.getTrialTableLogins()[i]) ;
        }
       /* for(int i=0; i<trialAmount; i++){
            System.out.println("triallogins" +purchasesPage.getTrialTableLogins()[i]+" ");
        }*/

    }
}
