<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/team.js"></script>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/teamList.js"></script> --%>
<div class="page-main-style">
	<h2>팀홈</h2>
	<hr class="style"><br> 
	<div>
		<c:if test="${joinCount==0 }">
			가입신청한 팀이 없습니다. 자신의 팀을 생성하거나 이미 등록된 팀에 가입신청하세요.
		</c:if>
		<c:if test="${joinCount>0 }">
		<h3>&lt;&lt;소속팀&gt;&gt;</h3>
			<c:forEach var="joinList" items="${joinList}">
				<div>
					<c:if test="${joinList.t_mem_auth > 0}">
						<a href="teamInfo.do?t_name=${joinList.t_name}">${joinList.t_name}</a> | 
						<a href="teamRank.do?t_name=${joinList.t_name}">우리팀랭킹</a> | 
						<a href="teamMem.do?t_name=${joinList.t_name}">팀원관리</a>
					</c:if>
				</div>
			</c:forEach>
			<hr size="1" width="85%">
			<h4>가입신청 중인 팀 : </h4> 
			<c:forEach var="joinList" items="${joinList}">
				<c:if test="${joinList.t_mem_auth ==0}"> 
					<div><a href="teamInfo.do?t_name=${joinList.t_name}">${joinList.t_name}</a></div>
				</c:if>				
			</c:forEach>
		</c:if>
		<input type="button" value="팀생성" class="btn" onclick="location.href='teamRegister.do'">
	</div>
	
	
	
	<br><hr class="style">
	
	<h3 style="color:red;">MixMatch등록팀현황</h3> 
	<input type="button" value="모든종목" class="btn" id="typeAll">
	<input type="button" value="축구" class="btn" id="typeFoot">
	<input type="button" value="야구" class="btn" id="typeBase">
	<input type="button" value="농구" class="btn" id="typeBasket">
	
	
	
	<div style="background-color: yellow; overflow-y: auto; max-height: 350px;">
	
	
	
	
	
		<br>
		<c:if test="${count>0}">
			<c:forEach var="list" items="${list}">
				<div style="width:200px;height:250px;margin:10px 10px 10px 10px; float:left; background-color: red;" id="${list.t_type}">
					<c:if test="${fn:endsWith(list.t_logo_name, '.jpg') || 
								fn:endsWith(list.t_logo_name, '.png') || 
								fn:endsWith(list.t_logo_name, '.gif') || 
								fn:endsWith(list.t_logo_name, '.JPG') || 
								fn:endsWith(list.t_logo_name, '.PNG') || 
								fn:endsWith(list.t_logo_name, '.GIF')}">
						<img src="imageView.do?t_name=${list.t_name}" style="width:200px;height:200px;">
					</c:if>
					<a href="teamInfo.do?t_name=${list.t_name}">${list.t_name}</a> | ${list.t_address}
					<br>
				</div>
			</c:forEach>
		</c:if>
		<c:if test="${count==0}">
			등록된 팀이 없습니다. 첫번째 팀을 생성해주세요.
		</c:if>
	
	
	
	<div id="output"><ul style="list-style: none"></ul></div>
	<div class="paging-team-button">다음글보기</div>
	
	</div>
</div>