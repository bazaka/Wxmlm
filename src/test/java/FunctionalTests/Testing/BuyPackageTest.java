package FunctionalTests.Testing;

import FunctionalTests.Testing.SingleTest.BuyPackageSingleTest;
import UsedByAll.CsvUsersReader;
import UsedByAll.RegionMatch;
import UsedByAll.TestUser;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertNotEquals;

// * Created for W-xmlm by Fill on 05.01.2015.
public class BuyPackageTest {
/*
    @Test
    public void changeMailTest(){
        BuyPackageSingleTest singleTest = new BuyPackageSingleTest();
        TestUser[] testUser = new CsvUsersReader().getUsersFromFile("src/Users.csv");

        assertNotEquals("Нет пользователей для тестирования. Закрываюсь", testUser.length, 0);
        System.out.println("Количество пользователей для тестирования: "+ testUser.length);

        for (int i = 0; i < testUser.length; i++) {
            if(RegionMatch.IsStringRegionMatch(testUser[i].getUseInTest(), "_BuyPackageTest("))
            {
                try {
                    singleTest.(testUser[i]);
                } catch (IOException e) {
                    e.printStackTrace();
                    driver.quit();
                } catch (MessagingException e) {
                    e.printStackTrace();
                    driver.quit();
                }
                if(driver!=null)
                    driver.quit();
            }
        }
        System.out.println("ChangeMail Test успешно пройден");
    }

*/
}
