<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="align-right">
	<c:if test="${empty user_id }">
	<a href="${pageContext.request.contextPath }/member/insert.do">회원가입</a>
	<a href="${pageContext.request.contextPath }/login.do">로그인</a>
	</c:if>
	<c:if test="${!empty user_id }">
	${user_id }님이 로그인 중
	<a href="${pageContext.request.contextPath }/logout.do">로그아웃</a>
	<a href="${pageContext.request.contextPath }/member/pwUpdate.do">비밀번호 수정</a>
	<a href="${pageContext.request.contextPath }/member/pwCheck.do">회원수정</a>
	<a href="${pageContext.request.contextPath }/member/delete.do">회원탈퇴</a>
	</c:if>
	<a href="${pageContext.request.contextPath}/#">사이트맵</a>
</div>

<div class="align-center" id="topMenu">
<ul>
<li class="topMenuLi">
<a class="menuLink" href="${pageContext.request.contextPath}/main.do">MIXMATCH</a>
</li>
<li class="topMenuLi">
<a class="menuLink" href="${pageContext.request.contextPath}/#">게시판</a>
<ul class="submenu">
<li><a href="${pageContext.request.contextPath}/#" class="submenuLink">공지사항</a></li>
<li><a href="${pageContext.request.contextPath}/#" class="submenuLink">자유게시판</a></li>
<li><a href="${pageContext.request.contextPath}/#" class="submenuLink">팀게시판</a></li>
</ul> 
</li>
<li class="topMenuLi">
<a class="menuLink" href="${pageContext.request.contextPath}/match/matchBoard.do">매칭</a>
<ul class="submenu">
<li><a href="${pageContext.request.contextPath}/match/matchBoard.do" class="submenuLink">매치보드</a></li>
<li><a href="${pageContext.request.contextPath}/match/scoreBoard.do" class="submenuLink">스코어보드</a></li>
<li><a href="${pageContext.request.contextPath}/match/toto.do" class="submenuLink">승부예측</a></li>
</ul>
</li>
<li class="topMenuLi">
<a class="menuLink" href="${pageContext.request.contextPath}/totalRank.do">기록/랭킹</a>
<ul class="submenu">
<li><a href="${pageContext.request.contextPath}/totalRank.do" class="submenuLink">통합P랭킹</a></li>
<li><a href="${pageContext.request.contextPath}/totalBaseRank.do" class="submenuLink">야구랭킹</a></li>
<li><a href="${pageContext.request.contextPath}/totalBasketRank.do" class="submenuLink">농구랭킹</a></li>
<li><a href="${pageContext.request.contextPath}/totalFootRank.do" class="submenuLink">축구랭킹</a></li>
</ul>
</li>
<li class="topMenuLi">
<a class="menuLink" href="${pageContext.request.contextPath}/#">포인트몰</a>
</li>
<li class="topMenuLi">
<a class="menuLink" href="${pageContext.request.contextPath}/team.do">팀관리</a>
<ul class="submenu">
<li><a href="${pageContext.request.contextPath}/teamSchedule.do" class="submenuLink">팀일정/결과</a></li>
<li><a href="${pageContext.request.contextPath}/teamRecord.do" class="submenuLink">팀기록</a></li>
</ul>
</li>
<li class="topMenuLi">
<a class="menuLink" href="${pageContext.request.contextPath}/#">마이페이지</a>
<ul class="submenu">
<li><a href="${pageContext.request.contextPath}/#" class="submenuLink">미니홈피</a></li>
<li><a href="${pageContext.request.contextPath}/#" class="submenuLink">구매내역</a></li>
</ul>
</li>
</ul>
</div>