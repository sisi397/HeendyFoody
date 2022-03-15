<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>마이페이지 | HeendyFoody </title>
<link href="${contextPath}/static/css/common/common.min.css" rel="stylesheet" type="text/css">
<link href="${contextPath}/static/css/common/css-library.min.css" rel="stylesheet" type="text/css">
<link href="${contextPath}/static/css/common/member.min.css" rel="stylesheet" type="text/css">
<link href="${contextPath}/static/css/mypage.min.css" rel="stylesheet" type="text/css">
</head>
<style>
.soldout {
	position: absolute;
    width: 100%;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    text-align: center;
    color: #101010;
    font-size: 20px;
    font-weight: 600;
    background-color: rgba(255, 255, 255, .8);
    z-index: 2;
}
</style>
<body>
	
  <div id="wrap" class="mypage mypage main">
  <!-- header include -->
	<%@ include file="/navbar.jsp"%>
  
    <div id="contents">
	  <div class="innercon">
	  <!-- 정적 파일 sidebar include -->
	  <%@ include file="/pages/mypage/sidebar.jsp" %>
		
		<section class="conarea">
		  <!-- 사용자 관련 정보 상단 표시  -->
		  <section class="mystate-section">
		    <div class="myinfo">
		      <div class="memclass normal"> 
		        <div class="cont">
			      <strong class="name">${loginUser.memberName}님</strong>                   
		          <ul>
	           	    <li><a href="#" style="color: gray;">오늘도 즐거운 하루 되세요</a></li>
	              </ul>
		        </div>
		      </div>
		    </div>
		    <div class="point">
		      <ul>          
		        <li>
		          <a href="#">
		            <div class="inner">
		              <strong><span id="upointCurAmt">${memberPoint}</span>P</span></strong> H.Point
		            </div>
		          </a>
		        </li> 
		        <li>
		          <a href="#">
		            <div class="inner">
		              <strong><span id="upointCurAmt">${totalOrderCount}</span>개</span></strong> ORDER
		            </div>
		          </a>
		        </li>  
		        <li>
		          <a href="#">
		            <div class="inner">
		              <strong><span id="upointCurAmt">${totalWishCount}</span>개</span></strong> LIKE
		            </div>
		          </a>
		        </li>  
		        <li>
		          <a href="#">
		            <div class="inner">
		              <strong><span id="upointCurAmt">${totalRvCount}</span>개</span></strong> VIEW
		            </div>
		          </a>
		        </li> 
		        <li>
		          <a href="#">
		            <div class="inner">
		              <strong><span id="upointCurAmt"><img src="${contextPath}/static/images/common/cursor_heendy.png" alt="heendy"></span></strong> Heendy
		            </div>
		          </a>
		        </li>                                                                                                
		      </ul>
		    </div>
		  </section>
		  
		  <!-- 좋아요 목록 5개 표시 -->
		  <section class="like-section">
		    <header class="header">
		      <h4><span>좋아요 </span><strong>${totalWishCount}</strong></h4>
		      <a href="${contextPath}/mypage/wish.do?pno=1" class="btn-line">더보기</a>
		    </header>
		     
		    <!-- 좋아요 목록이 있다면 -->
		  	<c:if test="${!empty wishList}">
		      <ul class="product-list small">
		        <c:forEach items="${wishList}" var="wishDTO">
		          <li> <!-- 상품이 삭제된 경우 -->
		           	<c:if test="${wishDTO.deleted == 1}">
		              <a href="#">
			            <span class="thumb">
		              	  <span class="soldout">판매중단</span>
			              <img src="${wishDTO.imageUrl}" alt="${wishDTO.productName}">
			            </span>
	                    <strong class="txt-ti ellipsis">${wishDTO.productName}</strong>
	                  </a>
		            </c:if>
		              
		            <!-- 상품이 삭제되지 않은 경우 -->
		            <c:if test="${wishDTO.deleted != 1 }">
			          <a href="${contextPath}/product/detail.do?pid=${wishDTO.productId}&cid=${wishDTO.companyId}">
			            <span class="thumb">
			              <!-- 상품 수량이 0인 경우 -->
			              <c:if test="${wishDTO.productCount == 0}">
			                <span class="soldout">일시품절</span>
			              </c:if>
			              <img src="${wishDTO.imageUrl}" alt="${wishDTO.productName}">
			            </span>
	                    <strong class="txt-ti ellipsis">${wishDTO.productName}</strong>
	                  </a>
	                </c:if>
		            <span class="info">
		              <span class="txt-price">
		                <!-- 할인하는 경우 -->
		                <c:if test="${wishDTO.discountPrice != wishDTO.productPrice}">
			              <strong><em><fmt:formatNumber value="${wishDTO.discountPrice}"/></em>원</strong>
			              <del><fmt:formatNumber value="${wishDTO.productPrice}"/></del>       
			            </c:if>
			            <!-- 할인하지 않는 경우 -->
			            <c:if test="${wishDTO.discountPrice == wishDTO.productPrice}">
			              <strong><em><fmt:formatNumber value="${wishDTO.discountPrice}"/></em>원</strong>     
			            </c:if>
		              </span>
		            </span>   
		          </li>    
		        </c:forEach>  
		      </ul>
		    </c:if>
		     
		    <!-- 좋아요 목록이 없는 경우 --> 
		    <c:if test="${empty wishList}">
		   	  <div class="nodata">좋아요 상품이 아직 없습니다.</div>
		    </c:if>
		  </section>
		   
		  <!-- 최근 본 목록 5개 표시 -->
		  <section class="view-section">
		    <header class="header">
		      <h4><span>최근 본 상품 </span><strong>${totalRvCount}</strong></h4>
		      <a href="${contextPath}/mypage/recent_view.do" class="btn-line">더보기</a>
		    </header>
		     
		    <!-- 최근 본 상품이 있다면 -->
		    <c:if test="${!empty rvList}">
		      <ul class="product-list small">
			    <c:forEach items="${rvList}" var="rvDTO">
			      <li>
			        <!-- 상품이 삭제된 경우 -->
			        <c:if test="${rvDTO.deleted == 1}">
			          <a href="#">
			            <span class="thumb">
			            <span class="soldout">판매중단</span>
			              <img src="${rvDTO.imageUrl}" alt=${rvDTO.productName}>
			            </span>
			            <strong class="txt-ti ellipsis">${rvDTO.productName}</strong>
			          </a>
			        </c:if>
			        <!-- 상품이 삭제되지 않은 경우 -->
			        <c:if test="${rvDTO.deleted != 1 }">
			          <a href="${contextPath}/product/detail.do?pid=${rvDTO.productId}&cid=${rvDTO.companyId}">
			            <span class="thumb">
			            <!-- 상품 수량이 0인 경우 -->
			            <c:if test="${rvDTO.productCount == 0}">
			              <span class="soldout">일시품절</span>
			            </c:if>
			            <img src="${rvDTO.imageUrl}" alt=${rvDTO.productName}>
			            </span>
			            <strong class="txt-ti ellipsis">${rvDTO.productName}</strong>
			          </a>
			        </c:if>
			        <span class="info">
			          <span class="txt-price">
			            <!-- 할인하는 경우 -->
			            <c:if test="${rvDTO.discountPrice != rvDTO.productPrice}">
			              <strong><em><fmt:formatNumber value="${rvDTO.discountPrice}"/></em>원</strong>
			              <del><fmt:formatNumber value="${rvDTO.productPrice}"/></del>        
			            </c:if>
			            <!-- 할인하지 않는 경우 -->
			            <c:if test="${rvDTO.discountPrice == rvDTO.productPrice}">
			              <strong><em><fmt:formatNumber value="${rvDTO.discountPrice}"/></em>원</strong>   
			            </c:if>
				      </span>
			        </span>   
			      </li>
			    </c:forEach>                           
		      </ul>
		    </c:if>
		    <!-- 최근 본 상품이 없다면 -->
		    <c:if test="${empty rvList}">
		   	  <div class="nodata">최근 본 상품이 아직 없습니다.</div>
		    </c:if>
		  </section>
        </section>
	  </div>
    </div>
    <!-- 정적 파일 footer include -->
    <%@ include file="/footer.jsp" %>
  </div>
</body>
</html>