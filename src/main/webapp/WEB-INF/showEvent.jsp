<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %> 
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><c:out value = "${event.title}"/></title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

	<div class = "container">
		<nav class='navbar navbar-light bg-dark text-white'>
			<a href = "/home" class = "navbar brand text-white">Home</a>
            <a href = "/logout" class='navbar brand text-white'>Logout</a>
			<h3>Welcome, <c:out value = "${user.firstName}"/></h3>
        </nav>
        <div class = "row">
        <div class = "col-4">
        	<h3><c:out value = "${event.title}"/></h3>
        	<p>Host: <c:out value = "${event.user.firstName}"/></p>
        	<p>Date: <fmt:formatDate pattern = "MMMM dd, yyyy" value = "${event.eventDate}"/></p>
        	<p>Location: <c:out value = "${event.location}"/>, <c:out value = "${event.state}"/></p>
        	<p>People who are attending this event: <c:out value = "${count}"/></p>
        </div>
        </div>
        <div class = "row">
        	<div class = "col-4">
        	<table class="table table-striped">
  			<thead>
			    <tr>
				  <th scope="col">Name</th>
			      <th scope="col">Location</th>
			    </tr>
  			</thead>
			  <tbody>
			    <c:forEach items = "${guests}" var = "guest">
				    <tr>
				      <td><c:out value = "${guest.firstName}"/> <c:out value = "${guest.lastName}"/></td>
				      <td><c:out value = "${guest.location}"/></td>
				    </tr>
			    </c:forEach>
			  </tbody>
		</table>
        	</div>
        
        	<div class = "col-6">
        		<div class = "overflow-auto ">
        			<c:forEach items = "${messages}" var = "message">
        				<p><c:out value = "${message.user.firstName}"/> says: <c:out value = "${message.body}"/></p>
        				<p>*----*----*----*----*</p>
        			</c:forEach>
        		</div>
        		<form:form action = "/addMessage" method = "POST" modelAttribute = "message">
        			<h3>Add a message: </h3>
        			<p>
        				<form:label path = "body"></form:label>
        				<form:errors path = "body"/>
        				<form:input path = "body" type = "text"/>
        			</p>
        			<p>
        				<form:hidden path = "user" value = "${user.id}"/>
        				<form:hidden path = "event" value = "${event.id}"/>
        				<button class = "btn btn-outline-dark ml-3 mt-3">Submit</button>
        			</p>
        		</form:form>
        	</div>
        </div>
 
	</div>
</body>
</html>