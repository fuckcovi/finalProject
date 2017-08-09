<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="page-main-style">
	<h3>통합포인트랭킹</h3>
	<div >
	<table style="width:90%;margin-right: 10%">
		<tr>
			<th style="width:10%;">순위</th>
			<th style="width:30%;">프로필사진</th>
			<th style="width:20%;">회원이름</th>
			<th style="width:20%;">포인트</th>
			<th style="width:30%;">가입일</th>
		</tr>
	<c:if test="${count>0 }"><!--  유저가 있으면 -->
	<c:forEach var="list" items="${list}" >
		<tr>
			<td>${list.pointStatus}</td>
			<td>
				<c:if test="${fn:endsWith(list.profile_name, '.jpg') || fn:endsWith(list.profile_name, '.png') || fn:endsWith(list.profile_name, '.gif') || fn:endsWith(list.profile_name, '.JPG') || fn:endsWith(list.profile_name, '.PNG') || fn:endsWith(list.profile_name, '.GIF')}">
					<img src="imageViewMem.do?id=${list.id}" style="width:100px;height:100px;">
				</c:if> 
			</td>
			<td><a href="mypage/main.do?id=${list.id}">${list.name}</a></td>
			<td>${list.point} 점</td>
			<td>${list.regdate} </td>
		</tr>
	</c:forEach>
	</table>
		<div class="align-center">${pagingHtml}</div>
	</c:if>
	<c:if test="${count==0 }">
		<tr>
			<td colspan="4">사이트에 등록된 유저가 없습니다.</td>
		</tr>
	</table>
	</c:if>
	</div>
</div>