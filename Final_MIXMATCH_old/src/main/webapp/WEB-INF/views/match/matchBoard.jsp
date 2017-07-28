<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>매치보드</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/layout.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/matchLayout.css">
</head>
<body>
<div class="page-main-style">
	<h2>매치보드</h2>
	<hr><br>
	<table>
		<tr>
			<th></th>
			<th>팀명</th>
			<th>지역</th>
			<th>날짜</th>
			<th>시간</th>
			<th></th>
		</tr>
<c:if test="${count == 0}">
	</table>
	<br><div class="align-center">등록된 매치가 없습니다. 매치등록을 해주세요 ↘↘</div>
</c:if>
<c:if test="${count > 0}">
		<tr>
			<td>팀사진</td>
			<td>팀명<br><input type="button" value="팀정보" class="btn-team-info"></td>
			<td>지역</td>
			<td>날짜</td>
			<td>시간</td>
			<td><input type="button" value="매치신청" class="btn"></td>
		</tr>
	</table>
</c:if>
	<br><hr> 
	<input type="button" value="매치등록" class="btn" style="float:right;margin-right:42px;">
</div>
</body>
</html>