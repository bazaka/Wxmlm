package FunctionalTests.Pages;

import UsedByAll.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by User on 12/1/2014.
 */
class BasePage {  //abstract class
    protected String url = Config.getConfig().getProtocol() + Config.getConfig().getScheme();
    protected WebDriver driver;
    protected WebDriverWait wait;


    public BasePage(WebDriver driver, WebDriverWait wait){
        this.driver=driver; this.wait=wait;
    }
    public void open(){
        driver.get(url);
    }
    public boolean isOpened(){
        return driver.getCurrentUrl().equals(url);
    }
    public boolean isOpened(String url){
        return driver.getCurrentUrl().equals(url);
    }
}