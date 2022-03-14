<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<style>
#footer .info .logo:after {
	width: 120% !important;
	height: 80% !important;
	background-size: contain !important;
	background: url(../static/images/common/logo_footer1.png) no-repeat 0 0;
}
</style>

	<!-- footer// -->
	<footer id="footer">
		<div class="util">
			<div class="inner">
				<a href="https://tohome.thehyundai.com/front/dp/dpf/companyIntro.do"
					class="btn-tohome">브랜드소개</a> <a
					href="https://tohome.thehyundai.com/front/dp/dpf/serviceTos.do">이용약관</a>
				<a
					href="https://tohome.thehyundai.com/front/dp/dpf/personalInfoPolicy.do"><strong>개인정보처리방침</strong></a>
				<a href="https://tohome.thehyundai.com/front/dp/dpf/youthPolicy.do">청소년보호정책</a>
				<a
					href="https://tohome.thehyundai.com/front/dp/dpf/imagingDevicePolicy.do">영상기기운영방침</a>

				<div class="share">
					<button type="button" class="btn-youtube"
						onclick="location.href='https://www.youtube.com/channel/UCiAFKYYJOexSKyKmhEIXDEw?outUrlYn=Y'">youtube</button>
					<button type="button" class="btn-facebook"
						onclick="location.href='https://www.facebook.com/tohome.official?outUrlYn=Y'">facebook</button>
					<button type="button" class="btn-instagram"
						onclick="location.href='https://www.instagram.com/tohome.official?outUrlYn=Y'">instagram</button>
				</div>

				<div class="familysite">
					<button type="button" class="btn-site"
						onclick="$('.familysite .link-list').fadeToggle()">Family
						Site</button>
					<ul class="link-list">
						<li><a href="https://www.thehyundai.com/Home.html"
							target="_blank">더현대닷컴</a></li>
						<li><a
							href="https://esuper.ehyundai.com/esuper/main.do?storeCd=210"
							target="_blank">e수퍼마켓</a></li>
						<li><a href="https://www.greating.co.kr/" target="_blank">그리팅
								(greating)</a></li>
						<li><a href="https://www.hmall.com/p/" target="_blank">현대Hmall</a></li>
						<li><a href="https://www.thehandsome.com/ko/" target="_blank">더한섬</a></li>
						<li><a href="https://www.eqlstore.com/main" target="_blank">한섬
								EQL</a></li>
						<li><a href="https://www.hfashionmall.com/sfmweb"
							target="_blank">H패션몰</a></li>
						<li><a href="https://www.hddfs.com/shop/dm/main.do"
							target="_blank">현대백화점 DUTY FREE</a></li>
						<li><a
							href="https://mall.hyundailivart.co.kr/front/index_org.lv"
							target="_blank">현대리바트몰</a></li>
						<li><a href="https://www.hyundairentalcare.co.kr/"
							target="_blank">현대렌탈케어</a></li>
						<li><a href="https://www.h-point.co.kr/cu/main/index.nhd"
							target="_blank">H.point</a></li>
					</ul>
				</div>
			</div>
		</div>

		<div class="info">
			<div class="inner">
				<div class="logo">현대식품관</div>
				<div id="fnDawnBizpCrstList">
					<strong>(주)흰디푸디</strong><br> <span> 대표 : 현대IT&E 개발자
						양성교육 1차 PJT 4조</span><br> <span> 주소 : 서울 송파구 중대로 135 </span><br>
					이메일 : heendyfoody@hyundai.com (제휴 문의, 02-1212-0761) <br>
					<p class='essential-info'>
						<em>흰디푸디 투홈의 개별 판매자가 등록한 상품(브랜드직송) 상품에 대한 광고, 상품주문, 배송, 환불의
							의무와 책임은 각 판매자가 부담하고,<br> 흰디푸디 투홈은 통신판매 중개자로서의 의무와 책임을 다합니다.
						</em><br> 고객님의 안전거래를 위해 현금결제 시 KG이니시스 구매안전 서비스를 이용하실 수 있습니다.<br>
						<small class='copyright'>Copyright &copy; Hyundai Foody
							Store. All rights reserved.</small>
				</div>
			</div>
		</div>

		<!-- topbtn// -->
		<button type="button" class="btn-top" onclick="GA_Event('PC_공통', 'TOP', 'TOP');">TOP</button>
		<!-- //topbtn -->
	</footer>
