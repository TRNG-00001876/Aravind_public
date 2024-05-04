package org.example.Pojo;

public class User {

    private String uname;
    private String email;
    private String password;

    public User(String uname, String email, String password) {
        this.uname = uname;
        this.email = email;
        this.password = password;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
