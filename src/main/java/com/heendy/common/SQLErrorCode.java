package com.heendy.common;

/**
 * @author : 이승준
 * 
 * SQL 예외 코드 정의
 * */
public enum SQLErrorCode {

	/*장바구니, 주문 관련 예외 코드*/
	ALREADY_CART_EXIST(20000),
	
	/*상품 재고가 부족할 때 발생하는 예외 코드*/
	LACK_OF_STOCK(20001),
	
	/*장바구니 수량이 1 미만으로 수정할 때 발생하는 예외 코드*/
	OUT_BOUND_RANGE(20002),
	
	/*이미 좋아요를 등록했을 때 발생하는 예외 코드*/
	ALREADY_LIKE_EXIST(20100),
	
	/*상품이 이미 삭제되었을 때 발생하는 예외 코드*/
	ALREADY_DELETED_PRODUCT(20200),
	
	/*요청한 자원이 없는 경우 발생하는 예외 코드*/
	NO_DATA_FOUND(1403),
	
	/*요청한 자원에 대해 생성자가 아닐 경우 예외 코드*/
	NOT_RESOURCE_OWNER(20090),
	
	/*특정 요일, 시간 대 상품을 생성하려고 할 때 발생하는 예외 코드*/
	SERVER_CHECK_TIME(20013);
	
	private final int code;
	
	SQLErrorCode(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return this.code;
	}
}
