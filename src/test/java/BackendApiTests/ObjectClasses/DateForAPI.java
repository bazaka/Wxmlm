package BackendApiTests.ObjectClasses;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

// * Created for W-xmlm by Fill on 01.12.2014.
public class DateForAPI {
    public static String makeDateTimeString(Calendar dt, int moveInDays){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        dt.add(Calendar.DATE, moveInDays);
        return (dateFormat.format(dt.getTime()) + "T" + timeFormat.format(dt.getTime()));
    }
}
