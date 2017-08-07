<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/member/confirmId.js"></script>
<div class="page-main-style">
	<h1>회원가입</h1>
	<form:form commandName="memberCommand" id="write_form" enctype="multipart/form-data">
		<form:errors element="div" cssClass="error-color"/>
		<ul class="menu">
			<li>
				<label for="id">아이디</label>
				<form:input path="id"/>
				<input type="button" id="confirmId" value="아이디 중복체크">
				<span id="message_id"></span>
				<img src="${pageContext.request.contextPath }/resources/images/ajax-loader.gif" id="loading" width="16" height="16" style="display: none;">
				<form:errors path="id" cssClass="error-color"/>
			</li>
			<li>
				<label for="name">이름</label>
				<form:input path="name"/>
				<form:errors path="name" cssClass="error-color"/>
			</li>
			<li>
				<label for="pw">비밀번호</label>
				<form:password path="pw"/>
				<form:errors path="pw" cssClass="error-color"/>
			</li>
			<li>
				<label for="birth">생일</label>
				<input type="date" name="birth" id="birth">
				<form:errors path="birth" cssClass="error-color"/>
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
					<option value="없음" selected="selected">없음</option>
					<option value="축구">축구</option>
					<option value="야구">야구</option>
					<option value="농구">농구</option>
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
			<input type="submit" value="회원가입">
			<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main.do'">
		</div>
	</form:form>
</div>