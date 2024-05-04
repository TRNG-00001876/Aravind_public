package org.example.Pojo;

public class Job {

    private String role;
    private String location;
    private int experience;
    private String companyName;
    private int salary;
    private String description;


    public Job(String role, String location, int experience, String companyName, int salary, String description) {
        this.role = role;
        this.location = location;
        this.experience = experience;
        this.companyName = companyName;
        this.salary = salary;
        this.description = description;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Job() {

    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
