<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/notice.js"></script>
<style type="text/css">
	#search_form{width:500px;height:50px;margin:0 auto;}
	.search{list-style:none;}
	.search li{float:left; margin:10px 10px 10px 10px;}
</style>
<div class="page-main-style">
	<h2>팀게시판</h2>
	<div class="align-center">
		<input type="button" value="글쓰기"  onclick="location.href='teamboardInsert.do'" >
	</div>
	<div class="align-right">
		<input type="button" value="글쓰기"  onclick="location.href='teamboardInsert.do'" >
	</div>
	<c:if test="${count==0 }">
		<div class="align-center">등록된 팀게시글이 없습니다.</div>
	</c:if>
	<c:if test="${count>0 }">
		<table>
			<tr>
				<th>번호</th>
				<th width="400">제목</th>
				<th>등록일</th>
				<th>작성자</th>
				<th>조회수</th>
			</tr>
			<c:forEach var="article" items="${list}">
			<tr>
				<td>${article.tb_seq }</td>
				<td><a href="noticeDetail.do?n_seq=${article.tb_seq}">${article.tb_title}</a></td>
				<td>${article.tb_regdate}</td>
				<td>${article.id}</td>
				<td>${article.tb_hit}</td>
			</tr>
			</c:forEach>
		</table>
		<div class="align-center">${pagingHtml}</div>
	</c:if>
	<form action="teamboard.do" id="search_form" method="get" >
		<ul class="search">
			<li>
				<select name="keyfield">
					<option value="tb_title">제목</option>
					<option value="tb_content">내용</option>
					<option value="id">작성자</option>
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
</div>