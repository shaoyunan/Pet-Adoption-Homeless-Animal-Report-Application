<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Complete Report</title>
</head>
<body>
	<form action="${contextPath}/report/complete.htm" method="POST">
		<input type="hidden" name="reportid" value="${reportid}" />
		<table>
			<tr>
				<td>Note:</td>
				<td><textarea name="note" rows="5" cols="31"></textarea></td>
			</tr>
			
			<tr>
				<td>Add new animal to adoption list:</td>
				<td><input type="checkbox" name="add" /></td>
			</tr>
			
			<tr>
				<td>Animal Type:</td>
				<td><input type="text" name="animaltype" value="${type}" /></td>
			</tr>
			
			<tr>
				<td>Age:</td>
				<td><input type="number" name="age"/></td>
			</tr>

			<tr>
				<td>breed:</td>
				<td><input type="text" name="petbreed" size="30"
					/></td>
			</tr>

			<tr>
				<td>Sex:</td>
				<td><select name="petgender">
						<option value="M">Male</option>
						<option value="F">Female</option>
				</select></td>
			</tr>

			<tr>
				<td>Color:</td>
				<td><input type="text" name="petcolor" size="30"
					 /></td>
			</tr>

			<tr>
				<td>Name:</td>
				<td><input type="text" name="name" size="30"
					 /></td>
			</tr>
			
			<tr>
				<td>Description:</td>
				<td><textarea name="petdescription" rows="5" cols="31"></textarea>
				</td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="Submit" /></td>
			</tr>

		</table>
	</form>
</body>
</html>