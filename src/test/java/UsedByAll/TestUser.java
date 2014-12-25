package UsedByAll;

public class TestUser
{
    private String email;
    private String mailerURL;
    private String ePassword;
    private String fullName ;
    private String password1;
    private String password2;
    private String newPassword1;
    private String newPassword2;
    private String phone;
    private String inviteCode;
    private String useInTest;

    // Сеттеры
    public void setEmail(String a)
    { this.email = a; }
    public void setNewEmail(String a)
    { this.mailerURL = a; }
    public void setEPassword(String a)
    { this.ePassword = a; }
    public void setFullName(String a)
    { this.fullName = a; }
    public void setPassword1(String a)
    { this.password1 = a; }
    public void setPassword2(String a)
    { this.password2 = a; }
    public void setNewPassword1(String a)
    { this.newPassword1 = a; }
    public void setNewPassword2(String a)
    { this.newPassword2 = a; }
    public void setPhone(String a)
    { this.phone = a; }
    public void setInviteCode(String a)
    { this.inviteCode = a; }
    public void setUseInTest(String a)
    { this.useInTest = a; }

    // Геттеры
    public String getEmail()
    { return email; }
    public String getNewEmail()
    { return mailerURL; }
    public String getEPassword()
    { return ePassword; }
    public String getFullName()
    { return fullName; }
    public String getPassword1()
    { return password1; }
    public String getPassword2()
    { return password2; }
    public String getNewPassword1()
    { return newPassword1; }
    public String getNewPassword2()
    { return newPassword2 ; }
    public String getPhone()
    { return phone ; }
    public String getInviteCode()
    { return inviteCode ; }
    public String getUseInTest()
    { return useInTest ; }
}