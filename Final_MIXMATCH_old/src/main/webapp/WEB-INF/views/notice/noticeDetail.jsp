<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<div class="page-main-style">
	<h2>${notice.gn_title}</h2>
	<ul>
		<li>번호 : ${notice.gn_seq}</li>
		<li>조회수 : ${notice.gn_hit}</li>
		<li>등록일 : ${notice.gn_regdate}</li>
		<c:if test="${!empty notice.gn_filename}">
			<li>첨부파일 : <a href="noticefile.do?gn_seq=${notice.gn_seq}">${notice.gn_filename}</a></li>	
		</c:if>
	</ul>
	<hr size="1" width="100%"> 
	<c:if test="${fn:endsWith(notice.gn_filename, '.jpg') || fn:endsWith(notice.gn_filename, '.png') || fn:endsWith(notice.gn_filename, '.gif') ||
				fn:endsWith(notice.gn_filename, '.JPG') || fn:endsWith(notice.gn_filename, '.PNG') || fn:endsWith(notice.gn_filename, '.GIF')}">
		<%-- endsWith : 뒤에 있는 문자값 확인. --%>		
		<div class="align-center">
			<img src="noticeimageView.do?n_seq=${notice.gn_seq}" style="max-width:500px">
		</div>
	</c:if>
	<p>
		${notice.gn_content}
	</p>
	<hr size="1" width="100%">
	<div class="align-right">
		<c:if test="${!empty user_id && user_id==notice.id }">
			<input type="button" value="수정" onclick="location.href='noticeUpdate.do?gn_seq=${notice.gn_seq}'">
			<input type="button" value="삭제" onclick="location.href='notiecDelete.do?gn_seq=${notice.gn_seq}'">
		</c:if>
		<input type="button" value="목록" onclick="location.href='notice.do'">
	</div>
	
</div>