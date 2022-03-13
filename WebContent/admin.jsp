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
</head>

<body>
<jsp:include page="header.jsp" flush="false" />
<div id="piechart" style="width: 600px; height: 500px;"></div>

<script type="text/javascript">

	//db 데이터 저장 객체
	var queryObject = "";
	//db 데이터 저장 객체 개수 저장
	var queryObjectLen="";

	$.ajax({
		url:'${contextPath}/admin/ageinfoChart.do',
		dataType:'json',
		async:false,
		success : function(data){
			console.log(data);
			queryObject = eval('('+JSON.stringify(data) +')');
			queryObjectLen = queryObject.ageinfo.length;
		},
	  error : function(xhr, type) {
	      alert('server error occoured')
	         }
	});//ajax 데이터 로드 끝
	
	
	//구글 그래프 그르기 시작
	google.load("visualization", "1", {packages:["corechart"]});
	google.setOnLoadCallback(drawChart);
	
	 function drawChart() {
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
				chartArea:{left:50,top:50,width:'70%',height:'85%'},
		};
	
		var chart = 
		new google.visualization.PieChart(document.getElementById('piechart'));
	
		chart.draw(data,options);
	}//drawChart() end 그래프 그리기 끝
      
</script>
<jsp:include page="footer.jsp" flush="false" />
</body>
</html>