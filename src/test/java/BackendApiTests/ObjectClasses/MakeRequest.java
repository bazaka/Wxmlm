package BackendApiTests.ObjectClasses;

import UsedByAll.TestUser;
import org.apache.commons.codec.binary.Base64;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import static BackendApiTests.ObjectClasses.DateForAPI.makeDateTimeString;

public class MakeRequest{
    // Для GET-ов
    public static HttpURLConnection getConnection(String scheme, TestUser user, String urlPart, int valueInDays, String requestMethod) throws IOException {
        // Создаем диапазон и ссылку
        String calBeforeString = makeDateTimeString(Calendar.getInstance(), -valueInDays);
        String calAfterString = makeDateTimeString(Calendar.getInstance(), valueInDays);
        String urlString = "http://" + scheme + urlPart + "?limit=1000000&offset=0&dt_from=" + calBeforeString + "&dt_to=" + calAfterString;

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

    // Для PUT-ов и POST-ов
    public static HttpURLConnection getConnection(String scheme, TestUser user, String urlPart, String requestMethod, String contentType, String accept, boolean setDoOutput) throws IOException {
        String urlString = "http://" + scheme + urlPart;

        // Содзаем HttpUrlConnection
        String authString = user.getEmail() + ":" + user.getPassword1();
        byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
        String authStringEnc = new String(authEncBytes);
        URL url = new URL(urlString);
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        httpCon.setRequestProperty("Authorization", "Basic " + authStringEnc);
        httpCon.setRequestMethod(requestMethod);
        httpCon.setRequestProperty("Content-Type", contentType);
        httpCon.setRequestProperty("Accept", accept);
        httpCon.setDoOutput(setDoOutput);
        return httpCon;
    }

    // Для GET-ов без авторизации
    public static HttpURLConnection getConnection(String scheme, String urlPart, String requestMethod) throws IOException {
        // Создаем диапазон и ссылку
        String urlString = "http://" + scheme + urlPart;

        // Содзаем HttpUrlConnection
        URL url = new URL(urlString);
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        httpCon.setRequestMethod(requestMethod);
        return httpCon;
    }

}
