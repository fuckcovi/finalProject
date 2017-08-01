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
	일자 : ${match.m_date}	장소 : ${match.m_place}
	<br><hr>
	<div><!--  개인기록  -->
		<c:forEach var="list" items="${list}">
		<c:if test="${list.t_name == match.t_name}"><!--  홈팀 기록 -->
			${list.id}
		</c:if>
		</c:forEach>
		<hr>
		<c:forEach var="list" items="${list}">
		<c:if test="${list.t_name == match.m_challenger}"><!--  어웨이팀 기록 -->
			${list.id}
		</c:if>
		</c:forEach> 
		<c:if test="${match.m_type eq '축구'}">
			
		</c:if>
	</div>
	
</div>