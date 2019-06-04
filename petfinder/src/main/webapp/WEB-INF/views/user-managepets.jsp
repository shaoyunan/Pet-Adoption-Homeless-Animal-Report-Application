<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage Pets</title>
</head>

<body>
	<input type="button"
		onclick="location.href='${contextPath}/pet/addpet.htm'"
		value="Add Pet">
	<br />

	<table border="1">
		<tr>
			<th>Pet Name</th>
			<th>Type</th>
			<th>Breed</th>
		</tr>
		<c:forEach items="${petList}" var="msg">
			<tr>
				<td>${msg.petName}</td>
				<td>${msg.animalType}</td>
				<td>${msg.breed}</td>
				<td><a href="${contextPath}/pet/delete.htm?petid=${msg.petId}">Delete</a>
				</td>
				<td><a href="${contextPath}/pet/addpet.htm?petid=${msg.petId}">Edit</a>
				</td>
			</tr>
		</c:forEach>
	</table>

	<a href="${contextPath}/user/dashboard.htm">Return to DashBoard</a>
</body>

</html>