<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery-3.2.1.min.js"></script>
<div class="page-main-style">
	<h2>경기장 예약</h2>
	<ul>
	<form:form commandName="stadiumCommand">
		<li>
		<label for="s_name">경기장 이름</label>
		<form:input path="s_name"/>
		</li>
		<li>
		<label for="s_address1">경기장 지역</label>
		<form:input path="s_address1"/>
		</li>
		<li>
		<label for="s_address2">경기장 상세주소</label>
		<form:input path="s_address2"/>
		</li>
		<li>
		<label for="s_logo_upload">경기장 로고</label>
		<input type="file" id="s_logo_upload" name="s_logo_upload">
		</li>
		<input type="submit" value="경기장등록">
	</form:form>
	</ul>
</div>