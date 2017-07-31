<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="page-main-style">
	<h3>통합포인트랭킹</h3>
	유저들의 포인트 랭킹
	<table>
		<tr>
			<th>순위</th>
			<th>프로필사진</th>
			<th>회원이름</th>
			<th>포인트</th>
		</tr>
	<c:forEach var="list" items="${list}" varStatus="status" >
		<tr>
			<td>${status.count}</td>
			<td>
				<c:if test="${fn:endsWith(list.profile_name, '.jpg') || fn:endsWith(list.profile_name, '.png') || fn:endsWith(list.profile_name, '.gif') || fn:endsWith(list.profile_name, '.JPG') || fn:endsWith(list.profile_name, '.PNG') || fn:endsWith(list.profile_name, '.GIF')}">
					<img src="imageViewMem.do?id=${list.id}" style="width:100px;height:100px;">
				</c:if> 
			</td>
			<td>${list.name}</td>
			<td>${list.point} 점</td>
		</tr>
	</c:forEach>
	</table>
</div>