use Company;

/* Department Table */
create table Department(
DepartmentId int Primary key not null,
DepartmentName nvarchar(200) not null);



select * from Department;

/* Employee Table */
create table Employee(
EmployeeId int Primary key not null,
FirstName nvarchar(100) not null,
LastName nvarchar(50) not null,
Email nvarchar(50) unique not null,
DateofBirth date not null,
DepartmentId int,
constraint FK_DepartmentId foreign key(DepartmentId) references Department(DepartmentId));


Select * from Employee;


/* Task 2  Add a new column named PhoneNumber (String) to the Employees table to store the contact number of each employee. */


alter table Employee Add PhonenNumber nvarchar(20);

select * from Employee;


/* Add a composite primary key constraint on EmployeeID and Email */
alter table Employee add constraint uk_compositeprimarykey unique(EmployeeID,Email); 


/* Task 3  Data Manipulation
 
Insert at least 5 records into the Employees table, ensuring that the data inserted adheres to the defined constraints.*/


/* Insert table into Department */
insert into Department(DepartmentID,DepartmentName) values(401,'developer');
insert into Department(DepartmentID,DepartmentName) values(402,'tester');
insert into Department(DepartmentID,DepartmentName) values(403,'admin');
insert into Department(DepartmentID,DepartmentName) values(404,'software engineer');
insert into Department(DepartmentID,DepartmentName) values(405,'service desk');

select * from Department;

/* Insert into Employee tbale*/

insert into Employee values (1,'Aravind','Betharasi','betharasiaravind@gmail.com','2024-10-12',401,'6305898984');
insert into Employee values (2,'Arru','Betharasi','betharasiarru@gmail.com','2024-8-12',402,'9550834585');
insert into Employee values (3,'Amma','Betharasi','betharasiamma@gmail.com','2002-09-12',403,'8106953972');
insert into Employee values (4,'Dady','Betharasi','betharasidady@gmail.com','2003-10-12',404,'8688172021');
insert into Employee values (5,'chintu','Betharasi','betharasichintu@gmail.com','2004-03-19',405,'8347614539');

select * from Employee;


/* Update the PhoneNumber column for a specific employee. */

update Employee set PhonenNumber = '6301028607' where EmployeeId = 4;

select * from Employee;

/* Delete a record from the Employees table. */

delete from Employee where EmployeeId = 2;

select * from Employee;


/* Task 4: Testing Constraints
 
Attempt to insert a record with a duplicate EmployeeID.  */


insert into Employee values (1,'Aravind','Betharasi','betharasiaravind@gmail.com','2024-10-12',401,'6305898984');

/* Ouput 
Violation of PRIMARY KEY constraint 'PK__Employee__7AD04F11C339E8AE'. Cannot insert duplicate key in object 'dbo.Employee'. The duplicate key value is (1).
The statement has been terminated.
*/


/* Attempt to insert a record with a duplicate Email. */
insert into Employee values (6,'Arru','Betharasi','betharasiaravind@gmail.com','2001-02-23',402,'8765432190');

/* out put 
Violation of UNIQUE KEY constraint 'UQ__Employee__A9D105349B008A70'. Cannot insert duplicate key in object 'dbo.Employee'. The duplicate key value is (betharasiaravind@gmail.com).
The statement has been terminated.
*/

/* Attempt to insert a record with a null value for FirstName. */

insert into Employee values (6,null,'Betharasi','betharasi@gmail.com','2001-02-23',402,'8765432190');

/* output 
Cannot insert the value NULL into column 'FirstName', table 'Company.dbo.Employee'; column does not allow nulls. INSERT fails.
The statement has been terminated. */


/* Attempt to insert a record with a foreign key value that does not exist in the referenced table. */


insert into Employee values (6,'Arru','Betharasi','betharasiarru@gmail.com','2001-02-23',406,'8765432190');


/* out put 

The INSERT statement conflicted with the FOREIGN KEY constraint "FK_DepartmentId". The conflict occurred in database "Company", table "dbo.Department", column 'DepartmentId'.
The statement has been terminated. */

/*Task 5: Additional Constraints 
 
Add a check constraint to ensure that the DateOfBirth column does not contain future dates. */


/*ALTER TABLE Employee DROP CONSTRAINT CHK_DateofBirth;



/* Add a default constraint to set a default value for the PhoneNumber column.*/
ALTER TABLE Employee
ADD CONSTRAINT DF_PhoneNumber DEFAULT '0000000000' FOR PhoneNumber;*/



