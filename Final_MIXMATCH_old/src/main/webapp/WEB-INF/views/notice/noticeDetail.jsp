<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<div class="page-main-style">
	<h2>${notice.n_title}</h2>
	<ul>
		<li>번호 : ${notice.n_seq}</li>
		<li>조회수 : ${notice.n_hit}</li>
		<li>등록일 : ${notice.n_regdate}</li>
		<c:if test="${!empty notice.n_file_name}">
			<li>첨부파일 : <a href="noticefile.do?n_seq=${notice.n_seq}">${notice.n_file_name}</a></li>	
		</c:if>
	</ul>
	<hr size="1" width="100%"> 
	<c:if test="${fn:endsWith(notice.n_file_name, '.jpg') || fn:endsWith(notice.n_file_name, '.png') || fn:endsWith(notice.n_file_name, '.gif') ||
				fn:endsWith(notice.n_file_name, '.JPG') || fn:endsWith(notice.n_file_name, '.PNG') || fn:endsWith(notice.n_file_name, '.GIF')}">
		<%-- endsWith : 뒤에 있는 문자값 확인. --%>		
		<div class="align-center">
			<img src="noticeimageView.do?n_seq=${notice.n_seq}" style="max-width:500px">
		</div>
	</c:if>
	<p>
		${notice.n_content}
	</p>
	<hr size="1" width="100%">
	<div class="align-right">
		<c:if test="${!empty user_id && user_id==notice.id }">
			<input type="button" value="수정" onclick="location.href='noticeUpdate.do?n_seq=${notice.n_seq}'">
			<input type="button" value="삭제" onclick="location.href='notiecDelete.do?n_seq=${notice.n_seq}'">
		</c:if>
		<input type="button" value="목록" onclick="location.href='notice.do'">
	</div>
	
</div>