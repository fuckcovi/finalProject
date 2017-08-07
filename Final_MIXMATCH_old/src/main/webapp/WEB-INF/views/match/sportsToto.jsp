<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="page-main-style">
	<h2>승부예측</h2>
	<hr class="style"><br>
	
	<form action="sportsToto.do" id="type_form" method="get">
	<input type="submit" value="축구" name="type" 
	<c:if test="${type eq '축구'}">class="select-btn" disabled</c:if>
	<c:if test="${type ne '축구'}">class="btn"</c:if>>
		
	<input type="submit" value="야구" name="type"  
	<c:if test="${type eq '야구'}">class="select-btn"</c:if>
	<c:if test="${type ne '야구'}">class="btn"</c:if>>
	
	<input type="submit" value="농구" name="type"  
	<c:if test="${type eq '농구'}">class="select-btn"</c:if>
	<c:if test="${type ne '농구'}">class="btn"</c:if>>
	</form><br>

	<table class="style">
		<tr>
			<th>경기날짜</th>
			<th></th>
			<th>매칭</th>
			<th></th>
			<th>배당률</th>
		</tr>
		
		<c:if test="${count == 0}">
			<tr><td colspan="5">매칭된 경기가 없습니다.</td></tr>
		</c:if>
		
		<c:if test="${count > 0}">
			<c:forEach var="match" items="${list}">
				<c:if test="${!empty match.m_challenger && match.m_home eq -1}">
					<tr> 
						<td>${match.m_date}</td>
						<td>${match.t_name}<br><input type="button" value="팀정보" class="btn-team-info" onclick="location.href='${pageContext.request.contextPath}/teamInfo.do?t_name=${match.t_name}'"></td>
						<td><img src="${pageContext.request.contextPath}/resources/images/versus.png" width="80"></td>
						<td>${match.m_challenger}<br><input type="button" value="팀정보" class="btn-team-info" onclick="location.href='${pageContext.request.contextPath}/teamInfo.do?t_name=${match.m_challenger}'"></td>
						<td><input type="button" value="배당률" class="btn" onClick="location.href='totoDetail.do?m_seq=${match.m_seq}'"></td>
					</tr>
				</c:if>
			</c:forEach>	
		</c:if> 
	</table>  
	<br><hr class="style">
</div>