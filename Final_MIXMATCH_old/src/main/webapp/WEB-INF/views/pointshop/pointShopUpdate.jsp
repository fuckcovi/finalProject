<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/pointshop.css">
<div class="page-main-style">
<h2>상품수정</h2>
<form:form commandName="pointShopCommand" enctype="multipart/form-data">
		<form:errors element="div" cssClass="error-color"/>		
		<form:hidden path="id"/>
		<ul class="menu">
			<li>				
				<label for="p_name">상품명</label>
				<form:input path="p_name" value="${product.p_name}"/>
				<form:errors path="p_name" cssClass="error-color"/>
			</li>
			<li>
				<label for="p_price">가격</label>
				<input type="number" name="p_price" min="1" value="${product.p_price}">
				<form:errors path="p_price" cssClass="error-color"/>
			</li>
			<li>
				<label for="p_context">상품설명</label>
				<input type="text" name="p_context" min="1" value="${product.p_context}">
				<form:errors path="p_context" cssClass="error-color"/>
			</li>
			<li>
				${product.p_grade}
				<label for="p_grade">등급</label>
				<select name="p_grade">
					<option value="BRONZE">브론즈</option>
					<option value="SILVER">실버</option>
					<option value="GOLD">골드</option>
					<option value="PLATINUM">플래티넘</option>
					<option value="DIAMOND">다이아몬드</option>
				</select>
				<form:errors path="p_grade" cssClass="error-color"/>
			</li>
			<li>
				<label for="p_file">파일업로드</label>
				<input type="file" name="p_file" id="p_file">
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="수정">
			<input type="button" value="홈으로" onclick="location.href='pointHome.do'">
		</div>
	</form:form>
</div>