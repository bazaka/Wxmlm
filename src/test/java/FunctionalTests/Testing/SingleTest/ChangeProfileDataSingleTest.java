package FunctionalTests.Testing.SingleTest;

import FunctionalTests.Pages.LogInPage;
import FunctionalTests.Pages.ProfilePage;
import FunctionalTests.Testing.ChangeProfileDataTest;
import UsedByAll.ProfileData;
import UsedByAll.TestUser;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by User on 12/26/2014.
 */
public class ChangeProfileDataSingleTest extends ChangeProfileDataTest {
    @Test
    public void changeProfileDataSingleTest(TestUser testUser, ProfileData profileData){
        LogInPage loginPage = new LogInPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        loginPage.open();
        assertTrue("Page not opened", loginPage.isOpened());
        loginPage.goLogin(testUser);
        assertEquals(loginPage.getTitle(), "KairosNet");

        profilePage.goProfilePage(); //зайти в Профіль
        profilePage.editInformationTab(profileData); // редагування інформації, обновити сторінку, зайти в профіль

        assertEquals("Full name not changed", profilePage.getFullName(), profileData.getUserFullName());
        assertEquals("Gender not changed",profilePage.getGender() ,profileData.getGender());
        assertEquals("Birth date not changed",profilePage.getBirthDate() ,profileData.getBirthDate());
        assertEquals("Phone number not changed",profilePage.getPhone() ,profileData.getPhoneNumber());
        assertEquals("Address not changed",profilePage.getAddress() ,profileData.getAddress());

        profilePage.editDocumentsTab(profileData);
        assertEquals("Citizen not changed", profilePage.getCitizen(), profileData.getCitizen());
        assertEquals("Passport series not changed", profilePage.getPassportSeries(), profileData.getPassportSeries());
        assertEquals("Passport number not changed", profilePage.getPassportNumber(), profileData.getPassportNum());
        assertEquals("Issued not changed", profilePage.getIssued(), profileData.getIssued());
        assertEquals("Issued date not changed", profilePage.getIssuedDate(), profileData.getIssuedDate());

        profilePage.editBankTab(profileData);
        assertEquals("Bank user name not changed", profilePage.getBankUserName() , profileData.getBName());
        assertEquals("Bank user adress not changed", profilePage.getBankUserAddress() , profileData.getBAddress());
        assertEquals("Bank name not changed", profilePage.getBankName() , profileData.getBankName());
        assertEquals("Bank address not changed", profilePage.getBankAddress() , profileData.getBankAddress());
        assertEquals("Iban not changed", profilePage.getIban() , profileData.getIban());
        assertEquals("Swift code not changed", profilePage.getSwift() , profileData.getSwift());
        assertEquals("Epid code not changed", profilePage.getEpid() , profileData.getEpid());

    }
}
