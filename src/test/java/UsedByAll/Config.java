package UsedByAll;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;

public class Config {
    private String scheme;

    public void setScheme(String a)
    { this.scheme = a; }
    public String getScheme()
    { return scheme; }

    public Config(String scheme){
        this.setScheme(scheme);
    }

    public static Config getConfig()
    {
        //Читаем .json-файл и заполняем списки данных значениями из файла. В списки попадают данные только тех пользователей, которые в поле "useInTest" имеют значение "+"
        try {
            // Читаем конфиг
            FileReader reader = new FileReader("src/Config.json");
            JSONParser jsonParser = new JSONParser();
            JSONObject object = (JSONObject) jsonParser.parse(reader);
            // Отдаем конфиг
            return new Config(object.get("scheme").toString());
            }
        catch (Exception e){
            System.out.println("File not found. Exception: " + e);
            return null;
        }
    }
}