<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
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
		queryString+="&userid="+document.getElementById("uid").value;
		xmlHttp.open("GET", "../validateusernameajax.htm?username="
				+ queryString, true);
		xmlHttp.send();
	}
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Profile</title>
</head>
<body>
	<form action="${contextPath}/user/editprofile.htm" method="POST">
	<input type="hidden" name="userid" value="${user.petOwnerId}" id="uid"/>
		<table>
			<tr>
				<td>User Name:</td>
				<td><input type="text" name="username" size="30"
					required="required" value="${user.userName}" id="username" onkeyup="usernameEvent()"/></td>
					<td id="un"></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="text" name="password" size="30"
					required="required" /></td>
			</tr>
			<tr>
				<td>First Name:</td>
				<td><input type="text" name="fname" size="30"
					required="required" value="${user.firstName}"/></td>
			</tr>

			<tr>
				<td>Last Name:</td>
				<td><input type="text" name="lname" size="30" value="${user.lastName}"/></td>
			</tr>

			<tr>
				<td>Phone Number:</td>
				<td><input type="text" name="phone" size="30"
					required="required" value="${user.phoneNumber}"/></td>
			</tr>

			<tr>
				<td>Email:</td>
				<td><input type="email" name="email" size="30"
					required="required" value="${user.email}"/></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="Update" /></td>
			</tr>

		</table>
	</form>
</body>
</html>