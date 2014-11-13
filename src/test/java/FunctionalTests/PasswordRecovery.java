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

public class PasswordRecovery {
	private DefaultSelenium selenium;
    WebDriver driver;

	@Before
	public void setUp(String scheme) throws Exception {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        selenium = new WebDriverBackedSelenium(driver, scheme);
/*        selenium = new DefaultSelenium("localhost", 4444, "*firefox", scheme); // Место где находится селениум, браузер, адрес начала работы
        selenium.start(); // Запуск селениума
        selenium.windowMaximize(); // Разворачивание окна на полный экран
*/        System.out.println("Запускаю селениум для проверки восстановления пароля на схеме " + scheme);
	}

	@Test
	public void testUserPasswordRecovery(String scheme, TestUser User) throws Exception {
		selenium.open("/"); // Открытие главной страницы сайта
		selenium.waitForPageToLoad("30000"); // Ожидание загрузки страницы
		selenium.click("link=Forgot password"); // Клик на ссылку "Forgot password"
		selenium.waitForPageToLoad("30000"); // Ожидание загрузки страницы
        selenium.waitForCondition("selenium.isTextPresent(\"Don't worry, we'll send you an email to reset your password.\")", "5000"); // Ожидание условия: на странице присутствует текст, характерный для страницы запроса на восстановление пароля
		selenium.type("name=username", User.getEmail());
		selenium.click("//button[@type='submit']");
        selenium.waitForPageToLoad("30000");
        if(selenium.isTextPresent("The password for this user has already been requested"))
        {
            System.out.println("Для этого пользователя уже было отправлено письмо с восстановлением пароля.");
        }
        else if(selenium.isTextPresent("It contains a link you must click to reset your password."))
        {
            System.out.println("Сообщение о том, что письмо было отправлено, видно.");
        }
        else
        {
            System.out.println("Что-то пошло не так после указания электронного адреса");
            return;
        }

        System.out.println("Проверяю почту");
        CheckLetterWithLink CheckNewPassLetter = new CheckLetterWithLink();
        try { CheckNewPassLetter.checkLetterWithLink("To reset your password - please visit", "resetting/reset", selenium, User); }
        catch (Exception e) { e.printStackTrace(); return;}

        // Переходим на открывшееся окно браузера, проверяем наличие поздравления о подтверждении регистрации
        selenium.selectWindow("Index");
        selenium.waitForCondition("selenium.isTextPresent(\"Please, set your new password\")", "10000");
        selenium.type("id=password", User.getNewPassword1());
        selenium.type("name=fos_user_resetting_form[plainPassword][second]", User.getNewPassword2());
        selenium.click("css=input.btn.btn-primary");
        User.setPassword1(User.getNewPassword1());
        User.setPassword2(User.getNewPassword1());
        selenium.waitForPageToLoad("7000");
        // Если вводились одинаковые пароли
        if(User.getNewPassword1().equals(User.getNewPassword2())) {
            try {
                selenium.waitForCondition("selenium.isTextPresent(\"For any questions regarding the rules of partnership and the network\")", "10000");
                System.out.println("После смены пароля вижу дашборд");
            }
            catch (SeleniumException e) {
                String errorMessage = selenium.getText("xpath=//div[contains(@class, 'alert alert-error')]/");
                System.out.println("После смены пароля не авторизировался. Ошибка: " + errorMessage);
                System.out.println("Exception: " + e);
                return;
            }
        }
        // Если вводились разные пароли
        else {
            try {
                selenium.waitForCondition("selenium.isTextPresent(\"The entered passwords don't match\")", "3000");
                String errorMessage = selenium.getText("xpath=//div[contains(@class, 'alert alert-error')]/");
                System.out.println("Текст ошибки о том, что пароли разные: " + errorMessage);
                return;
            }
            catch (SeleniumException e) {
                System.out.println("Текст ошибки о том, что пароли разные, отсутствует");
                System.out.println("Exception: " + e);
            }
        }

        System.out.println("Пытаюсь авторизироваться с новым паролем");
        // Авторизация
        UserLogin newPassUserLogin = new UserLogin();
        // Вызов метода, переходящего на главную страницу проекта
        try { newPassUserLogin.setUp(scheme); }
        catch (Exception e) { e.printStackTrace(); return;}
        // Вызов метода, запускающего тест авторизации
        try { newPassUserLogin.testUserLogin(User); }
        catch (Exception e) { e.printStackTrace(); return;}
        // Вызов метода окончания теста
        try { newPassUserLogin.tearDown(); }
        catch (Exception e) { e.printStackTrace(); }

    }

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}