package ApiTests.ObjectClasses;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * Created by User on 5/18/2015.
 */
public class Comment {
    private int id;
    private int typeId;
    private String text;
    private Object userId;
    private Object purchaseId;
    private Object operationId;
    private Object accountId;
    private Object rechargeRequestId;
    private Object withdrawRequestId;
    private Object verificationRequestId;
    private Object frontEventTypeId;
    private Object backEventTypeId;
    private String createdDate;
    private String updatedDate;

    //Constructors
    public Comment(int id, int typeId, String text, Object userId, Object purchaseId, Object operationId, Object accountId, Object rechargeRequestId, Object withdrawRequestId, Object verificationRequestId, Object frontEventTypeId, Object backEventTypeId, String createdDate, String updatedDate){
        this.setId(id);
        this.setTypeId(typeId);
        this.setText(text);
        this.setUserId(userId);
        this.setPurchaseId(purchaseId);
        this.setOperationId(operationId);
        this.setAccountId(accountId);
        this.setRechargeRequestId(rechargeRequestId);
        this.setWithdrawRequestId(withdrawRequestId);
        this.setVerificationRequestId(verificationRequestId);
        this.setFrontEventTypeId(frontEventTypeId);
        this.setBackEventTypeId(backEventTypeId);
        this.setCreatedDate(createdDate);
        this.setUpdatedDate(updatedDate);
    }

    //Getters
    public int getId() {return id;}
    public int getTypeId() {return typeId; }
    public String getText() {return text;}
    public Object getUserId() {return userId;}
    public Object getPurchaseId() {return purchaseId;}
    public Object getOperationId() {return operationId;}
    public Object getAccountId() {return accountId;}
    public Object getRechargeRequestId() {return rechargeRequestId;}
    public Object getWithdrawRequestId() {return withdrawRequestId;}
    public Object getVerificationRequestId() {return verificationRequestId;}
    public Object getFrontEventTypeId() {return frontEventTypeId;}
    public Object getBackEventTypeId() {return backEventTypeId;}
    public String getCreatedDate() {return createdDate;}
    public String getUpdatedDate() {return updatedDate;}

    //Setters
    public void setId(int a){this.id=a;}
    public void setTypeId(int a){this.typeId=a;}
    public void setText(String a){this.text=a;}
    public void setUserId(Object a){this.userId=a;}
    public void setPurchaseId(Object a){this.purchaseId=a;}
    public void setOperationId(Object a){this.operationId=a;}
    public void setAccountId(Object a){this.accountId=a;}
    public void setRechargeRequestId(Object a){this.rechargeRequestId=a;}
    public void setWithdrawRequestId(Object a){this.withdrawRequestId=a;}
    public void setVerificationRequestId(Object a){this.verificationRequestId=a;}
    public void setFrontEventTypeId(Object a){this.frontEventTypeId=a;}
    public void setBackEventTypeId(Object a){this.backEventTypeId=a;}
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
        if(getTypeId() != a.getTypeId()){
            System.out.println("6");
            return false;
        }
        if(!getText().equals(a.getText())){
            System.out.println("7");
            return false;
        }
        if(!getUserId().equals(a.getUserId())){
            System.out.println("10:" + getUserId() + "!=" + a.getUserId());
            return false;
        }
        if(!getPurchaseId().equals(a.getPurchaseId())){
            System.out.println("11");
            return false;
        }
        if(!getOperationId().equals(a.getOperationId())){
            System.out.println("12");
            return false;
        }
        if(!getAccountId().equals(a.getAccountId())){
            System.out.println("13");
            return false;
        }
        if(!getRechargeRequestId().equals(a.getRechargeRequestId())){
            System.out.println("14");
            return false;
        }
        if(!getWithdrawRequestId().equals(a.getWithdrawRequestId())){
            System.out.println("15");
            return false;
        }
        if(!getVerificationRequestId().equals(a.getVerificationRequestId())){
            System.out.println("16");
            return false;
        }
        if(!getFrontEventTypeId().equals(a.getFrontEventTypeId())){
            System.out.println("17");
            return false;
        }
        if(!getBackEventTypeId().equals(a.getBackEventTypeId())){
            System.out.println("18");
            return false;
        }
        if(toCheckCreatedDate && !getCreatedDate().equals(a.getCreatedDate())){
            System.out.println("8");
            return false;
        }
        return true;
    }
}
