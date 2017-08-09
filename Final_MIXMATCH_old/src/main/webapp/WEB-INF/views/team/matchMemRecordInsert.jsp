<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/team.js"></script>
<style type="text/css">
	input{
		width:100%;
	}
	#modifyform input{float:left;}
</style>
<div class="page-main-style">
	<h2>매치 개인 기록 입력</h2>
	<hr class="style"><br>

<c:if test="${match.m_type eq '축구'}">
	<form:form commandName="footCommand" id="type_form" action="homeMemRecordFoot.do">
	<form:hidden path="m_seq" value="${match.m_seq}"/>
	<form:hidden path="t_name" value="${match.t_name}"/>
	<table class="style"  id="homeMem">
		<tr>
			<th>매치번호</th>
			<th>매치홈팀</th>
			<th>매치홈 유저</th>
			<th>슈팅</th>
			<th>도움</th>
			<th>골</th>
			<th>공격포인트</th>
		</tr>
		<tr>
			<td>${match.m_seq}</td>
			<td>${match.t_name}</td>
			<td>
				<form:select path="id">
			<c:forEach var="homelist" items="${homelist}">
				<form:option value="${homelist.id}">${homelist.name}</form:option>
			</c:forEach>
				</form:select>
			</td>
			<td><form:input path="f_shoot"/></td>
			<td><form:input path="f_assist"/></td>
			<td><form:input path="f_goal"/></td>
			<td><form:input path="f_attack"/></td>
		</tr>
	</table>
	<input type="submit" value="홈선수추가등록">
	</form:form>
	
	<form:form commandName="footCommand" id="type_form" action="awayMemRecordFoot.do">
	<form:hidden path="m_seq" value="${match.m_seq}"/>
	<form:hidden path="t_name" value="${match.m_challenger}"/>
	<table class="style"  id="awayMem" >
		<tr>
			<th>매치번호</th>
			<th>매치원정팀</th>
			<th>매치원정 유저</th>
			<th>슈팅</th>
			<th>도움</th>
			<th>골</th>
			<th>공격포인트</th>
		</tr>
		<tr>
			<td>${match.m_seq}</td>
			<td>${match.m_challenger}</td>
			<td>
				<form:select path="id">
			<c:forEach var="awaylist" items="${awaylist}">
				<form:option value="${awaylist.id}">${awaylist.name}</form:option>
			</c:forEach>
				</form:select>
			</td>
			<td><form:input path="f_shoot" /></td>
			<td><form:input path="f_assist"/></td>
			<td><form:input path="f_goal"/></td>
			<td><form:input path="f_attack"/></td>
		</tr>
	</table>
	<input type="submit" value="원정선수추가등록">
	</form:form>
	<c:if test="${footcount>0}">
	<table class="style">
		<tr>
			<th>소속팀</th>
			<th>이름</th>
			<th>슈팅</th>
			<th>도움</th>
			<th>골</th>
			<th>공격포인트</th>
		</tr>
			<c:forEach var="list" items="${footlist}">
			<tr class="footRecord" >
				<td>${list.t_name}</td>
				<td>${list.name}</td>
				<td>${list.f_shoot}</td>
				<td>${list.f_assist}</td>
				<td>${list.f_goal}</td>
				<td>${list.f_attack}</td>
				<td><input type="button" value="수정" class="footRecordModify" list_mseq="${list.m_seq}" list-tname="${list.t_name}" list-id="${list.id}" list-name="${list.name}" list-fshoot="${list.f_shoot}" list-fassist="${list.f_assist}" list-fgoal="${list.f_goal}" list-fattack="${list.f_attack}"></td>
			</tr>
			</c:forEach>
	</table>
	</c:if>
</c:if>

