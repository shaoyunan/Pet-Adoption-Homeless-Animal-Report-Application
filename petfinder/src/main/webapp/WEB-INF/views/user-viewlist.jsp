<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Individual Adoption List</title>
</head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	function ajaxEvent() {
		if (event.which === 13) {
			var key = event.which;
			//alert(event.which);

			var xmlHttp;
			try // Firefox, Opera 8.0+, Safari
			{
				xmlHttp = new XMLHttpRequest();
			} catch (e) {
				try // Internet Explorer
				{
					xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
				} catch (e) {
					try {
						xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
					} catch (e) {
						alert("Your browser does not support AJAX!");
						return false;
					}
				}
			}

			//xmlHttp.onreadystatechange = function(data) {
			//if (xmlHttp.readyState == 4) {
			//alert(data.size());
			//}
			//}
			var pet = new Object();
			var queryString = "";
			var type = document.getElementById("type").value;
			var breed = document.getElementById("breed").value;
			var color = document.getElementById("color").value;
			var agefrom = document.getElementById("agefrom").value;
			var ageto = document.getElementById("ageto").value;
			var sex = document.getElementById("sex").value;

			queryString = "type=" + type + "&breed=" + breed + "&color="
					+ color + "&agefrom=" + agefrom + "&ageto=" + ageto + "&sex=" + sex;
			//alert(queryString);
			//xmlHttp.open("POST", "../ajaxservice.htm?" + queryString, true);
			//xmlHttp.send();

			$.ajax({
				type : "GET",
				url : "../ajaxservice.htm?" + queryString,
				dataType : 'json',

				success : function(response) {
					$("#pettable tbody tr").remove();
					popTable(response);
				},
				error : function() {
					alert('error');
				}
			});

			function popTable(data) {
				var trHTML = '';
				$
						.each(
								data,
								function(key, val) {
									//alert(key+val.petname);
									//$('#pettable').append( '<tr><td>' + val.petname + '</td></tr>' );
									trHTML += '<tr><td>'
											+ val.petname
											+ '</td><td>'
											+ val.pettype
											+ '</td><td>'
											+ val.breed
											+ '</td><td>'
											+ val.petage
											+ '</td><td>'
											+ val.sex
											+ '</td><td>'
											+ val.color
											+ '</td><td><a href="${contextPath}/pet/view.htm?petid='
											+ val.petid + '"'
											+ '>View</a></td></tr>';

								});
				$('#pettable').append(trHTML);
			}
		}

	}
</script>
<body>
	<table>
		<tr>
			<td>Type: <input type="text" id="type" size="5"
				onkeypress="ajaxEvent()" /></td>
			<td>Breed: <input type="text" id="breed" size="5"
				onkeypress="ajaxEvent()" /></td>
			<td>Color: <input type="text" id="color" size="5"
				onkeypress="ajaxEvent()" /></td>
		</tr>
		<tr>
			<td>Sex: <input type="text" id="sex" size="5"
				onkeypress="ajaxEvent()" /></td>
			<td>Age From: <input type="number" id="agefrom" min="0" max="10"
				onkeypress="ajaxEvent()" /></td>
			<td>Age To: <input type="number" id="ageto" min="0" max="10"
				onkeypress="ajaxEvent()" /></td>	
		</tr>
		<tr><td><input type="button"
		onclick="location.href='${contextPath}/list/browseadoptionlist.htm?option=0&page=1'"
		value="Reset"></td></tr>
	</table>
	

	<table border="1" id="pettable">
		<thead>
			<th>Pet Name</th>
			<th>Type</th>
			<th>Breed</th>
			<th>Age</th>
			<th>Sex</th>
			<th>Color</th>
		</thead>
		<tbody>
			<c:forEach items="${petList}" var="msg">
				<tr>
					<td>${msg.petName}</td>
					<td>${msg.animalType}</td>
					<td>${msg.breed}</td>
					<td>${msg.petAge}</td>
					<td>${msg.gender}</td>
					<td>${msg.color}</td>
					<td><a href="${contextPath}/pet/view.htm?petid=${msg.petId}">View</a>
					</td>
				</tr>
			</c:forEach>
	</table>
	</tbody>
	<c:forEach begin="1" end="${pages+1}" varStatus="p">
		<a
			href="${contextPath}/list/browseadoptionlist.htm?option=${option}&page=${p.index}">${p.index}</a>
	</c:forEach>
	<br />
	<a href="${contextPath}/list/map.htm">Open Map</a>
	<a href="${contextPath}/user/dashboard.htm">Return to DashBoard</a>
</body>
</html>