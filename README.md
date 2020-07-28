# Car rental web application 
Car rental management system written using Java/Jave EE technolgies.

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Features](#features)
* [Contact](#contact)

## General info
Car rental application is a web project which has been created for educational purpose, to develop my skills in Java object oriented programming and upskill myself in technologies like Java EE, Tomcat, JPA, MySQL, Bootstrap. This is a car rental  management system, cooperating with MySQL relational database. Application permit users to add/remove/modify/rent/return cars(app handles two types of cars : Passenger and Light commercial cars) and add/remove/modify customers. The system contains also log in and registration feature based on Tomact Authentication Mechanism. 

## Technologies
* Java
* Java EE
* Tomcat as a servlet container
* MySQL as a data source
* CDI - Weld implementation
* JPA - Hibernate implementation
* Bootstrap
* JSP
* Expression language 
* JSTL

## Features
* **Loging in system** - Access to application resources is allowed only to logged users. Application permits registered users to log into the application, using system created based on Tomcat Authentication Mechanism.

![Algorithm schema](./images/Logging.gif)
<div align="center">Logging into system</div>
<p>&nbsp;</p>

* **Adding new users** - Application allows to create new system users based on details provided in the form.

![Algorithm schema](./images/addingUser.gif)
<div align="center">Adding car</div>
<p>&nbsp;</p>

* **Adding cars/customers** - Application allows users to add two types of car(Passenger and Light commercial cars) and customers of car rental. New resources are created based on details provided by the users in the form. 

![Algorithm schema](./images/AddingCar.gif)
<div align="center">Adding car</div>
<p>&nbsp;</p>
                                                             
* **Removing/Modifying car/customers** - Application allows user to remove/modify selected car/customer. Updating of data is carrying out by new details provided in the form.

![Algorithm schema](./images/DeletingCar.gif)
<div align="center">Removing car</div>
<p>&nbsp;</p>

![Algorithm schema](./images/UpdatingCustomer.gif)
<div align="center">Modyfing customers</div>
<p>&nbsp;</p>

* **Printing cars and users** - Application allows users to print collection of cars and customers existed in system. To make viewing data comfortable, it has been implemented pagination system.

![Algorithm schema](./images/PrintigCars.gif)
<div align="center">Printing cars with pagination</div>
<p>&nbsp;</p>

* **Renting/Returning Cars** - Application allows to rent a car for period selected by the user. Once car has been rented, it cannot be rented by other customer, until the car has been returned. After returning, state of car changes to available.

![Algorithm schema](./images/RentingCar.gif)
<div align="center">Renting car</div>
<p>&nbsp;</p>

![Algorithm schema](./images/ReturningCar.gif)
<div align="center">Returning car</div>
<p>&nbsp;</p>

## Contact
Created by : Martin Skuthan. Please feel free to contact me :
* By mail: martin.skuthan95@gmail.com
* Linkedin : www.linkedin.com/in/martin-skuthan-630553190
