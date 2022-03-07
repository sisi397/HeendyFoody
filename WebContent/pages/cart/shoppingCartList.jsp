<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/shoppingcart.css"/>

<title>장바구니</title>
</head>
<body>
	<header id="header">
		<div class="inner-header">
			<div class="logo">
				<img src="${pageContext.request.contextPath}/static/images/assets/header_logo_freex34.png" />
			</div>
			<div class="util">
				<a href="#">로그아웃</a> <a href="#">마이페이지</a>
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
				<ul class="btn-group">
					<li>
						<button type="button">삭제</button>
					</li>
					<li>
						<button type="button">품절삭제</button>
					</li>
				</ul>
			</div>
			<form id="frmCartInfo">
				<!--start 장바구니 목록 -->
				<fieldset class="list-field">
					<legend class="tit">
						일반구매 <em>10</em>
					</legend>
					<div class="cont">
						<ul class="product-list">
							<li>
								<button type="button" class="btn-del">삭제</button> <label
								class="thumb"> <input type="checkbox" name="itemSelect"
									checked="checked" value="상품ID" /> <span> <img
										src="${pageContext.request.contextPath}/static/images/sample/sample_product.jpg" alt="상품이름" />
								</span>
							</label>
								<div class="contr">
									<a href="#"> <strong class="txt-ti ellipsis">[하나칼국수]
											알곤이 칼국수 (2인분)</strong>
									</a> <span class="info">
										<div class="ea-area">
											<input type="number" title="수량 입력" name="ordQty" value="1"
												readonly="readonly">
											<button type="button" class="btn-down">수량 낮추기</button>
											<button type="button" class="btn-up">수량 올리기</button>
										</div> <span class="txt-price"> <strong> <em>19,000</em>원
										</strong>
									</span>
										<div class="probtn">
											<button type="button" id="btnWish" class="btn-wish">선물하기</button>
											<button type="button" class="btn btn-buy">바로구매</button>
										</div>
									</span>
								</div>
							</li>

							<li>
								<button type="button" class="btn-del">삭제</button> <label
								class="thumb"> <input type="checkbox" name="itemSelect"
									checked="checked" value="상품ID" /> <span> <img
										src="${pageContext.request.contextPath}/static/images/sample/sample_product.jpg" alt="상품이름" />
								</span>
							</label>
								<div class="contr">
									<a href="#"> <strong class="txt-ti ellipsis">[하나칼국수]
											알곤이 칼국수 (2인분)</strong>
									</a> <span class="info">
										<div class="ea-area">
											<input type="number" title="수량 입력" name="ordQty" value="1"
												readonly="readonly">
											<button type="button" class="btn-down">수량 낮추기</button>
											<button type="button" class="btn-up">수량 올리기</button>
										</div> <span class="txt-price"> <strong> <em>19,000</em>원
										</strong>
									</span>
										<div class="probtn">
											<button type="button" id="btnWish" class="btn-wish">선물하기</button>
											<button type="button" class="btn btn-buy">바로구매</button>
										</div>
									</span>
								</div>
							</li>

							<li>
								<button type="button" class="btn-del">삭제</button> <label
								class="thumb"> <input type="checkbox" name="itemSelect"
									checked="checked" value="상품ID" /> <span> <img
										src="${pageContext.request.contextPath}/static/images/sample/sample_product.jpg" alt="상품이름" />
								</span>
							</label>
								<div class="contr">
									<a href="#"> <strong class="txt-ti ellipsis">[하나칼국수]
											알곤이 칼국수 (2인분)</strong>
									</a> <span class="info">
										<div class="ea-area">
											<input type="number" title="수량 입력" name="ordQty" value="1"
												readonly="readonly">
											<button type="button" class="btn-down">수량 낮추기</button>
											<button type="button" class="btn-up">수량 올리기</button>
										</div> <span class="txt-price"> <strong> <em>19,000</em>원
										</strong>
									</span>
										<div class="probtn">
											<button type="button" id="btnWish" class="btn-wish">선물하기</button>
											<button type="button" class="btn btn-buy">바로구매</button>
										</div>
									</span>
								</div>
							</li>

							<li>
								<button type="button" class="btn-del">삭제</button> <label
								class="thumb"> <input type="checkbox" name="itemSelect"
									checked="checked" value="상품ID" /> <span> <img
										src="${pageContext.request.contextPath}/static/images/sample/sample_product.jpg" alt="상품이름" />
								</span>
							</label>
								<div class="contr">
									<a href="#"> <strong class="txt-ti ellipsis">[하나칼국수]
											알곤이 칼국수 (2인분)</strong>
									</a> <span class="info">
										<div class="ea-area">
											<input type="number" title="수량 입력" name="ordQty" value="1"
												readonly="readonly">
											<button type="button" class="btn-down">수량 낮추기</button>
											<button type="button" class="btn-up">수량 올리기</button>
										</div> <span class="txt-price"> <strong> <em>19,000</em>원
										</strong>
									</span>
										<div class="probtn">
											<button type="button" id="btnWish" class="btn-wish">선물하기</button>
											<button type="button" class="btn btn-buy">바로구매</button>
										</div>
									</span>
								</div>
							</li>
						</ul>

					</div>
				</fieldset>
				<!--end 장바구니 목록-->
				<div class="rightarea">
					<!--start 결제 내역 필드-->
					<fieldset class="price-field">
						<legend style="position: absolute !important; left: -9999px">결제내역</legend>
						<dl class="orderprice">
							<dt>총 상품금액</dt>
							<dd>
								<strong> <em id="emPriceFTotNrmlprice">32,800</em>원
								</strong>
							</dd>
						</dl>
						<dl class="minus">
							<dt>총 할인금액</dt>
							<dd>
								<strong> <em id="emPriceFTotDcAmt">1,640</em>원
								</strong>
							</dd>
						</dl>
						<dl class="total">
							<dt>총 결제예상금액</dt>
							<dd>
								<strong> <em id="emPriceFTotPayAmt">34,660</em>원
								</strong>
							</dd>
						</dl>
					</fieldset>
					<!--end 결제 내역 필드-->
					<button type="button" class="btn btn-order">
						주문하기 <em id="emTotalItemCnt">2</em>
					</button>
				</div>
			</form>
		</div>
	</section>

	<footer id="footer"></footer>
</body>
</html>