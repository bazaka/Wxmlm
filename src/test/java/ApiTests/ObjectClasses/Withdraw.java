package ApiTests.ObjectClasses;

// * Created for W-xmlm by Fill on 01.12.2014.
public class Withdraw {
    public int id;
    public int userId;
    public int merchantId;
    public int operationId;
    public double amount;
    public String createdDate;
    public int status;
    public String epid;
    public String swiftName;
    public String swiftAddress;
    public String bankName;
    public String bankAddress;
    public String accountIban;
    public String swiftCode;
    public String merchant;

    public Withdraw(int wId, int wUserId, int wMerchantId, int wOperationId, double wAmount, String wCreatedDate, int wStatus, String wEpid, String wMerchant)
    {
        this.setId(wId);
        this.setUserId(wUserId);
        this.setMerchantId(wMerchantId);
        this.setOperationId(wOperationId);
        this.setAmount(wAmount);
        this.setCreatedDate(wCreatedDate);
        this.setStatus(wStatus);
        this.setEpid(wEpid);
        this.setMerchant(wMerchant);
    }

    public Withdraw(int wId, int wUserId, int wMerchantId, int wOperationId, double wAmount, String wCreatedDate, int wStatus, String wSwiftName, String wSwiftAddress, String wBankName, String wBankAddress, String wAccountIban, String wSwiftCode, String wMerchant)
    {
        this.setId(wId);
        this.setUserId(wUserId);
        this.setMerchantId(wMerchantId);
        this.setOperationId(wOperationId);
        this.setAmount(wAmount);
        this.setCreatedDate(wCreatedDate);
        this.setStatus(wStatus);
        this.setSwiftName(wSwiftName);
        this.setSwiftAddress(wSwiftAddress);
        this.setBankName(wBankName);
        this.setBankAddress(wBankAddress);
        this.setAccountIban(wAccountIban);
        this.setSwiftCode(wSwiftCode);
        this.setMerchant(wMerchant);
    }

    public int getId() {return id;}
    public int getUserId() {return userId;}
    public int getMerchantId() {return merchantId;}
    public int getOperationId() {return operationId;}
    public double getAmount() {return amount;}
    public String getCreatedDate() {return createdDate;}
    public int getStatus() {return status;}
    public String getEpid() {return epid;}
    public String getSwiftName() {return swiftName;}
    public String getSwiftAddress() {return swiftAddress;}
    public String getBankName() {return bankName;}
    public String getBankAddress() {return bankAddress;}
    public String getAccountIban() {return accountIban;}
    public String getSwiftCode() {return swiftCode;}
    public String getMerchant() {return merchant;}

    public void setId(int a) {this.id = a;}
    public void setUserId(int a) {this.userId = a;}
    public void setMerchantId(int a) {this.merchantId = a;}
    public void setOperationId(int a) {this.operationId = a;}
    public void setAmount(double a) {this.amount = a;}
    public void setCreatedDate(String a) {this.createdDate = a;}
    public void setStatus(int a) {this.status = a;}
    public void setEpid(String a) {this.epid = a;}
    public void setSwiftName(String a) {this.swiftName = a;}
    public void setSwiftAddress(String a) {this.swiftAddress = a;}
    public void setBankName(String a) {this.bankName = a;}
    public void setBankAddress(String a) {this.bankAddress = a;}
    public void setAccountIban(String a) {this.accountIban = a;}
    public void setSwiftCode(String a) {this.swiftCode = a;}
    public void setMerchant(String a) {this.merchant = a;}

    public boolean equalsExceptUpdatedDate(Withdraw a)
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
        if (getUserId() != a.getUserId()) {
            System.out.println("3");
            return false;
        }
        if (getMerchantId() != a.getMerchantId()) {
            System.out.println("4");
            return false;
        }
        if (getOperationId() != a.getOperationId()){
            System.out.println("5");
            return false;
        }
        if (getAmount() != a.getAmount()) {
            System.out.println("6");
            return false;
        }
        if (!getCreatedDate().equals(a.getCreatedDate())) {
            System.out.println("7");
            return false;
        }
        if (getAmount() != a.getAmount()) {
            System.out.println("8");
            return false;
        }
        if (getStatus() != a.getStatus()) {
            System.out.println("9");
            return false;
        }
        if (!getMerchant().equals(a.getMerchant())){
            System.out.println("10");
            return false;
        }
        if (getMerchant().equals("ePayment"))
        {
            if (!getEpid().equals(a.getEpid())) {
                System.out.println("10");
                return false;
            }
        }
        else {
            if (!getSwiftName().equals(a.getSwiftName())) {
                System.out.println("11");
                return false;
            }
            if (!getSwiftAddress().equals(a.getSwiftAddress())) {
                System.out.println("12");
                return false;
            }
            if (!getBankName().equals(a.getBankName())) {
                System.out.println("13");
                return false;
            }
            if (!getBankAddress().equals(a.getBankAddress())) {
                System.out.println("14");
                return false;
            }
            if (!getAccountIban().equals(a.getAccountIban())) {
                System.out.println("15");
                return false;
            }
            if (!getSwiftCode().equals(a.getSwiftCode())) {
                System.out.println("16");
                return false;
            }
        }
        return true;
    }
}
