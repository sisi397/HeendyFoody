package com.heendy.utils;

/**
 * @author 이승준
 * 
 * request 파라미터 검증 Class
 * */
public class Validation {

	private final static Validation INSTANCE = new Validation();
	
	public static Validation getInstance() {
		return INSTANCE;
	}
	
	public boolean validNotEmpty(String target) {
		boolean result = true;
		
		if(target == null || target.isEmpty() || target.trim().isEmpty()) {
			result = false;
		}
		return result;
	}
	
	
	public boolean validNotEmpty(String[] target) {
		boolean result = true;
		
		if(target == null || target.length == 0) {
			result = false;
		}
	
		
		return result;
	}
	
	public boolean validIsNumber(String target) {
		return target.chars().allMatch(Character::isDigit);
	}
	
	public boolean validMin(int target, int minValue) {
		
		return target >= minValue;
	}
	
	public boolean validMax(int target, int maxValue) {
		return target <= maxValue;
	}
	
}
