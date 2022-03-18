package com.heendy.common.exception;

/**
 * @author 이승준
 * 
 * 지원 되지 않는 파일 확장자 파일을 요청한 경우 발생하는 Exception
 * */
public class NotSupportExtension extends Exception{

	public NotSupportExtension(String extension) {
		super("이미지 파일 업로드 지원되는 확장자는 jpg,png,jpeg 입니다.");
	}
}
