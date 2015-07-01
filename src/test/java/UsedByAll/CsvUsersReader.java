package UsedByAll;

import com.Ostermiller.util.CSVParser;
import com.Ostermiller.util.LabeledCSVParser;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CsvUsersReader {
    public TestUser[] getUsersFromFile(String myFile)
    {
        //Подготавливаемся к чтению файла - объявляем списки данных, из которых будут составлены объекты тестовых пользователей
        File file1 = new File(myFile);
        List<String> emails = new ArrayList<String>();
        List<String> newEmails = new ArrayList<String>();
        List<String> ePasswords = new ArrayList<String>();
        List<String> fullNames = new ArrayList<String>();
        List<String> password1s = new ArrayList<String>();
        List<String> password2s = new ArrayList<String>();
        List<String> newPassword1s = new ArrayList<String>();
        List<String> newPassword2s = new ArrayList<String>();
        List<String> phones = new ArrayList<String>();
        List<String> inviteCodes = new ArrayList<String>();
        List<String> useInTests = new ArrayList<String>();

        //Читаем .csv-файл и заполняем списки данных значениями из файла. В списки попадают данные только тех пользователей, которые в поле "useInTest" имеют значение "+"
        try {
            LabeledCSVParser parser = new LabeledCSVParser(new CSVParser(new InputStreamReader(new FileInputStream(file1))));
            while(parser.getLine() != null) {
                if(!parser.getValueByLabel("useInTest").isEmpty()){
                    emails.add(parser.getValueByLabel("email"));
                    newEmails.add(parser.getValueByLabel("newEmail"));
                    ePasswords.add(parser.getValueByLabel("ePassword"));
                    fullNames.add(parser.getValueByLabel("fullName"));
                    password1s.add(parser.getValueByLabel("password1"));
                    password2s.add(parser.getValueByLabel("password2"));
                    newPassword1s.add(parser.getValueByLabel("newPassword1"));
                    newPassword2s.add(parser.getValueByLabel("newPassword2"));
                    phones.add(parser.getValueByLabel("phone"));
                    inviteCodes.add(parser.getValueByLabel("inviteCode"));
                    useInTests.add(parser.getValueByLabel("useInTest"));
                }
            }

            // Создаем по спискам данных массив тестовых пользователей
            String[] emailsArray = emails.toArray(new String[emails.size()]);
            String[] newEmailsArray = newEmails.toArray(new String[emails.size()]);
            String[] ePasswordsArray = ePasswords.toArray(new String[emails.size()]);
            String[] fullNamesArray = fullNames.toArray(new String[emails.size()]);
            String[] password1sArray = password1s.toArray(new String[emails.size()]);
            String[] password2sArray = password2s.toArray(new String[emails.size()]);
            String[] newPassword1sArray = newPassword1s.toArray(new String[emails.size()]);
            String[] newPassword2sArray = newPassword2s.toArray(new String[emails.size()]);
            String[] phonesArray = phones.toArray(new String[emails.size()]);
            String[] newInviteCodesArray = inviteCodes.toArray(new String[emails.size()]);
            String[] newUseInTestsArray = useInTests.toArray(new String[emails.size()]);
            TestUser[] testUsers = new TestUser[emails.size()];
            for (int i = 0; i < testUsers.length; i++) {
                testUsers[i] = new TestUser();
                testUsers[i].setEmail(emailsArray[i]);
                testUsers[i].setNewEmail(newEmailsArray[i]);
                testUsers[i].setEPassword(ePasswordsArray[i]);
                testUsers[i].setFullName(fullNamesArray[i]);
                testUsers[i].setPassword1(password1sArray[i]);
                testUsers[i].setPassword2(password2sArray[i]);
                testUsers[i].setNewPassword1(newPassword1sArray[i]);
                testUsers[i].setNewPassword2(newPassword2sArray[i]);
                testUsers[i].setPhone(phonesArray[i]);
                testUsers[i].setInviteCode(newInviteCodesArray[i]);
                testUsers[i].setUseInTest(newUseInTestsArray[i]);
            }

            //Отдаем массив тестовых пользователей
            return testUsers;
        }
        catch (Exception e){
            System.out.println("File not found. Exception: " + e);
            return null;
        }
    }
    public static Collection getDataForTest(String testName) {
        TestUser[] testUser = new CsvUsersReader().getUsersFromFile("src/Users.csv");
        int arrLength = 0;
        List<Integer> userIndex = new ArrayList<Integer>();
        for (int i = 0; i < testUser.length; i++) {
            if(RegionMatch.isStringRegionMatch(testUser[i].getUseInTest(), testName)){
                arrLength++;
                userIndex.add(i);
            }
        }
        TestUser testDatas[][] = new TestUser[arrLength][1];
        for (int i = 0; i < arrLength; i++) {
            testDatas[i][0] = testUser[userIndex.get(i)];
        }
        return Arrays.asList(testDatas);
    }
}