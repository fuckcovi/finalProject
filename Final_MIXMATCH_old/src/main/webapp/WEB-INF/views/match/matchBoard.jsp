<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/match.js"></script>
<div class="page-main-style">
	<h2>매치보드</h2>
	<hr class="style"><br>
	<form action="matchBoard.do" id="type_form" method="get">
	<input type="submit" value="축구" name="type" 
	<c:if test="${type eq '축구'}">class="select-btn" disabled</c:if>
	<c:if test="${type ne '축구'}">class="btn"</c:if>>	
	<input type="submit" value="야구" name="type"  
	<c:if test="${type eq '야구'}">class="select-btn"</c:if>
	<c:if test="${type ne '야구'}">class="btn"</c:if>>
	<input type="submit" value="농구" name="type"  
	<c:if test="${type eq '농구'}">class="select-btn"</c:if>
	<c:if test="${type ne '농구'}">class="btn"</c:if>>
	</form><br>
	<table class="style">
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
	<br><div class="align-center">등록된 매치가 없습니다.</div>
</c:if>
<c:if test="${count > 0}">
	<c:forEach var="match" items="${list}">
		<c:if test="${empty match.m_challenger}">
		<tr>
			<td>팀사진</td>
			<td>${match.t_name}<br><input type="button" value="팀정보" class="btn-team-info" onclick="location.href='${pageContext.request.contextPath}/teamInfo.do?t_name=${match.t_name}'"></td>
			<td>${match.m_area}</td>
			<td>${match.m_date}</td>
			<td>${match.m_time}</td>
			<td><input type="button" value="상세보기" class="btn" onClick="location.href='matchDetail.do?m_seq=${match.m_seq}'"></td>
		</tr>
		</c:if>
	</c:forEach>
	</table>
</c:if>
	<br><hr class="style">
	<c:if test="${!empty t_name}">
		<input type="button" value="매치등록" id="insert_btn" class="btn" style="float:right;margin-right:42px;" onClick="location.href='matchInsert.do'">
	</c:if>
</div>