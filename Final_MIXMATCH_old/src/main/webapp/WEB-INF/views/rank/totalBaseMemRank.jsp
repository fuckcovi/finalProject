<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	
	<div class="memList" >
		<table>
			<tr>
				<th>순위</th>
				<th>프로필</th>
				<th>이름</th>
				<th><a href='totalBaseMemRank.do?morder=b_bat'>타수</a></th>
				<th><a href='totalBaseMemRank.do?morder=b_hit'>안타수</a></th>
				<th><a href='totalBaseMemRank.do?morder=b_rbi'>타점</a></th>
				<th><a href='totalBaseMemRank.do?morder=b_score'>득점</a></th>
				<th><a href='totalBaseMemRank.do?morder=b_avg'>타율</a></th>
				<th><a href='totalBaseMemRank.do?morder=b_win'>승리</a></th>
				<th><a href='totalBaseMemRank.do?morder=b_lose'>패배</a></th>
				<th><a href='totalBaseMemRank.do?morder=b_strike'>삼진</a></th>
				<th><a href='totalBaseMemRank.do?morder=b_ip'>이닝</a></th>
				<th><a href='totalBaseMemRank.do?morder=b_er'>실점</a></th>
				<th><a href='totalBaseMemRank.do?morder=b_era'>방어율</a></th>
			</tr>
		<c:if test="${count>0 }">
		<c:forEach var="listMem" items="${listMem}">
			<tr>
				<td>${listMem.recordstatus}</td>
				<td>
				<c:if test="${fn:endsWith(listMem.profile_name, '.jpg') || fn:endsWith(listMem.profile_name, '.png') || fn:endsWith(listMem.profile_name, '.gif') || fn:endsWith(listMem.profile_name, '.JPG') || fn:endsWith(listMem.profile_name, '.PNG') || fn:endsWith(listMem.profile_name, '.GIF')}">
					<img src="imageViewMem.do?id=${listMem.id}" style="width:100px;height:100px;">
				</c:if> 
				<c:if test="${empty listMem.profile_name }">
					<img src="${pageContext.request.contextPath}/resources/images/profile.jpg" style="width:100px;height:100px;">
				</c:if>
				</td>
				<td><a href="mypage/main.do?id=${list.id}">${listMem.name}</a></td>
				<td>${listMem.b_bat}</td>
				<td>${listMem.b_hit}</td>
				<td>${listMem.b_rbi}</td>
				<td>${listMem.b_score}</td>
				<td><fmt:formatNumber value="${listMem.b_avg }" pattern="0.000"/></td>
				<td>${listMem.b_win}</td>
				<td>${listMem.b_lose}</td>
				<td>${listMem.b_strike}</td>
				<td>${listMem.b_ip}</td>
				<td>${listMem.b_er}</td>
				<td><fmt:formatNumber value="${listMem.b_era }" pattern="0.00"/></td>
			</tr>
		</c:forEach>
		</table>
		<div class="align-center">${pagingHtml}</div>
		</c:if>
		<c:if test="${count==0 }">
			<tr>
				<td colspan="14">야구 회원 기록이 없습니다.</td>
			</tr>
			</table>
		</c:if>
	</div>
</div>