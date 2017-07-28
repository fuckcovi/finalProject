<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스코어보드</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/layout.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/matchLayout.css">
</head>
<body>
<div class="page-main-style">
	<h2>스코어보드</h2>
	<hr><br>
	<table>
		<tr>
			<th>지역</th>
			<th>날짜</th>
			<th>시간</th>
			<th></th>
			<th>결과</th>
			<th></th>
		</tr>
<c:if test="${count == 0}">
	</table>
	<br><div class="align-center">매칭된 경기가 없습니다.</div>
</c:if>
<c:if test="${count > 0}">
		<tr>
			<td>지역</td>
			<td>날짜</td>
			<td>시간</td>
			<td>홈팀<br><input type="button" value="팀정보" class="btn-team-info"></td>
			<td><input type="button" value="결과등록" class="btn"></td>
			<td>어웨이팀<br><input type="button" value="팀정보" class="btn-team-info"></td>
		</tr>
	</table> 
</c:if>  
	<br><hr>
</div>
</body>
</html>