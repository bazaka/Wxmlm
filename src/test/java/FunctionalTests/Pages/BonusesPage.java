package FunctionalTests.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

// * Created for W-xmlm by Fill on 31.03.2015.
public class BonusesPage extends MoneyFamilyPage {
    public BonusesPage(WebDriver driver, WebDriverWait wait) {super(driver, wait);}
    public static final By pageHeader = By.xpath("//div[@class='header']/h3[contains(text(), 'Bonuses')]");
    public static final By bonusesTab = By.xpath("//a[@href='#tab-xmlm-bonuses']");
    public static final By bonusesTable = By.xpath("//div[@id='tab-xmlm-bonuses']//table[@id='datatable']");
    public static final By exportBonusesToXLSButton = By.xpath("//a[contains(text(), 'Export data to XLS')]");

    public static final By incomeFromPackageTab = By.xpath("//a[@href='#income-from-packages']");
    public static final By incomeFromPackageTable = By.xpath("//div[@id='income-from-packages']//table");
}
