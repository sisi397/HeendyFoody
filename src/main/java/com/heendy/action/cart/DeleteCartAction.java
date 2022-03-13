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
import com.heendy.utils.SessionUserService;
import com.heendy.utils.UserService;

/**
 * @author 이승준 자신의 장바구니 목록 중에서 장바구니 아이템을 삭제하는 Action 클래스
 */
public class DeleteCartAction implements Action {

	private final CartDAO cartDAO = CartDAO.getInstance();

	private UserService<MemberDTO, HttpSession> userService = SessionUserService.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		MemberDTO member = this.userService.loadUser(request.getSession()).orElseThrow(MemberNotExistSession::new);


		try {
			int cartId = Integer.parseInt(request.getParameter("cart_id"));

			cartDAO.deleteCartByCartIdAndMemberId(cartId, member.getMemberId());

			response.setStatus(201);
			response.getWriter()
					.write("{\"deleted\" : true, \"deleted_cart_id\" : " + cartId + ", \"result\" : \"장바구니 아이템이 삭제되었습니다.\"}");

		} catch (SQLException e) {
			int errorCode = e.getErrorCode();

			ErrorResponse errorResponse;
			if (errorCode == SQLErrorCode.NO_DATA_FOUND.getCode()) {
				errorResponse = ErrorResponse.of(ErrorCode.NO_DATA_FOUND);
			} else if (errorCode == SQLErrorCode.NOT_RESOURCE_OWNER.getCode()) {
				errorResponse = ErrorResponse.of(ErrorCode.NOT_RESOURCE_OWNER);
			} else {
				errorResponse = ErrorResponse.of(ErrorCode.UNCAUGHT_SERVER_ERROR);
			}

			String json = new Gson().toJson(errorResponse);
			response.setStatus(errorResponse.getStatus());
			response.getWriter().write(json);
		}

	}

}
