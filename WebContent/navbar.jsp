<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<header id="header" class="short">
   <div class="inner">
       
       <!-- gnbarea// -->
       <nav class="gnbarea" style="padding:14px 0 19px;">         
           
           <!-- 팝업 : category// -->
           <div id="popCategory">
           	<button type="button" class="btn-category" style="padding-bottom:20px;">카테고리 전체보기</button>
           </div>
          	<div id ="p_popCategory" class ="popcategory">
           	<nav class="lnb-list">
           		<ul class="lnb">
           			<li class="depth1"><button type="button">과일과 채소</button>
            			<ul class="depth2">
            				<li><a>전체보기</a></li>
            			</ul>
            		</li>	
           		</ul>
          		</nav>
          	</div>
           <!-- //팝업 : category -->
           
           <!-- gnb// -->
           <ul class="gnb-list" id="homeGnbList">
           	<li><a href="${contextPath }/product/list.do?menu=best">베스트</a></li>
           	<li><a href="${contextPath }/product/list.do?menu=sale">세일</a></li>
           	<li><a href="${contextPath }/product/list.do?menu=newprod">전체상품</a></li>
           	<li><a href="${contextPath }/product/list.do?menu=category&pcate=1&cate=1">과일</a></li>
           	<li><a href="${contextPath }/product/list.do?menu=category&pcate=1&cate=9">계절과일</a></li>
           </ul>
           <!-- //gnb -->
           <button type="button" class="btn-cart" onClick="cartView();" style="top:7px;">장바구니<span id="basketCnt"></span></button>
       </nav>
       <!-- //gnbarea -->
   </div>
</header>
<script>
function cartView(){
	location.href="${contextPath}/cart/shoppingCartList.do";
}
</script>