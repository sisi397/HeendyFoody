package com.heendy.action.company;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.heendy.action.Action;
import com.heendy.common.exception.MemberNotExistSession;
import com.heendy.dao.ChartDataDAO;
import com.heendy.dto.MemberDTO;
import com.heendy.utils.SessionUserService;
import com.heendy.utils.UserService;

public class OrderInfoAction implements Action {

	private final ChartDataDAO chartDataDAO = ChartDataDAO.getInstance();
	private UserService<MemberDTO, HttpSession> userService = SessionUserService.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		String sort = request.getParameter("sort");
		int pid = Integer.parseInt(request.getParameter("productId"));
		//MemberDTO member = this.userService.loadUser(request.getSession()).orElseThrow(MemberNotExistSession::new);
		
			int cid = 1;
			
			List<JSONObject> data = chartDataDAO.orderInfo(cid, sort, pid);
			
			JSONObject responseObj = new JSONObject();
			responseObj.put("orderinfo", data);
			response.getWriter().write(responseObj.toString());
	}

}
