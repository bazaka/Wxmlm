package ApiTests.ObjectClasses;

// * Created for W-xmlm by Fill on 03.03.2015.
public class CareerHistory {
    private int historyId;
    private int userId;
    private int career;
    private String date;
    private int[] members;
    private String createdDate;
    private String updatedDate;

    // Constructors
    public CareerHistory(int historyId, int userId, int career, String date, int[] members, String createdDate, String updatedDate)
    {
        this.setHistoryId(historyId);
        this.setUserId(userId);
        this.setCareer(career);
        this.setDate(date);
        this.setMembers(members);
        this.setCreatedDate(createdDate);
        this.setUpdatedDate(updatedDate);
    }

    // Getters
    public int getHistoryId() {return historyId;}
    public int getUserId() {return userId;}
    public int getCareer() {return career;}
    public String getDate() {return date;}
    public int[] getMembers() {return members;}
    public int getMembers(int i) {return members[i];}
    public String getCreatedDate() {return createdDate;}
    public String getUpdatedDate() {return updatedDate;}

    // Setters
    public void setHistoryId(int a) {this.historyId = a;}
    public void setUserId(int a) {this.userId = a;}
    public void setCareer(int a) {this.career = a;}
    public void setDate(String a) {this.date = a;}
    public void setMembers(int[] a) {this.members = a;}
    public void setMembers(int elementNumber, int a) {this.members[elementNumber] = a;}
    public void setCreatedDate(String a) {this.createdDate = a;}
    public void setUpdatedDate(String a) {this.updatedDate = a;}

    public boolean equalsExceptUpdatedDate(CareerHistory a, boolean toCheckCreatedDate)
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
        if (getHistoryId() != a.getHistoryId()) {
            System.out.println("3");
            return false;
        }
        if (getUserId() != a.getUserId()) {
            System.out.println("4");
            return false;
        }
        if (getCareer() != a.getCareer()){
            System.out.println("5");
            return false;
        }
        if (!getDate().equals(a.getDate())) {
            System.out.println("9");
            return false;
        }
        for (int i = 0; i < getMembers().length; i++) {
            if (getMembers(i) != a.getMembers(i)) {
                System.out.println("6");
                return false;
            }
        }
        if (toCheckCreatedDate && !getCreatedDate().equals(a.getCreatedDate())) {
            System.out.println("9");
            return false;
        }
        return true;
    }

}
