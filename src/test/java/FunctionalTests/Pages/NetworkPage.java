package FunctionalTests.Pages;

import UsedByAll.RandomValue;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by User on 6/17/2015.
 */
public class NetworkPage extends AuthorizedUserPage {
    public NetworkPage(WebDriver driver, WebDriverWait wait){super(driver, wait); }
    //вкладки
    public static final By treeView = By.xpath("//a[@href='#tab-tree-view']");
    public static final By tableView = By.xpath("//a[@href='#tab-table-view']");

    //фильтры
    public static final By minimizeFilter = By.xpath("//a[@class='minimize']/i");
    public static final By email = By.id("user_filter_email");
    public static final By name = By.id("user_filter_fullName");
    public static final By code = By.id("user_filter_invitations_code");
    public static final By careerOld = By.id("user_filter_career");
    public static final String careerSelectId = "user_filter_career";
    public static final By careerDropDown = By.xpath("//select[@id='user_filter_career']/../div/button");
    /*public static final By careerNew = By.*/

    //кнопки
    public static final By searchButton = By.xpath("//button[contains(@class, 'operation_filter_submit')]");
    public static final By resetButton = By.xpath("//button[contains(@class, 'operation_filter_clear')]");
    public static final By tableLength = By.name("xmlm_user_tree_network_length");
    public static final By searchField = By.xpath("//div[@id='xmlm_user_tree_network_filter']/label/input");
    public static final By nextPage = By.xpath("//li[@class='next']/a");
    public static final By exportToXLS = By.id("user-tree-download-excel");
    public static final By processingLoading = By.id("xmlm_user_tree_network_processing");

    //таблица
    //названия столбцов
    public static final By columnHead = By.xpath("//table[@id='xmlm_user_tree_network']/thead/tr/th");

    //колонки значений
    public static final By plusColumn = By.xpath("//img[@src='/images/plus.png']");
    public static final By registeredColumn = By.xpath("//tbody/tr[@role='row']/td[2]");
    public static final By usernameColumnSelect = By.xpath("//tbody/tr[@role='row']/td[3]/a[1]");
    public static final By usernameColumnName = By.xpath("//tbody/tr[@role='row']/td[3]/a[2]/span");
    public static final By usernameColumnEmail = By.xpath("//tbody/tr[@role='row']/td[3]/span/span");
    public static final By lineColumn = By.xpath("//tbody/tr[@role='row']/td[4]");
    public static final By careerColumn = By.xpath("//tbody/tr[@role='row']/td[5]");
    public static final By leaderColumn = By.xpath("//tbody/tr[@role='row']/td[6]");
    public static final By sponsorColumnName = By.xpath("//tbody/tr[@role='row']/td[7]");
    public static final By sponsorColumnEmail = By.xpath("//tbody/tr[@role='row']/td[7]/span/span");
    public static final By locationColumn = By.xpath("//tbody/tr[@class='details']//div[@class='row']/dl[1]/dd");
    public static final By verificationColumn = By.xpath("//tbody/tr[@class='details']//div[@class='row']/dl[2]/dd");
    public static final By statusColumn = By.xpath("//tbody/tr[@class='details']//div[@class='row']/dl[3]/dd");
    //public static final By careerColumn = By.xpath("//tbody/tr[@class='details']//div[@class='row']/dl[4]/dd");

    //переход на table view
    public void goTableView(){
        driver.findElement(tableView).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(email));
    }
    //сброс результатов поска
    public void resetSearchResult(){
        driver.findElement(resetButton).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(processingLoading));

    }
    //ожидание загрузки страницы
    public void waitForPageLoad(){
        wait.until(ExpectedConditions.presenceOfElementLocated(email));
    }
    //ожидание загрузки таблицы
    public void waitForTableLoad(){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(processingLoading));
    }


            //checkSorting один метод

    //выбрать рандомное значения из указанного столбца
    public WebElement getAnyRandomValueInTableByLocator(By tableLocator){

        List<WebElement> listOfElement = driver.findElements(tableLocator);
        for(int i=0; i<listOfElement.size(); i++)
            System.out.println(listOfElement.get(i).getText());
        System.out.println("size: " +listOfElement.size());
        int elementNumber = RandomValue.randomValue(listOfElement.size()-1);
        return listOfElement.get(elementNumber);
    }
    //ввести указаный текст в поисковую строку и нажать Search
    public String searchInTableByString(By fieldLocator, By tableLocator){
        driver.findElement(fieldLocator).click();
        driver.findElement(fieldLocator).clear();
        String searchValue = getAnyRandomValueInTableByLocator(tableLocator).getText();
        driver.findElement(fieldLocator).sendKeys(searchValue);
        driver.findElement(searchButton).click();
        return searchValue;
    }
    //выбрать элемент в невидимом выпадающем списке Select c помощью javascript и нажимаем Search
    public String searchInTableBySelect(String selectLocator, By tableLocator){
        String searchValue = getAnyRandomValueInTableByLocator(tableLocator).getText();
        JavascriptExecutor js = (JavascriptExecutor)driver;
//        String script = "var select = document.getElementById('" + selectLocator + "');" + "select.options[" + searchValue + "].text;";
        String script = "var careerLvl = $('#"+selectLocator+"').children().filter(function() {\n" +
                "  return $(this).text() === \""+searchValue+"\";\n" +
                "}).val();\n" +
                "$('#"+selectLocator+"').parent()\n" +
                ".find('.btn-group ul input[value=\"'+careerLvl+'\"]')\n" +
                ".click();";


        js.executeScript(script);
        driver.findElement(careerDropDown).click();


        assertEquals(driver.findElement(careerDropDown).getAttribute("title"), searchValue);
        driver.findElement(searchButton).click();
        return searchValue;

    }

    //Пробежаться по всей колонке и првоерить правильность поиска
    public boolean checkCorrectSearch(String searchValue, By tableLocator){
        List<WebElement> searchResult = driver.findElements(tableLocator);
        for(int i=0; i<searchResult.size(); i++){
            System.out.println("Нашли: "+searchValue);
            System.out.println("Искали: "+searchResult.get(i).getText());
            if(!searchResult.get(i).getText().equals(searchValue))
                return false;
        }
        return true;
    }

    //Поиск и проверка правильности поиска
    //берем значение, вбиваем в строку поиска, проверяем корректность
    public boolean searchStringAndCheckResult(By fieldLocator, By tableLocator){
        String getValue = searchInTableByString(fieldLocator, tableLocator);
        waitForTableLoad();
        return checkCorrectSearch(getValue, tableLocator);
    }
    //берем значение, выбираем его в Selecte, проверяем корректность
    public boolean searchSelectAndCheckResult(String searchLocator, By tableLocator){
        String getValue = searchInTableBySelect(searchLocator, tableLocator);
        waitForTableLoad();
        return checkCorrectSearch(getValue, tableLocator);
    }



    //поиск по email
    public boolean searchByEmail(){
        return searchStringAndCheckResult(email, usernameColumnEmail);
    }
    public boolean searchByName(){
        return searchStringAndCheckResult(name, usernameColumnName);
    }
    //поиск по career
    public boolean searchByCareer() {return searchSelectAndCheckResult(careerSelectId, careerColumn);}




    public void /*boolean */ checkSorting(){

    }
}
