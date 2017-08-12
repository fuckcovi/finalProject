<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	function bookmark(title, url) {
		if (document.all) { // 인터넷 익스플로러
			window.external.AddFavorite(url, title);
		} else if (window.chrome) { // 크롬
			alert('Ctrl + D 키를 눌러 즐겨찾기에 추가하실 수 있습니다.');
		}
	}
</script>
<div id="header1">
	<div id="icon-bar">
		<ul>
			<li><a class="glyphicon glyphicon-home" href="${pageContext.request.contextPath }/home.do" id="home-icon"></a></li>
			<li><a class="glyphicon glyphicon-star" href="javascript:bookmark('MIXMATCH', 'http://localhost:8080/mixmatch/home.do')" id="bookmark">즐겨찾기</a></li>
		</ul>
	</div>
	<div id="login-bar">
		<c:if test="${empty user_id}">
			<ul>
				<li><a href="${pageContext.request.contextPath}/login.do"><strong>로그인</strong></a></li>
				<li><a href="${pageContext.request.contextPath}/member/insert.do">회원가입</a></li>
			</ul>
		</c:if>
		<c:if test="${!empty user_id}">
			<ul>
				<li><strong>[ ${user_id} ] 님</strong></li>
				<li>${user_point} P</li>
				<li><a href="${pageContext.request.contextPath}/member/detail.do">회원정보</a></li>
				<li><a href="${pageContext.request.contextPath}/logout.do">로그아웃</a></li>
			</ul>
		</c:if>
	</div>
</div>

<div id="header2">
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
				<a href="${pageContext.request.contextPath}/#"><span class="menuLink glyphicon glyphicon-hand-right blink"><span>포인트몰</span></span></a>
			</li>
		</ul>
	</div>
</div>

<div id="main-logo">
	<img src="${pageContext.request.contextPath}/resources/images/mixmatch.png" width="300px">
</div>