package ApiTests.ObjectClasses;

import sun.nio.ch.Net;

// * Created for W-xmlm by Fill on 18.05.2015.
public class NetPurchase {
    public int id;
    public int userId;
    public int purchaseId;
    public int usersNet;
    public int inTurnover;
    public String createdDate;
    public String updatedDate;

    public NetPurchase(int aId, int aUserId, int aPurchaseId, int aUsersNet, int aInTurnover, String aCreatedDate)
    {
        this.setId(aId);
        this.setUserId(aUserId);
        this.setPurchaseId(aPurchaseId);
        this.setUsersNet(aUsersNet);
        this.setInTurnover(aInTurnover);
        this.setCreatedDate(aCreatedDate);
    }

    public int getId() {return id;}
    public int getUserId() {return userId;}
    public int getPurchaseId() {return purchaseId;}
    public int getUsersNet() {return usersNet;}
    public int getInTurnover() {return inTurnover;}
    public String getCreatedDate() {return createdDate;}
    public String getUpdatedDate() {return updatedDate;}

    public void setId(int a) {this.id = a;}
    public void setUserId(int a) {this.userId = a;}
    public void setPurchaseId(int a) {this.purchaseId = a;}
    public void setUsersNet(int a) {this.usersNet = a;}
    public void setInTurnover(int a) {this.inTurnover = a;}
    public void setCreatedDate(String a) {this.createdDate = a;}
    public void setUpdatedDate(String a) {this.updatedDate = a;}

    public boolean equalsExceptUpdatedDate(NetPurchase a, boolean toCheckCreatedDate)
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
        if (getUserId() != a.getUserId()) {
            System.out.println("4");
            return false;
        }
        if (getPurchaseId() != a.getPurchaseId()) {
            System.out.println("5");
            return false;
        }
        if (getUsersNet() != a.getUsersNet()) {
            System.out.println("6");
            return false;
        }
        if (getInTurnover() != a.getInTurnover()) {
            System.out.println("7");
            return false;
        }
        if (toCheckCreatedDate && !getCreatedDate().equals(a.getCreatedDate())) {
            System.out.println("8");
            return false;
        }
        return true;
    }
}
