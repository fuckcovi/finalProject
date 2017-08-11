<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/monthly.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/monthly.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#mycalendar').monthly({
			mode: 'event',
			//jsonUrl: 'events.json',
			//dataType: 'json'
			xmlUrl: 'events.xml'
		});

		$('#mycalendar2').monthly({
			mode: 'picker',
			target: '#mytarget',
			setWidth: '250px',
			startHidden: true,
			showTrigger: '#mytarget',
			stylePast: true,
			disablePast: true
		});

    switch(window.location.protocol) {
	case 'http:':
	case 'https:':
	// running on a server, should be good.
	break;
	case 'file:':
	alert('Just a heads-up, events will not work when run locally.');
	} 
	});
		

	//});
</script>
<div class="page">
		<div style="width:100%; max-width:600px; display:inline-block;">
			<div class="monthly" id="mycalendar"></div>
		</div>
		<br><br>
		<div style="display:inline-block; width:250px;">
			<input type="text" id="mytarget" value="Select Date">
			<div class="monthly" id="mycalendar2"></div>
		</div>
</div>
