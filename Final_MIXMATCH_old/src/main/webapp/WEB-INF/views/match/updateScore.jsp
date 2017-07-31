<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<div class="page-main-style">
	<h2>결과등록</h2>
	<hr class="style"><br>
	<form:form class="style" commandName="command" action="updateScore.do" enctype="multipart/form-data" id="result_form">
		<form:errors element="div" cssClass="error-color"/><br>
		<form:hidden path="m_seq"/>
		<ul>
			<li>
				<label for="m_home">홈팀 점수</label>
				<form:input path="m_home"/>
				<form:errors path="m_home" cssClass="error-color"/>
			</li>
			<li>
				<label for="m_away">어웨이팀 점수</label>
				<form:input path="m_away"/>
				<form:errors path="m_away" cssClass="error-color"/>
			</li>
			<li>
				<label for="m_mvp">MVP</label>
				<form:input path="m_mvp"/>
				<form:errors path="m_mvp" cssClass="error-color"/>
			</li>
		</ul>
	<input type="submit" value="결과등록" class="btn">
	<input type="button" value="목록으로" class="btn" onclick="location.href='scoreBoard.do'">
	</form:form>
	<br><hr class="style">
</div>