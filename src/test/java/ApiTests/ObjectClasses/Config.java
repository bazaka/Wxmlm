package ApiTests.ObjectClasses;

public class Config {
    private int id;
    private String name;
    private String value;

    public Config(int id, String name, String value)
    {
        this.setId(id);
        this.setName(name);
        this.setValue(value);
    }

    public int getId() {return id;}
    public String getName() {return name;}
    public String getValue() {return value;}

    public void setId(int a) {this.id = a;}
    public void setName(String a) {this.name = a;}
    public void setValue(String a) {this.value = a;}

    public boolean equals(Config a)
    {
        if (this == a)
            return true;
        if (a == null){
            System.out.println("1");
            return false;
        }
        if (getClass() != a.getClass()) {
            System.out.println("2");
            return false;
        }
        if (getId() != a.getId()) {
            System.out.println("3");
            return false;
        }
        if (!getName().equals(a.getName())) {
            System.out.println("4");
            return false;
        }
        if (!getValue().equals(a.getValue())) {
            System.out.println("5");
            return false;
        }
       return true;
    }
}