<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/match.js"></script>
<div class="page-main-style">
	<h2>베팅하기</h2>
	<hr class="style"><br>
	<div class="detail-style"><br><br>
		<table class="versus"> 
			<tr>
			 	<td>
					<c:if test="${!empty t_name.t_logo_name}">
						<img src="matchImageView.do?t_name=${match.t_name}" width="80">
					</c:if>
					<c:if test="${empty t_name.t_logo_name}">
						<c:if test="${match.m_type eq '축구'}"><img src="${pageContext.request.contextPath}/resources/images/football.png" style="width:80px;height:80px;"></c:if>
						<c:if test="${match.m_type eq '야구'}"><img src="${pageContext.request.contextPath}/resources/images/baseball.png" style="width:80px;height:80px;"></c:if>
						<c:if test="${match.m_type eq '농구'}"><img src="${pageContext.request.contextPath}/resources/images/basketball.png" style="width:80px;height:80px;"></c:if>
					</c:if>
				<td><span style="font-size:25px;color:blue;font-weight:bold;">?</span><br>${match.t_name}<br><input type="button" value="팀정보" class="btn-team-info" onclick="location.href='${pageContext.request.contextPath}/teamInfo.do?t_name=${match.t_name}'"></td>
				<td><img src="${pageContext.request.contextPath}/resources/images/versus.png" width="80"></td>
				<td><span style="font-size:25px;color:red;font-weight:bold;">?</span><br>${match.m_challenger}<br><input type="button" value="팀정보" class="btn-team-info" onclick="location.href='${pageContext.request.contextPath}/teamInfo.do?t_name=${match.m_challenger}'"></td>
				<td>
					<c:if test="${!empty m_challenger.t_logo_name}">
						<img src="matchImageView.do?t_name=${match.m_challenger}" width="80">
					</c:if>
					<c:if test="${empty m_challenger.t_logo_name}">
						<c:if test="${match.m_type eq '축구'}"><img src="${pageContext.request.contextPath}/resources/images/football.png" style="width:80px;height:80px;"></c:if>
						<c:if test="${match.m_type eq '야구'}"><img src="${pageContext.request.contextPath}/resources/images/baseball.png" style="width:80px;height:80px;"></c:if>
						<c:if test="${match.m_type eq '농구'}"><img src="${pageContext.request.contextPath}/resources/images/basketball.png" style="width:80px;height:80px;"></c:if>
					</c:if>
				</td>
			</tr>
			<tr>
				<td colspan="2"><strong>승:</strong>${t_name.t_win} <strong>패:</strong>${t_name.t_lose} <strong>무:</strong>${t_name.t_draw}<br>
								<strong>경기수:</strong>${home} <strong>팀승률:</strong>
					<c:if test="${home eq 0}">
						0
					</c:if>
					<c:if test="${home ne 0}">
						<fmt:formatNumber value="${t_name.t_win/(home) * 100}" pattern="0"/>
					</c:if>%
				</td>
				<td></td>
				<td colspan="2"><strong>승:</strong>${m_challenger.t_win} <strong>패:</strong>${m_challenger.t_lose} <strong>무:</strong>${m_challenger.t_draw}<br>
								<strong>경기수:</strong>${away} <strong>팀승률:</strong>
					<c:if test="${away eq 0}">
						0
					</c:if>
					<c:if test="${away ne 0}">
						<fmt:formatNumber value="${m_challenger.t_win/(away) * 100}" pattern="0"/>
					</c:if>%
				</td>
			</tr>
			<tr>
				<td colspan="2" style="color:blue;">승률: <fmt:formatNumber value="${home/(home+away) * 100}" pattern="0"/>%</td>
				<td></td>
				<td colspan="2" style="color:red;">승률: <fmt:formatNumber value="${away/(home+away) * 100}" pattern="0"/>%</td>
			</tr>
			<tr>
				<td colspan="2"style="color:blue;">배당률: 
			<input type="hidden" id="t1_rate" value="${1/(home/(home+away))}">
			<c:if test="${1/(home/(home+away)) <= 1}">
				1
			</c:if>
			<c:if test="${1/(home/(home+away)) > 1}">
				<fmt:formatNumber value="${1/(home/(home+away))}" pattern="0.0"/>
			</c:if>배</td>
				<td></td>
				<td colspan="2" style="color:red;">배당률: 
			<input type="hidden" id="t2_rate" value="${1/(away/(home+away))}">
			<c:if test="${1/(away/(home+away)) <= 1}">
				1
			</c:if>
			<c:if test="${1/(away/(home+away)) > 1}">
				<fmt:formatNumber value="${1/(away/(home+away))}" pattern="0.0"/>
			</c:if>배</td>
			</tr>
		</table><br>
		
		<form:form commandName="toto" action="totoInsert.do" enctype="multipart/form-data" id="toto_form">
		<form:hidden path="m_seq" value="${match.m_seq}"/>
		<form:hidden path="id" value="${user_id}"/>
		
		<div style="float:left;width:100%;">	
		<c:if test="${!fn:contains(myteam,match.t_name) && !fn:contains(myteam,match.m_challenger) && !empty user_id}">
			예상승리팀: <select id="t_winteam" name="t_winteam" class="select_box">
				<option value="${match.t_name}">${match.t_name}</option>
				<option value="${match.m_challenger}">${match.m_challenger}</option>
			</select><br>
			<c:if test="${t_winteam.value eq match.t_name}"><form:hidden path="t_rate" value="10"/></c:if>
			예상점수: <input type="number" name="t_score"><br>
			베팅포인트: 
			<select name="t_point" class="select_box">
				<option value="10">10</option>
				<option value="50">50</option>
				<option value="100">100</option>
				<option value="500">500</option>
				<option value="1000">1000</option>
			</select>
			<input type="hidden" id="final_rate" name="t_rate">
			<br><br><input type="submit" value="베팅하기" class="btn" onclick="location.href='totoInsert.do'">
		</c:if>
		<c:if test="${fn:contains(myteam,match.t_name) || fn:contains(myteam,match.m_challenger)}">
			<span>본인이 속한 팀에는 베팅할 수 없습니다.</span><br><br>
		</c:if>
		<c:if test="${empty user_id}">
			<span>베팅하려면 로그인하세요.<br><br></span>
			<input type="button" value="로그인" class="btn" onclick="location.href='${pageContext.request.contextPath}/login.do'">
		</c:if>
		<input type="button" value="목록으로" class="btn" onclick="location.href='sportsToto.do'">
		</div>
		</form:form>
	</div>
	<br><hr class="style">
</div>