<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="header" style="height:40px;border-bottom:1px solid #BDBDBD;">
	<div id="menu-icon">
	<ul>
		<li><a class="glyphicon glyphicon-home" href="${pageContext.request.contextPath }/home.do" id="home-icon" style="width:42px;height:40px;top:-1px;font-size:20px;"></a></li>
		<li><a class="glyphicon glyphicon-star" href="JavaScript:alert('Ctrl+D 키를 눌러 즐겨찾기에 추가하실 수 있습니다.')" id="menu-link" style="width:90px;height:40px;top:-4px;font-size:12px;">즐겨찾기</a></li>
	</ul>
	</div>
	<div class="header" id="login-bar">
	<c:if test="${empty user_id }">
		<ul>
			<li><a href="${pageContext.request.contextPath }/login.do" id="menu-link"><strong>로그인</strong></a></li>
			<li><a href="${pageContext.request.contextPath }/member/insert.do" id="menu-link">회원가입</a></li>
		</ul>
	</c:if>
	<c:if test="${!empty user_id }">
		<ul>
			<li style="padding-right:10px;"><strong>[ ${user_id} ] 님</strong></li>
			<li style="padding-right:5px;color:#ff4848;">${user_point} P</li>
			<li><a href="${pageContext.request.contextPath }/member/detail.do" id="menu-link">회원정보</a></li>
			<li><a href="${pageContext.request.contextPath }/logout.do" id="menu-link">로그아웃</a></li>
		</ul>
	</c:if>
	</div>
</div>

<div id="topMenu">
	<ul>
		<li class="topMenuLi">
			<a class="menuLink" href="${pageContext.request.contextPath}/notice.do">게시판</a>
			<ul class="submenu">
			<li><a href="${pageContext.request.contextPath}/notice.do" class="submenuLink">공지사항</a></li>
			<li><a href="${pageContext.request.contextPath}/freeboard.do" class="submenuLink">자유게시판</a></li>
			<li><a href="${pageContext.request.contextPath}/teamboard.do" class="submenuLink">팀게시판</a></li>
			</ul> 
		</li>
		<li class="topMenuLi">
			<a class="menuLink" href="${pageContext.request.contextPath}/match/matchBoard.do">매칭</a>
			<ul class="submenu">
				<li><a href="${pageContext.request.contextPath}/match/matchBoard.do" class="submenuLink">매치보드</a></li>
				<li><a href="${pageContext.request.contextPath}/match/scoreBoard.do" class="submenuLink">스코어보드</a></li>
				<li><a href="${pageContext.request.contextPath}/match/sportsToto.do" class="submenuLink">승부예측</a></li>
				<li><a href="${pageContext.request.contextPath}/league/leagueList.do" class="submenuLink">리그</a></li>
			</ul>
		</li>
		<li class="topMenuLi">
			<a class="menuLink" href="${pageContext.request.contextPath}/stadium.do">경기장예약</a>
			<ul class="submenu">
				<li><a href="${pageContext.request.contextPath}/stadium.do" class="submenuLink">경기장예약</a></li>
				<li><a href="${pageContext.request.contextPath}/stadiumConfirm.do" class="submenuLink">my예약목록</a></li>
			</ul>
		</li>
		<li class="topMenuLi">
			<a class="menuLink" href="${pageContext.request.contextPath}/team.do">팀메뉴</a>
			<ul class="submenu">
				<li><a href="${pageContext.request.contextPath}/team.do" class="submenuLink">팀관리</a></li>
				<li><a href="${pageContext.request.contextPath}/teamSchedule.do" class="submenuLink">팀일정/결과</a></li>
				<li><a href="${pageContext.request.contextPath}/teamRecord.do" class="submenuLink">팀기록</a></li>
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
			<a class="menuLink" href="${pageContext.request.contextPath}/mypage/main.do?id=${user_id}">마이페이지</a>
			<ul class="submenu">
				<li><a href="${pageContext.request.contextPath}/mypage/main.do?id=${user_id}" class="submenuLink">미니홈피</a></li>
				<li><a href="${pageContext.request.contextPath}/#" class="submenuLink">구매내역</a></li>
			</ul>
		</li>
		<li class="topMenuLi">
			<a class="menuLink glyphicon glyphicon-ok" href="${pageContext.request.contextPath}/#" style="color:#ff4848;">포인트몰</a>
		</li>
	</ul>
</div>