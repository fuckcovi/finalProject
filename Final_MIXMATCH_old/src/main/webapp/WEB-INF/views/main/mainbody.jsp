<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
$(document).ready(function(){
	var height =  $(".notice").height(); //공지사항의 높이값을 구해주고~~
	var num = $(".rolling li").length; // 공지사항의 개수를 알아볼수 있어요! length라는 것으로!
	var max = height * num; //그렇다면 총 높이를 알 수 있겠죠 ?
	var move = 0; //초기값을 설정해줍니다.
	function noticeRolling(){
		move += height; //여기에서 += 이라는 것은 move = move + height 값이라는 뜻을 줄인 거에요.
		$(".rolling").animate({"top":-move},600,function(){ // animate를 통해서 부드럽게 top값을 올려줄거에요.
			if( move >= max ){ //if문을 통해 최대값보다 top값을 많이 올렸다면 다시 !
				$(this).css("top",0); //0으로 돌려주고~
				move = 0; //초기값도 다시 0으로!
			};
		});
	};
	noticeRollingOff = setInterval(noticeRolling,5000); //자동롤링답게 setInterval를 사용해서 1000 = 1초마다 함수 실행!!
	$(".rolling").append($(".rolling li").first().clone()); //올리다보면 마지막이 안보여서 clone을 통해 첫번째li 복사!
	
	$("#baseRank").click(function(){
		$("#teamRankList .base").show();
		$("#teamRankList .foot").hide();
		$("#teamRankList .basket").hide();
	});
	$("#basketRank").click(function(){
		$("#teamRankList .base").hide();
		$("#teamRankList .foot").hide();
		$("#teamRankList .basket").show();
	});
	$("#footRank").click(function(){
		$("#teamRankList .base").hide();
		$("#teamRankList .foot").show();
		$("#teamRankList .basket").hide();
	});
});		
</script>
<div id="notice-link">
	<div style="float:left;text-align:right;width:40px;height:50px;font-size:20px;padding-top:13px;">
		<span class="glyphicon glyphicon-volume-up"></span>
	</div>
	<div class="notice">
		<ul class="rolling">
			<c:if test="${noticeCount>0 }">
			<c:forEach var="noticeList" items="${noticeList}">
				<li><a href="noticeDetail.do?gn_seq=${noticeList.gn_seq}">${noticeList.gn_title }</a></li>
			</c:forEach>
		</c:if>
		<c:if test="${noticeCount==0 }">
			<li>등록된 공지사항이 없습니다.</li>
		</c:if>
		</ul>
	</div>
</div>

<div style="min-width:600px;width:100%;height:200px;">
	<div class="flip-container" onclick="location.href='${pageContext.request.contextPath}/notice.do'">
	    <div class="flipper">
	        <div class="front">
		       	공지사항<br><span class="glyphicon glyphicon-volume-up" style="font-size:30px;"></span>
	        </div>
	        <div class="back">
		       	보러가기<br><span class="glyphicon glyphicon-chevron-right" style="font-size:30px;"></span>
	        </div>
	    </div>
	</div>
	<div class="flip-container" onclick="location.href='${pageContext.request.contextPath}/match/matchBoard.do'">
	    <div class="flipper">
	        <div class="front">
		       	매치보드<br><span class="glyphicon glyphicon-list-alt" style="font-size:30px;"></span>
	        </div>
	        <div class="back">
		    	보러가기<br><span class="glyphicon glyphicon-chevron-right" style="font-size:30px;"></span>
	        </div>
	    </div>
	</div>
	<div class="flip-container" onclick="location.href='${pageContext.request.contextPath}/stadium.do'">
	    <div class="flipper">
	        <div class="front">
		       	경기장예약<br><span class="glyphicon glyphicon-calendar" style="font-size:30px;"></span>
	        </div>
	        <div class="back">
		    	보러가기<br><span class="glyphicon glyphicon-chevron-right" style="font-size:30px;"></span>
	        </div>
	    </div>
	</div>
	<div class="flip-container" onclick="location.href='${pageContext.request.contextPath}/mypage/main.do?id=${user_id}'">
	    <div class="flipper">
	        <div class="front">
		   		미니홈피<br><span class="glyphicon glyphicon-home" style="font-size:30px;"></span>
	        </div>
	        <div class="back">
		    	보러가기<br><span class="glyphicon glyphicon-chevron-right" style="font-size:30px;"></span>
	        </div>
	    </div>
	</div>
</div>
<br>
<div id="freeboard">
	<a href="${pageContext.request.contextPath}/freeboard.do" style="font-size:18px;">자유게시판<span class="glyphicon glyphicon-chevron-right" style="font-size:18px;"></span></a>
	<br><br>
	<table>
		<tr>
			<th width="100">글번호</th>
			<th>제목</th>
			<th width="100">날짜</th>
			<th width="100">조회수</th>
		</tr>
		<tr>
			<td>글번호</td>
			<td>제목</td>
			<td>날짜</td>
			<td>조회수</td>
		</tr>
		<tr>
			<td>글번호</td>
			<td>제목</td>
			<td>날짜</td>
			<td>조회수</td>
		</tr>
		<tr>
			<td>글번호</td>
			<td>제목</td>
			<td>날짜</td>
			<td>조회수</td>
		</tr>	
	</table>
