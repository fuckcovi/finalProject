<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/rank.css">
<div class="page-main-style">
	<h3>통합포인트랭킹</h3>
	<div >
	<table class="ranking">
	<thead>
		<tr>
			<th>순위</th>
			<th id="profile">프로필사진</th>
			<th>회원이름</th>
			<th>포인트</th>
			<th id="regdate">가입일</th>
		</tr>
	</thead>
	<c:if test="${count>0 }"><!--  유저가 있으면 -->
	<tbody>
	<c:forEach var="list" items="${list}" >
		<tr>
			<td>${list.pointStatus}</td>
			<td>
				<c:if test="${empty list.profile_name }">
					<img src="${pageContext.request.contextPath}/resources/images/profile.jpg" style="width:60px;height:60px;">
				</c:if>
				<c:if test="${fn:endsWith(list.profile_name, '.jpg') || fn:endsWith(list.profile_name, '.png') || fn:endsWith(list.profile_name, '.gif') || fn:endsWith(list.profile_name, '.JPG') || fn:endsWith(list.profile_name, '.PNG') || fn:endsWith(list.profile_name, '.GIF')}">
					<img src="imageViewMem.do?id=${list.id}" style="width:60px;height:60px;">
				</c:if> 
			</td>
			<td><a href="mypage/main.do?id=${list.id}">${list.name}</a></td>
			<td>${list.point} 점</td>
			<td>${list.regdate} </td>
		</tr>
	</c:forEach>
	</tbody>
	</table>
	<br>
		<div class="align-center">${pagingHtml}</div>
	</c:if>
	<c:if test="${count==0 }">
	<tbody>
		<tr>
			<td colspan="4">사이트에 등록된 유저가 없습니다.</td>
		</tr>
	</tbody>
	</table>
	</c:if>
	</div>
</div>