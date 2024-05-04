package org.example;

import org.example.Dao.JOBDAO;
import org.example.Dao.JOBimpl;
import org.example.Pojo.Employee;
import org.example.Pojo.Job;
import org.example.Pojo.Resume;
import org.example.Pojo.User;
import org.example.Service.JobService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final String DRIVER_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String URL = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=Noukari;integratedSecurity=true;encrypt=true;trustServerCertificate=true";
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        logger.info("Application  started");
        try{
            Class.forName(DRIVER_NAME);
            Connection connection = DriverManager.getConnection(URL);
            System.out.println("connected");
            JOBDAO jobdao  = JOBimpl.getInstance(connection);
            JobService jservice = new JobService(jobdao);
            Scanner s = new Scanner(System.in);
            String usernameRegex = "^[a-zA-Z0-9_-]{3,12}$"; // Allows letters, numbers, underscores, and hyphens, 3-16 characters long
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
            String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$";
            // job seeker details
            String emails = "";
            String password = "";


            // Employee details
            String empemails = "";
            String emppassword = "";
            // object creation for the  resume
            Resume r = null;
            // create object for the job
            Job job = null;
            // create object for the Employee
            Employee e = null;

            String company="";
            while(true)
            {
                System.out.println("1.Job Seeker");
                System.out.println("2.Employee");
                System.out.println("3. Exit");
                System.out.println("Enter the Choice: ");
                int ch  = s.nextInt();


                // USER

                switch(ch)
                {
                    case 1:
                        System.out.println("Welcome to Job Portal - Job Seeker Section");
                        System.out.println("1.Login");
                        System.out.println("2.Create Account");
                        System.out.println("3.Forgot Password");
                        System.out.println("4.Delete");
                        System.out.println("4. Exit");
                        System.out.print("Enter your choice: ");
                         int choice = s.nextInt();
                         switch (choice)
                         {

                             // USER LOGIN



                             case 1:
                                 System.out.println("Enter the Email: ");
                                 emails = s.next();
                                 System.out.println("Enter the Password: ");
                                 password = s.next();
                                 String status =  jservice.login(emails,password);
                                 if(status.equals("success")) {
                                     System.out.println("Successfully login");
                                     System.out.println("1.Create Resume");
                                     System.out.println("2.Search jobs");
                                     System.out.println("3.CheckResume");
                                     System.out.println("4.Jobs Applied");
                                     System.out.println("5.WithDraw Your Application");
                                     System.out.println("6.Exit");
                                     System.out.println("Enter your Choice");
                                     int CHOICE = s.nextInt();

                                     // RESUME CREATION


                                     switch (CHOICE) {
                                         case 1:
                                             System.out.println("Enter the FirstName: ");
                                             String fname = s.next();
                                             System.out.println("Enter the LastName: ");
                                             String lname = s.next();
                                             System.out.println("Enter the Date-of-Birth: ");
                                             String date = s.next();
                                             System.out.println("Enter your personal Email: ");
                                             String email = s.next();
                                             System.out.println("Enter the ContactNumber: ");
                                             long phone = s.nextLong();
                                             System.out.println("Enter the Highest Education: ");
                                             String ug = s.next();
                                             System.out.println("Enter the Branch: ");
                                             String br = s.next();
                                             System.out.println("Enter the CGPA or PERCENTAGE: ");
                                             int cgpa = s.nextInt();
                                             System.out.println("Enter the Experience: ");
                                             int exp = s.nextInt();
                                             System.out.println("Enter the Skills: ");
                                             String skills = s.next();
                                             r = new Resume(fname, lname,date, email, phone, ug, br, cgpa, exp, skills);
                                           String create =  jservice.createResume(emails, password, r);
                                           if(create.equals("success"))
                                           {
                                               System.out.println("Successfully Resume Created");
                                           }
                                           else if(create.equals("failure"))
                                           {
                                               System.out.println("Not created");
                                           }
                                             break;

                                           // SEARCH FOR JOBS


                                         case 2:
                                             System.out.println("Enter the Role: ");
                                             String role = s.next();
                                             System.out.println("Enter the location: ");
                                             String loc = s.next();
                                             System.out.println("Enter the Experience: ");
                                             int exper = s.nextInt();
                                            // job = new Job(role,loc,exper);
                                           String search =   jservice.searchJob(role,loc,exper);
                                           if(search.equals("success"))
                                           {
                                               System.out.println("10.Apply for JobBased on the Job_Id which you are Seeing");
                                               System.out.println("Enter your choice: ");
                                               int app = s.nextInt();
                                               switch(app)
                                               {
                                                   case 10:
                                                       System.out.println("Enter Job_Id");
                                                       int jobid = s.nextInt();
                                                       jservice.applyJob(emails,password,jobid);
                                                       break;
                                               }
                                           }
                                           break;

                                         // RESUME DETAILS

                                         case 3:
                                             jservice.checkResume(emails,password);
                                             break;

                                             // WHAT AND ALL COMPANIES
                                                // USER APPLIED
                                         case 4:
                                           List<String> applied =   jservice.appliedJobs(emails,password);
                                             if (!applied.isEmpty()) {
                                                 System.out.println("Details fetched successfully!");
                                                 for (String userDetails : applied) {
                                                     System.out.println(userDetails);
                                                 }
                                             } else {
                                                 // Process failure
                                                 System.out.println("No details found for the company.");
                                             }
                                             break;

                                             // WITH DRAW APPLICATION FOR THE COMPANY
                                         case 5:

                                         {
                                             List<String> applie =   jservice.appliedJobs(emails,password);
                                             if (!applie.isEmpty()) {
                                                 System.out.println("APPLIED JOBS!");
                                                 for (String userDetails : applie) {
                                                     System.out.println(userDetails);
                                                 }
                                             }
                                             System.out.println("   ");
                                             System.out.println("1.Choose your option to withdraw your application ");
                                             int choose = s.nextInt();
                                             switch(choose)
                                             {
                                                 case 1:
                                                     System.out.println("Enter the Company Name: ");
                                                     String comp = s.next();
                                                    String draw = jservice.withdrawApplication(comp);
                                                    if(draw.equals("success"))
                                                    {
                                                        System.out.println("With draw Successfully");
                                                    }
                                                    else{
                                                        System.out.println("With draw failed");
                                                    }
                                             }

                                         }
                                         default:
                                             throw new IllegalStateException("Unexpected value: " + CHOICE);
                                     }

                                 }
                                 else if(status.equals("failure")){
                                     System.out.println("login failure");
                                 }
                                 break;


                                 // CREATE ACCOUNT FOR THE USER


                             case 2:
                                 System.out.println("Enter the UserName(Username must be 3-16 characters long and can only contain letters, numbers, underscores, and hyphens.): ");
                                 String uname = s.next();
                                 System.out.println("Enter the Email: ");
                                 String email = s.next();
                                 System.out.println("Enter the Password(Password must be at least 6 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character): ");
                                 String pass = s.next();
                                 System.out.println("Enter the Confirm-PassWord: ");
                                 String conf = s.next();
                                 if (!uname.matches(usernameRegex)) {
                                     System.out.println("Invalid username format. Username must be 3-16 characters long and can only contain letters, numbers, underscores, and hyphens.");
                                     return;
                                 }
                                 if (!email.matches(emailRegex)) {
                                     System.out.println("Invalid email format.");
                                     return;
                                 }
                                 if (!pass.equals(conf)) {
                                     System.out.println("Passwords do not match.");
                                     return;
                                 }
                                 if (!pass.matches(passwordRegex)) {
                                     System.out.println("Invalid password format. Password must be at least 6 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.");
                                     return;
                                 }
                                 User u = new User(uname,email,pass);
                                String sttus =  jservice.addUser(u);
                                if(sttus.equals("existed"))
                                {
                                    System.out.println("Existed Record");
                                }
                                else if (sttus.equals("success"))
                                {
                                    System.out.println("Registered Successfully");
                                }
                                else {
                                    System.out.println("Register Failed");
                                }
                                 break;

                                // FORGOT PASSWORD OF THE USER  //////

                             case 3:
                             {
                                 System.out.println("Enter the E-mail: ");
                                 String mail = s.next();
                                 System.out.println("Enter the PassWord: ");
                                 String passw = s.next();
                                 System.out.println("Re-Enter the Password: ");
                                 String passwords = s.next();
                                String Status =  jservice.forgotPassword(mail,passw);
                                if(Status.equals("success"))
                                {
                                    System.out.println("Successfully updated");
                                }
                                else {
                                    System.out.println("Failed to updated");
                                }
                                break;
                             }


                             //DELETE THE USER ACCOUNT


                             case 4:
                             {
                                 System.out.println("Enter your Email: ");
                                 String mail = s.next();
                             String delete =    jservice.deleteUser(mail);
                             if(delete.equals("success"))
                             {
                                 System.out.println("Deleted Successfully");
                             }
                             else {
                                 System.out.println("Deletion Failed");
                             }
                             }
                             break;
                         }
                         break;





                         // EMPLOYEE PORTAL


                    //  EMPLOYEE  PORTAL
                    case 2:
                        System.out.println("Welcome to Portal - Employee Section");
                        System.out.println("1.Login");
                        System.out.println("2.Create Account");
                        System.out.println("3.ForgotPassword");
                        System.out.println("4.Delete Your Account");
                        System.out.println("4.Exit");
                        System.out.println("Enter the Choice: ");
                        int choices = s.nextInt();
                        switch(choices)
                        {

                            // EMPLOYEE LOGIN PORTAL


                            case 1:
                                System.out.println("Enter the Email: ");
                                empemails = s.next();
                                System.out.println("Enter the Password: ");
                                emppassword = s.next();
                                String status =  jservice.loginEmployee(empemails,emppassword);
                                if(status.equals("success"))
                                {
                                    System.out.println("Successfully login");
                                    System.out.println("1.Post the Job ");
                                    System.out.println("2.view the Applications");
                                    System.out.println("Enter the Choice: ");
                                    int Choices = s.nextInt();
                                    switch(Choices)
                                    {


                                        // POST THE JOB BY EMPLOYEE



                                        case 1:
                                        System.out.println("Enter the Role: ");
                                        String role = s.next();
                                        System.out.println("Enter the location: ");
                                        String loc = s.next();
                                        System.out.println("Enter the Experience: ");
                                        int exp = s.nextInt();
                                        System.out.println("Enter the Salary: ");
                                        int sal = s.nextInt();
                                        System.out.println("Enter the Description: ");
                                        String desc = s.nextLine();
                                        job = new Job(role,loc,exp,company,sal,desc);
                                        String satus = jservice.addJob(empemails,emppassword,role,loc,exp,sal,desc);

                                        if(satus.equals("success"))
                                        {
                                            System.out.println("Successfully Posted the Company");
                                        }
                                        else {
                                            System.out.println("Applied failed");
                                        }
                                        break;

                                        // VIEW WHO AND ALL APPLIED FOR THAT SPECIFIC ROLE
                                        case 2:
                                          //  System.out.println("Check based on which u want to  ");
                                            System.out.println("Enter the Role: ");
                                            String develop = s.next();
                                            List<String> fetch = jservice.fetchDetails(empemails,emppassword,develop);
                                            if (!fetch.isEmpty()) {
                                                System.out.println("Details fetched successfully!");
                                                for (String userDetails : fetch) {
                                                    System.out.println(userDetails);
                                                }
                                            } else {
                                                // Process failure
                                                System.out.println("No details found for the company.");
                                            }
                                    }
                                }
                                else {
                                    System.out.println("login failed");
                                }
                                break;


                                // EMPLOYEE ACCOUNT REGISTRATION



                            case 2:
                                System.out.println("Enter the Company Name: ");
                                 company = s.next();
                                System.out.println("Enter the UserName(Username must be 3-16 characters long and can only contain letters, numbers, underscores, and hyphens.): ");
                                String empname = s.next();
                                System.out.println("Enter the your Emails: ");
                                String empmail = s.next();
                                System.out.println("Enter the Password( Password must be at least 6 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character): ");
                                String emppasword  = s.next();
                                System.out.println("Confirm Password: ");
                                String empconfirm = s.next();
                                if (!empname.matches(usernameRegex))
                                {
                                    System.out.println("Invalid username format. Username must be 3-16 characters long and can only contain letters, numbers, underscores, and hyphens.");
                                    return;
                                }
                                if (!empmail.matches(emailRegex))
                                {
                                    System.out.println("Invalid email format.");
                                    return;
                                }
                                if (!emppasword.equals(empconfirm)) {
                                    System.out.println("Passwords do not match.");
                                    return;
                                }
                                if (!emppasword.matches(passwordRegex)) {
                                    System.out.println("Invalid password format. Password must be at least 6 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.");
                                    return;
                                }
                                e = new Employee(company,empname,empmail,emppasword);
                                String statuss = jservice.addEmployee(e);
                                if(statuss.equals("success"))
                                {
                                    System.out.println("Successfully Registered for the Employee");
                                } else if (statuss.equals("existed")) {
                                    System.out.println("Existed record");
                                    
                                } else
                                {
                                    System.out.println("Failed to register");
                                }
                                break;

                                // FOR GOT PASSWORD OF THE EMPLOYEE



                            case 3:
                                System.out.println("Enter the E-mail: ");
                                String Email = s.next();
                                System.out.println("Enter the PassWord: ");
                                String Epassw = s.next();
                                System.out.println("Re-Enter the Password: ");
                                String passwords = s.next();
                                String Status =  jservice.forgotPasswordEmployee(Email,Epassw);
                                if(Status.equals("success"))
                                {
                                    System.out.println("Successfully updated");
                                }
                                else
                                {
                                    System.out.println("Failed to updated");
                                }
                                break;

                                // DELETE ACCOUNT OF THE EMPLOYEE

                            case 4:
                                System.out.println("Enter your E-mail");
                                String mail = s.next();
                                String delete =jservice.deleteEmployee(mail);
                                if(delete.equals("success"))
                                {
                                    System.out.println("Deleted Successfully");
                                }
                                else
                                {
                                    System.out.println("Deletion Failed");
                                }
                        }
                        break;
                        }
                        break;
                }

        }
        catch (SQLException |ClassNotFoundException e)
        {
            System.out.println("Connection failed. Error: " + e.getMessage());
        }

    }
}