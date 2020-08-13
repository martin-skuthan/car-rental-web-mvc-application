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
<jsp:include page="/WEB-INF/fragments/navbar.jsp" />
<!-- Navbar -->

<!-- Content -->
<div class="d-flex justify-content-center align-items-center container h-100">
<div class="col-sm-8 col-md-8 my-auto">
<c:choose>
<c:when test="${requestScope.controllerAction == 'SELECT_CUSTOMER'}">
  <h1 class="display-2">Select customer</h1>
</c:when>
<c:otherwise>
  <h1 class="display-2">Customers</h1>
</c:otherwise>
</c:choose>
<div class="bs-callout bs-callout-default">
<c:choose>
<c:when test="${requestScope.numberOfCustomersRecords != 0}">
  <table class="table">
  <thead class="thead-dark">
    <tr>
      <th scope="col">First name</th>
      <th scope="col">Last name</th>
      <th scope="col">Pesel</th>
      <th scope="col">Action</th>
    </tr>
  </thead>
  <tbody>
  	<c:forEach var="customer" items="${requestScope.customers}">
  	  <tr>	
        <td><c:out value="${customer.firstName}"></c:out></td>
        <td><c:out value="${customer.lastName}"></c:out></td>
        <td><c:out value="${customer.pesel}"></c:out></td>
        <td>   
        <div class="row">
        <c:choose>
		<c:when test="${requestScope.controllerAction == 'SELECT_CUSTOMER'}">
  		  <form class="form-signin" action="rentReturnCar" method="post">
            <input type="hidden" name="customerPesel" value="${customer.pesel}">
            <button class="btn btn-success btn-block count-button" name="controllerAction" value="rent" type="submit">Select</button>
          </form>   
		</c:when>
		<c:otherwise>
  		  <form style="margin-right: 5px" class="form-signin" action="updateCustomer" method="post">
          	<input type="hidden" name="idOfItemToUpdate" value="${customer.pesel}">
            <button name="controllerAction" value="forward" class="btn btn-success" title="Edit" type="submit"><i style="font-size: 15px" class="material-icons">&#xE254;</i></button>
          </form>     
          <form class="form-signin" action="deleteCustomer" method="get">
            <input type="hidden" name="formAction" value="deleteCustomer">
            <input type="hidden" name="idOfItemToDelete" value="${customer.pesel}">
            <input type="hidden" name="itemToDelete" value="${customer}">
            <button class="btn btn-danger" title="Delete" type="submit"><i style="font-size: 15px" class="material-icons">&#xE872;</i></button>
          </form>
		</c:otherwise>
		</c:choose> 
        </div>    
        </td>
      </tr>
  	</c:forEach>
  </tbody>
</table>
</c:when>
<c:otherwise>
    <h4 class="display-4">There is no customers</h4>  	
  </c:otherwise>
</c:choose>
<div class="row">
<nav aria-label="..." class="ml-auto">
  <ul class="pagination">
    <c:forEach begin="1" end="${requestScope.noOfPages}" varStatus="loop">
      <c:choose>
      <c:when test="${loop.index == requestScope.noOfPage}">
        <li class="page-item active"><a class="page-link" href="printCustomers?page=${loop.index}"><c:out value="${loop.index}"></c:out></a></li>
      </c:when>
      <c:otherwise>
        <li class="page-item"><a class="page-link" href="printCustomers?page=${loop.index}"><c:out value="${loop.index}"></c:out></a></li>
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
    <a class="dropdown-item" href="printCustomers?count=5">5</a>
    <a class="dropdown-item" href="printCustomers?count=10">10</a>
    <a class="dropdown-item" href="printCustomers?count=15">15</a>
</div>
</div>
</div>
</div>
</div>  
</div>   
<!-- Content -->

<!-- Footer -->
<jsp:include page="/WEB-INF/fragments/footer.jsp" />
<!-- Footer -->

<!-- Bootstrap js -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<!-- Bootstrap js -->

</body>
</html>
