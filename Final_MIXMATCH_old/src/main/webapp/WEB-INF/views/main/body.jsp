<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<style type="text/css">
	div.half{
		width:48%;
		height:200px;
		border:1px solid red;
		display: inline-block;
	}
	div.full{
		width:100%;
		height:200px;
		border:1px solid blue;
	}
</style>
<div class="page-main-style">
	<h1>MIXMATCH</h1>
	<img src="${pageContext.request.contextPath }/resources/images/main.png">
	
	<!--  공지사항 게시글 최근5개 보이기. 없으면 없음 띄움 -->
	<div id="noticeView" class="half">
		<h4>공지사항</h4>
	</div>
	<!--  자유게시판 인기글 5개 보이기 -->
	<div id="freeboardView" class="half">
		<h4>자유게시판</h4>
		
	</div>
	<div id="matchView" class="full">
		<h4>최근경기결과</h4>
	</div>
	<div id="rankView" class="full">
		<h4>팀랭킹 - 종목별구분?</h4>
	</div>
</div>