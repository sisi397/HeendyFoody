<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>최근 본 상품</title>
<link href="${contextPath}/static/css/common/common.min.css" rel="stylesheet" type="text/css">
<link href="${contextPath}/static/css/common/css-library.min.css" rel="stylesheet" type="text/css">
<link href="${contextPath}/static/css/mypage.min.css" rel="stylesheet" type="text/css">
<link href="${contextPath}/static/css/recent-view.css" rel="stylesheet" type="text/css">
<!-- masonry 레이아웃을 위한 라이브러리 -->
<script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js"></script>
<script src="https://unpkg.com/imagesloaded@4/imagesloaded.pkgd.min.js"></script>
</head>

<body>
  <div id="wrap" class="mypage recentprd">
	<%@ include file="/navbar.jsp"%>
    <div id="contents">
      <div class="innercon">
      <%@ include file="/pages/mypage/sidebar.jsp" %>
      
	    <section class="conarea">
          <h3 class="tit">최근 본 상품</h3>
          
          <!-- 최근 본 상품이 있다면 -->
          <c:if test="${!empty rvList}">
            <div class="recent-list">
              <ul>
                <li class="grid-sizer"></li>
                  <c:forEach items="${rvList}" var="rvDTO">
                    <li class="figure">
                      <!-- 상품이 업체로부터 삭제된 경우 -->
                 	  <c:if test="${rvDTO.deleted == 1}">
		                <span class="soldout">판매중단</span>
		                <a href="#">
       				      <img src="${rvDTO.imageUrl}" alt="${rvDTO.productName}">         
                        </a> 
		              </c:if>
		              <!-- 상품 수량이 0인 경우(삭제 X) -->
		              <c:if test="${rvDTO.productCount == 0 && rvDTO.deleted != 1}">
                        <a href="${contextPath}/product/detail.do?pid=${rvDTO.productId}&cid=${rvDTO.companyId}">
		                  <span class="soldout">일시품절</span>
       				      <img src="${rvDTO.imageUrl}" alt="${rvDTO.productName}">         
                        </a> 
		              </c:if>
		              <!-- 정상 -->	
		              <c:if test="${rvDTO.productCount > 0 && rvDTO.deleted != 1}">
                        <a href="${contextPath}/product/detail.do?pid=${rvDTO.productId}&cid=${rvDTO.companyId}">
       				      <img src="${rvDTO.imageUrl}" alt="${rvDTO.productName}">         
                        </a> 
		              </c:if>			            
                    </li>
                  </c:forEach>
                </ul>
              </div>
            </c:if>
          
            <!-- 최근 본 상품이 없다면 -->
            <c:if test="${empty rvList}">
	   	      <div class="nodata">최근 본 상품이 아직 없습니다.</div>
	        </c:if>           
          </section>
      
        </div>
      </div>
      <%@ include file="/footer.jsp" %>
    </div>
  <script>
  /*
	@Author 이지민
	masonry 레이아웃 호출
*/

  //masonry 레이아웃 세팅
  var msnry = new Masonry( ".recent-list ul", {
	  itemSelector: ".figure",
	  columnWidth: ".grid-sizer", 
	  percentPosition: true, //columnWidth와 같이 적용되어 width에 퍼센트 적용
	  gutter: 0, //여백 옵션
  });
  
  //이미지가 로딩될 떄마다 호출
  imagesLoaded( ".recent-list ul" ).on( "progress", function() {
	  msnry.layout();
	  });

  </script>
</body>
</html>