<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page-main-style">
	<h2>리그</h2>
	<hr class="l-style"><br>
	
	<form action="leagueList.do" method="get">
	<input type="submit" value="축구" name="type" 
	<c:if test="${type eq '축구'}">class="select-l-btn" disabled</c:if>
	<c:if test="${type ne '축구'}">class="btn"</c:if>>
		
	<input type="submit" value="야구" name="type"  
	<c:if test="${type eq '야구'}">class="select-l-btn"</c:if>
	<c:if test="${type ne '야구'}">class="btn"</c:if>>
	
	<input type="submit" value="농구" name="type"  
	<c:if test="${type eq '농구'}">class="select-l-btn"</c:if>
	<c:if test="${type ne '농구'}">class="btn"</c:if>>
	</form><br>
	
	<table class="l-style">
		<tr>
			<th>리그이름</th>
			<th>지역</th>
			<th>날짜</th>
			<th>시간</th>
			<th>모집현황</th>
			<th>참가신청</th>
		</tr>
		
		<c:if test="${count == 0}">
			<tr><td colspan="6">등록된 리그가 없습니다.</td></tr>
		</c:if>
		
		<c:if test="${count > 0}">
			<c:forEach var="league" items="${list}">
				<tr>
					<td>${league.l_title}</td>
					<td>${league.l_area}</td>
					<td>${league.l_date}</td>
					<td>${league.l_time}</td>
					<td>${league.l_team}/${league.l_number}</td>
					<td><input type="button" value="상세보기" class="btn"></td>
				</tr>
			</c:forEach>	
		</c:if>
	</table>
	<br>
	<div>${pagingHtml}</div>
	<br><hr class="l-style">
	
	<div>
	<input type="button" value="리그등록" id="insert_btn" class="btn" style="float:right;margin-right:42px;" onClick="location.href='leagueInsert.do'">
	</div>
</div>