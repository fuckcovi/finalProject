<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/team.js"></script>
<div class="page-main-style">
	<h3>통합농구랭킹</h3>
	<ul style="list-style: none; " >
		<li style="float:left;">
			<input type="button" id="team" class="btn" value="팀랭킹" onclick="location.href='totalBasketRank.do'">
		</li>
		<li style="float:left;">
			<input type="button" id="member" class="btn" value="개인랭킹" onclick="location.href='totalBasketMemRank.do'">
		</li>
	</ul>
	<br>
	
	<div class="memList" >
		<table>
			<tr>
				<th>순위</th>
				<th>프로필</th>
				<th>이름</th>
				<th><a href='totalBasketMemRank.do?morder=b_score'>득점</a></th>
				<th><a href='totalBasketMemRank.do?morder=b_assist'>도움</a></th>
				<th><a href='totalBasketMemRank.do?morder=b_rebound'>리바운드</a></th>
				<th><a href='totalBasketMemRank.do?morder=b_steel'>스틸</a></th>
				<th><a href='totalBasketMemRank.do?morder=b_block'>블록</a></th>
				<th><a href='totalBasketMemRank.do?morder=b_3point'>3점슛</a></th>
			</tr>
		<c:forEach var="list" items="${listMem}" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td>
				<c:if test="${fn:endsWith(list.profile_name, '.jpg') || fn:endsWith(list.profile_name, '.png') || fn:endsWith(list.profile_name, '.gif') || fn:endsWith(list.profile_name, '.JPG') || fn:endsWith(list.profile_name, '.PNG') || fn:endsWith(list.profile_name, '.GIF')}">
					<img src="imageViewMem.do?id=${list.id}" style="width:100px;height:100px;">
				</c:if> 
				</td>
				<td><a href=mypage/main.do?id=${list.id}>${list.name}</a></td>
				<td>${list.b_score}</td>
				<td>${list.b_assist}</td>
				<td>${list.b_rebound}</td>
				<td>${list.b_steel}</td>
				<td>${list.b_block}</td>
				<td>${list.b_3point}</td>
			</tr>
		</c:forEach>
		</table>
	</div>
</div>