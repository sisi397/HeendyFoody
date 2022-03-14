package com.heendy.utils;

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
	
	
}
