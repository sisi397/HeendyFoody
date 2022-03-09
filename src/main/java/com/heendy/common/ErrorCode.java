package com.heendy.common;

public enum ErrorCode {
	
	/*
	 * author : 이승준
	 * 장바구니 관련 Error code
	 * */
	
	ALREADY_CART_EXIST("CART-001","이미 장바구니에 담겨있습니다.", 400),
	
	LACK_OF_STOCK("CART-002","상품 재고보다 많은 수량을 장바구니에 담을 수 없습니다.", 400),
	
	OUT_BOUND_RANGE("CART-003","최소 1개 이상 장바구니에 담아야 합니다.", 400),
	
	
	/*
	 * author : 이승준
	 * 공통적으로 발생하는 SQL error code
	 * */
	NO_DATA_FOUND("COMMON-001","해당 데이터를 찾을 수 없습니다.", 404),
	
	NOT_RESOURCE_OWNER("COMMON-002","해당 리소스의 소유자가 아닙니다.", 403),
	
	/*
	 * 
	 * author : 이승준
	 * 공통 Error code
	 * */
	UNCAUGHT_SERVER_ERROR("SERVER-001","서버에서 예상치 못한 에러가 발생",500);
	
	
	
	private final String code;
	
	private final String message;
	
	private final int status;
	
	ErrorCode(String code, String message, int status) {
		this.code = code;
		this.message = message;
		this.status = status;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public int getStatus() {
		return this.status;
	}

}
