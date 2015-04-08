package ApiTests.ObjectClasses;

/**
 * Created by User on 4/8/2015.
 */
public class FinParameter {
    public int id;
    public int operationTypeId;
    public Object merchantId;
    public Object paymentMerchantId;
    public int parameterId;
    public String value;
    public String dateStart;
    public Object dateEnd;
    public boolean enabled;

    public FinParameter(int pId, int pOperationTypeId, Object pMerchantId, Object pPaymentMerchantId, int pParameterId, String pValue, String pDateStart, Object pDateEnd, boolean pEnabled){
        this.setId(pId);
        this.setOperationTypeId(pOperationTypeId);
        this.setMerchantId(pMerchantId);
        this.setPaymentMerchantId(pPaymentMerchantId);
        this.setParameterId(pParameterId);
        this.setValue(pValue);
        this.setDateStart(pDateStart);
        this.setDateEnd(pDateEnd);
        this.setEnabled(pEnabled);
    }
    public int getId(){return id;}
    public int getOperationTypeId(){return operationTypeId;}
    public Object getMerchantId(){return merchantId;}
    public Object getPaymentMerchantId(){return paymentMerchantId;}
    public int getParameterId(){return parameterId;}
    public String getValue(){return value;}
    public String getDateStart(){return dateStart;}
    public Object getDateEnd(){return  dateEnd;}
    public boolean getEnabled(){return enabled;}

    public void setId(int a){this.id=a;}
    public void setOperationTypeId(int a){this.operationTypeId=a;}
    public void setMerchantId(Object a){this.merchantId=a;}
    public void setPaymentMerchantId(Object a){this.paymentMerchantId=a;}
    public void setParameterId(int a){this.parameterId=a;}
    public void setValue(String a){this.value=a;}
    public void setDateStart(String a){this.dateStart=a;}
    public void setDateEnd(Object a){this.dateEnd=a;}
    public void setEnabled(boolean a){this.enabled=a;}

    public boolean equalsFin(FinParameter a){
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
        if(getOperationTypeId()!=a.getOperationTypeId()){
            System.out.println("4");
            return false;
        }
        if(!getMerchantId().equals(a.getMerchantId())){
            System.out.println("5");
            return false;
        }
        if(!getPaymentMerchantId().equals(a.getPaymentMerchantId())){
            System.out.println("6");
            return false;
        }
        if(getParameterId()!=a.getParameterId()){
            System.out.println("7");
            return false;
        }
        if(!getValue().equals(a.getValue())){
            System.out.println("8");
            return false;
        }
        if(!getDateStart().equals(a.getDateStart())){
            System.out.println("9");
            return false;
        }
        if(!getDateEnd().equals(a.getDateEnd())){
            System.out.println("10");
            return false;
        }
        if(getEnabled()!=a.getEnabled()){
            System.out.println("11");
            return false;
        }
        return true;
    }
}
