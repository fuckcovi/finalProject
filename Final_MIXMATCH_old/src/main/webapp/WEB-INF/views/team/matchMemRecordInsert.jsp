<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery-3.2.1.min.js"></script>
<div class="page-main-style">
	<h2>매치 개인 기록 입력</h2>
	<hr class="style"><br>

<c:if test="${match.m_type eq '축구'}">
	<form:form commandName="footCommand" id="type_form" action="homeMemRecord.do">
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
	
	<form:form commandName="footCommand" id="type_form" action="awayMemRecord.do">
	<form:hidden path="m_seq" value="${match.m_seq}"/>
	<form:hidden path="t_name" value="${match.m_challenger}"/>
	<table class="style"  id="awayMem">
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
			<td><form:input path="f_shoot"/></td>
			<td><form:input path="f_assist"/></td>
			<td><form:input path="f_goal"/></td>
			<td><form:input path="f_attack"/></td>
		</tr>
	</table>
	<input type="submit" value="원정선수추가등록">
	</form:form>
	<c:if test="${footcount>0}">
			<c:forEach var="list" items="${footlist}">
				${list.t_name} : ${list.name} : ${list.f_shoot} : ${list.f_assist} : ${list.f_goal}<br>
			</c:forEach>
	</c:if>
</c:if>

<c:if test="${match.m_type eq '농구'}">
	<form:form commandName="basketCommand" id="type_form" action="homeMemRecord.do">
	<form:hidden path="m_seq" value="${match.m_seq}"/>
	<form:hidden path="t_name" value="${match.t_name}"/>
	<table class="style"  id="homeMem">
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
	
	<form:form commandName="basketCommand" id="type_form" action="awayMemRecord.do">
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
			<c:forEach var="list" items="${basketlist}">
				${list.t_name} : ${list.name} <br>
			</c:forEach>
	</c:if>
</c:if>


<c:if test="${match.m_type eq '야구'}">
	<form:form commandName="baseCommand" id="type_form" action="homeMemRecord.do">
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
			<th>타율</th>
			<th>승리</th>
			<th>패배</th>
			<th>삼진</th>
			<th>이닝</th>
			<th>실점</th>
			<th>방어율</th>
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
			<td><form:input path="b_avg"/></td>
			<td><form:input path="b_win"/></td>
			<td><form:input path="b_lose"/></td>
			<td><form:input path="b_strike"/></td>
			<td><form:input path="b_ip"/></td>
			<td><form:input path="b_er"/></td>
			<td><form:input path="b_era"/></td>
		</tr>
	</table>
	<input type="submit" value="홈선수추가등록">
	</form:form>
	
	<form:form commandName="baseCommand" id="type_form" action="awayMemRecord.do">
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
			<th>타율</th>
			<th>승리</th>
			<th>패배</th>
			<th>삼진</th>
			<th>이닝</th>
			<th>실점</th>
			<th>방어율</th>
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
			<td><form:input path="b_avg"/></td>
			<td><form:input path="b_win"/></td>
			<td><form:input path="b_lose"/></td>
			<td><form:input path="b_strike"/></td>
			<td><form:input path="b_ip"/></td>
			<td><form:input path="b_er"/></td>
			<td><form:input path="b_era"/></td>
		</tr>
	</table>
	<input type="submit" value="원정선수추가등록">
	</form:form>
	<c:if test="${basecount>0}">
			<c:forEach var="list" items="${baselist}">
				${list.t_name} : ${list.name}<br>
			</c:forEach>
	</c:if>
</c:if>


	<input type="button" value="목록으로" onclick="location.href='match/scoreBoard.do'">
</div>