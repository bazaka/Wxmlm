package FunctionalTests.Testing;

import FunctionalTests.Pages.LogInPage;
import FunctionalTests.Pages.ProfilePage;
import UsedByAll.CsvProfileDataReader;
import UsedByAll.CsvUsersReader;
import UsedByAll.ProfileData;
import UsedByAll.TestUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Collection;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by User on 12/26/2014.
 */
@RunWith(value=Parameterized.class)
public class ChangeProfileDataTestToRun extends BaseTest {

    private String email;
    private String password;

    @Parameters
    public static Collection testData(){return CsvUsersReader.getDataForTest("_ChangeProfileDataTest(");}

    public ChangeProfileDataTestToRun(TestUser testUser){
        this.email=testUser.getEmail();
        this.password=testUser.getPassword1();

    }

    @Test
    public void changeProfileDataTestToRun(){
        LogInPage logInPage = new LogInPage(driver, wait);
        ProfilePage profilePage = new ProfilePage(driver, wait);
        ProfileData[] profileData = new CsvProfileDataReader().getProfileDataFromFile("src/ProfileData.csv");
       // ProfileData[] profileData = new CsvProfileDataReader().getProfileDataFromFile("src/ProfileData.csv");

        logInPage.open();
        assertTrue("Page not opened", logInPage.isOpened());
        logInPage.goLogin(email, password);
        //(String email, String pass){
        assertEquals(logInPage.getTitle(), "KairosNet");

        profilePage.goProfilePage(); //зайти в Профіль
        profilePage.editInformationTab(profileData[0].getUserFullName(), profileData[0].getGender(), profileData[0].getBirthDate(), profileData[0].getPhoneNumber(), profileData[0].getAddress()); // редагування інформації, обновити сторінку, зайти в профіль

        assertEquals("Full name not changed", profilePage.getFullName(), profileData[0].getUserFullName());
        assertEquals("Gender not changed",profilePage.getGender() ,profileData[0].getGender());
        assertEquals("Birth date not changed",profilePage.getBirthDate() ,profileData[0].getBirthDate());
        assertEquals("Phone number not changed",profilePage.getPhone() ,profileData[0].getPhoneNumber());
        assertEquals("Address not changed",profilePage.getAddress() , profileData[0].getAddress());

        profilePage.editDocumentsTab(profileData[0].getCitizen(), profileData[0].getPassportSeries(), profileData[0].getPassportNum(), profileData[0].getIssuedDate(), profileData[0].getIssued());

        assertEquals("Citizen not changed", profilePage.getCitizen(), profileData[0].getCitizen());
        assertEquals("Passport series not changed", profilePage.getPassportSeries(), profileData[0].getPassportSeries());
        assertEquals("Passport number not changed", profilePage.getPassportNumber(), profileData[0].getPassportNum());
        assertEquals("Issued not changed", profilePage.getIssued(), profileData[0].getIssued());
        assertEquals("Issued date not changed", profilePage.getIssuedDate(), profileData[0].getIssuedDate());

        profilePage.editBankTab(profileData[0].getBName(), profileData[0].getBAddress(), profileData[0].getBankName(), profileData[0].getBankAddress(), profileData[0].getIban(), profileData[0].getSwift(), profileData[0].getEpid());


        assertEquals("Bank user name not changed", profilePage.getBankUserName() , profileData[0].getBName());
        assertEquals("Bank user address not changed", profilePage.getBankUserAddress() , profileData[0].getBAddress());
        assertEquals("Bank name not changed", profilePage.getBankName() , profileData[0].getBankName());
        assertEquals("Bank address not changed", profilePage.getBankAddress() , profileData[0].getBankAddress());
        assertEquals("Iban not changed", profilePage.getIban() , profileData[0].getIban());
        assertEquals("Swift code not changed", profilePage.getSwift() , profileData[0].getSwift());
        assertEquals("Epid code not changed", profilePage.getEpid() ,  profileData[0].getEpid());
        System.out.println("Change Profile Data Test успешно пройден");
    }
}
