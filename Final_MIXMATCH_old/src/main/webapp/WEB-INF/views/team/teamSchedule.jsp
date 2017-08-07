<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/team.js"></script>
<div class="page-main-style">
	<h2>팀일정결과</h2>
	<hr class="style"><br>
	<c:if test="${count==0 }">
		<div>
			소속 팀도 없는데 일정이 있을리가 
		</div>
	</c:if>
	<c:if test="${count>0 }">
	<ul style="list-style: none" id="teamScheduleList" >
		<li style="float: left; width:150px;" value="allList">
			<input type="button" class="btn" value="전체보기">
		</li>
		<c:forEach var="list" items="${list}">
			<li style="float: left; width:150px;" value="${list.t_name}"><!-- 클릭하면 ajax로 화면 바꿔서 일정 보이기 -->
				<input type="button" class="btn" value="${list.t_name}">
			</li>
		</c:forEach>
	</ul>
	<br>
	<hr size="1" width="85%">
	<h4>예정일정: 아직 안잡힘</h4>
		<div class="scheduleList xplan">
			<table class="style">			
			<tr class="tablehead">
				<th>경기일자</th>
				<th>경기장소</th>
				<th>경기시간</th>
				<th>매칭신청팀</th>
			</tr>
			<c:forEach var="teamlist" items="${list}">
				<c:forEach var="matchlist" items="${matchList}" >
					<c:if test="${matchlist.m_challenger==null && ( matchlist.t_name == teamlist.t_name )}">
						<tr class="${matchlist.t_name}">
							<td>${matchlist.m_date}</td>
							<td>${matchlist.m_place}</td>
							<td>${matchlist.m_time}</td>
							<td>${matchlist.t_name}</td>
						</tr>
					</c:if>
				</c:forEach>	
			</c:forEach>
			<tr class="tablenull" style="display: none;">
				<td colspan="4">예정일정없음</td>
			</tr>
			</table>
		</div>
		<h4>확정일정</h4>
		<div class="scheduleList cplan">
			<table class="style">			
			<tr class="tablehead">
				<th>경기일자</th>
				<th>경기장소</th>
				<th>경기시간</th>
				<th>홈팀</th>
				<th>어웨이팀</th>
			</tr>
			<c:forEach var="teamlist" items="${list}">
				<c:forEach var="matchlist" items="${matchList}">
					<c:if test="${matchlist.m_challenger!=null && matchlist.m_home==-1 && matchlist.m_away==-1 && (matchlist.t_name==teamlist.t_name || matchlist.m_challenger==teamlist.t_name)}">
					<tr class="${matchlist.t_name} ${matchlist.m_challenger}">
						<td>${matchlist.m_date}</td>
						<td>${matchlist.m_place}</td>
						<td>${matchlist.m_time}</td>
						<td>${matchlist.t_name}</td>
						<td>${matchlist.m_challenger}</td>
					</tr>
					</c:if>
				</c:forEach>
			</c:forEach>
			<tr class="tablenull" style="display: none;">
				<td colspan="4">확정일정없음</td>
			</tr>
			</table>
		</div>
		<h4>결과 : 매칭 결과</h4>
		<div class="scheduleList fplan">
			<table class="style">	 		
			<tr class="tablehead">
				<th>경기일자</th>
				<th>매치결과</th>
				<th>MVP</th>
			</tr>
			<c:forEach var="teamlist" items="${list}">
				<c:forEach var="matchlist" items="${matchList}">
					<c:if test="${matchlist.m_home!=-1 && matchlist.m_away!=-1 && (matchlist.t_name==teamlist.t_name || matchlist.m_challenger==teamlist.t_name)}">
					<tr class="${matchlist.t_name} ${matchlist.m_challenger}">
						<td>${matchlist.m_date}</td>
						<td><a href="teamRecord.do?m_seq=${matchlist.m_seq}">${matchlist.t_name} ${matchlist.m_home} : ${matchlist.m_away} ${matchlist.m_challenger}</a></td>
						<td>${matchlist.m_mvp}</td>
					</tr>
					</c:if>
				</c:forEach>
			</c:forEach>
			<tr class="tablenull" style="display: none;">
				<td colspan="4">매치결과없음</td>
			</tr>
			</table>
		</div>
	</c:if>
	<br><hr class="style"> 
</div>