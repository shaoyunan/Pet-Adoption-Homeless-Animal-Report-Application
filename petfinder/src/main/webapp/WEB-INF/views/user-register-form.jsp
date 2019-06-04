<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script>
	function usernameEvent() {

		var xmlHttp;
		try // Firefox, Opera 8.0+, Safari
		{
			xmlHttp = new XMLHttpRequest();
		} catch (e) {
			try // Internet Explorer
			{
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {
					alert("Your browser does not support AJAX!");
					return false;
				}
			}
		}

		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4) {
				document.getElementById("un").innerHTML = xmlHttp.responseText;
			}
		}

		var queryString = document.getElementById("username").value;

		xmlHttp.open("GET", "../validateusernameajax.htm?username=" + queryString, true);
		xmlHttp.send();
	}
	
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Registration</title>
</head>
<body>
	<font color="red">${errorMessage}</font>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<form action="${contextPath}/user/create.htm" method="POST">
		<table>
			<tr>
				<td>User Name:</td>
				<td><input type="text" name="username" size="30"
					required="required" onkeyup="usernameEvent()" id="username"/></td>
					<td id="un"></td>
			</tr>

			<tr>
				<td>Password:</td>
				<td><input type="password" name="password" size="30"
					required="required" onkeyup="passwordEvent()"/></td>
					<td id="pwd"></td>
			</tr>

			<tr>
				<td>Email:</td>
				<td><input type="email" name="useremail" size="30"
					required="required" /></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="Register" /></td>
			</tr>
		</table>
	</form>
</body>
</html>