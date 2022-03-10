package com.heendy.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

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
}
