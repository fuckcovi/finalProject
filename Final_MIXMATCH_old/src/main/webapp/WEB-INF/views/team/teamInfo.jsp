<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/team.js"></script>
<div class="page-main-style">
	<h2>팀정보</h2>
	<hr class="style"><br>
	<table class="style">
		<tr>
			<th>팀명</th>
			<th>종목</th>
			<th>팀마스터</th>
			<th>등록일</th>
			<th>연고지</th>
			<th>팀원수</th>
		</tr>
		<tr>
			<td>${team.t_name}</td>
			<td>${team.t_type}</td>
			<td>${team.id}</td>
			<td>${team.t_regdate}</td>
			<td>${team.t_address}</td>
			<td>${count}</td>
		</tr>
	</table>
	 
	<h4>&lt;&lt;최근경기결과&gt;&gt;</h4>
	<c:if test="${matchCount>0}">
	<table class="style">
		<tr>
			<th>경기날짜</th>
			<th>장소</th>
			<th>매치업</th>
			<th>점수</th>
		</tr>
	<c:forEach var="match" items="${match}">
		<c:if test="${match.t_name == team.t_name && match.m_challenger!=null && match.m_home!=-1 && match.m_away!=-1}">
		<tr>
			<td>${match.m_date}</td>
			<td>${match.m_area}</td>
			<td><a href="teamInfo.do?t_name=${match.t_name}">${match.t_name}</a> vs <a href="teamInfo.do?t_name=${match.m_challenger}">${match.m_challenger}</a></td>
			<td>${match.m_home} : ${match.m_away}</td>
		</tr>
		</c:if>
		<c:if test="${match.m_challenger == team.t_name && match.m_challenger!=null && match.m_home!=-1 && match.m_away!=-1 }">
		<tr>
			<td>${match.m_date}</td>
			<td>${match.m_area}</td>
			<td><a href="teamInfo.do?t_name=${match.t_name}">${match.t_name}</a> vs <a href="teamInfo.do?t_name=${match.m_challenger}">${match.m_challenger}</a></td>
			<td>${match.m_home} : ${match.m_away}</td>
		</tr>
		</c:if>
	</c:forEach>
	</table>
	</c:if>
	<c:if test="${matchCount==0}">
		<div>최근경기결과 없음</div>
	</c:if>
	<br>
	<form:form commandName="teamMemCommand" action="teamMemJoin.do" id="teamMemJoin">
		<input type="hidden" value="${team.t_name}" id="t_name" name="t_name">
		<input type="hidden" value="${user_id}" id="id" name="id">
		<br><input type="submit" value="가입신청" class="btn">
		<c:if test="${tCheck == true}">
			<input type="button" value="가입철회" class="btn" onclick="location.href='cancelRegi.do?t_name=${team.t_name}'">
		</c:if>
	</form:form>
	<br><hr class="style">
	<c:if test="${team.id == user_id}">
		<input type="button" value="팀정보수정" class="btn" onclick="location.href='teamUpdate.do?t_name=${team.t_name}'" style="float:right;margin-right:42px;">
		<input type="button" value="팀삭제" class="btn" onclick="location.href='deleteTeam.do?t_name=${team.t_name}'" style="float:right;margin-right:18px;">
	</c:if>
</div>