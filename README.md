User Access Management System

This project is a web-based User Access Management System that allows employees to request software access, managers to approve/reject requests, and administrators to manage user roles and access. The project is built using Java Servlets, JSP, and PostgreSQL.

Table of Contents
Project Setup
Database Setup
Project Structure
Features and Servlets
JSP Pages
Running the Project
Accessing the Application

***Project Setup
Clone the Repository:

git clone https://github.com/yourusername/UserAccessManager.git
cd UserAccessManager
Import into Eclipse:

Open Eclipse and go to File -> Import -> Existing Maven Projects.
Select the cloned repository directory and click Finish.
Install Dependencies:

Make sure Maven dependencies are installed and updated by right-clicking on the project and selecting Maven -> Update Project.
Configure Database:

Make sure PostgreSQL is installed.
Create a new database and update database configurations in DatabaseHelper.java.
Database Setup
***Database:
Set up a PostgreSQL database named user_access_db.
Create tables for users, requests, and software.
Update Database Configuration:
Edit src/main/java/com/useraccessmanagement/helpers/DatabaseHelper.java to match your PostgreSQL credentials.

***Sample Tables and Initial Data:
Here are the sample table creation queries:
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);

CREATE TABLE software (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    access_level VARCHAR(50)
);

CREATE TABLE requests (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id),
    software_id INT REFERENCES software(id),
    access_type VARCHAR(50),
    reason TEXT,
    status VARCHAR(20) DEFAULT 'Pending'
);
![image](https://github.com/user-attachments/assets/28b0fc7e-64cc-477b-9f23-a8aa13ff97bd)

admin username: admin_user
admin password: adminpass

Features and Servlets   ADMIN-
Welcome Page (WelcomeServlet.java)

URL: /UserAccessManager
Redirects to welcome.jsp for a brief introduction and login link.
Login (LoginServlet.java)

URL: /login
Authenticates users and redirects based on role.
Logout (LogoutServlet.java)

URL: /logout
Ends user session and redirects to login page.
View Users (ViewUsersServlet.java)

URL: /viewUsers
Allows admin to view a list of users in the system.
Create Software (CreateSoftwareServlet.java)

URL: /createSoftware
Admins can create new software applications with access levels.
Assign Roles (AssignRolesServlet.java)

URL: /assignRoles
Admins can assign or update user roles.

***Running the Project
Start the Server:
Right-click on the project, select Run As -> Run on Server, and choose your server (e.g., Tomcat).
Access the Application:
Open a browser and go to http://localhost:8091/UserAccessManager.

***Accessing the Application
Admin Login
Username: admin
Password: adminpass
Admin Features:

View Users: See all users registered in the system.
Create Software: Add new software applications.
Assign Roles: Assign roles (Employee, Manager, Admin) to users.

there are several other deleiverables in src/main/java and webapp
Java Servlets:
■ SignUpServlet.java
■ LoginServlet.java
■ SoftwareServlet.java
■ RequestServlet.java
■ ApprovalServlet.java
○ JSP Pages:
■ signup.jsp
■ login.jsp
■ createSoftware.jsp
■ requestAccess.jsp
■ pendingRequests.jsp
and other servlets and jsp as required. 


Technologies Used

- Java Servlets
- JSP (JavaServer Pages)
- PostgreSQL
- HTML/CSS
- JSTL (JavaServer Pages Standard Tag Library)
- Apache Tomcat (for running the server)

  all details related to databse is given in DatabaseHelper.java file including url for database and name of database.

kindly download postgresql and tomcat from thier official website.

any other query mail at   amithsharma8075@gmail.com . 
  

