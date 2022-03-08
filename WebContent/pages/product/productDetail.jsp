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
                    	<span class="txt-price">
                    		<strong><em>${product.productPrice }</em>원</strong>
                    		<span class="txt-unit">100g당 4,462원</span>
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
                                                    &nbsp;&nbsp;&nbsp;(재고수량&nbsp;0개)
                                            </strong>
                                            <span class="txt-date">배송시작일 : <em></em></span>
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
                    
                    <!-- 구매정보// -->
                    <section id="p_proBuyinfo" class="tab-contents probuyinfo">
                        <h3 class="hide"><strong>구매정보</strong></h3>
                        

                        <div class="buyinfo" id="buyinfo">
                            <div class="cont">
                                <div class="infotxt">
                                    <strong>배송안내</strong>
                                    <ul>
                                        <li>새벽배송/당일배송</li>
                                        <li>택배배송</li>
                                        <li>해외배송</li>
                                    </ul>
                                </div>
                                <div class="infotxt">
                                    <strong>교환/환불 안내</strong>
                                    <ul>
                                        <li>교환/환불 정책 표기</li>
                                        <li>정책 표기</li>
                                        <li>정책 표기</li>
                                    </ul>
                                </div>
                                <div class="infotxt">
                                    <strong>배송단계별 주문취소 안내</strong>
                                    <ul>
                                        <li>교환/환불 정책 표기</li>
                                        <li>정책 표기</li>
                                        <li>정책 표기</li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        
                        <div class="infotxt notice">
                            <ul>
                                <li>전자상거래 등에서의 소비자 보호에 관한 법률에 의한 반품규정이 판매자가 지정한 반품조건보다 우선합니다.</li>
                                <li>단, 판매자 또는 협력사가 지정한 반품조건이 소비자에게 더 유리한 경우 가장 유리한 반품조건으로 적용됩니다.</li>
                                <li>구매자가 미성년자인 경우에는 상품 구입시 법정 대리인이 동의하지 아니하면 미성년자 본인 또는 법정 대리인이 구매취소 할 수 있습니다.</li>
                                <li class="pointtxt"><a href="/front/dp/dpf/customerCenterMain.do">소비자 피해보상 처리 및 상품 등에 대한 불만처리 방법(CS)</a></li>
                            </ul>
                        </div>                    
                    </section>
                    <!-- //구매정보 -->
                    
                    <!-- 취소/교환/반품//-->
                    <section id="p_cancel" class="tab-contents probuyinfo">
                        <div class="buyinfo">
                            <h4>주문취소 안내</h4>
                            <div class="cont">
                                <div class="infotxt">
                                    <ul>
                                        <li>결제완료 이후 주문의 상태가 “상품준비중”으로 변경될 경우, 취소가 제한됩니다.</li>
                                        <li>비회원은 로그인 > 비회원주문조회에서 주문을 취소하실 수 있습니다.</li>
                                        <li>일부 예약배송, 정기배송, 정기구독 등의 예약상품은 배송 3~4일전까지만 취소하실 수 있습니다.</li>
                                        <li>주문상품의 부분취소는 불가능합니다.</li>
                                        <li>카드환불은 카드사 정책에 따르며, 취소 시 사용하신 H.Point, H.Bonus, 쿠폰 등은 모두 복원됩니다.
                                            단, 취소시 사용기한이 지난 혜택은 복원되지 않습니다.</li>
                                    </ul>
                                </div>
                            </div>
                        </div>

                        <div class="buyinfo">
                            <h4>교환/반품안내</h4>
                            <div class="cont">
                                <div class="infotxt">
                                    <p>※상품설명에 배송/교환/반품/취소 관련 안내가 기재된 경우 다음 안내사항보다 우선적용됩니다.</p>
                                    <ul>
                                        <li>상품에 문제가 있는 경우<br>
                                            : 냉장/냉동상품은 제품수령 후 최대 2일(48시간) 내,<br>
                                            상품을     촬영한 사진과 함께 1:1문의 게시판에 등록하시면,<br>
                                            담당자 확인 후 교환/반품이 진행됩니다.<br>
                                            : 상온상품(유통기한 30일 이상)및 기타상품은 제품수령 후<br>
                                            3개월 이내에 사진과 함께 1:1문의 게시판에 등록하시면,<br>
                                            담당자 확인 후 교환/반품이 진행됩니다.<br>
                                            이때 발생하는 모든 배송비는 현대식품관 투홈이 부담합니다.</li>
                                        <li>단순변심/주문착오의 경우<br>
                                            : 냉장/냉동상품은 교환/반품이 불가능합니다.<br>
                                            : 상온상품(유통기한 30일 이상)및 기타상품은 제품수령 후<br>
                                            최대 7일 이내에 사진과 함께 1:1문의 게시판에 등록하시면, 담당자 확인 후 교환/반품이 진행됩니다. 이때 발생하는 모든 배송비(교환:왕복, 반품:편도)는 고객님께서 부담하셔야 합니다.</li>
                                        <li>
                                            교환/반품이 불가한 경우<br>
                                            : 교환/반품 가능기간을 초과하였을 경우<br>
                                            : 상품 및 구성품을 사용하였거나 부주의로 인하여 상품이 훼손(파손/고장/오염 등)된 경우<br>
                                            : 상품 사용 시 상품설명에 기재된 주의사항을 지키지 않는 경우<br>
                                            : 상품택을 파손하였거나 분실했을 경우<br>
                                            : 배송 후 설치완료된 상품인 경우<br>
                                            : 기타 ‘전자상거래 등에서의 소비자보호에 관한 법률’이<br>
                                            정하는 청약철회 제한사유에 해당되는 경우
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </section>
                    <!-- //취소/교환/반품-->
                    
                    <!-- 리뷰// -->
                    <section id="p_proReview" class="tab-contents proreview">
                        <h3 class="hide"><strong>리뷰</strong></h3>
                        
                        <div class="list-top">
                            <button type="button" class="btn black btn-review" onclick="fn.addClass('#p_proReviewWrite');fn.removeClass('#p_proReview');">리뷰 작성</button>
                            
                            <span class="grade-star big">
                                <span><span style="width:90%;"></span></span>
                                <strong><em>4.5</em> <i class="bar">/</i> 5</strong>
                            </span>
                        </div>

                        <div class="list-sort">
                            <label><input type="checkbox" name="pic" id="onlyPic" data-pic-yn="N" onclick="fnReviewAjaxPcList('A', 1);"><span>포토 리뷰</span></label>
                            <div class="form-sort" id="reviewImgYn">
                                <label><input type="radio" name="sort" value = '1' onclick="fnReviewAjaxPcList('A', 1);"><span>베스트순</span></label>
                                <label><input type="radio" name="sort" value = '2' checked onclick="fnReviewAjaxPcList('A', 1);"><span>최신순</span></label>
                            </div>
                        </div>
                        <div class="review-list">
                        </div>
                    </section>
                    <!-- //리뷰 -->

                    <section id="p_proReviewWrite" class="tab-contents prowrite review">
                        <h3>리뷰작성</h3>
                        <div class="product-list vertical">
                            <span class="thumb">
                                <img src="" alt="" id="popReviewWriteImg">
                            </span>
                            <!-- data-no: 글번호, data-cd:패키지일 경우 상품코드,  data-ord-no: 주문번호-->
                            <div class="contr" data-no="" data-cd="" data-ord-no="">
                                <strong class="txt-ti"></strong>
                                <span class="txt-option"></span>
                            </div>
                        </div>

                        <form id="fileForm" method="post" enctype="multipart/form-data">
                            <fieldset>
                                <legend class="hide">리뷰작성</legend>
                                <input type="hidden" name="itemEvalAtclNo" value="">
                                <input type="hidden" name="ordNo" value="">
                                <input type="hidden" name="slitmCd" value="">
                                <input type="hidden" name="optItemCd" value="">
                                <input type="hidden" name="pckgItemYn" value="">    
                                <input type="hidden" name="itemEvalScrg" id="itemEvalScrg" value="">
                                <input type="hidden" name="itemEvalCntn" id="itemEvalCntn" value="">
                                <input type="hidden" name="imgCnt" id="imgCnt" value="">    
                                <input type="hidden" name="imgYn1" id="imgYn1" value="">            
                                <input type="hidden" name="imgYn2" id="imgYn2" value="">            
                                <input type="hidden" name="imgYn3" id="imgYn3" value="">    
                                <input type="hidden" name="pathType1" id="pathType1" value="">  
                                <input type="hidden" name="pathType2" id="pathType2" value="">
                                <input type="hidden" name="atflNo" id="atflNo" value="">
                                
                                <div class="write-area">
                                    <div class="reviewstar">
                                        <div class="grade-star big active">
                                            <span class="active" id="start1">1점</span>
                                            <span class="active" id="start2">2점</span>
                                            <span class="active" id="start3">3점</span>
                                            <span class="active" id="start4">4점</span>
                                            <span class="active" id="start5">5점</span>
                                            <input type="hidden" name="starV" value="5">
                                            <div class="txt" id='totstart'><em class="tot">5</em>/<em>5</em></div>
                                        </div>
                                    </div>
                                    
                                    <div class="form-default horizon form-file" id="expsY2">
                                        <strong>사진 첨부</strong>
                                        <div class="upload-file">
                                            <label><input type="file" class="upload-hidden" title="사진 첨부" name="uploadImg" id="uploadImg1" accept="image/*"></label>
                                        </div>
                                        <div class="upload-file">
                                            <label><input type="file" class="upload-hidden" title="사진 첨부" name="uploadImg" id="uploadImg2" accept="image/*"></label>
                                        </div>
                                        <div class="upload-file">
                                            <label><input type="file" class="upload-hidden" title="사진 첨부" name="uploadImg" id="uploadImg3" accept="image/*"></label>
                                        </div>
                                    </div>
                                    
                                    <label class="form-counter" id="expsY1">
                                        <textarea title="리뷰 입력" placeholder="최소 글자수는 10자 이상입니다. 고객님의 취향과 경험을 좀 더 자세히 공유해주세요." name="reviewTextarea" id="reviewTextarea" onKeyup="javascript:fnReviewTextareaLimit(this, 500, 'B');"></textarea>
                                        <span class="counter"><em>0</em> /500 자</span>
                                    </label>
                                    <p class="txt-star" id="expsN1">
                                                                개인별 주관적인 의견으로 인해 상품의 기능 및 효과에 대한
                                                                오해의 소지가 있을 수 있어 평점만 선택 가능합니다.
                                    </p>                                
                                </div>

                                <div class="btns">
                                    <button type="button" class="btn gray middle btn-cancel" onclick="fn.addClass('#p_proReview');fn.removeClass('#p_proReviewWrite');">취소</button>
                                    <button type="button" class="btn fill black middle btn-confirm" onclick="fnReviewSave();">확인</button>
                                </div>
                            </fieldset>
                        </form>

                        <div class="infotxt">
                            <strong>리뷰 작성 안내</strong>
                            <ul>
                                <li id="expsY3">권한<br>현대식품관에서 구입한 상품만 가능하며, 배송 완료일 기준 90일까지 작성하실 수 있습니다.</li>
                                <li id="expsY4">혜택<br>리뷰 작성 혜택은 H.Point로 지급되며 H.Point 통합회원이 아닌 경우 지급받으실 수 없습니다.</li>
                                <li id="expsY5">텍스트 리뷰 50P / 포토 리뷰 150P</li>
                                <li id="expsY6">리뷰<br>아래 내용에 해당하는 사유라고  판단되는 경우 작성자 동의 없이 미전시할 수 있으며, 지급된 포인트는 회수됩니다.</li>
                                <li id="expsY7">부적합한 내용의 작성(허위 사실, 욕설, 비난, 일반식품의 효능, 효과, 해석 불가능한 리뷰, 타 상품에 대한 리뷰 등) 타인의  권리 혹은 개인정보 침해 우려가 있는 경우 (캡처. 제3자 사진 도용 등) 리뷰 작성 후 반품</li>
                                
                                <li id="expsN2">리뷰 작성 후 반품 시 지급된 리뷰 포인트는 회수됩니다.</li>
                                <li id="expsN3">포인트는 H.Point로 지급되며 H.Point가 없으신 경우 지급받을 수 없습니다.</li>
                            </ul>
                        </div>
                    </section>
                    <!-- //리뷰작성 -->
                </div>
                
                <div class="rightarea" id="bottom_rightarea">
                    <!-- 상품 선택 start// -->
                    <div class="optionarea">
	                    <div class="optionls">
	                        <div>
	                            <strong class="txt-ti">[새벽시장] 두리향 딸기 (1kg/1단)
	                                    <br>(남은 수량 0개)
	                            </strong>
	                            <span class="txt-date">배송시작일 : <em></em></span>
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
function wishUpdate(){
	if(document.getElementById('wish').className === "btn-wish"){ // 좋아요가 안눌려있을 경우
		document.getElementById('wish').className = "btn-wish active";
	
		//좋아요 insert
		$.ajax({
			url:${contextPath } + '/wish/insert',
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
			url:${contextPath } + '/wish/delete',
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
