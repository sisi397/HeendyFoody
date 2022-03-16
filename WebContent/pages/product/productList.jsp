<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
	
	<title>상품보기</title>
	
	<script src="http://code.jquery.com/jquery-2.2.1.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${contextPath}/static/css/common/common.min.css">
	<link rel="stylesheet" type="text/css" href="${contextPath}/static/css/common/main.min.css">
	<link rel="stylesheet" type="text/css" href="${contextPath}/static/css/common/css-library.min.css">
	<link rel="stylesheet" type="text/css" href="${contextPath}/static/css/product.min.css">
	
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
	<%@ include file="/navbar.jsp" %>
	<input type="hidden" name="paramSort" value="${param.sort }"/>
	<input type="hidden" name="paramPno" value="${param.pno }"/>
	<input type="hidden" name="paramMenu" value="${param.menu }"/>
	<input type="hidden" name="paramCate" value="${param.cate }"/>
	<input type="hidden" name="paramPcate" value="${param.pcate }"/>
    <div id="wrap" class="main product category">
	    <div id="contents">
	        <div class="innercon">
	        <!-- // 카테고리 메뉴일 경우 카테고리 표시 -->
	        <c:if test="${param.menu eq 'category' }">
	    	<section class="categorylist">
	    		<div class="depth">
	    			<h2>${categoryList[0].categoryName }</h2>
	    			<div class="path">
	    				홈 > ${categoryList[1].categoryName }
	    			</div>
	    		</div>
	    		<div class="depth-sub">
	    			<ul>
	    				<li class="" id="cate${categoryList[1].categoryId }"><a href="${contextPath }/product/list.do?menu=category&cate=${categoryList[1].categoryId}&pcate=${categoryList[1].parentCategoryId}">전체보기</a> </li>
	    				<c:forEach items="${categoryList }" var="category" begin="2">
	    					<li class="" id="cate${category.categoryId }"><a href="${contextPath }/product/list.do?menu=category&cate=${category.categoryId}&pcate=${category.parentCategoryId}">${category.categoryName }</a></li>
	    				</c:forEach>
	    			</ul>
	    		</div>
	    	</section>
	    	</c:if>
	        <!-- 카테고리 메뉴일 경우 카테고리 표시 끝 //-->
	    	
	    	<!-- 메뉴 제목 -->
	        <c:if test="${param.menu eq 'best' }"><h2>베스트</h2></c:if>
	        <c:if test="${param.menu eq 'sale' }"><h2>세일</h2></c:if>
	        <c:if test="${param.menu eq 'newprod' }"><h2>전체상품</h2></c:if>
	        
	        <!-- // 정렬기준 -->
	        <c:if test="${param.menu ne 'best' }">
            <section class="list-filter" style="height:40px">
                <div class="filter-wrapper">
	                <div class="form-filter">
	                    <ul class="btn-group" id="sortType">
	                        <li><button type="button" id="sortTypeA" onclick="sortType('A');">신상품순</button></li>
	                        <li><button type="button" id="sortTypeB" onclick="sortType('B');">인기상품순</button></li>
	                        <li><button type="button" id="sortTypeC" onclick="sortType('C');">낮은가격순</button></li>
	                        <li><button type="button" id="sortTypeD" onclick="sortType('D');">높은가격순</button></li>
	                    </ul>
	                </div>
	            </div>
            </section>
            </c:if>
	        <!-- 정렬기준 끝 //-->
            
            <!-- 상품 리스트 -->
	        <section class="list-product"></section>
	        
	        <!-- 페이지 번호 -->
	        <div class="pagination"></div>
	        </div>
	    </div>
    <jsp:include page="/footer.jsp" />
    </div>
    <script>
    $(document).ready(function(){
    	var menu = 'category';
    	
    	if (menu == "${param.menu }"){
    		document.getElementById('cate${param.cate}').className = "active"
    	}
    	
    	loadProductList();
    });
    
    // 페이지 이동
   	function movePage(pno){
    	var pno = $("input[name='paramPno']").val(pno);
    	loadProductList();
   	}
   	
    // 정렬
    function sortType(sort){
    	$('#sortTypeA').css('font-weight', 'normal');
    	$('#sortTypeB').css('font-weight', 'normal');
    	$('#sortTypeC').css('font-weight', 'normal');
    	$('#sortTypeD').css('font-weight', 'normal');
    	$('#sortType'+sort).css('font-weight', '600');
    	var pno = $("input[name='paramPno']").val(1); // 첫 페이지로 이동
    	var sort = $("input[name='paramSort']").val(sort);
    	loadProductList();
   	}
    
    function loadProductList(){
    	var sort = $("input[name='paramSort']").val();
    	var menu = $("input[name='paramMenu']").val();
    	var pno = $("input[name='paramPno']").val();
    	var cate = $("input[name='paramCate']").val();
    	var pcate = $("input[name='paramPcate']").val();
    	
    	var html = "";
    	$.ajax({
    		url:'${contextPath}/product/select.do',
    		type: 'post',
    		dataType:'json',
    		data:{
    			sort: sort,
    			menu: menu,
    			pno: pno,
    			cate: cate,
    			pcate: pcate
    		},
    		success : function(data){
				html += "<ul class='product-list' id = 'ulItemList'>";
				if(data.length > 0){
	    			for(var i in data){
	    				html += "<li>";
	    				html += "<a href='${contextPath }/product/detail.do?pid="+data[i].productId+"&cid="+data[i].companyId+"'>";
	    				html += "<span class='thumb'>";
	    				if(data[i].productCount == 0){
	    					html += "<span class='soldout'>일시품절</span>";
	    				}
	    				html += "<img src='" +data[i].imageUrl+ "' alt='' onerror=''/>"
	    				if(data[i].discountRate != 0){
	    					html += "<div class='badgewrap'><span class='badge'>";
	    					html += "<strong>"+data[i].discountRate+"%</strong></span></div>";
	    				}
	    				html += "</span><strong class='txt-ti ellipsis'>"+data[i].productName+"</strong></a>";
	    				html += "<span class='info'><span class='txt-price'><strong><em>"+data[i].discountPrice+"</em>원</strong>";
	    				if(data[i].discountRate > 0){
	    					html += "<del>"+data[i].productPrice+"</del>";
	    				}
	    				html += "</span><button type='button' class='btn-cart' onclick='addCartProduct("+data[i].productId+","+data[i].companyId+", 1)'>장바구니 담기</button>";
	    				html += "</span><span class='tag'>";
	    				if(data[i].discountRate > 0){
	    					html += "<span> 세일상품 </span>"
	    				}
	    				html += "</span></li>"
	    			}
				}else{
					html += "<div style='text-align:center; margin:100px;'>상품이 없습니다.</div>";
				}
				html += "</ul>";
				$(".list-product").html(html);
				if(menu != 'best'){
					loadPagination();
				}
    		}
    	});
    }
    
    // 비동기로 페이지 불러오기
    function loadPagination(){
    	var menu = $("input[name='paramMenu']").val();
    	var pno = $("input[name='paramPno']").val();
    	var cate = $("input[name='paramCate']").val();
    	var pcate = $("input[name='paramPcate']").val();
    	
    	var html = "";
    	$.ajax({
    		url:'${contextPath}/product/pagination.do',
    		type: 'post',
    		dataType:'json',
    		data:{
    			menu: menu,
    			pno: pno,
    			cate: cate,
    			pcate: pcate
    		},
    		success : function(data){
    			console.log(data);
    			if(data.beginPageNumber > data.pagePerList){
    				html += "<a onclick='movePage("+(data.beginPageNumber - 1)+")' class='prev'>이전</a>";
    			}
    			for(var i = data.beginPageNumber; i <= data.endPageNumber; i++){
    				if(i == pno){
        				html += "<button onclick='movePage("+i+")'><span class='num'><a style='border:3px solid #ff6913'>"+i+"</a></span></button>";
    				}else{
        				html += "<button onclick='movePage("+i+")'><span class='num'><a>"+i+"</a></span></button>";
    				}
    			}
    			if(data.endPageNumber < data.totalPage){
    				console.log("다음버튼 생성")
    				html += "<a onclick='movePage("+(data.endPageNumber + 1)+")' class='next'>다음</a>";
    			}
	    			
				$(".pagination").html(html);
				
				window.scrollTo(0, 0);
    		}
    	});
    }
    
    // 장바구니 담기
    function addCartProduct(pid, cid, qty){
    	$.ajax({
    		url:'${contextPath}/cart/create.do',
    		type: 'post',
    		dataType:'json',
    		data: {
    			product_id: pid,
    			company_id: cid,
    			count : qty
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
    </script>
</body>
</html>