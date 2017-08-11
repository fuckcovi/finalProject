<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
 $(document).ready(function(){
		if($( window ).width() < 1050 ){
			$("#main_side").hide();
		}else{
			$("#main_side").show();
		}
 });
</script>
<div class="align-center" id="sideMenu">
	<ul>
		<li>
			
				마이페이지
			
		</li>
		<li>
			팀메뉴
		</li>
		<li>
			포인트샵
		</li>
		<li>
			채팅			
		</li>
	</ul>
</div>