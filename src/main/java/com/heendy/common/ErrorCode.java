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
	 * 공통적으로 발생하는 error code
	 * */
	NO_DATA_FOUND("ERROR-020","해당 데이터를 찾을 수 없습니다.", 404),
	
	NOT_RESOURCE_OWNER("ERROR-021","해당 리소스의 소유자가 아닙니다.", 403),

	UNCAUGHT_SERVER_ERROR("ERROR-040","서버에서 예상치 못한 에러가 발생",500),
	
	UNATHORIZED_USER("ERROR-041","인증되지 않은 사용자입니다.",401),
	
	FORBIDDEN_USER("ERROR-044", "권한이 없는 사용자입니다.", 403),
	
	INVALID_FIELDS("ERROR-042","요청한 필드의 값이 유효하지 않습니다.",400),
	
	NOT_SUPPORT_IMAGE_FILE("ERROR-043","지원되지 않는 파일 확장자 입니다.", 400),
	/*
	 * author : 이지민
	 * 공통 Error code
	 * */
	SERVER_CHECK_TIME("ERROR-045", "서버 점검 시간입니다.", 400),
	
	
	/*
	 * author : 김시은
	 * 좋아요 관련 Error code
	 * */
	
	ALREADY_LIKE_EXIST("ERROR-050","이미 좋아요 된 상품입니다.", 400),

	/*
	 * author : 김시은
	 * 상품 관련 Error code
	 * */
	
	ALREADY_DELETED_PRODUCT("ERROR-060","존재하지 않는 상품입니다.", 400);
	
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
