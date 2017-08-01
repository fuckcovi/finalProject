<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/confirmTname.js"></script>
<div class="page-main-style">
	<h2>팀생성</h2>
	<hr class="style"><br>
	<form:form id="teamRegister" class="style" commandName="teamCommand" action="teamRegister.do" enctype="multipart/form-data">
	<form:errors element="div" cssClass="error-color"/>
	<input type="hidden" id="id" name="id" value="${user_id}"><br><br>
	<ul>
		<li>
			<label for="t_name">팀명</label>
			<form:input path="t_name"/>
			<input type="button" id="confirmTname" class="btn" value="중복체크">
			<span id="message_tname"></span>
			<img src="${pageContext.request.contextPath}/resources/images/ajax-loader.gif" width="16" height="16" style="display:none">
			<form:errors path="t_name" cssClass="error-color"/>
		</li>
		<li>
			<label for="t_type">팀종목</label>
			<form:select path="t_type"> 
				<form:option value="야구">야구</form:option>
				<form:option value="축구">축구</form:option>
				<form:option value="농구">농구</form:option>
			</form:select>
			<form:errors path="t_type" cssClass="error-color"/>
		</li>
		<li>
			<label for="t_address">팀연고지</label>
			<form:select path="t_address">
				<form:option value="서울">서울</form:option>
				<form:option value="경기도">경기도</form:option>
				<form:option value="강원도">강원도</form:option>
				<form:option value="전라도">전라도</form:option>
				<form:option value="경상도">경상도</form:option>
				<form:option value="충청도">충청도</form:option>
				<form:option value="제주도">제주도</form:option>
				<form:option value="해외">해외</form:option>
			</form:select>
			<form:errors path="t_address" cssClass="error-color"/>
		</li>
		<li>
			<label for="t_logo_upload">팀로고</label>
			<input type="file" name="t_logo_upload" id="t_logo_upload">
		</li>
	</ul>
	<div class="align-center">
		<input type="submit" value="팀생성" class="btn">
	</div>
	</form:form>
	<br><hr class="style">
</div>