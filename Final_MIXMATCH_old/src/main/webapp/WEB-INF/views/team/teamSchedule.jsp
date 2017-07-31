<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/team.js"></script>
<div class="page-main-style">
	<h3>팀일정결과</h3>
	<c:if test="${count==0 }">
		<div>
			소속 팀도 없는데 일정이 있을리가 
		</div>
	</c:if>
	<c:if test="${count>0 }">
	<ul style="list-style: none">
		<c:forEach var="list" items="${list}">
			<li style="float: left; width:150px;">
				<a href="#">${list.t_name}</a><!-- 클릭하면 ajax로 화면 바꿔서 일정 보이기 -->
			</li>
		</c:forEach>
	</ul>
	<br>
	<hr>
		<div>
		<h3>예정일정: 아직 안잡힘</h3>
		<table>			
			<tr>
				<td>경기일자</td>
				<td>경기장소</td>
				<td>경기시간</td>
				<td>홈팀</td>
				<td>어웨이팀</td>
			</tr>
			<c:forEach var="list" items="${matchList}">
			<tr>
				<td>${list.m_date}</td>
				<td>${list.m_place}</td>
				<td>${list.m_time}</td>
				
				<td>${list.t_name}</td>
				<td>${list.m_challenger}</td>
			</tr>
			</c:forEach>
		</table>
		</div>
		<div>
			<h3>확정일정: 참석해라</h3>
		</div>
		<div>
			<h3>결과 : 매칭 결과</h3>
		</div>
	</c:if>
</div>