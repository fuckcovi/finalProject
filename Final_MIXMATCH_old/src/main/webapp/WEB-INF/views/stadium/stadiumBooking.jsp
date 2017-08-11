<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery-3.2.1.min.js"></script>

<div class="page-main-style">
	<br>
	<h2>경기장 예약확인</h2>
	${booking.s_seq} : ${booking.b_time} : ${booking.b_regdate} : ${booking.b_check}
</div>