<c:if test="${match.m_type eq '농구'}">
	<form:form commandName="basketCommand" id="type_form" action="homeMemRecordBasket.do">
	<form:hidden path="m_seq" value="${match.m_seq}"/>
	<form:hidden path="t_name" value="${match.t_name}"/>
	<table class="style"  id="homeMem" >
		<tr>
			<th>매치번호</th>
			<th>매치홈팀</th>
			<th>매치홈 유저</th>
			<th>득점</th>
			<th>도움</th>
			<th>리바운드</th>
			<th>스틸</th>
			<th>블록</th>
			<th>3점슛</th>
		</tr>
		<tr>
			<td>${match.m_seq}</td>
			<td>${match.t_name}</td>
			<td>
				<form:select path="id">
			<c:forEach var="homelist" items="${homelist}">
				<form:option value="${homelist.id}">${homelist.name}</form:option>
			</c:forEach>
				</form:select>
			</td>
			<td><form:input path="b_score"/></td>
			<td><form:input path="b_assist"/></td>
			<td><form:input path="b_rebound"/></td>
			<td><form:input path="b_steel"/></td>
			<td><form:input path="b_block"/></td>
			<td><form:input path="b_3point"/></td>
		</tr>
	</table>
	<input type="submit" value="홈선수추가등록">
	</form:form>
	
	<form:form commandName="basketCommand" id="type_form" action="awayMemRecordBasket.do">
	<form:hidden path="m_seq" value="${match.m_seq}"/>
	<form:hidden path="t_name" value="${match.m_challenger}"/>
	<table class="style"  id="awayMem">
		<tr>
			<th>매치번호</th>
			<th>매치원정팀</th>
			<th>매치원정 유저</th>
			<th>득점</th>
			<th>도움</th>
			<th>리바운드</th>
			<th>스틸</th>
			<th>블록</th>
			<th>3점슛</th>
		</tr>
		<tr>
			<td>${match.m_seq}</td>
			<td>${match.m_challenger}</td>
			<td>
				<form:select path="id">
			<c:forEach var="awaylist" items="${awaylist}">
				<form:option value="${awaylist.id}">${awaylist.name}</form:option>
			</c:forEach>
				</form:select>
			</td>
			<td><form:input path="b_score"/></td>
			<td><form:input path="b_assist"/></td>
			<td><form:input path="b_rebound"/></td>
			<td><form:input path="b_steel"/></td>
			<td><form:input path="b_block"/></td>
			<td><form:input path="b_3point"/></td>
		</tr>
	</table>
	<input type="submit" value="원정선수추가등록">
	</form:form>
	<c:if test="${basketcount>0}">
	<table class="style">
		<tr>
			<th>소속팀</th>
			<th>이름</th>
			<th>득점</th>
			<th>도움</th>
			<th>리바운드</th>
			<th>스틸</th>
			<th>블록</th>
			<th>3점슛</th>
		</tr>
			<c:forEach var="list" items="${basketlist}">
			<tr class="basketRecord" >
				<td>${list.t_name}</td>
				<td>${list.name}</td>
				<td>${list.b_score}</td>
				<td>${list.b_assist}</td>
				<td>${list.b_rebound}</td>
				<td>${list.b_steel}</td>
				<td>${list.b_block}</td>
				<td>${list.b_3point}</td>
				<td><input type="button" value="수정" class="basketRecordModify" list_mseq="${list.m_seq}" list-tname="${list.t_name}" list-id="${list.id}" list-name="${list.name}" 
list-bscore="${list.b_score}" list-bassist="${list.b_assist}" list-bsteel="${list.b_steel}" list-bblock="${list.b_block}" list-brebound="${list.b_rebound}" list-b3point="${list.b_3point}"></td>
			</tr>
			</c:forEach>
	</table>
	</c:if>
</c:if>


