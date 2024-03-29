<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Portal</title>
</head>
<body>
	<h1>Employee Portal</h1>
	<b>${sessionScope.user}</b>
	
	<p>Current unprocessed homeless animal reports: ${newRequest}</p>
	
	<h2>Options:</h2>

	<a href="${contextPath}/employee/manageanimals.htm">Mange Shelter Animal</a>
	<br />
	<a href="${contextPath}/employee/viewreports.htm?option=0">View Reports</a>
	<br />
	<a href="${contextPath}/user/logout.htm">Logout</a>
	<br />
</body>
</html>