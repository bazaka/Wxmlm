package ApiTests.ObjectClasses;

/**
 * Created by User on 3/5/2015.
 */
public class User {
    private int userId;
    private Object surname;
    private String name;
    private Object patronymic;
    private String username;
    private String password;
    private String salt;
    private int countryId;
    private Object languageId;
    private String birthDate;
    private String emailMain;
    private Object email2;
    private Object email3;
    private String phoneNumberMain;
    private Object phoneNumber2;
    private Object phoneNumber3;
    private Object passportNumber;
    private Object passportSeries;
    private Object passportIssuedBy;
    private Object passportIssueDate;
    private Object adressMain;
    private Object adress2;
    private Object adress3;
    private int genderId;
    private int userStatusId;
    private Object createdDate;
    private Object createdBy;
    private Object changedBy;
    private Object changedDate;
    private Object parentId;
    private Object leaderId;
    private boolean network;
    private int career;
    private boolean isApproved;
    private String[] inviteCode;
    private boolean debtor;
    private double turnover;
    private Object turnoverDate;


    public User(int userId, Object surname, String name,Object patronymic, String username, String password, String salt, int countryId,Object languageId,String birthDate, String emailMain, Object email2, Object email3,
                String phoneNumberMain, Object phoneNumber2, Object phoneNumber3, Object passportNumber, Object passportSeries, Object passportIssuedBy, Object passportIssueDate, Object adressMain, Object adress2, Object adress3,
                int genderId, int userStatusId, Object createdDate, Object createdBy ,Object changedBy,Object changedDate,Object parentId,Object leaderId,boolean network,int career, boolean isApproved, String[] inviteCode, boolean debtor, double turnover, Object turnoverDate)
    {

        this.setUserId(userId);
        this.setSurname(surname);
        this.setName(name);
        this.setPatronymic(patronymic);
        this.setUsername(username);
        this.setPassword(password);
        this.setSalt(salt);
        this.setCountryId(countryId);
        this.setLanguageId(languageId);
        this.setBirthDate(birthDate);
        this.setEmailMain(emailMain);
        this.setEmail2(email2);
        this.setEmail3(email3);
        this.setPhoneNumberMain(phoneNumberMain);
        this.setPhoneNumber2(phoneNumber2);
        this.setPhoneNumber3(phoneNumber3);
        this.setPassportNumber(passportNumber);
        this.setPassportSeries(passportSeries);
        this.setPassportIssuedBy(passportIssuedBy);
        this.setPassportIssueDate(passportIssueDate);
        this.setAdressMain(adressMain);
        this.setAdress2(adress2);
        this.setAdress3(adress3);
        this.setGenderId(genderId);
        this.setUserStatusId(userStatusId);
        this.setCreatedDate(createdDate);
        this.setCreatedBy(createdBy);
        this.setChangedBy(changedBy);
        this.setChangedDate(changedDate);
        this.setParentId(parentId);
        this.setLeaderId(leaderId);
        this.setNetwork(network);
        this.setCareer(career);
        this.setIsApproved(isApproved);
        this.setInviteCode(inviteCode);
        this.setDebtor(debtor);
        this.setTurnover(turnover);
        this.setTurnoverDate(turnoverDate);
    }

