package ApiTests.ObjectClasses;

/**
 * Created by User on 3/26/2015.
 */
public class BankDetails {
    private int userId;
    private Object epayments;
    private String epid;
    private String createdEpayments;
    private String updatedEpayments;
    private Object swift;
    private String name;
    private String address;
    private String bankName;
    private String bankAddress;
    private String iban;
    private String swiftCode;
    private String swiftCreated;
    private String swiftUpdated;

    public BankDetails(int bUserId, String bEpid, String bCreatedEpayments, String bUpdatedEpayments, String bName, String bAddress,String bBankName, String bBankAddress, String bIban, String bSwiftCode, String bSwiftCreated, String bSwiftUpdated){
        this.setUserId(bUserId);
        this.setEpid(bEpid);
        this.setCreatedEpayments(bCreatedEpayments);
        this.setUpdatedEpayments(bUpdatedEpayments);
        this.setName(bName);
        this.setAddress(bAddress);
        this.setBankName(bBankName);
        this.setBankAddress(bBankAddress);
        this.setIban(bIban);
        this.setSwiftCode(bSwiftCode);
        this.setSwiftCreated(bSwiftCreated);
        this.setSwiftUpdated(bSwiftUpdated);
    }
    public BankDetails(int bUserId, Object bEpayments, Object bSwift ){
        this.setUserId(bUserId);
        this.setEpayments(bEpayments);
        this.setSwift(bSwift);
    }
    public BankDetails(int bUserId, String bEpid, String bCreatedEpayments, String bUpdatedEpayments, Object bSwift){
        this.setUserId(bUserId);
        this.setEpid(bEpid);
        this.setCreatedEpayments(bCreatedEpayments);
        this.setUpdatedEpayments(bUpdatedEpayments);
        this.setSwift(bSwift);
    }
    public BankDetails(int bUserId, Object bEpayments,String bName, String bAddress,String bBankName, String bBankAddress, String bIban, String bSwiftCode, String bSwiftCreated, String bSwiftUpdated) {
        this.setUserId(bUserId);
        this.setEpayments(bEpayments);
        this.setName(bName);
        this.setAddress(bAddress);
        this.setBankName(bBankName);
        this.setBankAddress(bBankAddress);
        this.setIban(bIban);
        this.setSwiftCode(bSwiftCode);
        this.setSwiftCreated(bSwiftCreated);
        this.setSwiftUpdated(bSwiftUpdated);
    }


    public int getUserId(){return userId;}
    public Object getEpayments(){return epayments;}
    public String getEpid(){return epid;}
    public String getCreatedEpayments(){return createdEpayments;}
    public String getUpdatedEpayments(){return updatedEpayments;}
    public Object getSwift(){return swift;}
    public String getName(){return name;}
    public String getAddress(){return address;}
    public String getBankName(){return bankName;}
    public String getBankAddress(){return bankAddress;}
    public String getIban(){return iban;}
    public String getSwiftCode(){return swiftCode;}
    public String getSwiftCreated(){return  swiftCreated;}
    public String getSwiftUpdated(){return swiftUpdated;}

    public void setUserId(int a){this.userId = a;}
    public void setEpayments(Object a){this.epayments = a;}
    public void setEpid(String a){this.epid = a;}
    public void setCreatedEpayments(String a){this.createdEpayments=a;}
    public void setUpdatedEpayments(String a){this.updatedEpayments=a;}
    public void setSwift(Object a){this.swift=a;}
    public void setName(String a){this.name=a;}
    public void setAddress(String a){this.address=a;}
    public void setBankName(String a){this.bankName=a;}
    public void setBankAddress(String a){this.bankAddress=a;}
    public void setIban(String a){this.iban=a;}
    public void setSwiftCode(String a){this.swiftCode=a;}
    public void setSwiftCreated(String a){this.swiftCreated=a;}
    public void setSwiftUpdated(String a){this.swiftUpdated=a;}


}
