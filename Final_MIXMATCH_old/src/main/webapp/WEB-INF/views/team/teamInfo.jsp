<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/team.js"></script>
<div class="page-main-style">
	<h3>팀정보</h3>
	<table>
		<tr>
			<th>팀명</th>
			<th>종목</th>
			<th>팀마스터</th>
			<th>등록일</th>
			<th>연고지</th>
		</tr>
		<tr>
			<td>${team.t_name}</td>
			<td>${team.t_type}</td>
			<td>${team.id}</td>
			<td>${team.t_regdate}</td>
			<td>${team.t_address}</td>
		</tr>
	</table>
	
	<hr size="2">
	<h5>최근경기결과</h5>
	<table>
		<tr>
			<th>경기날짜</th>
			<th>장소</th>
			<th>상대팀</th>
			<th>점수</th>
		</tr>
	<c:forEach var="match" items="${match}">
		<c:if test="${match.t_name == team.t_name}">
		<tr>
			<td>${match.m_date}</td>
			<td>${match.m_area}</td>
			<td>${match.t_name} vs ${match.m_challenger}</td>
			<td>${match.m_home} : ${match.m_away}</td>
		</tr>
		</c:if>
	</c:forEach>
	</table>
		
	<hr>
	<form:form commandName="teamMemCommand" action="teamMemJoin.do" id="teamMemJoin">
		<input type="hidden" value="${team.t_name}" id="t_name" name="t_name">
		<input type="hidden" value="${user_id}" id="id" name="id">
		<c:if test="${user_team != team.t_name}">
			<input type="submit" value="팀에 가입하고 싶어요">
		</c:if>
	</form:form>
</div>