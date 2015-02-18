package FunctionalTests.Testing;

import FunctionalTests.Pages.LogInPage;
import FunctionalTests.Pages.ProfilePage;
import UsedByAll.CsvProfileDataReader;
import UsedByAll.CsvUsersReader;
import UsedByAll.ProfileData;
import UsedByAll.TestUser;
import com.google.common.collect.Iterables;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by User on 12/26/2014.
 */
@RunWith(value=Parameterized.class)
public class ChangeProfileDataTestToRun extends BaseClass{

    private String email;
    private String password;
    private String fullName;
    private String gender;
    private String birthDate;
    private String phoneNumber;
    private String address;
    private String citizen;
    private String passportSeries;
    private String passportNum;
    private String issued;
    private String issuedDate;
    private String bName;
    private String bAddress;
    private String bankName;
    private String bankAddress;
    private String iban;
    private String swift;
    private String epid;


    public static Collection testData(){return CsvUsersReader.getDataForTest("_ChangeProfileDataTest(");}

    public static Collection profileData(){return CsvProfileDataReader.getProfileDataForTest();}

    public static Iterable combineData(){
        Iterable allData = Iterables.unmodifiableIterable(Iterables.concat(testData(), profileData()));
        return allData;
    }

    @Parameters
    public static Collection combineDataa(){
        return Arrays.asList(combineData());
    }
    /*
    @Parameters
    public static Collection combineData(){
        Iterable allData = Iterables.unmodifiableIterable(Iterables.concat(testData(), profileData()));
        return Arrays.asList(allData);
    }*/

    public ChangeProfileDataTestToRun(TestUser testUser, ProfileData profileData){
        this.email=testUser.getEmail();
        this.password=testUser.getPassword1();
        this.fullName=profileData.getUserFullName();
        this.gender=profileData.getGender();
        this.birthDate=profileData.getBirthDate();
        this.phoneNumber=profileData.getPhoneNumber();
        this.address=profileData.getAddress();
        this.citizen=profileData.getCitizen();
        this.passportSeries=profileData.getPassportSeries();
        this.passportNum=profileData.getPassportNum();
        this.issued=profileData.getIssued();
        this.issuedDate=profileData.getIssuedDate();
        this.bName=profileData.getBName();
        this.bAddress=profileData.getBAddress();
        this.bankName=profileData.getBankName();
        this.bankAddress=profileData.getBankAddress();
        this.iban=profileData.getIban();
        this.swift=profileData.getSwift();
        this.epid=profileData.getEpid();
    }

    @Test
    public void changeProfileDataTestToRun(){
        LogInPage logInPage = new LogInPage(driver, wait);
        ProfilePage profilePage = new ProfilePage(driver, wait);
        ProfileData[] profileData = new CsvProfileDataReader().getProfileDataFromFile("src/ProfileData.csv");

        logInPage.open();
        assertTrue("Page not opened", logInPage.isOpened());
        logInPage.goLogin(email, password);
        //(String email, String pass){
        assertEquals(logInPage.getTitle(), "KairosNet");

        profilePage.goProfilePage(); //зайти в Профіль
        profilePage.editInformationTab(fullName, gender, birthDate, phoneNumber, address); // редагування інформації, обновити сторінку, зайти в профіль

        assertEquals("Full name not changed", profilePage.getFullName(), fullName);
        assertEquals("Gender not changed",profilePage.getGender() ,gender);
        assertEquals("Birth date not changed",profilePage.getBirthDate() ,birthDate);
        assertEquals("Phone number not changed",profilePage.getPhone() ,phoneNumber);
        assertEquals("Address not changed",profilePage.getAddress() ,address);

        profilePage.editDocumentsTab(citizen, passportSeries, passportNum, issuedDate, issued);

        assertEquals("Citizen not changed", profilePage.getCitizen(), citizen);
        assertEquals("Passport series not changed", profilePage.getPassportSeries(), passportSeries);
        assertEquals("Passport number not changed", profilePage.getPassportNumber(), passportNum);
        assertEquals("Issued not changed", profilePage.getIssued(), issuedDate);
        assertEquals("Issued date not changed", profilePage.getIssuedDate(), issued);

        profilePage.editBankTab(bName, bAddress, bankName, bankAddress, iban, swift, epid);


        assertEquals("Bank user name not changed", profilePage.getBankUserName() , bName);
        assertEquals("Bank user address not changed", profilePage.getBankUserAddress() , bAddress);
        assertEquals("Bank name not changed", profilePage.getBankName() , bankName);
        assertEquals("Bank address not changed", profilePage.getBankAddress() , bankAddress);
        assertEquals("Iban not changed", profilePage.getIban() , iban);
        assertEquals("Swift code not changed", profilePage.getSwift() , swift);
        assertEquals("Epid code not changed", profilePage.getEpid() , epid);
        System.out.println("Change Profile Data Test успешно пройден");
    }
}
