<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Report List</title>
</head>
<body>
	<input type="button"
		onclick="location.href='${contextPath}/report/export.xls'"
		value="Print Task Table">
	<table border="1">
		<tr>

			<th>Report Id</th>
			<th>Date</th>
			<th>Animal Type</th>
			<th>Priority</th>
			<th>Status</th>
		</tr>
		<c:forEach items="${reportList}" var="msg">
			<tr>
				<td>${msg.reportid}</td>
				<td>${msg.reportdate}</td>
				<td>${msg.animaltype}</td>
				<td>${msg.priority}</td>
				<td>${msg.status}</td>
				<td><a
					href="${contextPath}/report/viewreport.htm?reportid=${msg.reportid}">View</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<a href="${contextPath}/report/map.htm">Open Map</a>
	<a href="${contextPath}/employee/login.htm">Return to Portal</a>
</body>
</html>