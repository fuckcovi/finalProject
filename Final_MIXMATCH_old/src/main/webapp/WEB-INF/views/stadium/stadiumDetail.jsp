<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery-3.2.1.min.js"></script>
<div class="page-main-style">
	<br>
	<h2>경기장 상세보기</h2>
	<table>
	<tr>
		<th>경기장사진</th>
		<th>경기장이름</th>
		<th>종목</th>
		<th>경기장지역</th>
		<th>경기장등록일</th>
	</tr>
	
		<tr>
			<td>
				<c:if test="${fn:endsWith(stadium.s_logo_name, '.jpg') || 
							fn:endsWith(stadium.s_logo_name, '.png') || 
							fn:endsWith(stadium.s_logo_name, '.gif') || 
							fn:endsWith(stadium.s_logo_name, '.JPG') || 
							fn:endsWith(stadium.s_logo_name, '.PNG') || 
							fn:endsWith(stadium.s_logo_name, '.GIF')}">
					<img src="imageViewStadium.do?s_seq=${list.s_seq}" style="width:200px;height:200px;">
				</c:if>
				<c:if test="${empty stadium.s_logo_name }">
				<c:if test="${stadium.s_type eq '야구' }">
					<img src="${pageContext.request.contextPath}/resources/images/baseball.png" style="width:200px;height:200px;">
				</c:if>
				<c:if test="${stadium.s_type eq '농구' }">
					<img src="${pageContext.request.contextPath}/resources/images/basketball.png" style="width:200px;height:200px;">
				</c:if>
				<c:if test="${stadium.s_type eq '축구' }">
					<img src="${pageContext.request.contextPath}/resources/images/football.png" style="width:200px;height:200px;">
				</c:if>
				</c:if>
			</td>
			<td><a href="${pageContext.request.contextPath }/stadiumDetail.do?s_seq=${stadium.s_seq}">${stadium.s_name}</a></td>
			<td>${stadium.s_type }</td>
			<td>${stadium.s_address1}</td>
			<td>${stadium.s_regdate}</td>
		</tr>
		
	</table>
		<div class="align-center">${pagingHtml}</div>
	
</div>