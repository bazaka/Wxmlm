package UsedByAll;

import UsedByAll.TestUser;
import com.thoughtworks.selenium.*;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CheckLetterWithLink {
	private DefaultSelenium selenium;
    private WebDriver driver;

	@Before
	public void setUp(String mailerURL) throws Exception {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        selenium = new WebDriverBackedSelenium(driver, mailerURL);
/*		selenium = new DefaultSelenium("localhost", 4444, "*firefox", mailerURL);
		selenium.start();
        selenium.windowMaximize();
*/        System.out.println("Запускаю селениум для подтверждения регистрации на почтовике " + mailerURL);
	}

	@Test
	public void checkLetterWithLink(String letterText, String letterLink, DefaultSelenium selenium, TestUser User) throws Exception {

        // Открываем страницу авторизации на почтовике
        selenium.open(User.getMailerURL());
        selenium.waitForPageToLoad("5000");

        // Авторизируемся
		selenium.type("id=Email", User.getEmail());
		selenium.type("id=Passwd", User.getEPassword());
		selenium.click("id=signIn");

        // Находим письмо и кликаем на него
        Thread.sleep(7000); // Вынужденная задержка, пока не получилось обойти явно
        System.out.println("Ищу письмо в ящике");
        try
        {
            selenium.waitForCondition("selenium.isVisible(\"//span[contains(., '" + letterText + "')]\")", "15000");
            System.out.println("Письмо найдено");
        }
        catch(SeleniumException e) {
            System.out.println("Письмо не найдено: " + e);
        }

        selenium.click("//span[contains(., '" + letterText + "')]");
        System.out.println("Выбираю письмо с текстом " + letterText);

        // Находим ссылку и кликаем на неё
        Thread.sleep(4000);
        selenium.waitForCondition("selenium.isElementPresent(\"//a[contains(., '" + letterLink + "')]\")", "5000");
        selenium.waitForCondition("selenium.isVisible(\"//a[contains(., '" + letterLink + "')]\")", "5000");
        selenium.click("//a[contains(., '" + letterLink + "')]");
        System.out.println("Кликаю по ссылке в письме");
        Thread.sleep(3000);
    }

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}