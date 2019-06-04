<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Succeed</title>
</head>
<body>
	<p>Your pet have been listed for adoption!</p>

	<a href="${contextPath}/user/managepets.htm">Return to Manage Page</a>

	<a href="${contextPath}/pet/addpet.htm">Add more</a>
</body>
</html>