$(document).ready(function(){

	/*$("#t_type").click(function(){
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
	});*/
	$(".bookListX").click(function(){
		$(".bookListX div").show();
		$(".bookListF div").hide();
	});
	$(".bookListF").click(function(){
		$(".bookListX div").hide();
		$(".bookListF div").show();
	});
	
	
	$(".bookList tr").show();
	$(".tablenull").hide();
	if(!$(".xplan tr").hasClass("plan")){
		$(".xplan .tablenull").show();
	}if(!$(".fplan tr").hasClass("plan")){
		$(".fplan .tablenull").show();
	}
	
	// 팀 선택에 따라 일정결과 목록 보여주기
	$("#teamBookList li").click(function(){
		if($(this).attr("value")=="allList"){
			$(".bookList tr").show();
			$(".tablenull").hide();
			if(!$(".xplan tr").hasClass("plan")){
				$(".xplan .tablenull").show();
			}if(!$(".fplan tr").hasClass("plan")){
				$(".fplan .tablenull").show();
			}
			alert("전체보기");
		}else{
			$(".bookList tr").hide();
			$(".tablehead").show();
			$("."+$(this).attr("value")).show();
			alert("팀선택 : "+$(this).attr("value"));
			if(!$(".xplan tr").hasClass($(this).attr("value"))){
				$(".xplan .tablenull").show();
			}if(!$(".fplan tr").hasClass($(this).attr("value"))){
				$(".fplan .tablenull").show();
			}
		}
	});
	
	/*
	$(document).on("click","#stadiumBookF",function(){
		 		$.ajax({
					type:"post",
					data:{b_seq:$(this).attr("b_seq")},
					url:"stadiumBookF.do",
					dataType:"json",
					cache:false,
					timeout:30000,
					success:function(data){
						
						
					},
					error:function(){
						alert("네트워크 오류!");
					}
				});
		
	});
	*/
	
});