<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<c:set var="contextPath" value="${pageContext.request.contextPath}"
	scope="page" />
<c:set var="errorMessage" value="${param.errorMsg}" scope="page" />
<c:set var="normalCartList" value="${param.normalCartList}" scope="page" />
<c:set var="soldOutCartList" value="${param.soldOutCartList}"
	scope="page" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<!-- 공통 CSS -->
<link rel="stylesheet" type="text/css"
	href="${contextPath}/static/css/common/common.min.css">
<link rel="stylesheet" type="text/css" href="${contextPath}/static/css/shoppingcart.css" />	
<!-- 공통 JS / jquery 라이브러리 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="${contextPath}/static/js/function.min.js"></script>
<script type="text/javascript" src="${contextPath}/static/js/jquery-library.min.js"></script>


<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>장바구니</title>
</head>
<body>
	<script type="text/javascript">
		function gfn_appendComma(nNumber,nDetail) {
	    	if (nNumber == null)    return "";
	    	if (nDetail == null)    nDetail = 0;

	    	nNumber             = parseFloat(nNumber);
	    	nNumber             = Math.round(nNumber, nDetail);
	    
	    	var minusFlag = false;
	    	if(nNumber < 0) {
	    		nNumber = nNumber *-1;
	    		minusFlag = true;
	    	}
	    
	    	var strNumber       = new String(nNumber);
	    	var arrNumber       = strNumber.split(".");
	    	var strFormatNum    = "";
	    	var j = 0;
	
	    	for (var i = arrNumber[0].length - 1; i >= 0; i--) {
	        	if (i != strNumber.length && j == 3) {
	            	strFormatNum = arrNumber[0].charAt(i) + "," + strFormatNum;
	            	j = 0;
	        	} else {
	            	strFormatNum = arrNumber[0].charAt(i) + strFormatNum;
	        	}
	        	j++;
	    	}

	    	if (arrNumber.length > 1)   strFormatNum = strFormatNum + "." + arrNumber[1];
	    
	    	if (minusFlag) strFormatNum = '-'+strFormatNum ;

	    		return strFormatNum;
		}
	</script>
	<script type="text/javascript">
		$(document).ready(function() {
			var errorMessage = <%= request.getAttribute("errorMsg") %>;
			if (errorMessage != null) {
				alert(errorMessage);
			}
		
		});
		
		

		$(document).ready(function() {
		
			window.setTimeout(calculateSellPrice, 500);
			bindCartList();

		});
		
		/*주문 내역*/
		function calculateSellPrice() {
			var totalNormalPrice = 0; //총 상품금액
			var toalDcPrice = 0; //총 할인 금액
			var totalPayPrice = 0; //총 결제예상금액
			var selectedItemCount = 0;
			
			var totOrdQty = 0; //총 주문수량 합
			var skuItemCheckInfo = []; //체크된 상품 정보
			
			var checkedLength = $("input:checkbox[name=itemSelect]:checked").length;
			$("input:checkbox[name=itemSelect]:checked").each(function(idx){
				selectedItemCount += 1;
				//선택된 상품 계산
				var itemObj = $(this).parents("li");
				var ordQty = Number($(itemObj).find("input[name='ordQty']").val());
			
				if(!isNaN(ordQty)) {
					//상품금액 계산(정상가)
					if(!isNaN(Number($(itemObj).find("input[name='nrmlPrc']").val()))) {
						totalNormalPrice += Number($(itemObj).find("input[name='nrmlPrc']").val()) * ordQty;
					}
					
					//할인금액 계산
					if(!isNaN(Number($(itemObj).find("input[name='dcAmt']").val()))) {
						toalDcPrice += Number($(itemObj).find("input[name='dcAmt']").val()) * ordQty;
					}
					
					//총 결제예상 금액
					if(!isNaN(Number($(itemObj).find("input[name='sellPrc']").val()))) {
						totalPayPrice += Number($(itemObj).find("input[name='sellPrc']").val()) * ordQty;
					}
				}
			});
			
			$("#emPriceFTotNrmlprice").text(gfn_appendComma(totalNormalPrice));
			$("#emPriceFTotDcAmt").text(gfn_appendComma(toalDcPrice));
			$("#emPriceFTotPayAmt").text(gfn_appendComma(totalPayPrice));
			$("#emTotalItemCnt").text(selectedItemCount);
		}
		
		/*상품영역의 판매가x수량 노출을 위한 계산*/
		function calculateItemSellPrice(itemObj, obj) {
			var sellPrice = Number($("input[name=sellPrc]", $(itemObj)).val());
			var ordQty = Number($("input[name=ordQty]", $(itemObj)).val());
			
			$(itemObj).find("span.txt-price strong em").text(gfn_appendComma(sellPrice * ordQty));
			
			if($(itemObj).find("span.txt-price del").length > 0) {
				var nrmlPrc = Number($("input[name=nrmlPrc]",$(itemObj)).val());
				$(itemObj).find("span.txt-price del").text(gfn_appendComma(nrmlPrc * ordQty));
			}
		}
		
		 function checkOrderAble() {
			var selectedItemCount = $("input:checkbox[name=itemSelect]:checked").length;
			var orderBtn = $("#orderBtn");
			if(selectedItemCount > 0) {
				
				orderBtn.removeAttr("disabled");
			} else {
				
				orderBtn.attr("disabled","disabled");
			}
			
		} 
		
		function bindCartList() {
			//전체선택 체크박스 클릭 시
			if($("input:checkbox[name=allItemSelect]")) {
				$("input:checkbox[name=allItemSelect]").click(function(e) {
					var isChecked = $(this).is(":checked");
					$("input:checkbox[name=itemSelect]").prop("checked",isChecked);
					
					//가격 재계산
					calculateSellPrice();
					
					checkOrderAble();
				});
			}
			
			//상품 선택 체크박스 클릭 시 결제금액 재계산
			if($("input:checkbox[name=itemSelect]")) {
				$("input:checkbox[name=itemSelect]").click(function(e) {
					//상품 선택 해제 시 전체선택 체크박스 선택 해제
					if(!$(this).is(":checked") && $("input:checkbox[name=allItemSelect]").is(":checked")) {
						$("input:checkbox[name=allItemSelect]").prop("checked",false);
					}
				
					//전체 상품 체크 된 경우 전체선택 체크박스 선택
					var isAllCheck = true;
					$("input:checkbox[name=itemSelect]").each(function() {
						if(!$(this).is(":checked")) {
							isAllCheck = false;
							return;
						}
					});
					
					if(isAllCheck) {
						$("input:checkbox[name=allItemSelect]").prop("checked",true);
					}
					
					//가격 재계산
					calculateSellPrice();
					
					checkOrderAble();
				});
			}
		}
		
		
		/* 주문수량변경(더하기) */
		function changeOrdQtyUp(obj) {
			
		    
		    var itemObj = $(obj).parents("li");

		    var ordQty = Number($(obj).parent().find("input[name=ordQty]").val());
		  
		    var cartId = Number(itemObj.find("input:checkbox[name=itemSelect]").val());
		    
		    
		    
		    var data = {
				cart_id : cartId,
				count : 1
			};
		    
		    $.ajax({
		            type: "POST",
		            url: "${contextPath}" + "/cart/addCount.do",
		            data: data,
		            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		            dataType: "json",
		            success : function(data) {
		            	ordQty++;
		     
		            	$(obj).parent().find("input[name=ordQty]").val(ordQty);
		            	
		                calculateItemSellPrice(itemObj, obj);
		                calculateSellPrice();
		                
		            }, 
		            error: function(xhr, status, error) {
		            	var errorResponse = JSON.parse(xhr.responseText);
		            	var errorCode = errorResponse.code;
		            	var message = errorResponse.message;
		     
		            	
		                alert(message);
		            }
		    });
		}
		
		/* 주문수량변경(빼기)) */
		function changeOrdQtyDown(obj) {
		   
		    
		    var itemObj = $(obj).parents("li");

		    var ordQty = Number($(obj).parent().find("input[name=ordQty]").val());
		  
		    var cartId = Number(itemObj.find("input:checkbox[name=itemSelect]").val());
		    
		    
		   
		    
		    var data = {
				cart_id : cartId,
				count : 1
			};
		    
		    $.ajax({
		            type: "POST",
		            url: "${contextPath}" + "/cart/minusCount.do",
		            data: data,
		            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		            dataType: "json",
		            success : function(data) {
		            	ordQty--;
		            	$(obj).parent().find("input[name=ordQty]").val(ordQty);
		                calculateItemSellPrice(itemObj, obj);
		                calculateSellPrice();
		                
		            }, 
		            error: function(xhr, status, error) {
		            	var errorResponse = JSON.parse(xhr.responseText);
		            	var errorCode = errorResponse.code;
		            	var message = errorResponse.message;
		     
		            	
		                alert(message);
		            }
		    });
		}
		
		
		/*바로 구매(단품)*/
		function orderProduct(obj) {
			var itemObj = $(obj).parents("li");
			
			/* var form_data = $("#frmCartInfo").serialize();
			console.log(form_data);*/			
			var ordQty = Number($(obj).parent().find("input[name=ordQty]").val());
			var cartId = Number(itemObj.find("input:checkbox[name=itemSelect]").val());
			
			var data = {
				itemSelect: cartId
			}
			$.ajax({
				type: "POST",
				url: "${contextPath}" + "/order/orderCartProducts.do",
				data: data,
				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	            dataType: "json",
	            success : function(data) {
	           		alert("주문이 완료되었습니다.");
	         		location.reload();
	               /*  calculateItemSellPrice(itemObj, obj);
	                calculateSellPrice(); */
	            }, 
	            error: function(xhr, status, error) {
	            	var errorResponse = JSON.parse(xhr.responseText);
	            	var errorCode = errorResponse.code;
	            	var message = errorResponse.message;
	     
	            	
	                alert(message);
	            }
			});
		}
		
		
		/*상품 삭제(단일)*/
		function deleteCart(obj) {
			var itemObj = $(obj).parents("li");
			var cartId = Number(itemObj.find("input:hidden[name=cartId]").val());
			
			var data = {
				cart_id: cartId
			}
			var deleted = confirm("정말 장바구니를 삭제하겠습니까?");
			
			if(deleted) {
				$.ajax({
					type: "POST",
					url: "${contextPath}" + "/cart/delete.do",
					data: data,
					contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		        	dataType: "json",
		        	success : function(data) {
		        		alert("장바구니가 삭제되었습니다.")
		        		location.reload();
		                
		        	}, 
		        	error: function(xhr, status, error) {
		        		var errorResponse = JSON.parse(xhr.responseText);
		            	var errorCode = errorResponse.code;
		            	var message = errorResponse.message;
		     
		            	alert(message);
		        	}
				});
				
			}
		}
		
		/*선택한 항목 모두 삭제*/
		function deleteSelectedCarts() {
			var cartIds = $("#nrmlProds").find("input:checkbox[name=itemSelect]:checked");
			if(cartIds.length == 0) {
				alert("선택된 장바구니가 없습니다.");
				return;
			}
			cartIds.each(function() {
				var cartId = $(this).val();
				console.log(cartId);
				(deleteCart(this))();
			});
		}
		
		/*장바구니 주문 validation*/
		function orderSelectedProducts() {
			
			var form_data = $("#frmCartInfo").serialize();
			
			$.ajax({
				type: "POST",
				url: "${contextPath}" + "/order/orderCartProducts.do",
				data: form_data,
				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	        	dataType: "json",
	        	success : function(data) {
	        		alert("주문이 완료되었습니다.");
	        		location.reload();
	                
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
	<header id="header">
		<div class="inner-header">
			<div class="logo">
				<a href="${contextPath}/">
				<img
					src="${contextPath}/static/images/assets/header_logo_freex34.png" />
					</a>
			</div>
			<div class="util">
				<a href="#">로그아웃</a> <a href="${contextPath}/mypage/info">마이페이지</a>
			</div>
		</div>
	</header>
	<section id="shopping-cart-list" class="contents">
		<div class="inner-content">
			<h2>장바구니</h2>
			<div class="cart-select">
				<label> <input type="checkbox" checked="checked"
					name="allItemSelect" /> <span> 전체선택 </span>
				</label>
			</div>
			<form id="frmCartInfo">
				<c:if test="${empty normalCartList and empty soldOutCartList}">
				 <div class="nodata">
                     장바구니에 담긴 상품이 없습니다.
                </div>
                </c:if>
                <c:if test="${not empty normalCartList or not empty soldOutCartList}">
				<!--start 장바구니 목록 -->
				<fieldset class="my-list-field">
					<legend class="tit">
						일반구매 <em>${fn:length(normalCartList)}</em>
					</legend>
					<div class="cont">
						<ul class="my-product-list" id="nrmlProds">
							<c:forEach var="item" items="${normalCartList}">
								<li>
									<input type="hidden" name="companyId" value="${item.companyId}" />
									<input type="hidden" name="cartId" value="${item.cartId}" />
									<input type="hidden" name="productId" value="${item.productId}" />
									<button type="button" class="btn-del" onclick="deleteCart(this)">삭제</button> 
									<label class="thumb"> 
										<input type="checkbox" name="itemSelect" checked="checked" value="${item.cartId}" /> 
										
										<span> 
											<img src="${item.imgUrl}" alt="${item.productName}" />
										</span>
									</label>
									<div class="contr">
										<a href="#"> <strong class="txt-ti ellipsis">${item.productName}</strong>
										</a> <span class="info">
											<div class="ea-area">
												<input type="number" title="수량 입력" name="ordQty" value="${item.cartCount}" readonly="readonly">
												<button type="button" class="btn-down" onclick="changeOrdQtyDown(this)">수량 낮추기</button>
												<button type="button" class="btn-up" onclick="changeOrdQtyUp(this)">수량 올리기</button>
											</div> 
											<span class="txt-price"> 
												<strong> 
													<em>
													<fmt:formatNumber
															value="${item.productDisCountedPrice}" /></em>원
												</strong> 
												<c:if
													test="${item.productDisCountedPrice != item.productPrice}">
													<del>
														<fmt:formatNumber value="${item.productPrice}" />
													</del>
												</c:if>
												<input type=hidden name="nrmlPrc" value="${item.productPrice}"/>
												<input type=hidden name="sellPrc" value="${item.productDisCountedPrice}" />
												<input type=hidden name="dcAmt" value="${item.productPrice - item.productDisCountedPrice}" />
										</span>
											<div class="probtn">
												<button type="button" class="btn btn-buy" onclick="orderProduct(this)">바로구매</button>
											</div>
										</span>
									</div>
								</li>
							</c:forEach>

						</ul>

					</div>
					
				</fieldset>
				</c:if>
				<!--end 장바구니 목록-->

				<c:if test="${not empty soldOutCartList}">
					<!--start 품절 상품 목록-->
					<fieldset class="my-list-field">
						<legend class="tit">
							품절상품 <em>${fn:length(soldOutCartList)}</em>
						</legend>
						<div class="cont">
							<ul class="my-product-list" id="soldOutProds">
								<c:forEach var="item" items="${soldOutCartList}">
									<li data-soldoutyn="Y">
										<input type="hidden" name="companyId" value="${item.companyId}" />
										<input type="hidden" name="cartId" value="${item.cartId}" />
										<input type="hidden" name="productId" value="${item.productId}" />
										<button type="button" class="btn-del" onclick="deleteCart(this)">삭제</button> 
										<label class="thumb"> 
											<span class="sold-out"> 품절 </span> 
											<span>
												<img src="${item.imgUrl}" alt="${item.productName}" />
											</span>
										</label>
										<div class="contr">
											<a href="#">
												<strong class="txt-ti ellipsis">${item.productName}</strong>
											</a> 
											<span class="info"> 
												<span class="txt-price"> 
													<strong>
														<em>${item.productPrice}</em>원
													</strong>
												</span>
											</span>
										</div>
									</li>
								</c:forEach>
							</ul>
						</div>
					</fieldset>
					<!--end 품절 상품 목록-->
				</c:if>
				
				
				<div class="rightarea">
					<!--start 결제 내역 필드-->
					<fieldset class="price-field">
						<legend style="position: absolute !important; left: -9999px">결제내역</legend>
						<dl class="orderprice">
							<dt>총 상품금액</dt>
							<dd>
								<strong> <em id="emPriceFTotNrmlprice">0</em>원
								</strong>
							</dd>
						</dl>
						<dl class="minus">
							<dt>총 할인금액</dt>
							<dd>
								<strong> <em id="emPriceFTotDcAmt">0</em>원
								</strong>
							</dd>
						</dl>
						<dl class="total">
							<dt>총 결제예상금액</dt>
							<dd>
								<strong> <em id="emPriceFTotPayAmt">0</em>원
								</strong>
							</dd>
						</dl>
					</fieldset>
					<!--end 결제 내역 필드-->
					<button id="orderBtn" type="button" class="btn btn-order" onclick="orderSelectedProducts()">
						주문하기 <em id="emTotalItemCnt">0</em>
					</button>
				</div>
			</form>
		</div>
	</section>

	<jsp:include page="../../footer.jsp" flush="false" />
</body>
</html>