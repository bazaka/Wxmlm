package FunctionalTests.Testing;

import FunctionalTests.Testing.SingleTest.BuyPackageSingleTest;
import UsedByAll.CsvUsersReader;
import UsedByAll.RegionMatch;
import UsedByAll.TestUser;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

// * Created for W-xmlm by Fill on 05.01.2015.
public class BuyPackageTest {
    @Test
    public void buyPackageTest(){
        BuyPackageSingleTest singleTest = new BuyPackageSingleTest();
        TestUser[] testUser = new CsvUsersReader().getUsersFromFile("src/Users.csv");

        assertNotEquals("Нет пользователей для тестирования. Закрываюсь", testUser.length, 0);
        System.out.println("Количество пользователей для тестирования: "+ testUser.length);

        for (TestUser aTestUser : testUser) {
            if (RegionMatch.IsStringRegionMatch(aTestUser.getUseInTest(), "_BuyPackageTest(")) {
                try {
                    singleTest.buyPackageSingleTest(aTestUser);
                    System.out.println("BuyPackageTest Test успешно пройден");
                } catch (Exception e) {
                    e.printStackTrace();
                    singleTest.tearDown();
                    assertTrue("There is an exception", false);
                } catch (AssertionError e) {
                    e.printStackTrace();
                    singleTest.tearDown();
                    assertTrue("Assertion is failed", false);
                }
                singleTest.tearDown();
            }
        }
    }
}
