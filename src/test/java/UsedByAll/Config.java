package UsedByAll;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;

public class Config {
    private String scheme;
    private boolean isHttpsConnection;
    private TestUser admin;

    public void setScheme(String a)
    { this.scheme = a; }
    public void setIsHttpsConnection(boolean a)
    { this.isHttpsConnection = a; }
    public void setAdmin(TestUser a)
    { this.admin = a; }
    public String getScheme()
    { return scheme; }
    public boolean getIsHttpsConnection()
    { return isHttpsConnection; }
    public TestUser getAdmin()
    { return admin; }
    public String getProtocol()
    {
        if (getIsHttpsConnection()){
            return "https://";
        }
        else return "http://";
    }

    public Config(String scheme, boolean isHttpsConnection, TestUser admin){
        this.setScheme(scheme);
        this.setIsHttpsConnection(isHttpsConnection);
        this.setAdmin(admin);

    }

    public static Config getConfig()
    {
        //Читаем .json-файл и заполняем списки данных значениями из файла. В списки попадают данные только тех пользователей, которые в поле "useInTest" имеют значение "+"
        try {
            // Читаем конфиг
            FileReader reader = new FileReader("src/Config.json");
            BufferedReader br = new BufferedReader(reader);
            String result = "";
            String line;
            while ((line = br.readLine()) != null) {
                result += line;
            }
            br.close();
            JSONObject object = new JSONObject(result);
            JSONObject adminObject = object.getJSONObject("admin");
            TestUser admin = new TestUser();
            admin.setEmail(adminObject.getString("login"));
            admin.setPassword1(adminObject.getString("password"));
            // Отдаем конфиг
            return new Config(object.getString("scheme"), object.getBoolean("isHttpsConnection"), admin);
            }
        catch (Exception e){
            System.out.println("File not found. Exception: " + e);
            return null;
        }
    }
}