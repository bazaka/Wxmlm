package FunctionalTests;

import UsedByAll.CheckLetterWithLink;
import UsedByAll.TestUser;
import com.thoughtworks.selenium.*;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Registration {
	private DefaultSelenium selenium;
    private WebDriver driver;

	@Before
	public void setUp(String scheme) throws Exception { // Метод, подготавливающий параметры браузера
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        selenium = new WebDriverBackedSelenium(driver, scheme);
/*		selenium = new DefaultSelenium("localhost", 4444, "*firefox", scheme); // Место где находится селениум, браузер, адрес начала работы
		selenium.start(); // Запуск селениума
        selenium.windowMaximize(); // Разворачивание окна на полный экран
*/        System.out.println("Запускаю селениум для проверки регистрации на схеме " + scheme);
    }

	@Test
    // В будущем следует переписать таким образом, чтобы принимать не отдельные параметры, а объект пользователя
	public void testReg(TestUser User) throws Exception {

        // Открываем страницу регистрации
		selenium.open("/");
		selenium.click("link=Registration");
		selenium.waitForPageToLoad("5000");

        // Заполняем данными первый шаг регистрации
        selenium.type("id=fos_user_registration_form_email", User.getEmail());
        selenium.type("id=fos_user_registration_form_plainPassword_first", User.getPassword1());
        selenium.type("id=fos_user_registration_form_plainPassword_second", User.getPassword2());
		selenium.type("id=fos_user_registration_form_code", User.getInviteCode());
        Thread.sleep(500);
		selenium.click("//div[@id='step1']/div[5]/div/button");
        System.out.println("Пытаюсь перейти на второй шаг регистрации");
        Thread.sleep(500);

        // Проверяем, что пользователь перешел на второй шаг (проверяем видимость Заголовка второго шага "Personal info")
        try
        {
            selenium.waitForCondition("selenium.isVisible(\"//h3[contains(., 'Personal info')]\")", "2000");
            System.out.println("Перешел");
        }
        catch(SeleniumException e) {
            System.out.println("Не перешел: " + e);
            selenium.stop();
        }

        // Заполняем данными второй шаг регистрации
        selenium.type("//*[@id=\"fos_user_registration_form_fullName\"]", User.getFullName());
		selenium.type("id=fos_user_registration_form_phone", User.getPhone());
        selenium.click("id=fos_user_registration_form_birthday");
		selenium.click("//div[@class='datetimepicker datetimepicker-dropdown-bottom-right dropdown-menu']/div[@class='datetimepicker-years']/table/tbody/tr/td/span[3]");
		selenium.click("//div[@class='datetimepicker datetimepicker-dropdown-bottom-right dropdown-menu']/div[@class='datetimepicker-months']/table/tbody/tr/td/span[3]");
		selenium.click("//div[@class='datetimepicker datetimepicker-dropdown-bottom-right dropdown-menu']/div[@class='datetimepicker-days']/table/tbody/tr[2]/td[4]");
		selenium.click("id=fos_user_registration_form_agreement");
        Thread.sleep(500);
		selenium.click("css=div.form-group.text-right > div.col-sm-12 > button.btn.btn-primary");
        System.out.println("Пытаюсь отправить форму после второго шага регистрации");

        // Проверяем что открылось окно с текстом сообщения о том что письмо было выслано на почту (проверка по наличию текста). Если есть - выводим текст сообщения
        selenium.waitForPageToLoad("5000");
        try {
            selenium.waitForCondition("selenium.isElementPresent(\"//div[contains(., 'An email has been sent to')]\")", "5000");
            System.out.println("Отправил. Email: " + User.getEmail() + ", Password: " + User.getPassword1());
        }
        catch(SeleniumException e) {
            System.out.println("Не отправил: " + e);
        }

        //Вызываем метод, проверяющий почту и переходящий по ссылке из письма
        CheckLetterWithLink CheckRegistrationLetter = new CheckLetterWithLink();
        Thread.sleep(7000); // Ожидаем получения письма
        try { CheckRegistrationLetter.checkLetterWithLink("To finish activating your account - please visit", "register/confirm", selenium, User); }
        catch (Exception e) { e.printStackTrace(); return;}

        // Переходим на открывшееся окно браузера, проверяем наличие поздравления о подтверждении регистрации
        selenium.selectWindow("KairosNet");
        selenium.waitForCondition("selenium.isTextPresent(\"Congrats \")", "10000");
        System.out.println("Подтвердил регистрацию - вижу поздравление на сайте");
    }


	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}