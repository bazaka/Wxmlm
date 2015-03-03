package ApiTests.ObjectClasses;

// * Created for W-xmlm by Fill on 24.02.2015.
public class IncomeDetails {
    private int id;
    private int operationId;
    private int userId;
    private int[] purchaseIds;
    private int timeOnline;
    private int timeOnlinePaid;
    private String createdDate;
    private String updatedDate;

    // Constructors
    public IncomeDetails(int id, int operationId, int userId, int[] purchaseIds, int timeOnline, int timeOnlinePaid, String createdDate, String updatedDate)
    {
        this.setId(id);
        this.setOperationId(operationId);
        this.setUserId(userId);
        this.setPurchaseIds(purchaseIds);
        this.setTimeOnline(timeOnline);
        this.setTimeOnlinePaid(timeOnlinePaid);
        this.setCreatedDate(createdDate);
        this.setUpdatedDate(updatedDate);
    }

    // Getters
    public int getId() {return id;}
    public int getOperationId() {return operationId;}
    public int getUserId() {return userId;}
    public int[] getPurchaseIds() {return purchaseIds;}
    public int getPurchaseIds(int i) {return purchaseIds[i];}
    public int getTimeOnline() {return timeOnline;}
    public int getTimeOnlinePaid() {return timeOnlinePaid;}
    public String getCreatedDate() {return createdDate;}
    public String getUpdatedDate() {return updatedDate;}

    // Setters
    public void setId(int a) {this.id = a;}
    public void setOperationId(int a) {this.operationId = a;}
    public void setUserId(int a) {this.userId = a;}
    public void setPurchaseIds(int[] a) {this.purchaseIds = a;}
    public void setPurchaseIds(int elementNumber, int a) {this.purchaseIds[elementNumber] = a;}
    public void setTimeOnline(int a) {this.timeOnline = a;}
    public void setTimeOnlinePaid(int a) {this.timeOnlinePaid = a;}
    public void setCreatedDate(String a) {this.createdDate = a;}
    public void setUpdatedDate(String a) {this.updatedDate = a;}

    public boolean equalsExceptUpdatedDate(IncomeDetails a, boolean toCheckCreatedDate)
    {
        if (this == a)
            return true;
        if (a == null) {
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
        if (getOperationId() != a.getOperationId()) {
            System.out.println("4");
            return false;
        }
        if (getUserId() != a.getUserId()){
            System.out.println("5");
            return false;
        }
        for (int i = 0; i < getPurchaseIds().length; i++) {
            if (getPurchaseIds(i) != a.getPurchaseIds(i)) {
                System.out.println("6");
                return false;
            }
        }
        if (getTimeOnline() != a.getTimeOnline()) {
            System.out.println("7");
            return false;
        }
        if (getTimeOnlinePaid() != a.getTimeOnlinePaid()) {
            System.out.println("8");
            return false;
        }
        if (toCheckCreatedDate && !getCreatedDate().equals(a.getCreatedDate())) {
            System.out.println("9");
            return false;
        }
        return true;
    }
}
