<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>매치보드</title>
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
	<h2>매치보드</h2>
	<hr style="width:90%;border:3px solid #002266;"><br>
	<table>
		<tr>
			<th></th>
			<th>팀명</th>
			<th>지역</th>
			<th>날짜</th>
			<th>시간</th>
			<th></th>
		</tr>
		<tr>
			<td>팀사진</td>
			<td>팀명</td>
			<td>지역</td>
			<td>날짜</td>
			<td>시간</td>
			<td><input type="button" value="매치신청"></td>
		</tr>
	</table>   
	<br><hr style="width:90%;border:3px solid #002266;"> 
	<input type="button" value="매치등록" style="float:right;margin-right:42px;">
</div>
</body>
</html>