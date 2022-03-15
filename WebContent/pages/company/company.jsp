<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script src="http://code.jquery.com/jquery-2.2.1.min.js"></script>
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	
		
	<link href="${contextPath}/static/css/common.min.css" rel="stylesheet" type="text/css">
	<link href="${contextPath}/static/css/css-library.min.css" rel="stylesheet" type="text/css">
	<link href="${contextPath}/static/css/member.min.css" rel="stylesheet" type="text/css">
	<link href="${contextPath}/static/css/mypage.min.css" rel="stylesheet" type="text/css">
	
	<style>
	.select-css {
	  width: 150px;
	  padding: .4em .5em;
	  font-family: inherit;
	  background: url("${contextPath }/static/images/common/arrow.jpg") no-repeat 95% 50%;
	  -webkit-appearance: none;
	  -moz-appearance: none;
	  appearance: none;
	  border: 1px solid #999;
	  border-radius: 0px;
	  background-color:white;
	}
	
	.select-css::-ms-expand {
	  display: none;
	}
			.swiper-wrapper ::-webkit-scrollbar {
			    width: 5px;
			    height: 7px;
			}
			 
			.swiper-wrapper ::-webkit-scrollbar-track {
			    background-color: rgba(230,230,230,0.8);
			    border-radius: 7px;
			}
			 
			.swiper-wrapper ::-webkit-scrollbar-thumb {
			    background-color: rgba(100,100,100,0.4);
			    border-radius: 7px;
			}
	</style>
</head>

<body>
<jsp:include page="/header.jsp" flush="false" />
<div id="wrap">
	<div id="contents">
	<div class="innercon">
		<section class="lnbarea">
			<h2>업체회원</h2>
			<ul>
				<li class="lnb-depth2">
				<a href="${contextPath}/company/company.do">홈</a>
				</li>
				<li class="lnb-depth2">
				<a href="${contextPath}/company/createProductForm.do">상품 관리</a>
				</li>
			</ul>
		</section>
		<section class="conarea">
			<section class="chart" style="background-color:#f8f8f8">
				<div style="width:100%;">
					<div style="background-color: white;width:40%;height:430px;display:inline-block;margin:10px;">
						<div id="piechart" style="width: 100%; height: 100%;"></div>
					</div>
					<div style="background-color: white;width:55%;height:400px;display:inline-block;margin:10px;">
										
						<div id="orderchart" style="width: 100%; height: 100%;"></div>
						<div class="productList" style="text-align:center; margin:10px;">
							<span>구분 : </span>
							<input type="hidden" name="dateSort" value="YY-MM-DD"/>
							<input type="hidden" name="productId" value="0"/>
							<select class="select-css" onchange='selectDate(this.value)'>
								<option value="YY-MM-DD">일별</option>
								<option value="MM">월별</option>
								<option value="YYYY">년도별</option>
							</select>
						</div>
					</div>
				</div>
			</section>
		
		<section class='innercon category' data-aos='fade-up' style="margin-top:30px;">
                <h2><strong style='color:;font-weight:'>상품목록</strong></h2>
                <div class='swiper-container categoryswiper'>
                    <div class='swiper-wrapper'>
                        <div class='swiper-slide ' style="overflow-x:scroll;height:360px;">
                            
                <div class="product">
                	
                </div>
                        </div>
                    </div>
                    <div class='swiper-pagination-category'></div>
                </div>
            </section>
		</section>
	</div>
</div>
<jsp:include page="/footer.jsp" flush="false" />
</div>


