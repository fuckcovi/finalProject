<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="page-main-style">
	팀원관리 <br>
	팀명: ${tMemList.get(0).t_name} : <%-- <% out.print(request.getParameter("t_name")); %> --%>
	<hr>
	소속 팀원 :
	<table>
		<tr>
			<th>프로필</th>
			<th>이름</th>
			<th>팀가입일</th>
			<th>팀원여부</th>
			<c:if test="${tMemList.get(0).master == user_id}">
			<th>팀내보내기</th>
			</c:if>
		</tr>
	<c:forEach var="list" items="${tMemList}">
		<tr>
			<td>
				<c:if test="${fn:endsWith(list.profile_name, '.jpg') || fn:endsWith(list.profile_name, '.png') || fn:endsWith(list.profile_name, '.gif') || fn:endsWith(list.profile_name, '.JPG') || fn:endsWith(list.profile_name, '.PNG') || fn:endsWith(list.profile_name, '.GIF')}">
					<img src="imageViewMem.do?id=${list.id}" style="width:100px;height:100px;">
				</c:if> 
			</td>
			<td><a href="mypage/main.do?id=${list.id}">${list.name}</a></td>
			<td>${list.t_mem_regdate}</td>
			<td> 
				<c:if test="${list.t_mem_auth==1}">
					팀원
				</c:if>
				<c:if test="${list.t_mem_auth==0}">
					미승인 회원
					<c:if test="${list.master == user_id }">
					<input type="button" class="btn" value="승인" onclick="location.href='approveMem.do?t_name=${list.t_name}&id=${list.id}'">
					</c:if>
				</c:if>
			</td>
			<c:if test="${list.master == user_id }">
				<td><input type="button" class="btn" value="내쫒기"  onclick="location.href='deleteMem.do?t_name=${list.t_name}&id=${list.id}'" <c:if test="${list.id==list.master}">disabled</c:if>></td>
			</c:if>
		<tr>
	</c:forEach>
	</table>
</div>