$(document).ready(function(){
	
	var currentPage;
	var teamCount;
	var rowCount;
	
	function teamList(pageNum){
		currentPage = pageNum;
		if(pageNum==1){
			$("#output").empty();
		}
		$.ajax({
			type:"post",
			data:{pageNum:pageNum},
			url:"teamList.do",
			dataType:"json",
			cache:false,
			timeout:30000,
			success:function(data){
				teamCount = data.teamCount;
				rowCount = data.rowCount;
				var list = data.list;
				
				if(teamCount<0 || list == null){
					alert("팀목록 호출 시 오류 발생");
				}else{
					$(list).each(function(index,item){
						var output="<li style='list-style: none;float:left;'>";
						output += "<div style='width:200px;height:250px;margin:10px 10px 10px 10px;' id='"+item.t_type+"'>";
						output += "	<c:if test='${fn:endsWith("+item.t_logo_name+", '.jpg') || fn:endsWith("+item.t_logo_name+", '.png') || fn:endsWith("+item.t_logo_name+", '.gif') || fn:endsWith("+item.t_logo_name+",  '.JPG') || fn:endsWith("+item.t_logo_name+",  '.PNG') || fn:endsWith("+item.t_logo_name+",  '.GIF')}'>";
						output += "		<img src='imageView.do?t_name="+item.t_name+"' style='width:200px;height:200px; >";
						output += "	</c:if>";
						output += "	<a href='teamInfo.do?t_name="+item.t_name+"'>"+item.t_name+"</a> | "+item.t_address ;
						output += "</div></li>";
						$("#output").append(output);
					});
					
					if(currentPage >= Math.ceil(teamCount/rowCount)){
						$(".paging-team-button").hide();
					}else{
						$(".paging-team-button").show();
					}
				}
			},
			error:function(){
				alert("네트워크 오류 발생");
			}
		});
	}
	$(".paging-team-button").click(function(){
		var pageNum = currentPage +1;
		teamList(pageNum);
	});

	// 초기 목록 호출
	teamList(1);
	
	
});