<script type="text/javascript">

	// 업체 회원의 상품 목록 불러오기
	var html = "";
	var list = "";
	$.ajax({
		url:'${contextPath}/company/productList.do',
		dataType:'json',
		async:false,
		success : function(data){
			console.log(data);

			list += "<ul style='width:"+data.length * 220+"px;'>";
			
			html += "<span>상품목록 : </span>";
			html += "<select class='select-css' onchange='selectProductId(this.value)' style='width: 200px;'>";
			html += "<option value=0>전체</option>";
			for(var i in data){
				html += "<option value="+data[i].productId +">" + data[i].productName + "</option>";
				list += "<li style='float:left; margin:10px;'>";
				list += "<a><img src='https://tohomeimage.thehyundai.com/PD/PDImages/S/9/8/2/8809607171289_00.jpg?RS=350x420' style='width:200px;height:250px;'></a>";
				list += "</span><strong class='txt-ti ellipsis'>"+data[i].productName+"</strong></a><span class='info'>";
				list += "<span class='txt-price'><strong><em>"+data[i].discountPrice+"</em>원</strong></span></span></li>";
			}	
			html += "</select>";
			list += "</ul>";
			$(".productList").append(html);
			$(".product").append(list);
		},
		error: function(xhr, status, error) {
    		var errorResponse = JSON.parse(xhr.responseText);
        	var errorCode = errorResponse.code;
        	var message = errorResponse.message;
        	
        	alert(message);
    	}
	});//상품목록 불러오기 끝
	
	// ===== 차트그리기 시작 ====== //
	// ===== PieChart : 구매자 주 연령층 ===== //
	
	//db 데이터 저장 객체
	var queryObject = "";
	//db 데이터 저장 객체 개수 저장
	var queryObjectLen="";
	
	$.ajax({
		url:'${contextPath}/company/ageinfoChart.do',
		dataType:'json',
		async: false,
		success : function(data){
			queryObject = eval('('+JSON.stringify(data) +')');
			queryObjectLen = queryObject.ageinfo.length;
			
			google.load("visualization", "1", {packages:["corechart"]}); //구글 그래프 그리기 시작
			google.setOnLoadCallback(drawAgeinfoChart);
		},
		error: function(xhr, status, error) {
    		var errorResponse = JSON.parse(xhr.responseText);
        	var errorCode = errorResponse.code;
        	var message = errorResponse.message;
        	
        	alert(message);
    	}
	});//구매자 주 연령층 끝
	
	
	// ===== LineChart : 날짜별 판매량 ===== //
	
	//db 데이터 저장 객체
	var orderObject = "";
	//db 데이터 저장 객체 개수 저장
	var orderObjectLen="";
	
	//db 데이터 저장 객체
	
	$.ajax({
		url:'${contextPath}/company/orderinfoChart.do',
		dataType:'json',
		async: false,
		type:'post',
		data:{
			productId : 0,
			sort: 'YY-MM-DD',
		},
		success : function(data){
			orderObject = eval('('+JSON.stringify(data) +')');
			orderObjectLen = orderObject.orderinfo.length;
			
			google.load("visualization", "1", {packages:["corechart"]});
			google.setOnLoadCallback(drawOrderinfoChart);
		},
		error: function(xhr, status, error) {
    		var errorResponse = JSON.parse(xhr.responseText);
        	var errorCode = errorResponse.code;
        	var message = errorResponse.message;
        	
        	alert(message);
    	}
	});//날짜별 판매량 끝 
	
	
	// ===== 차트그리기 함수 ===== //
	 function drawAgeinfoChart() {
	      var data = new google.visualization.DataTable();
	      data.addColumn('string', 'group');
	      data.addColumn('number', 'count');
	 
	      for(var i=0 ; i < queryObjectLen ; i++ ) {
	            var group = queryObject.ageinfo[i].group;
	            var count = queryObject.ageinfo[i].count;
	            data.addRows([
	                [ group , parseInt(count) ]
	               ] );
	      }
	      
		var options = {
				title: '구매자 주연령층',
				backgroundColor: "transparent", //배경색 투명하게
				pieHole: 0.3,
				colors: ["#f3a683", "#f7d794", "#778beb", "#e77f67", "#cf6a87"],
				chartArea:{left:50,top:50,width:'90%',height:'95%'},
		};
	
		var chart = new google.visualization.PieChart(document.getElementById('piechart'));
	
		chart.draw(data,options);
	}//drawChart() end 그래프 그리기 끝
	
	function drawOrderinfoChart() {
	      var data = new google.visualization.DataTable();
	      data.addColumn('string', 'group');
	      data.addColumn('number', 'count');
	 
	      for(var i=0 ; i < orderObjectLen ; i++ ) {
	            var group = orderObject.orderinfo[i].group;
	            var count = orderObject.orderinfo[i].count;
	            data.addRows([
	                [ group , parseInt(count) ]
	               ] );
	      }
	      
		var options = {
				hAxis: {
					title: 'Date'
				},
				vAxis: {
					title: 'sales'
				},
				legend:{
					position:'none',
				},
				title: '날짜별 판매량',
				backgroundColor: '#f1f8e9',
				colors: ["#f3a683", "#f7d794", "#778beb", "#e77f67", "#cf6a87"],
				chartArea:{left:50,top:50,width:'85%',height:'70%'},
		};
	
		var chart = new google.visualization.LineChart(document.getElementById('orderchart'));
	
		chart.draw(data,options);
	}//lineChart() end 그래프 그리기 끝
	
	// LineChart 옵션 변경
	// 상품목록 변경
	function selectProductId(value){
		$("input[name=productId]").val(value);
		changeOrderChart();
	}
	
	// 날짜 변경
	function selectDate(value){
		$("input[name=dateSort]").val(value);
		changeOrderChart();
	}
	
	// Chart를 다시 그리기 위한 데이터 가져오기
	function changeOrderChart(){
		$.ajax({
			url:'${contextPath}/company/orderinfoChart.do',
			type:'post',
			dataType:'json',
			data:{
				productId : $("input[name=productId]").val(),
				sort: $("input[name=dateSort]").val(),
			},
			async:false,
			success : function(data){
				orderObject = eval('('+JSON.stringify(data) +')');
				orderObjectLen = orderObject.orderinfo.length;

				//구글 그래프 그리기 시작
				google.load("visualization", "1", {packages:["corechart"]});
				google.setOnLoadCallback(drawOrderinfoChart);
			},
			error: function(xhr, status, error) {
	    		var errorResponse = JSON.parse(xhr.responseText);
	        	var errorCode = errorResponse.code;
	        	var message = errorResponse.message;
	        	
	        	alert(message);
	    	}
		});//ajax 데이터 로드 끝
	}
      
</script>
</body>
</html>