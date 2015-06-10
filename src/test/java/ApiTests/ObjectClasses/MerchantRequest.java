package ApiTests.ObjectClasses;

// * Created for W-xmlm by Fill on 16.03.2015.
public class MerchantRequest {
    private int requestId;
    private int userId;
    private String[] files;
    private int status;
    private String createdDate;
    private String updatedDate;

    public MerchantRequest(int requestId, int userId, String[] files, int status, String createdDate, String updatedDate)
    {
        this.setRequestId(requestId);
        this.setUserId(userId);
        this.setFiles(files);
        this.setStatus(status);
        this.setCreatedDate(createdDate);
        this.setUpdatedDate(updatedDate);
    }

    public int getRequestId() {return requestId;}
    public int getUserId() {return userId;}
    public String[] getFiles() {return files;}
    public String getFile(int i) {return files[i];}
    public int getStatus() {return status;}
    public String getCreatedDate() {return createdDate;}
    public String getUpdatedDate() {return updatedDate;}

    public void setRequestId(int a) {this.requestId = a;}
    public void setUserId(int a) {this.userId = a;}
    public void setFiles(String[] a) {this.files = a;}
    public void setFile(int elementNumber, String a) {this.files[elementNumber] = a;}
    public void setStatus(int a) {this.status = a;}
    public void setCreatedDate(String a) {this.createdDate = a;}
    public void setUpdatedDate(String a) {this.updatedDate = a;}

    public boolean equalsExceptUpdatedDate(MerchantRequest a, boolean toCheckCreatedDate)
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
        if (getRequestId() != a.getRequestId()) {
            System.out.println("3");
            return false;
        }
        if (getUserId() != a.getUserId()) {
            System.out.println("4");
            return false;
        }
        for (int i = 0; i < getFiles().length; i++) {
            if (!getFile(i).equals(a.getFile(i))) {
                System.out.println("6");
                return false;
            }
        }
        if (getStatus() != a.getStatus()) {
            System.out.println("8");
            return false;
        }
        if (toCheckCreatedDate && !getCreatedDate().equals(a.getCreatedDate())) {
            System.out.println("9");
            return false;
        }
        return true;
    }
}
