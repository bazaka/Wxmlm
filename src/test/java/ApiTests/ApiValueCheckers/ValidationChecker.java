package ApiTests.ApiValueCheckers;

import org.apache.commons.validator.routines.EmailValidator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ValidationChecker {
    // Проверка что поле содержит Id
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
    public boolean checkNotNull(String str){return (!str.equals(null));
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
    public boolean checkDateOrNull(Object dTime){
        if (dTime.equals(null))
            return true;
        else{
            String dateTime = dTime.toString();
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
    public boolean checkStringOrNull(Object str) {
        if (str == null || str.equals(null))
            return true;
        else {
            String strStr = str.toString();
            return strStr.length() >= 0;
        }
    }
    //public boolean checkMoreOrNull(int moreNull) { return (moreNull >=0); }
    public boolean checkGenderId(int genderId){return (genderId == 1 || genderId == 2);}
    public boolean checkUserStatusId(int userStatusId) {return (userStatusId>=1 && userStatusId<=4);}
    public boolean checkOperationStatusId(int statusId) {return (statusId>=1 && statusId<=5);}
    public boolean checkWithdrawStatusId(int statusId) {return (statusId>=1 && statusId<=3);}
    public boolean checkOperationTypeId(int typeId) {return (typeId>=1 && typeId<=9);}
    public boolean checkCareer(int careerId) {return (careerId>=0 && careerId<=11);}
    public boolean checkMoreOrNullId (Object someId){
        if (someId.equals(null))
            return true;
        else{
            String strId = someId.toString();
            int IntId = (Integer.valueOf(strId));
            return (IntId >= 0);
        }
    }
    public boolean checkEmail (String email) {
        return (EmailValidator.getInstance().isValid(email));
    }
    // Проверка что поле содержит необязательный Id
    public boolean checkIdOrNull(Object id){
        return (id.equals(null) || checkIdValue(Integer.valueOf(id.toString())));
    }
    public boolean checkAnotherEmail (Object anotherEmail) {
        if (anotherEmail.equals(null))
            return true;
        else{
            String mailStr = anotherEmail.toString();
            return (mailStr.length()>0);
        }
        //return anotherEmail.equals(null) || (EmailValidator.getInstance().isValid(anotherEmail.toString()));
    }
    public boolean checkProductId (int productId){
        return(productId == 1 || productId == 2 || productId == 3 || productId == 4);
    }
    public boolean checkMoneyFormat(String value){
        int i = Integer.valueOf(value);
        if(i == Integer.parseInt(value)) {
            return true;
        }
        else {
            String[] parts = value.split(".");
            return (Integer.valueOf(parts[0]) > 0 && Integer.valueOf(parts[1]) > 0 && parts[1].length() <= 2);
        }
    }




}
