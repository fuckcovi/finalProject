<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery-3.2.1.min.js"></script>
<div class="page-main-style">
	<h2>경기장 예약</h2>
	<ul style="list-style: none;">
	<form:form commandName="stadiumCommand" enctype="multipart/form-data" id="stadiumRegister" action="stadiumRegi.do" class="style" >
		<br>
		<br>
		<li>
		<label for="s_name">경기장 이름</label>
		<form:input path="s_name"/>
		</li>
		<li>
		<label for="s_type">경기장종목</label>
		<form:select path="s_type">
			<form:option value="야구"/>
			<form:option value="농구"/>
			<form:option value="축구"/>
		</form:select>
		</li>
		<li>
		<label for="s_address1">경기장 지역</label>
		<form:select path="s_address1">
			<form:option value="서울"/>
			<form:option value="경기"/>
			<form:option value="인천"/>
			<form:option value="강원"/>
			<form:option value="대전"/>
			<form:option value="충북"/>
			<form:option value="충남"/>
			<form:option value="광주"/>
			<form:option value="전북"/>
			<form:option value="전남"/>
			<form:option value="대구"/>
			<form:option value="울산"/>
			<form:option value="경북"/>
			<form:option value="경남"/>
			<form:option value="부산"/>
			<form:option value="제주"/>
		</form:select>
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