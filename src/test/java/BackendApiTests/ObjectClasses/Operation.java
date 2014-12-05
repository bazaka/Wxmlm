package BackendApiTests.ObjectClasses;

// * Created for W-xmlm by Fill on 27.11.2014.
public class Operation {
    public int id;
    public int targetAccountId;
    public int sourceAccountId;
    public String purchaseId;
    public int initiatorUserId;
    public String createdDate;
    public String updatedDate;
    public double amount;
    public int status;
    public int type;
    public boolean quarantine;

    public Operation(int oId, int oTargetAccountId, int oSourceAccountId, String oPurchaseId, int oInitiatorUserId, String oCreatedDate, double oAmount, int oStatus, int oType, boolean oQuarantine)
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

    public void setId(int a) {this.id = a;}
    public void setTargetAccountId(int a) {this.targetAccountId = a;}
    public void setSourceAccountId(int a) {this.sourceAccountId = a;}
    public void setPurchaseId(String a) {this.purchaseId = a;}
    public void setInitiatorUserId(int a) {this.initiatorUserId = a;}
    public void setCreatedDate(String a) {this.createdDate = a;}
    public void setUpdatedDate(String a) {this.updatedDate = a;}
    public void setAmount(double a) {this.amount = a;}
    public void setStatus(int a) {this.status = a;}
    public void setType(int a) {this.type = a;}
    public void setQuarantine(boolean a) {this.quarantine = a;}

    public boolean equalsExceptUpdatedDate(Operation a)
    {
        if (this == a)
            return true;
        if (a == null) {
            return false;
        }
        if (getClass() != a.getClass()) {
            return false;
        }
        if (getTargetAccountId() != a.getTargetAccountId()) {
            return false;
        }
        if (getSourceAccountId() != a.getSourceAccountId()) {
            return false;
        }
        if (getPurchaseId() != null && a.getPurchaseId() != null){
            if (!getPurchaseId().equals(a.getPurchaseId())) {
                return false;
            }
        }
        if (getInitiatorUserId() != a.getInitiatorUserId()) {
            return false;
        }
        if (!getCreatedDate().equals(a.getCreatedDate())) {
            return false;
        }
        if (getAmount() != a.getAmount()) {
            return false;
        }
        if (getStatus() != a.getStatus()) {
            return false;
        }
        if (getType() != a.getType()) {
            return false;
        }
        if (getQuarantine() != a.getQuarantine()) {
            return false;
        }
        return true;
    }
}
