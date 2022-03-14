package com.heendy.action.company;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.heendy.action.Action;
import com.heendy.common.ErrorCode;
import com.heendy.common.ErrorResponse;
import com.heendy.common.exception.MemberNotExistSession;
import com.heendy.dao.ChartDataDAO;
import com.heendy.dto.MemberDTO;
import com.heendy.utils.SessionUserService;
import com.heendy.utils.UserService;


/**
 * @author 김시은
 * LineChart를 그리기 위한
 * 날짜별 주문 내역 데이터를 불러오는 Action 클래스
 * 
 */
public class OrderInfoAction implements Action {

	private final ChartDataDAO chartDataDAO = ChartDataDAO.getInstance();
	private UserService<MemberDTO, HttpSession> userService = SessionUserService.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String sort = request.getParameter("sort"); // 분류 (년도별, 월별, 일별)
			int pid = Integer.parseInt(request.getParameter("productId")); // 주문 내역을 분석할 상품 (전체상품(0) or 상품id)
			//MemberDTO member = this.userService.loadUser(request.getSession()).orElseThrow(MemberNotExistSession::new);
			
			int cid = 1; //업체 id
			
			// chartDataDAO에서 주문내역 정보를 가져옴
			List<JSONObject> data = chartDataDAO.orderInfo(cid, sort, pid);
			
			JSONObject responseObj = new JSONObject();
			responseObj.put("orderinfo", data);
			response.getWriter().write(responseObj.toString());
		}  catch (SQLException e){
			ErrorResponse errorResponse;
			
			errorResponse = ErrorResponse.of(ErrorCode.UNCAUGHT_SERVER_ERROR);
			
			String json = new Gson().toJson(errorResponse);
			response.setStatus(errorResponse.getStatus());
			response.getWriter().write(json);
		}
	}

}
