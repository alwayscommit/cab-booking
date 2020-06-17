<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.18/js/jquery.dataTables.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/gh/openlayers/openlayers.github.io@master/en/v6.3.1/css/ol.css"
	type="text/css">
<script
	src="https://cdn.jsdelivr.net/gh/openlayers/openlayers.github.io@master/en/v6.3.1/build/ol.js"></script>
<style>
.map {
	height: 450px;
	width: 50%;
}
</style>
<script type="text/javascript">
	var map;
	var markerLayers = [];
	$(document).ready(function() {
		setupMap();
		getAllCabStatus();
	})

	function findLatLong() {
		$
				.ajax({
					url : "https://us1.locationiq.com/v1/search.php?key=09fe7f8a046535&q="
							+ $('#location').val() + "&format=json",
					method : "GET",
					success : function(locationData) {
						$
								.ajax({
									url : "https://us1.locationiq.com/v1/search.php?key=09fe7f8a046535&q="
											+ $('#desitination').val()
											+ "&format=json",
									method : "GET",
									success : function(destinationData) {
										if (Array.isArray(locationData)
												&& Array
														.isArray(destinationData)) {
											bookCab(locationData,
													destinationData);
										} else {
											alert("Unable to identify your location!");
										}
									},
									error : function(error) {

										alert(error.responseJSON.message);
									}
								})
					},
					error : function(error) {

						alert(error.responseJSON.message);
					}
				})
	}

	function bookCab(locationData, destinationData) {
		var bookingDetails = {};
		bookingDetails.startLongitude = locationData[0].lon;
		bookingDetails.startLatitude = locationData[0].lat;
		bookingDetails.endLongitude = destinationData[0].lon;
		bookingDetails.endLatitude = destinationData[0].lat;
		bookingDetails.customerMobileNumber = $('#customerNumber').val();
		bookingDetails.numberOfPassengers = $('#passengersNo').val();

		var bookingObj = JSON.stringify(bookingDetails);
		$.ajax({
			url : "http://localhost:8080/cab-service/booking",
			method : "POST",
			data : bookingObj,
			contentType : 'application/json; charset=utf-8',
			success : function(response) {
				alert('Cab has been booked successfully!');
				reset();
				getAllCabStatus();
			},
			error : function(error) {
				alert(error.responseJSON.message);
			}
		})

	}

	function setupMap() {
		map = new ol.Map({
			target : 'map',
			layers : [ new ol.layer.Tile({
				source : new ol.source.OSM()
			}) ],
			view : new ol.View({
				center : ol.proj.fromLonLat([ 72.9824377, 19.2309696 ]),
				zoom : 12.5
			})
		});

		$('#map').data('map', map);
	}

	function getAllCabStatus() {
		$
				.ajax({
					url : "http://localhost:8080/cab-service/booking/status",
					method : "GET",
					dataType : "json",
					success : function(data) {
						var tableBody = $('#tblCabs tbody');
						tableBody.empty();
						$(data)
								.each(
										function(index, element) {
											if (element.customerName == undefined) {
												element.customerName = "-";
											}
											if (element.carStatus.toUpperCase() == "AVAILABLE") {
												addMarker(element.carLongitude,
														element.carLatitude);
											}
											tableBody.append('<tr>' + '<td>'
													+ element.driverName
													+ '</td>' + '<td>'
													+ element.customerName
													+ '</td>' + '<td>'
													+ element.carStatus
													+ '</td>' + '</tr>');
										})
					},
					error : function(error) {
						alert(error.responseJSON.message);
					}
				})
	}

	function addMarker(longitude, latitude) {
		var layer = new ol.layer.Vector({
			source : new ol.source.Vector({
				features : [ new ol.Feature({
					geometry : new ol.geom.Point(ol.proj.fromLonLat([
							longitude, latitude ]))
				}) ]
			}),
			style : new ol.style.Style({
				image : new ol.style.Icon({
					scale : 0.1,
					anchor : [ 0.5, 1 ],
					anchorXUnits : "fraction",
					anchorYUnits : "fraction",
					src : "car1.png",
					opacity : 1
				})
			})
		});
		var map = $('#map').data('map');
		map.addLayer(layer);
		markerLayers.push(layer);
	}

	function reset() {
		$('#customerNumber').val('');
		$('#desitination').val('');
		$('#passengersNo').val('');
		var map = $('#map').data('map');
		markerLayers.forEach(function(layer) {
			map.removeLayer(layer);
		});
	}
</script>
</head>
<body>
	<div>
		<table>

			<tr>
				<td>Customer Number</td>
				<td><input type="text" id="customerNumber" value=""></td>
			</tr>
			<tr>
				<td>Location</td>
				<td><input type="text" id="location"
					value="Everest World, Thane, Maharashtra"></td>
			</tr>
			<tr>
				<td>Destination</td>
				<td><input type="text" id="desitination"
					value=""></td>
			</tr>
			<tr>
				<td>Number of Passengers</td>
				<td><input type="text" id="passengersNo" value=""></td>
			</tr>
			<tr colspan="2">
				<td><input type="button" onclick="findLatLong()"
					value="Book Cab" id="btnBookCab"></td>
			</tr>
		</table>
		<br>
		<table border="1" id="tblCabs">
			<thead>
				<tr>
					<th>Driver Name</th>
					<th>Customer Name</th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody>

			</tbody>

		</table>
		<br> <br>
		<div id="map" class="map"></div>
	</div>
</body>
</html>