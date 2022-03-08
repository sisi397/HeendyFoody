package com.heendy.common;

public enum SQLErrorCode {

	ALREADY_CART_EXIST(20000);
	
	private final int code;
	
	SQLErrorCode(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return this.code;
	}
}
