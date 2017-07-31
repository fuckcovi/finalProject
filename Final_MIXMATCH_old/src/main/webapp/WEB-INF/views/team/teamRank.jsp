<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="page-main-style">
	<h3>우리팀통합랭킹</h3>
	
	<table>
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
</div>
	<br>
<div>
	<h3>우리팀원통합랭킹</h3>
	<table>
		<c:if test="${team.t_type eq '농구'}">
		<tr>
			<th>순위</th>
			<th>프로필</th>
			<th>이름</th>
			<th>득점</th>
			<th>도움</th>
			<th>리바운드</th>
			<th>스틸</th>
			<th>블록</th>
			<th>3점슛</th>			
		</tr>
			<c:if test="${teamMemCount > 0}">
				<c:forEach var="list" items="${listTMemBasket}" varStatus="status">
					<tr>
						<td>${status.count}</td>
						<td>
						<c:if test="${fn:endsWith(list.profile_name, '.jpg') || fn:endsWith(list.profile_name, '.png') || fn:endsWith(list.profile_name, '.gif') || fn:endsWith(list.profile_name, '.JPG') || fn:endsWith(list.profile_name, '.PNG') || fn:endsWith(list.profile_name, '.GIF')}">
							<img src="imageView.do?id=${list.id}" style="width:100px;height:100px;">
						</c:if> 
						</td>
						<td><a href="#?id=${list.id}">${list.name}</a></td>
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
					<td colspan="9">소속 회원의 기록이 하나도 없을수가 있나</td>
				</tr>
			</c:if>
		</c:if>
		<c:if test="${team.t_type eq '야구'}">
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
			<c:if test="${teamMemCount > 0}">
				<c:forEach var="list" items="${listTMemBase}" varStatus="status">
					<tr>
						<td>${status.count}</td>
						<td>
						<c:if test="${fn:endsWith(list.profile_name, '.jpg') || fn:endsWith(list.profile_name, '.png') || fn:endsWith(list.profile_name, '.gif') || fn:endsWith(list.profile_name, '.JPG') || fn:endsWith(list.profile_name, '.PNG') || fn:endsWith(list.profile_name, '.GIF')}">
							<img src="imageView.do?id=${list.id}" style="width:100px;height:100px;">
						</c:if> 
						</td>
						<td><a href="#?id=${list.id}">${list.name}</a></td>
						<td>${list.b_bat}</td>
						<td>${list.b_hit}</td>
						<td>${list.b_rbi}</td>
						<td>${list.b_score}</td>
						<td>타율계산</td>
						<td>${list.b_win}</td>
						<td>${list.b_lose}</td>
						<td>${list.b_strike}</td>
						<td>${list.b_ip}</td>
						<td>${list.b_er}</td>
						<td>방어율 계산</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${teamMemCount == 0}">
				<tr>
					<td colspan="9">소속 회원의 기록이 하나도 없을수가 있나</td>
				</tr>
			</c:if>
		</c:if>
		<c:if test="${team.t_type eq '축구'}">
		<tr>
			<th>순위</th>
			<th>프로필</th>
			<th>이름</th>
			<th>슈팅</th>
			<th>도움</th>
			<th>득점</th>
			<th>공격포인트</th>
		</tr>
			<c:if test="${teamMemCount > 0}">
				<c:forEach var="list" items="${listTMemFoot}" varStatus="status">
					<tr>
						<td>${status.count}</td>
						<td>
						<c:if test="${fn:endsWith(list.profile_name, '.jpg') || fn:endsWith(list.profile_name, '.png') || fn:endsWith(list.profile_name, '.gif') || fn:endsWith(list.profile_name, '.JPG') || fn:endsWith(list.profile_name, '.PNG') || fn:endsWith(list.profile_name, '.GIF')}">
							<img src="imageView.do?id=${list.id}" style="width:100px;height:100px;">
						</c:if> 
						</td>
						<td><a href="#?id=${list.id}">${list.name}</a></td>
						<td>${list.f_shoot}</td>
						<td>${list.f_assist}</td>
						<td>${list.f_goal}</td>
						<td>${list.f_attack}</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${teamMemCount == 0}">
				<tr>
					<td colspan="9">소속 회원의 기록이 하나도 없을수가 있나</td>
				</tr>
			</c:if>
		</c:if>
	<c:forEach var="memlist" items="${listTeamMem}">
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
	</c:forEach>
	</table>
	
</div>