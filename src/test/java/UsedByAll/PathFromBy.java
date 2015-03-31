package UsedByAll;

import org.openqa.selenium.By;

// * Created for W-xmlm by Fill on 31.03.2015.
public class PathFromBy {
    public static String getXpathFromBy(By by){
        return by.toString().replace("By.xpath: ", "");
    }
}