</div>
<br>
<div id="rankView">
	<a href="${pageContext.request.contextPath}/totalRank.do" style="font-size:18px;">기록/랭킹<span class="glyphicon glyphicon-chevron-right" style="font-size:18px;"></span></a>
	<br><br>
	<ul id="ttype">
		<li id="footRank"><img src="${pageContext.request.contextPath}/resources/images/scicon.png" width="15"> 축구</li>
		<li id="baseRank"><img src="${pageContext.request.contextPath}/resources/images/bsicon.png" width="15"> 야구</li>
		<li id="basketRank"><img src="${pageContext.request.contextPath}/resources/images/bkicon.png" width="15"> 농구</li>
	</ul>
	<br>
	<table id="teamRankList">
		<tr>
			<th>순위</th>
			<th>팀명</th>
			<th>승리</th>
			<th>무승부</th>
			<th>패배</th>
			<th>연고지</th>
		</tr>
		<c:if test="${baseTeamCount>0 }">
			<c:forEach var="baseTeamList" items="${baseTeamList}" varStatus="status">
					<tr class="base" onclick="location.href='teamInfo.do?t_name=${baseTeamList.t_name}'" style="display: none;">
						<td>
							<c:if test="${status.count eq 1}"><img src="${pageContext.request.contextPath}/resources/images/goldmedal.png" width="20"></c:if>
							<c:if test="${status.count eq 2}"><img src="${pageContext.request.contextPath}/resources/images/silvermedal.png" width="20"></c:if>
							<c:if test="${status.count eq 3}"><img src="${pageContext.request.contextPath}/resources/images/bronzemedal.png" width="20"></c:if>
							<c:if test="${status.count ne 1 && status.count ne 2 && status.count ne 3}">${status.count}</c:if>
						</td>
						<td>${baseTeamList.t_name}</td>
						<td>${baseTeamList.t_win}</td>
						<td>${baseTeamList.t_draw}</td>
						<td>${baseTeamList.t_lose}</td>
						<td>${baseTeamList.t_address}</td>
					</tr>
			</c:forEach>			
		</c:if>
		<c:if test="${baseTeamCount==0 }">
			<tr class="base"><td colspan="6">등록된 야구팀이 없습니다.</td></tr>
		</c:if>
			
		<c:if test="${basketTeamCount>0 }">
			<c:forEach var="basketTeamList" items="${basketTeamList}" varStatus="status">
				<tr class="basket" onclick="location.href='teamInfo.do?t_name=${basketTeamList.t_name}'" style="display: none;">
						<td>
							<c:if test="${status.count eq 1}"><img src="${pageContext.request.contextPath}/resources/images/goldmedal.png" width="20"></c:if>
							<c:if test="${status.count eq 2}"><img src="${pageContext.request.contextPath}/resources/images/silvermedal.png" width="20"></c:if>
							<c:if test="${status.count eq 3}"><img src="${pageContext.request.contextPath}/resources/images/bronzemedal.png" width="20"></c:if>
							<c:if test="${status.count ne 1 && status.count ne 2 && status.count ne 3}">${status.count}</c:if>
						</td>
						<td>${basketTeamList.t_name}</td>
						<td>${basketTeamList.t_win}</td>
						<td>${basketTeamList.t_draw}</td>
						<td>${basketTeamList.t_lose}</td>
						<td>${basketTeamList.t_address}</td>
					</tr>	
			</c:forEach>			
		</c:if>
		<c:if test="${basketTeamCount==0 }">
			<tr class="basket"><td colspan="6">등록된 농구팀이 없습니다.</td></tr>
		</c:if>
		
		<c:if test="${footTeamCount>0 }">
			<c:forEach var="footTeamList" items="${footTeamList}" varStatus="status">
				<tr class="foot" onclick="location.href='teamInfo.do?t_name=${footTeamList.t_name}'" >
						<td>
							<c:if test="${status.count eq 1}"><img src="${pageContext.request.contextPath}/resources/images/goldmedal.png" width="20"></c:if>
							<c:if test="${status.count eq 2}"><img src="${pageContext.request.contextPath}/resources/images/silvermedal.png" width="20"></c:if>
							<c:if test="${status.count eq 3}"><img src="${pageContext.request.contextPath}/resources/images/bronzemedal.png" width="20"></c:if>
							<c:if test="${status.count ne 1 && status.count ne 2 && status.count ne 3}">${status.count}</c:if>
						</td>
						<td>${footTeamList.t_name}</td>
						<td>${footTeamList.t_win}</td>
						<td>${footTeamList.t_draw}</td>
						<td>${footTeamList.t_lose}</td>
						<td>${footTeamList.t_address}</td>
					</tr>
			</c:forEach>			
		</c:if>
		<c:if test="${footTeamCount==0 }">
			<tr class="foot"><td colspan="6">등록된 축구팀이 없습니다.</td></tr>
		</c:if>
	</table>
</div>
<br>

<%-- <div id="matchView" class="full">
		<h4>최근경기결과</h4>
		<ul>
		<c:if test="${matchResultCount>0 }">
			<c:forEach var="matchResultList" items="${matchResultlist}">
				<c:if test="${!empty matchResultList.m_challenger }">
					<li>${matchResultList.t_name }-${matchResultList.m_home} : ${matchResultList.m_away}-${matchResultList.m_challenger }</li>
				</c:if>
			</c:forEach>
		</c:if>
		<c:if test="${matchResultCount==0 }">
			<li>최근 경기결과가 없습니다.</li>
		</c:if>
		</ul>
	</div>
	
</div> --%>