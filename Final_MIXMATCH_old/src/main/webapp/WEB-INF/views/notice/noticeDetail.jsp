<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/notice.js"></script>
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
	<div id="reply_div">
		<span class="reply-title">댓글달기</span>
		<form id="re_form">
			<input type="hidden" name="gnre_seq" value="${notice.gn_seq}" id="gnre_seq">
			<input type="hidden" name="id" value="${user_id}" id="user_id">
			<textarea rows="3" cols="50" name="gnre_content" id="gnre_content" class="rep-content" 
					<c:if test="${empty user_id}">disabled="disabled"</c:if>
			><c:if test="${empty user_id}">로그인해야 작성할 수 있습니다.</c:if></textarea>
			
			<c:if test="${!empty user_id}">
				<div id="re_first">
					<span class="letter-count">300/300</span>
				</div>
				<div id="re_second" class="align-right">
					<input type="submit" value="전송">
				</div>
			</c:if>
			
		</form>
	</div>
	<!-- - 목록 출력 = -->
	<div id="output"></div>
	<div class="paging-button" style="display: none">
		<input type="button" value="다음글 보기">
	</div>
	<div id="loading" style="display: none;">
		<img src="${pageContext.request.contextPath}/resources/images/ajax-loader.gif">
	</div>
</div>