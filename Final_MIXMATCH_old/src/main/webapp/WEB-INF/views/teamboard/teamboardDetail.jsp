<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<div class="page-main-style">
	<h2>${teamboard.gt_title}</h2>
	<ul>
		<li>번호 : ${teamboard.gt_seq}</li>
		<li>조회수 : ${teamboard.gt_hit}</li>
		<li>등록일 : ${teamboard.gt_regdate}</li>
		<c:if test="${!empty teamboard.gt_filename}">
			<li>첨부파일 : <a href="teamboardfile.do?gt_seq=${teamboard.gt_seq}">${teamboard.gt_filename}</a></li>	
		</c:if>
	</ul>
	<hr size="1" width="100%"> 
	<c:if test="${fn:endsWith(teamboard.gt_filename, '.jpg') || fn:endsWith(teamboard.gt_filename, '.png') || fn:endsWith(teamboard.gt_filename, '.gif') ||
				fn:endsWith(teamboard.gt_filename, '.JPG') || fn:endsWith(teamboard.gt_filename, '.PNG') || fn:endsWith(teamboard.gt_filename, '.GIF')}">
		<%-- endsWith : 뒤에 있는 문자값 확인. --%>		
		<div class="align-center">
			<img src="teamboardimageView.do?gt_seq=${teamboard.gt_seq}" style="max-width:500px">
		</div>
	</c:if>
	<p>
		${teamboard.gt_content}
	</p>
	<hr size="1" width="100%">
	<div class="align-right">
		<c:if test="${!empty user_id && user_id==teamboard.id }">
			<input type="button" value="수정" onclick="location.href='teamboardUpdate.do?gt_seq=${teamboard.gt_seq}'">
			<input type="button" value="삭제" onclick="location.href='teamboardDelete.do?gt_seq=${teamboard.gt_seq}'">
		</c:if>
		<input type="button" value="목록" onclick="location.href='teamboard.do'">
	</div>
	
</div>