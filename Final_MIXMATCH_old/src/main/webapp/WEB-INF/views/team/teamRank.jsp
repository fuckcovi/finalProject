<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/rank.css">
<div class="page-main-style">
	<h3>우리팀통합랭킹</h3>
	
	<table >
		<tr>
			<th>팀로고</th>
			<th>팀명</th>
			<th>승</th>
			<th>무</th>
			<th>패</th>
			<th>종목</th>
			<th>팀생성일</th>
			<th>팀연고지</th>
			<th>팀마스터</th>
		</tr>
		<tr>
			<td>
				<c:if test="${fn:endsWith(team.t_logo_name, '.jpg') || fn:endsWith(team.t_logo_name, '.png') || fn:endsWith(team.t_logo_name, '.gif') || fn:endsWith(team.t_logo_name, '.JPG') || fn:endsWith(team.t_logo_name, '.PNG') || fn:endsWith(team.t_logo_name, '.GIF')}">
					<img src="imageView.do?t_name=${team.t_name}" style="width:200px;height:200px;">
				</c:if>
				<c:if test="${empty team.t_logo_name }">
					<c:if test="${team.t_type eq '야구' }">
						<img src="${pageContext.request.contextPath}/resources/images/baseball.png" style="width:200px;height:200px;">
					</c:if>
					<c:if test="${team.t_type eq '농구' }">
						<img src="${pageContext.request.contextPath}/resources/images/basketball.png" style="width:200px;height:200px;">
					</c:if>
					<c:if test="${team.t_type eq '축구' }">
						<img src="${pageContext.request.contextPath}/resources/images/football.png" style="width:200px;height:200px;">
					</c:if>
				</c:if> 
			</td>
			<td>${team.t_name}</td>
			<td>${team.t_win}</td>
			<td>${team.t_draw}</td>	
			<td>${team.t_lose}</td>
			<td>${team.t_type}</td>
			<td>${team.t_regdate}</td>
			<td>${team.t_address}</td>
			<td>${team.id}</td>
		</tr>
	</table>
	<h3>우리팀원통합랭킹</h3> 
	<table class="ranking">
		<c:if test="${team.t_type eq '농구'}">
		<tr>
			<th>순위</th>
			<th>프로필</th>
			<th>이름</th>
			<th><a href='teamRank.do?t_name=${team.t_name}&bkorder=b_score'>득점</a></th>
			<th><a href='teamRank.do?t_name=${team.t_name}&bkorder=b_assist'>도움</a></th>
			<th><a href='teamRank.do?t_name=${team.t_name}&bkorder=b_rebound'>리바운드</a></th>
			<th><a href='teamRank.do?t_name=${team.t_name}&bkorder=b_steel'>스틸</a></th>
			<th><a href='teamRank.do?t_name=${team.t_name}&bkorder=b_block'>블록</a></th>
			<th><a href='teamRank.do?t_name=${team.t_name}&bkorder=b_3point'>3점슛</a></th>			
		</tr>
			<c:if test="${teamMemCount > 0}">
				<c:forEach var="list" items="${listTMemBasket}" varStatus="status">
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
						<td>${list.b_score}</td>
						<td>${list.b_assist}</td>
						<td>${list.b_rebound}</td>
						<td>${list.b_steel}</td>
						<td>${list.b_block}</td>
						<td>${list.b_3point}</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${teamMemCount == 0}">
				<tr>
					<td colspan="9">${team.t_name}의 회원기록이 존재하지 않습니다.</td>
				</tr>
			</c:if>
		</c:if>
		<c:if test="${team.t_type eq '야구'}">
		<tr>
			<th>순위</th>
			<th>프로필</th>
			<th>이름</th>
			<th><a href='teamRank.do?t_name=${team.t_name}&border=b_bat'>타수</a></th>
			<th><a href='teamRank.do?t_name=${team.t_name}&border=b_hit'>안타수</a></th>
			<th><a href='teamRank.do?t_name=${team.t_name}&border=b_rbi'>타점</a></th>
			<th><a href='teamRank.do?t_name=${team.t_name}&border=b_score'>득점</a></th>
			<th><a href='teamRank.do?t_name=${team.t_name}&border=b_avg'>타율</a></th>
			<th><a href='teamRank.do?t_name=${team.t_name}&border=b_win'>승리</a></th>
			<th><a href='teamRank.do?t_name=${team.t_name}&border=b_lose'>패배</a></th>
			<th><a href='teamRank.do?t_name=${team.t_name}&border=b_strike'>삼진</a></th>
			<th><a href='teamRank.do?t_name=${team.t_name}&border=b_ip'>이닝</a></th>
			<th><a href='teamRank.do?t_name=${team.t_name}&border=b_er'>실점</a></th>
			<th><a href='teamRank.do?t_name=${team.t_name}&border=b_era'>방어율</a></th>
		</tr>
			<c:if test="${teamMemCount > 0}">
				<c:forEach var="list" items="${listTMemBase}" varStatus="status">
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
						<td>${list.b_bat}</td>
						<td>${list.b_hit}</td>
						<td>${list.b_rbi}</td>
						<td>${list.b_score}</td>
						<td><fmt:formatNumber value="${list.b_avg }" pattern="0.000"/></td>
						<td>${list.b_win}</td>
						<td>${list.b_lose}</td>
						<td>${list.b_strike}</td>
						<td>${list.b_ip}</td>
						<td>${list.b_er}</td>
						<td><fmt:formatNumber value="${list.b_era }" pattern="0.00"/></td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${teamMemCount == 0}">
				<tr>
					<td colspan="14">${team.t_name}의 회원기록이 존재하지 않습니다.</td>
				</tr>
			</c:if>
		</c:if>
		<c:if test="${team.t_type eq '축구'}">
		<tr>
			<th>순위</th>
			<th>프로필</th>
			<th>이름</th>
			<th><a href='teamRank.do?t_name=${team.t_name}&forder=f_shoot'>슈팅</a></th>
			<th><a href='teamRank.do?t_name=${team.t_name}&forder=f_assist'>도움</a></th>
			<th><a href='teamRank.do?t_name=${team.t_name}&forder=f_goal'>득점</a></th>
			<th><a href='teamRank.do?t_name=${team.t_name}&forder=f_attack'>공격포인트</a></th>
		</tr>
			<c:if test="${teamMemCount > 0}">
				<c:forEach var="list" items="${listTMemFoot}" varStatus="status">
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
			</c:if>
			<c:if test="${teamMemCount == 0}">
				<tr>
					<td colspan="7">${team.t_name}의 회원기록이 존재하지 않습니다.</td>
				</tr>
			</c:if>
		</c:if>
	<%-- <c:forEach var="memlist" items="${listTeamMem}">
		<c:if test="${memlist.t_mem_auth>0}">
			<tr>
				<td>${memlist.id}</td>
				<td>${memlist.t_mem_auth}</td>
			</tr>
		</c:if>		
	</c:forEach>
	<c:forEach var="memlist" items="${listTeamMem}">
		<c:if test="${memlist.t_mem_auth==0}">
			<tr>
				<td>${memlist.id}</td>
				<td>${memlist.t_mem_auth}</td>
			</tr>
		</c:if>		
	</c:forEach> --%>
	</table>
	
</div>