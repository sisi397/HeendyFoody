<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="ko">

<head>
    <!-- 새벽배송 공통 태그-->
    <meta charset="UTF-8">
    
    <title>신상품</title>

	<script src="http://code.jquery.com/jquery-2.2.1.min.js"></script>

    <link rel="stylesheet" type="text/css" href="../static/css/product.min.css">
    <link rel="stylesheet" type="text/css" href="../static/css/common.min.css">
    <link rel="stylesheet" type="text/css" href="../static/css/main.min.css">
    <link rel="stylesheet" type="text/css" href="../static/css/css-library.min.css">
    <link rel="stylesheet" type="text/css" href="../static/css/s-style_v2.min.css">
    
    <script type="text/javascript" src="../static/js/jquery-library.min.js"></script>
    <script type="text/javascript" src="../static/js/function.min.js"></script>
</head>

<body>
<!-- contents// -->
<div id="wrap" class="product detail">
    <div id="contents">
        <div class="innercon">
            <section class="proinfo-area">
                <!-- propic// -->
                <div class="propic">
                    <div class="propicbig">
                        <!-- 이미지가 있을 경우 첫번째 이미지를 넣어줌. -->
                        <img data-zoom-image="../static/images/product/${productDTO.imageUrl }" src="../static/images/product/${productDTO.imageUrl }">
                        
                    </div>
                    
                    <div class="propicsmall">
                        <div class="swiper-pagination-propic"><span class="current">1</span> / <span class="total">1</span></div>
                        <div class="swiper-button-next-propic"></div>
                        <div class="swiper-button-prev-propic"></div>
                        
                        <div class="swiper-container propicsmallswiper" style="opacity: 1">
                            <div id="P_picSmall" class="swiper-wrapper" style="display:inline-block">
                                <div class="swiper-slide">
                                    <a class="active" href="#" data-image="../static/images/product/${productDTO.imageUrl }" data-zoom-image="../static/images/product/${productDTO.imageUrl }">
                                    <img src="../static/images/product/${productDTO.imageUrl }">
                                    </a>
                                </div>           
                            </div>
                        </div>
                    </div>
                </div>
                <!-- //propic -->
                
                <!-- proinfo// -->
                <div class="proinfo">
                    <h2>
                        <strong>${product.productName }</strong>
                        <small></small>
                    </h2>
                    
                    <c:if test="${product.discountRate > 0 }">
                    <div class="tag">
                        <span>세일상품</span>
                    </div>
                    </c:if>
                    
                    <div class="brandwrap" id="brand_section">
                        <a href="#">${product.companyName }</a>
                    </div>
                    <div class="price" id="price_section">
                    	<c:if test="${product.discountRate != 0 }">
                    	<span class="txt-sale">
                    		<em>${product.discountRate }</em>%
                    	</span>
                    	</c:if>
                    	<span class="txt-price">
                    		<strong><em>${product.discountPrice }</em>원</strong>
                    		<div class="popinline"></div>
                    		<c:if test="${product.discountRate != 0 }">
                    		<del>${product.productPrice }</del>
                    		</c:if>
                    	</span>
                    	
                    	<div class="probtn">
                    		<button type="button" id="wish" class="btn-wish" onclick="wishUpdate()">
                    		좋아요
                    		</button>
                    	</div>
                    </div>

                    <div class="detailinfo">
                        <dl>
                        	<dt>원산지</dt>
                        	<dd>국내산</dd>
                        	<dt>포장타입</dt>
                        	<dd>포장</dd>
                        	
                            <dt>배송형태</dt>
                            <dd id="deliverySection">
                                    새벽배송 / 밤 11시까지 결제 시
                                <br>
									배송비 3,500원 (5만원 이상 구매 시 무료)
                                <br>
                            </dd>
                            
                            <dt id="tagListSection1">추천태그</dt>
                            <dd id="tagListSection2">
                            	<div class="hashtag">
                            		<a href="${contextPath }/product/list?cate=${product.pcategoryId }&pcate=${product.pcategoryId }&menu=category">#${product.parentCategoryName }</a>
                            		<a href="${contextPath }/product/list?cate=${product.categoryId }&pcate=${product.pcategoryId }&menu=category">#${product.categoryName }</a>
                            	</div>
                            </dd>
                            
                            <dt>상품선택</dt>
                            <dd>
                                <!-- 상품선택// start-->
                                <div class="optionarea" id="top_optionarea">
                                    
                                    <div class="optionls">
                                        <div>
                                            <strong class="txt-ti">${product.productName }
                                                    &nbsp;&nbsp;&nbsp;(재고수량&nbsp;${product.productCount }개)
                                            </strong>
                                            <div class="ea-area">
                                                <input type="number" class="pcount" title="수량 입력" value="1" readonly>
                                                <button type="button" class="btn-down" onclick="downCount()">수량 낮추기</button>
                                                <button type="button" class="btn-up" onclick="upCount()">수량 올리기</button>
                                            </div>
                                            <span class="txt-price"><em>
                                            <input type="text" value="${product.discountPrice }" disabled style="border:none; outline:none;text-align:right;background-color:none;">
                                            </em>원</span>
                                        </div>
                                    </div>  
                                </div>
                            </dd>
                        </dl>
                    </div>
                    
                    <div class="buybutton" id="top_buybutton">
                    	<p class="txt-total">총 금액 <strong><em>${product.discountPrice }</em>원</strong></p>
                        <c:if test="${product.productCount eq 0 }">
                        <div class="btns">
                        	<button type="button" class="btn darkgray bigger btn-buy" onclick="addCartProduct()">장바구니 넣어두기</button>
                            <button type="button" class="btn fill gray bigger btn-buy" onclick="fnPDPopWeightingNight('#p_popWeightingNight');">재입고 알림 신청</button>                                                            
                        </div> 
                        </c:if>
                        <c:if test="${product.productCount ne 0 }">
                        <div class="btns">
                        	<button type="button" class="btn orange bigger btn-buy" onclick="addCartProduct()">장바구니</button>
                        	<button type="button" class="btn fill orange bigger btn-buy" onclick="buyProduct()">바로구매</button>
                        </div>               
                        </c:if>
                    </div>
                </div>
                <!-- //proinfo -->
            </section>
            
            <div class="prodetailcont">
                <div class="prodetail-area">
                    <!-- tabs// -->
                    <div class="tab-menu protabs">
                        <a href="#p_proDetail" class="active"><span>상세정보</span></a>
                        <a href="#p_proBuyinfo"><span>구매정보</span></a>
                        <a href="#p_cancel"><span>취소/교환/반품</span></a>
                        <a href="#p_proReview"><span>리뷰 <em id="reviewCnt">
                        </em></span></a>
                    </div>
                    <!-- //tabs -->            
                
                    <!-- 상품상세// -->
                    <section id="p_proDetail" class="tab-contents prodetail active">
                        <h3 class="hide">상품상세</h3>
                        <img width="0" height="0" style="border:0px;" src="../static/images/product/${productDTO.imageUrl }">
                            <div class="detailcont">
                                <div style="width: 100%;margin: auto; max-width: 840;">
                                <h1 style="text-align:center">${product.productName }</h1>
                                <img class="s-lazy s-loaded" src = "../static/images/product/${productDTO.imageUrl }">
                                <h1 style="text-align:center">상품 상세 입니다.</h1>
                                </div>
                            </div>                        
                    </section>
                    <!-- //상품상세 -->
                    
                    <%@ include file="./buyInfo.jsp" %>
                    
                    <%@ include file="./exchangeInfo.jsp" %>
                    
                    <%@ include file="./review.jsp" %>  
                </div>
                
                <div class="rightarea" id="bottom_rightarea">
                    <!-- 상품 선택 start// -->
                    <div class="optionarea">
	                    <div class="optionls">
	                        <div>
	                            <strong class="txt-ti">${product.productName }
	                                    <br>(남은 수량 ${product.productCount }개)
	                            </strong>
	                            <div class="ea-area">
	                                <input type="number" class="pcount" title="수량 입력" value="1" readonly>
	                                <button type="button" class="btn-down" onclick="downCount()">수량 낮추기</button>
	                                <button type="button" class="btn-up" onclick="upCount()">수량 올리기</button>
	                            </div>
	                            <span class="txt-price"><em>
	                            ${product.discountPrice }
	                            </em>원</span>
	                        </div>
	                    </div>
                    </div>
                    <!-- 상품 선택 end// -->

                    <div class="buybutton">
                        <p class="txt-total">총 금액 <strong><em>${product.discountPrice }</em>원</strong></p>
                        <c:if test="${product.productCount eq 0 }">
                        <div class="btns">
	                        <button type="button" class="btn darkgray bigger btn-buy" onclick="addCartProduct()">장바구니 넣어두기</button>
	                        <button type="button" class="btn fill gray bigger btn-buy" onclick="">재입고 알림 신청</button>                                           
                        </div>
                        </c:if>
                        <c:if test="${product.productCount ne 0 }">
                        <div class="btns">
                        	<button type="button" class="btn orange bigger btn-buy">장바구니</button>
                        	<button type="button" class="btn fill orange bigger btn-buy">바로구매</button>
                        </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- //contents -->
