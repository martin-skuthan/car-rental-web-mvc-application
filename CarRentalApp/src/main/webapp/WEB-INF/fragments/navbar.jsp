<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="info">Car Rental</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="addCar">Add car</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="printCars">Print/Modify cars</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="addCustomer">Add customer</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="printCustomers">Print/Modify customers</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="rentReturnCar">Rent/Return car</a>
      </li>
    </ul>
    <ul class="navbar-nav ml-auto">
      <li class="nav-item">
        <a class="nav-link" href="addUser">Add admin account</a>
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