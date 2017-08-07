<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/team.js"></script>
<div class="page-main-style">
	<h3>매치 상세 기록</h3>
	<h2>${match.m_away} ${match.m_challenger} : ${match.t_name} ${match.m_home}</h2>
	<br>
	일자 : ${match.m_date}	장소 : ${match.m_place}	mvp : ${match.m_mvp}
	<br><hr>
	<div>
		<c:if test="${match.m_type eq '야구'}">
			<!--  개인기록  ㅇ-->
			<c:if test="${basecount > 0}">
			<div class="detailRecord_home" style="float:right">
			<table class="homeRecord">
				<tr>
					<th>홈이름</th>
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
			<c:forEach var="list" items="${baselist}">
			<c:if test="${list.t_name == match.t_name}"><!--  홈팀 기록 -->
				<tr>
					<td><a href="mypage/main.do?id=${list.id}">${list.name}</a></td>
					<td>${list.b_bat}</td>
					<td>${list.b_hit}</td>
					<td>${list.b_rbi}</td>
					<td>${list.b_score}</td>
					<td>${list.b_avg}</td>
					<td>${list.b_win}</td>
					<td>${list.b_lose}</td>
					<td>${list.b_strike}</td>
					<td>${list.b_ip}</td>
					<td>${list.b_er}</td>
					<td>${list.b_era }</td>
				</tr>
			</c:if>
			</c:forEach>
			</table>
			</div>
			<div class="detailRecord_away" style="float:left;">
			<table class="awayRecord">
				<tr>
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
			<c:forEach var="list" items="${baselist}">
			<c:if test="${list.t_name == match.m_challenger}"><!--  어웨이팀 기록 -->
				<tr>
					<td><a href="mypage/main.do?id=${list.id}">${list.name}</a></td>
					<td>${list.b_bat}</td>
					<td>${list.b_hit}</td>
					<td>${list.b_rbi}</td>
					<td>${list.b_score}</td>
					<td>${list.b_avg }</td>
					<td>${list.b_win}</td>
					<td>${list.b_lose}</td>
					<td>${list.b_strike}</td>
					<td>${list.b_ip}</td>
					<td>${list.b_er}</td>
					<td>${list.b_era }</td>
				</tr>
			</c:if>
			</c:forEach>
			</table> 
			</div>
			</c:if>
			<c:if test="${basecount == 0}">
				${match.m_seq}매치의 개인기록을 입력해주세요.
			</c:if>
		</c:if>
		<c:if test="${match.m_type eq '농구'}">
			<!--  개인기록 ㄴ -->
			<c:if test="${basketcount > 0}">
			<div class="detailRecord_home" style="float:right">
			<table class="homeRecord">
				<tr>
					<th>홈이름</th>
					<th>득점</th>
					<th>도움</th>
					<th>리바운드</th>
					<th>스틸</th>
					<th>블록</th>
					<th>3점슛</th>
				</tr>
			<c:forEach var="list" items="${basketlist}">
			<c:if test="${list.t_name == match.t_name}"><!--  홈팀 기록 -->
				<tr>
					<td><a href="mypage/main.do?id=${list.id}">${list.name}</a></td>
					<td>${list.b_score}</td>
					<td>${list.b_assist}</td>
					<td>${list.b_rebound}</td>
					<td>${list.b_steel}</td>
					<td>${list.b_block}</td>
					<td>${list.b_3point}</td>
				</tr>
			</c:if>
			</c:forEach>
			</table>
			</div>
			<div class="detailRecord_away" style="float:left;">
			<table class="awayRecord">
				<tr>
					<th>이름</th>
					<th>득점</th>
					<th>도움</th>
					<th>리바운드</th>
					<th>스틸</th>
					<th>블록</th>
					<th>3점슛</th>
				</tr>
			<c:forEach var="list" items="${basketlist}">
			<c:if test="${list.t_name == match.m_challenger}"><!--  어웨이팀 기록 -->
				<tr>
					<td><a href="mypage/main.do?id=${list.id}">${list.name}</a></td>
					<td>${list.b_score}</td>
					<td>${list.b_assist}</td>
					<td>${list.b_rebound}</td>
					<td>${list.b_steel}</td>
					<td>${list.b_block}</td>
					<td>${list.b_3point}</td>
				</tr>
			</c:if>
			</c:forEach>
			</table> 
			</div>
			</c:if>
			<c:if test="${basketcount == 0}">
				${match.m_seq}매치의 개인기록을 입력해주세요.
			</c:if>
		</c:if>
		<c:if test="${match.m_type eq '축구'}">
			<!--  개인기록  ㅊ-->
			<c:if test="${footcount > 0}">
			<div class="detailRecord_home" style="float:right">
			<table class="homeRecord">
				<tr>
					<th>홈이름</th>
					<th>슈팅</th>
					<th>도움</th>
					<th>골</th>
					<th>공격포인트</th>
				</tr>
			<c:forEach var="list" items="${footlist}">
			<c:if test="${list.t_name == match.t_name}"><!--  홈팀 기록 -->
				<tr>
					<td><a href="mypage/main.do?id=${list.id}">${list.name}</a></td>
					<td>${list.f_shoot}</td>
					<td>${list.f_assist}</td>
					<td>${list.f_goal}</td>
					<td>${list.f_attack}</td>
				</tr>
			</c:if>
			</c:forEach>
			</table>
			</div>
			<div class="detailRecord_away" style="float:left;">
			<table class="awayRecord">
				<tr>
					<th>이름</th>
					<th>슈팅</th>
					<th>도움</th>
					<th>골</th>
					<th>공격포인트</th>
				</tr>
			<c:forEach var="list" items="${footlist}">
			<c:if test="${list.t_name == match.m_challenger}"><!--  어웨이팀 기록 -->
				<tr>
					<td><a href="mypage/main.do?id=${list.id}">${list.name}</a></td>
					<td>${list.f_shoot}</td>
					<td>${list.f_assist}</td>
					<td>${list.f_goal}</td>
					<td>${list.f_attack}</td>
				</tr>
			</c:if>
			</c:forEach>
			</table> 
			</div>
			</c:if>
			<c:if test="${footcount == 0}">
				${match.m_seq}매치의 개인기록을 입력해주세요.
			</c:if>
		</c:if>
	</div>
	
</div>