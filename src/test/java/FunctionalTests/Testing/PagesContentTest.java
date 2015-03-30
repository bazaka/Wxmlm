package FunctionalTests.Testing;

import FunctionalTests.Pages.AuthorizedUserPage;
import FunctionalTests.Pages.LogInPage;
import UsedByAll.*;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import FunctionalTests.Pages.*;

import java.lang.reflect.Field;

import java.io.IOException;
import java.util.Collection;

import static UsedByAll.Config.getConfig;
import static org.junit.Assert.assertTrue;

// * Created for W-xmlm by Fill on 26.03.2015.
@RunWith(value = Parameterized.class)
public class PagesContentTest extends BaseTest {
    String email;
    String password;

    @Parameterized.Parameters
    public static Collection testData() {
        return CsvUsersReader.getDataForTest("_PagesContentTest(");
    }

    public PagesContentTest(TestUser testUser){
        this.email = testUser.getEmail();
        this.password = testUser.getPassword1();
    }

    @Test
    public void pagesContentTest() throws IOException, JSONException, ClassNotFoundException, IllegalAccessException, NullPointerException {
        //Авторизируемся
        LogInPage logInPage = new LogInPage(driver, wait);
        logInPage.open();
        assertTrue("Page not opened", logInPage.isOpened());
        logInPage.goLogin(email, password);
        Assert.assertEquals(logInPage.getTitle(), "KairosNet");

        // Определяем масив страниц и элементов, которые нужно проверить на странице, из файла "Pages.csv"
        CsvPagesReader csvPagesReader = new CsvPagesReader();
        Page[] pages = csvPagesReader.getPagesFromFile("src/Pages.csv");

        // В цикле вызываем каждую страницу для проверки
        for (Page page : pages) {
            Class<?> currentPage = Class.forName(page.getPageName());
            driver.get(getConfig().getProtocol() + getConfig().getScheme() + page.getRoute());

            // Для каждой страницы, в цикле вызываем каждый ее элемент для проверки
            for (String elementName : page.getElements()) {
                By element = (By) ReflectUtils.getFieldRecursive(currentPage, elementName).get(currentPage);
                assertTrue("Element \"" + elementName + "\"(" + element + ") is not visible on page \"" + currentPage.getSimpleName() + "\"",  new ElementProperty().isElementVisible(driver, element));
            }
        }
    }
}