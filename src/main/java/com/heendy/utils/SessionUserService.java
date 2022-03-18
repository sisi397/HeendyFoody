package com.heendy.utils;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import com.heendy.dto.MemberDTO;

/**
 * @author 이승준
 * 
 * MeberDTO 타입의 객체를 HttpSession 에 저장/읽기 기능을 제공하는 클래스
 * */
public class SessionUserService implements UserService<MemberDTO,HttpSession> {
	
	private final static SessionUserService INSTANCE = new SessionUserService();
	
	private SessionUserService() {}
	
	public static SessionUserService getInstance() {
		return INSTANCE;
	}
	
	@Override
	public void saveUser(MemberDTO member, HttpSession session) {
		
		session.setAttribute("loginUser", member);
	
	}

	@Override
	public Optional<MemberDTO> loadUser(HttpSession session) {
		
		MemberDTO member = (MemberDTO)session.getAttribute("loginUser");
		
		return Optional.ofNullable(member);
	}

	
}
