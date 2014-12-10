package ApiTests.UsedByAll;

import UsedByAll.RegionMatch;
import org.apache.commons.validator.routines.EmailValidator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ValidationChecker {
    // Null - NotNull
    public static boolean checkStringNotNull(String str){return (str != null && !str.equals(""));}
    public static boolean checkStringOrNull(Object str) {return str == null || str.toString().length() >= 0;}

    // ID
    public static boolean checkIdValue(int id){return (id > 0);}
    public static boolean checkGenderId(int genderId){return (genderId == 1 || genderId == 2);}
    public static boolean checkUserStatusId(int userStatusId) {return (userStatusId>=1 && userStatusId<=4);}
    public static boolean checkOperationStatusId(int statusId) {return (statusId>=1 && statusId<=5);}
    public static boolean checkWithdrawStatusId(int statusId) {return (statusId>=1 && statusId<=3);}
    public static boolean checkProductStatusId(int statusId) {return (statusId>=1 && statusId<=4);}
    public static boolean checkOperationTypeId(int typeId) {return (typeId>=1 && typeId<=9);}
    public static boolean checkProductTypeId(int typeId) {return (typeId>=11);}
    public static boolean checkCareerId(int careerId) {return (careerId>=0 && careerId<=11);}
    public static boolean checkProductId (int productId){return(productId == 1 || productId == 2 || productId == 3 || productId == 4);}
    public static boolean checkIdOrNull(Object id){return (checkStringOrNull(id) || checkIdValue(Integer.valueOf(id.toString())));} // Проверка что поле содержит необязательный Id

    // Date, Time
    public static boolean checkDateTimeString(String dateTime) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            dateFormat.parse(dateTime.split("T")[0]);
            timeFormat.parse(dateTime.split("T")[1]);
            return true; }
        catch (ParseException e) {return false;}
    }
    public static boolean checkDateString(String date) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.parse(date);
            return true; }
        catch (ParseException e) {return false;}
    }

    // Boolean
    public static boolean checkBooleanValue(boolean boo){return true;}

    // Double
    public static boolean checkDoubleValue(double dbl){return  true;}

    // Special
    public static boolean checkAccountNumberValue(String accountNumber){return (accountNumber.length() >= 12);}
    public static boolean checkAccountTypeValue(int accountType){return (accountType == 10 || accountType == 20 || accountType == 30);}
    public static boolean checkMoneyFormat(String amount){return Integer.valueOf(amount) == Integer.parseInt(amount) || (Integer.valueOf(amount.split(".")[0]) > 0 && Integer.valueOf(amount.split(".")[1]) > 0 && amount.split(".")[1].length() <= 2);}
    public static boolean checkEmail (String email) {return (EmailValidator.getInstance().isValid(email));}
    public static boolean checkURLOnDomain(String url, String domain){return RegionMatch.IsStringRegionMatch(url, domain);}
    public static boolean checkPositiveInt(int count){return count >= 0;}
}