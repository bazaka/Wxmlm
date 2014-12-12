package ApiTests.ObjectClasses;

/**
 * Created by User on 12/10/2014.
 */
public class Purchases {
    public int id;
    public int buyerUserId;
    public int productId;
    public String date;
    public String updatedDate;
    public String price;
    public double paymentAmount;
    public int status;
    public String terms;

    public Purchases(int pId, int pBuyerUserId, int pProductId, String pDate, String pPrice, double pPaymentAmount, int pStatus, String pTerms){
        this.setId(pId);
        this.setBuyerUserId(pBuyerUserId);
        this.setProductId(pProductId);
        this.setDate(pDate);
        this.setPrice(pPrice);
        this.setPaymentAmount(pPaymentAmount);
        this.setStatus(pStatus);
        this.setTerms(pTerms);
    }

    public int getId(){return id;}
    public int getBuyerUserId(){return buyerUserId;}
    public int getProductId(){return productId;}
    public String getDate(){return date;}
    public String getUpdatedDate(){return updatedDate;}
    public String getPrice(){return price;}
    public double getPaymentAmount(){return paymentAmount;}
    public int getStatus(){return status;}
    public String getTerms(){return terms;}

    public void setId(int a) {this.id = a;}
    public void setBuyerUserId(int a) {this.buyerUserId = a;}
    public void setProductId(int a) {this.productId = a;}
    public void setDate(String a) {this.date = a;}
    public void setUpdatedDate(String a) {this.updatedDate = a;}
    public void setPrice(String a) {this.price = a;}
    public void setPaymentAmount(double a) {this.paymentAmount = (double)((Math.round(a*100))/100);}
    public void setStatus(int a){this.status = a;}
    public void setTerms(String a){this.terms = a;}

    public boolean equalsExceptUpdatedDate(Purchases a){
        if(this ==a)
            return true;
        if (a==null){
            System.out.println("1");
            return false;
        }
        if(getClass() !=a.getClass()){
            System.out.println("2");
            return false;
        }
        if(getBuyerUserId() != a.getBuyerUserId()){
            System.out.println("3");
            return false;
        }
        if(getProductId() != a.getProductId()){
            System.out.println("4");
            return false;
        }
/* if(!getDate().equals(a.getDate())){
                System.out.println("5");
                return false;
        }*/

        if(!getPrice().equals(a.getPrice())){
            System.out.println("6");
            return false;
        }
        if(getPaymentAmount() != a.getPaymentAmount()){
            System.out.println("7");
            return false;
        }
        if(getStatus() != a.getStatus()){
            System.out.println("8");
            return false;
        }
        if(getTerms() != null && a.getTerms() != null){
            if(!getTerms().equals(a.getTerms())){
                System.out.println("9");
                return false;
            }
        }
        return true;

    }
}
