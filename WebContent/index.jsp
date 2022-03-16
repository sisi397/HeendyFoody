<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 로딩바스타일 -->
<style type="text/css">
#Progress_Loading {
	position: absolute;
	left: 50%;
	top: 50%;
	background: #ffffff;
}
</style>
</head>
<body>
	<!-- 로딩바 -->
	<div id="Progress_Loading">
		<img src="${contextPath }/static/images/common/loding.gif" />
	</div>
	<%@ include file="/navbar.jsp"%>
	<div id="wrap" class="main">
		<div id="contents">
			<!-- // 메인베너 -->
			<section class='mainbanner'>
				<div class='swiper-container mainbannerswiper'>
					<div class='swiper-wrapper'>
						<c:forEach begin="0" end="5" varStatus="status">
							<div class='swiper-slide'>
								<a
									href='https://tohome.thehyundai.com/front/dp/dpd/mgzDetail.do?mgzNo=210407100576002&ga_param=dwMain${status.count }'>
									<img
									src='https://tohomeimage.thehyundai.com/DP/DP034/2022/03/04/093653/drmky.jpg?RS=1204x540'
									alt=''>
								</a>
							</div>
						</c:forEach>
					</div>
				</div>
				<div class='innercon'>
					<div class='swiper-pagination-tot'>
						<strong>00</strong> / <span>00</span>
					</div>
					<a href='#' class='btn-play active'>재생/일시정지</a>
				</div>
			</section>
			<!-- 메인베너 끝 //-->
			<!-- // 가장 할인율 큰 상품 -->
			<div id='cdnTimeSale'>
				<section class='timesale' id='timeSale'>
					<div class='innercon'>
						<h2>
							<span class='bl' style='border-top: 4px solid'><i
								style='border-bottom: 4px solid'></i></span> <strong
								style='color:; font-weight: bold'>알뜰 장보기</strong> <small
								style='color:; font-weight: normal'>지금 이 순간 만날 수 있는 특가</small>
						</h2>
						<div class='saleprod'>
					      	<div class='swiper-container timesaleswiper'>
					      		<c:forEach items="${saleProduct}" var="product">
					      		<div class='swiper-wrapper'>
						      		<div class='swiper-slide' data-time-start='' data-time-end=''>
						      		<a href='${contextPath}/product/detail.do?pid=${product.productId}&cid=${product.companyId}'>
							      		<span class='thumb'>
								      		<img src="${product.imageUrl}"alt='' onerror=''>
								      		<span class='badge'><strong>${product.discountRate }%</strong></span>
							      		</span>
							      		<strong class='txt-ti ellipsis'>${product.productName }</strong>
							      		<strong><em>${product.discountPrice }</em>원</strong><del>${product.productPrice}원</del>
							      		<span class='txt-price'></span> 
						      		</a>
						      		<button type='button' class='btn-cart' onclick=''>장바구니 담기</button>
						      		</div>
					      		</div>
						      	</c:forEach>
					      	</div>
						</div>
					</div>
				</section>
			</div>
			<!-- 가장 할인율 큰 상품 끝 // -->

			<!-- // 좋아요 많은 상품 -->
			<section class='innercon exhibition' data-aos='fade-up'></section>
			<section class='innercon mdspick' data-aos='fade-up'>
				<h2>
					<span class='bl' style='border-top: 4px solid'><i
						style='border-bottom: 4px solid'></i></span><strong
						style='color:; font-weight: bold'>Heendy's Pick</strong><small
						style='color:; font-weight: normal'></small>
				</h2>
				<ul class='product-list small bestprodlist'>
					<c:forEach items="${bestProduct}" var="product">
			       	<li>
			       		<a href='${contextPath}/product/detail.do?pid=${product.productId }&cid=${product.companyId}'>
			       		<span class='thumb'>
			       		<img src='https://tohomeimage.thehyundai.com/PD/PDImages//S/7/0/3/8809539023489_01.jpg?RS=350x420' alt='' onerror=''>
			       		<div class='badgewrap'>
			       		<c:if test="${product.discountRate > 0}">
			       			<span class='badge'><strong>${product.discountRate }%</strong></span>
			       		</c:if>
			       		</div>
			       		</span>
			       		<strong class='txt-ti'>${product.productName }</strong> </a>
			       		<span class='info'><span class='txt-price'><strong><em>${product.discountPrice }</em>원</strong>
			       		<c:if test="${product.discountRate > 0}">
			       			<del>${product.productPrice}</del>
			       		</c:if>
			       		</span><button type='button' class='btn-cart' onclick=''>장바구니 담기</button></span>
			       	</li>
			       	</c:forEach>
				</ul>
			</section>
			<!-- 좋아요 많은 상품 끝 // -->
			<!-- // 신상품 리스트 -->
			<section class='innercon exhibition' data-aos='fade-up'></section>
			<section class='innercon' data-aos='fade-up'>
				<h2>
					<span class='bl' style='border-top: 4px solid'><i
						style='border-bottom: 4px solid'></i></span><strong
						style='color:; font-weight: bold'>신상품</strong><small
						style='color:; font-weight: normal'>HeendyFoody의 신상품을
						만나보세요</small><a
						href='${contextPath}/product/list.do?menu=newprod'
						class='btn all'>전체보기</a>
				</h2>
				<ul class='product-list newprodlist'>
					<c:forEach items="${saleProduct}" var="product">
			       	<li>
				       	<a href='${contextPath}/product/detail.do?pid=${product.productId }&cid=${product.companyId}'>
					       	<span class='thumb'>
					       	<img src='https://tohomeimage.thehyundai.com/PD/PDImages//S/7/0/3/8809539023489_01.jpg?RS=350x420' alt='' onerror=''>
						       	<div class='badgewrap'>
						       	<c:if test="${product.discountRate > 0}">
						       		<span class='badge'><strong>${product.discountRate}%</strong></span>
						       	</c:if>
					       		</div>
				       		</span>
				       		<strong class='txt-ti ellipsis'>${product.productName }</strong> 
				       	</a>
				       	<span class='info'>
				       		<span class='txt-price'><strong><em>${product.discountPrice}</em>원</strong>
					       	<c:if test="${product.discountRate > 0}">
					       		<del>${product.productPrice }</del>
						   	</c:if>
				       		</span>
				       		<button type='button' class='btn-cart' onclick=''>장바구니 담기</button>
				       	</span>
				       	<span class='tag'><span>신상품</span></span>
				   	</li>
				   	</c:forEach>
				</ul>
			</section>
			<!-- 신상품 리스트 끝 // -->
			
			<!-- // 카테고리별 상품 -->
			<section class="categoryprod"></section>
			<!-- 카테고리별 상품 끝 //-->
			</div>
		<jsp:include page="footer.jsp" flush="false" />
	</div>

	<script>
		$(document).ready(function(){
			$('#Progress_Loading').hide(); //첫 시작시 로딩바를 숨겨준다.
		})
		.ajaxStart(function(){
			$('#Progress_Loading').show(); // ajax실행시 로딩바를 보여준다.
		})
		.ajaxStop(function(){
			$('#Progress_Loading').hide(); //ajax종료시 로딩바를 숨겨준다.
        });
          
		$(document).ready(function(){
			
       	/*
       		@Author 김시은
       		카테고리 목록 불러오기 => 카테고리 목록 뿌려주기
       		카테고리 개수만큼 반복해서 상품 6개 뽑아오기 단, 부모 카테고리는 패스, 
       	*/
      	var html = "";
      	var list = "";
      	function prodlist(cid, pcid){
      		console.log("cid",cid, pcid);
      		$.ajax({
       		url:'${contextPath}/product/select.do',
       		type: 'post',
       		dataType:'json',
       		async:false,
       		data:{
       			beginRow:1,
       			endRow:6,
       			menu : 'category',
       			cate : cid,
       			pcate : pcid,
       		},
       		success : function(data){
       			list += "<div class='swiper-slide '>";
       			list += "<ul class='product-list big'>";
       			for(var i in data){
       				console.log("함수 : " + JSON.stringify(data[i]));
       				list += "<li><a href='${contextPath}/product/detail.do?pid="+data[i].productId +"&cid="+ data[i].companyId +"''><span class='thumb'>";
       				list += "<img src='https://tohomeimage.thehyundai.com/PD/PDImages/S/2/9/7/8806079686792_00.jpg?RS=350x420' alt='' onerror=''>";
       				list += "<div class='badgewrap'></div>";
       				list += "</span><strong class='txt-ti ellipsis'>"+data[i].productName+"</strong></a>";
       				list += "<span class='info'><span class='txt-price'><strong><em>"+data[i].discountPrice+"</em>원</strong></span></span>"
       				list += "</li>"
       			}
       			list += "</ul></div>"
       		},
       		error: function(err){
       			console.log(err);
       		}
       	});
      	}
      	
      	//카테고리 전체 물러오기
      	
      	$.ajax({
      		url:'${contextPath}/product/category.do',
      		type: 'post',
      		dataType:'json',
      		async:false,
      		data:{
      			cate : 0,
      			pcate : 0
      		},
      		success : function(data){
      			console.log("카테고리 : " + JSON.stringify(data));
      			var i = 0;
      			while(i < data.length){
      				list = "";
      				if(data[i].categoryId == data[i].parentCategoryId){	//부모 카테고리인 경우
      					html += "<section class='innercon category' data-aos='fade-up'>"
      					html += "<h2><strong style='color:;font-weight:'>" + data[i].categoryName + "</strong>";
      					html += "<a href='${contextPath }/product/list.do?menu=category&cate="+data[i].categoryId+"&pcate="+data[i].parentCategoryId+"' class='btn all'>"
      					html += data[i].categoryName + " 전체보기 </a>";
      	                html += "</h2>";
      	                html += "<div class='swiper-container categorytitleswiper'>";
      	                html += "<div class='swiper-wrapper'>";
      	                i++;
      	                while(i != data.length && data[i].categoryId != data[i].parentCategoryId){ //자식 카테고리라면
      	                	html += "<div class='swiper-slide'>" + data[i].categoryName + "</div>";
      	                	prodlist(data[i].categoryId, data[i].parentCategoryId);
      	                	i++;
      	                }
      	                
      	                html += "</div><div class='swiper-pagination-categorytitle'></div></div>";
      	                html += "<div class='swiper-container categoryswiper'><div class='swiper-wrapper'>";
      	                html += list;
      	                html += "</div><div class='swiper-pagination-categorytitle'></div></div></section>";
      				}
      			}
      			$(".categoryprod").append(html);
      		}
      	});
      });
	</script>
</body>
</html>
