package FunctionalTests.Pages;

import UsedByAll.TestUser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by User on 12/2/2014.
 */
public class GmailPage extends BasePage {

    WebDriverWait wait = new WebDriverWait(driver, 10);

    private static final By signIn = By.id("gmail-sign-in");
    private static final By email = By.id("Email");
    private static final By password = By.id("Passwd");
    private static final By enter = By.id("signIn");
    private static final By remember = By.id("PersistentCookie");
    private static final String letter = "//table/tbody/tr/td[6]//span[contains(text(), '%s')]";
    private static final By letterLink = By.xpath("//a[contains(@href, 'xmlm.t4web.com.ua/register/confirm/')]");
    private static final By successConfirm = By.xpath("//div[@id='main-modal-window-confirmed-email']//p[contains(text(), 'Congrats')]");



    public GmailPage(WebDriver driver){
        super(driver);
        url="https://mail.google.com/mail/u/0/?pli=1#inbox";
    }



    public void checkGmail(TestUser user) {
        //driver.get(url);

        wait.until(ExpectedConditions.titleContains("Gmail"));

        if (!driver.findElement(email).isDisplayed()) // якщо не знайдене поле вводу пошти, нажимаем "Войти"
            driver.findElement(signIn).click();
        driver.findElement(email).sendKeys(user.getEmail());
        driver.findElement(password).sendKeys(user.getEPassword());
        if (driver.findElement(remember).isSelected()) // прибираємо чекбокс "Оставаться в системе"
            driver.findElement(remember).click();
        driver.findElement(enter).click();

        wait.until(ExpectedConditions.titleContains(user.getEmail()));
    }
    public void checkConfirmLetter(TestUser user, String letterText){
        try{
            driver.findElement(By.xpath(String.format(letter, letterText))).click();
            System.out.println("Письмо найдено");
        }catch(WebDriverException e){
            System.out.println("Письмо не найдено");
        }

        wait.until(ExpectedConditions.titleContains("Welcome "+user.getEmail()));
        driver.findElement(letterLink).click();


    }

    public String confirmActivation(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        for(String newWindow : driver.getWindowHandles()){    // перейти на нове вікно
            driver.switchTo().window(newWindow);
        }
        return (driver.findElement(successConfirm).getText());
    }

}
