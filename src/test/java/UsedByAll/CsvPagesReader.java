package UsedByAll;

import com.Ostermiller.util.CSVParser;
import com.Ostermiller.util.LabeledCSVParser;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// * Created for W-xmlm by Fill on 26.03.2015.
public class CsvPagesReader {
    public Page[] getPagesFromFile(String myFile) throws IOException, JSONException {
        //Подготавливаемся к чтению файла - объявляем списки данных, из которых будут составлены объекты проверяемых страниц
        File file1 = new File(myFile);
        List<String> routes = new ArrayList<String>();
        List<String> pageNames = new ArrayList<String>();
        List<String> elementJsons = new ArrayList<String>();

        //Читаем .csv-файл и заполняем списки данных значениями из файла. В списки попадают данные только тех пользователей, которые в поле "useInTest" имеют значение "+"
        LabeledCSVParser parser = new LabeledCSVParser(new CSVParser(new InputStreamReader(new FileInputStream(file1))));
        while (parser.getLine() != null) {
            routes.add(parser.getValueByLabel("route"));
            pageNames.add(parser.getValueByLabel("pageName"));
            elementJsons.add(parser.getValueByLabel("visibleElements"));
            System.out.println("Route: " + parser.getValueByLabel("route"));
            System.out.println("Page Name: " + parser.getValueByLabel("pageName"));
            System.out.println("Visible Elements: " + parser.getValueByLabel("visibleElements"));
            }
        // Создаем по спискам данных массив тестовых пользователей
        String[] routesArray = routes.toArray(new String[routes.size()]);
        String[] pageNamesArray = pageNames.toArray(new String[pageNames.size()]);
        String[] elementJSONsArray = elementJsons.toArray(new String[elementJsons.size()]);
        Page[] pages = new Page[routes.size()];

        for (int i = 0; i < pages.length; i++) {
            pages[i] = new Page();
            pages[i].setRoute(routesArray[i]);
            pages[i].setPageName(pageNamesArray[i]);
            //Читаем строку и заполняем массив элементов значениями из этой строки
            String[] elements = elementJSONsArray[i].split(", ");
            pages[i].setElements(elements);
        }
    return pages;
    }
}