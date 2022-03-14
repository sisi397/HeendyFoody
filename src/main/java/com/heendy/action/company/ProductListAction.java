package com.heendy.action.company;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.heendy.action.Action;
import com.heendy.common.exception.MemberNotExistSession;
import com.heendy.dao.ChartDataDAO;
import com.heendy.dto.MemberDTO;
import com.heendy.dto.ProductDTO;
import com.heendy.utils.SessionUserService;
import com.heendy.utils.UserService;

public class ProductListAction implements Action {

	private final ChartDataDAO chartDataDAO = ChartDataDAO.getInstance();
	private UserService<MemberDTO, HttpSession> userService = SessionUserService.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		//MemberDTO member = this.userService.loadUser(request.getSession()).orElseThrow(MemberNotExistSession::new);
		
			int cid = 1;

			List<ProductDTO> data = chartDataDAO.productList(cid);
			
			String json = new Gson().toJson(data);
			response.getWriter().write(json);
	}

}
