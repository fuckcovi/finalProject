<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="page-main-style">
	<h1>회원수정</h1>
	<form:form commandName="memberCommand" id="update_form" enctype="multipart/form-data">
		<form:errors element="div" cssClass="error-color"/>
		<form:hidden path="id"/>
		<ul class="menu">
			<li>
				<label for="name">이름</label>
				<form:input path="name"/>
				<form:errors path="name" cssClass="error-color"/>
			</li>
			<li>
				<label for="phone">전화번호</label>
				<form:input path="phone"/>
				<form:errors path="phone" cssClass="error-color"/>
			</li>
			<li>
				<label for="email">이메일</label>
				<form:input path="email"/>
				<form:errors path="email" cssClass="error-color"/>
			</li>
			<li>
				<label for="favor">관심종목</label>
				<select name="favor">
					<option value="none" selected="selected">없음</option>
					<option value="soccer">축구</option>
					<option value="baseball">야구</option>
					<option value="basketball">농구</option>
				</select>
			</li>
			<li>
				<label for="address">주소</label>
				<form:input path="address"/>
				<form:errors path="address" cssClass="error-color"/>
			</li>
			<li>
				<label for="profile_upload">프로필 사진</label>
				<input type="file" name="profile_upload" id="profile_upload">
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="회원수정">
			<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main.do'">
		</div>
	</form:form>
</div>