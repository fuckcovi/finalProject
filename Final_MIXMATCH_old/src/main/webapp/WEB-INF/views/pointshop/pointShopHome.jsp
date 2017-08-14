<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/point.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/pointshop.css">
<div class="page-main-style">
	<h2>포인트몰</h2>
	<!-- 검색 -->
	<form action="pointHome.do" id="search_form" method="get">
		<ul class="search">
			<li>
				<select name="keyfield">
					<option value="p_grade">등급</option>
					<option value="p_name">상품명</option>
					<option value="p_price">상품가격</option>
					<option value="all">전체</option>
				</select>
			</li>
			<li>
				<input type="text" name="keyword" id="keyword">
			</li>
			<li>
				<input type="submit" value="상품찾기">
			</li>
		</ul>
	</form>
	
	<c:if test="${user_id == 'admin' }">
	<div class="align-right">
    	<input type="button" value="상품등록" onclick="location.href='${pageContext.request.contextPath}/point/pointWrite.do'">
    </div>
	</c:if>
    
    <!-- 상품목록 -->
    <c:if test="${count == 0}">
	<div class="align-center">상품목록이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
    <table class="type07" border="1" >
        <tr>
            <th width="80">상품등급</th>
            <th>상품명</th>
            <th>포인트</th>
            <th>상품이미지</th>
          
        </tr>
        <c:forEach var="product" items="${list}">
        <tr>
           <td>${product.p_grade}</td>
           <td>${product.p_name}</td>
           <td><fmt:formatNumber value="${product.p_price}" pattern="###,###,###"/> p</td>
           <td>
           <a href="${pageContext.request.contextPath}/point/pointShopDetail.do?p_seq=${product.p_seq}">
           <c:if test="${fn:endsWith(product.p_file_name,'.jpg') ||
				 fn:endsWith(product.p_file_name,'.JPG') ||
				 fn:endsWith(product.p_file_name,'.gif') ||
				 fn:endsWith(product.p_file_name,'.GIF') ||
				 fn:endsWith(product.p_file_name,'.png') ||
				 fn:endsWith(product.p_file_name,'.PNG')}">
	<div class="align-center">
		<img src="imageView.do?p_seq=${product.p_seq}" style="max-width:100px">
	</div>			
	</c:if>
	</a>
           </td>
        </tr>
        </c:forEach>
    </table>
    
    <div class="align-center">
		${pagingHtml}
	</div>
</c:if>
</div>