package ApiTests.ObjectClasses;

// * Created for W-xmlm by Fill on 05.03.2015.
public class Approve {
    private int id;
    private int userId;
    private int approveUserId;
    private String createdDate;
    private String updatedDate;
    private String approveDate;
    private String userComment;
    private String adminComment;
    private int status;
    private int[] documents;

    // Constructors
    public Approve(int id, int userId, int approveUserId, String createdDate, String updatedDate, String approveDate, String userComment, String adminComment, int status, int[] documents)
    {
        this.setId(id);
        this.setUserId(userId);
        this.setApproveUserId(approveUserId);
        this.setCreatedDate(createdDate);
        this.setUpdatedDate(updatedDate);
        this.setApproveDate(approveDate);
        this.setUserComment(userComment);
        this.setAdminComment(adminComment);
        this.setStatus(status);
        this.setDocuments(documents);
    }

    // Getters
    public int getId() {return id;}
    public int getUserId() {return userId;}
    public int getApproveUserId() {return approveUserId;}
    public String getCreatedDate() {return createdDate;}
    public String getUpdatedDate() {return updatedDate;}
    public String getApproveDate() {return approveDate;}
    public String getUserComment() {return userComment;}
    public String getAdminComment() {return adminComment;}
    public int getStatus() {return status;}
    public int[] getDocuments() {return documents;}
    public int getDocuments(int i) {return documents[i];}

    // Setters
    public void setId(int a) {this.id = a;}
    public void setUserId(int a) {this.userId = a;}
    public void setApproveUserId(int a) {this.approveUserId = a;}
    public void setCreatedDate(String a) {this.createdDate = a;}
    public void setUpdatedDate(String a) {this.updatedDate = a;}
    public void setApproveDate(String a) {this.approveDate = a;}
    public void setUserComment(String a) {this.userComment = a;}
    public void setAdminComment(String a) {this.adminComment = a;}
    public void setStatus(int a) {this.status = a;}
    public void setDocuments(int[] a) {this.documents = a;}
    public void setDocuments(int elementNumber, int a) {this.documents[elementNumber] = a;}

    public boolean equalsExceptUpdatedDate(Approve a, boolean toCheckCreatedDate)
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
        if (getId() != a.getId()) {
            System.out.println("3");
            return false;
        }
        if (getUserId() != a.getUserId()) {
            System.out.println("4");
            return false;
        }
        if (getApproveUserId() != a.getApproveUserId()){
            System.out.println("5");
            return false;
        }
        if (toCheckCreatedDate && !getCreatedDate().equals(a.getCreatedDate())) {
            System.out.println("6");
            return false;
        }
        if (!getApproveDate().equals(a.getApproveDate())) {
            System.out.println("7");
            return false;
        }
        if (!getUserComment().equals(a.getUserComment())) {
            System.out.println("8");
            return false;
        }
        if (!getAdminComment().equals(a.getAdminComment())) {
            System.out.println("9");
            return false;
        }
        if (getStatus() != a.getStatus()) {
            System.out.println("10");
            return false;
        }
        for (int i = 0; i < getDocuments().length; i++) {
            if (getDocuments(i) != a.getDocuments(i)) {
                System.out.println("11");
                return false;
            }
        }
        return true;
    }
}
