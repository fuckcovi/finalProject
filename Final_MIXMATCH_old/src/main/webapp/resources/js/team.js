$(document).ready(function(){
	
	// 팀랭킹화면
	$("#member").click(function(){
		$(".teamList").hide();
		$(".memList").show();
		
	});
	$("#team").click(function(){
		$(".teamList").show();
		$(".memList").hide();
		
	});
	
	
	// 팀홈화면
	$("#typeAll").click(function(){
		$("#야구").show();
		$("#농구").show();
		$("#축구").show();
	});
	$("#typeFoot").click(function(){
		$("#야구").hide();
		$("#농구").hide();
		$("#축구").show();
	}); 
	$("#typeBase").click(function(){
		$("#야구").show();
		$("#농구").hide();
		$("#축구").hide();
	}); 
	$("#typeBasket").click(function(){
		$("#야구").hide();
		$("#농구").show();
		$("#축구").hide();
	}); 
	
	
});