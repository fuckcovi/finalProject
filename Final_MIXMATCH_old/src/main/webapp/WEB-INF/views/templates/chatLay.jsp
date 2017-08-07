<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:getAsString name="title"/></title>
</head>
<body>
	<div id="main" style="height:350px;width:350px;">
		
		<div id="main_body" style="text-align: center;">
			<tiles:insertAttribute name="body"/>
		</div>
		
	</div>
</body>
</html>