package org.example.Dao;

import org.example.Pojo.Employee;
import org.example.Pojo.Job;
import org.example.Pojo.Resume;
import org.example.Pojo.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JOBimpl implements JOBDAO {

    private static JOBimpl instance;
    private static Connection connection;

    // Private constructor to prevent external instantiation
    private JOBimpl(Connection connection) {
        this.connection = connection;
    }

    // Method to get the singleton instance
    public static synchronized JOBimpl getInstance(Connection connection) {
        if (instance == null) {
            instance = new JOBimpl(connection);
        }
        return instance;
    }


    // USER  Register code
    @Override
    public String addUser(User user) {
        String status = "";
        try {
            java.sql.Statement st = null;
            ResultSet rs = null;
            st = connection.createStatement();
            rs = st.executeQuery("select * from UserAdmin where U_email='" +user.getEmail()  + "';");
            boolean b = rs.next();
            if (b) {
                status = "existed";
            }
            else {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO UserAdmin VALUES (?, ?, ?)");
                ps.setString(1, user.getUname());
                ps.setString(2, user.getEmail());
                ps.setString(3, user.getPassword());
                int a = ps.executeUpdate();
                if (a > 0) {
                    status = "success";
                } else {
                    status = "failure";
                }
            }

        } catch (SQLException e) {
            System.err.println("Error adding employee: " + e.getMessage());
        }
        return status;
    }


    // LOGIN OF THE USER


    @Override
    public String login(String email, String password) {
        String status = "";
        try {
            java.sql.Statement st = null;
            ResultSet rs = null;

            st = connection.createStatement();

            rs = st.executeQuery("select * from UserAdmin where U_email='" + email + "' and U_password='" + password + "';");
            boolean b = rs.next();
            if (b == true) {
                status = "success";

            } else {

                status = "failure";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }


    @Override
    // RESUME CREATION


    public String createResume(String emails, String password, Resume r) {
        String status = "";
        try {
            // Query the U_id based on username and email
            String selectQuery = "SELECT U_id FROM UserAdmin WHERE U_email = ? AND U_password = ?";
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            selectStatement.setString(1, emails);
            selectStatement.setString(2, password);
            ResultSet resultSet = selectStatement.executeQuery();

            // Check if the user exists
            if (resultSet.next()) {
                int userId = resultSet.getInt("U_id");
                // Now insert into the ResumeBuild table
                PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO ResumeBuild (U_id, R_fname, R_lname,R_Date, R_Email, R_Contact, R_Ug, R_Branch, R_Cgpa, R_Experience, R_Skills) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                insertStatement.setInt(1, userId);
                insertStatement.setString(2, r.getFname());
                insertStatement.setString(3, r.getLname());
                insertStatement.setString(4, r.getDate());
                insertStatement.setString(5, r.getEmail());
                insertStatement.setLong(6, r.getContact());
                insertStatement.setString(7, r.getUg());
                insertStatement.setString(8, r.getBranch());
                insertStatement.setInt(9, r.getCgpa());
                insertStatement.setInt(10, r.getExper());
                insertStatement.setString(11, r.getSkills());

                int rowsAffected = insertStatement.executeUpdate();
                if (rowsAffected > 0) {
                    status = "success";
                } else {
                    status = "failure";
                }
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return status;
    }



    // SEARCH FOR THE JOB


    public String searchJob(String role, String location, int experience) {
        List<Job> jobs = new ArrayList<>();
        String status = "";
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Job WHERE J_Role = ? AND J_Location = ? AND J_Exper <= ?");
            stmt.setString(1, role);
            stmt.setString(2, location);
            stmt.setInt(3, experience);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("J_Id");
                String rol = rs.getString("J_Role");
                String loc = rs.getString("J_Location");
                int exper = rs.getInt("J_Exper");
                String Companyname = rs.getString("J_Companyname");
                int sal = rs.getInt("Sal");
                String desc = rs.getString("Descrption");
                System.out.println("JobId :" + id + ", Role: " + rol + ", Location: " + loc + ", Experience: " + exper + ", CompanyName: " + Companyname+", Salary: "+sal+", Description: "+desc);
                status = "success";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            status = "failure";
        }
        return status;
    }


    // APPLY FOR THE JOB



    public void applyJob(String emails, String password, int id) {
        String firstname = "";
        String Llastname = "";
        String email = "";
        String contact = "";
        String ug = "";
        String branch = "";
        int exp = 0;
        String skills = "";
        String jobrole = "";
        String joblocation = "";
        String companyname = "";

        try {
            java.sql.Statement st = null;
            ResultSet rs1 = null;
            st = connection.createStatement();
            rs1 = st.executeQuery("select J_Role,J_Location,J_Companyname from Job where J_Id='" + id + "';");
            if(rs1.next())
            {
                jobrole = rs1.getString("J_Role");
                joblocation = rs1.getString("J_Location");
                companyname =rs1.getString("J_Companyname");
                //System.out.println("Job ROle :"+jobrole + "  job Location :"+joblocation+" companyname :"+companyname);
            }

            String selectQuery = "SELECT U_id FROM UserAdmin WHERE U_email = ? AND U_password = ?";
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            selectStatement.setString(1, emails);
            selectStatement.setString(2, password);
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt("U_id");
                System.out.println(userId);
                String resume = "select R_fname,R_lname,R_Email,R_Contact,R_Ug,R_Branch,R_Experience,R_Skills from ResumeBuild where U_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(resume);
                preparedStatement.setInt(1,userId);
                ResultSet rs = preparedStatement.executeQuery();
                if(rs.next()) {
                    firstname = rs.getString("R_fname");
                    Llastname = rs.getString("R_lname");
                    email = rs.getString("R_Email");
                    contact = rs.getString("R_Contact");
                    ug = rs.getString("R_Ug");
                    branch = rs.getString("R_Branch");
                    exp = rs.getInt("R_Experience");
                    skills = rs.getString("R_Skills");
                    //System.out.println(firstname);
                }
                PreparedStatement ps = connection.prepareStatement("INSERT INTO JOBAPPLICATIONS VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                ps.setInt(1, userId);
                ps.setInt(2, id);
                ps.setString(3,jobrole);
                ps.setString(4,joblocation);
                ps.setString(5,companyname);
                ps.setString(6,firstname);
                ps.setString(7,Llastname);
                ps.setString(8,email);
                ps.setString(9,contact);
                ps.setString(10,ug);
                ps.setString(11,branch);
                ps.setInt(12,exp);
                ps.setString(13,skills);
                int a = ps.executeUpdate();
                if (a > 0) {
                    System.out.println("Applied Successfully");
                } else {
                    System.out.println("Failed");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // RESUME DETAILS


    public void checkResume(String mail,String pass) {
        try {
            String selectQuery = "SELECT U_id FROM UserAdmin WHERE U_email = ? AND U_password = ?";
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            selectStatement.setString(1, mail);
            selectStatement.setString(2, pass);
            ResultSet resultSet = selectStatement.executeQuery();

            // Check if the user exists
            if (resultSet.next()) {
                int userId = resultSet.getInt("U_id");

                PreparedStatement ps = connection.prepareStatement("SELECT * FROM ResumeBuild WHERE U_id = ?");
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int rid = rs.getInt("R_id");
                    String firstname  = rs.getString("R_fname");
                    String lastName = rs.getString("R_lname");
                    String date = rs.getString("R_Date");
                    String email = rs.getString("R_Email");
                    String higher = rs.getString("R_Ug");
                    String br = rs.getString("R_Branch");
                    int cgpa = rs.getInt("R_Cgpa");
                    int exp = rs.getInt("R_Experience");
                    String skill = rs.getString("R_Skills");
                    System.out.println("Resume Id: " + rid);
                    System.out.println("Firstname: "+firstname);
                    System.out.println("Lastname: "+lastName);
                    System.out.println("Date: "+date);
                    System.out.println("Email: "+email);
                    System.out.println("HigherStudies: "+higher);
                    System.out.println("Branch: "+br);
                    System.out.println("Cgpa :"+cgpa);
                    System.out.println("Experience: "+exp);
                    System.out.println("Skill: "+skill);

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




    // DELETION OF THE USER



    @Override
    public String deleteuser(String mail)
    {
        java.sql.Statement st = null;
        String status = "";
        try {
            String selectQuery = "SELECT U_id FROM UserAdmin WHERE U_email = ?";
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            selectStatement.setString(1, mail);
            ResultSet resultSet = selectStatement.executeQuery();
            // Check if the user exists
            if (resultSet.next()) {
                int userId = resultSet.getInt("U_id");
                st = connection.createStatement();
                 int count = st.executeUpdate("delete from UserAdmin where "+ "U_id='" +userId + "'");
                if (count > 0) {
                    status = "success";
                } else {
                    status = "failure";
                }
            }
        }
        catch(SQLException e)
        {
            throw new RuntimeException(e);

        }
        return status;
    }



    // FORGOT PASSWORD OF THE USER


    @Override
    public String forgotPassword(String mail,String password) {
        String status="";
        try {
            java.sql.Statement st = connection.createStatement();
            int rspw = st.executeUpdate("update UserAdmin set U_password='" + password+ "' where U_email='" + mail + "';");
            if (rspw > 0)
                status = "success";
            else
                status = "failure";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }



    // USER WHAT AND ALL COMPANIES APPLIED



    @Override
    public List<String> appliedJobs(String mail,String password)
    {
        List<String> resultList = new ArrayList<>();

        try {
            String selectQuery = "SELECT U_id FROM UserAdmin WHERE U_email = ? AND U_password = ?";
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            selectStatement.setString(1, mail);
            selectStatement.setString(2, password);
            ResultSet resultSet = selectStatement.executeQuery();

            // Check if the user exists
            if (resultSet.next()) {
                int userId = resultSet.getInt("U_id");
                java.sql.Statement st = null;
                ResultSet rs = null;
                st = connection.createStatement();
                rs = st.executeQuery("SELECT * FROM JOBAPPLICATIONS WHERE U_id='" + userId + "';");
                while (rs.next()) {
                    int jobid = rs.getInt("J_Id");
                    String jobRole = rs.getString("JobRole");
                    String joblocation = rs.getString("JobLocation");
                    String companyNameFromJobApp = rs.getString("CompanyName");
                    String skills = rs.getString("Skills");
                        String userDetails ="JobRole: "+jobRole+
                                ", JobLocation: " + joblocation +"CompanyName: "+companyNameFromJobApp+ ", Skills: " + skills;
                        resultList.add(userDetails);
                    }
                }



            } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }



    // USER WITH DRAW APPLICATION


    @Override
    public String withdrawApplication(String comp)
    {
        java.sql.Statement st = null;
        String status = "";
        try {
                st = connection.createStatement();
                int count = st.executeUpdate("delete from JobApplications where "+ "CompanyName='" +comp + "'");
                if (count > 0) {
                    status = "success";
                } else {
                    status = "failure";
                }
            } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }





    //Employee Details

    @Override
    public String loginEmployee(String emails, String password) {
        String status = "";
        try {
            java.sql.Statement st = null;
            ResultSet rs = null;
            st = connection.createStatement();
            rs = st.executeQuery("select * from Employee where E_Email='" + emails + "' and E_Password='" + password + "';");
            boolean b = rs.next();
            if (b == true) {
                status = "success";
            } else {
                status = "failure";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;

    }


    //  EMPLOYEE REGISTRATION




    @Override
    public String addEmployee(Employee emp) {
        String status = "";
        try {
            java.sql.Statement st = null;
            ResultSet rs = null;
            st = connection.createStatement();
            rs = st.executeQuery("select * from Employee where E_Email ='" +emp.getEemail() + "';");
            boolean b = rs.next();
            if (b) {
                status = "existed";
            }
            else {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO Employee VALUES (?, ?, ?, ?)");
                ps.setString(1, emp.getCompanyName());
                ps.setString(2, emp.getEname());
                ps.setString(3, emp.getEemail());
                ps.setString(4, emp.getEpassword());
                int a = ps.executeUpdate();
                if (a > 0) {
                    status = "success";
                } else {
                    status = "failure";
                }
            }

        } catch (SQLException e) {
            System.err.println("Error adding employee: " + e.getMessage());
        }
        return status;


    }




    // FOR GOT PASSWORD OF THE EMPLOYEE

    @Override
    public String forgotPasswordEmployee(String mail,String Password)
    {
        String status="";
        try {
            java.sql.Statement st = connection.createStatement();
            int rspw = st.executeUpdate("update Employee set E_Password='" + Password+ "' where E_Email='" + mail + "';");
            if (rspw > 0)
                status = "success";
            else
                status = "failure";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;

    }

    @Override



    // DELETE PASSWORD OF THE EMPLOYEE



    public String deleteEmployee(String mail)
    {
        java.sql.Statement st = null;
        String status = "";
        try {
            String selectQuery = "SELECT E_Id FROM Employee WHERE E_Email = ?";
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            selectStatement.setString(1, mail);
            ResultSet resultSet = selectStatement.executeQuery();
            // Check if the user exists
            if (resultSet.next()) {
                int EmpId = resultSet.getInt("E_Id");
                st = connection.createStatement();
                int count = st.executeUpdate("delete from Employee where "+ "E_Id='" +EmpId + "'");
                if (count > 0) {
                    status = "success";
                } else {
                    status = "failure";
                }
            }
        }
        catch(SQLException e)
        {
            throw new RuntimeException(e);

        }
        return status;

    }




    // POST THE JOB FROM THE EMPLOYEE




    @Override
    public String addJob(String email,String password,String role, String loc, int exp,int sal,String desc) {

        String status = "";
        try {
            String selectQuery = "SELECT E_Id,E_CompanyName FROM Employee WHERE E_Email = ? AND E_password = ?";
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            selectStatement.setString(1, email);
            selectStatement.setString(2, password);
            ResultSet resultSet = selectStatement.executeQuery();

            // Check if the user exists
            if (resultSet.next()) {
                String company = resultSet.getString("E_CompanyName");
                int Eid = resultSet.getInt("E_Id");
                PreparedStatement ps = connection.prepareStatement("insert into Job values (?,?,?,?,?,?,?)");
                ps.setString(1, role);
                ps.setString(2, loc);
                ps.setInt(3, exp);
                ps.setString(4, company);
                ps.setInt(5,Eid);
                ps.setInt(6,sal);
                ps.setString(7,desc);
                int a = ps.executeUpdate();
                if (a > 0) {
                    status = "success";
                } else {
                    status = "failure";
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }





    // APPLICATION FOR THE SPECIFIC ROLE




    @Override
    public List<String> fetchDetails(String mail, String password, String developer) {
        List<String> resultList = new ArrayList<>();
        try {
            String selectQuery = "SELECT E_CompanyName FROM Employee WHERE E_Email = ? AND E_password = ?";
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            selectStatement.setString(1, mail);
            selectStatement.setString(2, password);
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                String company = resultSet.getString("E_CompanyName");
                java.sql.Statement st = null;
                ResultSet rs = null;
                st = connection.createStatement();
                rs = st.executeQuery("SELECT * FROM JOBAPPLICATIONS WHERE JobRole='" + developer + "';");
                while (rs.next()) {
                    int userid = rs.getInt("U_Id");
                    int jobid = rs.getInt("J_Id");
                    String joblocation = rs.getString("JobLocation");
                    String firstname = rs.getString("FirstName");
                    String lastname = rs.getString("LastName");
                    String email = rs.getString("Email");
                    String contact = rs.getString("Contact");
                    String hiherstudies = rs.getString("HigherStudies");
                    String branch = rs.getString("Branch");
                    int ep = rs.getInt("Experience");
                    String skills = rs.getString("Skills");
                    String companyNameFromJobApp = rs.getString("CompanyName");
                    if (company.equals(companyNameFromJobApp)) {
                        String userDetails = "UserId: " + userid + ", JobId: " + jobid +
                                ", JobLocation: " + joblocation + ", FirstName: " + firstname + ", LastName: " + lastname +
                                ", Email: " + email + ", Contact: " + contact + ", HiherStudies: " + hiherstudies +
                                ", Branch: " + branch + ", Skills: " + skills;
                        resultList.add(userDetails);
                    }
                }

                return resultList;
            }





        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // If no company name was found or no results were found in the job applications for the given developer, return an empty list
        return resultList;
    }
}

