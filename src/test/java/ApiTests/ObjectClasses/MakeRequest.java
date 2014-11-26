package ApiTests.ObjectClasses;

import UsedByAll.TestUser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by User on 11/26/2014.
 */
public class MakeRequest{
    public static String request(String scheme, TestUser User, String url, int value){

        Calendar calBefore = Calendar.getInstance();
        Calendar calAfter = Calendar.getInstance();
        calBefore.add(Calendar.DATE, -value);
        calAfter.add(Calendar.DATE, value);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        return("http://" + User.getEmail() + ":" + User.getPassword1() + "@" + scheme + url + "?limit=10&offset=0&dt_from=" + dateFormat.format(calBefore.getTime()) + "T" + timeFormat.format(calBefore.getTime()) + "&dt_to=" + dateFormat.format(calAfter.getTime()) + "T" + timeFormat.format(calAfter.getTime()));

        //return request;

    }
}
