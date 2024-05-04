package org.example.Pojo;

public class Employee {
    private String CompanyName;
    private String Ename;
    private String Eemail;
    private String Epassword;

    public Employee(String companyName,String ename, String eemail, String epassword) {
        Ename = ename;
        Eemail = eemail;
        Epassword = epassword;
        CompanyName = companyName;

    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getEname() {
        return Ename;
    }

    public void setEname(String ename) {
        Ename = ename;
    }

    public String getEemail() {
        return Eemail;
    }

    public void setEemail(String eemail) {
        Eemail = eemail;
    }

    public String getEpassword() {
        return Epassword;
    }

    public void setEpassword(String epassword) {
        Epassword = epassword;
    }
}
