package com.heendy.common.exception;

public class NotSupportExtension extends Exception{

	public NotSupportExtension(String extension) {
		super("이미지 파일 업로드 지원되는 확장자는 jpg,png,jpeg 입니다.");
	}
}
