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
import com.heendy.dto.ProductDTO;
import com.heendy.utils.SessionUserService;
import com.heendy.utils.UserService;

/**
 * @author 김시은
 * 업체별 상품정보를 가져오는 Action 클래스
 * 
 */
public class ProductListAction implements Action {

	private final ChartDataDAO chartDataDAO = ChartDataDAO.getInstance();
	private UserService<MemberDTO, HttpSession> userService = SessionUserService.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			try{
				//MemberDTO member = this.userService.loadUser(request.getSession()).orElseThrow(MemberNotExistSession::new);
				int cid = 1;
				
				// chartDataDAO에서 업체별 상품정보를 가져옴
				List<ProductDTO> data = chartDataDAO.productList(cid);
				
				String json = new Gson().toJson(data);
				response.getWriter().write(json);
			}  catch (SQLException e){
				ErrorResponse errorResponse;
				
				errorResponse = ErrorResponse.of(ErrorCode.UNCAUGHT_SERVER_ERROR);
				
				String json = new Gson().toJson(errorResponse);
				response.setStatus(errorResponse.getStatus());
				response.getWriter().write(json);
			}
	}

}
