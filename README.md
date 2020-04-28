# BankLoan
BankLoan is a RESTful web service built using SpringBoot with the following functionalities: - Add customer, Get customer details based on loan type and Loan for an existing customer who has no loan.

# Start project from Begining
Go to https://start.spring.io/  generate, extract the downloaded zip file and import it into Eclipse.

# Setup
Your installed JDK should be at least Java8 Version and establish database connection on eclipse.

# Dependency
Go to pom.xml and add all required dependencies.

# Modify these according to your database credential
Go to application.properties file
# MYSQL settings
spring.datasource.url=jdbc:mysql://localhost:3306/spring_project
spring.datasource.username=root
spring.datasource.password=abcdef
# Oracle settings
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
spring.datasource.username=system
spring.datasource.password=oracle
# JPA settings
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.hibernate.ddl-auto=create

# Main Logic
Sign Up / Sign In
After sign in you have the following options:- 
1.) Add customer
2.) Get customer details based on loan type
3.) Loan for an existing customer who has no loan

# RESTful Web Service and Use of Postman
I have provided the RESTful Web Service logic in the LoanAPI.java class and checked the logic on the Postman using the following URL:- 
POST operation with http://localhost:8080/login/ URL and put the customer details in Body section in JSON format for add customer.
POST operation http://localhost:8080/login/loan and put the customer details in Body section in JSON format to sanction loan an existing customer who has no loan.
GET operation http://localhost:8080/login/HomeLoan to get customer details having loan type HomeLoan.
GET operation http://localhost:8080/login/CarLoan to get customer details having loan type CarLoan.

# Logging using Log4j
I have provided logging logic in LoggingExpect.java class i.e followed the Spring AOP concept for all the methods of DAO and Service class after throwing an exception on error level.

# Unit Testing using JUnit5
I have tested each and every method of Service class without the help of DAO class real object i.e mocked the DAO class object and injected it into Service class object and tested the logic of Service class methods.
