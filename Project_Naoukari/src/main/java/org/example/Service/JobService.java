package org.example.Service;

import org.example.Dao.JOBDAO;
import org.example.Pojo.Employee;
import org.example.Pojo.Resume;
import org.example.Pojo.User;

import java.util.List;

public class JobService {
    private JOBDAO jobDao;


    public JobService(JOBDAO jobDao){
        this.jobDao = jobDao;
    }



//    USER

    // Creation user


    public String addUser(User user) {
      return  jobDao.addUser(user);
    }
    // USER  login code
    public String  login(String email,String password)
    {
       return   jobDao.login(email,password);
    }


    // USER RESUME CREATION

    public String createResume(String emails,String password,Resume r)
    {
     return   jobDao.createResume(emails,password,r);
    }


    // SEARCH JOB


    public String searchJob(String role, String loc, int experience)
    {
     return   jobDao.searchJob(role,loc,experience);
    }


    // APPLY FOR THE JOB


    public void applyJob(String emails,String password,int jobid)
    {
        jobDao.applyJob(emails,password,jobid);
    }
    // CHECK RESUME
    public void checkResume(String email,String password)
    {
        jobDao.checkResume(email,password);
    }

    // FORGOT PASS WORD OF THE USER
    public String forgotPassword(String mail,String password)
    {
     return jobDao.forgotPassword(mail,password);
    }

    // DELETE USER


    public String deleteUser(String mail)
    {
       return  jobDao.deleteuser(mail);
    }

    // FETCH THE ALL THE APPLIED JOBS


    public List<String> appliedJobs(String mail,String password)
    {
        List<String> details =jobDao.appliedJobs(mail,password);
        return details;
    }

//    WITH DRAW APPLICATION

    public String withdrawApplication(String comp)
    {
       return  jobDao.withdrawApplication(comp);
    }






    // Employee


    // EMPLOYEE LOGIN

    public String loginEmployee(String emails,String password)
    {
        return jobDao.loginEmployee(emails,password);
    }


    // EMPLOYEE REGISTRATION

    public String addEmployee(Employee e)
    {
        return jobDao.addEmployee(e);
    }


    // DELETE EMPLOYEE

    public String deleteEmployee(String mail)
    {
        return jobDao.deleteEmployee(mail);
    }

    // POST  THE JOB


    public String addJob(String mail,String password,String role,String loc,int exp,int sal,String desc)
    {
        return jobDao.addJob(mail,password,role,loc,exp,sal,desc);
    }

    // FOR GET THE PASSWORD OF THE EMPLOYEE


    public String forgotPasswordEmployee(String mail,String password)
    {
        return jobDao.forgotPasswordEmployee(mail,password);
    }

    // APPLIED FOR THE SPECIFIC ROLE


    public List<String> fetchDetails(String mail,String password,String develop) {
        List<String> details = jobDao.fetchDetails(mail,password,develop);
        return details;
    }
}
