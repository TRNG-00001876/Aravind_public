package org.example.Dao;

import org.example.Pojo.Employee;
import org.example.Pojo.Resume;
import org.example.Pojo.User;

import java.util.List;

public interface JOBDAO {

   // USER

   public String addUser(User u);

   public String login(String email, String password);

   public String createResume(String emails,String password,Resume resume);

   public String searchJob(String role, String location, int experience);

   public void applyJob(String emails,String password,int id);

   public void checkResume(String email,String passowrd);

   public String forgotPassword(String mail, String password);

   public String deleteuser(String mail);

   public String withdrawApplication(String comp);

   public List<String> appliedJobs(String mail,String password);


   //Employee

   public String loginEmployee(String emails,String password);

   public String addEmployee(Employee e);

   public String forgotPasswordEmployee(String mai,String password);

   public String deleteEmployee(String mail);

   public String addJob(String email,String password,String role,String loc,int exp,int sal,String desc);

   public List<String> fetchDetails(String mail,String password,String Developer);






}
