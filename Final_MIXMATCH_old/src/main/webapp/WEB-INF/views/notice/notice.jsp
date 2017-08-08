<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/notice.js"></script>
<div class="page-main-style">
	<h2>공지사항</h2>
	<form action="notice.do" id="search_form" method="get">
		<ul class="search">
			<li>
				<select name="keyfield">
					<option value="n_title">제목</option>
					<option value="id">아이디</option>
					<option value="n_content">내용</option>
					<option value="all">전체</option>
				</select>
			</li> 
			<li>
				<input type="text" name="keyword" id="keyword">
			</li>
			<li>
				<input type="submit" value="찾기">
			</li>
		</ul>
	</form>
	<div class="align-right">
		<input type="button" value="글쓰기"  onclick="location.href='noticeInsert.do'" <c:if test="${user_id != 'admin'}">disabled="disabled"</c:if>>
	</div>
	<c:if test="${count==0 }">
		<div class="align-center">등록된 게시물이 없습니다.</div>
	</c:if>
	<c:if test="${count>0 }">
		<table>
			<tr>
				<th>번호</th>
				<th width="400">제목</th>
				<th>등록일</th>
				<th>조회수</th>
			</tr>
			<c:forEach var="article" items="${list}">
			<tr>
				<td>${article.n_seq }</td>
				<td><a href="noticeDetail.do?seq=${article.n_seq}">${article.n_title}(조회수)</a></td>
				<td>${article.n_regdate}</td>
				<td>${article.n_hit}</td>
			</tr>
			</c:forEach>
		</table>
		<div class="align-center">${pagingHtml}</div>
	</c:if>
</div>