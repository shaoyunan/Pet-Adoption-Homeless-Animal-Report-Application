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

	<form action="${contextPath}/request/sendrequest.htm" method="POST">
		<input type="hidden" name="petid" value="${petid}"> <input
			type="hidden" name="action" value="${action}"> <input
			type="hidden" name="requestid" value="${requestid}">
		<table>

			<tr>
				<td>Send To:</td>
				<td><input type="text" name="receiver" size="30"
					value="${receiver}" disabled /></td>
			</tr>

			<tr>
				<td>Message:</td>
				<td><textarea name="message" rows="5" cols="31" required>${message}</textarea>
				</td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="Add" /></td>
			</tr>

		</table>
	</form>
</body>
</html>