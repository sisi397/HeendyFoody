package com.heendy.common;

import java.util.ArrayList;
import java.util.List;


/*
 * @author: 이승준
 * @version: 1
 * 에러 반환을 위한 클래스
 * */
public class ErrorResponse {
	
	private String code;
	
	private String message;
	
	private int status;
	
	private String location;
	
	private List<ErrorField> errors;

	public static class ErrorField {
		
		private String field;
		
		private String value;
		
		private String reason;
		
		public ErrorField() {}
		
		public ErrorField(String field, String value, String reason) {
			this.field = field;
			this.value = value;
			this.reason = reason;
		}

		public String getField() {
			return field;
		}

		public void setField(String field) {
			this.field = field;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getReason() {
			return reason;
		}

		public void setReason(String reason) {
			this.reason = reason;
		}
	
	}
	
	
	public static ErrorResponse of(ErrorCode errorCode) {
		ErrorResponse errorResponse = new ErrorResponse();
		
		errorResponse.setCode(errorCode.getCode());
		errorResponse.setStatus(errorCode.getStatus());
		errorResponse.setMessage(errorCode.getMessage());
		errorResponse.setErrors(new ArrayList<ErrorResponse.ErrorField>());
	
		return errorResponse;
	}
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<ErrorField> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorField> errors) {
		this.errors = errors;
	}
	
	
}
