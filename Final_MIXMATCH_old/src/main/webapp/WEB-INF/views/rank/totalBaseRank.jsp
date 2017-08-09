<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/team.js"></script>
<div class="page-main-style">
	<h3>통합야구랭킹</h3>
	<div class="align-center" style="height:30px;">
	<ul style="list-style:none;  " >
		<li style="display: inline; ">
			<input type="button" id="team" class="btn" value="팀랭킹" onclick="location.href='totalBaseRank.do'">
		</li>
		<li style="display: inline; ">
			<input type="button" id="member" class="btn" value="개인랭킹" onclick="location.href='totalBaseMemRank.do'">
		</li>
	</ul>
	</div>
	<br>
	<div class="teamList">
	<table>
			<tr>
				<th>순위</th>
				<th>팀로고</th>
				<th>팀명</th>
				<th>연고지</th>
				<th><a href='totalBaseRank.do?order=t_win'>승리</a></th>
				<th><a href='totalBaseRank.do?order=t_draw'>무승부</a></th>
				<th><a href='totalBaseRank.do?order=t_lose'>패배</a></th>
			</tr>
		<c:if test="${count>0 }">
		<c:forEach var="list" items="${list}">
			<tr>
				<td>${list.status}</td>
				<td>
				<c:if test="${fn:endsWith(list.t_logo_name, '.jpg') || fn:endsWith(list.t_logo_name, '.png') || fn:endsWith(list.t_logo_name, '.gif') || fn:endsWith(list.t_logo_name, '.JPG') || fn:endsWith(list.t_logo_name, '.PNG') || fn:endsWith(list.t_logo_name, '.GIF')}">
					<img src="imageView.do?t_name=${list.t_name}" style="width:100px;height:100px;">
				</c:if> 
				<c:if test="${empty list.t_logo_name }">
				<c:if test="${list.t_type eq '야구' }">
					<img src="${pageContext.request.contextPath}/resources/images/baseball.png" style="width:100px;height:100px;">
				</c:if>
				<c:if test="${list.t_type eq '농구' }">
					<img src="${pageContext.request.contextPath}/resources/images/basketball.png" style="width:100px;height:100px;">
				</c:if>
				<c:if test="${list.t_type eq '축구' }">
					<img src="${pageContext.request.contextPath}/resources/images/football.png" style="width:100px;height:100px;">
				</c:if>
				</c:if>
				</td>
				<td><a href="teamInfo.do?t_name=${list.t_name}">${list.t_name}</a></td>
				<td>${list.t_address}</td>
				<td>${list.t_win}</td>
				<td>${list.t_draw}</td>
				<td>${list.t_lose}</td>
			</tr>
		
		</c:forEach>
		</table>
			<div class="align-center">${pagingHtml}</div>
		</c:if>
		<c:if test="${count==0 }">
			<tr>
				<td colspan="7">등록된 야구팀이 없습니다.</td>
			</tr>
			</table>
		</c:if>
	</div>
</div>