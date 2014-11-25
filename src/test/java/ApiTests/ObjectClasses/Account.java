package ApiTests.ObjectClasses;

public class Account {
    public int accountId;
    public int userId;
    public String accountNumber;
    public int accountType;
    public boolean status;
    public String accountInfo;
    public double amount;
    public String updatedDate;

    public Account(int aId, String aNumber, int aType, boolean aStatus, String aInfo, double aAmount)
    {
        accountId = aId;
        accountNumber = aNumber;
        accountType = aType;
        status = aStatus;
        accountInfo = aInfo;
        amount = aAmount;
    }

    public int getAccountId() {return accountId;}
    public int getUserId() {return userId;}
    public String getAccountNumber() {return accountNumber;}
    public int getAccountType() {return accountType;}
    public boolean getStatus() {return status;}
    public String getAccountInfo() {return accountInfo;}
    public double getAmount() {return amount;}
    public String getUpdatedDate() {return updatedDate;}

    public void setAccountId(int a) {this.accountId = a;}
    public void setUserId(int a) {this.userId = a;}
    public void setAccountNumber(String a) {this.accountNumber = a;}
    public void setAccountType(int a) {this.accountType = a;}
    public void setStatus(boolean a) {this.status = a;}
    public void setAccountInfo(String a) {this.accountInfo = a;}
    public void setAmount(double a) {this.amount = a;}
    public void setUpdatedDate(String a) {this.updatedDate = a;}

    public boolean equalsExceptUpdatedDate(Account acc)
    {
        if (this == acc)
            return true;
        if (acc == null)
            return false;
        if (getClass() != acc.getClass())
            return false;
        if (getAccountId() != acc.getAccountId())
            return false;
        if (getAmount() != acc.getAmount())
            return false;
        if (!getAccountInfo().equals(acc.getAccountInfo()))
            return false;
        if (!getAccountNumber().equals(acc.getAccountNumber()))
            return false;
        if (getAccountType() != acc.getAccountType())
            return false;
        if (getStatus() != acc.getStatus())
            return false;
        if (getUserId() != acc.getUserId())
            return false;
        return true;
    }
}