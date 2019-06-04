<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate  type="time" var="hour" value="${now}" pattern="HH" />
<fmt:formatDate  type="date" var="day" value="${now}" pattern="F" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<title>User Dashboard</title>
</head>
<body>
	<p>Current Date: <fmt:formatDate pattern = "yyyy-MM-dd" value = "${now}" /></p>
	<p>
	<c:if test="${hour>=5 && hour<12}">
                Good Morning
            </c:if>
            <c:if test="${hour>=12 && hour<17}">
                Good Afternoon
            </c:if>
            <c:if test="${hour>=17 && hour<21}">
                Good Evening
            </c:if>
            <c:if test="${hour>=21 || hour<5}">
                Good Night
            </c:if><b>${sessionScope.user}</b></p>
            <p>You have ${newRequest} new adoption requests.</p>

            <p>User Actions:</p>
            <a href="${contextPath}/user/managepets.htm">Manage Your Adoption Information</a><br/>
            <a href="${contextPath}/request/getrequests.htm?option=1">View Your Adoption Requests</a><br/>
            <a href="${contextPath}/list/browseadoptionlist.htm?option=0&page=1">Browse Adoption List</a><br/>
            <a href="${contextPath}/list/browseshelterlist.htm">Browse Shelter List</a><br/>
            <a href="${contextPath}/user/report.htm">Report Homeless Animals</a><br/>
            <a href="${contextPath}/user/editprofile.htm">Edit Your Profile</a><br/>
            <a href="${contextPath}/user/logout.htm">Logout</a><br/>
</body>
</html>