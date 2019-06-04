<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Login page</title>
</head>
<body>

	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<form action="${contextPath}/user/login.htm" method="POST">
		<table>
		<tr>
		    <td>User Name:</td>
		    <td><input type="text" name="username" size="30" required="required" /></td>
		</tr>
		
		<tr>
		    <td>Password:</td>
		    <td><input type="password" name="password" size="30" required="required"/></td>
		</tr>
		
		<tr>
		    <td colspan="2"><input type="submit" value="Login" /></td>
		</tr>
				
		</table>
	</form>
	<a href="${contextPath}/employee/login.htm">Employee Login</a>
	<a href="${contextPath}/user/forgotpassword.htm">Forgot password?</a>
	<a href="${contextPath}/user/register.htm">Register User</a>
</body>
</html>