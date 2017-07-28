<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="page-main-style">
	<h1>회원 로그인</h1>
	<form:form commandName="memberCommand" id="login_form">
		<ul class="menu">
			<li>
				<label for="id">아이디</label>
				<form:input path="id"/>
				<form:errors path="id" cssClass="error-color"/>
			</li>
			<li>
				<label for="pw">비밀번호</label>
				<form:password path="pw"/>
				<form:errors path="pw" cssClass="error-color"/>
			</li>
			<li>
				<form:errors element="div" cssClass="error-color"/>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="로그인">
			<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main.do'">
		</div>
	</form:form>
</div>