<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery-3.2.1.min.js"></script>
<!-- <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <meta name="robots" content="noindex, nofollow">
  <meta name="googlebot" content="noindex, nofollow">
 
  <link rel="stylesheet" type="text/css" href="/css/result-light.css"> -->
   <script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.js"></script>
  <style type="text/css">
    .wrap {
	width: 500px;
	height:400px; 
	margin: 0 auto;
}
.btn-holder {
	text-align: center;
	margin: 10px 0 10px 0;
}
#calendar table {
	border-collapse: collapse;
	text-align: center;
}
#calendar table thead td {
	height: 30px;
	font-weight: bold;
}	
#calendar table td {
	border: solid 1px #000;
}
#calendar table td.date-cell {
	height: 50px;
}
#calendar table td.sun {
	color: red;
}
#calendar table td.sat {
	color: blue;
}
#calendar table td.not-this-month {
	background: #ddd;
	color: #999;
}
/* #calendar table td.not-this-month a{
	display: none;
} */


  </style>
<script type='text/javascript'>//<![CDATA[
$(window).load(function(){

	
	
	var s_seq = $("#stadiumSeq").val();

var calendar = new controller(); 
calendar.init();

function controller(target) {

	var that = this;   
	var m_oMonth = new Date();
	m_oMonth.setDate(1);

	this.init = function() {
		that.renderCalendar();
		that.initEvent();
	}

    /* 달력 UI 생성 */
	this.renderCalendar = function() {
		var arrTable = [];

		arrTable.push('<table><colgroup>');
		for(var i=0; i<7; i++) {
			arrTable.push('<col width="100">');
		}		
		arrTable.push('</colgroup><thead><tr>');

		var arrWeek = "일월화수목금토".split("");

		for(var i=0, len=arrWeek.length; i<len; i++) {
			var sClass = '';
			sClass += i % 7 == 0 ? 'sun' : '';
			sClass += i % 7 == 6 ? 'sat' : '';
			arrTable.push('<td class="'+sClass+'">' + arrWeek[i] + '</td>');
		}
		arrTable.push('</tr></thead>');
		arrTable.push('<tbody>');

		var oStartDt = new Date(m_oMonth.getTime());
        // 1일에서 1일의 요일을 빼면 그 주 첫번째 날이 나온다.
		oStartDt.setDate(oStartDt.getDate() - oStartDt.getDay());

		for(var i=0; i<100; i++) {
			if(i % 7 == 0) {
				arrTable.push('<tr>');
			}

			var sClass = 'date-cell '
            sClass += m_oMonth.getMonth() != oStartDt.getMonth() ? 'not-this-month ' : '';
			sClass += i % 7 == 0 ? 'sun' : '';
			sClass += i % 7 == 6 ? 'sat' : '';
 
			arrTable.push('<td class="'+sClass+'" dataPick="'+that.getYearMonth(m_oMonth).substr(0,9)+oStartDt.getDate()+'일" id="'+that.getYearMonth(m_oMonth).substr(0,9)+oStartDt.getDate()+'일"><div>' + oStartDt.getDate() + '</div></td>'); 
			/*  arrTable.push('<td class="'+sClass+'" dataPick="'+that.getYearMonth(m_oMonth).substr(0,9)+oStartDt.getDate()+'일" id="'+that.getYearMonth(m_oMonth).substr(0,9)+oStartDt.getDate()+'일"><a href="stadiumBooking.do?s_seq='+s_seq+'">' + oStartDt.getDate() + '</a></td>'); */ 
			oStartDt.setDate(oStartDt.getDate() + 1);

			if(i % 7 == 6) {
				arrTable.push('</tr>');
				if(m_oMonth.getMonth() != oStartDt.getMonth()) {
					break;
				}
			}
		}
		arrTable.push('</tbody></table>');

		$('#calendar').html(arrTable.join(""));

		that.changeMonth();
	}

    /* Next, Prev 버튼 이벤트 */
	this.initEvent = function() {
		$('#btnPrev').click(that.onPrevCalendar);
		$('#btnNext').click(that.onNextCalendar);
	}

    /* 이전 달력 */
	this.onPrevCalendar = function() {
		m_oMonth.setMonth(m_oMonth.getMonth() - 1);
		that.renderCalendar();
	}

    /* 다음 달력 */
	this.onNextCalendar = function() {
		m_oMonth.setMonth(m_oMonth.getMonth() + 1);
		that.renderCalendar();
	}

    /* 달력 이동되면 상단에 현재 년 월 다시 표시 */
	this.changeMonth = function() {
		$('#currentDate').text(that.getYearMonth(m_oMonth).substr(0,9));
	}

    /* 날짜 객체를 년 월 문자 형식으로 변환 */
	this.getYearMonth = function(oDate) {
		return oDate.getFullYear() + '년' + (oDate.getMonth() + 1) + '월';
	}
}
});//]]> 

