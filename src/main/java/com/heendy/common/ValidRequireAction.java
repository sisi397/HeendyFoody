package com.heendy.common;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.heendy.action.Action;


/**
 * @author 이승준
 * 
 * request로 넘어온 parameter 값이 검증이 필요한 Action 추상 클래스
 * */
public abstract class ValidRequireAction implements Action {
	
	private final Action action;
	
	public ValidRequireAction(Action action) {
		this.action = action;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		List<ErrorResponse.ErrorField> errors = valid(request);
		System.out.println(errors.size());
		if(errors.size() != 0) {
			ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_FIELDS);
			
			errorResponse.setErrors(errors);
			
			String json = new Gson().toJson(errorResponse);
			response.setStatus(errorResponse.getStatus());
			response.getWriter().write(json);
			return;
		}
		this.action.execute(request, response);
		
	}
	
	protected abstract List<ErrorResponse.ErrorField> valid(HttpServletRequest request);

}
