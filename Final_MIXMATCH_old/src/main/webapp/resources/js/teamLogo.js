$(document).ready(function(){

	$("#t_type").click(function(){
		if($("#t_type option:selected").val() =="농구"){
			$("#baseballImages").hide();
			$("#basketballImages").show();
			$("#footballImages").hide();
		}else if($("#t_type option:selected").val() =="축구"){
			$("#baseballImages").hide();
			$("#basketballImages").hide();
			$("#footballImages").show();
		}else if($("#t_type option:selected").val() =="야구"){
			$("#baseballImages").show();
			$("#basketballImages").hide();
			$("#footballImages").hide();
		}
	});
	$("#"+$("#myteam option:selected").val()).show();
	$("#myteam").click(function(){
		$("#myteamLogo img").hide();
		$("#"+$("#myteam option:selected").val()).show();
		
	});
	
	
});