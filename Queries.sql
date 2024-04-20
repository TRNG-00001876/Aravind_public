use Revature;

create table Employee(
Empcode int,
EmpFname varchar(20),
EmpLname varchar(20),
Job varchar(40),
Manager varchar(20),
HireDate Date,
Salary int,
Commission int,
DeptCode int);

select * from Employee;

create table Department(
Deptcode int,
DeptName varchar(30),
Location varchar(40));

select * from Department;

--2. Alter Department by adding primary key constraint to Deptcode.
ALTER TABLE Department
ALTER COLUMN Deptcode INT NOT NULL;

Alter table Department Add constraint PK_Department Primary key(Deptcode);

-- 3. Alter Department table add unique constraint to DeptName.
Alter table Department
ADD CONSTRAINT UK_DeptName UNIQUE (DeptName);

-- 4. Alter Department table add constraint not null for location.

Alter table Department ALTER COLUMN Location VARCHAR(40) NOT NULL; 


--  5. Alter Employee table  by adding primary key constraint to Empcode.

ALTER TABLE Employee
ALTER COLUMN Empcode INT NOT NULL;


ALTER TABLE Employee
ADD CONSTRAINT PK_Employee PRIMARY KEY (Empcode);

-- 6. Alter Employee table add foreign key constraint to deptcode

ALTER TABLE Employee
ADD CONSTRAINT FK_Employee_DeptCode FOREIGN KEY (DeptCode)
REFERENCES Department(Deptcode);

INSERT INTO Department VALUES (10, 'FINANCE', 'EDINBURGH'),
                              (20,'SOFTWARE','PADDINGTON'),
                              (30, 'SALES', 'MAIDSTONE'),
                              (40,'MARKETING', 'DARLINGTON'),
                              (50,'ADMIN', 'BIRMINGHAM');


select * from Department;


INSERT INTO Employee  
VALUES (9369, 'TONY', 'STARK', 'SOFTWARE ENGINEER', 7902, '1980-12-17', 2800,0,20),
       (9499, 'TIM', 'ADOLF', 'SALESMAN', 7698, '1981-02-20', 1600, 300,30),    
       (9566, 'KIM', 'JARVIS', 'MANAGER', 7839, '1981-04-02', 3570,0,20),
       (9654, 'SAM', 'MILES', 'SALESMAN', 7698, '1981-09-28', 1250, 1400, 30),
       (9782, 'KEVIN', 'HILL', 'MANAGER', 7839, '1981-06-09', 2940,0,10),
       (9788, 'CONNIE', 'SMITH', 'ANALYST', 7566, '1982-12-09', 3000,0,20),
       (9839, 'ALFRED', 'KINSLEY', 'PRESIDENT', 7566, '1981-11-17', 5000,0, 10),
       (9844, 'PAUL', 'TIMOTHY', 'SALESMAN', 7698, '1981-09-08', 1500,0,30),
       (9876, 'JOHN', 'ASGHAR', 'SOFTWARE ENGINEER', 7788, '1983-01-12',3100,0,20),
       (9900, 'ROSE', 'SUMMERS', 'TECHNICAL LEAD', 7698, '1981-12-03', 2950,0, 20),
       (9902, 'ANDREW', 'FAULKNER', 'ANAYLYST', 7566, '1981-12-03', 3000,0, 10),
       (9934, 'KAREN', 'MATTHEWS', 'SOFTWARE ENGINEER', 7782, '1982-01-23', 3300,0,20),
       (9591, 'WENDY', 'SHAWN', 'SALESMAN', 7698, '1981-02-22', 500,0,30),
       (9698, 'BELLA', 'SWAN', 'MANAGER', 7839, '1981-05-01', 3420, 0,30),
       (9777, 'MADII', 'HIMBURY', 'ANALYST', 7839, '1981-05-01', 2000, 200, NULL),
       (9860, 'ATHENA', 'WILSON', 'ANALYST', 7839, '1992-06-21', 7000, 100, 50),
       (9861, 'JENNIFER', 'HUETTE', 'ANALYST', 7839, '1996-07-01', 5000, 100, 50); 


	   select * from Employee;


