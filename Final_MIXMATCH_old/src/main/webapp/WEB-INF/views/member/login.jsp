<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
	<form:form commandName="memberCommand" action="login.do">
	<form:errors element="div" cssClass="error-color"/>
		<form:input path="id"/>
		<form:errors path="id" cssClass="error-color"/>
		<form:input path="pw"/>
		<form:errors path="id" cssClass="error-color"/>
		<input type="submit" value="로그인">
	</form:form>
	<c:if test="${!empty user_id}">
		<h2>로그인됨!!!</h2>
		<h1>${user_id}</h1>
	</c:if>
	<a href="logout.do">로그아웃</a>