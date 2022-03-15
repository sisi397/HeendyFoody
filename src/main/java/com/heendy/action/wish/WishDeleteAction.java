package com.heendy.action.wish;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
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
 * 좋아요 삭제 Action 클래스
 * 
 * */

public class WishDeleteAction implements Action {

	private final WishDAO wishDAO = WishDAO.getInstance();
	private UserService<MemberDTO, HttpSession> userService = SessionUserService.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		try {
			int pid = Integer.parseInt(request.getParameter("productId"));
			int cid = Integer.parseInt(request.getParameter("companyId"));
			MemberDTO member = this.userService.loadUser(request.getSession()).orElseThrow(MemberNotExistSession::new);
			// 좋아요 삭제
			int wishDelete = wishDAO.deleteWish(member.getMemberId(), pid, cid);
			
			response.setStatus(201);
			response.getWriter()
					.write("{\"deleted\" : true, \"result\" : \"좋아요 성공.\"}");
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
