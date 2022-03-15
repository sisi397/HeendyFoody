package com.heendy.action.wish;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.heendy.action.Action;
import com.heendy.common.ErrorCode;
import com.heendy.common.ErrorResponse;
import com.heendy.common.exception.MemberNotExistSession;
import com.heendy.dao.WishDAO;
import com.heendy.dto.MemberDTO;
import com.heendy.utils.SessionUserService;
import com.heendy.utils.UserService;

/**
 * @author 김시은
 * 
 * 상품과 사용자 id로 좋아요 여부를 가져오는 Action 클래스
 * 
 * */

public class WishCheckAction implements Action {

	private final WishDAO wishDAO = WishDAO.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");

		try {
			int pid = Integer.parseInt(request.getParameter("productId"));
			int cid = Integer.parseInt(request.getParameter("companyId"));

		    MemberDTO member = (MemberDTO) request.getAttribute("loginUser");
			
			// 좋아요 여부 가져오기
			int wish = wishDAO.wishCheck(member.getMemberId(), pid, cid); 
			
			JsonObject jsonObj = new JsonObject();
			jsonObj.addProperty("wish", wish);
			String json = new Gson().toJson(jsonObj);
			response.getWriter().write(json);
		} catch (SQLException e){
			int errorCode = e.getErrorCode();
			ErrorResponse errorResponse;
			
			errorResponse = ErrorResponse.of(ErrorCode.UNCAUGHT_SERVER_ERROR);
			
			String json = new Gson().toJson(errorResponse);
			response.setStatus(errorResponse.getStatus());
			response.getWriter().write(json);
		}
	}
}
