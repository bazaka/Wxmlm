package FunctionalTests.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// * Created for W-xmlm by Fill on 11.02.2015.
public class OperationHistoryPage extends MoneyFamilyPage {
    public OperationHistoryPage(WebDriver driver, WebDriverWait wait) {super(driver, wait);}
    public static final By lastOperationDateCell = By.xpath("//table[@id='datatable2']/tbody/tr[1]/td[2]");
    public static final By lastOperationTypeCell = By.xpath("//table[@id='datatable2']/tbody/tr[1]/td[3]");
    public static final By lastOperationSenderCell = By.xpath("//table[@id='datatable2']/tbody/tr[1]/td[4]");
    public static final By lastOperationAmountCell = By.xpath("//table[@id='datatable2']/tbody/tr[1]/td[5]");
    public static final By lastOperationStatusCell = By.xpath("//table[@id='datatable2']/tbody/tr[1]/td[6]");

    public String getLastOperationDate(){
        wait.until(ExpectedConditions.presenceOfElementLocated(lastOperationDateCell));
        return driver.findElement(lastOperationDateCell).getText();
    }
    public String getLastOperationType(){
        wait.until(ExpectedConditions.presenceOfElementLocated(lastOperationTypeCell));
        return driver.findElement(lastOperationTypeCell).getText();
    }
    public String getLastOperationSender(){
        wait.until(ExpectedConditions.presenceOfElementLocated(lastOperationSenderCell));
        return driver.findElement(lastOperationSenderCell).getText();
    }
    public String getLastOperationAmount(){
        wait.until(ExpectedConditions.presenceOfElementLocated(lastOperationAmountCell));
        return driver.findElement(lastOperationAmountCell).getText();
    }
    public String getLastOperationStatus(){
        wait.until(ExpectedConditions.presenceOfElementLocated(lastOperationStatusCell));
        return driver.findElement(lastOperationStatusCell).getText();
    }
}
