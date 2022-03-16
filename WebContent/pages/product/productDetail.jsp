<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ page import="com.heendy.utils.CookieUtils" %>

<% 
	
	String pid = request.getParameter("pid");
	CookieUtils ck = new CookieUtils();
	ck.setCookie("RECENT_VIEW_ITEMS", pid, request, response);

%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <!-- 새벽배송 공통 태그-->
    <meta charset="UTF-8">
    
    <title>신상품</title>

	<script src="http://code.jquery.com/jquery-2.2.1.min.js"></script>

    <link rel="stylesheet" type="text/css" href="${contextPath}/static/css/product.min.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/static/css/common/common.min.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/static/css/common/main.min.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/static/css/common/css-library.min.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/static/css/s-style_v2.min.css">
    
    <script type="text/javascript" src="${contextPath}/static/js/jquery-library.min.js"></script>
    <script type="text/javascript" src="${contextPath}/static/js/function.min.js"></script>
    
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
<!-- contents// -->
    
	<%@ include file="/navbar.jsp" %>
<div id="wrap" class="product detail">
    <div id="contents">
        <div class="innercon">
            <section class="proinfo-area">
                <div class="propic">
                	<!-- 상품 이미지 -->
                    <div class="propicbig">
                		<!-- 품절일 경우 이미지 -->
                    	<c:if test="${product.productCount == 0 }">
	        			<span class="soldout">일시품절</span>
	        			</c:if>
                        <img data-zoom-image="${product.imageUrl}" src="${product.imageUrl}">
                    </div>
                    <div class="propicsmall">
                        <div class="swiper-pagination-propic"><span class="current">1</span> / <span class="total">1</span></div>
                        <div class="swiper-button-next-propic"></div>
                        <div class="swiper-button-prev-propic"></div>
                        
                        <div class="swiper-container propicsmallswiper" style="opacity: 1">
                            <div id="P_picSmall" class="swiper-wrapper" style="display:inline-block">
                                <div class="swiper-slide">
                                    <a class="active" href="#" data-image="${product.imageUrl}" data-zoom-image="${product.imageUrl}">
                                    <img src="${product.imageUrl}">
                                    </a>
                                </div>           
                            </div>
                        </div>
                    </div>
                </div>
                
                <!-- 상품 정보 -->
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
                        <a href="">${product.companyName }</a>
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
                            <dd id="deliverySection">새벽배송 / 밤 11시까지 결제 시<br>배송비 3,500원 (5만원 이상 구매 시 무료)<br></dd>
                            
                            <dt id="tagListSection1">추천태그</dt>
                            <dd id="tagListSection2">
                            	<div class="hashtag">
                            		<a href="${contextPath }/product/list.do?cate=${product.pcategoryId }&pcate=${product.pcategoryId }&menu=category">#${product.parentCategoryName }</a>
                            		<a href="${contextPath }/product/list.do?cate=${product.categoryId }&pcate=${product.pcategoryId }&menu=category">#${product.categoryName }</a>
                            	</div>
                            </dd>
                            
                            <dt>상품선택</dt>
                            <dd>
                                <div class="optionarea" id="top_optionarea">
                                    <div class="optionls">
                                        <div>
                                            <strong class="txt-ti">${product.productName }
                                                    &nbsp;&nbsp;&nbsp;(재고수량&nbsp;${product.productCount }개)
                                            </strong>
                                            <div class="ea-area">
                                                <input type="number" class="pcount" title="수량 입력" value="1" readonly>
                                                <button type="button" class="btn-down" onclick="downCount(this)">수량 낮추기</button>
                                                <button type="button" class="btn-up" onclick="upCount(this)">수량 올리기</button>
                                            </div>
                        					<input type="hidden" name="totalPrc" value="${product.discountPrice }"/>
                                            <span class="txt-price total-price"><em>
                                            <fmt:formatNumber value="${product.discountPrice }" />
                                            </em>원</span>
                                        </div>
                                    </div>  
                                </div>
                            </dd>
                        </dl>
                    </div>
                    
                    <div class="buybutton" id="top_buybutton">
                    	<p class="txt-total total-price">총 금액 <strong><em><fmt:formatNumber value="${product.discountPrice}" /></em>원</strong></p>
                        <c:if test="${product.productCount eq 0 }">
                        <div class="btns">
                        	<button type="button" class="btn darkgray bigger btn-buy" onclick="addCartProduct()">장바구니 넣어두기</button>
                            <button type="button" class="btn fill gray bigger btn-buy" onclick="productalarm()">재입고 알림 신청</button>                                                            
                        </div> 
                        </c:if>
                        <c:if test="${product.productCount ne 0 }">
                        <div class="btns">
                        	<button type="button" class="btn orange bigger btn-buy" onclick="addCartProduct()">장바구니</button>
                        	<button type="button" class="btn fill orange bigger btn-buy" onclick="buyProduct(this)">바로구매</button>
                        </div>               
                        </c:if>
                    </div>
                </div>
            </section>
            
            <div class="prodetailcont">
                <div class="prodetail-area">
                    <!-- //tabs -->
                    <div class="tab-menu protabs">
                        <a href="#p_proDetail" class="active"><span>상세정보</span></a>
                        <a href="#p_proBuyinfo"><span>구매정보</span></a>
                        <a href="#p_cancel"><span>취소/교환/반품</span></a>
                        <a href="#p_proReview"><span>리뷰 <em id="reviewCnt">
                        </em></span></a>
                    </div>
                    <!-- tabs// -->            
                
                    <!-- //상품상세 -->
                    <section id="p_proDetail" class="tab-contents prodetail active">
                        <h3 class="hide">상품상세</h3>
                        <img width="0" height="0" style="border:0px;" src="${product.imageUrl }">
                            <div class="detailcont">
                                <div style="width: 100%;margin: auto; max-width: 840;">
                                <h1 style="text-align:center">${product.productName }</h1>
                                <div style="width:100%;text-align:center;">
                                <img class="s-lazy s-loaded" src = "${product.imageUrl }">
                                </div>
                                <h1 style="text-align:center">상품 상세 입니다.</h1>
                                </div>
                            </div>                        
                    </section>
                    <!-- 상품상세 // -->
                    
                    <!-- // 기타 메뉴 -->
                    <%@ include file="./buyInfo.jsp" %>
                    
                    <%@ include file="./exchangeInfo.jsp" %>
                    
                    <section id="p_proReview" class="tab-contents proreview">
                        <div class="list-top">
                            <span class="grade-star big">
                                <strong><em>작성된 리뷰가 없습니다.</em></strong>
                            </span>
                        </div>
					</section>
                    <!-- 기타 메뉴 // -->
                </div>
                
                <!-- 오른쪽 영역 : 수량입력 및 구매  -->
                <div class="rightarea" id="bottom_rightarea">
                    <div class="optionarea">
	                    <div class="optionls">
	                        <div>
	                            <strong class="txt-ti">${product.productName }
	                                    <br>(남은 수량 ${product.productCount }개)
	                            </strong>
	                            <div class="ea-area">
	                                <input type="number" class="pcount" title="수량 입력" value="1" readonly>
	                                <button type="button" class="btn-down" onclick="downCount(this)">수량 낮추기</button>
	                                <button type="button" class="btn-up" onclick="upCount(this)">수량 올리기</button>
	                            </div>
	                            <span class="txt-price total-price"><em>
	                            <fmt:formatNumber value="${product.discountPrice}" />
	                            </em>원</span>
	                        </div>
	                    </div>
                    </div>
                    
                    <div class="buybutton" style="opacity:1;visibility:visible;">
                        <p class="txt-total total-price">총 금액 <strong><em><fmt:formatNumber value="${product.discountPrice}" /></em>원</strong></p>
                        <c:if test="${product.productCount eq 0 }">
                        <div class="btns">
	                        <button type="button" class="btn darkgray bigger btn-buy" onclick="addCartProduct()">장바구니 넣어두기</button>
	                        <button type="button" class="btn fill gray bigger btn-buy" onclick="productalarm()">재입고 알림 신청</button>                                           
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
    <jsp:include page="/footer.jsp" />
