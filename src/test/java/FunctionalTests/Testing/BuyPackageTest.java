package FunctionalTests.Testing;

import FunctionalTests.Testing.SingleTest.BuyPackageSingleTest;
import UsedByAll.CsvUsersReader;
import UsedByAll.RegionMatch;
import UsedByAll.TestUser;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static org.junit.Assert.assertNotEquals;

// * Created for W-xmlm by Fill on 05.01.2015.
public class BuyPackageTest {
    @Test
    public void buyPackageTest(){
        BuyPackageSingleTest singleTest = new BuyPackageSingleTest();
        TestUser[] testUser = new CsvUsersReader().getUsersFromFile("src/Users.csv");

        assertNotEquals("Нет пользователей для тестирования. Закрываюсь", testUser.length, 0);
        System.out.println("Количество пользователей для тестирования: "+ testUser.length);

        for (int i = 0; i < testUser.length; i++) {
            if(RegionMatch.IsStringRegionMatch(testUser[i].getUseInTest(), "_BuyPackageTest("))
            {
                try {
                    singleTest.buyPackageSingleTest(testUser[i]);
                } catch (IOException e) {
                    e.printStackTrace();
                    singleTest.tearDown();
                }
            }
        }
        System.out.println("BuyPackageTest Test успешно пройден");
    }
}
