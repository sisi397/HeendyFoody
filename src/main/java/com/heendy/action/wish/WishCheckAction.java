package com.heendy.action.wish;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.heendy.action.Action;
import com.heendy.dao.WishDAO;

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
		int pid = Integer.parseInt(request.getParameter("productId"));
		int cid = Integer.parseInt(request.getParameter("companyId"));
		
		// 좋아요 여부 가져오기
		int wish = wishDAO.wishCheck(7, pid, cid); // memberid 
		
		JsonObject jsonObj = new JsonObject();
		jsonObj.addProperty("wish", wish);
		String json = new Gson().toJson(jsonObj);
		response.getWriter().write(json);
	}

}
