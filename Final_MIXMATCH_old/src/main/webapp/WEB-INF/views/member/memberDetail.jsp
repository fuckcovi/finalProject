<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="page-main-style">
	<h2>회원 상세 정보</h2>
	<ul class="menu" style="width: 200px;">
		<li>아이디 : ${member.id }</li>
		<li>이름 : ${member.name }</li>
		<li>생일 : ${member.birth }</li>
		<li>전화번호 : ${member.phone }</li>
		<li>이메일 : ${member.email }</li>
		<li>주소 : ${member.address }</li>
		<c:choose>
		<c:when test="${member.favor eq soccer}">
		<li> 관심종목 : 축구</li>
	    </c:when>
	    <c:when test="${member.favor eq basketball}">
	       <li> 관심종목 : 농구</li>
	    </c:when>
	    <c:when test="${member.favor eq baseball}">
		<li> 관심종목 : 야구</li>
	    </c:when>
	    <c:otherwise>
	   	<li> 관심종목 : 없음</li>
	    </c:otherwise>
		</c:choose>
		<li>현재 포인트 : ${member.point }</li>
		<li>회원등급 : ${member.auth }</li>
		<li>가입일 : ${member.regdate }</li>
	</ul>
	<hr size="1" width="100%"/>
	<p class="align-center">
		<input type="button" value="비밀번호 수정" onclick="location.href='pwUpdate.do'">
		<input type="button" value="회원정보 수정" onclick="location.href='pwCheck.do'">
		<input type="button" value="회원 탈퇴" onclick="location.href='delete.do'">
	</p>
</div>