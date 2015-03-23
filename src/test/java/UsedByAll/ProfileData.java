package UsedByAll;

/**
 * Created by User on 12/25/2014.
 */
public class ProfileData {



        private String userFullName;
        private String gender;
        private String birthDate;
        private String phoneNumber ;
        private String address;
        private String citizen;
        private String passportSeries;
        private String passportNum;
        private String issued;
        private String issuedDate;
        private String bName;
        private String bAddress;
        private String bankName;
        private String bankAddress;
        private String iban;
        private String swift;
        private String epid;
        private String userTitle;
        private String cardNumber;
        private String cardHolder;
        private String cardDate;

        // Сеттеры
        public void setUserFullName(String a)
        { this.userFullName = a; }
        public void setGender(String a)
        { this.gender = a; }
        public void setBirthDate(String a)
        { this.birthDate = a; }
        public void setPhoneNumber(String a)
        { this.phoneNumber = a; }
        public void setAddress(String a)
        { this.address = a; }
        public void setCitizen(String a)
        { this.citizen = a; }
        public void setPassportSeries(String a)
        { this.passportSeries = a; }
        public void setPassportNum(String a)
        { this.passportNum = a; }
        public void setIssued(String a)
        { this.issued = a; }
        public void setIssuedDate(String a)
        { this.issuedDate = a; }
        public void setBName(String a)
        { this.bName = a; }
        public void setBAddress(String a)
        { this.bAddress = a; }
        public void setBankName(String a)
        { this.bankName = a; }
        public void setBankAddress(String a)
        { this.bankAddress = a; }
        public void setIban(String a)
        { this.iban = a; }
        public void setSwift(String a)
        { this.swift = a; }
        public void setEpid(String a)
        { this.epid = a; }
        public void setUserTitle(String a){
            this.userTitle = a;
        }
        public void setCardNumber(String a){
            this.cardNumber = a;
        }
        public void setCardHolder(String a){
            this.cardHolder = a;
        }
        public void setCardDate(String a){
            this.cardDate = a;
        }


        // Геттеры
        public String getUserFullName()
        { return userFullName; }
        public String getGender()
        { return gender; }
        public String getBirthDate()
        { return birthDate; }
        public String getPhoneNumber()
        { return phoneNumber; }
        public String getAddress()
        { return address; }
        public String getCitizen()
        { return citizen; }
        public String getPassportSeries()
        { return passportSeries; }
        public String getPassportNum()
        { return passportNum ; }
        public String getIssued()
        { return issued ; }
        public String getIssuedDate()
        { return issuedDate ; }
        public String getBName()
        { return bName ; }
        public String getBAddress()
        { return bAddress ; }
        public String getBankName()
        { return bankName ; }
        public String getBankAddress()
        { return bankAddress ; }
        public String getIban()
        { return iban ; }
        public String getSwift()
        { return swift ; }
        public String getEpid()
        { return epid ; }
        public String getUserTitle(){
            return userTitle;
        }
        public String getCardNumber(){
            return cardNumber;
        }
        public String getCardHolder(){
            return cardHolder;
        }
        public String getCardDate(){
            return cardDate;
        }

}
