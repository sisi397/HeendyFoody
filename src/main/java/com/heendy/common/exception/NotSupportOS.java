package com.heendy.common.exception;

public class NotSupportOS extends RuntimeException{

	public NotSupportOS(String osName) {
		super(osName + " 은 지원하지 않습니다.");
	}
}
