<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, viewport-fit=cover">

	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, viewport-fit=cover">
	<meta name="format-detection" content="telephone=no, email=no, address=no">
	<meta name="keywords" content="">
	<meta name="description" content="">
	
	<title>신상품</title>
	
	<script src="http://code.jquery.com/jquery-2.2.1.min.js"></script>
	<link rel="stylesheet" type="text/css" href="../static/css/common.min.css">
	<link rel="stylesheet" type="text/css" href="../static/css/main.min.css">
	<link rel="stylesheet" type="text/css" href="../static/css/css-library.min.css">
	<link rel="stylesheet" type="text/css" href="../static/css/product.min.css">
</head>

<body>
    <div id="wrap" class="main product category">
    	
        <!-- header// -->
	    <!-- contents// -->
	    <div id="contents">
	        <div class="innercon">
	        <c:if test="${param.menu eq 'category' }">
	    	<section class="categorylist">
	    		<div class="depth">
	    			<h2>${param.pcate }</h2>
	    			<div class="path">
	    				홈 > ${categoryDTO.parentCategoryName }
	    			</div>
	    		</div>
	    		<div class="depth-sub">
	    			<ul>
	    				<li class="active full"> 전체보기 </li>
	    				<c:forEach items="${categoryDTO.categoryNames }" var="category">
	    					<li class="active">${category }</li>
	    				</c:forEach>
	    			</ul>
	    		</div>
	    	</section>
	    	</c:if>
	    	
	        <c:if test="${param.menu eq 'best' }"><h2>베스트</h2></c:if>
	        <c:if test="${param.menu eq 'sale' }"><h2>세일</h2></c:if>
	        <c:if test="${param.menu eq 'newprod' }"><h2>신상품</h2></c:if>
	        <!-- filter// -->
	        <c:if test="${param.menu ne 'best' }">
            <section class="list-filter" style="height:40px;">
                <div class="filter-wrapper">
	                <div class="form-filter">
	                    <ul class="btn-group" id="sortType">
	                        <li><a href="${contextPath }/product/list?sort=A&menu=${param.menu }"><button type="button" id="sortTypeA" class="active" onclick="sortType('A');">신상품순</button></a></li>
	                        <li><a href="${contextPath }/product/list?sort=B&menu=${param.menu }"><button type="button" id="sortTypeB" onclick="sortType('B');">인기상품순</button></a></li>
	                        <li><a href="${contextPath }/product/list?sort=C&menu=${param.menu }"><button type="button" id="sortTypeC" onclick="sortType('C');">낮은가격순</button></a></li>
	                        <li><a href="${contextPath }/product/list?sort=D&menu=${param.menu }"><button type="button" id="sortTypeD" onclick="sortType('D');">높은가격순</button></a></li>
	                    </ul>
	                </div>
	            </div>
            </section>
            </c:if>
            
	        <ul class="product-list" id="ulItemList">
	        <c:if test="${!empty productList }">
	        	<c:forEach items="${productList }" var="productDTO">
	        	<li>
	        		<a href="${contextPath }/product/detail?pid=${productDTO.productId }">
	        			<span class="thumb">
	        				<img src="../static/images/product/${productDTO.imageUrl }.jpg" alt=""/>
	        				<c:if test="${productDTO.discountRate != 0 }">
	        				<div class="badgewrap">
					            <span class="badge"><strong> ${productDTO.discountRate }% </strong></span>
					        </div>
					        </c:if>
	        			</span>
	        			<strong class="txt-ti ellipsis">${productDTO.productName}</strong>
	        		</a>
	        		<span class="info">
                        <span class="txt-price">
                        	<strong><em>${productDTO.discountPrice }</em>원</strong>
                        	<c:if test="${productDTO.discountRate > 0 }">
                        		<del>${productDTO.productPrice }</del>
                        	</c:if>
                        </span>
                        <button type="button" class="btn-cart" onclick="fnProductBasketAdd('01', 'S02203099573','105006','100873');">장바구니 담기</button>
                    </span>
                    <span class="tag">
                    	<c:if test="${productDTO.discountRate > 0 }">
                        	<span>세일상품</span>
                        </c:if>
                    </span> 
        		</li>
        		</c:forEach>
        	</c:if>
        	<c:if test="${empty productList }">
        	<div style="text-align:center; margin:100px;"> 상품이 없습니다. </div>
        	</c:if>
	        </ul>
	        
	        <c:if test="${param.menu ne 'best' }">
	        <div class="pagination">
	        	<c:if test="${pageInfo.beginPageNumber > pageInfo.pagePerList}">
					<a class="prev" href="<c:url value="/product/list.do?pno=${pageInfo.beginPageNumber-1}&menu=${param.menu }&sort=${param.sort }"/>">이전</a>
				</c:if>
				<c:forEach var="pno" begin="${pageInfo.beginPageNumber}" end="${pageInfo.endPageNumber}">
					<span class="num"><a href="<c:url value="/product/list.do?pno=${pno}&menu=${param.menu }&sort=${param.sort }" />">${pno}</a></span>
				</c:forEach>
				<c:if test="${pageInfo.endPageNumber < pageInfo.totalPage}">
					<a class="next" href="<c:url value="/product/list.do?pno=${pageInfo.endPageNumber + 1}&menu=${param.menu }&sort=${param.sort }"/>">다음</a>
				</c:if>
	        </div>
	        </c:if>
	        </div>
	    </div>
    <!-- //contents -->
    </div>
    <script>
    $(document).ready(function(){
    	$('#sortType${param.sort }').css('font-weight', '600');
    	var menu = "${param.menu }";
    	if (menu == "category"){
    		if("${cate }" == "${pcate }"){
    			console.log("hoho")
    			$(".full").css('font-weight', '600');
    		}else{
    			
    		}
    	}
    });
    
    </script>
</body>
</html>

