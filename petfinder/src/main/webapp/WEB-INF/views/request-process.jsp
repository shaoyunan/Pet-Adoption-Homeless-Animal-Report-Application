<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Request Form</title>
</head>

<body>

		<input type="hidden" name="requestid" value="${requestid}">

		<table>
			<tr>
				<td>Pet:</td>
				<td><input type="text" name="petname" size="30"
					value="${petname}" disabled /></td>
			</tr>
			<tr>
				<td>From:</td>
				<td><input type="text" name="sender" size="30"
					value="${sender}" disabled /></td>
			</tr>

			<tr>
				<td>Message:</td>
				<td><textarea name="message" rows="5" cols="31" disabled>${message}</textarea>
				</td>
			</tr>

		</table>
		<c:if test="${status=='show'}">
		<input type="button" onclick="location.href='${contextPath}/request/process.htm?action=reject&requestid=${requestid}'" value="Reject">
		<input type="button" onclick="location.href='${contextPath}/request/process.htm?action=approve&requestid=${requestid}'" value="Approve">
		</c:if>
		<br/><a href="${contextPath}/request/getrequests.htm?option=1">Return</a>
</body>
</html>