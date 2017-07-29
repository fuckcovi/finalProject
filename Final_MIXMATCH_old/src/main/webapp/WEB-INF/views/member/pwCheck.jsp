<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="page-main-style">
	<h1>비밀번호 확인</h1>
	<form:form commandName="memberCommand" id="pwCheck_form">
		<form:errors element="div" cssClass="error-color"/>
		<ul class="menu">
			<li>
				<label for="pw">비밀번호</label>
				<form:password path="pw"/>
				<form:errors path="pw" cssClass="error-color"/>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="회원수정">
			<input type="button" value="내정보 보기" onclick="location.href='detail.do'">
		</div>
	</form:form>
</div>