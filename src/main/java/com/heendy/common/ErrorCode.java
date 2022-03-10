package com.heendy.common;

public enum ErrorCode {
	
	/*
	 * author : 이승준
	 * 장바구니 주문 관련 Error code
	 * */
	
	ALREADY_CART_EXIST("ERROR-001","이미 장바구니에 담겨있습니다.", 400),
	
	LACK_OF_STOCK("ERROR-002","상품 재고가 부족합니다.", 400),
	
	OUT_BOUND_RANGE("ERROR-003","최소 1개 이상 장바구니에 담아야 합니다.", 400),
	
	
	/*
	 * author : 이승준
	 * 공통적으로 발생하는 SQL error code
	 * */
	NO_DATA_FOUND("ERROR-020","해당 데이터를 찾을 수 없습니다.", 404),
	
	NOT_RESOURCE_OWNER("ERROR-021","해당 리소스의 소유자가 아닙니다.", 403),
	
	/*
	 * 
	 * author : 이승준
	 * 공통 Error code
	 * */
	UNCAUGHT_SERVER_ERROR("ERROR-040","서버에서 예상치 못한 에러가 발생",500);
	
	
	
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
