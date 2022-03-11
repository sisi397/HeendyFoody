<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link href="${contextPath}/static/css/common.min.css" rel="stylesheet" type="text/css">
<link href="${contextPath}/static/css/css-library.min.css" rel="stylesheet" type="text/css">
<link href="${contextPath}/static/css/mypage.min.css" rel="stylesheet" type="text/css">
</head>

<body>
  <div id="wrap" class="mypage recentprd">
    <div id="contents">
      <div class="innercon">
		<section class="lnbarea">
		  <h2>마이페이지</h2>
		  <ul>
		    <li class="lnb-depth1">
			  <a href="#">활동 관리</a>
			  <ul class="lnb-depth2">
			    <li><a href="${contextPath}/mypage/order_list">주문 내역</a></li>
	            <li><a href="${contextPath}/mypage/wish">좋아요</a></li>
			    <li><a href="${contextPath}/mypage/recent_view">최근 본 상품</a></li>
			    <li><a href="${contextPath}/mypage/personal_info">개인정보 이용현황</a></li>
			  </ul>
		    </li>
		  </ul>
		</section> 
		
		<section class="conarea">
          <h3 class="tit">최근 본 상품</h3>
           <div class="recent-list">
             <ul>
               <li class="grid-sizer"></li>
               	 <c:forEach items="${rvList}" var="rvDTO">
                 <li>
                   <a href="${contextPath}/product/detail?pid=${rvDTO.productId}">
       				<img src="${rvDTO.imageUrl}" alt="${rvDTO.productName}">         
                   </a> 
                 </li>
               </c:forEach>
             </ul>
           </div>           
        </section>
      
      </div>
    </div>
  </div>
  <script>
  $(document).ready(function(){

      var $grid = null;

      $(".recent-list ul").imagesLoaded(function(){
          $grid = $(".recent-list ul").masonry({
              columnWidth: ".grid-sizer",
              itemSelector: "li",
              gutter:0,
              stagger:0,
              horizontalOrder : true,
              percentPosition: true
          });
      });
  });
  
  </script>
</body>
</html>