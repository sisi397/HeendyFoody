package com.heendy.common;

/*
 * @author : 이승준
 * 
 * 예외 코드 정의
 * */
public enum SQLErrorCode {

	/*장바구니 관련 예외 코드*/
	ALREADY_CART_EXIST(20000),
	
	LACK_OF_STOCK(20001),
	
	OUT_BOUND_RANGE(20002),
	
	
	/*공통 예외 코드*/
	NO_DATA_FOUND(1403),
	
	NOT_RESOURCE_OWNER(20090);
	
	private final int code;
	
	SQLErrorCode(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return this.code;
	}
}
