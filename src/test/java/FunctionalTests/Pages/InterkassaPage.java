package FunctionalTests.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

/**
 * Created by User on 3/31/2015.
 */
public class InterkassaPage extends RechargePage {
    public InterkassaPage(WebDriver driver, WebDriverWait wait){ super(driver, wait);
    }
    public static By interkassaImage = By.xpath("//form[@id='interkassa_form']//img");
    public static By iAmount = By.xpath("//form[@id='interkassa_form']//input[@name='amount']");
    public static By iFee = By.className("interkassa_fee_amount");
    public static By rechargeButton = By.xpath("//form[@id='interkassa_form']//button[text()='Recharge']");
    public static By paymentSumm = By.xpath("//span[@class='payment-summ-amount ng-binding']");
    public static By paymentDescription = By.xpath("//div[@class='payment-description ng-binding']");


    public void waitForPageLoading(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(iAmount));
    }
    public String getImageLink(){
        return(driver.findElement(interkassaImage).getAttribute("src"));
    }
    public void enterAmount(String value){
        driver.findElement(iAmount).clear();
        driver.findElement(iAmount).click();
        driver.findElement(iAmount).sendKeys(value);
    }
    public String getSumWithFee(){
        return(driver.findElement(iFee).getText());
    }
    public void createPayment(){

        String originalWindow = driver.getWindowHandle();
        final Set<String> oldWindowsSet = driver.getWindowHandles(); //набор текущих открытых окон
        driver.findElement(rechargeButton).click();

        String newWindow = wait.until(new ExpectedCondition<String>() {
                                          public String apply(WebDriver driver){
                                              Set<String> newWindowsSet = driver.getWindowHandles();
                                              newWindowsSet.removeAll(oldWindowsSet);
                                              return newWindowsSet.size()>0 ? newWindowsSet.iterator().next() : null;
                                          }
                                      }
        );
        driver.switchTo().window(newWindow);
    }
    public void waitForPaymentFormLoading(){
        wait.until(ExpectedConditions.presenceOfElementLocated(paymentDescription));
    }
    public String getPaymentSumm(){
        return(driver.findElement(paymentSumm).getText());
    }
    public String getEmailFromPaymentForm(){
        return(driver.findElement(paymentDescription).getText().split("from ")[1].split(", ")[0]);
    }
    public String getInviteCodeFromPaymentForm(){
        String temp = driver.findElement(paymentDescription).getText().split("code ")[1];
        return(temp.substring(0, temp.length()-1));
    }

}