select * from Employee;
 select * from Department;

 /*  1.	Create a query that displays EMPFNAME, EMPLNAME, DEPTCODE, DEPTNAME,
 LOCATION from EMPLOYEE, and DEPARTMENT
 tables. Make sure the results are in 
 ascending order based on the EMPFNAME and LOCATION of the department.*/

 select E.EmpFname,E.EmpLname,E.DeptCode,D.DeptName,D.Location

 from Employee E join Department D on 

 E.DeptCode = D.Deptcode

 order by E.EmpFName Asc,D.Location Asc;

 /*2.	Display EMPFNAME and “TOTAL SALARY” for each employee  */

 select EmpFname,sum(Salary) as TotalSalary
 from Employee

 group by EmpFname;


 /*3.	Display MAX and 2nd MAX SALARY from the EMPLOYEE table. */

 select Max(salary) as FirstMaxSalary
from Employee
union
select Max(salary) as SecondMaxSalary
from Employee
where salary < (select Max(salary) from Employee);


select Max(Salary) AS Max_Salary,(Select Max(Salary) From Employee Where Salary < (Select Max(Salary) From Employee)) AS Second_Max_Salary
FROM Employee;


/*4.	Display the TOTAL SALARY drawn by an analyst working in dept no 20 */

select sum(Salary) as TotalSalary

from Employee

where job = 'Analyst' and DeptCode= 20;

select sum(Salary+isnull(commission,0)) as TotalSalary

from Employee

where job = 'Analyst' and DeptCode= 20;


/* 5.	Compute the average, minimum, and maximum salaries of the group of employees having the job of ANALYST*/

select Avg(Salary) as AvgSal,Max(Salary) as MaxSalary, Min(Salary) as MinSalary
from Employee;

select Avg(Salary+isnull(commission,0)) as AvgSal,Max(Salary+isnull(commission,0)) as MaxSalary, Min(Salary+isnull(commission,0)) as MinSalary
from Employee;

/6.	Query to find all departments that are located in Edinburgh:/

Select * from Department where location = 'Edinburgh';


/* 7.	Query to find all employees who work in the FINANCE department:*/

select *

from Employee E join Department D 
on E.DeptCode = D.Deptcode
where D.DeptName = 'Finance';

/* 8.	Query to find the average salary of employees in each department:*/

Select D.Deptcode,D.DeptName,AVG(E.Salary) AS Average_Salary
from Department D JOIN Employee E ON D.Deptcode = E.DeptCode
group by D.Deptcode,D.DeptName;



Select D.Deptcode,D.DeptName,AVG(E.Salary + ISNULL(E.Commission, 0)) AS Average_Salary
from Department D JOIN Employee E ON D.Deptcode = E.DeptCode
group by D.Deptcode,D.DeptName;



/*9.	Query to find the top 10 highest-paid employees: */

select * from Employee;


select top 10 
EmpCode,EmpFname,EmpLname,TotalSalary
from (select EmpCode,EmpFname,EmpLname,SUM(Salary) AS TotalSalary from Employee
    group by EmpCode,EmpFname,EmpLname
) AS EmployeeTotalSalary
order by TotalSalary DESC;

/* 10.	Query to find all employees who did not get a promotion in the last year:*/


SELECT 
    EmpCode,
    EmpFname,
    EmpLname,
    HireDate
FROM 
    Employee
WHERE 
    HireDate < DATEADD(YEAR, -1, GETDATE());

	select * from Employee;


/* 11.	Return a list of all employees who are paid above the average salary. */

select EmpFname,EmpLname,SUM(Salary) AS totalSalary
from Employee
group by EmpFname,EmpLname
having SUM(Salary) > (select avg(Salary) from Employee);

/* 12.	Return a list of all employees who have been with the company for more than 5 years.*/

select EmpCode,EmpFname,EmpLname,HireDate
from Employee
where DATEDIFF(YEAR, HireDate, GETDATE()) > 5;

/* 13.	 Return a list of all departments, ordered by the number of employees in each department.*/

SELECT D.Deptcode,D.DeptName,COUNT(E.EmpCode) AS NumEmployees
FROM Department D LEFT JOIN Employee E ON D.Deptcode = E.DeptCode
group by D.Deptcode,D.DeptName
order by NumEmployees DESC;

select * from Department;

	/*14.	Return a list of all job titles, ordered by the number of employees in each job title. */


select Job,COUNT(*) AS NumEmployees
from Employee group by Job
order by NumEmployees DESC;

/*15.	Return a list of all managers, ordered by the number of employees managed by each manager. */

select Manager,COUNT(*) AS NumEmployees
from Employee
group by Manager
order by NumEmployees DESC;




