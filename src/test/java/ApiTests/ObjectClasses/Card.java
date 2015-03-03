package ApiTests.ObjectClasses;

/**
 * Created by User on 2/26/2015.
 */
public class Card {
    public int cardId;
    public int userId;
    public String cardNumber;
    public String expDate;
    public String cardHolder;
    public boolean enabled;


    public Card(int aCard, int aUser, String aNumber, String aExpDate, String aHolder, boolean aEnabled ){
        this.setCardId(aCard);
        this.setUserId(aUser);
        this.setCardNumber(aNumber);
        this.setExpDate(aExpDate);
        this.setCardHolder(aHolder);
        this.setEnabled(aEnabled);
    }

    public int getCardId() {return cardId;}
    public int getUserId(){return userId;}
    public String getCardNumber(){return cardNumber;}
    public String getExpDate(){return expDate;}
    public String getCardHolder(){return cardHolder;}
    public boolean getEnabled(){return enabled;}

    public void setCardId(int a){this.cardId =a;}
    public void setUserId(int a){this.userId = a;}
    public void setCardNumber(String a){this.cardNumber = a;}
    public void setExpDate(String a){this.expDate = a;}
    public void setCardHolder(String a){this.cardHolder = a;}
    public void setEnabled(boolean a){this.enabled=a;}

    public boolean equalsExceptDate(Card a)
    {
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
        if(getCardId()!=a.getCardId()){
            System.out.println("3");
            return false;
        }
        if(getUserId()!=a.getUserId()){
            System.out.println("4");
            return false;
        }
        if(!getCardNumber().equals(a.getCardNumber())){

            System.out.println("5");
            return false;
        }
        if(!getExpDate().equals(a.getExpDate())){
            System.out.println("6");
            return false;
        }
        if(!getCardHolder().equals(a.getCardHolder())){
            System.out.println("7");
            return false;
        }
        if(getEnabled() != a.getEnabled()){
            System.out.println("8");
            return  false;
        }
        return true;
    }
}
