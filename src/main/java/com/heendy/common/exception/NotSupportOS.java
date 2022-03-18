package com.heendy.common.exception;


/**
 * @author 이승준
 * 
 * OS 정보를 읽어 올 때 지원하지 않는 OS일 때 발생하는 Exception
 * */
public class NotSupportOS extends RuntimeException{

	public NotSupportOS(String osName) {
		super(osName + " 은 지원하지 않습니다.");
	}
}
