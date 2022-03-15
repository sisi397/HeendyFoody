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
 * PieChart를 그리기 위한
 * 업체별 상품 구매자 주 연령층 데이터를 가져오는 Action 클래스
 * 
 */
public class AgeInfoAction implements Action {

	private final ChartDataDAO chartDataDAO = ChartDataDAO.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		try{
			MemberDTO member = (MemberDTO) request.getAttribute("loginUser");

			// 구매자 연령층 정보를 chartDataDAO에서 받아옴
			List<JSONObject> data = chartDataDAO.ageInfo(member.getMemberId());
			
			JSONObject responseObj = new JSONObject();
			responseObj.put("ageinfo", data);
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
