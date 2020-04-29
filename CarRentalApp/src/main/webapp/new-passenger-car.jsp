<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<html>
<head>
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link href="css/styles.css" rel="stylesheet">
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="index.jsp">Car Rental</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="select-type-of-car.jsp">Add/Remove car</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="printCars">Print cars</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="new-customer.jsp">Add/Remove customer</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="printCustomers">Print customers</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Rent/Return car</a>
      </li>
    </ul>
    <ul class="navbar-nav ml-auto">
      <li class="nav-item">
        <a class="nav-link" href="new-user.jsp">Add admin account</a>
      </li>	
	  <c:choose>
      <c:when test="${not empty sessionScope.user}">
        <li class="nav-item">
          <a class="nav-link" href="logOut">Logout</a>
        </li>
      </c:when>
      <c:otherwise>
        <li class="nav-item">
          <a class="nav-link" href="login-success.jsp">Login</a>
        </li>
      </c:otherwise>
      </c:choose>	
	</ul>
  </div>
</nav>
</div>
<!-- Navbar -->

<!-- Content -->
<div class="d-flex justify-content-center align-items-center container h-100">
<div class="col-sm-6 col-md-6 my-auto">
<h1 class="display-2">New car</h1>
<div class="bs-callout bs-callout-default">
<form class="form-signin" action="addCar" method="post">
<input name="inputRegistrationNumber" type="text" class="form-control" placeholder="Registration number" required autofocus>
<input name="inputBrand" type="text" class="form-control" placeholder="Brand" required>
<input name="inputModel" type="text" class="form-control" placeholder="Model" required>
<input name="inputSeats" type="number" min="2" max="9" class="form-control" placeholder="Number of seats" required>
<input name="inputNumberOfDoors" type="number" min="3" max="6" class="form-control" placeholder="Number of doors" required>
<input name="inputTrunkCapacity" type="number" min="1" max="10" class="form-control" placeholder="Luggage Capacity (in suticases)"><br>
<label>Transmission:</label>
    <select class="form-control" name="inputTransmission">
      <option value="Manual">Manual</option>
      <option value="Automatic">Automatic</option>
    </select><br>
<label>Type of drive:</label>
    <select class="form-control" name="inputTypeOfDrive">
      <option value="Petrol">Petrol</option>
      <option value="Diesel">Diesel</option>
      <option value="Hybrid">Hybrid</option>
    </select><br>
<label>Air Conditionig:</label>
    <select class="form-control" name="inputAirConditioning">
      <option value="true">Yes</option>
      <option value="false">No</option>
    </select><br>          
<button class="btn btn-lg btn-primary btn-block" type="submit" name="typeOfCar" value="passenger_car">Add car</button>
</form>
</div>
</div>
</div>     
<!-- Content -->

<!-- Footer -->
<footer class="page-footer font-small pt-4">
  <div class="footer-copyright text-center py-3">Developed by
    <a href="http://www.linkedin.com/in/martin-skuthan-630553190"> Martin Skuthan</a>
  </div>
</footer>
<!-- Footer -->

<!-- Bootstrap js -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<!-- Bootstrap js -->

</body>
</html>
