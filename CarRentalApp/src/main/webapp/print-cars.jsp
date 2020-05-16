<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<html>
<head>
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link href="styles.css" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
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
<div class="container-fluid h-100 d-flex">
<div class="col-sm-12 col-md-12 my-auto container-fluid">
<c:choose>
  <c:when test="${requestScope.typeOfCar == 'PASSENGER_CAR'}">
    <h1 class="display-2">Passenger cars</h1>
  </c:when>
  <c:otherwise>
    <h1 class="display-2">Light commercial cars</h1>
  </c:otherwise>
</c:choose>
<div class="bs-callout bs-callout-default">
<c:choose>
  <c:when test="${requestScope.numberOfCarRecords != 0}">
    <table class="table">
    <thead class="thead-dark">
    <tr>
      <th scope="col">Registration no.</th>
      <th scope="col">Brand</th>
      <th scope="col">Model</th>
      <th scope="col">Seats</th>
      <th scope="col">Air Conditioning</th>
      <th scope="col">Transmission</th>
      <c:choose>
  	    <c:when test="${requestScope.typeOfCar == 'PASSENGER_CAR'}">
          <th scope="col">Doors</th>
          <th scope="col">Drive</th>
          <th scope="col">Trunk capacity</th>
        </c:when>
        <c:otherwise>
          <th scope="col">Payload</th>
          <th scope="col">Load volume</th>
          <th scope="col">Load height</th>
        </c:otherwise>
      </c:choose>
      <c:if test="${requestScope.typeOfCar == 'LIGHT_COMMERCIAL_CAR'}">
        <th scope="col">Load width</th>
        <th scope="col">Load length</th>
      </c:if>
      <th>Action</th>
    </tr>
    </thead>
    <tbody>
  	<c:forEach var="car" items="${requestScope.cars}">
  	  <tr>	
        <td><c:out value="${car.registrationNumber}"></c:out></td>
        <td><c:out value="${car.brand}"></c:out></td>
        <td><c:out value="${car.model}"></c:out></td>
        <td><c:out value="${car.seats}"></c:out></td>
        <td><c:out value="${car.airConditioning}"></c:out></td>
        <td><c:out value="${car.transmission}"></c:out></td>
        
        <c:choose>
  	    <c:when test="${requestScope.typeOfCar == 'PASSENGER_CAR'}">
  	      <td><c:out value="${car.numberOfDoors}"></c:out></td>
  	      <td><c:out value="${car.typeOfDrive}"></c:out></td>
  	      <td><c:out value="${car.trunkCapacity}"></c:out></td>
        </c:when>
        <c:otherwise>
          <td><c:out value="${car.payload}"></c:out></td>
          <td><c:out value="${car.loadVolume}"></c:out></td>
          <td><c:out value="${car.loadHeight}"></c:out></td>
        </c:otherwise>
        </c:choose>
        <c:if test="${requestScope.typeOfCar == 'LIGHT_COMMERCIAL_CAR'}">
          <td><c:out value="${car.loadWidth}"></c:out></td>
          <td><c:out value="${car.loadLength}"></c:out></td>
        </c:if>
        <td>
          <div class="row">
          <form style="margin-right: 5px" class="form-signin" action="updateCar" method="post">
          	<input type="hidden" name="idOfItemToUpdate" value="${car.registrationNumber}">
            <button name="controllerAction" value="forward" class="btn btn-success" title="Edit" type="submit"><i style="font-size: 15px" class="material-icons">&#xE254;</i></button>
          </form>     
          <form class="form-signin" action="delete-item.jsp" method="post">
            <input type="hidden" name="formAction" value="deleteCar">
            <input type="hidden" name="idOfItemToDelete" value="${car.registrationNumber}">
            <input type="hidden" name="itemToDelete" value="${car}">
            <button class="btn btn-danger" title="Delete" type="submit"><i style="font-size: 15px" class="material-icons">&#xE872;</i></button>
          </form> 
          </div>	   
        </td>
      </tr>
  	</c:forEach>
    </tbody>
  </table>
  </c:when>
  <c:otherwise>
    <h4 class="display-4">There is no <c:out value="${requestScope.typeOfCar}"></c:out></h4>  	
  </c:otherwise>
</c:choose>
<div class="row">
<form class="form-signin col-sm-2 col-md-2" action="printCars?page=1" method="get">
  <c:choose>
  <c:when test="${requestScope.typeOfCar == 'PASSENGER_CAR'}">
    <button class="btn btn-lg btn-primary btn-block" name="typeOfCar" value="LIGHT_COMMERCIAL_CAR" type="submit" >Light Commercial Cars</button>
  </c:when>
  <c:otherwise>
    <button class="btn btn-lg btn-primary btn-block" name="typeOfCar" value="PASSENGER_CAR" type="submit" >Passenger Cars</button>
  </c:otherwise>
</c:choose>	         
</form>
                                             
<nav aria-label="..." class="ml-auto">
  <ul class="pagination">
    <c:forEach begin="1" end="${requestScope.noOfPages}" varStatus="loop">
      <c:choose>
      <c:when test="${loop.index == requestScope.noOfPage}">
        <li class="page-item active"><a class="page-link" href="printCars?page=${loop.index}&typeOfCar=${requestScope.typeOfCar}"><c:out value="${loop.index}"></c:out></a></li>
      </c:when>
      <c:otherwise>
        <li class="page-item"><a class="page-link" href="printCars?page=${loop.index}&typeOfCar=${requestScope.typeOfCar}"><c:out value="${loop.index}"></c:out></a></li>
      </c:otherwise>
      </c:choose>
    </c:forEach>
  </ul>
</nav>
<div class="btn-group">
  <button type="button" class="btn btn-primary dropdown-toggle count-button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    Count
  </button>
  <div class="dropdown-menu">
    <a class="dropdown-item" href="printCars?typeOfCar=${requestScope.typeOfCar}&count=5">5</a>
    <a class="dropdown-item" href="printCars?typeOfCar=${requestScope.typeOfCar}&count=10">10</a>
    <a class="dropdown-item" href="printCars?typeOfCar=${requestScope.typeOfCar}&count=15">15</a>
</div>
</div>
</div>
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
