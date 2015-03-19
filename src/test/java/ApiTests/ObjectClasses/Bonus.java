package ApiTests.ObjectClasses;

/**
 * Created by User on 3/19/2015.
 */
public class Bonus {
    private int id;
    private int userId;
    private int partnerId;
    private int operationId;
    private int type;
    private int percent;
    private String createdDate;
    private String updatedDate;

    public Bonus(int bId, int bUserId, int bPartnerId, int bOperationId, int bType, int bPercent, String bCreatedDate, String bUpdatedDate ){
        this.setId(bId);
        this.setUserId(bUserId);
        this.setPartnerId(bPartnerId);
        this.setOperationId(bOperationId);
        this.setType(bType);
        this.setPercent(bPercent);
        this.setCreatedDate(bCreatedDate);
        this.setUpdatedDate(bUpdatedDate);
    }
    public int getId(){return id;}
    public int getUserId(){return userId;}
    public int getPartnerId(){return partnerId;}
    public int getOperationId(){return operationId;}
    public int getType(){return type;}
    public int getPercent(){return percent;}
    public String getCreatedDate(){return createdDate;}
    public String getUpdatedDate(){return updatedDate;}

    public void setId(int a){this.id=a;}
    public void setUserId(int a){this.userId=a;}
    public void setPartnerId(int a){this.partnerId=a;}
    public void setOperationId(int a){this.operationId=a;}
    public void setType(int a){this.type=a;}
    public void setPercent(int a){this.percent=a;}
    public void setCreatedDate(String a){this.createdDate=a;}
    public void setUpdatedDate(String a){this.updatedDate=a;}

    public boolean equalsExceptUpdatedDate(Bonus a){
        if(this==a)
            return true;
        if(a==null){
            System.out.println("1");
            return false;
        }
        if(getClass()!=a.getClass()){
            System.out.println("2");
            return false;
        }
        if(getId()!=a.getId()){
            System.out.println("3");
            return false;
        }
        if(getUserId()!=a.getUserId()){
            System.out.println("4");
            return false;
        }
        if(getPartnerId()!=a.getPartnerId()){
            System.out.println("5");
            return false;
        }
        if(getOperationId()!=a.getOperationId()){
            System.out.println("6");
            return false;
        }
        if(getType()!=a.getType()){
            System.out.println("7");
            return false;
        }
        if(getPercent()!=a.getPercent()){
            System.out.println("8");
            return false;
        }
        if(!getCreatedDate().equals(a.getCreatedDate())){
            System.out.println("9");
            return false;
        }
        return true;
    }
}
