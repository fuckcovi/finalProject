<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery-3.2.1.min.js"></script>

<div class="page-main-style">
	<br>
	<h2>경기장 예약</h2>
	<input type="button" value="경기장등록" onclick="location.href='stadiumRegi.do'">
	협약맺은 경기장 등록 -->> 관리자가 하면 경기장 목록 쫙 뜨면서 경기장 상세정보 보면 지도 나오고.
	경기장예약하기들어가서 해당 경기장의 날짜,시간 선택해서 예약(0)걸어두고 포인트 결제하면 (1)완료.
<div style="width:100%;height:30px;">
	<form action="stadium.do" id="search_form" method="get">
		<ul class="search" style="list-style: none;">
			<li style="float:left ;">
				<select name="keyfield">
					<option value="s_type">종목</option>
					<option value="s_address1">지역</option>
					<option value="s_name">경기장이름</option>
				</select>
			</li> 
			<li style="float:left ;">
				<input type="text" name="keyword" id="keyword">
			</li>
			<li style="float:left ;">
				<input type="submit" value="경기장찾기">
			</li>
		</ul>
	</form>
</div>
	<br>
<div>
	<table >
	<tr>
		<th>경기장사진</th>
		<th>경기장이름</th>
		<th>종목</th>
		<th>경기장지역</th>
		<th>경기장등록일</th>
	</tr>
	
	<c:if test="${stadiumCount >0}">
		<c:forEach var="list" items="${stadiumList}">
		<tr>
			<td>
				<c:if test="${fn:endsWith(list.s_logo_name, '.jpg') || 
							fn:endsWith(list.s_logo_name, '.png') || 
							fn:endsWith(list.s_logo_name, '.gif') || 
							fn:endsWith(list.s_logo_name, '.JPG') || 
							fn:endsWith(list.s_logo_name, '.PNG') || 
							fn:endsWith(list.s_logo_name, '.GIF')}">
					<img src="imageViewStadium.do?s_seq=${list.s_seq}" style="width:200px;height:200px;">
				</c:if>
				<c:if test="${empty list.s_logo_name }">
				<c:if test="${list.s_type eq '야구' }">
					<img src="${pageContext.request.contextPath}/resources/images/baseball.png" style="width:200px;height:200px;">
				</c:if>
				<c:if test="${list.s_type eq '농구' }">
					<img src="${pageContext.request.contextPath}/resources/images/basketball.png" style="width:200px;height:200px;">
				</c:if>
				<c:if test="${list.s_type eq '축구' }">
					<img src="${pageContext.request.contextPath}/resources/images/football.png" style="width:200px;height:200px;">
				</c:if>
				</c:if>
			</td>
			<td><a href="${pageContext.request.contextPath }/stadiumDetail.do?s_seq=${list.s_seq}">${list.s_name}</a></td>
			<td>${list.s_type }</td>
			<td>${list.s_address1}</td>
			<td>${list.s_regdate}</td>
		</tr>
		</c:forEach>
		
	</table>
		<div class="align-center">${pagingHtml}</div>
	</c:if>
	<c:if test="${stadiumCount ==0}">
		<tr>
			<td colspan="3">등록된 경기장 없음</td>
		</tr>
		
	</table>
	</c:if>
</div>

</div>