<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/teamboard.reply.js"></script>
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
	<div id="reply_div">
		<span class="reply-title">댓글 달기</span>
		<form id="re_form">
			<input type="hidden" name="gt_seq"
			                 value="${teamboard.gt_seq}" id="gt_seq">
			<input type="hidden" name="id"
			                 value="${user_id}" id="user_id">
			<textarea rows="3" cols="50"
			   name="gtre_content" id="gtre_content"
			   class="rep-content"
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
	<!-- 목록 출력 -->
	<div id="output"></div>
	<div class="paging-button" style="display:none;">
		<input type="button" value="다음글 보기">
	</div>
	<div id="loading" style="display:none;">
		<img src="${pageContext.request.contextPath}/resources/images/ajax-loader.gif">
	</div>
	
</div>