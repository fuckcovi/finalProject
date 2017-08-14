<!-- <!-- <input type="text" id="sample5_address" placeholder="주소">
<input type="button" onclick="sample5_execDaumPostcode()" value="주소 검색"><br>
<div id="map" style="width:300px;height:300px;margin-top:10px;display:none"></div>

<script type="text/javascript" src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?apikey=7ef9069e49bc91fd21ed364c07865507&libraries=services"></script>
<script>
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div
        mapOption = {
            center: new daum.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
            level: 5 // 지도의 확대 레벨
        };

    //지도를 미리 생성
    var map = new daum.maps.Map(mapContainer, mapOption);
 	// 주소-좌표 변환 객체를 생성합니다
    var geocoder = new daum.maps.services.Geocoder();
    //마커를 미리 생성
    var marker = new daum.maps.Marker({
        position: new daum.maps.LatLng(37.537187, 127.005476),
        map: map
    });
    function sample5_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = data.address; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수

                // 기본 주소가 도로명 타입일때 조합한다.
                if(data.addressType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }

                // 주소 정보를 해당 필드에 넣는다.
                document.getElementById("sample5_address").value = fullAddr;
                
                alert(geocoder);
                // 주소로 좌표를 검색
                geocoder.addr2coord(data.address, function(status, result) {
                	alert(document.getElementById("sample5_address").value);
                    // 정상적으로 검색이 완료됐으면
                    if (status === daum.maps.services.Status.OK) {
                        // 해당 주소에 대한 좌표를 받아서
                        var coords = new daum.maps.LatLng(result.addr[0].lat, result.addr[0].lng);
                        // 지도를 보여준다.
                        mapContainer.style.display = "block";
                        map.relayout();
                        // 지도 중심을 변경한다.
                        map.setCenter(coords);
                        // 마커를 결과값으로 받은 위치로 옮긴다.
                        marker.setPosition(coords)
                    }
                });
            }
        }).open();
    }
</script> -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구글맵에 내 위치 표시하기</title>
<link rel="stylesheet" type="text/css" href="http://code.google.com/apis/maps/documentation/javascript/examples/default.css">
<script type="text/javascript" src="http://maps.google.com/maps/api/js?key=AIzaSyCdyu1YFV4ZjHm9VxN_bvLikvh524hb_uI"></script>
<script type="text/javascript">
/*
  	구글맵 사이트 : https://developers.google.com/maps
 	Maps 자바스크립트 : https://developers.google.com/maps/documentation/javascript
 	콘솔키 등록 : https://console.developers.google.com/
 	구글맵api 내key: key=AIzaSyCdyu1YFV4ZjHm9VxN_bvLikvh524hb_uI
 */
	window.onload = function(){
		// geolocation서비스 지원여부 체크
		if(!navigator.geolocation){
			document.getElementById("disp").innerHTML="위치정보지원안함";
			return;
		}
		// 사용자 현재 위치 확인
		navigator.geolocation.getCurrentPosition(function(position){
			var lat = position.coords.latitude;
			var lon = position.coords.longitude;
			// 검사의 console창에 찍히는것
			console.log("위도 : "+lat);
			console.log("경도 : "+lon);
			
			// 구글맵에 표시할 위치 정보를 갖는 객체 생성
			var initLoc = new google.maps.LatLng(lat,lon);
			
			// 구글맵 객체 생성
			var map = new google.maps.Map(document.getElementById("map_canvas"), 
											{zoom:16,
											// mapTypeId : ROADMAP(약도형태), TERRAIN(지형지물까지 보여짐)
											mapTypeId:google.maps.MapTypeId.ROADMAP}
											);
			
			// 구글맵에 위치정보 매칭
			map.setCenter(initLoc);	// 지도의 중앙에 현재 위치(위도경도) 명시
			// 지도위에 표시해줄 마커표시						initLoc 나의 위치를 map 지도에 표시하는데 title을 위에 써서 표지판
			//var marker = new google.maps.Marker({position:initLoc,map:map,title:"I am HERE"});
			
			
			// 말풍선 형태의 마커
			var infowindow = new google.maps.InfoWindow();
			infowindow.setContent("현재 내 위치 <br> 위도 : "+lat +", 경도 : " + lon);
			infowindow.setPosition(initLoc);	// 마커의 포지션은 lat,lon의 위치를 갖는 구글맵
			infowindow.open(map);
		});
	};
</script>
</head>
<body>
asdfasdfasdfㄴㅇㅊㅇㅊ
	<h2 id="disp">구글맵 보기</h2>
	<div id="map_canvas" style="width:300px;height:500px;border:2px solid red"></div>
</body>
</html>

 -->