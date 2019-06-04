<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Request Dashboard</title>
</head>
<body>
	<a href="${contextPath}/request/getrequests.htm?option=1">Inbox</a>
	<a href="${contextPath}/request/getrequests.htm?option=0">Sent</a>

	<c:if test="${option==0}">
		<table border="1">
			<tr>

				<th>To</th>
				<th>Date</th>
				<th>Status</th>
			</tr>
			<c:forEach items="${requestList}" var="msg">
				<tr>

					<td>${msg.pet.owner.userName}</td>
					<td>${msg.requestdate}</td>
					<td>${msg.status}</td>
					<td><a
						href="${contextPath}/request/cancel.htm?requestid=${msg.requestid}">Cancel</a>
					</td>
					<td><a
						href="${contextPath}/request/edit.htm?requestid=${msg.requestid}&to=${msg.pet.owner.userName}">Edit</a>
					</td>

				</tr>
			</c:forEach>
		</table>
	</c:if>

	<c:if test="${option==1}">
		<table border="1">
			<tr>
				<th>From</th>

				<th>Date</th>
				<th>Pet</th>
				<th>Status</th>
			</tr>
			<c:forEach items="${requestList}" var="msg">
				<tr>
					<td>${msg.from.userName}</td>

					<td>${msg.requestdate}</td>
					<td>${msg.pet.petName}</td>
					<td>${msg.status}</td>
					<td><a
						href="${contextPath}/request/view.htm?requestid=${msg.requestid}">View</a>
					</td>

				</tr>
			</c:forEach>
		</table>
	</c:if>
	<a href="${contextPath}/user/dashboard.htm">Return to DashBoard</a>
</body>
</html>