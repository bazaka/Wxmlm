package ApiTests.ObjectClasses;

// * Created for W-xmlm by Fill on 27.11.2014.
public class Operation {
    private int id;
    private int targetAccountId;
    private int sourceAccountId;
    private String purchaseId;
    private int initiatorUserId;
    private String createdDate;
    private String updatedDate;
    private double amount;
    private int status;
    private int type;
    private boolean quarantine;
    private Object parentOperationId;
    private int sourceUserId;
    private int targetUserId;

    public Operation(int oId, int oTargetAccountId, int oSourceAccountId, String oPurchaseId, int oInitiatorUserId, String oCreatedDate, double oAmount, int oStatus, int oType, boolean oQuarantine, Object oParentOperationId, int oSourceUserId, int oTargetUserId)
    {
        this.setId(oId);
        this.setTargetAccountId(oTargetAccountId);
        this.setSourceAccountId(oSourceAccountId);
        this.setPurchaseId(oPurchaseId);
        this.setInitiatorUserId(oInitiatorUserId);
        this.setCreatedDate(oCreatedDate);
        this.setAmount(oAmount);
        this.setStatus(oStatus);
        this.setType(oType);
        this.setQuarantine(oQuarantine);
        this.setParentOperationId(oParentOperationId);
        this.setSourceUserId(oSourceUserId);
        this.setTargetUserId(oTargetUserId);
    }

    public int getId() {return id;}
    public int getTargetAccountId() {return targetAccountId;}
    public int getSourceAccountId() {return sourceAccountId;}
    public String getPurchaseId() {return purchaseId;}
    public int getInitiatorUserId() {return initiatorUserId;}
    public String getCreatedDate() {return createdDate;}
    public String getUpdatedDate() {return updatedDate;}
    public double getAmount() {return amount;}
    public int getStatus() {return status;}
    public int getType() {return type;}
    public boolean getQuarantine() {return quarantine;}
    public Object getParentOperationId() {return parentOperationId;}
    public int getSourceUserId() {return sourceUserId;};
    public int getTargetUserId() {return targetUserId;};

    public void setId(int a) {this.id = a;}
    public void setTargetAccountId(int a) {this.targetAccountId = a;}
    public void setSourceAccountId(int a) {this.sourceAccountId = a;}
    public void setPurchaseId(String a) {this.purchaseId = a;}
    public void setInitiatorUserId(int a) {this.initiatorUserId = a;}
    public void setCreatedDate(String a) {this.createdDate = a;}
    public void setUpdatedDate(String a) {this.updatedDate = a;}
    public void setAmount(double a) {this.amount = (double)((Math.round(a * 100))/100);}
    public void setStatus(int a) {this.status = a;}
    public void setType(int a) {this.type = a;}
    public void setQuarantine(boolean a) {this.quarantine = a;}
    public void setParentOperationId(Object a) {this.parentOperationId = a;}
    public void setSourceUserId(int a) {this.sourceUserId = a;}
    public void setTargetUserId(int a) {this.targetUserId = a;}

    public boolean equalsExceptUpdatedDate(Operation a)
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
        if (getTargetAccountId() != a.getTargetAccountId()) {
            System.out.println("3");
            return false;
        }
        if (getSourceAccountId() != a.getSourceAccountId()) {
            System.out.println("4");
            return false;
        }
        if (getPurchaseId() != null && a.getPurchaseId() != null){
            if (!getPurchaseId().equals(a.getPurchaseId())) {
                System.out.println("5");
                return false;
            }
        }
        if (getInitiatorUserId() != a.getInitiatorUserId()) {
            System.out.println("6");
            return false;
        }
        if (!getCreatedDate().equals(a.getCreatedDate())) {
            System.out.println("6.5");
            return false;
        }
        if (getAmount() != a.getAmount()) {
            System.out.println(a.getAmount() + " = " + a.getAmount());
            System.out.println("7");
            return false;
        }
        if (getStatus() != a.getStatus()) {
            System.out.println("8");
            return false;
        }
        if (getType() != a.getType()) {
            System.out.println("9");
            return false;
        }
        if (getQuarantine() != a.getQuarantine()) {
            System.out.println("10");
            return false;
        }
        if (getParentOperationId() != a.getParentOperationId()) {
            System.out.println("11");
            return false;
        }
        if (getSourceUserId() != a.getSourceUserId()) {
            System.out.println("12");
            return false;
        }
        if (getTargetUserId() != a.getTargetUserId()) {
            System.out.println("13");
            return false;
        }
        return true;
    }
}
