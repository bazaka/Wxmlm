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
    public void pagesContentTest() throws IOException, JSONException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        //Авторизируемся
        LogInPage logInPage = new LogInPage(driver, wait);
        logInPage.open();
        assertTrue("Page not opened", logInPage.isOpened());
        logInPage.goLogin(email, password);
        Assert.assertEquals(logInPage.getTitle(), "KairosNet");

        CsvPagesReader csvPagesReader = new CsvPagesReader();
        Page[] pages = csvPagesReader.getPagesFromFile("src/Pages.csv");
        System.out.println(pages[0].getPageName());
        for (Page page : pages) {
            System.out.println(page.getPageName());
            Class<?> currentPage = Class.forName(page.getPageName());
            driver.get(getConfig().getProtocol() + getConfig().getScheme() + page.getRoute());
            for (String elementName : page.getElements()) {
                System.out.println("currentElement: " + elementName);
                Field field = currentPage.getField(elementName);
                System.out.println(field);
                //assertTrue("Element " + elementName + " is not visible", currentElement);
            }

            System.out.println(getConfig().getProtocol() + getConfig().getScheme() + page.getRoute());
        }
    }

}
