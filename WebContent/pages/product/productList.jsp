<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
	
	<title>상품보기</title>
	
	<script src="http://code.jquery.com/jquery-2.2.1.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${contextPath}/static/css/common.min.css">
	<link rel="stylesheet" type="text/css" href="${contextPath}/static/css/main.min.css">
	<link rel="stylesheet" type="text/css" href="${contextPath}/static/css/css-library.min.css">
	<link rel="stylesheet" type="text/css" href="${contextPath}/static/css/product.min.css">
	
	<style>
	.soldout{
		position: absolute;
	    top: 0;
	    left: 0;
	    width: 100%;
	    height: 100%;
	    display: flex;
	    align-items: center;
	    justify-content: center;
	    flex-direction: column;
	    text-align: center;
	    color: #101010;
	    font-size: 18px;
	    font-weight: 600;
	    background-color: rgba(255, 255, 255, .8);
	    z-index: 2;
    }
	</style>
</head>

<body>
	<jsp:include page="/header.jsp" />
    <div id="wrap" class="main product category">
	    <!-- contents// -->
	    <div id="contents">
	        <div class="innercon">
	        <c:if test="${param.menu eq 'category' }">
	    	<section class="categorylist">
	    		<div class="depth">
	    			<h2>${categoryList[0].categoryName }</h2>
	    			<div class="path">
	    				홈 > ${categoryList[1].categoryName }
	    			</div>
	    		</div>
	    		<div class="depth-sub">
	    			<ul>
	    				<li class="" id="cate${categoryList[1].categoryId }"><a href="${contextPath }/product/list.do?menu=category&cate=${categoryList[1].categoryId}&pcate=${categoryList[1].parentCategoryId}">전체보기</a> </li>
	    				
	    				<c:forEach items="${categoryList }" var="category" begin="2">
	    					<li class="" id="cate${category.categoryId }"><a href="${contextPath }/product/list.do?menu=category&cate=${category.categoryId}&pcate=${category.parentCategoryId}">${category.categoryName }</a></li>
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
	                        <li><a href="${contextPath }/product/list.do?sort=A&menu=${param.menu }&cate=${param.cate}&pcate=${param.pcate}"><button type="button" id="sortTypeA" class="active" onclick="sortType('A');">신상품순</button></a></li>
	                        <li><a href="${contextPath }/product/list.do?sort=B&menu=${param.menu }&cate=${param.cate}&pcate=${param.pcate}"><button type="button" id="sortTypeB" onclick="sortType('B');">인기상품순</button></a></li>
	                        <li><a href="${contextPath }/product/list.do?sort=C&menu=${param.menu }&cate=${param.cate}&pcate=${param.pcate}"><button type="button" id="sortTypeC" onclick="sortType('C');">낮은가격순</button></a></li>
	                        <li><a href="${contextPath }/product/list.do?sort=D&menu=${param.menu }&cate=${param.cate}&pcate=${param.pcate}"><button type="button" id="sortTypeD" onclick="sortType('D');">높은가격순</button></a></li>
	                    </ul>
	                </div>
	            </div>
            </section>
            </c:if>
            
	        <ul class="product-list" id="ulItemList">
	        <c:if test="${!empty productList }">
	        	<c:forEach items="${productList }" var="productDTO" varStatus="status">
	        	<li>
	        		<a href="${contextPath }/product/detail.do?pid=${productDTO.productId }&cid=${productDTO.companyId }">
	        			<span class="thumb">
	        			<c:if test="${productDTO.productCount == 0 }">
	        			<span class="soldout">일시품절</span>
	        			</c:if>
	        				<img src="${contextPath}/static/images/product/${productDTO.imageUrl }" alt="" onerror="${contextPath}/static/images/product/pro01.jpg"/>
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
                        <button type="button" class="btn-cart" onclick="addCartProduct(${productDTO.productId }, ${productDTO.companyId }, 1)">장바구니 담기</button>
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
					<a class="prev" href="<c:url value="/product/list.do?pno=${pageInfo.beginPageNumber-1}&menu=${param.menu }&sort=${param.sort }&cate=${param.cate }&pcate=${param.pcate }"/>">이전</a>
				</c:if>
				<c:forEach var="pno" begin="${pageInfo.beginPageNumber}" end="${pageInfo.endPageNumber}">
					<span class="num"><a href="<c:url value="/product/list.do?pno=${pno}&menu=${param.menu }&sort=${param.sort }&cate=${param.cate }&pcate=${param.pcate }" />">${pno}</a></span>
				</c:forEach>
				<c:if test="${pageInfo.endPageNumber < pageInfo.totalPage}">
					<a class="next" href="<c:url value="/product/list.do?pno=${pageInfo.endPageNumber + 1}&menu=${param.menu }&sort=${param.sort }&cate=${param.cate }&pcate=${param.pcate }"/>">다음</a>
				</c:if>
	        </div>
	        </c:if>
	        </div>
	    </div>
    <jsp:include page="/footer.jsp" />
    <!-- //contents -->
    </div>
    <script>
    $(document).ready(function(){
    	$('#sortType${param.sort }').css('font-weight', '600');
    	var menu = 'category';
    	console.log("ho")
    	if (menu == "${param.menu }"){
    		document.getElementById('cate${param.cate}').className = "active"
    	}
    });
    
    function addCartProduct(pid, cid, qty){
    	$.ajax({
    		url:'${contextPath}/cart/create.do',
    		type: 'post',
    		dataType:'json',
    		data: {
    			product_id: pid,
    			company_id: cid,
    			count : qty
    		},
    		success : function(data){
    			alert("장바구니에 담았습니다.");
    		},
    		error: function(xhr, status, error) {
        		var errorResponse = JSON.parse(xhr.responseText);
            	var errorCode = errorResponse.code;
            	var message = errorResponse.message;
     
            	alert(message);
        	}
    	});
    }
    
    </script>
</body>
</html>
