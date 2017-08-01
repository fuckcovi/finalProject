<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/match.js"></script>  
<div class="page-main-style">
	<h2>상세보기</h2>
	<hr class="style"><br>
	<div class="detail-style"><br>
		<table class="versus"> 
			<tr>
				<td><img src="${pageContext.request.contextPath}/resources/images/home_img.png" width="80"></td>
				<td>${match.t_name}<br><input type="button" value="팀정보" class="btn-team-info" onclick="location.href='${pageContext.request.contextPath}/teamInfo.do?t_name=${match.t_name}'"></td>
				<td><img src="${pageContext.request.contextPath}/resources/images/versus.png" width="80"></td>
				<td>없음</td>
				<td><img src="${pageContext.request.contextPath}/resources/images/away_img.png" width="80"></td>
			</tr>
		</table>  
		<table class="list"> 
			<tr>
				<td class="left-style">지역</td>
				<td align="left"> ${match.m_area}</td>
			</tr>
			<tr>
				<td class="left-style">날짜</td>
				<td align="left"> ${match.m_date}</td>
			</tr>
			<tr>	
				<td class="left-style">시간</td>
				<td align="left"> ${match.m_time}</td>
			</tr>
			<tr>
				<td class="left-style">경기장</td>
				<td align="left"> ${match.m_place}</td>
			</tr>
			<tr>
				<td class="left-style">비용</td>
				<td align="left"> ${match.m_cost}</td>
			</tr>
			<tr height="80">
				<td class="left-style">내용</td>
				<td align="left"> ${match.m_content}</td>
			</tr>
		</table>
		<c:if test="${!fn:contains(t_name,match.t_name)}">
			<form:form commandName="match" action="challengerUpdate.do" enctype="multipart/form-data" id="challenger_form">
				<form:errors element="div" cssClass="error-color"/>
				<form:hidden path="m_seq"/>
				<form:hidden path="m_area"/>
				<form:hidden path="m_date"/>
				<form:hidden path="m_time"/>
				<form:hidden path="m_place"/>
				<form:hidden path="m_cost"/>
				<form:hidden path="m_content"/>
				<form:select path="t_name" items="${t_name}"/><br>
				<input type="submit" value="매치신청" id="match_btn" class="btn">
				<input type="button" value="목록으로" class="btn" onclick="location.href='matchBoard.do'">
			</form:form>
		</c:if>	
	</div>
	<br><hr class="style">
	<c:if test="${fn:contains(t_name,match.t_name)}">  
		<input type="button" value="삭제" class="btn" style="float:right;margin-right:42px;" onClick="location.href='matchDelete.do?m_seq=${match.m_seq}'">
		<input type="button" value="수정" class="btn" style="float:right;margin-right:18px;" onClick="location.href='matchUpdate.do?m_seq=${match.m_seq}'">
	</c:if>
</div>