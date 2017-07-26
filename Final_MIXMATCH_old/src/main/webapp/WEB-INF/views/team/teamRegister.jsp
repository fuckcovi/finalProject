<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="page-main-style">
	<form:form commandName="teamCommand" action="teamRegister.do">
	<form:errors element="div" cssClass="error-color"/>
	<input type="hidden" id="id" name="id" value="${user_id}">
		<label for="t_name">팀명</label>
		<form:input path="t_name"/>
		<form:errors path="t_name" cssClass="error-color"/>
		<label for="t_type">팀종목</label>
		<form:select path="t_type">
			<form:option value="야구">야구</form:option>
			<form:option value="축구">축구</form:option>
			<form:option value="농구">농구</form:option>
		</form:select>
		<form:errors path="t_type" cssClass="error-color"/>
		<label for="t_address">팀연고지</label>
		<form:input path="t_address"/>
		<form:errors path="t_address" cssClass="error-color"/>
		
		<input type="submit" value="팀생성">
	</form:form>
</div>