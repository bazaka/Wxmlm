package UsedByAll;

import ApiTests.UsedByAll.MakeRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import static org.junit.Assert.assertTrue;

// * Created for W-xmlm by Fill on 25.06.2015.
public class SendMail {
    public static boolean sendMailAction() throws IOException {
        String siteUrl = Config.getConfig().getProtocol() + Config.getConfig().getScheme();
        HttpURLConnection httpCon = MakeRequest.getConnection(siteUrl, "mail/send", "GET");
        InputStream inStrm = httpCon.getInputStream();
        assertTrue("Check response code is 200", httpCon.getResponseCode() == 200);
        InputStreamReader isReader = new InputStreamReader(inStrm);
        BufferedReader br = new BufferedReader(isReader);
        String result = "";
        String line;
        while ((line = br.readLine()) != null) {
            result += line;
        }
        br.close();
        System.out.println(result);
        return (RegionMatch.isStringRegionMatch(result, " emails was sent.") && !RegionMatch.isStringRegionMatch(result, "0 emails was sent."));
    }
}
