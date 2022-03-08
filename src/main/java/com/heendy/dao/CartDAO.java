package com.heendy.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

import com.heendy.dto.cart.CreateCartDTO;
import com.heendy.utils.DBManager;

/*
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
	
	/*
	 *  장바구니에 상품 추가 기능
	 * */
	public void createCart(CreateCartDTO data) throws SQLException{
	
		Connection conn = DBManager.getConnection();
		
		
		CallableStatement cstmt = conn.prepareCall("{call sp_create_cart(?,?,?,?)}");
		
		cstmt.setInt(1, data.getProductId());
		cstmt.setInt(2, data.getCompanyId());
		cstmt.setInt(3, data.getMemberId());
		cstmt.setInt(4, data.getCount());
		
		cstmt.execute();
		
		conn.close();
		
	}
	
	
	public void addCart(int cartId) {
		Connection conn = DBManager.getConnection();
		
		
	}
	
}
