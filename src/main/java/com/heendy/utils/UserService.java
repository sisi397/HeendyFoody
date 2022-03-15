package com.heendy.utils;

import java.util.Optional;

/**
 * @author 이승준
 * 
 * 데이터소스로 부터 유저 정보를 저장 또는 불러오는 인터페이스
 * 
 * T : 유저 타입
 * R : 데이터 소스 타입
 * */
public interface UserService<T,R> {

	/**
	 *  @param user 
	 *  @param dataSource
	 *  
	 *  user를 dataSoure에 저장하는 메소드
	 * */
	void saveUser(T user, R dataSource);
	
	
	/**
	 * 	@param dataSource
	 * 
	 * 	dataSource로 부터 user를 불러오는 메소드
	 * 
	 *  @return Optional<T>
	 * */
	Optional<T> loadUser(R dataSource);
}
