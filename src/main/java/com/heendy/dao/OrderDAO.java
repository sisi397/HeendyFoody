package com.heendy.dao;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.heendy.dto.order.CreateCartOrderDTO;
import com.heendy.dto.order.CreateOrderDTO;
import com.heendy.utils.DBManager;

/**
 * @author : 이승준
 * @version : 1
 * 결제 DAO 클래스
 * */
public class OrderDAO {
	
	private final static OrderDAO INSTANCE = new OrderDAO();
	
	private OrderDAO() {}
	
	public static OrderDAO getInstance() {
		return INSTANCE;
	}
	
	
	public void createOrder(CreateOrderDTO data) throws SQLException {
		Connection conn = DBManager.getConnection();
		
		CallableStatement cstmt = conn.prepareCall("{call sp_create_order(?,?,?,?)}");
		
		cstmt.setInt(1, data.getProductId());
		cstmt.setInt(2, data.getCompanyId());
		cstmt.setInt(3, data.getMemberId());
		cstmt.setInt(4, data.getCount());
		
		cstmt.execute();
		
		cstmt.close();
		conn.close();
	}
	
	public void createCartOrder(CreateCartOrderDTO data) throws SQLException {
		Connection conn = DBManager.getConnection();
		
		
		CallableStatement cstmt = conn.prepareCall("{ call sp_create_order_from_cart(?,?) }");
		
		
		Array cartIds = ((oracle.jdbc.OracleConnection)conn).createOracleArray("USER01.CARTIDSARRAY", data.getCartIds());
		
		cstmt.setInt(1, data.getMemberId());
		cstmt.setArray(2, cartIds);
		
		cstmt.execute();
		
		
		cstmt.close();
		conn.close();
	}
}