<c:if test="${match.m_type eq '야구'}">
	<form:form commandName="baseCommand" id="type_form" action="homeMemRecordBase.do">
	<form:hidden path="m_seq" value="${match.m_seq}"/>
	<form:hidden path="t_name" value="${match.t_name}"/>
	<table class="style"  id="homeMem">
		<tr>
			<th>매치번호</th>
			<th>매치홈팀</th>
			<th>매치홈 유저</th>
			<th>타수</th>
			<th>안타수</th>
			<th>타점</th>
			<th>득점</th>
			<th>승리</th>
			<th>패배</th>
			<th>삼진</th>
			<th>이닝</th>
			<th>실점</th>
		</tr>
		<tr>
			<td>${match.m_seq}</td>
			<td>${match.t_name}</td>
			<td>
				<form:select path="id">
			<c:forEach var="homelist" items="${homelist}">
				<form:option value="${homelist.id}">${homelist.name}</form:option>
			</c:forEach>
				</form:select>
			</td>
			<td><form:input path="b_bat"/></td>
			<td><form:input path="b_hit"/></td>
			<td><form:input path="b_rbi"/></td>
			<td><form:input path="b_score"/></td>
			<td><form:input path="b_win"/></td>
			<td><form:input path="b_lose"/></td>
			<td><form:input path="b_strike"/></td>
			<td><form:input path="b_ip"/></td>
			<td><form:input path="b_er"/></td>
		</tr>
	</table>
	<input type="submit" value="홈선수추가등록">
	</form:form>
	
	<form:form commandName="baseCommand" id="type_form" action="awayMemRecordBase.do">
	<form:hidden path="m_seq" value="${match.m_seq}"/>
	<form:hidden path="t_name" value="${match.m_challenger}"/>
	<table class="style"  id="awayMem">
		<tr>
			<th>매치번호</th>
			<th>매치원정팀</th>
			<th>매치원정 유저</th>
			<th>타수</th>
			<th>안타수</th>
			<th>타점</th>
			<th>득점</th>
			<th>승리</th>
			<th>패배</th>
			<th>삼진</th>
			<th>이닝</th>
			<th>실점</th>
		</tr>
		<tr>
			<td>${match.m_seq}</td>
			<td>${match.m_challenger}</td>
			<td>
				<form:select path="id">
			<c:forEach var="awaylist" items="${awaylist}">
				<form:option value="${awaylist.id}">${awaylist.name}</form:option>
			</c:forEach>
				</form:select>
			</td>
			<td><form:input path="b_bat"/></td>
			<td><form:input path="b_hit"/></td>
			<td><form:input path="b_rbi"/></td>
			<td><form:input path="b_score"/></td>
			<td><form:input path="b_win"/></td>
			<td><form:input path="b_lose"/></td>
			<td><form:input path="b_strike"/></td>
			<td><form:input path="b_ip"/></td>
			<td><form:input path="b_er"/></td>
		</tr>
	</table>
	<input type="submit" value="원정선수추가등록">
	</form:form>
	<c:if test="${basecount>0}">
	<table class="style">
		<tr>
			<th>소속팀</th>
			<th>이름</th>
			<th>타수</th>
			<th>안타수</th>
			<th>타점</th>
			<th>득점</th>
			<th>타율</th>
			<th>승리</th>
			<th>패배</th>
			<th>삼진</th>
			<th>이닝</th>
			<th>실점</th>
			<th>방어율</th>
		</tr>
			<c:forEach var="list" items="${baselist}">
			<tr class="baseRecord">
				<td>${list.t_name}</td>
				<td>${list.name}</td>
				<td>${list.b_bat}</td>
				<td>${list.b_hit}</td>
				<td>${list.b_rbi}</td>
				<td>${list.b_score}</td>
				<td><fmt:formatNumber value="${list.b_avg }" pattern="0.000"/></td>
				<td>${list.b_win}</td>
				<td>${list.b_lose}</td>
				<td>${list.b_strike}</td>
				<td>${list.b_ip}</td>
				<td>${list.b_er}</td>
				<td><fmt:formatNumber value="${list.b_era }" pattern="0.00"/></td>
				<td><input type="button" value="수정" class="baseRecordModify" list_mseq="${list.m_seq}" list-tname="${list.t_name}" list-id="${list.id}" list-name="${list.name}" 
list-bbat="${list.b_bat}" list-bhit="${list.b_hit}" list-brbi="${list.b_rbi}" list-bscore="${list.b_score}" list-bavg="${list.b_avg}" 
list-bwin="${list.b_win}" list-blose="${list.b_lose}" list-bstrike="${list.b_strike}" list-bip="${list.b_ip}" list-ber="${list.b_er}" list-bera="${list.b_era}"></td>
			</tr>
			</c:forEach>
	</table>
	</c:if>
</c:if>


	<input type="button" value="목록으로" onclick="location.href='match/scoreBoard.do'">
</div>
