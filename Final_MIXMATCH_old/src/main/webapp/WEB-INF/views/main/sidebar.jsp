<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var chat;
		var open = 0;
		$("#chat").click(function(){
			if(open==1){
				chat.close();
				open=0;
			}
			onPopupWindow();
		});
		function onPopupWindow(){  
		    chat = window.open("${pageContext.request.contextPath}/chat.do", "_blank", "top=300, left=500, width=400, height=400");
		    open=1;
		}
	});
</script>
<div class="align-center" id="sideMenu">
	<ul>
		<li>
			<c:if test="${!empty user_id }">
			<a href="${pageContext.request.contextPath}/mypage/main.do?id=${user_id}" >
				<c:if test="${fn:endsWith(member.profile_name, '.jpg') || fn:endsWith(member.profile_name, '.png') || fn:endsWith(member.profile_name, '.gif') || fn:endsWith(member.profile_name, '.JPG') || fn:endsWith(member.profile_name, '.PNG') || fn:endsWith(member.profile_name, '.GIF')}">
					<img src="${pageContext.request.contextPath}/imageViewSide.do?id=${member.id}" style="width:100px;height:100px;">
				</c:if> 
				<c:if test="${empty member.profile_name }">
					<img src="${pageContext.request.contextPath}/resources/images/profile.jpg" style="width:100px;height:100px;">
				</c:if>
				
			</a>
			</c:if>
			<c:if test="${empty user_id}">
			<div style="height:100px;background:red;">
			<form action="${pageContext.request.contextPath}/login.do" method="post">
				<input type="text" id="id" name="id" >
				<input type="password" id="pw" name="pw">
				<input type="submit" value="로그인">
			</form>
			</div>
			</c:if>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/team.do">
			<div style="height:100px;background:yellow;">팀메뉴</div>
			</a>
		</li>
		<li>
			<a href="#">
			<div style="height:100px;background:orange;">포인트샵</div>
			</a>
		</li>
		<li>
			<c:if test="${!empty user_id }">
			<div id="chat" style="height:100px;background:green;"> 채팅			
			</div>
			</c:if>
			<c:if test="${empty user_id}">
			<div style="height:100px;background:green;">로그인하세요</div>
			</c:if>
		</li>
	</ul>
</div>