</div>
<script>
// 시작할 때 좋아요 여부 확인 
$(document).ready(function(){
	//좋아요 여부 가져오기
	$.ajax({
		url:'${contextPath}/wish/check.do?productId=${param.pid }&compainyId=${param.cid }',
		type: 'get',
		dataType:'json',
		success : function(data){
			if(data.wish === 1){
				document.getElementById('wish').className = "btn-wish active";
			}else{
				document.getElementById('wish').className = "btn-wish";
			}
		}
	});
});

function wishUpdate(){
	if(document.getElementById('wish').className === "btn-wish"){ // 좋아요가 안눌려있을 경우
		document.getElementById('wish').className = "btn-wish active";
		
		//좋아요 insert
		$.ajax({
			url:'${contextPath}/wish/insert.do',
			type: 'post',
			dataType:'json',
			data: {
				productId: ${product.productId },
				memberId: 7, // 세션에서 memberId 가져오기
				companyId: ${product.companyId },
			},
			success : function(data){
				console.log("fin");
			}
		});
		
	}else{
		document.getElementById('wish').className = "btn-wish";
		
		//좋아요 delete
		$.ajax({
			url:'${contextPath}/wish/delete.do',
			type: 'post',
			dataType:'json',
			data: {
				productId: ${product.productId },
				memberId: 7, // 세션에서 memberId 가져오기
			},
			success : function(data){
				console.log("fin");
			}
		});
	}
}

