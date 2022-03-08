package com.heendy.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import com.heendy.dto.cart.CartCountUpdateDTO;
import com.heendy.dto.cart.CreateCartDTO;
import com.heendy.utils.DBManager;

/**
 * @author : 이승준
 * @version: 1
 * 장바구니 DAO 클래스
 * */
public class CartDAO {
	
	private final static CartDAO INSTANCE = new CartDAO();
	
	private CartDAO() {}
	
	public static CartDAO getInstance() {
		return INSTANCE;
	}
	
	/**
	 * 
	 * @param data
	 * @throws SQLException
	 * 
	 * 장바구니 추가 기
	 */
	public void createCart(CreateCartDTO data) throws SQLException{
	
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
	 * 장바구니 수량 증가 기능
	 */
	public void addCartCount(CartCountUpdateDTO data) throws SQLException {
		Connection conn = DBManager.getConnection();
		
		CallableStatement cstmt = conn.prepareCall("{call sp_add_cart(?,?)}");
		
		cstmt.setInt(1, data.getCartId());
		cstmt.setInt(2, data.getCount());
		
		cstmt.execute();
		
		cstmt.close();
		conn.close();
	}
	
	/**
	 * 
	 * @param data
	 * @throws SQLException
	 * 
	 * 장바구니 수량 감소 기능
	 */
	public void minusCartCount(CartCountUpdateDTO data) throws SQLException {
		Connection conn = DBManager.getConnection();
		
		CallableStatement cstmt = conn.prepareCall("{call sp_minus_cart(?,?) }");
		
		cstmt.setInt(1, data.getCartId());
		cstmt.setInt(2, data.getCount());
		
		cstmt.execute();
		
		cstmt.close();
		conn.close();
	}
	
}
