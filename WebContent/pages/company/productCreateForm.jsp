<!-- 
	author : 이승준
	상품 등록 화면
 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:set var="contextPath" value="${pageContext.request.contextPath}"
	scope="page" />

<c:set var="categoryList" value="${param.categoryList}" scope="page" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${contextPath}/static/css/mypage.min.css" rel="stylesheet" type="text/css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>상품 등록</title>

</head>
<body>
	<script type="text/javascript">
		$(document).ready(function() {
			var errorMessage = <%=request.getAttribute("errorMsg")%>;
			if (errorMessage != null) {
				alert(errorMessage);
			}

		});

		$(document).ready(function() {

			/*이미지 업로드*/
			$("#imageUploadInput").change(function(e) {

				var formData = new FormData();

				formData.append("file1", $("#imageUploadInput")[0].files[0]);
				$.ajax({
					type : "POST",
					url : "${contextPath}" + "/image/upload.do",
					data : formData,
					processData : false,
					contentType : false,
					dataType : "json",
					success : function(data) {
						alert("업로드가 완료되었습니다.");
						console.log(data);

						var imgUrl = data.imageName;
						$("#imgUrlInput").val(imgUrl);

					},
					error : function(xhr, status, error) {
						var errorResponse = JSON.parse(xhr.responseText);
						var errorCode = errorResponse.code;
						var message = errorResponse.message;

						alert(message);
					}
				});
			});

		});

		/*상품 등록 함수*/
		function onSubmit() {
			var selectCategoryValue = $("#categorySelect").val();
			var productNameObj = $("#productNameInput");
			var priceObj = $("#productPriceInput");
			var discoutRateObj = $("#discountRateInput");
			var countObj = $("#countInput");
			var imgUrlInput = $("#imgUrlInput");
			
			var valid = checkSubmit(productNameObj, priceObj,discoutRateObj,countObj,imgUrlInput);
			
			if(valid) {
				var data = {
					productName:  productNameObj.val(),
					price: priceObj.val(),
					discountRate: discoutRateObj.val(),
					count: countObj.val(),
					imageUrl: imgUrlInput.val(),
					categoryId: selectCategoryValue
					
				}
				
				$.ajax({
					type : "POST",
					url : "${contextPath}" + "/company/createProduct.do",
					data : data,
					contentType : "application/x-www-form-urlencoded; charset=UTF-8",
					dataType : "json",
					success : function(data) {
						alert("상품이 등록되었습니다.");
						location.reload();
					},
					error : function(xhr, status, error) {
						var errorResponse = JSON.parse(xhr.responseText);
						var errorCode = errorResponse.code;
						var message = errorResponse.message;

						alert(message);
					}
				})
			} else {
				alert("필수 값들을 입력해주세요.");
			}
			
			
		}
		
		/*상품 등록전 input field 값 검증*/
		function checkSubmit(productNameObj, priceObj,discoutRateObj,countObj,imgUrlInput) {
			var valid = true;
			if (!checkProdutNameInput(productNameObj)) {
				productNameObj.addClass("is-invalid");
				valid = false;
			} else {
				productNameObj.removeClass("is-invalid");
			}
			
			if(!checkProductPriceInput(priceObj)){
				priceObj.addClass("is-invalid");
				valid = false;
			} else {
				priceObj.removeClass("is-invalid");
			}
			
			if(!checkDiscountRateInput(discoutRateObj)){
				discoutRateObj.addClass("is-invalid");
				valid = false;
			} else {
				discoutRateObj.removeClass("is-invalid");
			}
			
			if(!checkCountInput(countObj)) {
				countObj.addClass("is-invalid");
				valid = false;
			} else {
				countObj.removeClass("is-invalid");
			}
			
			if(!checkImgUrlInput(imgUrlInput)) {
				imgUrlInput.addClass("is-invalid");
				valid = false;
			} else {
				imgUrlInput.removeClass("is-invalid");
			}
			
			return valid;
		}
		

		/*상품 이름 input 검증*/
		function checkProdutNameInput(obj) {
			var value = $(obj).val();
			console.log(value)
			var result = true;
			if (value === null || value.length == 0 || value.trim().length == 0) {
				result = false;
			}
			return result;
		}
		/*상품 가격 input 검증*/
		function checkProductPriceInput(obj) {
			var value = $(obj).val();
			console.log(value)
			var result = true;

			if (value === null || value.length == 0 || value.trim().length == 0 || isNaN(value)) {
				result = false;
			}

			return result;
		}
		/*상품 할인율 input 검증*/
		function checkDiscountRateInput(obj) {
			var value = $(obj).val();
			console.log(value)
			var result = true;
			
			if (value.length == 0 || value.trim().length == 0 || isNaN(value) || value < 0 || value > 100) {
				result = false;
			}
			
			return result;
		}
		/*상품 재고 input 검증*/
		function checkCountInput(obj) {
			var value = $(obj).val();
			console.log(value)
			var result = true;

			if (value.length == 0 || value.trim().length == 0 || isNaN(value) || value < 1) {
				result = false;
			}
			return result;
		}
		/*상품 이미지 input 검증*/
		function checkImgUrlInput(obj) {
			var value = $(obj).val();
			console.log(value)
			var result = true;

			if (value === null || value.length == 0 || value.trim().length == 0) {
				result = false;
			}

			return result;
		}
	</script>


	<jsp:include page="/header.jsp" flush="false" />

	<div id="wrap">
		<div id="contents">
			<div class="innercon">
				<section class="lnbarea">
					<h2>업체회원</h2>
					<ul>
						<li class="lnb-depth2"><a href="${contextPath}/company/company.do">홈</a></li>
						<li class="lnb-depth2"><a href="${contextPath}/company/createProductForm.do">상품 관리</a></li>
					</ul>
				</section>
				<section class="conarea">

					<div class="container mt-5">
						<form id="frmCreateProd" method="post"
							action="${contextPath}/company/createProduct.do">
							<div class="input-group">
								<div class="input-group-prepend">
									<span class="input-group-text">상품명</span>
								</div>
								<input type="text" id="productNameInput" name="productName"
									class="form-control" placeholder="상품명을 입력해주세요.">
								<div id="productNameFeedBack" class="invalid-feedback">상품
									이름은 필수입니다.</div>

							</div>

							<div class="input-group mt-2">
								<div class="input-group-prepend">
									<span class="input-group-text">가격</span>
								</div>
								<input type="number" class="form-control" id="productPriceInput"
									name="price" placeholder="상품의 가격을 입력해주세요.">
								<div id="productPriceFeedBack" class="invalid-feedback">상품
									가격은 필수입니다. (숫자만 가능)</div>
							</div>

							<div class="input-group mt-2">
								<div class="input-group-prepend">
									<span class="input-group-text">할인율</span>
								</div>
								<input type="number" class="form-control" id="discountRateInput"
									name="discountRate" value=0
									placeholder="단위는 '%'입니다.(0 ~ 100만 가능합니다.)">
								<div id="discountRateFeedBack" class="invalid-feedback">
									할인율은 필수 입니다. (숫자만 가능. 최소 0 부터 최대 100까지만 가능합니다.)</div>
							</div>

							<div class="input-group mt-2">
								<div class="input-group-prepend">
									<span class="input-group-text">재고</span>
								</div>
								<input type="number" class="form-control" id="countInput"
									name="count" placeholder="상품의 재고를 입력해주세요. (최소 1개)">
								<div id="countFeedBack" class="invalid-feedback">상품 수량은 필수
									입니다. (숫자만 가능. 최소 1 부터 가능합니다.)</div>
							</div>
							<div class="input-group mt-2">
								<select class="form-select" id="categorySelect"
									aria-label="Example select with button addon">
									<c:forEach var="category" items="${categoryList}">

										<optgroup label="${category.value[0].categoryName}">
											<c:forEach var="childCategory" items="${category.value}"
												varStatus="status">
												<c:if test="${not status.first}">
													<option value="${childCategory.categoryId}">${childCategory.categoryName}</option>
												</c:if>
											</c:forEach>
										</optgroup>
									</c:forEach>
								</select>
							</div>

							<div class="input-group mt-2">
								<input type="file" class="form-control" id="imageUploadInput"
									accept=".jpg, .jpeg, .png"> <input type="hidden"
									id="imgUrlInput" name="imgUrl" value=""> <label
									class="input-group-text" for="imageUpload">Upload</label>
								<div id="imgUrlFeedBack" class="invalid-feedback">상품 사진은
									필수 입니다.</div>
							</div>

							<button type="button" class="btn mt-2" style="background: #0a58ca;"
								onclick="onSubmit()">등록하기</button>
						</form>
					</div>
				</section>
			</div>
		</div>
		<jsp:include page="/footer.jsp" flush="false" />
	</div>





</body>
</html>