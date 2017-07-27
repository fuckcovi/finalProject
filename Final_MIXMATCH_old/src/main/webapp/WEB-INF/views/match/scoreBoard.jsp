<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스코어보드</title>
</head>
<style>
	table {
		margin: 0 auto; 
	}
	
	table, th, td {
		border: 1px solid black;
		border-collapse: collapse;
	}
	
	th, td {
		width: 130px;  
	}
	
	th {
		background: #FF4848;
		color: white;
		height: 30px;
	}
	
	td {
		height: 80px;
	}
</style>
<body>
<div>
	<h2>스코어보드</h2>
	<hr style="width:90%;border:3px solid #002266;"><br>
	<table>
		<tr>
			<th>지역</th>
			<th>날짜</th>
			<th>시간</th>
			<th></th>
			<th>결과</th>
			<th></th>
		</tr>
		<tr>
			<td>지역</td>
			<td>날짜</td>
			<td>시간</td>
			<td>홈팀</td>
			<td><input type="button" value="결과등록"></td>
			<td>어웨이팀</td>
		</tr>
	</table>   
	<br><hr style="width:90%;border:3px solid #002266;">
</div>
</body>
</html>