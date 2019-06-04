<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Pet</title>
</head>
<body>
	<c:set var="ismale" value="" />
	<c:set var="isfemale" value="" />
	<c:if test="${pet.gender=='MALE'}">
		<c:set var="ismale" value="selected" />
	</c:if>
	<c:if test="${pet.gender=='FEMALE'}">
		<c:set var="isfemale" value="selected" />
	</c:if>
<body>

	<table>
		<tr>
			<td>Pet Name:</td>
			<td>${pet.petName}</td>
		</tr>

		<tr>
			<td>Age:</td>
			<td>${pet.petAge}</td>
		</tr>

		<tr>
			<td>type:</td>
			<td>${pet.animalType}</td>
		</tr>

		<tr>
			<td>breed:</td>
			<td>${pet.breed}</td>
		</tr>

		<tr>
			<td>Sex:</td>
			<td><select name="petgender" disabled>
					<option value="MALE" ${ismale}>MALE</option>
					<option value="FEMALE" ${isfemale}>FEMALE</option>
			</select></td>
		</tr>

		<tr>
			<td>Color:</td>
			<td>${pet.color}</td>
		</tr>

		<tr>
			<td>Location:</td>
			<td><p>${pet.location}</p></td>
		</tr>

		<tr>
			<td>Description:</td>
			<td><p>${pet.description}</p></td>
		</tr>

		<tr>
			<td>Pet Owner:</td>
			<td><p>${pet.owner.firstName}${pet.owner.lastName}</p></td>
		</tr>
		<tr>
			<td>Phone Number:</td>
			<td><p>${pet.owner.phoneNumber}</p></td>
		</tr>
		<tr>
			<td>Email:</td>
			<td><p>${pet.owner.email}</p></td>
		</tr>

	</table>
	<input type="button"
		onclick="location.href='${contextPath}/request/createrequest.htm?petid=${pet.petId}'"
		value="Send Request to Owner">
</body>
<a href="${contextPath}/list/browseadoptionlist.htm?option=0&page=1">Return</a>

</html>