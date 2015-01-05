package UsedByAll;

import com.Ostermiller.util.CSVParser;
import com.Ostermiller.util.LabeledCSVParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 12/26/2014.
 */
public class CsvProfileDataReader {
    public ProfileData[] getProfileDataFromFile(String myFile)
    {

        //Подготавливаемся к чтению файла - объявляем списки данных, из которых будут составлены объекты тестовых пользователей
        File file1 = new File(myFile);
        List<String> userFullNames = new ArrayList<String>();
        List<String> genders = new ArrayList<String>();
        List<String> birthDates = new ArrayList<String>();
        List<String> phoneNumbers = new ArrayList<String>();
        List<String> addresses = new ArrayList<String>();
        List<String> citizens = new ArrayList<String>();
        List<String> passportSerieses = new ArrayList<String>();
        List<String> passportNums = new ArrayList<String>();
        List<String> issueds = new ArrayList<String>();
        List<String> issuedDates = new ArrayList<String>();
        List<String> bNames = new ArrayList<String>();
        List<String> bAddresses = new ArrayList<String>();
        List<String> bankNames = new ArrayList<String>();
        List<String> bankAddresses = new ArrayList<String>();
        List<String> ibans = new ArrayList<String>();
        List<String> swifts = new ArrayList<String>();
        List<String> epids = new ArrayList<String>();

        //Читаем .csv-файл и заполняем списки данных значениями из файла. В списки попадают данные только тех пользователей, которые в поле "useInTest" имеют значение "+"
        try {
            LabeledCSVParser parser = new LabeledCSVParser(new CSVParser(new InputStreamReader(new FileInputStream(file1))));
            while (parser.getLine() != null) {
                userFullNames.add(parser.getValueByLabel("userFullName"));
                genders.add(parser.getValueByLabel("gender"));
                birthDates.add(parser.getValueByLabel("birthDate"));
                phoneNumbers.add(parser.getValueByLabel("phoneNumber"));
                addresses.add(parser.getValueByLabel("address"));
                citizens.add(parser.getValueByLabel("citizen"));
                passportSerieses.add(parser.getValueByLabel("passportSeries"));
                passportNums.add(parser.getValueByLabel("passportNum"));
                issueds.add(parser.getValueByLabel("issued"));
                issuedDates.add(parser.getValueByLabel("issuedDate"));
                bNames.add(parser.getValueByLabel("bName"));
                bAddresses.add(parser.getValueByLabel("bAddress"));
                bankNames.add(parser.getValueByLabel("bankName"));
                bankAddresses.add(parser.getValueByLabel("bankAddress"));
                ibans.add(parser.getValueByLabel("iban"));
                swifts.add(parser.getValueByLabel("swift"));
                epids.add(parser.getValueByLabel("epid"));
            }
            // Создаем по спискам данных массив тестовых пользователей
            String[] userFullNamesArray = userFullNames.toArray(new String[userFullNames.size()]);
            String[] gendersArray = genders.toArray(new String[genders.size()]);
            String[] birthDatesArray = birthDates.toArray(new String[birthDates.size()]);
            String[] phoneNumbersArray = phoneNumbers.toArray(new String[phoneNumbers.size()]);
            String[] addressesArray = addresses.toArray(new String[addresses.size()]);
            String[] citizensArray = citizens.toArray(new String[citizens.size()]);
            String[] passportSeriesesArray = passportSerieses.toArray(new String[passportSerieses.size()]);
            String[] passportNumsArray = passportNums.toArray(new String[passportNums.size()]);
            String[] issuedsArray = issueds.toArray(new String[issueds.size()]);
            String[] issuedDatesArray = issuedDates.toArray(new String[issuedDates.size()]);
            String[] bNamesArray = bNames.toArray(new String[bNames.size()]);
            String[] bAddressesArray = bAddresses.toArray(new String[bAddresses.size()]);
            String[] bankNamesArray = bankNames.toArray(new String[bankNames.size()]);
            String[] bankAddressesArray = bankAddresses.toArray(new String[bankAddresses.size()]);
            String[] ibansArray = ibans.toArray(new String[ibans.size()]);
            String[] swiftsArray = swifts.toArray(new String[swifts.size()]);
            String[] epidsArray = epids.toArray(new String[epids.size()]);

            ProfileData[] profileData = new ProfileData[userFullNames.size()];
            for (int i = 0; i < profileData.length; i++) {
                profileData[i] = new ProfileData();
                profileData[i].setUserFullName(userFullNamesArray[i]);
                profileData[i].setGender(gendersArray[i]);
                profileData[i].setBirthDate(birthDatesArray[i]);
                profileData[i].setPhoneNumber(phoneNumbersArray[i]);
                profileData[i].setAddress(addressesArray[i]);
                profileData[i].setCitizen(citizensArray[i]);
                profileData[i].setPassportSeries(passportSeriesesArray[i]);
                profileData[i].setPassportNum(passportNumsArray[i]);
                profileData[i].setIssued(issuedsArray[i]);
                profileData[i].setIssuedDate(issuedDatesArray[i]);
                profileData[i].setBName(bNamesArray[i]);
                profileData[i].setBAddress(bAddressesArray[i]);
                profileData[i].setBankName(bankNamesArray[i]);
                profileData[i].setBankAddress(bankAddressesArray[i]);
                profileData[i].setIban(ibansArray[i]);
                profileData[i].setSwift(swiftsArray[i]);
                profileData[i].setEpid(epidsArray[i]);
            }

            return profileData;


        } catch (Exception e) {
            System.out.println("File not found. Exception: " + e);
            return null;
        }
    }
}
