<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page-main-style">
	<h3>팀홈</h3>
	<c:if test="${empty user_team}">
		<a href="teamRegister.do">팀생성</a>
		<a href="teamMembership.do">팀가입</a>
	</c:if>
	<c:if test="${!empty user_team}">
		<h1>소속팀 : ${user_team}</h1>
		<a href="teamRank.do">팀랭킹</a>
	</c:if>
</div>