<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<div class="page-main-style">
	<h2>매치등록</h2>
	<hr class="style"><br>
	<form:form class="style" commandName="match" action="matchInsert.do" enctype="multipart/form-data" id="insert_form">
		<form:errors element="div" cssClass="error-color"/><br>
		<ul>
			<li>
				<label for="t_name">팀명</label>
				<form:select path="t_name" items="${teamList}" cssClass="select_box"/>
			</li>
			<li>
				<label for="m_area">지역</label>
				<form:select path="m_area" items="${area}" cssClass="select_box"/>
				<form:errors path="m_area" cssClass="error-color"/>
			</li>
			<li>
				<label for="m_date">날짜</label>
				<input type="date" name="m_date">
				<form:errors path="m_date" cssClass="error-color"/>
			</li>
			<li>
				<label for="m_time">시간</label>
				<form:input path="m_time"/>
				<form:errors path="m_time" cssClass="error-color"/>
			</li>
			<li>
				<label for="m_place">경기장</label>
				<form:input path="m_place"/>
				<form:errors path="m_place" cssClass="error-color"/>
			</li>
			<li>
				<label for="m_cost">비용</label>
				<form:input path="m_cost"/>원
				<form:errors path="m_cost" cssClass="error-color"/>
			</li>
			<li>
				<label for="m_content">내용</label>
				<form:textarea path="m_content"/>
				<form:errors path="m_content" cssClass="error-color"/>
			</li>
			<li>
				<label for="m_type">종목</label>
				<form:select path="m_type" items="${type}" cssClass="select_box"/>
				<form:errors path="m_type" cssClass="error-color"/>
			</li>
		</ul>
	<input type="submit" value="매치등록" class="btn">
	<input type="button" value="목록으로" class="btn" onclick="location.href='matchBoard.do'">
	</form:form>
	<br><hr class="style">
</div>