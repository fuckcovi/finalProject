<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page-main-style">
	<h1>MIXMATCH</h1>
	<c:if test="${empty user_id}">
		<a href="${pageContext.request.contextPath }/login.do">로그인</a><br><br>
	</c:if>
	<img src="${pageContext.request.contextPath }/resources/images/cat.jpg" width="300" height="372">
</div>