    public int getUserId(){return userId;}
    public Object getSurname(){return surname;}
    public String getName(){return name;}
    public Object getPatronymic(){return patronymic;}
    public String getUsername(){return username;}
    public String getPassword(){return password;}
    public String getSalt(){return salt;}
    public int getCountryId(){return countryId;}
    public Object getLanguageId(){return languageId;}
    public String getBirthDate(){return birthDate;}
    public String getEmailMain(){return emailMain;}
    public Object getEmail2(){return email2;}
    public Object getEmail3(){return email3;}
    public String getPhoneNumberMain(){return phoneNumberMain;}
    public Object getPhoneNumber2(){return phoneNumber2;}
    public Object getPhoneNumber3(){return phoneNumber3;}
    public Object getPassportNumber(){return passportNumber;}
    public Object getPassportSeries(){return passportSeries;}
    public Object getPassportIssuedBy(){return passportIssuedBy;}
    public Object getPassportIssueDate(){return passportIssueDate;}
    public Object getAdressMain(){return adressMain;}
    public Object getAdress2(){return adress2 ;}
    public Object getAdress3(){return adress3;}
    public int getGenderId(){return genderId;}
    public int getUserStatusId(){return userStatusId;}
    public Object getCreatedDate(){return createdDate;}
    public Object getCreatedBy(){return createdBy;}
    public Object getChangedBy(){return changedBy;}
    public Object getChangedDate(){return changedDate;}
    public Object getParentId(){return parentId;}
    public Object getLeaderId(){return leaderId;}
    public boolean getNetwork(){return network;}
    public int getCareer(){return career;}
    public boolean getIsApproved(){return isApproved;}
    public String[] getInviteCode(){return inviteCode;}
    public String getInviteCode(int i){return inviteCode[i];}
    public boolean getDebtor(){return debtor;}
    public double getTurnover(){return turnover;}
    public Object getTurnoverDate(){return turnoverDate;}



    public void setUserId(int a){this.userId = a;}
    public void setSurname(Object a){this.surname = a;}
    public void setName(String a){this.name = a;}
    public void setPatronymic(Object a){this.patronymic = a;}
    public void setUsername(String a){this.username = a;}
    public void setPassword(String a){this.password = a;}
    public void setSalt(String a){this.salt = a;}
    public void setCountryId(int a){this.countryId = a;}
    public void setLanguageId(Object a){this.languageId = a;}
    public void setBirthDate(String a){this.birthDate = a;}
    public void setEmailMain(String a){this.emailMain = a;}
    public void setEmail2(Object a){this.email2 = a;}
    public void setEmail3(Object a){this.email3 = a;}
    public void setPhoneNumberMain(String a){this.phoneNumberMain = a;}
    public void setPhoneNumber2(Object a){this.phoneNumber2 = a;}
    public void setPhoneNumber3(Object a){this.phoneNumber3 = a;}
    public void setPassportNumber(Object a){this.passportNumber = a;}
    public void setPassportSeries(Object a){this.passportSeries = a;}
    public void setPassportIssuedBy(Object a){this.passportIssuedBy = a;}
    public void setPassportIssueDate(Object a){this.passportIssueDate = a;}
    public void setAdressMain(Object a){this.adressMain = a;}
    public void setAdress2(Object a){this.adress2  = a;}
    public void setAdress3(Object a){this.adress3 = a;}
    public void setGenderId(int a){this.genderId = a;}
    public void setUserStatusId(int a){this.userStatusId = a;}
    public void setCreatedDate(Object a){this.createdDate = a;}
    public void setCreatedBy(Object a){this.createdBy = a;}
    public void setChangedBy(Object a){this.changedBy = a;}
    public void setChangedDate(Object a){this.changedDate = a;}
    public void setParentId(Object a){this.parentId = a;}
    public void setLeaderId(Object a){this.leaderId = a;}
    public void setNetwork(boolean a){this.network = a;}
    public void setCareer(int a){this.career = a;}
    public void setIsApproved(boolean a){this.isApproved = a;}
    public void setInviteCode(String[] a){this.inviteCode=a;}
    public void setInviteCode(String a, int elementNumber){this.inviteCode[elementNumber]=a;}
    public void setDebtor(boolean a){this.debtor = a;}
    public void setTurnover(double a){this.turnover = a;}
    public void setTurnoverDate(Object a){this.turnoverDate = a;}



