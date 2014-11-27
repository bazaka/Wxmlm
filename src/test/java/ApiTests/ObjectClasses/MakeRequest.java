package ApiTests.ObjectClasses;

import UsedByAll.TestUser;
import org.apache.commons.codec.binary.Base64;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by User on 11/26/2014.
 */
public class MakeRequest{
    public static HttpURLConnection getConnection(String scheme, TestUser user, String urlPart, int valueInDays, String requestMethod) throws IOException {
        // Создаем диапазон и ссылку
        Calendar calBefore = Calendar.getInstance();
        Calendar calAfter = Calendar.getInstance();
        calBefore.add(Calendar.DATE, -valueInDays);
        calAfter.add(Calendar.DATE, valueInDays);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String urlString = "http://" + scheme + urlPart + "?limit=10&offset=0&dt_from=" + dateFormat.format(calBefore.getTime()) + "T" + timeFormat.format(calBefore.getTime()) + "&dt_to=" + dateFormat.format(calAfter.getTime()) + "T" + timeFormat.format(calAfter.getTime());

        // Содзаем HttpUrlConnection
        String authString = user.getEmail() + ":" + user.getPassword1();
        byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
        String authStringEnc = new String(authEncBytes);
        URL url = new URL(urlString);
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        httpCon.setRequestProperty("Authorization", "Basic " + authStringEnc);
        httpCon.setRequestMethod(requestMethod);
        return httpCon;
    }
}
