<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Animal</title>
</head>

<c:if test="${option eq 'EDIT'}">
<body>
	<c:set var="ismale" value="" />
	<c:set var="isfemale" value="" />
	<c:if test="${animal.gender=='MALE'}">
		<c:set var="ismale" value="selected" />
	</c:if>
	<c:if test="${animal.gender=='FEMALE'}">
		<c:set var="isfemale" value="selected" />
	</c:if>
	<form action="${contextPath}/animal/edit.htm" method="POST">
		<input type="hidden" name="id" value="${animal.animalid}" />
		<table>
			<tr>
				<td>Name:</td>
				<td><input type="text" name="name" size="30"
					required="required" value="${animal.name}" /></td>
			</tr>

			<tr>
				<td>Age:</td>
				<td><input type="number" name="age" size="50"
					value="${animal.age}" /></td>
			</tr>

			<tr>
				<td>Type:</td>
				<td><input type="text" name="type" size="30"
					required="required" value="${animal.type}" /></td>
			</tr>

			<tr>
				<td>breed:</td>
				<td><input type="text" name="breed" size="30"
					required="required" value="${animal.breed}" /></td>
			</tr>

			<tr>
				<td>Sex:</td>
				<td><select name="gender" >
						<option value="MALE" ${ismale}>MALE</option>
						<option value="FEMALE" ${isfemale}>FEMALE</option>
				</select></td>
			</tr>

			<tr>
				<td>Color:</td>
				<td><input type="text" name="color" size="30"
					required="required" value="${pet.animalcolor}" /></td>
			</tr>

			<tr>
				<td>Description:</td>
				<td><textarea name="description" rows="5" cols="31">${animal.description}</textarea></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="Edit" /></td>
			</tr>

		</table>
	</form>
</body>
</c:if>
<c:if test="${option eq 'ADD'}">
<body>

	<form action="${contextPath}/animal/add.htm" method="POST">
		<input type="hidden" name="id" value="${animal.animalid}" />
		<table>
			<tr>
				<td>Name:</td>
				<td><input type="text" name="name" size="30"
					required="required" /></td>
			</tr>

			<tr>
				<td>Age:</td>
				<td><input type="number" name="age" size="50"
					/></td>
			</tr>

			<tr>
				<td>Type:</td>
				<td><input type="text" name="type" size="30"
					required="required"/></td>
			</tr>

			<tr>
				<td>breed:</td>
				<td><input type="text" name="breed" size="30"
					required="required"/></td>
			</tr>

			<tr>
				<td>Sex:</td>
				<td><select name="gender">
						<option value="MALE">MALE</option>
						<option value="FEMALE">FEMALE</option>
				</select></td>
			</tr>

			<tr>
				<td>Color:</td>
				<td><input type="text" name="color" size="30"
					required="required"/></td>
			</tr>

			<tr>
				<td>Description:</td>
				<td><textarea name="description" rows="5" cols="31"></textarea></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="Add" /></td>
			</tr>

		</table>
	</form>
</body>
</c:if>
<c:if test="${option eq 'VIEW'}">
<body>
	<c:set var="ismale" value="" />
	<c:set var="isfemale" value="" />
	<c:if test="${animal.gender=='MALE'}">
		<c:set var="ismale" value="selected" />
	</c:if>
	<c:if test="${animal.gender=='FEMALE'}">
		<c:set var="isfemale" value="selected" />
	</c:if>

		<input type="hidden" name="id" value="${animal.animalid}" disabled/>
		<table>
			<tr>
				<td>Name:</td>
				<td><input type="text" name="name" size="30"
					required="required" value="${animal.name}" disabled/></td>
			</tr>

			<tr>
				<td>Age:</td>
				<td><input type="number" name="age" size="50"
					value="${animal.age}" disabled/></td>
			</tr>

			<tr>
				<td>Type:</td>
				<td><input type="text" name="type" size="30"
					required="required" value="${animal.type}" disabled/></td>
			</tr>

			<tr>
				<td>breed:</td>
				<td><input type="text" name="breed" size="30"
					required="required" value="${animal.breed}" disabled/></td>
			</tr>

			<tr>
				<td>Sex:</td>
				<td><select name="gender" disabled>
						<option value="MALE" ${ismale}>MALE</option>
						<option value="FEMALE" ${isfemale}>FEMALE</option>
				</select></td>
			</tr>

			<tr>
				<td>Color:</td>
				<td><input type="text" name="color" size="30"
					required="required" value="${pet.animalcolor}" disabled/></td>
			</tr>

			<tr>
				<td>Description:</td>
				<td><textarea name="description" rows="5" cols="31" disabled>${animal.description}</textarea></td>
			</tr>

		</table>

</body>
</c:if>
</html>