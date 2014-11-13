package FunctionalTests;

import UsedByAll.TestUser;
import com.thoughtworks.selenium.*;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class UserLogin {
	private DefaultSelenium selenium;
    private WebDriver driver;

	@Before
	public void setUp(String scheme) throws Exception {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        selenium = new WebDriverBackedSelenium(driver, scheme);
/*        selenium = new DefaultSelenium("localhost", 4444, "*firefox", scheme);
		selenium.start();

*/        System.out.println("Запускаю селениум для авторизации");
	}

	@Test
	public void testUserLogin(TestUser User) throws Exception {

        // Открываем страницу авторизации
		selenium.open("/");
		selenium.waitForPageToLoad("30000");

        // Заполняем и отправляем форму авторизации
        selenium.waitForCondition("selenium.isVisible(\"id=username\")", "5000");
        selenium.type("id=username", User.getEmail());
        selenium.waitForCondition("selenium.isVisible(\"id=password\")", "5000");
        selenium.type("id=password", User.getPassword1());
		selenium.click("css=button.btn.btn-primary");

        // Ожидаем страницу авторизированного пользователя
		selenium.waitForPageToLoad("30000");
        selenium.waitForCondition("selenium.isVisible(\"//div[contains(., 'Home')]\")", "5000");
        System.out.println("Авторизировался. Пользователь: " + User.getEmail() + ". Пароль: " + User.getPassword1());
    }

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}