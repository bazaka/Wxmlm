package ApiTests.ObjectClasses;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * Created by User on 5/18/2015.
 */
@RunWith(value = Parameterized.class)
public class Comment {
    private int id;
    private int objectType;
    private int objectId;
    private int type;
    private String text;
    private String createdDate;
    private String updatedDate;

    //Constructors
    public Comment(int id, int objectType, int objectId, int type, String text, String createdDate, String updatedDate){
        this.setId(id);
        this.setObjectType(objectType);
        this.setObjectId(objectId);
        this.setType(type);
        this.setText(text);
        this.setCreatedDate(createdDate);
        this.setUpdatedDate(updatedDate);
    }

    //Getters
    public int getId() {return id;}
    public int getObjectType() {return objectType;}
    public int getObjectId() {return objectId; }
    public int getType() {return type; }
    public String getText() {return text;}
    public String getCreatedDate() {return createdDate;}
    public String getUpdatedDate() {return updatedDate;}

    //Setters
    public void setId(int a){this.id=a;}
    public void setObjectType(int a){this.objectType=a;}
    public void setObjectId(int a){this.objectId=a;}
    public void setType(int a){this.type=a;}
    public void setText(String a){this.text=a;}
    public void setCreatedDate(String a){this.createdDate=a;}
    public void setUpdatedDate(String a){this.updatedDate=a;}

    public boolean equalsExceptDates(Comment a, boolean toCheckCreatedDate){
        if(this==a)
            return true;
        if(a==null){
            System.out.println("1");
            return false;
        }
        if(getClass() != a.getClass()){
            System.out.println("2");
            return false;
        }
        if(getId() != a.getId()){
            System.out.println("3");
            return false;
        }
        if(getObjectType() !=a.getObjectType()){
            System.out.println("4");
            return false;
        }
        if(getObjectId() != a.getObjectId()){
            System.out.println("5");
            return false;
        }
        if(getType() != a.getType()){
            System.out.println("6");
            return false;
        }
        if(!getText().equals(a.getText())){
            System.out.println("7");
            return false;
        }
        if(toCheckCreatedDate && !getCreatedDate().equals(a.getCreatedDate())){
            System.out.println("8");
            return false;
        }
        return true;
    }


}
