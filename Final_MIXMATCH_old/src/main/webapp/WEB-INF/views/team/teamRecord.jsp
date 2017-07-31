<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/team.js"></script>
<div class="page-main-style">
	<h3>팀기록</h3>
	<c:if test="${count==0 }">
		<div>
			소속 팀도 없는데 기록이 있을리가 
		</div>
	</c:if>
	<c:if test="${count>0 }">  
	<ul style="list-style: none">
		<c:forEach var="list" items="${list}">
			<li style="float: left; width:150px;">
				<a href="#">${list.t_name}</a><!-- 클릭하면 ajax로 화면 바꿔서 기록 보이기 -->
			</li>
		</c:forEach>
	</ul>
	</c:if>
</div>