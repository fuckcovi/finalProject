<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/team.js"></script> --%>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/teamList.js"></script> --%>
<div class="page-main-style">
	<h2>팀홈</h2>
	<hr class="style">
	<div>
		<c:if test="${joinCount==0 }">
			가입신청한 팀이 없습니다. 자신의 팀을 생성하거나 이미 등록된 팀에 가입신청하세요.
		</c:if>
		<c:if test="${joinCount>0 }">
		<h3>&lt;&lt;소속팀&gt;&gt;</h3>
		<div style="margin:0 auto;overflow-y: auto;width:600px; height:120px;">  
			<c:forEach var="joinList" items="${joinList}">
				<div>
					<c:if test="${joinList.t_mem_auth > 0}">
						<a href="teamInfo.do?t_name=${joinList.t_name}">${joinList.t_name}</a> | 
						<a href="teamRank.do?t_name=${joinList.t_name}">우리팀랭킹</a> | 
						<a href="teamMem.do?t_name=${joinList.t_name}">팀원관리</a>
					</c:if>
				</div>
			</c:forEach>
			</div>
			<hr size="1" width="85%">
			<h4>&lt;&lt;가입신청 중인 팀&gt;&gt;</h4> 
			<div style="overflow-y: auto; height:50px; width:600px; margin:0 auto;">  
			<c:forEach var="joinList" items="${joinList}">
				<c:if test="${joinList.t_mem_auth ==0}"> 
					<div><a href="teamInfo.do?t_name=${joinList.t_name}">${joinList.t_name}</a></div>
				</c:if>				
			</c:forEach>
			</div>
		</c:if>
		<input type="button" value="팀생성" class="btn" onclick="location.href='teamRegister.do'">
	</div>
	
	<hr class="style">
	
	<h3 style="color:red;">MixMatch등록팀현황</h3> 
	<input type="button" value="전체보기" class="btn" id="typeAll" onclick="location.href='team.do?t_type='">
	<input type="button" value="축구" class="btn" id="typeFoot" onclick="location.href='team.do?t_type=축구'">
	<input type="button" value="야구" class="btn" id="typeBase" onclick="location.href='team.do?t_type=야구'">
	<input type="button" value="농구" class="btn" id="typeBasket" onclick="location.href='team.do?t_type=농구'">
	
	
	<div  style="height: 300px;">
	
		<c:if test="${count>0}">
			<c:forEach var="list" items="${list}">
			<div style="width:30%;height:250px;margin:10px 10px 10px 10px; display:inline-block;" id="${list.t_type}">
				<c:if test="${fn:endsWith(list.t_logo_name, '.jpg') || 
							fn:endsWith(list.t_logo_name, '.png') || 
							fn:endsWith(list.t_logo_name, '.gif') || 
							fn:endsWith(list.t_logo_name, '.JPG') || 
							fn:endsWith(list.t_logo_name, '.PNG') || 
							fn:endsWith(list.t_logo_name, '.GIF')}">
					<img src="imageView.do?t_name=${list.t_name}" style="width:100%;height:200px;">
				</c:if>
				<c:if test="${empty list.t_logo_name }">
				<c:if test="${list.t_type eq '야구' }">
					<img src="${pageContext.request.contextPath}/resources/images/baseball.png" style="width:100%;height:200px;">
				</c:if>
				<c:if test="${list.t_type eq '농구' }">
					<img src="${pageContext.request.contextPath}/resources/images/basketball.png" style="width:100%;height:200px;">
				</c:if>
				<c:if test="${list.t_type eq '축구' }">
					<img src="${pageContext.request.contextPath}/resources/images/football.png" style="width:100%;height:200px;">
				</c:if>
				</c:if>
				<a href="teamInfo.do?t_name=${list.t_name}">${list.t_name}</a> | ${list.t_address}
				<br>
			</div>
			</c:forEach>
		<div class="align-center">${pagingHtml}</div>
		</c:if>
		<c:if test="${count==0}">
			등록된 팀이 없습니다. 첫번째 팀을 생성해주세요.
		</c:if>
	
	
	</div>
</div>