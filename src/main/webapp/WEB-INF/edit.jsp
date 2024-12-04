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
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>Edit Event</title>
</head>
<body>

	<div class = "container">
		<nav class='navbar navbar-light bg-dark text-white'>
			<a href = "/home" class = "navbar brand text-white">Home</a>
            <a href = "/logout" class='navbar brand text-white'>Logout</a>
			<h3>Welcome, <c:out value = "${user.firstName}"/></h3>
        </nav>
        <form:form action="/editEvent/${event.id}" method="put" modelAttribute = "event">
            <div class="row">
                <div class="col-4">
                    <p>
	  					<form:label path = "title">Name:</form:label>
	  					<form:errors path = "title"/>
	  					<form:input path = "title" type = "text"/>
  					</p>
  					<p>
	  					<form:label path = "eventDate">Date:</form:label>
	  					<form:errors path = "eventDate"/>
	  					<form:input path = "eventDate" type = "date"/>
  					</p>
  					<p>
	  					<form:label path = "location">Location:</form:label>
	  					<form:errors path = "location"/>
	  					<form:input path = "location" type = "text"/>
  					</p>
  					<p>
	  					<form:label path = "state">State:</form:label>
	  					<form:errors path = "state"/>
			            <form:select path = "state">
				            <form:option value ="AL">Alabama</form:option>
				            <form:option value ="AK">Alaska</form:option>
				            <form:option value ="AZ">Arizona</form:option>
				            <form:option value ="AR">Arkansas</form:option>
				            <form:option value ="CA">California</form:option>
				            <form:option value ="CO">Colorado</form:option>
				            <form:option value ="CT">Connecticut</form:option>
				            <form:option value ="DE">Delaware</form:option>
				            <form:option value ="FL">Florida</form:option>
				            <form:option value ="GA">Georgia</form:option>
				            <form:option value ="HI">Hawaii</form:option>
				            <form:option value ="ID">Idaho</form:option>
				            <form:option value ="IL">Illinois</form:option>
				            <form:option value ="IN">Indiana</form:option>
				            <form:option value ="IA">Iowa</form:option>
				            <form:option value ="KS">Kansas</form:option>
				            <form:option value ="KY">Kentucky</form:option>
				            <form:option value ="LA">Louisana</form:option>
				            <form:option value ="ME">Maine</form:option>
				            <form:option value ="MD">Maryland</form:option>
				            <form:option value ="MA">Massachusetts</form:option>
				            <form:option value ="MI">Michigan</form:option>
				            <form:option value ="MN">Minnesota</form:option>
				            <form:option value ="MS">Mississippi</form:option>
				            <form:option value ="MO">Missouri</form:option>
				            <form:option value ="MT">Montana</form:option>
				            <form:option value ="NE">Nebraska</form:option>
				            <form:option value ="NV">Nevada</form:option>
				            <form:option value ="NH">New Hampshire</form:option>
				            <form:option value ="NJ">New Jersey</form:option>
				            <form:option value ="NM">New Mexico</form:option>
				            <form:option value ="NY">New York</form:option>
				            <form:option value ="NC">North Carolina</form:option>
				            <form:option value ="ND">North Dakota</form:option>
				            <form:option value ="OH">Ohio</form:option>
				            <form:option value ="OK">Oklahoma</form:option>
				            <form:option value ="OR">Oregon</form:option>
				            <form:option value ="PA">Pennsylvania</form:option>
				            <form:option value ="RI">Rhode Island</form:option>
				            <form:option value ="SC">South Carolina</form:option>
				            <form:option value ="SD">South Dakota</form:option>
				            <form:option value ="TN">Tennessee</form:option>
				            <form:option value ="TX">Texas</form:option>
				            <form:option value ="UT">Utah</form:option>
				            <form:option value ="VT">Vermont</form:option>
				            <form:option value ="VA">Virginia</form:option>
				            <form:option value ="WA">Washington</form:option>
				            <form:option value ="WV">West Virginia</form:option>
				            <form:option value ="WI">Wisconsin</form:option>
				            <form:option value ="WY">Wyoming</form:option>
			            </form:select>
  					</p>
                </div>
            </div>
            <div class='row'>
            	<form:hidden path = "user" value = "${user.id}"/>
                <button class='btn btn-outline-dark ml-3 mt-3'>Edit Event</button>
            </div>
        </form:form>
       </div>


</body>
</html>