</div>
<script>
// 시작할 때 좋아요 여부 확인 
	$(document).ready(function(){

		if("${sessionScope.loginUser}" != "" && "${sessionScope.loginUser.roleId}" != 1){
			
			wishCheck();
		}
	});

	//좋아요 여부 가져오기
	function wishCheck(){
		$.ajax({
			url:'${contextPath}/wish/check.do?productId=${param.pid }&companyId=${param.cid }',
			type: 'get',
			dataType:'json',
			success : function(data){
				if(data.wish === 1){
					document.getElementById('wish').className = "btn-wish active";
				}else{
					document.getElementById('wish').className = "btn-wish";
				}
			},
			error: function(xhr, status, error) {
	    		var errorResponse = JSON.parse(xhr.responseText);
	        	var errorCode = errorResponse.code;
	        	var message = errorResponse.message;
	        	
	        	alert(message);
	    	}
		});
	}
	
	// 좋아요 버튼 클릭할 경우
	function wishUpdate(){
		if(document.getElementById('wish').className === "btn-wish"){ // 좋아요가 안눌려있을 경우
			
			//좋아요 insert
			$.ajax({
				url:'${contextPath}/wish/insert.do',
				type: 'post',
				dataType:'json',
				data: {
					productId: ${product.productId },
					companyId: ${product.companyId },
				},
				success : function(data){
					document.getElementById('wish').className = "btn-wish active";
				},
				error: function(xhr, status, error) {
	        		var errorResponse = JSON.parse(xhr.responseText);
	            	var errorCode = errorResponse.code;
	            	var message = errorResponse.message;
	            	
	            	if(errorCode == "ERROR-041"){
	                	alert("로그인 후 이용해 주세요.");
	            	}else{
	            		alert(message);
	            	}
	        	}
			});
		}else{
			//좋아요 delete
			$.ajax({
				url:'${contextPath}/wish/delete.do',
				type: 'post',
				dataType:'json',
				data: {
					productId: ${product.productId },
					companyId: ${product.companyId },
				},
				success : function(data){
					document.getElementById('wish').className = "btn-wish";
				},
				error: function(xhr, status, error) {
	        		var errorResponse = JSON.parse(xhr.responseText);
	            	var errorCode = errorResponse.code;
	            	var message = errorResponse.message;
	
	            	if(errorCode == "ERROR-041"){
	                	alert("로그인 후 이용해 주세요.");
	            	}else{
	            		alert(message);
	            	}
	        	}
			});
		}
	}

	// 바로구매
	function buyProduct(obj){
		const pqty = document.querySelector('.pcount');
		$.ajax({
			url:'${contextPath}/order/orderProduct.do',
			type: 'post',
			dataType:'json',
			data: {
				product_id: ${product.productId },
				company_id: ${product.companyId },
				product_count : pqty.value
			},
			success : function(data){
				alert("구매 완료하였습니다.");
			},
			error: function(xhr, status, error) {
	    		var errorResponse = JSON.parse(xhr.responseText);
	        	var errorCode = errorResponse.code;
	        	var message = errorResponse.message;
	
	        	if(errorCode == "ERROR-041"){
	            	alert("로그인 후 이용해 주세요.");
	        	}else{
	        		alert(message);
	        	}
	    	}
		});
	}

	// 장바구니 추가
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
			error: function(xhr, status, error) {
	    		var errorResponse = JSON.parse(xhr.responseText);
	        	var errorCode = errorResponse.code;
	        	var message = errorResponse.message;
	
	        	if(errorCode == "ERROR-041"){
	            	alert("로그인 후 이용해 주세요.");
	        	}else{
	        		alert(message);
	        	}
	    	}
		});
	}

	// 수량 증가
	function upCount(obj){
		const pqty = document.querySelectorAll('.pcount');
		for(var i = 0; i < pqty.length; i++){
			pqty[i].value = Number(pqty[i].value) + 1;
		}
	
		const value = document.querySelector('.pcount').value;
		priceChange(value, 'up');
	}

	// 수량 감소
	function downCount(obj){
		const pqty = document.querySelectorAll('.pcount');
		
		if(pqty[0].value != 1){
			for(var i = 0; i < pqty.length; i++){
				pqty[i].value = Number(pqty[i].value) - 1;
			}
	
			const value = document.querySelector('.pcount').value;
			priceChange(value, 'down');
		}else{
			alert("최소 주문 수량은 1개 입니다.");
		}
	}

	// 가격 변경
	function priceChange(value, option){
		var price = document.querySelectorAll('.total-price');
		var totalPrc = Number($("input[name=totalPrc]").val())*value;
		
		if(option === 'up'){
			for(var i = 0; i < price.length; i++){
				$(price[i]).find("em").text(totalPrc);
			}
		}else{
			for(var i = 0; i < price.length; i++){
				$(price[i]).find("em").text(totalPrc);
			}
		}
	}

	// 재고 알림
	function productalarm(){
		alert("준비중 입니다.")
	}
</script>

</body>
</html>
