package FunctionalTests.Testing;

import FunctionalTests.Pages.*;
import UsedByAll.CsvUsersReader;
import UsedByAll.RandomString;
import UsedByAll.TestUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;

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

        LogInPage loginPage = new LogInPage(driver, wait);
        AuthorizedUserPage userPage = new AuthorizedUserPage(driver, wait);
        ProductsFamilyPage productPage = new ProductsFamilyPage(driver, wait);
        ITProductsPage itProductsPage = new ITProductsPage(driver, wait);


        loginPage.open();
        loginPage.goLogin(email, password);
        assertEquals(loginPage.getTitle(), "KairosNet");
        userPage.goProducts();
        productPage.waitForPageLoading();
        productPage.goItProducts();
        itProductsPage.checkNonActiveTrials();

        //проверяем, что у юзера есть возможность активировать все 4 триала

        assertTrue("This user must have " + trialAmount + " unactive trials to continue this test", itProductsPage.isAllTrialsAvailable(trialAmount));

        //активируем все 4 продукта в цикле
        String[] trialLogins = new String[trialAmount]; //массив случайно сгенерированных имен триалов для подальшей проверки
        for(int i=0; i<trialAmount; i++){
            itProductsPage.clickActivateButton();
            trialLogins[i]= RandomString.generateString(6); //длина рандомной строки = 6
            itProductsPage.enterTrialLogin(trialLogins[i]);
            //проверка корректности домена
            assertEquals("Incorrect domain. Domain is: "+itProductsPage.getDomainName(), itProductsPage.getDomainName(), domain);
            itProductsPage.createTrial();
        }
        productPage.goToPurchases();
        PurchasesPage purchasesPage = new PurchasesPage(driver, wait);
        purchasesPage.waitForPageLoading();
        purchasesPage.goItProductsTable();

        String[] invertLoginList = new String[purchasesPage.getTrialTableLogins().length];
        //переворачиваем массив логинов
        int count = invertLoginList.length-1;
        for(int i=0; i<invertLoginList.length; i++){
            invertLoginList[i]=purchasesPage.getTrialTableLogins()[count];
            count--;
        }

        for(int i=0; i<trialAmount; i++){
            assertEquals("Not same activated trial names: "+trialLogins[i]+"@kairosplanet.com and "+invertLoginList[i], trialLogins[i]+"@kairosplanet.com",invertLoginList[i]) ;
        }
       /* for(int i=0; i<trialAmount; i++){
            System.out.println("triallogins" +purchasesPage.getTrialTableLogins()[i]+" ");
        }*/

    }
}
