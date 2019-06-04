<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Report</title>
</head>
<body>
	<table>
		<tr>
			<td>ReportID</td>
			<td>${report.reportid}</td>
		</tr>
		<tr>
			<td>Contact</td>
			<td>${report.contact}</td>
		</tr>
		<tr>
			<td>Report Date</td>
			<td>${report.reportdate}</td>
		</tr>
		<tr>
			<td>Status</td>
			<td>${report.status}</td>
		</tr>
		<tr>
			<td>Priority</td>
			<td>${report.priority}</td>
		</tr>
		<tr>
			<td>Animal Type</td>
			<td>${report.animaltype}</td>
		</tr>
		<tr>
			<td>Health Condition</td>
			<td>${report.healthcondition}</td>
		</tr>
		<tr>
			<td>Location</td>
			<td>${report.location}</td>
		</tr>

	<c:if test="${editable==true}">
		<input type="button"
			onclick="location.href='${contextPath}/report/complete.htm?reportid=${report.reportid}&type=${report.animaltype}'"
			value="Mark as Complete">
		</table>
	</c:if>

	<c:if test="${editable==false}">
		<tr>
			<td>Finished By</td>
			<td>${emp.fname}</td>
		</tr>
		<tr>
			<td>Complete Date</td>
			<td>${report.completeReport.finishDate}</td>
		</tr>
		<tr>
			<td>Note</td>
			<td>${report.completeReport.note}</td>
		</tr>
		</table>
	</c:if>
</body>
</html>