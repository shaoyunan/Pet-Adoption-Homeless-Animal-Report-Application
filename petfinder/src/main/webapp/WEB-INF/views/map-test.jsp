<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Report Map</title>
<meta name="viewport" content="initial-scale=1.0">
<meta charset="utf-8">
<style>
/* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
#map {
	height: 100%;
}
/* Optional: Makes the sample page fill the window. */
html, body {
	height: 100%;
	margin: 0;
	padding: 0;
}
</style>
<script
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD0f4SMovlmc8aTmo_5_0PRSXVB599HP20"></script>
</head>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script>
	var geocoder;
	var map;
	var lastWindow = false;
	function initialize() {
		geocoder = new google.maps.Geocoder();
		var latlng = new google.maps.LatLng(42.361145, -71.057083);
		var mapOptions = {
			zoom : 14,
			center : latlng,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		}
		map = new google.maps.Map(document.getElementById('map'), mapOptions);
		ajaxEvent();//call the function
	}

	function ajaxEvent() {
		//alert("called");
		var xmlHttp;

		$.ajax({
			type : "GET",
			url : "../mapservice.htm?",
			dataType : 'json',

			success : function(response) {
				popMap(response);
			},
			error : function() {
				alert('error');
			}
		});

		function popMap(data) {
			$.each(data, function(key, val) {

				codeAddress(val);

			});

		}

	}

	function codeAddress(val) {
		geocoder
				.geocode(
						{
							address : val.location
						},
						function(results, status) {
							if (status == google.maps.GeocoderStatus.OK) {
								map.setCenter(results[0].geometry.location);
								var marker = new google.maps.Marker({
									map : map,
									position : results[0].geometry.location
								});
								marker
										.setIcon('http://maps.google.com/mapfiles/ms/icons/green-dot.png');
								if (val.priority === 'MEDIUM') {
									marker
											.setIcon('http://maps.google.com/mapfiles/ms/icons/yellow-dot.png');
								} else if (val.priority === 'HIGH') {
									marker
											.setIcon('http://maps.google.com/mapfiles/ms/icons/red-dot.png');
								}

								var infoWindow = new google.maps.InfoWindow({
									content : "<p>Animal Type: " + val.type
											+ "<br />" + "Location: "
											+ val.location + "<br />Message: "
											+ val.description
											+ "<br />Contact: " + val.contact
											+ "</p>"
								});
								google.maps.event.addListener(marker, 'click',
										function() {
											if (lastWindow) {
												lastWindow.close();
											}
											lastWindow = infoWindow;
											infoWindow.open(map, marker);
										});
							} else {
								alert('Geocode was not successful for the following reason: '
										+ status);
							}
						});
	}
	google.maps.event.addDomListener(window, 'load', initialize);
</script>

<body>
	<div id="map"></div>
</body>
</html>