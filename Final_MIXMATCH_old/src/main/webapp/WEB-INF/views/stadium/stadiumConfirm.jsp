<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/book.js"></script>
<div class="page-main-style">
	<h2>경기장 예약 확인 - 팀마스터인 팀만 보여짐</h2>
	<div style="margin:0 auto;overflow-y: auto;">
	<c:if test="${teamCountMaster>0 }">    
		<ul style="list-style: none;margin: 0 auto;height:70px; width:750px;padding:0;"  id="teamBookList" >
			<li style="float: left; width:150px;" value="allList">
				<input type="button" class="btn"  style="width:150px;border:1px solid white;" value="전체보기">
			</li>
			<c:forEach var="list" items="${teamlist}">
				<li style="float: left; width:150px;" value="${list.t_name}">
					<input type="button" class="btn" style="width:150px;border:1px solid white;"  value="${list.t_name}"> 
					
				</li>
			</c:forEach>
		</ul>
		<hr size="1" width="100%">
		<ul style="list-style: none;">
			<li class="bookListX btn" style="display:inline-block;width:150px;background-color:#e8d477;">예약준비</li>
			<li class="bookListF btn" style="display:inline-block;width:150px;background-color:#e8d477;">예약확정</li>
		</ul>
		<div class="bookListX">
			<div class="bookList xplan">
			<table class="style">			 
			<tr class="tablehead">
				<th>예약일자</th>
				<th>예약경기장</th>
				<th>예약타임</th>
				<th>예약팀</th>
			</tr>
			<c:forEach var="teamlist" items="${teamlist}">
				<c:forEach var="booklist" items="${booklist}" >
					<c:if test="${booklist.b_check==1 && (booklist.t_name == teamlist.t_name )}">
						<tr class="${booklist.t_name} plan">
							<td>${booklist.b_regdate}</td>
							<td>${booklist.s_seq}</td>
							<td>${booklist.b_time}</td>
							<td>${booklist.t_name}</td>
							<td><input type="button" id="stadiumBookF" onclick="location.href='stadiumBookC.do?b_seq=${booklist.b_seq}'" value="예약취소"></td>
							<td><input type="button" id="stadiumBookF" onclick="location.href='stadiumBookF.do?b_seq=${booklist.b_seq}'" value="예약확정"></td>
						</tr>
					</c:if>
				</c:forEach>	
			</c:forEach>
			<tr class="tablenull" style="display: none;">
				<td colspan="3">예정일정없음</td>
			</tr>
			</table>
			</div>
		</div>
		<div class="bookListF" >
			<div class="bookList fplan" style="display:none">
				<table class="style">			
				<tr class="tablehead">
					<th>예약일자</th>
					<th>예약경기장</th>
					<th>예약타임</th>
					<th>예약팀</th>
				</tr>
				
				<c:forEach var="teamlist" items="${teamlist}">
					<c:forEach var="booklist" items="${booklist}">
						<c:if test="${booklist.b_check==2 && (booklist.t_name == teamlist.t_name) }">
							<tr class="${booklist.t_name} plan">
								<td>${booklist.b_regdate}</td>
								<td>${booklist.b_seq}</td>
								<td>${booklist.b_time}</td>
								<td>${booklist.t_name}</td>
							</tr>
						</c:if>
					</c:forEach>
				</c:forEach>
				<tr class="tablenull" style="display: none;">
					<td colspan="4">확정일정없음</td>
				</tr>
				</table>
			</div>
		</div>
	</c:if>
	<c:if test="${teamCountMaster ==0 }">
		팀마스터인 팀이 존재하지 않으므로 예약리스트를 볼 수 없습니다.
	</c:if>
	</div>
</div>