<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<%
	String contextPath = request.getContextPath();
%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
	<script type="text/javascript" src="js/libs/md5.js"> </script>
<script type="text/javascript">
	var maxSeatSelection = 0;
	var minSeatSelection = 0;
	var REQUEST_DOMAIN ="http://localhost:8080/TicketBookingApp";
	var jsonSeatInfo = {};
	var jsonSeat = {};
	$(document).ready(
			function() {
				generateResponderList();
				regenerateDeviceUniqueID();
				 $('.booknow').click(function(e){
					 e.preventDefault();
					 var email = $("#eMail").val();
					 var phone = $("#phone").val();
					 if(email != "" && phone != ""){
					console.log($( "form" ).serialize());
					var dataBooking = {};
					var deviceID = get_cookie('deviceUID');
					dataBooking =  $( "form" ).serialize() + "&deviceHash="+deviceID;
					//dataBooking['seatBookingInfo'] = jsonSeat;
					console.log("data" + JSON.stringify(dataBooking));
					 $
						.ajax({
							type : "POST",
							url : "<s:url action='newTicketBooking'/>",
							dataType : "json",
							 data:dataBooking,
							async : false
						})
						.done(
								function(data, textStatus, jqXHR) {
									console.log("Data " + JSON.stringify(data));
									if(data.errorCode == 1){
										alert("Your ticket successfully Booked!! \n Hi, "+ data.eMail + "\n Thank you for booking,\n Reference Number : " + data.bookTrnx);
										
									}else if(data.errorCode == 7){
										alert("Please try again!! your holding ticket time was expired!!");
									}
									maxSeatSelection = 0;
									minSeatSelection = 0;
									$('#seatBookingInfo').val();
									$('#customerInfo').trigger("reset");
									var modal = document.getElementById("contactPopup");
									modal.style.display = "none";
								});
					 }else{
						 alert("Please fille required fields value Email and Phone number!!");
					 }
				}); 
				$('.showContent').on(
						'click',
						function(e) {
							/* console.log("Data :: " + $(this).filter('.show').text()); */
							//console.log("Data1 :: " + $(this).text());
							console.log(JSON.stringify($(this).children(
									'input[class=show]').val()));
							var showId = $(this).children('input[class=show]')
									.val();
							var hallId = $(this).children('input[class=hall]')
									.val();
							var movieId = $(this)
									.children('input[class=movie]').val();
							var noofSeat = $(this).children(
									'input[class=seatinfo]').val();
							var seatTransaction = $(this).children('input[class=seatbooked]').val();
					
							creatSeatView(noofSeat, seatTransaction);
							$("#showId").val(showId);
							ticketBookedExists(showId);
							var modal = document.getElementById("seatPopup");
							modal.style.display = "block";

						});

				var modal = document.getElementById("contactPopup");

				var span = document.getElementsByClassName("contactClose")[0];

				span.onclick = function() {
					modal.style.display = "none";
				};
				var seatSelection = document
						.getElementsByClassName("seatSelection");
				var seatmodal = document.getElementById("seatPopup");

				window.onclick = function(event) {
					if (event.target == modal) {
						modal.style.display = "none";
					}
					if (event.target == seatSelection) {

					}
					if (event.target == seatmodal) {
						seatmodal.style.display = "none";
					}
				};

				/* var btn = document.getElementById("openpopup"); */

				var seatspan = document.getElementsByClassName("seatclose")[0];

				/* btn.onclick = function() {
				  modal.style.display = "block";
				} */

				seatspan.onclick = function() {
					seatmodal.style.display = "none";
				};
			});

	function generateResponderList() {

		var dynamicList = "";
		var jsonVal = $
				.ajax({
					type : "POST",
					url : "<s:url action='getMovieInformation'/>",
					dataType : "json",
					// data:{userID:userID},
					async : false
				})
				.done(
						function(data, textStatus, jqXHR) {
							// console.log ( JSON.stringify(data));
							/* const objData = JSON.parse(data); */
							if (data.errorCode == 1) {
								lstShowInformation = data.lstShowInformation;
								console.log(JSON.stringify(lstShowInformation));

								var oldHallID = "";
								var oldMovieID = "";
								if (lstShowInformation.length > 0) {
									for ( var i = 0; i < lstShowInformation.length; i++) {
										var showID = lstShowInformation[i].showId;
										var hallName = "";
										var hallDescription = "";
										var hallAddress = "";
										var hallPhone = "";
										var hallSeat = "";
										var movieName = "";
										var movieDuration = "";
										var movieDescription = "";
										var hallID = "";
										var movieID = "";
										var lstHallInfo = lstShowInformation[i].lstHallInformation;
										var lstMovieInfo = lstShowInformation[i].lstMovieInfo;
										var lstBookedSeat = lstShowInformation[i].LstSeatTransactionInfo;
										for ( var j = 0; j < lstHallInfo.length; j++) {
											hallID = lstHallInfo[j].hallId;
											hallName = lstHallInfo[j].name;
											hallAddress = lstHallInfo[j].address;
											hallDescription = lstHallInfo[j].description;
											hallSeat = lstHallInfo[j].seat;
										}
										for ( var j = 0; j < lstMovieInfo.length; j++) {
											movieID = lstMovieInfo[j].movieId;
											movieName = lstMovieInfo[j].movieName;
											movieDuration = lstMovieInfo[j].duration;
											movieDescription = lstMovieInfo[j].description;
										}
										/* if(oldHallID === ""){
											oldHallID = hallID;
										}
										if(oldMovieID === ""){
											oldMovieID = movieID;
										}
										if (oldMovieID != movieID){
											
										} */
										dynamicList += '<div class="row"><div class="column" ><div class="showContent"><h2>'
												+ movieName
												+ '</h2><p>'
												+ movieDescription
												+ '</p><p>Duration : '
												+ movieDuration
												+ ' minutes</p><p>Theatre Name : '
												+ hallName
												+ ', Location : '
												+ hallAddress + '</p>';
										var hiddenListShow = '<input type="hidden" class = "show" id = "show_'+ showID +'" value ="' +showID+'" />';
										var hiddenListHall = '<input type="hidden" class="hall" id = "hall_'+ hallID +'" value ="' +hallID+'" />';
										var hiddenListMovie = '<input type="hidden" class = "movie" id = "movie_'+ movieID +'" value ="' +movieID+'" />';
										var hiddenListMovie = '<input type="hidden" class = "seatinfo" id = "seat_'+ hallSeat +'" value ="' +hallSeat+'" />';
										var hiddenListBookedSeat = '<input type="hidden" class = "seatbooked" id = "bookedseat_'+ lstBookedSeat +'" value ="' +lstBookedSeat+'" />';
										dynamicList += hiddenListShow;
										dynamicList += hiddenListHall;
										dynamicList += hiddenListMovie;
										dynamicList += hiddenListBookedSeat;
										dynamicList += "</div></div></div>";
									}

									// dynamicList += '<div class="row"><div class="column" style="background-color:blue;"><h2>Column 1</h2><p>Some text..</p></div></div>';
								}

							}
							document.getElementById("mainviewshow").innerHTML += dynamicList;
						}).fail(function(jqXHR, textStatus, errorThrown) {
					console.log("The request has failed :: " + errorThrown);
				});

	}
	function creatSeatView(seat, seatTransaction) {
		document.getElementById("seatView").innerHTML = "";
		jsonSeatInfo = {};
		jsonSeat = {};

		var row = 10;
		console.log("No Of Seat :: " + seat);
		var column = parseInt(seat / row);
		console.log("No Of column :: " + column);
		var viewSeat = "";
		for ( var i = 1; i <= row; i++) {
			var columnRow = '<div class="seatRow">';
			for ( var j = 0; j <= column - 1; j++) {
				columnRow += '<input type="checkbox" value="'+i+':'+j+'" id="'+i+j+'" class = "seatSelection"/>';
			}
			columnRow += '</div>';
			viewSeat += columnRow;
		}
		viewSeat += '<input type="button" class="seatselected" value="Continue" ></input>';
		document.getElementById("seatView").innerHTML += viewSeat;
		$('.seatSelection').bind(
				'change',
				function() {
					var numberOfChecked = $('input:checkbox:checked').length;
					var totalCheckboxes = $('input:checkbox').length;
					var numberNotChecked = totalCheckboxes - numberOfChecked;
					if ($(this).is(':checked')){
						if(maxSeatSelection < 6){
							
						maxSeatSelection  = maxSeatSelection + 1;
						var val = $(this).val(); //.split(":");
						console.log("Json Data :: " +arrayVal);
						var arrayVal = val.split(":");
						jsonSeat['key'+arrayVal[0]+arrayVal[1]] = val;
						console.log("Json Data :: " + JSON.stringify(jsonSeat));
						}else{
							alert("You have reached maximum seat selection ");
							if ($(this).is(':checked'))
								$(this).prop("checked", false);
						}
					}
					else{
						
						var val = $(this).val(); //.split(":");
						var arrayVal = val.split(":");
						console.log("Json Data :: " +arrayVal);
						delete jsonSeat['key'+arrayVal[0]+arrayVal[1]] ;
						maxSeatSelection  = maxSeatSelection - 1;
						console.log("Json Data :: " + JSON.stringify(jsonSeat) + maxSeatSelection);
					}
					
					
					console.log("Data selection " + maxSeatSelection);
/* 					if (maxSeatSelection > ) {

						alert("You have reached maximum seat selection ");
						if ($(this).is(':checked'))
							$(this).prop("checked", false);
					} */
					$('#seatBookingInfo').val(JSON.stringify(jsonSeat));
				});
		$('.seatselected').click(function(){
			seatHold4Payment();
			var numberOfChecked = $('input:checkbox:checked').length;
			if(maxSeatSelection > 0){
				
			var seatmodal = document.getElementById("seatPopup");
			seatmodal.style.display = "none";
			var modal = document.getElementById("contactPopup");
			modal.style.display = "block";
			}else{
				alert("Please Select atleat one seat");
			}
		});
		
		

	}
	function setCookie(key, value) 
	{
		var date = new Date(),
	    expires = 'expires=';
		date.setDate(date.getDate() + 1);
		expires += date.toGMTString();
		document.cookie = key + '=' + value + '; ' + expires + '; path=/';
	}
	var delete_cookie = function(name) {
	    document.cookie = name + '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
	};
	function get_cookie( name ) 
	{
		var start = document.cookie.indexOf( name + "=" );
		var len = start + name.length + 1;
		if ( ( !start ) && ( name != document.cookie.substring( 0, name.length ) ) )
		{
			return null;
		}
		if ( start == -1 ) return null;
		var end = document.cookie.indexOf( ";", len );
		if ( end == -1 ) end = document.cookie.length;
		return unescape( document.cookie.substring( len, end ) );
	}

	function seatHold4Payment(){
		var seatSelected  =  $('#seatBookingInfo').val();
		var showId  =  $('#showId').val();
		var deviceID = get_cookie('deviceUID');
		var data = "seatBookingInfo="+ seatSelected + "&deviceHash="+deviceID + "&showId="+showId;
		$.ajax({
			type : "POST",
			url : "<s:url action='seatHoldAction'/>",
			dataType : "json",
			data: data,
			async : false
		}).done(function(data, textStatus, jqXHR) {
			if (data.errorCode == 1) {
				console.log("Successfully inserted");
				}
			
 		});
	}
	function regenerateDeviceUniqueID()
	{
		var existingDeviceId = JSON.parse(localStorage.getItem(""));
		if(existingDeviceId == null) 
		{
			var navigator_info = window.navigator;
			var screen_info = window.screen;
			var uid = navigator_info.mimeTypes.length;
			uid += navigator_info.userAgent.replace(/\D+/g, '');
			uid += navigator_info.plugins.length;
			uid += screen_info.height || '';
			uid += screen_info.width || '';
			uid += screen_info.pixelDepth || '';
					console.log("uid" + uid);
			
			var deviceID = hex_md5(uid);
			
			localStorage.setItem('deviceUniqueID', JSON.stringify(deviceID));
			setCookie("deviceUID", deviceID); 
			
			return deviceID;
		}
	}
	
	function ticketBookedExists(showId) {
		$.ajax({
			type : "POST",
			url : "<s:url action='getBookedSeatInformation'/>",
			dataType : "json",
			data: {"showId":showId},
			async : false
		}).done(function(data, textStatus, jqXHR) {
			if (data.errorCode == 1) {
				var lstSeatBooked = data.lstSeatTransactionInfo;
				
				 if (lstSeatBooked.length > 0) {
					for ( var i = 0; i < lstSeatBooked.length; i++) {
						var rowId = lstSeatBooked[i].rowId;
						var seatNumber = lstSeatBooked[i].seatNumber;
						$('#'+rowId +seatNumber ).prop('checked',true);
						$('#'+rowId +seatNumber ).attr("disabled", true);
					}
				}
 
			}
		});

	}
	function getBookedSeatInfo() {

		var dynamicList = "";
		var jsonVal = $
				.ajax({
					type : "POST",
					url : "<s:url action='getBookedSeatInformation'/>",
					dataType : "json",
					async : false
				})
				.done(
						function(data, textStatus, jqXHR) {
							// console.log ( JSON.stringify(data));
							/* const objData = JSON.parse(data); */
							if (data.errorCode == 1) {
								lstShowInformation = data.lstBookedSeatInfo;
								console.log(JSON.stringify(lstShowInformation));

								var oldHallID = "";
								var oldMovieID = "";
								if (lstShowInformation.length > 0) {
									for ( var i = 0; i < lstShowInformation.length; i++) {
										var showID = lstShowInformation[i].showId;
										var hallName = "";
										var hallDescription = "";
										var hallAddress = "";
										var hallPhone = "";
										var hallSeat = "";
										var movieName = "";
										var movieDuration = "";
										var movieDescription = "";
										var hallID = "";
										var movieID = "";
										var lstHallInfo = lstShowInformation[i].lstHallInformation;
										var lstMovieInfo = lstShowInformation[i].lstMovieInfo;
										for ( var j = 0; j < lstHallInfo.length; j++) {
											console.log("Data :: "
													+ lstHallInfo[j].address);
											hallID = lstHallInfo[j].hallId;
											hallName = lstHallInfo[j].name;
											hallAddress = lstHallInfo[j].address;
											hallDescription = lstHallInfo[j].description;
											hallSeat = lstHallInfo[j].seat;
										}
										for ( var j = 0; j < lstMovieInfo.length; j++) {
											console
													.log("Data :: "
															+ lstMovieInfo[j].movieName);
											movieID = lstMovieInfo[j].movieId;
											movieName = lstMovieInfo[j].movieName;
											movieDuration = lstMovieInfo[j].duration;
											movieDescription = lstMovieInfo[j].description;
										}
										/* if(oldHallID === ""){
											oldHallID = hallID;
										}
										if(oldMovieID === ""){
											oldMovieID = movieID;
										}
										if (oldMovieID != movieID){
											
										} */
										dynamicList += '<div class="row"><div class="column" ><div class="showContent"><h2>'
												+ movieName
												+ '</h2><p>'
												+ movieDescription
												+ '</p><p>Duration : '
												+ movieDuration
												+ ' minutes</p><p>Theatre Name : '
												+ hallName
												+ ', Location : '
												+ hallAddress + '</p>';
										var hiddenListShow = '<input type="hidden" class = "show" id = "show_'+ showID +'" value ="' +showID+'" />';
										var hiddenListHall = '<input type="hidden" class="hall" id = "hall_'+ hallID +'" value ="' +hallID+'" />';
										var hiddenListMovie = '<input type="hidden" class = "movie" id = "movie_'+ movieID +'" value ="' +movieID+'" />';
										dynamicList += hiddenListShow;
										dynamicList += hiddenListHall;
										dynamicList += hiddenListMovie;
										dynamicList += "</div></div></div>";
									}

									// dynamicList += '<div class="row"><div class="column" style="background-color:blue;"><h2>Column 1</h2><p>Some text..</p></div></div>';
								}

							}
							document.getElementById("mainviewshow").innerHTML += dynamicList;
						}).fail(function(jqXHR, textStatus, errorThrown) {
					console.log("THe request has failed :: " + errorThrown);
				});

	}
