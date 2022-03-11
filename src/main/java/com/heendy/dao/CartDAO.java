package com.heendy.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.heendy.dto.cart.CartCountUpdateDTO;
import com.heendy.dto.cart.CartItemDTO;
import com.heendy.dto.cart.CreateCartDTO;
import com.heendy.utils.DBManager;

import oracle.jdbc.OracleTypes;

/**
 * @author 이승준
 * @version 1 장바구니 DAO 클래스
 */
public class CartDAO {

	private final static CartDAO INSTANCE = new CartDAO();

	private CartDAO() {
	}

	public static CartDAO getInstance() {
		return INSTANCE;
	}

	public List<CartItemDTO> getCartList(int memberId) throws SQLException {
		List<CartItemDTO> cartList = new ArrayList<>();

		Connection conn = DBManager.getConnection();

		CallableStatement cstmt = conn.prepareCall("{call sp_select_cart_list(?,?)}");

		cstmt.setInt(1, memberId);
		cstmt.registerOutParameter(2, OracleTypes.CURSOR);

		cstmt.execute();

		ResultSet rs = (ResultSet) cstmt.getObject(2);

		while (rs.next()) {
			int cartId = rs.getInt("cart_id");
			int productId = rs.getInt("product_id");
			int companyId = rs.getInt("company_id");
			int cartCount = rs.getInt("cart_count");
			int productCount = rs.getInt("product_count");
			String productName = rs.getString("product_name");
			String imgUrl = rs.getString("image_url");
			int productPrice = rs.getInt("product_price");
			int productDisCountedPrice = rs.getInt("discount_price");
			boolean isDeleted = rs.getBoolean("deleted");

			CartItemDTO cartItem = new CartItemDTO(cartId, productId, companyId, cartCount, productCount, productName, imgUrl,
					productPrice, productDisCountedPrice, isDeleted);
			
			cartList.add(cartItem);
		}
		
		rs.close();
		cstmt.close();
		conn.close();

		return cartList;
	}

	/**
	 * 
	 * @param data
	 * @throws SQLException
	 * 
	 *                      장바구니 추가 기능
	 */
	public void createCart(CreateCartDTO data) throws SQLException {

		Connection conn = DBManager.getConnection();

		CallableStatement cstmt = conn.prepareCall("{call sp_create_cart(?,?,?,?)}");

		cstmt.setInt(1, data.getProductId());
		cstmt.setInt(2, data.getCompanyId());
		cstmt.setInt(3, data.getMemberId());
		cstmt.setInt(4, data.getCount());

		cstmt.execute();

		cstmt.close();
		conn.close();

	}

	/**
	 * 
	 * @param data
	 * @throws SQLException
	 * 
	 *                      장바구니 수량 증가 기능
	 */
	public void addCartCount(CartCountUpdateDTO data) throws SQLException {
		Connection conn = DBManager.getConnection();

		CallableStatement cstmt = conn.prepareCall("{call sp_add_cart(?,?,?)}");

		cstmt.setInt(1, data.getCartId());
		cstmt.setInt(2, data.getCount());
		cstmt.setInt(3, data.getMemberId());

		cstmt.execute();

		cstmt.close();
		conn.close();
	}

	/**
	 * 
	 * @param data
	 * @throws SQLException
	 * 
	 *                      장바구니 수량 감소 기능
	 */
	public void minusCartCount(CartCountUpdateDTO data) throws SQLException {
		Connection conn = DBManager.getConnection();

		CallableStatement cstmt = conn.prepareCall("{call sp_minus_cart(?,?,?)}");

		cstmt.setInt(1, data.getCartId());
		cstmt.setInt(2, data.getCount());
		cstmt.setInt(3, data.getMemberId());

		cstmt.execute();

		cstmt.close();
		conn.close();
	}

	/**
	 * 
	 * @param cartId
	 * @throws SQLException
	 * 
	 *                      장바구니 삭제 기능
	 */
	public void deleteCartByCartIdAndMemberId(int cartId, int memberId) throws SQLException {
		Connection conn = DBManager.getConnection();

		CallableStatement cstmt = conn.prepareCall("{call sp_delete_cart(?,?)}");

		cstmt.setInt(1, cartId);
		cstmt.setInt(2, memberId);

		cstmt.execute();

		cstmt.close();
		conn.close();
	}

}
