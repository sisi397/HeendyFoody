package com.heendy.common.exception;

/**
 * @author 이승준
 * 
 * 세션에 멤버가 저장되어 있지 않을 경우 발생하는 Exception
 * */
public class MemberNotExistSession extends RuntimeException {
	
	public MemberNotExistSession() {
		super("세션의 해당 멤버가 존재하지 않습니다.");
	}

}
