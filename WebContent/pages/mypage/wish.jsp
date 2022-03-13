<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="${contextPath}/static/js/function.min.js"></script>
<script type="text/javascript" src="${contextPath}/static/js/jquery-library.min.js"></script>
<link href="${contextPath}/static/css/common.min.css" rel="stylesheet" type="text/css">
<link href="${contextPath}/static/css/css-library.min.css" rel="stylesheet" type="text/css">
<link href="${contextPath}/static/css/mypage.min.css" rel="stylesheet" type="text/css">
<link href="${contextPath}/static/css/wish.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" type="image/x-icon" href="${contextPath}/static/images/favicon.ico">
</head>

<body>
  <div id="wrap" class="mypage like">
    <div id="contents">
      <div class="innercon">
      
        <section class="lnbarea">
		  <h2>마이페이지</h2>
		  <ul>
		    <li class="lnb-depth1">
			  <a href="${contextPath}/mypage/info.do">활동 관리</a>
			  <ul class="lnb-depth2">
			    <li><a href="${contextPath}/mypage/order_list.do">주문 내역</a></li>
	            <li><a href="${contextPath}/mypage/wish.do">좋아요</a></li>
			    <li><a href="${contextPath}/mypage/recent_view.do">최근 본 상품</a></li>
			  </ul>
		    </li>
		  </ul>
		</section> 
		
		<section class="conarea">
		  <h3 class="tit">좋아요</h3>
		  
		  <div class="list-filter">
	        <label><input type="checkbox" id="checkAll" onclick="fn_checkboxAll();"><span class="select-all">전체선택</span></label>
	        <button type="button" onclick="fn_deleteCheckList();" class="btn small lightgray">선택삭제</button>
	        <button type="button" onclick="fn_deleteSoldoutList();" class="btn small lightgray">품절삭제</button>
	      </div>
	       
	      <fieldset class="list-field">
	        <c:if test="${!empty wishList}">
              <ul id="contUl" class="product-list vertical">
             
                <c:forEach items="${wishList}" var="wishDTO">
             
                
	              <li>
		            <button type="button" class="btn-del" onclick="wishDelete(${wishDTO.productId}, ${wishDTO.companyId});">삭제</button>
		              <label class="thumb">
		                <input type="checkbox" name="checkboxAll" value="${wishDTO.productId}">
		                <c:if test="${wishDTO.deleted == 1}">
		                <span class="soldout">판매중단</span>
		                </c:if>
		               <c:if test="${wishDTO.productCount == 0 && wishDTO.deleted != 1}">
		                <span class="soldout">일시품절</span>
		                </c:if>
		                <span class="normal"></span>
		                <img src="${wishDTO.imageUrl}" alt="${wishDTO.productName}">
	                  </label>
		
		              <div class="contr">
		              <c:if test="${wishDTO.deleted != 1}">
		                <a href="${contextPath}/product/detail.do?pid=${wishDTO.productId}&cid=${wishDTO.companyId}">
		                  <strong class="txt-ti ellipsis">${wishDTO.productName}</strong>
		                </a>
		               </c:if>
		               <c:if test="${wishDTO.deleted == 1}">
		                <a href="#">
		                  <strong class="txt-ti ellipsis">${wishDTO.productName}</strong>
		                </a>
		               </c:if>
		                     
	                    <input id="dawnSoldoutYn" name="dawnSoldoutYn" type="hidden" value="N"/>
	                    <span class="info">
	                      <span class="txt-price">
	                        <!-- 원가와 주문가(할인가) 다르다면 -->                            
	                        <c:if test="${wishDTO.discountPrice != wishDTO.productPrice}">
		                      <strong><em>${wishDTO.discountPrice}</em>원</strong>
	                          <del>${wishDTO.productPrice}</del>
	                        </c:if> 
	                        <!-- 원가와 주문가(할인가) 같다면 -->
	                        <c:if test="${wishDTO.discountPrice == wishDTO.productPrice}">
		                      <strong><em>${wishDTO.discountPrice}</em>원</strong>
		                    </c:if>      
	                      </span>
	                      <div class="probtn">
	                       <!-- 판매자가 상품을 삭제한 경우  --> 
		       				<c:if test="${wishDTO.deleted == 1}">      
		                      <button type="button" class="btn fill small lightgray" onclick="soldoutAlarm()">판매중단 상품</button>
		                    </c:if> 
		                    <!-- 정상  --> 
		                    <c:if test="${wishDTO.productCount > 0 && wishDTO.deleted != 1}">      
		                      <button type="button" class="btn small orange" onclick="addToCart(${wishDTO.productId}, ${wishDTO.companyId})">장바구니 담기</button>
		                    </c:if> 
		                    <!-- 수량이 없는 경우  --> 
		                    <c:if test="${wishDTO.productCount <= 0 && wishDTO.deleted != 1}">
		                      <button type="button" class="btn fill small lightgray" onclick="restockAlarm()">재입고 알림</button>
		                    </c:if>    
		                  </div>
		                </span>
	                   </div>
		             </li>
	             
	            </c:forEach>      
               </ul>
               
              <!-- 페이지네이션 -->
		      <div class="pagination">
		   		<c:if test="${beginPage > pagePerList}">
				  <a class="prev" href="${contextPath}/mypage/wish.do?pno=${beginPage-1}">이전</a>
				</c:if>
				<c:forEach var="pno" begin="${beginPage}" end="${endPage}">
				  <span class="num"><a href="${contextPath}/mypage/wish.do?pno=${pno}">${pno}</a></span>
				</c:forEach>
				<c:if test="${endPage < totalPageCount}">
				  <a class="next" href="${contextPath}/mypage/wish.do?pno=${endPage + 1}">다음</a>
				</c:if>
			  </div>
			  
              </c:if>
               
               
              <c:if test="${empty wishList}">
		   	    <div class="nodata">좋아요 상품이 아직 없습니다.</div>
		      </c:if>
               
               
          </fieldset>                   
        </section>
      </div>
    </div>
  </div>
 <script type="text/javascript">

	  
	function wishDelete(pId, cId){
				
		$.ajax({
			type: 'POST',
			url:'${contextPath}/wish/delete.do',
			dataType:'text',
			data: {
				productId: pId,
				memberId: ${loginUser.memberId},
				companyId: cId
			},
			success : function(data){
				console.log(data);
				alert("좋아요 삭제");
				location.reload();
				//window.location.reload(true);
			},
			error : function(err) {
				console.log(err);
			}
		});
			
		}
	
	function addToCart(pId, cId) {
			
		$.ajax({
			type: 'POST',
			url:'${contextPath}/cart/create.do',
			dataType:'json',
			data: {
				product_id: pId,
				company_id: cId,
				count: 1
			},
			success : function(data){
				console.log(data);
				alert("장바구니에 담았습니다");
			},
			error : function(err) {
				console.log(err);
				var errorMsg = err.responseJSON.message;
				alert(errorMsg);
				
			}
		});
				
	}
  
	function soldoutAlarm() {
		alert("상품 판매가 중단되었습니다");
	}
	
	function restockAlarm() {
		alert("준비 중입니다");	
	}
  </script>
</body>
</html>