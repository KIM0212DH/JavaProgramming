package kr.co.javaex.ex09.java_09_3_ex1_userManager;

public class Member {
    private String id;
    private String password;
    private String name;
    private String email;
    private String phoneNum;
    private boolean status = false;

    public Member() {
    }

    public String toString() {
        String str = "ID: " + this.id + "\tPassword: " + this.password + "\tName: " + this.name + "\tEmail: " + this.email + "\tPhoneNum: " + this.phoneNum;
        return str;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return this.phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public boolean isStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
