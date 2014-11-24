package ApiTests.ApiValueCheckers;

import org.apache.commons.validator.routines.EmailValidator;

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
    public boolean checkNotNull(String str){return (str != null);
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
    public boolean checkStringOrNull(String str) { return (str == null || str.length() > 0 );}//дописати
    //public boolean checkMoreOrNull(int moreNull) { return (moreNull >=0); }
    public boolean checkGenderId(int genderId){return (genderId == 1 || genderId == 2);}
    public boolean checkUserStatusId(int statusId) {return (statusId>=1 && statusId<=4);}
    public boolean checkCareer(int careerId) {return (careerId>=0 && careerId<=11);}
    public boolean checkMoreOrNullId (Object languageId){
        if (languageId.equals(null))
            return true;
        else{
            String langId = languageId.toString();
            int langInt = (Integer.valueOf(langId));
            return (langInt >= 0);


        }
    }
    public boolean checkEmail (String email) {

        return (EmailValidator.getInstance().isValid(email));
    }
    public boolean checkAnotherEmail (Object anotherEmail) {
        if (anotherEmail.equals(null))
           return true;
        else{
          //  boolean valid;// = EmailValidator.getInstance().isValid(anotherEmail.toString());
            return (EmailValidator.getInstance().isValid(anotherEmail.toString()));
        }

     /*  // if (anotherEmail.equals("null"))
        if
            return true;
        else{
            boolean valid = EmailValidator.getInstance().isValid(anotherEmail);
            return valid;
        }*/

    }

}