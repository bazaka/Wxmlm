package FunctionalTests.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

// * Created for W-xmlm by Fill on 12.01.2015.
public class PurchasesPage extends ProductsFamilyPage {
    public PurchasesPage(WebDriver driver, WebDriverWait wait) {super(driver, wait);}
    public static final By pageHeader = By.xpath("//div[@class='header']/h3[contains(text(), 'Purchases')]");
    public static final By appDownloadButton = By.xpath("//a[@href='/products/installer/main/downloader/']");
    public static final By myPurchasesTab = By.xpath("//a[@href='#tab-my-purchases']");
    public static final By myPurchasesTable = By.xpath("//table[@id='datatable-purchases']");
    public static final By lastPurchasePriceCell = By.xpath("//table[@id='datatable-purchases']/tbody/tr[1]/td[3]");
    public static final By myNetworkPurchasesTab = By.xpath("//a[@href='#tab-my-net-purchases']");
    public static final By totalData = By.xpath("//div[@class='total-data']");
    public static final By netPurchasesTable = By.xpath("//table[@id='datatable-net-purchases']");
    public static final By myITProductsTab = By.xpath("//a[@href='#tab-my-it-products']");

    public static final By myITProducts = By.xpath("//a[@href='#tab-my-it-products']");
    public static final By table = By.id("datatable-it-purchase");
    public static final By trialLogins = By.xpath("//table[@id='datatable-it-purchase']/tbody//td[contains(text(), '@kairosplanet.com')]");

    public void waitForPageLoading(){
        wait.until(ExpectedConditions.presenceOfElementLocated(myITProducts));
    }

    public String getLastPurchasePrice(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastPurchasePriceCell));
        return driver.findElement(lastPurchasePriceCell).getText();
    }
    public void goItProductsTable(){
        driver.findElement(myITProducts).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(table));
    }
    public String[] getTrialTableLogins(){
        List<WebElement> logins = driver.findElements(trialLogins);
        //записать последний логин в таблице как первый элемент в массиве trials и тд
        String[] trials = new String[logins.size()];

        int count = logins.size()-1;
        for (int i=0; i<logins.size(); i++){
            trials[i]=logins.get(count).getText();
            count--;
        }
        return trials;
    }

}
