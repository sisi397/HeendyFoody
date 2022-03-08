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
    
    <link rel="canonical" href="https://tohome.thehyundai.com/">

	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, viewport-fit=cover">
	<meta name="format-detection" content="telephone=no, email=no, address=no">
	<meta name="keywords" content="">
	<meta name="description" content="">
	
	<title>신상품</title>
	
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
	                        <li>    
	                            <div class="popinline recominfo">
	                                <button type="button" class="icon question" onclick="fn.toggleClass('#p_popRecomInfo');">?</button>
	                                <div id="p_popRecomInfo" class="popinner">
	                                    <button type="button" class="btn-close" onclick="fn.toggleClass('#p_popRecomInfo');">닫기</button>
	                                    판매량, 사용자 선호도 등을 고려해 상품을 추천해 드리며, 일부 광고상품이 상단에 노출될 수 있습니다.
	                                </div>
	                            </div>
	                            <button type="button" class="active" onclick="fnSortType('C');">신상품순</button><!-- 활성화 : class="active" 추가 -->
	                        </li>
	                        <li><button type="button"  onclick="fnSortType('B');">인기상품순</button></li>
	                        <li><button type="button"  onclick="fnSortType('A');">추천순</button></li>
	                        <li><button type="button"  onclick="fnSortType('D');">낮은가격순</button></li>
	                        <li><button type="button"  onclick="fnSortType('E');">높은가격순</button></li>
	                    </ul>
	                </div>
	            </div>
            </section>
            
	        <ul class="product-list" id="ulItemList">
	        	<c:forEach items="${productList }" var="productDTO">
	        	<li>
	        		<a href="${contextPath }/product/detail?pid=${productDTO.productId }">
	        			<span class="thumb">
	        				<img src="${productDTO.imageUrl }" alt=""/>
	        				<div class="badgewrap"></div>
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
               <li>
                   <a href="javascript:fnProductDetailMove('S02203099573','105006','100873');">
                       <span class="thumb">
                           <img src="https://tohomeimage.thehyundai.com/PD/PDImages/S/3/7/5/S02203099573_00.jpg?RS=350x420" alt="" onerror="this.src='/UIUX/m/pjtCom/images/common/noimage_350x420.jpg'">
                            <div class="badgewrap"></div>
                        </span>
                        <strong class="txt-ti ellipsis">[루게뜨] 본파이어 그릴치즈 2종</strong>
                    </a>
                    <span class="info">
                        <span class="txt-price"><strong><em>8,500</em>원</strong></span>
                        <button type="button" class="btn-cart" onclick="fnProductBasketAdd('01', 'S02203099573','105006','100873');">장바구니 담기</button>
                    </span>
                    <span class="tag">
                        <span>신상품</span>
                    </span> 
                </li>
                <li>
                    <a href="javascript:fnProductDetailMove('S02202099307','107002','100744');">
                        <span class="thumb">
                            <img src="https://tohomeimage.thehyundai.com/PD/PDImages//S/7/0/3/8809539023489_01.jpg?RS=350x420" alt="" onerror="this.src='/UIUX/m/pjtCom/images/common/noimage_350x420.jpg'">
                            <div class="badgewrap">
					            <span class="badge"><strong>10%</strong></span>
                            </div>
                        </span>
                        <strong class="txt-ti ellipsis">[남도애꽃] 떡갈비 4종</strong>
                    </a>
                    <span class="info">
                        <span class="txt-price"><strong><em>9,900</em>원</strong><del>11,000</del></span>
                        <button type="button" class="btn-cart" onclick="fnProductBasketAdd('01', 'S02202099307','107002','100744');">장바구니 담기</button>
                    </span>
                    <span class="tag">
                        <span>신상품</span>
                    </span> 
                </li>
                <li>
                    <a href="javascript:fnProductDetailMove('S02203099561','106002','100739');">
                        <span class="thumb">
                            <img src="https://tohomeimage.thehyundai.com/PD/PDImages/S/4/8/6/8809218529684_01.jpg?RS=350x420" alt="" onerror="this.src='/UIUX/m/pjtCom/images/common/noimage_350x420.jpg'">
                            <div class="badgewrap"></div>
                        </span>
                        <strong class="txt-ti ellipsis">[고메공방] 돈까스 김치나베우동 (585g)</strong>
                    </a>
                    <span class="info">
                        <span class="txt-price"><strong><em>8,900</em>원</strong></span>
                        <button type="button" class="btn-cart" onclick="fnProductBasketAdd('01', 'S02203099561','106002','100739');">장바구니 담기</button>
                    </span>
                    <span class="tag">
                        <span>신상품</span>
		            </span> 
                </li>
                <li>
                    <a href="javascript:fnProductDetailMove('O02203061503','123003','120143');">
                        <span class="thumb">
                            <img src="https://tohomeimage.thehyundai.com/PD/PDImages/O/3/0/5/O02203061503_01.jpg?RS=350x420" alt="" onerror="this.src='/UIUX/m/pjtCom/images/common/noimage_350x420.jpg'">
                            <div class="badgewrap">
					            <span class="badge"><strong>38%</strong></span>
                                <span class="badge brand-badge"><strong>브랜드직송</strong></span>
					        </div>
                        </span>
                        <strong class="txt-ti ellipsis">[오덴세] 레고트쿡 4종세트</strong>
                    </a>
                    <span class="info">
                        <span class="txt-price"><strong><em>179,800</em>원</strong><del>290,000</del></span>
                        <button type="button" class="btn-cart" onclick="fnProductBasketAdd('01', 'O02203061503','123003','120143');">장바구니 담기</button>
                    </span>
                    <span class="tag">
                        <span>신상품</span>
                        <span>브랜드직송</span>
                    </span> 
                </li>
                <li>
                    <a href="javascript:fnProductDetailMove('O02202061300','123003','120143');">
                        <span class="thumb">
                            <img src="https://tohomeimage.thehyundai.com/PD/PDImages/O/0/0/3/O02202061300_01.jpg?RS=350x420" alt="" onerror="this.src='/UIUX/m/pjtCom/images/common/noimage_350x420.jpg'">
                            <div class="badgewrap">
                                <span class="badge"><strong>17%</strong></span>
                                <span class="badge brand-badge"><strong>브랜드직송</strong></span>
                            </div>
                        </span>
                        <strong class="txt-ti ellipsis">[오덴세] 아틀리에 노드 프리미엄 6인조</strong>
                    </a>
                    <span class="info">
                        <span class="txt-price"><strong><em>331,170</em>원</strong><del>399,000</del></span>
                        <button type="button" class="btn-cart" onclick="fnProductBasketAdd('01', 'O02202061300','123003','120143');">장바구니 담기</button>
                    </span>
                    <span class="tag">
                        <span>신상품</span>
                        <span>H.Bonus적용제외</span>
                    </span> 
                </li>
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
</body>
</html>

