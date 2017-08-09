<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/team.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/rank.css">
<div class="page-main-style">
	<h3>통합축구랭킹</h3>
	<div class="align-center" style="height:30px;">
	<ul style="list-style:none;  " >
		<li style="display: inline; ">
			<input type="button" id="team" class="btn" value="팀랭킹" onclick="location.href='totalFootRank.do'">
		</li>
		<li style="display: inline; ">
			<input type="button" id="member" class="btn" value="개인랭킹" onclick="location.href='totalFootMemRank.do'">
		</li>
	</ul>
	</div>
	<br>
	
	<div class="memList">
		<table class="ranking">
			<tr>
				<th>순위</th>
				<th>프로필</th>
				<th>이름</th>
				<th><a href='totalFootMemRank.do?morder=f_shoot'>슈팅</a></th>
				<th><a href='totalFootMemRank.do?morder=f_assist'>도움</a></th>
				<th><a href='totalFootMemRank.do?morder=f_goal'>득점</a></th>
				<th><a href='totalFootMemRank.do?morder=f_attack'>공격포인트</a></th>
			</tr>
			<c:if test="${count>0 }">
		<c:forEach var="list" items="${listMem}" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td>
				<c:if test="${fn:endsWith(list.profile_name, '.jpg') || fn:endsWith(list.profile_name, '.png') || fn:endsWith(list.profile_name, '.gif') || fn:endsWith(list.profile_name, '.JPG') || fn:endsWith(list.profile_name, '.PNG') || fn:endsWith(list.profile_name, '.GIF')}">
					<img src="imageViewMem.do?id=${list.id}" style="width:100px;height:100px;">
				</c:if>
				<c:if test="${empty list.profile_name }">
					<img src="${pageContext.request.contextPath}/resources/images/profile.jpg" style="width:100px;height:100px;">
				</c:if> 
				</td>
				<td><a href="mypage/main.do?id=${list.id}">${list.name}</a></td>
				<td>${list.f_shoot}</td>
				<td>${list.f_assist}</td>
				<td>${list.f_goal}</td>
				<td>${list.f_attack}</td>
			</tr>
		</c:forEach>
		</table>
		<div class="align-center">${pagingHtml}</div>
		</c:if>
		<c:if test="${count==0 }">
			<tr>
				<td colspan="7">축구 회원 기록이 없습니다.</td>
			</tr>
			</table>
		</c:if>
	</div>
</div>