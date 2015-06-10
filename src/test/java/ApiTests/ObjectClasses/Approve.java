package ApiTests.ObjectClasses;

import java.util.Objects;

// * Created for W-xmlm by Fill on 05.03.2015.
public class Approve {
    private int id;
    private int userId;
    private Object approveUserId;
    private String createDate;
    private String updateDate;
    private Object approveDate;
    private int status;
    private int[] documents;

    // Constructors
    public Approve(int id, int userId, Object approveUserId, String createDate, String updateDate, Object approveDate, int status, int[] documents)
    {
        this.setId(id);
        this.setUserId(userId);
        this.setApproveUserId(approveUserId);
        this.setCreateDate(createDate);
        this.setUpdateDate(updateDate);
        this.setApproveDate(approveDate);
        this.setStatus(status);
        this.setDocuments(documents);
    }

    // Getters
    public int getId() {return id;}
    public int getUserId() {return userId;}
    public Object getApproveUserId() {return approveUserId;}
    public String getCreateDate() {return createDate;}
    public String getUpdateDate() {return updateDate;}
    public Object getApproveDate() {return approveDate;}
    public int getStatus() {return status;}
    public int[] getDocuments() {return documents;}
    public int getDocuments(int i) {return documents[i];}

    // Setters
    public void setId(int a) {this.id = a;}
    public void setUserId(int a) {this.userId = a;}
    public void setApproveUserId(Object a) {this.approveUserId = a;}
    public void setCreateDate(String a) {this.createDate = a;}
    public void setUpdateDate(String a) {this.updateDate = a;}
    public void setApproveDate(Object a) {this.approveDate = a;}
    public void setStatus(int a) {this.status = a;}
    public void setDocuments(int[] a) {this.documents = a;}
    public void setDocuments(int elementNumber, int a) {this.documents[elementNumber] = a;}

    public boolean equalsExceptUpdatedDateAndApproveUserId(Approve a, boolean toCheckCreateDate)
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
        if (toCheckCreateDate && !getCreateDate().equals(a.getCreateDate())) {
            System.out.println("6");
            return false;
        }
        if (!getApproveDate().equals(a.getApproveDate())) {
            System.out.println("7");
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
