package org.example.Pojo;

public class Resume {
    private String fname;

    private String lname;

    private String email;

    private long contact;

    private String ug;

    private String branch;

    private int cgpa;

    private int exper;

    private String skills;
    private String date;

    public Resume(String fname, String lname,String date, String email, long contact, String ug, String branch, int cgpa, int exper, String skills) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.contact = contact;
        this.ug = ug;
        this.branch = branch;
        this.cgpa = cgpa;
        this.exper = exper;
        this.skills = skills;
        this.date = date;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getContact() {
        return contact;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }

    public String getUg() {
        return ug;
    }

    public void setUg(String ug) {
        this.ug = ug;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public int getCgpa() {
        return cgpa;
    }

    public void setCgpa(int cgpa) {
        this.cgpa = cgpa;
    }

    public int getExper() {
        return exper;
    }

    public void setExper(int exper) {
        this.exper = exper;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
