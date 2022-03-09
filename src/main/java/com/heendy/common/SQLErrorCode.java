package com.heendy.common;

/*
 * @author : 이승준
 * 
 * 사용자 정의 예외 정의
 * */
public enum SQLErrorCode {

	ALREADY_CART_EXIST(20000),
	
	LACK_OF_STOCK(20001),
	
	OUT_BOUND_RANGE(20002);
	
	private final int code;
	
	SQLErrorCode(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return this.code;
	}
}