</script>
<script>
  // tell the embed parent frame the height of the content
  if (window.parent && window.parent.parent){
    window.parent.parent.postMessage(["resultsFrame", {
      height: document.body.getBoundingClientRect().height,
      slug: "8b44ft0w"
    }], "*")
  }
</script>
 
 <script type="text/javascript">
 $(document).ready(function(){
	 var bookList;
	 
	 $(document).on("click",".date-cell",function(){
		$("#loading").show();
		alert($(this).attr("dataPick")); 
		$.ajax({
			type:"post",
			data:{b_regdate:$(this).attr("dataPick"),s_seq:$("#stadiumSeq").val()},
			url:"bookingList.do",
			dataType:"json",
			cache:false,
			timeout:30000,
			success:function(data){
				$("#loading").hide();
				alert("예약가능 목록 호출");
				var bookCount = data.bookCount;
				bookList = data.bookList;
				if(bookCount>=0 && bookList!=null){
					alert(bookList);
					var output = "";
					output += "<table>"
					output += "	<tr>";
					output += "		<th>날짜</th>";
					output += "		<th>타임</th>";
					output += "		<th>가능여부</th>";
					output += "	</tr>";
					
					$(bookList).each(function(index,item){	
						alert("a123123123");
						;
						output += "	<tr>";
						output += "		<td>"+item.b_regdate+"</td>";
						output += "		<td>"+item.b_time+"</td>";
						output += "		<td>"+item.b_check+"</td>";
						output += "	</tr>";
						
					});

					output += "</table>";
					$("#output").append(output);
					
					
					
				}else{
					alert("예약목록 호출 시 에러 발생");
				}
			},
			error:function(){
				$("#loading").hide();
				alert("목록 호출 시 네트워크 오류!");
			}
		});
	 });
 }); 
 </script> 
 <div class="page-main-style">
	<br> 
<div>
	<h2>경기장 상세보기</h2>
	<input type="hidden" value="${stadium.s_seq}" id="stadiumSeq">
	<table>
	<tr>
		<th>경기장사진</th>
		<th>경기장이름</th>
		<th class="asd">종목</th>
		<th>경기장지역</th>
		<th>상세주소</th>
		<th>경기장등록일</th>
	</tr>
	
		<tr>
			<td>
				<c:if test="${fn:endsWith(stadium.s_logo_name, '.jpg') || 
							fn:endsWith(stadium.s_logo_name, '.png') || 
							fn:endsWith(stadium.s_logo_name, '.gif') || 
							fn:endsWith(stadium.s_logo_name, '.JPG') || 
							fn:endsWith(stadium.s_logo_name, '.PNG') || 
							fn:endsWith(stadium.s_logo_name, '.GIF')}">
					<img src="imageViewStadium.do?s_seq=${list.s_seq}" style="width:200px;height:200px;">
				</c:if>
				<c:if test="${empty stadium.s_logo_name }">
				<c:if test="${stadium.s_type eq '야구' }">
					<img src="${pageContext.request.contextPath}/resources/images/baseball.png" style="width:200px;height:200px;">
				</c:if>
				<c:if test="${stadium.s_type eq '농구' }">
					<img src="${pageContext.request.contextPath}/resources/images/basketball.png" style="width:200px;height:200px;">
				</c:if>
				<c:if test="${stadium.s_type eq '축구' }">
					<img src="${pageContext.request.contextPath}/resources/images/football.png" style="width:200px;height:200px;">
				</c:if>
				</c:if>
			</td>
			<td>${stadium.s_name}</td>
			<td>${stadium.s_type }</td>
			<td>${stadium.s_address1}</td>
			<td>${stadium.s_address2}</td>
			<td>${stadium.s_regdate}</td>
		</tr>
		
	</table>
</div>
<hr noshade="noshade">
<div>
	<h2>예약하기</h2>

	달력보여주고 각 날짜별로 4타임씩 선택가능.
	db에서 존재하는건 선택불가능.
	선택하면 날짜,타임이 db로 등록.
	예약목록가서 확정하면 포인트 차감하고 완료.
<div class='wrap' >
	<div class='btn-holder'>
		<button id='btnPrev'>&lt;</button>
		 <span id='currentDate'></span> 
		<button id='btnNext'>&gt;</button>
	</div>
	<div id="calendar"></div>
</div>
<div class='wrap' id="output" style="border:1px solid red;">

</div>
  
	
</div>
  


