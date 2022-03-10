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
	<link rel="stylesheet" type="text/css" href="../static/css/product.min.css?ver=15">
</head>

<body>
    <div id="wrap" class="main">
        <!-- header// -->
	    <!-- contents// -->
	    <div id="contents">
	        <div class="innercon">
	        <h2>신상품</h2>
	        <!-- filter// -->
            <section class="list-filter">
                <div class="filter-wrapper">
	                <div class="form-filter">
	                    <ul class="btn-group" id="sortType">
	                        <li><a href="${contextPath }/product/list?sort=A"><button type="button" id="sortTypeA" class="active" onclick="sortType('A');">신상품순</button></a></li>
	                        <li><a href="${contextPath }/product/list?sort=B"><button type="button" id="sortTypeB" onclick="sortType('B');">인기상품순</button></a></li>
	                        <li><a href="${contextPath }/product/list?sort=C"><button type="button" id="sortTypeC" onclick="sortType('C');">낮은가격순</button></a></li>
	                        <li><a href="${contextPath }/product/list?sort=D"><button type="button" id="sortTypeD" onclick="sortType('D');">높은가격순</button></a></li>
	                    </ul>
	                </div>
	            </div>
            </section>
            
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
                        <span class="txt-price"><strong><em>${productDTO.productPrice }</em>원</strong></span>
                        <button type="button" class="btn-cart" onclick="fnProductBasketAdd('01', 'S02203099573','105006','100873');">장바구니 담기</button>
                    </span>
                    <span class="tag">
                        <span>신상품</span>
                    </span> 
        		</li>
        		</c:forEach>
        	</c:if>
	        </ul>
	        <div class="pagination">
	        	<c:if test="${beginPage > pagePerList}">
					<a class="prev" href="<c:url value="/product/list?pno=${beginPage-1}"/>">이전</a>
				</c:if>
				<c:forEach var="pno" begin="${beginPage}" end="${endPage}">
					<span class="num"><a href="<c:url value="/product/list?pno=${pno}" />">${pno}</a></span>
				</c:forEach>
				<c:if test="${endPage < totalPageCount}">
					<a class="next" href="<c:url value="/product/list?pno=${endPage + 1}"/>">다음</a>
				</c:if>
	        </div>
	        <!-- //list -->
	        </div>
	    </div>
    <!-- //contents -->
    </div>
    <script>
    $(document).ready(function(){
    	$('#sortType${param.sort }').css('font-weight', '600');
    });
    </script>
</body>
</html>

