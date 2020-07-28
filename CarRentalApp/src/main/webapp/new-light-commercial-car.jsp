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
        <a class="nav-link" href="select-type-of-car.jsp">Add car</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="printCars">Print/Modify cars</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="new-customer.jsp">Add customer</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="printCustomers">Print/Modify customers</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="rent-return-car.jsp">Rent/Return car</a>
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
          <a class="nav-link" href="logIn">Login</a>
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
<c:choose>
<c:when test="${requestScope.controllerAction == 'UPDATE'}">
  <h1 class="display-2">Update car</h1>
</c:when>
<c:otherwise>
  <h1 class="display-2">New car</h1>
</c:otherwise>
</c:choose>
<div class="bs-callout bs-callout-default">
<c:choose>
<c:when test="${requestScope.controllerAction == 'UPDATE'}">
  <form class="form-signin" action="updateCar" method="post">
</c:when>
<c:otherwise>
  <form class="form-signin" action="addCar" method="post">
</c:otherwise>
</c:choose>
<label>Registration number:</label>
<c:choose>
<c:when test="${requestScope.controllerAction == 'CORRECT'}">
<input name="inputRegistrationNumber" value="${requestScope.car.registrationNumber}" type="text" class="form-control is-invalid" placeholder="Registration number" required autofocus>
<small id="passwordHelp" class="text-danger">Car with provided registration number already exists</small><br>
</c:when>
<c:otherwise>
<input name="inputRegistrationNumber" value="${requestScope.car.registrationNumber}" type="text" class="form-control" placeholder="Registration number" required autofocus><br>
</c:otherwise>
</c:choose>
<input type="hidden" name="registrationNumber" value="${requestScope.car.registrationNumber}" >
<label>Brand:</label>
<input name="inputBrand" value="${requestScope.car.brand}" type="text" class="form-control" placeholder="Brand" required><br>
<label>Model:</label>
<input name="inputModel" value="${requestScope.car.model}" type="text" class="form-control" placeholder="Model" required><br>
<label>Number of seats:</label>
<input name="inputSeats" value="${requestScope.car.seats}" type="number" min="2" max="4" class="form-control" placeholder="Number of seats" required><br>
<label>Payload (kg):</label>
<input name="inputTrunkPayload" value="${requestScope.car.payload}" type="number" min="600" max="5000" class="form-control" placeholder="Payload (kg)"><br>
<label>Load volume (m3):</label>
<input name="inputLoadVolume" value="${requestScope.car.loadVolume}" type="number" min="2" max="25" class="form-control" placeholder="Load volume (m3)"><br>
<label>Load height (mm):</label>
<input name="inputLoadHeight" value="${requestScope.car.loadHeight}" type="number" min="1000" max="2500" class="form-control" placeholder="Load height (mm)"><br>
<label>Load width (mm):</label>
<input name="inputLoadWidth" value="${requestScope.car.loadWidth}" type="number" min="1500" max="2000" class="form-control" placeholder="Load width (mm)"><br>
<label>Load length (mm):</label>
<input name="inputLoadLength" value="${requestScope.car.loadLength}" type="number" min="1500" max="5500" class="form-control" placeholder="Load length (mm)"><br>
<label>Transmission:</label>
<select class="form-control" name="inputTransmission">
<c:choose>
<c:when test="${requestScope.car.transmission  == 'MANUAL'}">
  <option selected="selected" value="Manual">Manual</option>
  <option value="Automatic">Automatic</option>
</c:when>
<c:otherwise>
  <option value="Manual">Manual</option>
  <option selected="selected" value="Automatic">Automatic</option>
</c:otherwise>
</c:choose>
</select><br>
<label>Air Conditionig:</label>
<select class="form-control" name="inputAirConditioning">
<c:choose>
<c:when test="${requestScope.car.airConditioning == 'true'}">
  <option selected="selected" value="true">Yes</option>
  <option value="false">No</option>
</c:when>
<c:otherwise>
  <option value="true">Yes</option>
  <option selected="selected" value="false">No</option>
</c:otherwise>
</c:choose>
</select><br>          
<input type="hidden" name="typeOfCar" value="LightCommercial">      
<c:choose>
<c:when test="${requestScope.controllerAction == 'UPDATE'}">
  <button class="btn btn-lg btn-primary btn-block" type="submit" name="controllerAction" value="update">Update car</button>
</c:when>
<c:otherwise>
  <button class="btn btn-lg btn-primary btn-block" type="submit" name="controllerAction" value="create">Add car</button>
</c:otherwise>
</c:choose>
</form>
</div>
</div>
</div>     
<!-- Content -->


<!-- Bootstrap js -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<!-- Bootstrap js -->

</body>
</html>