</script>
<style>
.column { /* float: left; */
	width: 100%;
	/* padding: 10px; */
}

.showContent {
	padding: 10px 31px;
	cursor: pointer;
}
/* Clear floats after the columns */
.row:after {
	content: "";
	display: table;
	clear: both;
}

.row {
	width: 100%;
	/* padding: 10px; */
	border-style: solid;
	border-width: 1px;
	border-radius: 5px;
	margin-bottom: 2px;
	background-color: blue;
}

.modal {
	display: none;
	position: fixed;
	z-index: 1;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	overflow: auto;
	background-color: rgb(0, 0, 0);
	background-color: rgba(0, 0, 0, 0.5);
}

.modal-content {
	background-color: #fefefe;
	margin: 15% auto;
	padding: 20px;
	border: 1px solid #888;
	width: 80%;
	text-align: center;
}

.close {
	color: #aaa;
	float: right;
	font-size: 28px;
	font-weight: bold;
}

.close:hover,.close:focus {
	color: black;
	text-decoration: none;
	cursor: pointer;
}
.inputsapce{
    padding-right: 70px;
  }
  .item{
  height: 3vw;
  }
</style>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to ticket Booking Application</title>
</head>

<body>
	<div>
		<div>
			<h1>Welcome to ticket Booking Application</h1>
		</div>
		<div id="mainviewshow"></div>
	</div>
	<div id="contactPopup" class="modal">
	<form name="customerInfo" id="customerInfo">
		<!-- Modal content -->
		<div class="modal-content">
			<span class="close contactClose">&times;</span>
			<div class="banner">
				<h1>Welcomes You</h1>
			</div>
			<div class="colums">
			<div class="item">
					<label for="emailddress"  class="inputsapce">Email Address<span>*</span> </label> <input
						id="eMail" type="text" name="eMail"  />
				</div>
				<div class="item">
					<label for="phone" class="inputsapce">Phone<span>*</span> </label> <input id="phone"
						type="tel" name="phone"  />
				</div>
				<div class="item">
					<label for="lname" class="inputsapce">Full Name<span></span> </label> <input
						id="lName" type="text" name="lName"  />
				</div>
				<div class="item">
					<label for="address1" class="inputsapce">Address 1 </label> <input id="addressLine1"
						type="text" name="addressLine1"  />
				</div>
				<div class="item">
					<label for="address2" class="inputsapce">Address 2 </label> <input id="addressLine2"
						type="text" name="addressLine2"  />
				</div>
				<div class="item">
					<label for="state" class="inputsapce">State </label> <input id="state" type="text"
						name="state"  />
				</div>
				<div class="item">
					<label for="zip" class="inputsapce">Zip/Postal Code </label> <input id="zipCode"
						type="text" name="zipCode"  />
				</div>
				<div class="item">
					<label for="city" class="inputsapce">City </label> <input id="city" type="text"
						name="city"  />
				</div>
				<input type = "hidden" id="seatBookingInfo" name="seatBookingInfo"  value=""/>
				<input type = "hidden" id="showId" name="showId"  value=""/>
				<input type = "button" class="booknow" value="Book Now"/>
				<input type = "button" class="back" value="Back">
				<input type = "button" class="cancel" value="Cancel"/>
			</div>
		</div>
		</form>
	</div>

	<div id="seatPopup" class="modal">

		<!-- Modal content -->
		<div class="modal-content">
			<span class="close seatclose">&times;</span>
			<div class="banner">
				<h1>Please select the seat:</h1>
			</div>
			<div class="seatview" id="seatView"></div>

		</div>
	</div>


</body>
</html>