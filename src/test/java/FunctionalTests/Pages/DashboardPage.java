package FunctionalTests.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import UsedByAll.PathFromBy;

// * Created for W-xmlm by Fill on 31.03.2015.
public class DashboardPage extends AuthorizedUserPage {
    public DashboardPage(WebDriver driver, WebDriverWait wait) {super(driver, wait);}
    public static final By walletsSection = By.xpath("//div[@class='user-wallets']");
    public static final By currentWallet = By.xpath(PathFromBy.getXpathFromBy(walletsSection) + "//p[contains(text(), 'Current')]");
    public static final By bonusesWallet = By.xpath(PathFromBy.getXpathFromBy(walletsSection) + "//p[contains(text(), 'Bonuses')]");
    public static final By salaryWallet = By.xpath(PathFromBy.getXpathFromBy(walletsSection) + "//p[contains(text(), 'Salary')]");
    public static final By leftGraph = By.xpath("//div[@id='site_statistics2']");
    public static final By rightGraph = By.xpath("//div[@id='chart3']");
    public static final By infoWidget = By.xpath("//div/p[contains(text(), 'For any questions')]");
}
