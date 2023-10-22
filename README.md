<h1 align = "center"> ExpenseTracker_UsingJavaSpringBoot </h1>
<p align="center">
<a href="Java url">
    <img alt="Java" src="https://img.shields.io/badge/Java->=8-darkblue.svg" />
</a>
<a href="Maven url" >
    <img alt="Maven" src="https://img.shields.io/badge/maven-3.8.1-brightgreen.svg" />
</a>
<a href="Spring Boot url" >
    <img alt="Spring Boot" src="https://img.shields.io/badge/Spring Boot-3.1.1-brightgreen.svg" />
</a>

<a >
    <img alt="MySQL" src="https://img.shields.io/badge/MySQL-blue.svg">
</a>
</p>

This project is a basic web application that allows users to sign in, 
sign up, order and manage their Expense information. Additionally,
The application uses authentication tokens to secure user data and ensure 
that only authenticated users can access certain features of the application.

---
<br>

## Framework Used
* Spring Boot

---
<br>

## Dependencies
The following dependencies are required to run the project:

* Spring Boot Dev Tools
* Spring Web
* Spring Data JPA
* MySQL Driver
* Lombok
* Validation
* Swagger

<br>

## Database Configuration
To connect to a MySQL database, update the application.properties file with the appropriate database URL, username, and password. The following properties need to be updated:
```

spring.datasource.url = jdbc:mysql://<>:3306/<DatabaseName>
spring.datasource.username = <userName>
spring.datasource.password = <password>
spring.jpa.show-sql = true
spring.jpa.hibernate.ddl-auto = update

spring.jpa.properties.hibernate.show_sql=true
spring.jpa.properties.hibernate.use_sql_comments=true
spring.jpa.properties.hibernate.format_sql=true

```
<br>

## Language Used
* Java

---
<br>

## Data Model

The Job data model is defined in the Job class, which has the following attributes:
<br>

* User 
```
    userId : integer
    userName : String
    userEmail : string(@Email)
    userPassword : string

```
* Product
```
    productId: Integer
    productName: String
    productCategory : Category
    productPrice : Double

```
* Order
```
    orderId: Integer
    orderDate LocalDate
    product: Product(@ManyToOne())
    user: User(@ManyToOne())
```
* Expense
```
    expenseId: Integer
    expenseYear: Year
    expenseMonth: Month
    monthlyExpense Double
    user: User(@ManyToOne())
```
* Authentication Token
```
tokenId : Long
token : string
tokenCreationDate : LocalDate
@OneToOne 
user : User
```

## Data Flow

1. The user at client side sends a request to the application through the API endpoints.
2. The API receives the request and sends it to the appropriate controller method.
3. The controller method makes a call to the method in service class.
4. The method in service class builds logic and retrieves or modifies data from the database, which is in turn given to controller class
5. The controller method returns a response to the API.
6. The API sends the response back to the user.

---

<br>


## API End Points

The following endpoints are available in the API:

* User Controller:
```
    @PostMapping("SignUp")
    @PostMapping("SignIn")
    @PostMapping("order")
    @GetMapping("Monthly/Expense")
    @GetMapping("Yearly/Expense")
    @GetMapping("expense/RecentMonths/{limit}")
    @GetMapping("products/date")
  
 ```
* Product Controller

```
    @PostMapping("product")
    @PostMapping("products")
   
```
* Swagger 
```
    http://<Public Ip>:8080/swagger-ui/index.html#/
```
<br>

## DataBase Used
* SQL database
```
We have used Persistent database to implement CRUD Operations.

```
---
<br>

## Deployed
* AWS Ec2 Instance
```
We have used two Ec2 instance for Both Spring Boot Application and Mysql Database 
```

## Project Summary

The project is a basic web application built using Java and the Spring framework.
It is a User Expense Tracking application. A user can signUp, signIn,
Order a product and get there Monthly Expense records and manage them accordingly
