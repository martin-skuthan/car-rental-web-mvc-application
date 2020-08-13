<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link href="css/styles.css" rel="stylesheet">
    <link href="/WEB-INF/hidden-views" rel="stylesheet">
</head>
<body>
<!-- Navbar -->
<jsp:include page="/WEB-INF/fragments/navbar.jsp"/>
<!-- Navbar -->

<!-- Content -->
<div class="container">
  <div class="row h-100">
    <div class="my-auto">
      <h1 class="display-2">Info</h1>
      <div class="bs-callout bs-callout-default">
      	<h4 class="display-4">General info</h4>
        <p class="text-justify">
        Car rental application is a web project which has been created for educational purpose,
         to develop my skills in Java object oriented programming and upskill myself in technologies 
         like Java EE, Tomcat, JPA, MySQL, Bootstrap. This is a car rental  management system, 
         cooperating with MySQL relational database. Application permit users to add/remove/modify/rent/return 
         cars(app handles two types of cars : Passenger and Light commercial cars) and add/remove/modify customers. 
         The system contains also log in and registration feature based on Tomact Authentication Mechanism. 
        </p>
        <h4 class="display-4">Technologies</h4>
        <ul>
          <li>Java</li>
          <li>Java EE</li>
          <li>Tomcat as a servlet container</li>
          <li>MySQL as a data source</li>
          <li>CDI - Weld implementation</li>
          <li>JPA - Hibernate implementation</li>
          <li>Bootstrap</li>
          <li>JSP</li>
          <li>Expression language</li>
          <li>JSTL</li>
        </ul>
        <h4 class="display-4">Features</h4>
        <ul>
          <li>
            <p class="text-justify">
            <b>Loging in system</b>
             - Access to application resources is allowed only to logged users. 
             Application permits registered users to log into the application, using system 
             created based on Tomcat Authentication Mechanism.
            </p>
          </li>
          <li>
            <p>
            <b>Adding new users</b>
             - Application allows to create new system users based on details provided in the form.
            </p>
          </li>
          <li>
            <p>
            <b>Adding cars/customers</b>
             - Application allows users to add two types of car(Passenger and Light commercial cars) and customers 
             of car rental. New resources are created based on details provided by the users in the form.
            </p>
          </li>
          <li>
            <p>
            <b>Removing/Modifying car/customers</b>
             - Application allows user to remove/modify selected car/customer. Updating of data is carrying out by new 
             details provided in the form.
            </p>
          </li>
          <li>
            <p>
            <b>Printing cars and users</b>
             - Application allows users to print collection of cars and customers existed in system. To make 
             viewing data comfortable, it has been implemented pagination system.
            </p>
          </li>
          <li>
            <p>
            <b>Renting/Returning Cars</b>
             - Application allows to rent a car for period selected by the user. Once car has been rented, it cannot be rented by other customer, 
             until the car has been returned. After returning, state of car changes to available.
            </p>
          </li>
        </ul>
        <h4 class="display-4">Contact</h4>
        <p>Created by : Martin Skuthan. Please feel free to contact me :</p>
        <ul>
          <li>By mail: martin.skuthan95@gmail.com</li>
          <li><a href="http://www.linkedin.com/in/martin-skuthan-630553190">Linkedin</a></li>
          <li><a href="https://github.com/martin-skuthan">Github</a></li>
        </ul>
      </div>
    </div>
  </div>
</div>
<!-- Content -->


<!-- Footer -->
<br><br><br><br><br><br><br><br><br><br>
<jsp:include page="/WEB-INF/fragments/footer.jsp" />
<!-- Footer -->

<!-- Bootstrap js -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<!-- Bootstrap js -->

</body>
</html>
