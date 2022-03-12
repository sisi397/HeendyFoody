package com.heendy.action.wish;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.heendy.action.Action;
import com.heendy.common.ErrorCode;
import com.heendy.common.ErrorResponse;
import com.heendy.common.SQLErrorCode;
import com.heendy.dao.ProductDAO;
import com.heendy.dao.WishDAO;
import com.heendy.dto.ProductDTO;

/**
 * @author 김시은
 * 
 * 좋아요 추가 Action 클래스
 * 
 * */

public class WishInsertAction implements Action{

	private final WishDAO wishDAO = WishDAO.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			int mid = Integer.parseInt(request.getParameter("memberId"));
			int pid = Integer.parseInt(request.getParameter("productId"));
			int cid = Integer.parseInt(request.getParameter("companyId"));
			
			// 좋아요 추가
			int wishInsert = wishDAO.insertWish(mid, pid, cid);
		}catch(SQLException e) {
			int errorCode = e.getErrorCode();

			ErrorResponse errorResponse;
			if (errorCode == SQLErrorCode.ALREADY_LIKE_EXIST.getCode()) {
				errorResponse = ErrorResponse.of(ErrorCode.ALREADY_LIKE_EXIST);
			} else {
				errorResponse = ErrorResponse.of(ErrorCode.UNCAUGHT_SERVER_ERROR);
			}

			String json = new Gson().toJson(errorResponse);
			response.setStatus(errorResponse.getStatus());
			response.getWriter().write(json);
		}
	}

}