function buyProduct(){
	console.log("buy");
	$.ajax({
		url:'${contextPath}/order/orderProduct.do',
		type: 'post',
		dataType:'json',
		data: {
			product_id: ${product.productId },
			company_id: ${product.companyId },
			product_count : ${product.productCount}
		},
		success : function(data){
			alert("구매 완료하였습니다.");
		}
	});
}

function addCartProduct(){
	const pqty = document.querySelector('.pcount');
	console.log("cart");
	$.ajax({
		url:'${contextPath}/cart/create.do',
		type: 'post',
		dataType:'json',
		data: {
			product_id: ${product.productId },
			company_id: ${product.companyId },
			count : pqty.value
		},
		success : function(data){
			alert("장바구니에 담았습니다.");
		},
		error : function(err){
			alert(err.code);
		}
	});
}

// 수량 증가
function upCount(){
	const pqty = document.querySelectorAll('.pcount');
	for(var i = 0; i < pqty.length; i++){
		console.log(pqty[i].value);
		pqty[i].value = Number(pqty[i].value) + 1;
	}
	console.log(document.querySelector('.txt-total'));
}

// 수량 감소
function downCount(){
	const pqty = document.querySelectorAll('.pcount');
	for(var i = 0; i < pqty.length; i++){
		console.log(pqty[i].value);
		pqty[i].value = Number(pqty[i].value) - 1;
	}
}
</script>

</body>
</html>
