<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:getAsString name="title"/></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/layout.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/matchLayout.css">
<link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
 $(document).ready(function(){
	$( window ).resize(function() {
		if($( window ).width() < 1050 ){
			$("#main_side").hide();
		}else{
			$("#main_side").show();
		}
	});
		
 });
</script>
</head>
<body>
	<div id="main">
		<div id="main_header">
			<tiles:insertAttribute name="header"/>
		</div>
		<div id="main_body">
			<tiles:insertAttribute name="body"/>
		</div>
		<div id="main_side">
			<tiles:insertAttribute name="side"/>
		</div>
		<div id="main_footer">
			<tiles:insertAttribute name="footer"/>
		</div>
	</div>
</body>
</html>