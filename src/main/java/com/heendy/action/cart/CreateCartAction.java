package com.heendy.action.cart;

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
import com.heendy.common.SQLErrorCode;
import com.heendy.common.exception.MemberNotExistSession;
import com.heendy.dao.CartDAO;
import com.heendy.dto.MemberDTO;
import com.heendy.dto.cart.CreateCartDTO;
import com.heendy.utils.SessionUserService;
import com.heendy.utils.UserService;

/**
 * @author 이승준 장바구니 신규 생성을 위한 Action 클래스
 */
public class CreateCartAction implements Action {

	private final CartDAO cartDAO = CartDAO.getInstance();
	
	private UserService<MemberDTO, HttpSession> userService = SessionUserService.getInstance();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");

			MemberDTO member = this.userService.loadUser(request.getSession()).orElseThrow(MemberNotExistSession::new);


			int productId = Integer.parseInt(request.getParameter("product_id"));
			int companyId = Integer.parseInt(request.getParameter("company_id"));
			int count = Integer.parseInt(request.getParameter("count"));

			CreateCartDTO data = new CreateCartDTO(productId, companyId, member.getMemberId(), count);
			this.cartDAO.createCart(data);

			response.setStatus(201);
			response.getWriter().write("{\"created\" : true, \"result\" : \"장바구니에 상품이 추가되었습니다.\"}");

		} catch (SQLException e) {
			int errorCode = e.getErrorCode();
			ErrorResponse errorResponse;
			if (errorCode == SQLErrorCode.ALREADY_CART_EXIST.getCode()) {
				errorResponse = ErrorResponse.of(ErrorCode.ALREADY_CART_EXIST);

				/* 이미 장바구니에 담겨져 있을 경우, 장바구니에 상품 수량 추가 요청을 위한 url 제공 */
				String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
						+ request.getContextPath() + "/cart/addCount.do";

				System.out.println(url);

				errorResponse.setLocation(url);
			} else if (errorCode == SQLErrorCode.LACK_OF_STOCK.getCode()) {
				errorResponse = ErrorResponse.of(ErrorCode.LACK_OF_STOCK);
			} else {
				errorResponse = ErrorResponse.of(ErrorCode.UNCAUGHT_SERVER_ERROR);
			}
			
			String json = new Gson().toJson(errorResponse);
			response.setStatus(errorResponse.getStatus());
			response.getWriter().write(json);
		}

	}

}
