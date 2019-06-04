<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<c:if test="${option=='add'}">
	<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Pet</title>
	</head>

	<body>
		<form action="${contextPath}/pet/addnewpet.htm" method="POST">
			<table>
				<tr>
					<td>Pet Name:</td>
					<td><input type="text" name="petname" size="30"
						required="required" /></td>
				</tr>

				<tr>
					<td>Age:</td>
					<td><input type="number" name="petage" size="50" /></td>
				</tr>

				<tr>
					<td>Type:</td>
					<td><input type="text" name="pettype" size="30"
						required="required" /></td>
				</tr>

				<tr>
					<td>breed:</td>
					<td><input type="text" name="petbreed" size="30"
						required="required" /></td>
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
						required="required" /></td>
				</tr>

				<tr>
					<td>Location:</td>
					<td><textarea name="petlocation" rows="2" cols="31"></textarea>
					</td>
				</tr>

				<tr>
					<td>Description:</td>
					<td><textarea name="petdescription" rows="5" cols="31"></textarea>
					</td>
				</tr>

				<tr>
					<td colspan="2"><input type="submit" value="Add" /></td>
				</tr>

			</table>
		</form>
</c:if>
<c:if test="${option=='edit'}">
	<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Pet</title>
	</head>
	<c:set var="ismale" value="" />
	<c:set var="isfemale" value="" />
	<c:if test="${pet.gender=='MALE'}">
		<c:set var="ismale" value="selected" />
	</c:if>
	<c:if test="${pet.gender=='FEMALE'}">
		<c:set var="isfemale" value="selected" />
	</c:if>
	<body>
		<form action="${contextPath}/pet/editpet.htm?petid=${petid}"
			method="POST">
			<table>
				<tr>
					<td>Pet Name:</td>
					<td><input type="text" name="petname" size="30"
						required="required" value="${pet.petName}" /></td>
				</tr>

				<tr>
					<td>Age:</td>
					<td><input type="number" name="petage" size="50"
						value="${pet.petAge}" /></td>
				</tr>

				<tr>
					<td>Type:</td>
					<td><input type="text" name="pettype" size="30"
						required="required" value="${pet.animalType}" /></td>
				</tr>

				<tr>
					<td>breed:</td>
					<td><input type="text" name="petbreed" size="30"
						required="required" value="${pet.breed}" /></td>
				</tr>

				<tr>
					<td>Sex:</td>
					<td><select name="petgender">
							<option value="MALE" ${ismale}>MALE</option>
							<option value="FEMALE" ${isfemale}>FEMALE</option>
					</select></td>
				</tr>

				<tr>
					<td>Color:</td>
					<td><input type="text" name="petcolor" size="30"
						required="required" value="${pet.color}" /></td>
				</tr>

				<tr>
					<td>Location:</td>
					<td><textarea name="petlocation" rows="2" cols="31">${pet.location}</textarea></td>
				</tr>

				<tr>
					<td>Description:</td>
					<td><textarea name="petdescription" rows="5" cols="31">${pet.description}</textarea></td>
				</tr>

				<tr>
					<td colspan="2"><input type="submit" value="Edit" /></td>
				</tr>

			</table>
		</form>
</c:if>
<a href="${contextPath}/user/managepets.htm">Return to DashBoard</a>
</body>

</html>