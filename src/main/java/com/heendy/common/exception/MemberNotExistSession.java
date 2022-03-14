package com.heendy.common.exception;

public class MemberNotExistSession extends RuntimeException {
	
	public MemberNotExistSession() {
		super("세션의 해당 멤버가 존재하지 않습니다.");
	}

}
