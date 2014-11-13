package ApiTests.ApiValueCheckers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ValidationChecker {
    public boolean checkIdValue(int id)
    {
        return (id > 0);
    }
    public boolean checkAccountNumberValue(String accountNumber){
        return (accountNumber.length() >= 12);
    }
    public boolean checkAccountTypeValue(int accountType){
        return (accountType == 10 || accountType == 20 || accountType == 30);
    }
    public boolean checkBooleanValue(boolean boo){
        return true;
    }
    public boolean checkNotNull(String str){
        return (str != null);
    }
    public boolean checkDoubleValue(double dbl) {return  true; }
    public boolean checkDateTimeString(String dateTime) {
        String[] parts = dateTime.split("T");
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            Date date = dateFormat.parse(parts[0]);
            Date time = timeFormat.parse(parts[1]);
            return true; }
        catch (ParseException e) {return false;}
    }
}