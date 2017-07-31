<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/team.js"></script>
<div class="page-main-style">
	<h3>통합야구랭킹</h3>
	<ul style="list-style: none; " >
		<li style="float:left;"><a id="team" class="menuLink" >팀</a></li>
		<li style="float:left;"><a id="member" class="menuLink" >개인</a></li>
	</ul>
	<br>
	<div class="teamList">
	<table>
			<tr>
				<th>순위</th>
				<th>팀로고</th>
				<th>팀명</th>
				<th>연고지</th>
				<th>승리</th>
				<th>무승부</th>
				<th>패배</th>
			</tr>
		<c:forEach var="list" items="${list}" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td>
				<c:if test="${fn:endsWith(list.t_logo_name, '.jpg') || fn:endsWith(list.t_logo_name, '.png') || fn:endsWith(list.t_logo_name, '.gif') || fn:endsWith(list.t_logo_name, '.JPG') || fn:endsWith(list.t_logo_name, '.PNG') || fn:endsWith(list.t_logo_name, '.GIF')}">
					<img src="imageView.do?t_name=${list.t_name}" style="width:100px;height:100px;">
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
	</div>
	<div class="memList" style="display:none;">
		<table>
			<tr>
				<th>순위</th>
				<th>프로필</th>
				<th>이름</th>
				<th>타수</th>
				<th>안타수</th>
				<th>타점</th>
				<th>득점</th>
				<th>타율</th>
				<th>승리</th>
				<th>패배</th>
				<th>삼진</th>
				<th>이닝</th>
				<th>실점</th>
				<th>방어율</th>
			</tr>
		<c:forEach var="listMem" items="${listMem}" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td>
				<c:if test="${fn:endsWith(listMem.profile_name, '.jpg') || fn:endsWith(listMem.profile_name, '.png') || fn:endsWith(listMem.profile_name, '.gif') || fn:endsWith(listMem.profile_name, '.JPG') || fn:endsWith(listMem.profile_name, '.PNG') || fn:endsWith(listMem.profile_name, '.GIF')}">
					<img src="imageView.do?id=${listMem.id}" style="width:100px;height:100px;">
				</c:if> 
				</td>
				<td><a href="#?id=${listMem.id}">${listMem.name}</a></td>
				<td>${listMem.b_bat}</td>
				<td>${listMem.b_hit}</td>
				<td>${listMem.b_rbi}</td>
				<td>${listMem.b_score}</td>
				<td>타율계산</td>
				<td>${listMem.b_win}</td>
				<td>${listMem.b_lose}</td>
				<td>${listMem.b_strike}</td>
				<td>${listMem.b_ip}</td>
				<td>${listMem.b_er}</td>
				<td>방어율 계산</td>
			</tr>
		</c:forEach>
		</table>
	</div>
</div>