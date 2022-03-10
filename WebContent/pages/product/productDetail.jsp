<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="ko">

<head>
    <!-- 새벽배송 공통 태그-->
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, viewport-fit=cover">
    <meta name="facebook-domain-verification" content="mdeov123vj5nmzk24oy1wsyx9ukdup" />
    
    <link rel="canonical" href="https://tohome.thehyundai.com/">
    <!-- // 새벽배송 공통 태그-->
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, viewport-fit=cover">
    <meta name="format-detection" content="telephone=no, email=no, address=no">
    <meta name="keywords" content="">
    <meta name="description" content="">

    <title>신상품</title>

	<script src="http://code.jquery.com/jquery-2.2.1.min.js"></script>

    <link rel="stylesheet" type="text/css" href="../static/css/product.min.css">
    <link rel="stylesheet" type="text/css" href="../static/css/common.min.css">
    <link rel="stylesheet" type="text/css" href="../static/css/main.min.css">
    <link rel="stylesheet" type="text/css" href="../static/css/css-library.min.css">
    <link rel="stylesheet" type="text/css" href="../static/css/s-style_v2.min.css">

    <link rel="shortcut icon" type="image/x-icon" href="/UIUX/w/pjtCom/images/common/favicon.ico">
    <script type="text/javascript" src="../static/js/jquery-library.min.js"></script>
    <script type="text/javascript" src="../static/js/function.min.js"></script>

    <script type="text/javascript" src="../static/js/common.js?ver=1646546415251"></script>
    <script type="text/javascript" src="../static/js/product.search.pc.js?ver=1646546415251"></script>
    <script type="text/javascript" src="/UIUX/w/pjtCom/js/v1/main.min.js?ver=0615"></script>
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
                        <img data-zoom-image="https://tohomeimage.thehyundai.com/PD/PDImages/S/3/8/6/2801001124683_00.jpg?RS=720x864" src="https://tohomeimage.thehyundai.com/PD/PDImages/S/3/8/6/2801001124683_00.jpg?RS=720x864" onerror="this.src='/UIUX/w/pjtCom/images/common/noimage_720x864.jpg'">
                        
                    </div>
                    
                    <div class="propicsmall">
                        <div class="swiper-pagination-propic"><span class="current">1</span> / <span class="total">1</span></div>
                        <div class="swiper-button-next-propic"></div>
                        <div class="swiper-button-prev-propic"></div>
                        
                        <div class="swiper-container propicsmallswiper" style="opacity: 1">
                            <div id="P_picSmall" class="swiper-wrapper" style="display:inline-block">
                                <div class="swiper-slide">
                                    <a class="active" href="#" data-image="https://tohomeimage.thehyundai.com/PD/PDImages/S/3/8/6/2801001124683_00.jpg?RS=720x864" data-zoom-image="https://tohomeimage.thehyundai.com/PD/PDImages/S/3/8/6/2801001124683_00.jpg?RS=1500x1800">
                                    <img src="https://tohomeimage.thehyundai.com/PD/PDImages/S/3/8/6/2801001124683_00.jpg?RS=720x864" onerror="this.src='/UIUX/m/pjtCom/images/common/noimage_350x420.jpg'">
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
                    <div class="tag">
                        <span>예약배송</span>
                    </div>
                    <div class="brandwrap" id="brand_section">
                        <a href="#">후르츠사계절</a>
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
                    		::after
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
                            		<a href="">#카테고리명</a>
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
                                                <input type="number" title="수량 입력" value="1" readonly>
                                                <button type="button" class="btn-down" onclick="fnOptionEaDown(this);">수량 낮추기</button>
                                                <button type="button" class="btn-up" onclick="fnOptionEaUp(this);">수량 올리기</button>
                                            </div>
                                            <span class="txt-price"><em>
                                            ${product.productPrice }
                                            </em>원</span>
                                        </div>
                                    </div>  
                                </div>
                            </dd>
                        </dl>
                    </div>
                    
                    <div class="buybutton" id="top_buybutton">
                    	<p class="txt-total">총 금액 <strong><em>${product.productPrice }</em>원</strong></p>
                        <!-- 
                        <div class="btns">
                        	<button type="button" class="btn darkgray bigger btn-buy" onclick="fnPopupScaleOpenA(this, '#p_popCartAdd');">장바구니 넣어두기</button>
                            <button type="button" class="btn fill gray bigger btn-buy" onclick="fnPDPopWeightingNight('#p_popWeightingNight');">재입고 알림 신청</button>                                                            
                        </div> 
                        --> 
                        <div class="btns">
                        	<button type="button" class="btn orange bigger btn-buy">장바구니</button>
                        	<button type="button" class="btn fill orange bigger btn-buy">바로구매</button>
                        </div>               
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
                                11
                        </em></span></a>
                    </div>
                    <!-- //tabs -->            
                
                    <!-- 상품상세// -->
                    <section id="p_proDetail" class="tab-contents prodetail active">
                        <h3 class="hide">상품상세</h3>
                        <img width="0" height="0" style="border:0px;" src="https://tohomeca.thehyundai.com/Acceleration/Cached?pid=S12202099059&cVer=20220223093201&dv=pc&charset=utf-8&https=Y&inc_css=N&inc_js=N&tu=https%3A%2F%2Ftohome.thehyundai.com%2Ffront%2Fpd%2Fpdf%2FimgDetail.do%3FslitmCd%3DS12202099059">
                            <div class="detailcont">
                                <div style="width: 100%;margin: auto; max-width: 840;">
                                <h1 style="text-align:center">${product.productName }</h1>
                                <img class="s-lazy s-loaded" src = "https://tohomeimage.thehyundai.com/PD/PDImages/S/3/8/6/2801001124683_00.jpg">
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
	                            <strong class="txt-ti">[새벽시장] 두리향 딸기 (1kg/1단)
	                                    <br>(남은 수량 ${product.productCount }개)
	                            </strong>
	                            <div class="ea-area">
	                                <input type="number" title="수량 입력" value="1" readonly>
	                                <button type="button" class="btn-down" onclick="fnOptionEaDown(this);">수량 낮추기</button>
	                                <button type="button" class="btn-up" onclick="fnOptionEaUp(this);">수량 올리기</button>
	                            </div>
	                            <span class="txt-price"><em>
	                            39,900
	                            </em>원</span>
	                        </div>
	                    </div>
                    </div>
                    <!-- 상품 선택 end// -->

                    <div class="buybutton">
                        <p class="txt-total">총 금액 <strong><em>39,900</em>원</strong></p>
                        <!-- 수량 없을 때 
                        <div class="btns">
	                        <button type="button" class="btn darkgray bigger btn-buy" onclick="fnPopupScaleOpenA(this, '#p_popCartAdd');">장바구니 넣어두기</button>
	                        <button type="button" class="btn fill gray bigger btn-buy" onclick="fnPDPopWeightingNight('#p_popWeightingNight');">재입고 알림 신청</button>                                           
                        </div>
                        -->
                        
                        <div class="btns">
                        	<button type="button" class="btn orange bigger btn-buy">장바구니</button>
                        	<button type="button" class="btn fill orange bigger btn-buy">바로구매</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- //contents -->
</div>
<script>
// 시작할 때 좋아요 여부 확인 
var wish = ${wishIs };
$(document).ready(function(){
	if(wish === 1){
		document.getElementById('wish').className = "btn-wish active";
	}else{
		document.getElementById('wish').className = "btn-wish";
	}
	
});

function wishUpdate(){
	var path = "${contextPath}"
	if(document.getElementById('wish').className === "btn-wish"){ // 좋아요가 안눌려있을 경우
		document.getElementById('wish').className = "btn-wish active";
		
		//좋아요 insert
		$.ajax({
			url:path + '/wish/insert',
			type: 'post',
			dataType:'json',
			data: {
				productId: ${product.productId },
				memberId: 6, // 세션에서 memberId 가져오기
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
			url:path + '/wish/delete',
			type: 'post',
			dataType:'json',
			data: {
				productId: ${product.productId },
				memberId: 6, // 세션에서 memberId 가져오기
			},
			success : function(data){
				console.log("fin");
			}
		});
	}
}
</script>

</body>
</html>