    public boolean exceptedChangedDate(User a){
        if(this==a)
            return true;
        if(a==null){
            System.out.println("1");
            return false;
        }
        if(getClass() !=a.getClass()){
            System.out.println("2");
            return false;
        }
        if(getUserId()!=a.getUserId()){
            System.out.println("3");
            return false;
        }
        if(!getSurname().equals(a.getSurname())){
            System.out.println("4");
            return false;
        }
        if(!getName().equals(a.getName())){
            System.out.println("5");
            return false;
        }
        if(!getPatronymic().equals(a.getPatronymic())){
            System.out.println("6");
            return false;
        }
        if(!getUsername().equals(a.getUsername())){
            System.out.println("7");
            return false;
        }
        if(!getPassword().equals(a.getPassword())){
            System.out.println("8");
            return false;
        }
        if(!getSalt().equals(a.getSalt())){
            System.out.println("9");
            return false;
        }
        if(getCountryId()!=a.getCountryId()){
            System.out.println("10");
            return false;
        }
        if(getLanguageId()!=a.getLanguageId()){
            System.out.println("11");
            return false;
        }
        if(!getBirthDate().equals(a.getBirthDate())){
            System.out.println("12");
            return false;
        }

        if(!getEmailMain().equals(a.getEmailMain())){
            System.out.println("13");
            return false;
        }

        if(!getEmail2().equals(a.getEmail2())){
            System.out.println("14");
            return false;
        }

        if(!getEmail3().equals(a.getEmail3())){
            System.out.println("15");
            return false;
        }

        if(!getPhoneNumberMain().equals(a.getPhoneNumberMain())){
            System.out.println("16");
            return false;
        }

        if(!getPhoneNumber2().equals(a.getPhoneNumber2())){
            System.out.println("17");
            return false;
        }

        if(!getPhoneNumber3().equals(a.getPhoneNumber3())){
            System.out.println("18");
            return false;
        }

        if(!getPassportNumber().equals(a.getPassportNumber())){
            System.out.println("19");
            return false;
        }

        if(!getPassportSeries().equals(a.getPassportSeries())){
            System.out.println("20");
            return false;
        }

        if(!getPassportIssuedBy().equals(a.getPassportIssuedBy())){
            System.out.println("21");
            return false;
        }

        if(!getPassportIssueDate().equals(a.getPassportIssueDate())){
            System.out.println("22");
            return false;
        }

        if(!getAdressMain().equals(a.getAdressMain())){
            System.out.println("23");
            return false;
        }

        if(!getAdress2().equals(a.getAdress2())){
            System.out.println("24");
            return false;
        }

        if(!getAdress3().equals(a.getAdress3())){
            System.out.println("25");
            return false;
        }
        if(getGenderId()!=a.getGenderId()){
            System.out.println("26");
            return false;
        }
        if(getUserStatusId()!=a.getUserStatusId()){
            System.out.println("27");
            return false;
        }
        if(!getCreatedDate().equals(a.getCreatedDate())){
            System.out.println("28");
            return false;
        }
        if(getCreatedBy()!=a.getCreatedBy()){
            System.out.println("29");
            return false;
        }
        if(getChangedBy()!=a.getChangedBy()){
            System.out.println("30");
            return false;
        }
       /* if(!getChangedDate().equals(a.getChangedDate())){
            System.out.println("31");
            return false;
        }*/
        if(!getParentId().equals(a.getParentId())){
            System.out.println("32");
            return false;
        }
        if(!getLeaderId().equals(a.getLeaderId())){
            System.out.println("33");
            return false;
        }
        if(getNetwork()!=a.getNetwork()){
            System.out.println("34");
            return false;
        }
        if(getCareer()!=a.getCareer()){
            System.out.println("35");
            return false;
        }
        if(getIsApproved()!=a.getIsApproved()){
            System.out.println("36");
            return false;
        }
        for(int i=0; i<getInviteCode().length; i++){
            if(!getInviteCode(i).equals(a.getInviteCode(i))){
                System.out.println("36-"+i);
                return false;
            }
        }
        if(getDebtor()!=a.getDebtor()){
            System.out.println("37");
            return false;
        }
        if(getTurnover()!=a.getTurnover()){
            System.out.println("38");
            return false;
        }
        if(!getTurnoverDate().equals(getTurnoverDate())){
            System.out.println("39");
            return false;
        }

        return true;

    }

}
