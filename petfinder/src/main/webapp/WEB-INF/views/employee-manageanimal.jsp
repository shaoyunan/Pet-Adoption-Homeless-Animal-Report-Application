<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Shelter Animals</title>
</head>
<body>
	<c:if test="${from==1}">
		<input type="button"
			onclick="location.href='${contextPath}/animal/add.htm'"
			value="Add New">
		<br />
		<table border="1">
			<tr>
				<th>Name</th>
				<th>Type</th>
				<th>Breed</th>
			</tr>
			<c:forEach items="${aList}" var="msg">
				<tr>
					<td>${msg.name}</td>
					<td>${msg.type}</td>
					<td>${msg.breed}</td>
					<td><a href="${contextPath}/animal/delete.htm?id=${msg.animalid}">Delete</a>
					</td>
					<td><a href="${contextPath}/animal/edit.htm?id=${msg.animalid}">Edit</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${from==0}">
		<table border="1">
			<tr>
				<th>Name</th>
				<th>Type</th>
				<th>Breed</th>
			</tr>
			<c:forEach items="${aList}" var="msg">
				<tr>
					<td>${msg.name}</td>
					<td>${msg.type}</td>
					<td>${msg.breed}</td>
					<td><a href="${contextPath}/animal/view.htm?id=${msg.animalid}">View</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

</body>
</html>