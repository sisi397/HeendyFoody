<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<header id="header" class="short">

	<div class="inner">

		<!-- gnbarea// -->
		<nav class="gnbarea" style="padding: 14px 0 19px;">

			<!-- 팝업 : category// -->
			<div id="popCategory">
				<button type="button" class="btn-category"
					style="padding-bottom: 20px;">카테고리 전체보기</button>
			</div>
			<div id="p_popCategory" class="popcategory">
				<nav class="lnb-list">
					<!-- 부모카테고리 리스트 전체 나열 -->
					<ul class="lnb">
					</ul>
				</nav>
			</div>
			<!-- //팝업 : category -->

			<!-- gnb// -->
			<ul class="gnb-list" id="homeGnbList">
				<li><a href="${contextPath }/product/list.do?menu=best">베스트</a></li>
				<li><a href="${contextPath }/product/list.do?menu=sale">세일</a></li>
				<li><a href="${contextPath }/product/list.do?menu=newprod">신상품</a></li>
			</ul>
			<!-- //gnb -->
			<button type="button" class="btn-cart" onClick="cartView();"
				style="top: 7px;">
				장바구니<span id="basketCnt"></span>
			</button>
		</nav>
		<!-- //gnbarea -->
	</div>
</header>
<script>
$(document).ready(function () {
	console.log("부모 카테고리")
	var html ="";
	$.ajax({
		url:'/HeendyFoody/product/category.do',
		type: 'post',
		dataType:'json',
		async:false,
		data:{
			cate : 0,
			pcate : 0
		},
		success : function(response){
			console.log("navBar : " + response);
			var parentCategory = [];	// 부모 카테고리를 담을 리스트
			var childCategory = [];		// 자식 카테고리를 담을 리스트
			for(i in response){

				if(response[i].categoryId == response[i].parentCategoryId){
					parentCategory.push([response[i].categoryId, response[i].categoryName, response[i].parentCategoryId]);
				}else{
					childCategory.push([response[i].categoryId, response[i].categoryName, response[i].parentCategoryId]);
				}
			}
			for(i in parentCategory){
				html += "<li class='depth1'>";
				html += "<button type='button' onclick=location.href='${contextPath}/product/list.do?menu=category&cate=" + parentCategory[i][0] + "&pcate=" + parentCategory[i][2] + "'>" + parentCategory[i][1] + "</button>";
				html += "<ul class='depth2'>";
				html += "<li><a href='${contextPath}/product/list.do?menu=category&cate=" + parentCategory[i][0] + "&pcate=" + parentCategory[i][2] + "'>전체보기</a></li>";
				for(j in childCategory){
					if(parentCategory[i][0] == childCategory[j][2]){ //부모의 카테고리id와 자식의 부모카테고리 ID가 같다면
						html += "<li><a href='${contextPath}/product/list.do?menu=category&cate=" + childCategory[j][0] + "&pcate=" + childCategory[j][2] + "'>" + childCategory[j][1] + "</a></li>";
					}	
					
				}
				html += "</ul>";
				html += "</li>";
			}
			$(".lnb").append(html);
		}
	});
	
});

	function cartView() {
		location.href = "${contextPath}/cart/shoppingCartList.do";
	}
</script>