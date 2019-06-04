<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Report Homeless Animal</title>
</head>
<body>
	<form action="${contextPath}/user/report.htm" method="POST">
		<table>
		<tr>
				<td>Contact:</td>
				<td><input type="text" name="contact" size="31" /></td>
			</tr>
			<tr>
				<td>Animal Type:</td>
				<td><input type="text" name="type" size="31" /></td>
			</tr>
			<tr>
				<td>Health Condition:</td>
				<td><select name="healthcondition">
						<option value="G">GOOD</option>
						<option value="F">FAIR</option>
						<option value="P">POOR</option>
						<option value="C">CRITICAL</option>
				</select></td>
			</tr>
			<tr>
				<td>Aggressive Behavior:</td>
				<td><input type="checkbox" name="aggressive" /></td>
			</tr>

			<tr>
				<td>Food Provided?:</td>
				<td><input type="checkbox" name="food" /></td>
			</tr>

			<tr>
				<td>Location:</td>
				<td><textarea name="location" rows="2" cols="31" required></textarea></td>
			</tr>

			<tr>
				<td>Description:</td>
				<td><textarea name="description" rows="5" cols="31"></textarea>
				</td>
			</tr>
			<tr>
				<td>This animal is in
					danger:</td>
				<td><input type="checkbox" name="danger" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Report" /></td>
			</tr>

		</table>
	</form>
</body>
</html>