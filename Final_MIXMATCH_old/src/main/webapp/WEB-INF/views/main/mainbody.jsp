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
<%-- <style type="text/css">
	ul{
		list-style: none;
	}
	div.half{
		width:49%;
		height:200px; 
		border:1px solid red;
		margin:0 auto;
		padding:0 0 0 0 ;
	}
	div.full{
		width:100%;
		height:200px;
		border:1px solid blue;
		float:left;
	}
	#ttype li{
		display: inline-block;
	}
	#noticeList{
		border: 1px solid red;
	}
	#noticeList li{
		border: 1px solid red;
		text-align: left;
	}
	#teamRankList{
		border: 1px solid blue;
	}
	#teamRankList li{
		border: 1px solid blue;
		text-align: left;
	}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		
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
<div style="width:80%;margin:0 auto;">
	<!--  공지사항 게시글 최근5개 보이기. 없으면 없음 띄움 -->
	<div id="noticeView" class="half" 
		style="float:left;">
		<h4>공지사항</h4>
		<ul id="noticeList">
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
	<!--  자유게시판 인기글 5개 보이기 -->
	<div id="freeboardView" class="half" style="float:right;">
		<h4>자유게시판</h4>
		
	</div>
	<div id="rankView" class="full">
		<h4>팀랭킹</h4>
		<ul id="ttype">
			<li id="baseRank">야구</li>
			<li id="footRank">축구</li>
			<li id="basketRank">농구</li>
		</ul>
		<ul id="teamRankList">
			<c:if test="${baseTeamCount>0 }">
				<c:forEach var="baseTeamList" items="${baseTeamList}" varStatus="status">
					<li class="base" ><a href="teamInfo.do?t_name=${baseTeamList.t_name}">${status.count}-${baseTeamList.t_name}-${baseTeamList.t_win}-${baseTeamList.t_draw}-${baseTeamList.t_lose}-${baseTeamList.t_address}</a></li>	
				</c:forEach>			
			</c:if>
			<c:if test="${baseTeamCount==0 }">
				<li class="base">등록된 야구팀이 없습니다.</li>
			</c:if>
			
			<c:if test="${basketTeamCount>0 }">
				<c:forEach var="basketTeamList" items="${basketTeamList}">
					<li class="basket" style="display: none;"><a href="teamInfo.do?t_name=${basketTeamList.t_name}">${basketTeamList.t_name}-${basketTeamList.t_win}-${basketTeamList.t_draw}-${basketTeamList.t_lose}-${basketTeamList.t_address}</a></li>	
				</c:forEach>			
			</c:if>
			<c:if test="${basketTeamCount==0 }">
				<li class="basket">등록된 농구팀이 없습니다.</li>
			</c:if>
			
			<c:if test="${footTeamCount>0 }">
				<c:forEach var="footTeamList" items="${footTeamList}">
					<li class="foot" style="display: none;"><a href="teamInfo.do?t_name=${footTeamList.t_name}">${footTeamList.t_name}-${footTeamList.t_win}-${footTeamList.t_draw}-${footTeamList.t_lose}-${footTeamList.t_address}</a></li>	
				</c:forEach>			
			</c:if>
			<c:if test="${footTeamCount==0 }">
				<li class="foot">등록된 축구팀이 없습니다.</li>
			</c:if>
		</ul>
	</div>
	<div id="matchView" class="full">
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