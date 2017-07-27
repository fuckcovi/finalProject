<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="align-right">
	<a href="${pageContext.request.contextPath}/login.do">로그인</a>
	<a href="${pageContext.request.contextPath}/#">회원가입</a>
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
<a class="menuLink" href="${pageContext.request.contextPath}/#">매칭</a>
<ul class="submenu">
<li><a href="${pageContext.request.contextPath}/match/matchBoard.do" class="submenuLink">매치보드</a></li>
<li><a href="${pageContext.request.contextPath}/match/scoreBoard.do" class="submenuLink">스코어보드</a></li>
<li><a href="${pageContext.request.contextPath}/match/toto.do" class="submenuLink">승부예측</a></li>
</ul>
</li>
<li class="topMenuLi">
<a class="menuLink" href="${pageContext.request.contextPath}/#">기록/랭킹</a>
<ul class="submenu">
<li><a href="${pageContext.request.contextPath}/#" class="submenuLink">통합P랭킹</a></li>
<li><a href="${pageContext.request.contextPath}/#" class="submenuLink">야구랭킹</a></li>
<li><a href="${pageContext.request.contextPath}/#" class="submenuLink">농구랭킹</a></li>
<li><a href="${pageContext.request.contextPath}/#" class="submenuLink">축구랭킹</a></li>
</ul>
</li>
<li class="topMenuLi">
<a class="menuLink" href="${pageContext.request.contextPath}/#">포인트몰</a>
</li>
<li class="topMenuLi">
<a class="menuLink" href="${pageContext.request.contextPath}/team.do">팀관리</a>
<ul class="submenu">
<li><a href="${pageContext.request.contextPath}/#" class="submenuLink">팀일정/결과</a></li>
<li><a href="${pageContext.request.contextPath}/#" class="submenuLink">팀기록</a></li>
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